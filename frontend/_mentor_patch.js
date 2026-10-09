/* _mentor_patch.js — 导师查询页(v20260913c)
 * 背景:菜单表虽有 iframe_src 列,但本构建的菜单→路由转换器只白名单拷贝
 *      title/icon/order/component/badge/children,iframe_src / link 会被丢弃。
 *      → 因此这里在运行时给 /lab/mentor 路由补 meta.iframeSrc,
 *        由框架自带的 IFrameRouterView 渲染内嵌页面。
 * 安全:纯增量赋值;取不到 router 就静默退出,不影响任何既有功能。
 *     + 平面图导师高亮幂等兜底(见文末,规则与线上一致,不会与既有逻辑打架)。
 */
(function () {
  'use strict';
  var PATH = '/lab/mentor', NAME = 'lab_mentor', SRC = '/mentor.html?v=20260913c';
  var timer = null, tries = 0;

  function apply() {
    try {
      var host = document.querySelector('#app');
      var app = host && host.__vue_app__;
      var cfg = app && app.config;
      var gp = cfg && cfg.globalProperties;
      var router = gp && gp.$router;
      if (!router || typeof router.getRoutes !== 'function') return false;
      var routes = router.getRoutes(), hit = null, i;
      for (i = 0; i < routes.length; i++) {
        var r = routes[i];
        if (r.path === PATH || r.name === NAME) { hit = r; break; }
      }
      if (!hit) return false;
      if (!hit.meta) hit.meta = {};
      if (hit.meta.iframeSrc !== SRC) {
        hit.meta.iframeSrc = SRC;
        if (window.console && console.log) console.log('[mentor] iframeSrc ->', SRC, 'on', hit.path);
      }
      return true;
    } catch (e) { return false; }
  }

  function run(limit) {
    if (timer) return;
    tries = 0;
    timer = setInterval(function () {
      tries++;
      if (apply() || tries > limit) { clearInterval(timer); timer = null; }
    }, 500);
  }

  run(90);                                                   // 首次:最多 45s
  window.addEventListener('hashchange', function () { run(40); }); // 路由变化后再补一次

  /* ===================== 平面图导师高亮幂等兜底 =====================
   * 为什么需要:线上 __fpMv2 的 _hiMentor 只在“路由就绪”时跑一次,并把结果
   * 记在 _hiN 里,900ms 后才用 _hiN 判断是否需要自动跳实验室。若那一次跑得
   * 太早(卡片还没渲染完 / dept 还没填),高亮会整体丢失且不会重试。
   * 做法:按 500ms 幂等补齐。规则与 _hiMentor 完全一致(读 .ws-dept === ?m=),
   *      **只在状态真正变化时写 style**,不会触发 MutationObserver 死循环。
   * 边界:不是 “工位视图 + ?m=” 场景时直接 return,零开销。
   */
  var _fpM = null; // null=未进入高亮场景 '' 不存放;字符串=当前已应用的导师名
  function _fpReadM() {
    var h = location.hash || '';
    if (h.indexOf('/lab/workstations/') < 0) return '';
    var i = h.indexOf('?');
    if (i < 0) return '';
    var ps = h.slice(i + 1).split('&');
    for (var j = 0; j < ps.length; j++) {
      var p = ps[j].split('=');
      if (p[0] === 'm' && p[1]) { try { return decodeURIComponent(p[1]); } catch (e) { return p[1]; } }
    }
    return '';
  }
  function _fpTick() {
    var m = _fpReadM();
    if (!m && _fpM === null) return;                      // 非本功能场景:零开销
    var area = document.querySelector('.floor-plan-wrap .canvas-area');
    if (area) {
      var cards = area.querySelectorAll('.ws-card');
      for (var i = 0; i < cards.length; i++) {
        var c = cards[i];
        var dp = c.querySelector('.ws-dept');
        var t = dp ? (dp.innerText || '').trim() : '';
        var keep = !!m && t === m;
        var has = !!(c.style.outline && c.style.outline !== 'none');
        try {
          if (keep && !has) { c.style.outline = '3px solid #2563eb'; c.style.outlineOffset = '-2px'; }
          else if (!keep && has) { c.style.outline = ''; c.style.outlineOffset = ''; }
        } catch (e) {}
      }
    }
    _fpM = m || null;
  }
  setInterval(_fpTick, 500);
})();
