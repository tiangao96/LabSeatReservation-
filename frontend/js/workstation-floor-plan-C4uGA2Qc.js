import{A as e,D as t,I as n,Mt as r,E as vt,N as i,O as a,P as o,Q as s,Vt as c,an as l,bt as ee,k as u,nn as te,ot as ne,s as d,tt as f,x as p,y as m,yt as h}from"./vue-core-Cvh10b_J.js";import{f as g,p as _,T as AE}from"./src-wYtYYgIc.js";import{g as v}from"./src-dyk27duv.js";import{t as y}from"./src-B5Bdyw8Z.js";import{n as b}from"./src-DiZH6YyA.js";import{A as re,i as x,j as S,r as C,v as w}from"./es-CIlS_XJ_.js";import{t as T}from"./input-sHUGFxQD.js";import{t as E}from"./button-CKbEp0Le.js";import"./locales-C67JuxXT.js";import{T as D,i as O,l as k,r as ie,x as ae,y as yq,St as Ax}from"./store-CW5ocW69.js";import"./css-Cn5FfNla.js";import"./css-DMsUYNP-.js";import"./css-YlnFfZhH.js";import{a as oe,o as se,t as A}from"./floor-plan-DLPHvT9W.js";import"./css-CrfvqRIJ.js";import"./css-DyXFfiog.js";import"./css-DQhQPNjF.js";import{t as ce}from"./floor-plan-DBLIu8lK.js";import{t as j}from"./workstation-drawer-zlu7Vv88.js";var M={class:`flex items-center gap-2`},N={class:`flex h-full flex-col gap-3`},P={key:0,class:`flex flex-wrap items-center gap-2 rounded-lg border border-orange-200 bg-orange-50 p-3 dark:border-orange-900 dark:bg-orange-950`},F=n({__name:`index`,setup(n){let F=g(),I=_(),L=F.params.labId,R=r(null),z=r([]),B=r(!1),V=r(!1),H=r([]),U=r(null),W=r(!1),[G,le]=b({connectedComponent:j,onClosed:function(){setTimeout(_calcStats,0)}});s(d(function*(){B.value=!0;try{let[e,t]=yield Promise.all([D(L),k(L)]);R.value=e;let _rm={};try{let _rs=yield yq({status:'APPROVED'});for(let _r of _rs||[]){if(!_r.studentName)continue;for(let _w of _r.workstations||[]){if(_w&&_w.workstationNanoId)_rm[_w.workstationNanoId]={name:_r.studentName,mentor:_r.mentorName,grade:_r.gradeName,endMonth:_r.endMonth}}}for(let _w of t){let _ri=_rm[_w.id];if(_ri&&String(_w.status).toUpperCase()===`OCCUPIED`){if(!_w.currentUser)_w.currentUser=_ri.name;if(!_w.mentorName)_w.mentorName=_ri.mentor;if(!_w.gradeName)_w.gradeName=_ri.grade;if(!_w.reservedEndMonth)_w.reservedEndMonth=_ri.endMonth}}}catch(_e){}z.value=t;_calcStats()}finally{B.value=!1}}));function ue(){_flt.value=[],V.value=!0,(function(){try{var _b=document.getElementById('__lg-ov');if(_b){_b.style.display='none'}}catch(_e){}})();H.value=z.value.map(e=>{let t=oe(e),n=t===`workstation`;return{id:e.id,type:t,x:e.position.x,y:e.position.y,width:n?A.width:e.position.width,height:n?A.height:e.position.height,name:e.name,status:n?e.status:void 0,occupantMentorGroupName:e.occupantMentorGroupName,reservedStartMonth:e.reservedStartMonth,reservedEndMonth:e.reservedEndMonth,currentUserName:e.currentUser}}),U.value=null,setTimeout(_injectEditBar,0)}function _removeEditBar(){let _el=document.getElementById(`__edit-actions`);_el&&_el.remove()}function _injectEditBar(){try{if(document.getElementById(`__edit-actions`))return;let _w=document.createElement(`div`),_b1=document.createElement(`button`),_b2=document.createElement(`button`);_w.id=`__edit-actions`;_w.style.cssText=`position:fixed;top:12px;left:50%;transform:translateX(-50%);z-index:99999;display:flex;align-items:center;gap:8px;padding:6px 10px;background:#fff;border:1px solid #e4e7ed;border-radius:8px;box-shadow:0 2px 12px rgba(0,0,0,.15)`;_b1.type=`button`;_b1.className=`el-button el-button--small`;_b1.textContent=`退出编辑`;_b1.onclick=function(){window.__de&&window.__de()};_b2.type=`button`;_b2.className=`el-button el-button--primary el-button--small`;_b2.textContent=`保存布局`;_b2.onclick=function(){window.__ge&&window.__ge()};_w.appendChild(_b1),_w.appendChild(_b2),document.body.appendChild(_w)}catch(e){console.log(`[EDITBAR]`,e.message)}}function de(){V.value=!1,U.value=null,_removeEditBar(),_refreshLegend()}function fe(e){V.value||(le.setData({workstation:e,canManage:(AE().accessCodes||[]).includes(`workstation:manage`)}),le.open())}function pe(e){U.value=e}function me(e){if(H.value=e,U.value){let t=U.value.id;U.value=e.find(e=>e.id===t)||null}}function he(){return K.apply(this,arguments)}function K(){return K=d(function*(){if(!U.value)return;let e=U.value.id;try{yield C.confirm(y(`page.lab.confirmDelete`),y(`page.lab.deleteSelected`),{type:`warning`})}catch(e){return}try{!e.startsWith(`new-`)&&!e.startsWith(`default-`)&&(yield O(e),x.success(y(`page.lab.deleteSuccess`))),H.value=H.value.filter(t=>t.id!==e),U.value=null}catch(e){}}),K.apply(this,arguments)}function ge(){return q.apply(this,arguments)}function q(){return q=d(function*(){W.value=!0;try{yield ae({labId:L,elements:H.value}),z.value=yield k(L),x.success(y(`page.lab.saveSuccess`)),V.value=!1,_removeEditBar()}finally{W.value=!1}}),q.apply(this,arguments)}let J=r(!1),Y=r(`workstation`),X=r(!1),Z=r({code:``,description:``,occupantName:``,mentorName:``,endMonth:``}),_mgAdd=r([]),_e={workstation:{w:A.width,h:A.height},door:{w:130,h:100},window:{w:300,h:50},aisle:{w:300,h:50}};function Q(e=`workstation`){Y.value=e,Z.value={code:``,description:``,occupantName:``,mentorName:``,endMonth:``},J.value=!0,Ax.get(`/mentor-groups`).then(tr=>{let raw=(tr&&tr.data)||tr||[],flat=[];!function w(n){for(let g of n||[]){flat.push({id:g.nanoId,name:g.name}),w(g.children)}}(raw);_mgAdd.value=flat}).catch(()=>{})};function _nxt(t){let p=t===`aisle`?`AISLE`:t===`door`?`DOOR`:`WINDOW`,mx=0;function _up(_n){if(_n){_n=String(_n).toUpperCase();if(_n.startsWith(p+`-`)){let _k=parseInt(_n.slice(p.length+1),10);if(!isNaN(_k)&&_k>mx)mx=_k}}}for(let _o of z.value||[])_up(_o.code||_o.name);for(let _o of H.value||[])_up(_o.name||_o.code);return p+`-`+String(mx+1).padStart(2,`0`)}function ad(e){let t=e===`door`?`door`:e===`window`?`window`:`aisle`,m={door:{code:`门`,desc:`门`},window:{code:`窗`,desc:`窗`},aisle:{code:`过道`,desc:`过道`}}[t];Y.value=t,Z.value={code:_nxt(t),description:m.desc,occupantName:``,mentorName:``,endMonth:``},$()}function ve(){return $.apply(this,arguments)}function $(){return $=d(function*(){let e=Z.value.code.trim();if(!e){x.warning(y(`page.lab.codeRequired`));return}X.value=!0;try{let t=Y.value,{w:n,h:r}=_e[t],i=yield ie({code:e,labNanoId:L,type:t===`workstation`?void 0:t===`aisle`?`WINDOW`:t.toUpperCase(),description:t===`aisle`?`过道`:Z.value.description.trim()||void 0,posX:100,posY:100,width:n,height:r}),a=t===`door`?`door`:t===`window`?`window`:t===`aisle`?`aisle`:`workstation`,o={id:i,type:a,x:100,y:100,width:n,height:r,name:e,status:a===`workstation`?(Z.value.occupantName.trim()?se.OCCUPIED:se.AVAILABLE):void 0};let _oc=Z.value.occupantName.trim(),_mg=Z.value.mentorName.trim(),_em=Z.value.endMonth.trim();try{if(_oc)yield Ax.put(`/workstations/${i}/occupant-name?name=${encodeURIComponent(_oc)}`)}catch(_e){}_oc&&(o.currentUser=_oc,o.currentUserName=_oc);if(_mg&&_mg!==`未分配`){let _g=_mgAdd.value.find(g=>g.name===_mg);try{yield Ax.put(`/workstations/${i}/mentor-group`,{mentorGroupNanoId:_g?_g.id:null,mentorGroupName:_g?null:_mg})}catch(_e){}o.occupantMentorGroupName=_mg,o.mentorName=_mg}try{if(_em)yield Ax.put(`/workstations/${i}/reserved-end-month?month=${encodeURIComponent(_em)}`)}catch(_e){}_em&&(o.reservedEndMonth=_em);H.value=[...H.value,o],U.value=o,x.success(y(`page.lab.createSuccess`)),J.value=!1}finally{X.value=!1}}),$.apply(this,arguments)}function ye(){I.push({name:`lab_list`})}function _oc(o){let _s=String(o.status||``).toUpperCase();return _s===`OCCUPIED`||!!(o.currentUser||o.currentUserName||o.occupantName||o.manualOccupantName)}function _pr(o){let _s=String(o.status||``).toUpperCase();return !_oc(o)&&(_s===`PRE_RESERVED`||_s===`RESERVED`)}let _statsRef=r({total:0,groups:[]});let _flt=r([]);function _applyFilterDom(){try{var _sel=_flt.value,_el=document.querySelector('.floor-plan-wrap .canvas-area');if(!_el){return}var _cards=_el.querySelectorAll('.ws-card');for(var _i=0;_i<_cards.length;_i++){var _c=_cards[_i],_dp=_c.querySelector('.ws-dept'),_g=_dp?_dp.innerText.trim():'';var _keep=!_sel.length||_sel.indexOf(_g)>=0;if(_c.style.display==='none'&&_keep){_c.style.display=''}else if(!_keep){_c.style.display='none'}}}catch(_e){console.log('[FLT-ERR]',_e.message)}}function _applyLegendDom(){try{var _el=document.querySelector('.floor-plan-wrap');if(!_el){return}var _box=document.getElementById('__lg-ov');if(!_box){_box=document.createElement('div');_box.id='__lg-ov';_box.style.cssText='position:fixed;z-index:9990;width:250px;max-height:calc(100vh - 200px);overflow-y:auto;border:1px solid #e5e7eb;border-radius:10px;background:rgba(255,255,255,.96);padding:10px 8px;display:flex;flex-direction:column;gap:2px;box-shadow:0 4px 16px rgba(0,0,0,.08);font-family:inherit';document.body.appendChild(_box)}if(!window.__lgObs){window.__lgObs=1;var _obs=new MutationObserver(function(){try{if(!document.querySelector('.floor-plan-wrap .canvas-area')){var _b2=document.getElementById('__lg-ov');if(_b2){_b2.remove()}}}catch(_e2){}});_obs.observe(document.body,{childList:true,subtree:true})}var _r=_el.getBoundingClientRect();var _mR=0;var _cands=_el.querySelectorAll('.ws-card,.element-card.ws-card');var _alt=_el.querySelectorAll('.window-card,.door-card,.aisle-card');if(!_cands.length){_cands=_alt}for(var _i=0;_i<_cands.length;_i++){var _cr=_cands[_i].getBoundingClientRect();if(_cr.width>10&&_cr.right>_mR){_mR=_cr.right}}var _left=_mR+8;var _capL=window.innerWidth-_box.offsetWidth-8;if(!(_mR>0)){_mR=_r.right}if(_left>_capL){_left=_capL}if(_left<8){_left=8}_box.style.top=Math.max(88,_r.top+6)+'px';_box.style.left=_left+'px';var _sel=_flt.value,_gs=_statsRef.value.groups||[],_h='';_h+='<div style="display:flex;align-items:center;justify-content:space-between;padding:2px 4px 8px;font-size:13px;font-weight:600"><span>导师图例</span><span style="font-size:10px;font-weight:400;color:#9ca3af">点击行多选 · 再点取消</span></div>';_h+='<div class="__lg-r" data-i="-1" style="display:flex;align-items:center;justify-content:space-between;cursor:pointer;padding:6px 8px;border-radius:6px;font-size:12px;font-weight:500;background:'+(_sel.length===0?'#eff6ff':'transparent')+';box-shadow:'+(_sel.length===0?'inset 0 0 0 1px #93c5fd':'none')+'"><span>全部</span><span style="color:#9ca3af">'+_statsRef.value.total+'个</span></div>';for(var _i=0;_i<_gs.length;_i++){var _g=_gs[_i],_on=_sel.indexOf(_g.name)>=0;_h+='<div class="__lg-r" data-i="'+_i+'" style="display:flex;flex-direction:column;cursor:pointer;padding:6px 8px;border-radius:6px;font-size:12px;background:'+(_on?'#eff6ff':'transparent')+';box-shadow:'+(_on?'inset 0 0 0 1px #93c5fd':'none')+'">';_h+='<div style="display:flex;align-items:center;justify-content:space-between;font-weight:500"><span>'+_g.name+'</span><span style="color:#9ca3af">'+_g.count+'个</span></div>';_h+='<div style="display:flex;flex-wrap:wrap;align-items:center;gap:2px 12px;margin-top:2px;font-size:11px;line-height:16px">';if(_g.occupied>0){_h+='<span style="color:#22c55e">● 占用 '+_g.occupied+'</span>'}if(_g.pre>0){_h+='<span style="color:#f59e0b">● 预占 '+_g.pre+'</span>'}if(_g.idle>0){_h+='<span style="color:#9ca3af">● 空闲 '+_g.idle+'</span>'}_h+='</div></div>'}_box.innerHTML=_h;if(!_box.__b){_box.__b=1;_box.addEventListener('click',function(_ev){var _t=_ev.target;while(_t&&_t!==_box&&String(_t.className||'').indexOf('__lg-r')!==0){_t=_t.parentElement}if(_t&&_t!==_box){var _k=_t.getAttribute('data-i');if(_k==='-1'){window.__flt&&window.__flt('')}else if(_k!=null&&_gs[Number(_k)]){window.__flt&&window.__flt(_gs[Number(_k)].name)}}})}}catch(_e){console.log('[LG-ERR]',_e.message)}}function _refreshLegend(){setTimeout(_applyLegendDom,0);setTimeout(_applyFilterDom,0);setTimeout(_applyLegendDom,500);setTimeout(_applyFilterDom,500)}function _calcStats(){let e=(z.value||[]).filter(e=>{var t;return((t=e.type)==null?void 0:t.toUpperCase())==`SEAT`||e.type==null}),t={};for(let n=0;n<e.length;n++){let o=e[n],i=o.occupantMentorGroupName||`未分配`,a=t[i]||(t[i]={count:0,occupied:0,pre:0,idle:0});a.count++,_oc(o)?a.occupied++:_pr(o)?a.pre++:a.idle++}let r=Object.keys(t).sort();_statsRef.value={total:e.length,groups:r.map(n=>({name:n,count:t[n].count,occupied:t[n].occupied,pre:t[n].pre,idle:t[n].idle}))};setTimeout(_applyStatsDom,0);setTimeout(_applyStatsDom,800);_refreshLegend()}window.__flt=function(_n,_f){var _a=_flt.value||[];if(_n===''){_a=[]}else if(_f===1){if(_a.indexOf(_n)<0){_a.push(_n)}}else{var _x=_a.indexOf(_n);if(_x>=0){_a.splice(_x,1)}else{_a.push(_n)}}_flt.value=_a;_refreshLegend()},window.__de=de,window.__ge=ge,document.addEventListener(`keydown`,function(e){if(e.key===`Escape`&&V.value)de()});function _applyStatsDom(){try{let _labArea=R.value&&R.value.area;if(_labArea!=null){let _spans2=document.querySelectorAll('span');for(let _s2 of _spans2){if(_s2.innerText==='面积'&&_s2.parentElement){let _v2=_s2.parentElement.children[1];if(_v2)_v2.innerText=String(_labArea)+' m²'}}}let _spans=document.querySelectorAll('span');for(let _s of _spans){if(_s.innerText==='工位总数'&&_s.parentElement){let _v=_s.parentElement.children[1];if(_v)_v.innerText=String(_statsRef.value.total)}}let _box=document.querySelector('._stats-groups');if(_box){let _html='';for(let _g of _statsRef.value.groups){_html+='<div class="rounded-md bg-gray-50 px-2 py-1 text-xs dark:bg-gray-800">'+_g.name+' '+_g.count+'个'+(Number(_g.occupied)>0?' · 占用'+_g.occupied:'')+(Number(_g.pre||0)>0?' · 预占'+_g.pre:'')+(Number(_g.idle||0)>0?' · 空闲'+_g.idle:'')+'</div>'}_box.innerHTML=_html}}catch(_e){console.log('[DOM-ERR]',_e.message)}};;return(n,r) => {var s;let d=ne(`loading`);return f(),a(c(v),{title:((s=R.value)==null?void 0:s.name)||c(y)(`page.lab.workstations`),description:R.value?`${R.value.location||``}`:``,"auto-content-height":``},{extra:h(()=>[t(`div`,M,[V.value?u(``,!0):(f(),a(c(E),{key:0,type:`primary`,size:`small`,onClick:ue},{default:h(()=>[i(l(c(y)(`page.lab.editMode`)),1)]),_:1})),o(c(E),{size:`small`,onClick:ye},{default:h(()=>[i(l(c(y)(`page.lab.backToList`)),1)]),_:1})])]),default:h(()=>[t(`div`,N,[V.value?f(``,!0):(f(),e(`div`,{class:`flex flex-wrap items-center gap-x-4 gap-y-2 rounded-lg border bg-white px-4 py-2 dark:bg-gray-900`},[t(`div`,{class:`text-base font-semibold`},`实验室概况`),t(`div`,{class:`flex items-center gap-1 text-sm`},[t(`span`,{class:`text-gray-500`},`面积`),t(`span`,{class:`font-medium`},((s=R.value)==null?void 0:s.area)!=null?`${R.value.area} m²`:`未配置`)]),t(`div`,{class:`flex items-center gap-1 text-sm`},[t(`span`,{class:`text-gray-500`},`工位总数`),t(`span`,{class:`font-medium`},`${(()=>{try{let s=(z.value||[]).filter(e=>{var t;return((t=e.type)==null?void 0:t.toUpperCase())==`SEAT`||e.type==null});return s.length}catch(e){return `0`}})()}`)]),t(`div`,{class:`h-4 w-px bg-gray-200 dark:bg-gray-700`}),t(`div`,{class:`text-sm font-semibold`},`导师占用`),t(`div`,{class:`_stats-groups flex flex-wrap gap-2`},(()=>{try{let e=(z.value||[]).filter(e=>{var t;return((t=e.type)==null?void 0:t.toUpperCase())==`SEAT`||e.type==null}),t={};for(let n=0;n<e.length;n++){let o=e[n],i=o.occupantMentorGroupName||`未分配`,a=t[i]||(t[i]={count:0,occupied:0,idle:0});a.count++,_oc(o)?a.occupied++:a.idle++}let r=Object.keys(t).sort();return r.map(n=>t(`div`,{key:n,class:`rounded-md bg-gray-50 px-2 py-1 text-xs dark:bg-gray-800`},`${l(n)} ${t[n].count}个 · 占用${t[n].occupied}`))}catch(e){return[]}})())])),ee((f(),e(`div`,{class:te([`flex-1 overflow-hidden rounded-lg border bg-white p-2 dark:bg-gray-900`,V.value?`border-orange-300`:``])},[o(ce,{workstations:z.value,"edit-mode":V.value,elements:V.value?H.value:void 0,onSelect:fe,"onUpdate:elements":me,onSelectElement:pe},null,8,[`workstations`,`edit-mode`,`elements`])],2)),[[d,B.value]]),V.value?(f(),e(`div`,P,[o(c(E),{size:`small`,onClick:r[0]||(r[0]=e=>Q(`workstation`))},{default:h(()=>[i(` + `+l(c(y)(`page.lab.addWorkstation`)),1)]),_:1}),o(c(E),{size:`small`,onClick:r[1]||(r[1]=e=>ad(`door`))},{default:h(()=>[i(` + `+l(c(y)(`page.lab.addDoor`)),1)]),_:1}),o(c(E),{size:`small`,onClick:r[2]||(r[2]=e=>ad(`window`))},{default:h(()=>[i(` + `+l(c(y)(`page.lab.addWindow`)),1)]),_:1}),o(c(E),{size:`small`,onClick:r[3]||(r[3]=e=>ad(`aisle`))},{default:h(()=>[i(` + `+l(c(y)(`page.lab.addAisle`)),1)]),_:1}),r[8]||(r[8]=t(`div`,{class:`flex-1`},null,-1)),o(c(E),{size:`small`,type:`danger`,disabled:!U.value,onClick:he},{default:h(()=>[i(l(c(y)(`page.lab.deleteSelected`)),1)]),_:1},8,[`disabled`])])):u(``,!0)]),o(c(w),{modelValue:J.value,"onUpdate:modelValue":r[7]||(r[7]=e=>J.value=e),title:c(y)(`page.lab.createWorkstation`),width:`420px`,"append-to-body":``},{footer:h(()=>[o(c(E),{onClick:r[6]||(r[6]=e=>J.value=!1)},{default:h(()=>[i(l(c(y)(`common.cancel`)),1)]),_:1}),o(c(E),{type:`primary`,loading:X.value,onClick:ve},{default:h(()=>[i(l(c(y)(`common.confirm`)),1)]),_:1},8,[`loading`])]),default:h(()=>[o(c(re),{"label-width":`80px`,onSubmit:r[5]||(r[5]=m(()=>{},[`prevent`]))},{default:h(()=>[o(c(S),{label:c(y)(`page.lab.code`)},{default:h(()=>[o(c(T),{modelValue:Z.value.code,"onUpdate:modelValue":r[14]||(r[14]=e=>Z.value.code=e),placeholder:c(y)(`page.lab.codePlaceholder`)},null,8,[`modelValue`,`placeholder`])]),_:1},8,[`label`]),o(c(S),{label:c(y)(`page.lab.remark`)},{default:h(()=>[o(c(T),{modelValue:Z.value.description,"onUpdate:modelValue":r[4]||(r[4]=e=>Z.value.description=e),type:`textarea`,rows:3,placeholder:c(y)(`page.lab.remarkPlaceholder`)},null,8,[`modelValue`,`placeholder`])]),_:1},8,[`label`]),Y.value===`workstation`?[o(c(S),{key:9,label:`使用学生`},{default:h(()=>[o(c(T),{modelValue:Z.value.occupantName,"onUpdate:modelValue":r[11]||(r[11]=e=>Z.value.occupantName=e),placeholder:`学生姓名（选填）`,class:`w-full`},null,8,[`modelValue`,`placeholder`])]),_:1}),o(c(S),{key:10,label:`导师`},{default:h(()=>[o(c(T),{modelValue:Z.value.mentorName,"onUpdate:modelValue":r[12]||(r[12]=e=>Z.value.mentorName=e),placeholder:`选择或输入导师（选填）`,class:`w-full`,list:`mg-add-list`},null,8,[`modelValue`,`placeholder`]),o(`datalist`,{id:`mg-add-list`},_mgAdd.value.map(g=>o(`option`,{value:g.name})))]),_:1}),o(c(S),{key:11,label:`预计释放`},{default:h(()=>[o(c(T),{modelValue:Z.value.endMonth,"onUpdate:modelValue":r[13]||(r[13]=e=>Z.value.endMonth=e),placeholder:`如 2026-12（选填）`,class:`w-full`},null,8,[`modelValue`,`placeholder`])]),_:1})]:u(``,!0)]),_:1})]),_:1},8,[`modelValue`,`title`]),V.value?u(``,!0):(f(),a(c(G),{key:0}))]),_:1},8,[`title`,`description`])}}});export{F as default};











;(function(){try{
if(window.__fpMv2)return;window.__fpMv2=1;
function _q(k){
  var h=location.hash||'';
  var i=h.indexOf('?');
  if(i<0)return '';
  var q=h.slice(i+1);
  var ps=q.split('&');
  for(var j=0;j<ps.length;j++){
    var kv=ps[j].split('=');
    if(kv[0]===k&&kv[1]){try{return decodeURIComponent(kv[1]||'')}catch(e){return kv[1]||''}}
  }
  return '';
}
var _mlRaw='';
function _ml(){
  var raw=_q('ml');
  _mlRaw=raw;
  if(!raw)return null;
  var out=[];
  raw.split(',').forEach(function(p){
    var c=p.indexOf(':');
    if(c>0){var nm='';try{nm=decodeURIComponent(p.slice(c+1))}catch(e){nm=p.slice(c+1)}out.push({id:p.slice(0,c),name:nm})}
  });
  return out.length?out:null;
}
function _curLab(){
  var mm=location.hash.match(/workstations\/([^?]+)/);
  return mm?mm[1]:'';
}
var _applied='',_lastTotal=-1,_lastLab='',_t=null,_t2=null,_skipCount=0;
function _jumpTo(labs,m,notId){
  for(var j=0;j<labs.length;j++){
    if(labs[j].id!==notId){
      location.hash='#/lab/workstations/'+labs[j].id+'?m='+encodeURIComponent(m)+'&auto=1'+(_mlRaw?'&ml='+encodeURIComponent(_mlRaw):'');
      return true;
    }
  }
  return false;
}
function _applyOnce(m,labs,auto){
  try{
    if(!m)return;
    _hiMentor(m);
    clearTimeout(_t2);
    _t2=setTimeout(function(){
      try{
        var vis=_hiCount();
        if(vis===0&&auto&&labs&&labs.length>1&&_skipCount<2){_skipCount++;_jumpTo(labs,m,_curLab())}
        else if(vis>0){_skipCount=0}
      }catch(e){}
    },900);
  }catch(e){}
}
function _bar(labs,m){
  try{
    if(!m)return;
    if(document.getElementById('__mgBar'))return;
    var btns=document.querySelectorAll('button');
    var anchor=null;
    for(var i=0;i<btns.length;i++){if((btns[i].innerText||'').indexOf('返回列表')>=0){anchor=btns[i];break}}
    if(!anchor)return;
    var tb=anchor;
    while(tb&&tb!==document.body&&tb.querySelectorAll('button').length<2){tb=tb.parentElement}
    if(!tb||tb===document.body)return;
    var curId=_curLab();
    var h='<div id="__mgBar" style="display:flex;align-items:center;gap:6px;flex-wrap:wrap;padding:6px 12px;margin-bottom:10px;background:#fff;border:1px solid #e5e7eb;border-radius:8px;font-size:13px">';
    h+='<span style="color:#6b7280;white-space:nowrap">该导师涉及实验室：</span>';
    for(var j=0;j<labs.length;j++){
      var on=labs[j].id===curId;
      h+='<a href="#/lab/workstations/'+labs[j].id+'?m='+encodeURIComponent(m)+(_mlRaw?'&ml='+encodeURIComponent(_mlRaw):'')+'" style="'+(on?'background:#1d4ed8;color:#fff':'background:#eff6ff;color:#1d4ed8')+';padding:2px 12px;border-radius:12px;text-decoration:none;font-weight:500">'+labs[j].name+'</a>';
    }
    h+='</div>';
    var el=document.createElement('div');
    el.innerHTML=h;
    var bar=el.firstChild;
    tb.parentElement.insertBefore(bar,tb.nextSibling);
  }catch(e){}
}
var _hiN=0;
function _hiDept(card){
  var dp=card.querySelector('.ws-dept');
  var t=dp?(dp.innerText||'').trim():'';
  return t;
}
function _hiMentor(m){
  try{
    _hiN=0;
    var cards=document.querySelectorAll('.floor-plan-wrap .canvas-area .ws-card');
    for(var i=0;i<cards.length;i++){
      var c=cards[i];
      var keep=_hiDept(c)===m;
      try{
        if(keep){c.style.outline='3px solid #2563eb';c.style.outlineOffset='-2px';_hiN++}
        else{c.style.outline='';c.style.outlineOffset=''}
      }catch(e){}
    }
  }catch(e){}
}
function _hiCount(){return _hiN}
function _hiClear(){try{var cards=document.querySelectorAll('.floor-plan-wrap .canvas-area .ws-card');for(var i=0;i<cards.length;i++){try{cards[i].style.outline='';cards[i].style.outlineOffset=''}catch(e){}}}catch(e){}_hiN=0}
function _try(){
  try{
    var lab=_curLab();
    if(lab!==_lastLab){
      _lastLab=lab;_applied='';_lastTotal=-1;_skipCount=0;
      var ob=document.getElementById('__mgBar');
      if(ob&&ob.parentNode){ob.parentNode.removeChild(ob)}
    }
    var cards=document.querySelectorAll('.floor-plan-wrap .canvas-area .ws-card').length;
    var m=_q('m');
    if(!m){_applied='';_hiClear();return}
    if(cards<2){_applied='';return}
    var labs=_ml();
    if(labs&&labs.length>1){_bar(labs,m)}
    if(_applied===m&&cards===_lastTotal)return;
    _lastTotal=cards;
    _applied=m;
    if(_t)clearTimeout(_t);
    var auto=_q('auto')==='1';
    _t=setTimeout(function(){_applyOnce(m,labs,auto)},600);
  }catch(e){}
}
setInterval(_try,700);
}catch(_e){console.log('[MGV2-ERR]',_e&&_e.message)}})();
/*__LABPICK_V1__*/
;(function(){try{
if(window.__labPick)return;window.__labPick=1;
var AUTH=null,LABS=null,SEL=null;
try{
  var _sh=XMLHttpRequest.prototype.setRequestHeader;
  XMLHttpRequest.prototype.setRequestHeader=function(k,v){try{if(String(k).toLowerCase()==='authorization'&&v)AUTH=String(v)}catch(_e){}return _sh.apply(this,arguments)};
}catch(_e){}
try{
  if(window.fetch){var _of=window.fetch;window.fetch=function(){try{var o=arguments[1]||{},h=o.headers||{},a=h.Authorization||h.authorization;if(a)AUTH=String(a)}catch(_e){}return _of.apply(this,arguments)}}
}catch(_e){}
function _curLab(){var m=location.hash.match(/workstations\/([^?]+)/);return m?m[1]:''}
function _anchor(){
  var bs=document.querySelectorAll('button');
  for(var i=0;i<bs.length;i++){
    if((bs[i].innerText||'').replace(/\s+/g,'').indexOf('返回列表')>=0){
      var up=bs[i];
      while(up&&up!==document.body&&up.querySelectorAll('button').length<2)up=up.parentElement;
      if(up&&up!==document.body&&up.parentElement)return up;
    }
  }
  return null;
}
function _targetName(id){var r='';(LABS||[]).forEach(function(l){if(l.id===id)r=l.name});return r}
function _titleHas(nm){
  try{
    if(!nm)return false;
    var t=(document.body.innerText||'').replace(/\s+/g,'');
    return t.indexOf(nm.replace(/\s+/g,''))>=0;
  }catch(_e){}
  return false;
}
function _mkSel(){
  var s=document.createElement('select');
  s.id='__labPickSel';
  s.setAttribute('title','切换实验室房间号');
  s.style.cssText='height:32px;border:1px solid #d1d5db;border-radius:6px;padding:0 8px;font-size:13px;background:#fff;color:#111827;outline:none;cursor:pointer;margin-right:8px;min-width:150px';
  s.addEventListener('change',function(){
    var v=s.value;if(!v||v===_curLab())return;
    var nm=_targetName(v);
    location.hash='#/lab/workstations/'+v;
    setTimeout(function(){
      if(!_titleHas(nm)){try{location.reload()}catch(_e){}}
    },900);
  });
  return s;
}
function _fill(){
  var host=_anchor();
  if(!host)return;
  if(SEL&&!SEL.isConnected)SEL=null;
  if(!SEL){SEL=_mkSel();host.insertBefore(SEL,host.firstChild)}
  if(LABS&&LABS.length){
    var key=LABS.map(function(l){return l.id}).join(',');
    if(SEL.getAttribute('data-k')!==key){
      var h='<option value="">选择实验室</option>';
      for(var i=0;i<LABS.length;i++){h+='<option value="'+LABS[i].id+'">'+LABS[i].name+'</option>'}
      SEL.innerHTML=h;SEL.setAttribute('data-k',key);
    }
    var c=_curLab();
    if(SEL.value!==c)SEL.value=c;
  }
}
function _setLabs(d){
  var arr=(d||[]).filter(function(l){return l&&l.name&&l.nanoId});
  if(arr.length)LABS=arr.map(function(l){return{id:l.nanoId,name:l.name,code:l.code}});
}
function _load(){
  if(LABS)return;
  var done=false;
  function ok(d){if(done)return;done=true;try{_setLabs(d)}catch(_e){}}
  try{Promise.resolve(Ax.get('/labs')).then(function(r){ok((r&&r.data)||r)}).catch(function(_e){})}catch(_e){}
  if(AUTH){
    try{fetch('/api/labs',{headers:{Authorization:AUTH}}).then(function(r){return r.json()}).then(function(j){ok((j&&j.data)||j)}).catch(function(_e){})}catch(_e){}
  }
}
function _tick(){try{_load();_fill()}catch(_e){}}
setTimeout(_tick,200);setTimeout(_tick,1200);
setInterval(_tick,700);
}catch(e){console.log('[LABPICK-FATAL]',e&&e.message)}})();
