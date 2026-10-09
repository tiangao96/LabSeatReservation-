import{A as e,D as t,I as n,Mt as r,N as i,O as a,P as o,Vt as s,an as c,bt as l,k as u,ot as d,s as f,tt as p,yt as m}from"./vue-core-Cvh10b_J.js";import{g as h}from"./src-dyk27duv.js";import{c as g,i as _,l as v,p as y}from"./es-CIlS_XJ_.js";import{t as b}from"./date-picker-Cffui1gm.js";import{a as x,rt as pe}from"./store-CW5ocW69.js";import"./css-DxJbqgI8.js";import"./css-DKGBNAKb.js";import"./css-YlnFfZhH.js";import"./css-4ZWsjPzA.js";import{t as S}from"./page-header-Nq3zarsY.js";import{t as C}from"./mentor-group-selector-C389TF9Y.js";import{St as hh}from"./store-CW5ocW69.js";var w={style:{width:`260px`}},T={class:`font-medium text-primary`},E={key:1,class:`mt-4 flex flex-wrap items-center justify-between gap-2 rounded-lg border border-gray-200 bg-gray-50 px-4 py-3 dark:border-gray-700 dark:bg-gray-900`},D={class:`flex flex-wrap items-center gap-x-4 gap-y-1 text-sm text-gray-600 dark:text-gray-400`},O={class:`text-gray-900 dark:text-gray-100`},k={class:`text-gray-900 dark:text-gray-100`},A={class:`text-gray-900 dark:text-gray-100`},j={class:`text-sm`},M={class:`text-lg font-semibold text-primary`},N=n({__name:`index`,setup(n){let N=R(),P=r(``),F=r([N.slice(0,4)+`-01`,N]),I=r(null),L=r(!1),Y=r(N.slice(0,4)),Yl=Array.from({length:7},(_,i)=>String(+N.slice(0,4)-6+i));let J=r(!1),W=r(null),H=r([]),U=r({teacherName:``,labCode:``,quantity:1,startMonth:``,endMonth:``,area:null,remark:``});function R(){let e=new Date;return`${e.getFullYear()}-${String(e.getMonth()+1).padStart(2,`0`)}`}function z(){return P.value?B.apply(this,arguments):K.apply(this,arguments)}function B(){return B=f(function*(){if(!P.value){_.warning(`请选择导师组`);return}let e=F.value,t=e==null?void 0:e[0],n=e==null?void 0:e[1];if(!t||!n){_.warning(`请选择月份范围`);return}L.value=!0;try{let q=yield x({mentorGroupNanoId:P.value,startMonth:t,endMonth:n});if(q&&q.items)for(let it of q.items)it.mentorGroupName=q.mentorGroupName||P.value;q.agg=window.__uagApply(UsAgg(q.items||[]),Y.value),I.value=q}catch(e){}finally{L.value=!1}}),B.apply(this,arguments)}function K(){return $.apply(this,arguments)}function $(){return $=f(function*(){let e=F.value,t=e==null?void 0:e[0],n=e==null?void 0:e[1];if(!t||!n){_.warning(`请选择月份范围`);return}if(L.value)return;L.value=!0;try{yield Xe();let a=(yield pe())||[],s=[];!function walk(o){for(let e of o){(!e.children||!e.children.length)&&s.push(e),e.children&&e.children.length&&walk(e.children)}}(a);let r=yield Promise.all(s.map(e=>x({mentorGroupNanoId:e.id,startMonth:t,endMonth:n}).catch(()=>null))),all={items:[],distinctStudentCount:0,distinctWorkstationCount:0,totalWorkstationMonths:0,totalAreaMonths:0},stu=new Set,ws=new Set;for(let i=0;i<s.length;i++){let g=r[i];if(g&&g.items)for(let it of g.items){it.mentorGroupName=s[i].name,it.mentorGroupNanoId=s[i].id;if(window.__ulKeepItem&&!window.__ulKeepItem(it))continue;all.items.push(it),stu.add(it.studentUserName||it.studentUserNanoId),ws.add(it.workstationNanoId),all.totalWorkstationMonths+=(it.durationMonths||0),all.totalAreaMonths+=(Number(it.areaMonths)||0)}}if(H.value.length)for(let q2 of H.value){if(window.__ulKeepItem&&!window.__ulKeepItem({labName:q2.labCode,mentorGroupName:q2.teacherName||`手动补充`}))continue;let ms=q2.startMonth||``,me=q2.endMonth||``;if(!ms||!me)continue;if(ms>n||me<t)continue;let ys=ms<t?t:ms,ye=me>n?n:me;if(ys>ye)continue;let cc=(+ye.slice(0,4))*12+(+ye.slice(5,7))-((+ys.slice(0,4))*12+(+ys.slice(5,7)))+1,qn=q2.quantity||1,tt2=cc*qn,ar2=q2.area==null?0:Number(q2.area)*tt2;all.items.push({mentorGroupName:q2.teacherName||`手动补充`,mentorGroupNanoId:``,studentUserName:``,studentUserNanoId:``,workstationCode:''+(q2.quantity||1),workstationNanoId:q2.labCode+`-`+q2.nanoId,labName:q2.labCode,startMonth:ys,endMonth:ye,durationMonths:tt2,workstationArea:q2.area,areaMonths:Math.round(ar2*100)/100}),(()=>{for(let j2=0;j2<qn;j2++)ws.add(q2.labCode+`-`+q2.nanoId+`#`+j2)})(),all.totalWorkstationMonths+=tt2,all.totalAreaMonths+=Math.round(ar2*100)/100}all.items.sort((e,t)=>(e.mentorGroupName||``).localeCompare(t.mentorGroupName||``,`zh`)),all.distinctStudentCount=stu.size,all.distinctWorkstationCount=ws.size,all.totalAreaMonths=Math.round(all.totalAreaMonths*100)/100,all.agg=window.__uagApply(UsAgg(all.items),Y.value),I.value=all}catch(e){}finally{L.value=!1}}),$.apply(this,arguments)}function UsAgg(items){
    var rows=[],rm={};
    (items||[]).forEach(function(it){
      var g=it.mentorGroupName||`未分组`;
      var R=rm[g];if(!R){R=rm[g]={name:g,total:0,area:0,parts:[],labs:[],labsSeen:{}};rows.push(R)}
      var wc=String(it.workstationCode||``),isMan=/^\d+$/.test(wc),lab=String(it.labName||``),code=``;
      if(isMan)code=lab.replace(/\s+/g,``);
      else{var m2=wc.match(/^([A-Za-z]+\d*[A-Za-z]*)/);code=m2?m2[1].toUpperCase():lab.replace(/\u5b9e\u9a8c\u5ba4|laboratory|lab/gi,``).replace(/\s+/g,``).toUpperCase();if(it.labNanoId&&!R.labsSeen[it.labNanoId]){R.labsSeen[it.labNanoId]=1;R.labs.push({id:it.labNanoId,name:code})}}
      if(!code)return;
      var P2=R.parts,idx=-1;for(var i2=0;i2<P2.length;i2++){if(P2[i2].code===code){idx=i2;break}}
      if(idx<0){idx=P2.length;P2.push({code:code,n:0,area:0,seats:{}})}
      var pt=P2[idx];
      var qn=isMan?(parseInt(wc,10)||1):1;
      pt.n+=qn;
      var wa=it.workstationArea==null?null:Number(it.workstationArea);
      if(isMan){if(wa!=null)pt.area+=wa*qn}
      else{var sid=it.workstationNanoId||wc;pt.seats[sid]=wa==null?0:wa}
    });
    var out=[];
    rows.forEach(function(R){
      var ns=[],as2=[],tot=0,ar=0;
      R.parts.forEach(function(pt){
        tot+=pt.n;ns.push(pt.n+`(`+pt.code+`)`);
        var s2=0;for(var k in pt.seats)s2+=pt.seats[k];
        s2=Math.round(s2*100)/100;
        var pa=s2+pt.area;
        ar+=pa;
        var seg=[];
        if(s2>0)seg.push(s2.toFixed(2)+`\u33a1(`+pt.code+`)`);
        if(pt.area>0)seg.push(pt.area.toFixed(2)+`\u33a1(`+pt.code+`)`);
        if(seg.length)as2.push(seg.join(`+`));
      });
      ar=Math.round(ar*100)/100;
      var labs=R.labs.slice().sort(function(a,b){return(a.name||``).localeCompare(b.name||``,`zh`)});var jump=labs.length?`#/lab/workstations/`+labs[0].id+`?m=`+encodeURIComponent(R.name)+`&auto=1`+(labs.length>1?`&ml=`+labs.map(function(l){return l.id+`:`+encodeURIComponent(l.name)}).join(`,`):``):`#/lab/workstation?mentorName=`+encodeURIComponent(R.name);out.push({name:R.name,total:tot,area:ar,nBig:String(tot),nSub:ns.join(`+`),aBig:ar>0?ar.toFixed(2)+`\u33a1`:`-`,aSub:as2.join(`+`),jump:jump});
    });
    return out;
  }function V(){P.value=``,F.value=[N.slice(0,4)+`-01`,N],I.value=null,Y.value=N.slice(0,4)}function Xw(){try{document.title=`CLICKED-XW`}catch(e){}J.value=!0,W.value=null,U.value={teacherName:``,labCode:``,quantity:1,startMonth:F.value[0],endMonth:F.value[1],area:null,remark:``}}function Xe(){return Xe=f(function*(){try{let e=yield hh.get(`/usage-manual-records`);H.value=Array.isArray(e)?e:[]}catch(e){H.value=[]}}),Xe.apply(this,arguments)}function Xs(){return Xs=f(function*(){let b=U.value;if(!b.labCode||!b.labCode.trim()){_.warning(`请输入房间号`);return}if(!b.startMonth||!b.endMonth){_.warning(`请选择月份范围`);return}if(b.startMonth>b.endMonth){_.warning(`结束月不能早于起始月`);return}try{let body={teacherName:b.teacherName||null,labCode:b.labCode.trim(),quantity:b.quantity||1,startMonth:b.startMonth,endMonth:b.endMonth,area:b.area||null,remark:b.remark||null};W.value?yield hh.put(`/usage-manual-records/`+W.value,body):yield hh.post(`/usage-manual-records`,body);J.value=!1,yield Xe(),yield z()}catch(e){let m2=e&&e.message?e.message:`保存失败`;_.error(m2)}}),Xs.apply(this,arguments)}function Xd(n2){return Xd=f(function*(){if(!confirm(`确定删除该手动补充记录？`))return;try{yield hh.delete(`/usage-manual-records/`+n2),yield Xe(),yield z()}catch(e){_.error(e&&e.message?e.message:`删除失败`)}}),Xd.apply(this,arguments)}function Xt(n2){let it=H.value.find(e=>e.nanoId===n2);if(!it)return;W.value=n2,U.value={teacherName:it.teacherName||``,labCode:it.labCode,quantity:it.quantity||1,startMonth:it.startMonth,endMonth:it.endMonth,area:it.area,remark:it.remark||``},J.value=!0}return K(),(n,r)=>{let f=d(`loading`);return p(),a(s(h),{"auto-content-height":``},{default:m(()=>{var n,d,h,_,x;return[o(S,{loading:L.value,onSearch:z,onReset:V,onRefresh:z},{search:m(()=>[o(s(b),{modelValue:F.value,"onUpdate:modelValue":r[1]||(r[1]=e=>F.value=e),type:`monthrange`,format:`YYYY-MM`,"value-format":`YYYY-MM`,"range-separator":`至`,"start-placeholder":`起始月`,"end-placeholder":`结束月`,style:{width:`280px`}},null,8,[`modelValue`]),t(`select`,{value:Y.value,"onChange":r[9]||(r[9]=e=>{Y.value=e.target.value,F.value=[Y.value+`-01`,Y.value+`-12`],I.value=null,setTimeout(()=>{try{var bs=document.querySelectorAll(`button`);for(var i0=0;i0<bs.length;i0++){if((bs[i0].innerText||``).trim()===`搜索`){bs[i0].click();break}}}catch(_e0){}},300)}),style:{height:`32px`,borderRadius:`4px`,border:`1px solid #d1d5db`,padding:`0 8px`,fontSize:`14px`,background:`#fff`,outline:`none`,cursor:`pointer`,marginLeft:`12px`}},Yl.map(e=>t(`option`,{value:e},e+`年`)))]),_:1},8,[`loading`]),I.value?l((p(),a(s(g),{key:0,data:(n=I.value.agg)==null?[]:n,border:``,stripe:``,"max-height":`480`},{empty:m(()=>[o(s(y),{description:`暂无占用记录`})]),default:m(()=>[o(s(v),{label:`导师组`,width:160},{default:m(({row:e})=>[t(`span`,{class:`font-medium text-gray-900 dark:text-gray-100`},c(e.name),1)]),_:1}),o(s(v),{label:`工位数（点击查看工位）`,width:300,align:`center`},{default:m(({row:e})=>[t(`div`,null,[t(`a`,{href:e.jump,class:`inline-block text-base font-semibold text-primary hover:underline`,onClick:e2=>{e2.preventDefault(),location.hash=e.jump}},c(e.nBig),1),e.nSub?t(`div`,{class:`mt-0.5 text-xs text-gray-500 dark:text-gray-400`},c(e.nSub),1):u(``,!0)])]),_:1}),o(s(v),{label:`占用面积`,minWidth:200,align:`right`},{default:m(({row:e})=>[t(`div`,null,[t(`div`,{class:`text-base font-semibold text-gray-800 dark:text-gray-100 cursor-pointer hover:text-primary`,title:`点击手动填写占用面积`,onClick:e3=>{e3.stopPropagation(),window.__uagOvr&&window.__uagOvr(e.name,e)}},c(e.aBig),1),e.aSub?t(`div`,{class:`mt-0.5 text-xs text-gray-500 dark:text-gray-400`},c(e.aSub),1):u(``,!0)])]),_:1})]),_:1},8,[`data`])),[[f,L.value]]):u(``,!0),I.value?(p(),e(`div`,E,[t(`div`,D,[t(`span`,null,c((window.__ulScopeTxt&&window.__ulScopeTxt())||``),1),t(`span`,null,[r[2]||(r[2]=i(`正在使用学生 `,-1)),t(`b`,O,c((d=I.value.distinctStudentCount)==null?0:d),1),r[3]||(r[3]=i(` 人`,-1))]),t(`span`,null,[r[4]||(r[4]=i(`已使用工位(含导师预占用) `,-1)),t(`b`,k,c((h=I.value.distinctWorkstationCount)==null?0:h),1),r[5]||(r[5]=i(` 个`,-1))]),t(`span`,null,[t(`span`,null,`该年(`+Y.value+`年)使用总月数 `,1),t(`b`,A,c((_=I.value.totalWorkstationMonths)==null?0:_),1),r[7]||(r[7]=i(` 月`,-1))])]),t(`div`,j,[r[8]||(r[8]=t(`span`,{class:`text-gray-600 dark:text-gray-400`},`总计使用面积：`,-1)),t(`span`,M,c((x=I.value.totalAreaMonths)==null?0:x)+` m²·月 `,1)]),t(`button`,{type:`button`,onClick:Xw,class:`rounded-lg bg-primary px-3 py-1.5 text-sm font-medium text-white hover:opacity-90`},`＋ 手动新增`,-1)])):u(``,!0),H.value.length?(p(),a(s(g),{key:1,data:H.value,size:`small`,border:``,class:`mt-4`},{default:m(()=>[o(s(v),{label:`姓名`,"min-width":`90`},{default:m(({row:e})=>[i(c(e.teacherName||`-`),1)]),_:1}),o(s(v),{label:`房间号`,"min-width":`80`},{default:m(({row:e})=>[i(c(e.labCode),1)]),_:1}),o(s(v),{label:`数量`,"min-width":`60`,align:`center`},{default:m(({row:e})=>[i(c(e.quantity||1),1)]),_:1}),o(s(v),{label:`占用期间`,"min-width":`150`},{default:m(({row:e})=>[i(c(e.startMonth)+` ~ `+c(e.endMonth),1)]),_:1}),o(s(v),{label:`人均面积`,"min-width":`80`,align:`right`},{default:m(({row:e})=>[i(e.area==null?`-`:c(e.area)+` m²`,1)]),_:1}),o(s(v),{label:`备注`,"min-width":`140`},{default:m(({row:e})=>[i(c(e.remark||`-`),1)]),_:1}),o(s(v),{label:`操作`,"min-width":`110`,fixed:`right`},{default:m(({row:e})=>[t(`button`,{type:`button`,onClick:()=>Xt(e.nanoId),class:`mr-2 text-primary hover:underline`},`编辑`,-1),t(`button`,{type:`button`,onClick:()=>Xd(e.nanoId),class:`text-red-500 hover:underline`},`删除`,-1)]),_:1})]),_:1},8,[`data`])):u(``,!0),J.value?(p(),e(`div`,{key:2,class:`fixed inset-0 z-50 flex items-center justify-center bg-black/40`,onClick:()=>J.value=!1},[e(`div`,{class:`w-[520px] max-h-[90vh] overflow-auto rounded-xl bg-white p-5 shadow-2xl`,onClick:e2=>e2.stopPropagation()},[t(`div`,{class:`mb-4 text-base font-semibold`},W.value?`编辑手动补充`:`手动新增`,1),t(`div`,{class:`mb-3 flex items-center gap-2`},[t(`label`,{class:`w-16 shrink-0 text-sm text-gray-600`},`姓名（选填）`,-1),e(`input`,{value:U.value.teacherName,onInput:e2=>U.value.teacherName=e2.target.value,placeholder:`如：邓娜`,class:`flex-1 rounded-lg border border-gray-300 px-3 py-2 text-sm outline-none focus:border-primary`},null,8)]),t(`div`,{class:`mb-3 flex items-center gap-2`},[t(`label`,{class:`w-16 shrink-0 text-sm text-gray-600`},`房间号`,-1),e(`input`,{value:U.value.labCode,onInput:e2=>U.value.labCode=e2.target.value,placeholder:`必填，如 s105b / S404B`,class:`flex-1 rounded-lg border border-gray-300 px-3 py-2 text-sm outline-none focus:border-primary`},null,8)]),t(`div`,{class:`mb-3 flex items-center gap-2`},[t(`label`,{class:`w-16 shrink-0 text-sm text-gray-600`},`数量`,-1),e(`input`,{type:`number`,min:1,value:U.value.quantity,onInput:e2=>U.value.quantity=Number(e2.target.value)||1,class:`w-24 rounded-lg border border-gray-300 px-3 py-2 text-sm outline-none focus:border-primary`},null,8)]),t(`div`,{class:`mb-3 flex items-center gap-2`},[t(`label`,{class:`w-16 shrink-0 text-sm text-gray-600`},`起始月`,-1),e(`input`,{type:`month`,value:U.value.startMonth,onInput:e2=>U.value.startMonth=e2.target.value,class:`flex-1 rounded-lg border border-gray-300 px-3 py-2 text-sm outline-none focus:border-primary`},null,8)]),t(`div`,{class:`mb-3 flex items-center gap-2`},[t(`label`,{class:`w-16 shrink-0 text-sm text-gray-600`},`结束月`,-1),e(`input`,{type:`month`,value:U.value.endMonth,onInput:e2=>U.value.endMonth=e2.target.value,class:`flex-1 rounded-lg border border-gray-300 px-3 py-2 text-sm outline-none focus:border-primary`},null,8)]),t(`div`,{class:`mb-3 flex items-center gap-2`},[t(`label`,{class:`w-16 shrink-0 text-sm text-gray-600`},`人均面积`,-1),e(`input`,{type:`number`,step:0.01,value:U.value.area==null?``:U.value.area,onInput:e2=>U.value.area=e2.target.value===``?null:Number(e2.target.value),class:`w-32 rounded-lg border border-gray-300 px-3 py-2 text-sm outline-none focus:border-primary`},null,8)]),t(`div`,{class:`mb-3`},[t(`label`,{class:`mb-1 block text-sm text-gray-600`},`备注（选填）`,-1),e(`textarea`,{value:U.value.remark,onInput:e2=>U.value.remark=e2.target.value,rows:2,placeholder:`如：6月底搬迁至s404`,class:`w-full rounded-lg border border-gray-300 px-3 py-2 text-sm outline-none focus:border-primary`},null,8)]),t(`div`,{class:`mt-5 flex justify-end gap-2`},[t(`button`,{type:`button`,onClick:()=>J.value=!1,class:`rounded-lg border border-gray-300 px-4 py-2 text-sm text-gray-600 hover:bg-gray-50`},`取消`,-1),t(`button`,{type:`button`,onClick:Xs,class:`rounded-lg bg-primary px-4 py-2 text-sm font-medium text-white hover:opacity-90`},`保存`,-1)])])])):u(``,!0)]}),_:1})}}});export{N as default};

;(function(){try{
if(window.__ulv)return;window.__ulv=1;
/* --- token(复用/捕获) --- */
function _setTok(v){if(v&&String(v).length>8){try{window.__mgTok=v}catch(e){}}}
try{
  var _ox=XMLHttpRequest.prototype.open;
  XMLHttpRequest.prototype.open=function(){return _ox.apply(this,arguments)};
  var _oh=XMLHttpRequest.prototype.setRequestHeader;
  XMLHttpRequest.prototype.setRequestHeader=function(k,v){try{if(String(k).toLowerCase()==='authorization'){_setTok(v);try{window.__ulTok=v}catch(x){}}}catch(e){}return _oh.apply(this,arguments)};
}catch(e){}
try{
  var _of=window.fetch;
  if(_of)window.fetch=function(u,o){try{var h=(o&&o.headers)||{};if(h&&h.get){var a=h.get('Authorization');if(a)_setTok(a)}else if(h.Authorization){_setTok(h.Authorization)}else if(h.authorization){_setTok(h.authorization)}}catch(e){}return _of.apply(this,arguments)};
}catch(e){}
function _tok(){try{return window.__mgTok||window.__ulTok||''}catch(e){return ''}}
function _qs(s,r){r=r||document;try{return r.querySelector(s)}catch(e){return null}}
function _qsa(s,r){r=r||document;try{return Array.prototype.slice.call(r.querySelectorAll(s))}catch(e){return []}}
function _D2(t){return document.createElement(t)}
function _fj(u,at){at=at||0;return new Promise(function(res,rej){var h={'Content-Type':'application/json','X-Requested-With':'XMLHttpRequest'};var tk=_tok();if(tk)h['Authorization']=tk;fetch(u,{headers:h,credentials:'same-origin'}).then(function(r){
  if(r.status===401&&at<6){setTimeout(function(){_fj(u,at+1).then(res,rej)},700+at*600);return}
  if(!r.ok){rej(new Error('HTTP'+r.status));return}
  r.json().then(function(j){if(j&&j.data!=null){res(j.data)}else{rej(new Error('bad-resp'))}}).catch(rej);
}).catch(rej)})}
/* --- 状态 --- */
var LABS=[],labEl=null,menEl=null,tipEl=null;
function _labsLoaded(){return LABS.length>0}
function _loadLabs(){
  if(_labsLoaded())return;
  _fj('/api/labs').then(function(ls){ls=(ls||[]).filter(function(l){return l&&l.name});LABS=ls;_fillLab()}).catch(function(e){console.log('[UL-ERR]',e&&e.message)});
}
/* --- 主表定位 --- */
function _mainTable(){
  var ts=_qsa('.el-table');
  for(var i=0;i<ts.length;i++){var t=ts[i],tx=t.innerText||'';if(tx.indexOf('导师组')>=0&&tx.indexOf('占用面积')>=0&&tx.indexOf('labCode')<0)return t}
  return null;
}
function _rowLab(r){return (r.cells&&r.cells[3])?r.cells[3].innerText.trim():''}
function _rowLabs(r){
  var out=[];
  try{
    var c1=r.cells&&r.cells[1];
    if(c1){var tx=c1.innerText||'';var re=/\(([^()]*)\)/g,m;
      while((m=re.exec(tx))){var v=(m[1]||'').trim();if(v)out.push(v)}}
  }catch(e){}
  if(!out.length){var lab0=_rowLab(r);if(lab0)out.push(lab0)}
  return out;
}
function _rowGrp(r){return (r.cells&&r.cells[0])?r.cells[0].innerText.trim():''}
function _rowNum(r,i){var c=r.cells&&r.cells[i];if(!c)return 0;var s=c.innerText.replace(/[^\d.]/g,'');var n=parseFloat(s);return isNaN(n)?0:n}
function _esc(s){return String(s==null?'':s).replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/"/g,'&quot;')}
/* --- 汇总条定位 --- */
function _sumE(){
  var bs=_qsa('b');
  for(var i=0;i<bs.length;i++){
    var p=bs[i].parentElement;
    if(p&&p.parentElement&&(p.parentElement.innerText||'').indexOf('正在使用学生')===0)return p.parentElement.parentElement;
  }
  return null;
}
/* --- 应用过滤 + 重算汇总 --- */
function _labHit(rl,lab){
  if(!lab)return true;
  var a=(''+rl).toLowerCase().replace(/\s+/g,'');
  var b=(''+lab).toLowerCase().replace(/\s+/g,'');
  if(!a||!b)return false;
  if(a.indexOf(b)===0||b.indexOf(a)===0)return true;
  return a===b;
}
function _apply(){} /* 筛选已下沉 owner 聚合入口,DOM 过滤不再需要 */
/* --- lab/导师选择 -> 主人重查 --- */
var _goT=null;
function _go(){
  if(_goT)clearTimeout(_goT);
  _goT=setTimeout(function(){
    try{window.__ulLab=labEl?labEl.value:'';window.__ulMen=menEl?menEl.value:''}catch(e){}
    try{
      var btns=Array.prototype.slice.call(document.querySelectorAll('button'));
      for(var k=0;k<btns.length;k++){if((btns[k].innerText||'').trim()==='搜索'){btns[k].click();break}}
    }catch(e){}
  },600);
}
/* owner 聚合入口的过滤判定(labName 与 lab 化名前缀/相等;导师精确相等) */
window.__ulKeepItem=function(it){
  try{
    var lab=window.__ulLab||'',men=window.__ulMen||'';
    if(!lab&&!men)return true;
    var ln=(it&&it.labName)||'';
    if(lab){
      var a=(''+ln).toLowerCase().replace(/\s+/g,'');
      var b=(''+lab).toLowerCase().replace(/\s+/g,'');
      if(!a||!b)return false;
      if(!(a===b||a.indexOf(b)===0||b.indexOf(a)===0))return false;
    }
    if(men){if((it&&it.mentorGroupName)!==men)return false}
    return true;
  }catch(e){return true}
};
function _recalc(rows){
  var E=_sumE();
  if(!E||E.__ulBusy)return;
  var stu={},ws={},mon=0,area=0;
  for(var i=0;i<rows.length;i++){
    var r=rows[i];
    if(r.style.display==='none')continue;
    var cells=r.cells;if(!cells||cells.length<8)continue;
    var sn=(cells[1].innerText||'').trim();
    var g0=(cells[0].innerText||'').trim();
    var wn=(cells[2].innerText||'').trim();
    var isMan=/^\d+$/.test(wn);
    if(isMan){sn=''}
    if(sn)stu[sn]=1;
    if(isMan){wn=g0+'|'+wn+'|'+((cells[4]&&cells[4].innerText)||'').trim()}
    if(wn)ws[wn]=1;
    mon+=_rowNum(r,5);
    area+=_rowNum(r,7);
  }
  var D=E.children&&E.children[0],J=E.children&&E.children[1];
  var _yv='';try{var _sls=document.querySelectorAll('select');for(var _si=0;_si<_sls.length;_si++){var _o=_sls[_si].options;if(!_o||!_o.length)continue;var _v=(_o[_o.selectedIndex]&&_o[_o.selectedIndex].value)||'';if(/^\d{4}$/.test(_v)){_yv=_v;break}}}catch(_e){}if(D&&D.children){D.innerHTML='<span>'+((window.__ulScopeTxt&&window.__ulScopeTxt())||'')+'</span><span>正在使用学生 <b class="text-gray-900 dark:text-gray-100">'+Object.keys(stu).length+'</b> 人</span><span>已使用工位(含导师预占用) <b class="text-gray-900 dark:text-gray-100">'+Object.keys(ws).length+'</b> 个</span><span>'+( _yv?'该年('+_yv+'年)使用总月数 ':'该年使用总月数 ')+'<b class="text-gray-900 dark:text-gray-100">'+mon+'</b> 月</span>'}
  if(J){J.innerHTML='<span class="text-gray-600 dark:text-gray-400">总计使用面积：</span><span class="text-lg font-semibold text-primary">'+Math.round(area*100)/100+' m²·月 </span>'}
}
/* --- 导师下拉选项(从主表行推导,随 lab 过滤) --- */
function _fillMen(){
  if(!menEl)return;
  var t=_mainTable();if(!t)return;
  var rows=_qsa('tbody tr',t);
  var lab=labEl?labEl.value:'';
  var set={},order=[];
  for(var i=0;i<rows.length;i++){
    var r=rows[i];if(r.style.display==='none')continue;
    var rls=_rowLabs(r);
    var hitLab=false;
    for(var _li=0;_li<rls.length;_li++){if(_labHit(rls[_li],lab)){hitLab=true;break}}
    if(!hitLab)continue;
    var g=_rowGrp(r);if(!g)continue;
    if(!(g in set)){set[g]=1;order.push(g)}
  }
  order.sort(function(a,b){return a.localeCompare(b,'zh')});
  var old=menEl.value;
  menEl.innerHTML='<option value="">全部导师</option>'+order.map(function(g){return '<option value="'+_esc(g)+'">'+_esc(g)+'</option>'}).join('');
  if(old&&set[old]!==undefined){menEl.value=old}else{menEl.value=''}
}
/* --- 注入 UI --- */
function _mkSel(w){
  var s=_D2('select');
  s.style.cssText='height:32px;border:1px solid #d1d5db;border-radius:4px;padding:0 8px;font-size:14px;background:#fff;outline:none;cursor:pointer;margin-right:10px;width:'+w+'px';
  return s;
}
function _tryInject(){
  if(labEl&&!labEl.isConnected){labEl=null}
  if(menEl&&!menEl.isConnected){menEl=null}
  if(labEl&&menEl)return true;
  var yr=_qs('select[style*="32px"]');
  if(!yr)return false;
  var row=yr.parentElement;
  if(!row)return false;
  if(!labEl){
    labEl=_mkSel(150);
    labEl.className='__ul-lab';
    labEl.innerHTML='<option value="">全部实验室</option>';
    labEl.addEventListener('change',function(){_fillMen();_go()});
  }
  if(!menEl){
    menEl=_mkSel(160);
    menEl.className='__ul-men';
    menEl.innerHTML='<option value="">全部导师</option>';
    menEl.addEventListener('change',function(){_go()});
  }
  row.insertBefore(labEl,row.firstChild);
  row.insertBefore(menEl,labEl.nextSibling);
  /* 隐藏原生导师组选择器(请选择导师组),避免双导师选择 */
  var mg=_qs('.mentor-group-selector',row);
  if(mg){mg.style.display='none';var pw=mg;
    while(pw&&pw.parentElement&&pw.parentElement!==row&&/width\s*:\s*260/.test((pw.getAttribute&&pw.getAttribute('style'))||'')){pw=pw.parentElement}
    if(pw!==mg&&/width\s*:\s*260/.test((pw.getAttribute&&pw.getAttribute('style'))||''))pw.style.display='none';
  }
  if(!tipEl){
    tipEl=_D2('div');
    tipEl.style.cssText='display:none;color:#d97706;font-size:13px;padding:10px 2px';
    tipEl.innerText='所选条件下暂无记录,可切换实验室/导师或调整月份后点「搜索」';
    var E=_sumE();
    if(E)E.parentElement.insertBefore(tipEl,E);else document.body.appendChild(tipEl);
  }
  _loadLabs();
  return true;
}
window.__ulScopeTxt=function(){
  try{
    var v=window.__ulLab||'';
    var n=0;
    if(labEl){for(var i=0;i<labEl.options.length;i++){if(labEl.options[i].value)n++}}
    if(!n&&LABS.length)n=LABS.length;
    if(v)return String(v).replace(/实验室$/,'');
    return n?n+' 个实验室':'';
  }catch(e){return ''}
};
function _fillLab(){
  if(!labEl)return;
  if(LABS.length&&labEl.options.length<=1){
    var opts=[];
    for(var i=0;i<LABS.length;i++){var l=LABS[i];opts.push('<option value="'+_esc(l.name)+'">'+_esc(l.name)+'</option>')}
    labEl.innerHTML='<option value="">全部实验室</option>'+opts.join('');
  }
  _fillMen();
}
/* --- 年份处理:>=2024 选年自动查当年;<2024 提示 --- */
var _yrSel=null,_yrLast='',_yrBound=false,_yrRestoring=false;
function _toast(msg){
  try{
    var t0=document.querySelector('.__ul-toast');
    if(t0)t0.remove();
    var d=_D2('div');
    d.className='__ul-toast';
    d.style.cssText='position:fixed;top:18px;left:50%;transform:translateX(-50%);z-index:99999;background:#1f2937;color:#fff;font-size:14px;padding:10px 18px;border-radius:6px;box-shadow:0 4px 14px rgba(0,0,0,.25);max-width:80vw';
    d.innerText=msg;
    document.body.appendChild(d);
    setTimeout(function(){if(d.parentNode)d.parentNode.removeChild(d)},3200);
  }catch(e){}
}
function _bindYear(){
  if(_yrBound)return;
  var row=labEl&&labEl.parentElement;
  if(!row)return;
  var sels=Array.prototype.slice.call(row.querySelectorAll('select'));
  var s=null;
  for(var i=0;i<sels.length;i++){
    var o=sels[i];
    if(o===labEl||o===menEl)continue;
    var has=false;
    for(var j=0;j<o.options.length;j++){if(/^(19|20)\d{2}年$/.test(o.options[j].text)){has=true;break}}
    if(has){s=o;break}
  }
  if(!s)return;
  _yrSel=s;
  _yrLast=s.value;
  s.addEventListener('change',function(){
    if(_yrRestoring)return;
    var y=parseInt(this.value,10);
    if(isNaN(y))return;
    if(y<2024){
      _yrRestoring=true;
      this.value=_yrLast;
      try{this.dispatchEvent(new Event('change',{bubbles:true}))}catch(e){}
      _yrRestoring=false;
      _toast('无2024年前的数据,最早从2024年1月起');
      return;
    }
    _yrLast=this.value;
    /* owner 组件已把月份区间设为当年 1-12 月;等它渲染后自动点「搜索」(全文档找,可能在 page-header 其它位置) */
    setTimeout(function(){
      try{
        var btns=Array.prototype.slice.call(document.querySelectorAll('button'));
        for(var k=0;k<btns.length;k++){
          if((btns[k].innerText||'').trim()==='搜索'){btns[k].click();break}
        }
      }catch(e){}
    },800);
  });
  _yrBound=true;
}
/* --- 可输入下拉增强(与工位管理一致:打字过滤+动态全量名单) --- */
var _grpTs=0,_grpAll=null;
function _grpNamesAll(){
  if(Date.now()-_grpTs<60000)return;
  _grpTs=Date.now();
  _fj('/api/mentor-groups').then(function(list){
    var out=[];
    (function walk(ns){(ns||[]).forEach(function(x){if(!x||!x.name)return;var lv=x.level||0;if(lv>=2&&(!x.children||!x.children.length))out.push(x.name);walk(x.children||[])})})(list);
    if(out.length){_grpAll=out;if(menEl&&menEl.__ulWrapped)_dlSync(menEl.__ulWrapped,menEl,'men')}
  }).catch(function(){});
}
function _comboNames(sel){
  var set={};
  for(var i=0;i<sel.options.length;i++){var v=sel.options[i].value;if(v)set[v]=1}
  if(sel===menEl&&_grpAll){for(var j=0;j<_grpAll.length;j++){if(_grpAll[j])set[_grpAll[j]]=1}}
  return Object.keys(set).sort(function(a,b){return a.localeCompare(b,'zh')});
}
function _mkCombo(sel,ph,kind){
  var w=_D2('div');
  w.className='__ul-combo __ul-combo-'+kind;
  w.style.cssText='position:relative;display:inline-flex;align-items:center;height:32px;border:1px solid #d1d5db;border-radius:4px;background:#fff;box-sizing:border-box;margin-right:10px;width:'+(kind==='lab'?150:160)+'px;flex:0 0 auto';
  var inp=_D2('input');
  inp.type='text';inp.setAttribute('autocomplete','off');inp.placeholder=ph;
  inp.style.cssText='flex:1;min-width:0;height:100%;border:none;outline:none;padding:0 24px 0 8px;font-size:14px;color:#333;background:transparent;box-sizing:border-box';
  var dl=_D2('datalist');dl.id='__uldl'+kind+Math.floor(Math.random()*1e6);
  inp.setAttribute('list',dl.id);
  var ca=_D2('span');ca.textContent='▾';
  ca.style.cssText='position:absolute;right:7px;top:50%;transform:translateY(-50%);color:#a8abb2;font-size:12px;pointer-events:none';
  w.appendChild(inp);w.appendChild(dl);w.appendChild(ca);
  sel.style.display='none';
  if(sel.parentNode)sel.parentNode.insertBefore(w,sel);
  return w;
}
function _dlSync(w,sel,kind){
  if(!w||!sel)return;
  var inp=w.querySelector('input'),dl=w.querySelector('datalist');
  var names=_comboNames(sel);
  if(dl)dl.innerHTML=names.map(function(n){return '<option value="'+_esc(n)+'">'}).join('');
  if(inp&&document.activeElement!==inp){
    var v=sel.value||'';
    inp.value=v;
    inp.placeholder=v?'':(kind==='lab'?'全部实验室':'全部导师');
  }
}
function _commitCombo(w,sel,kind){
  var inp=w.querySelector('input');
  var v=(inp.value||'').trim();
  var names=_comboNames(sel);
  var exact=names.indexOf(v)>=0;
  var hits=[];
  if(v&&!exact){for(var i=0;i<names.length;i++){if(names[i].indexOf(v)>=0)hits.push(names[i])}}
  if(v&&!exact&&hits.length===1){v=hits[0];exact=true}
  if(v&&!exact)return;
  if(sel.value!==v){sel.value=v}
  try{sel.dispatchEvent(new Event('change',{bubbles:true}))}catch(e){}
  var _inp2=w.querySelector('input');
  if(_inp2&&_inp2.value!==v){_inp2.value=v;_inp2.placeholder=v?'':(kind==='lab'?'全部实验室':'全部导师')}
}
function _upgradeSels(){
  if(!labEl||!menEl)return;
  /* 实验室下拉保持原生:房间列表由管理员维护,只选不输入 */
  if(labEl.__ulWrapped){
    try{var _wl0=labEl.__ulWrapped;if(_wl0&&_wl0.parentNode)_wl0.parentNode.removeChild(_wl0)}catch(_e){}
    try{labEl.__ulWrapped=null;labEl.style.display=''}catch(_e){}
  }
  if(!menEl.__ulWrapped&&menEl.parentNode){menEl.__ulWrapped=_mkCombo(menEl,'全部导师','men')}
  var pairs=[[menEl,menEl.__ulWrapped,'men']];
  for(var i=0;i<pairs.length;i++){
    (function(sel,w,kind){
      if(!w||w.__ulBound)return;
      w.__ulBound=true;
      var inp=w.querySelector('input');
      inp.addEventListener('focus',function(){w.style.borderColor='#409eff';if(kind==='men')_grpNamesAll()});
      inp.addEventListener('blur',function(){w.style.borderColor='#d1d5db';
        setTimeout(function(){
          if(document.activeElement===inp)return;
          var v=inp.value.trim();
          if(v&&_comboNames(sel).indexOf(v)<0)_dlSync(w,sel,kind);
        },180);
      });
      inp.addEventListener('keydown',function(e){
        if(e.key==='Enter'){e.preventDefault();_commitCombo(w,sel,kind)}
        else if(e.key==='Escape'){inp.value='';_commitCombo(w,sel,kind)}
      });
      inp.addEventListener('change',function(){
        var v=inp.value.trim();
        if(v&&_comboNames(sel).indexOf(v)>=0)_commitCombo(w,sel,kind);
      });
      if(!sel.__ulObs){
        sel.__ulObs=true;
        try{new MutationObserver(function(){_dlSync(w,sel,kind)}).observe(sel,{childList:true,subtree:true})}catch(e){}
      }
      _dlSync(w,sel,kind);
    })(pairs[i][0],pairs[i][1],pairs[i][2]);
  }
}
/* --- 轮询/观察 --- */
function _tick(){
  try{_tryInject()}catch(e){}
  try{_upgradeSels()}catch(e){}
  try{_bindYear()}catch(e){}
  /* 返回页面后 DOM 重建:LABS 缓存仍在,_loadLabs 会短路,_fillLab 不触发 -> 补一次幂等填充 */
  try{if(LABS.length&&labEl&&labEl.options.length<=1){_fillLab()}}catch(_e){}
  var t=_mainTable();
  if(t){
    var rows=_qsa('tbody tr',t);
    if(rows.length){_fillMen()}
  }
}
try{
    if(!window.__ulScoped){
      var _n0=0,_el=labEl;
      if(_el){for(var _i2=0;_i2<_el.options.length;_i2++){if(_el.options[_i2].value)_n0++}}
      if(_n0>=2){
        window.__ulScoped=1;
        setTimeout(function(){
          try{
            var E=_sumE();if(!E)return;
            var scope=(window.__ulScopeTxt&&window.__ulScopeTxt())||'';
            if(!scope)return;
            if(((E.innerText||'').replace(/\s+/g,'')).indexOf(scope.replace(/\s+/g,''))>=0)return;
            var _bs=document.querySelectorAll('button');
            for(var _i3=0;_i3<_bs.length;_i3++){if((_bs[_i3].innerText||'').trim()==='搜索'){_bs[_i3].click();break}}
          }catch(e){}
        },600);
      }
    }
  }catch(e){}
setInterval(function(){_tick()},800);
}catch(_e){console.log('[UL-ERR]',_e&&_e.message)}})();
;(function(){try{
if(window.__uagOvrInit)return;window.__uagOvrInit=1;
var KEY='uagOv1';
var _lastY='';
function _load(){try{return JSON.parse(localStorage.getItem(KEY)||'{}')}catch(e){return{}}}
function _save(m){try{localStorage.setItem(KEY,JSON.stringify(m))}catch(e){}}
function _fmt(v){v=Number(v);if(isNaN(v))return '-';return Math.round(v*100)/100+'㎡'}
window.__uagApply=function(rows,y){
  _lastY=String(y==null?new Date().getFullYear():y);
  var m=_load();
  (rows||[]).forEach(function(r){
    if(!r._autoSub){r._autoSub=r.aSub||''}
    var k=_lastY+'|'+r.name;
    if(m[k]!=null&&m[k]!==''){
      r._ovr=1;
      r.aBig=_fmt(m[k]);
      r.aSub='手动填写'+(r._autoSub?' · 自动 '+r._autoSub:'');
    }else{
      r._ovr=0;
      r.aBig=(r.area>0?_fmt(r.area):'-');
      r.aSub=r._autoSub||'';
    }
  });
  return rows;
};
function _close(){
  var o=document.getElementById('__uagOv');
  if(o&&o.parentNode){o.parentNode.removeChild(o)}
}
window.__uagOvr=function(name,row){
  try{
    _close();
    var y=_lastY,key=y+'|'+name;
    var m=_load();
    var cur=m[key];
    var autoTxt=(row&&(row.aBig||(row.area>0?_fmt(row.area):'-')))||'-';
    var ov=document.createElement('div');
    ov.id='__uagOv';
    ov.style.cssText='position:fixed;inset:0;z-index:99999;background:rgba(0,0,0,.45);display:flex;align-items:center;justify-content:center';
    var box=document.createElement('div');
    box.style.cssText='background:#fff;border-radius:12px;padding:20px 22px;width:340px;box-shadow:0 10px 40px rgba(0,0,0,.25);font-size:14px;color:#111';
    var inp=document.createElement('input');
    inp.type='number';inp.step='any';inp.min='0';
    inp.style.cssText='width:100%;box-sizing:border-box;margin:10px 0 4px;padding:8px 10px;border:1px solid #d1d5db;border-radius:8px;font-size:15px;outline:none';
    if(cur!=null&&cur!=='')inp.value=cur;
    inp.placeholder='如 8.5';
    function rowMsg(t){var x=document.getElementById('__uagMsg');if(x)x.innerText=t}
    var html='';
    html+='<div style="font-size:16px;font-weight:600">手动填写占用面积</div>';
    html+='<div style="margin-top:6px;color:#374151">导师：<b style="color:#111">'+name+'</b> · '+y+'年</div>';
    html+='<div style="margin-top:4px;color:#6b7280;font-size:13px">自动计算：'+autoTxt+(cur!=null&&cur!==''?'<span style="color:#b45309">（当前为手动值 '+_fmt(cur)+'）</span>':'')+'</div>';
    box.innerHTML=html;
    box.appendChild(inp);
    var msg=document.createElement('div');
    msg.id='__uagMsg';
    msg.style.cssText='color:#dc2626;font-size:12px;min-height:16px;margin:2px 0 6px';
    box.appendChild(msg);
    var bar=document.createElement('div');
    bar.style.cssText='display:flex;gap:8px;justify-content:flex-end';
    var b1=document.createElement('button');
    b1.innerText='取消';b1.style.cssText=_btn('#fff','#374151','#d1d5db');
    var b2=document.createElement('button');
    b2.innerText='恢复自动';b2.style.cssText=_btn('#fff','#b45309','#f59e0b');
    var b3=document.createElement('button');
    b3.innerText='保存';b3.style.cssText=_btn('#1d4ed8','#fff','#1d4ed8');
    b1.onclick=_close;
    b2.onclick=function(){
      delete m[key];_save(m);
      if(row){row._ovr=0;row.aBig=(row.area>0?_fmt(row.area):'-');row.aSub=row._autoSub||''}
      _close();
      setTimeout(function(){try{var bs=document.querySelectorAll('button');for(var i=0;i<bs.length;i++){if((bs[i].innerText||'').trim()==='\u641c\u7d22'){bs[i].click();break}}}catch(e){}},400);
    };
    b3.onclick=function(){
      var v=parseFloat(inp.value);
      if(isNaN(v)||v<0){rowMsg('请输入有效数字，如 8.5');return}
      m[key]=Math.round(v*100)/100;_save(m);
      if(row){row._ovr=1;row.aBig=_fmt(m[key]);row.aSub='手动填写'+(row._autoSub?' · 自动 '+row._autoSub:'')}
      _close();
      setTimeout(function(){try{var bs=document.querySelectorAll('button');for(var i=0;i<bs.length;i++){if((bs[i].innerText||'').trim()==='\u641c\u7d22'){bs[i].click();break}}}catch(e){}},400);
    };
    bar.appendChild(b1);bar.appendChild(b2);bar.appendChild(b3);
    box.appendChild(bar);
    ov.appendChild(box);
    document.body.appendChild(ov);
    inp.focus();
  }catch(e){}
};
function _btn(bg,fg,bd){return 'background:'+bg+';color:'+fg+';border:1px solid '+bd+';border-radius:8px;padding:6px 14px;font-size:13px;cursor:pointer'}
document.addEventListener('keydown',function(e){if(e.key==='Escape')_close()});
}catch(_e){console.log('[UAGOV-ERR]',_e&&_e.message)}})();


;(function(){
if(window.__umc)return;window.__umc=1;
/* ===== 手动补充记录 编辑/新增/删除 接管(owner 弹窗失效,注入层自建) ===== */
var _RECS=null,_loadedAt=0;
function _tok(){return window.__mgTok||''}
function _api(method,url,body){
  return new Promise(function(res,rej){
    var h={Accept:'application/json'};
    if(_tok())h.Authorization=_tok();
    if(body!==undefined)h['Content-Type']='application/json';
    fetch(url,{method:method,headers:h,body:body!==undefined?JSON.stringify(body):undefined,credentials:'same-origin'})
      .then(function(r){if(!r.ok){return r.text().then(function(tx){rej(new Error('HTTP'+r.status+' '+String(tx).slice(0,160)))})}
        r.json().then(function(j){if(j&&j.code===0)res(j.data);else rej(new Error((j&&j.message)||'fail'))}).catch(rej)})
      .catch(rej);
  });
}
function _loadRecs(){
  return _api('GET','/api/usage-manual-records').then(function(ls){
    _RECS=(ls||[]).filter(function(r){return r&&r.nanoId});
    _loadedAt=Date.now();
    return _RECS;
  });
}
function _keyOf(r){return [r.teacherName||'',r.labCode||'',r.quantity||1,r.startMonth||'',r.endMonth||''].join('|')}
function _findNano(rowKey){
  if(!_RECS||Date.now()-_loadedAt>60000)return null;
  for(var i=0;i<_RECS.length;i++){if(_keyOf(_RECS[i])===rowKey)return _RECS[i]}
  return null;
}
/* ---------- 弹窗 UI ---------- */
var _box=null;
function _close(){try{if(_box&&_box.parentNode)_box.parentNode.removeChild(_box)}catch(e){}_box=null}
function _toast2(msg){
  try{
    var d=document.createElement('div');
    d.style.cssText='position:fixed;top:80px;left:50%;transform:translateX(-50%);z-index:99999;background:#333;color:#fff;font-size:14px;padding:10px 18px;border-radius:6px;box-shadow:0 4px 14px rgba(0,0,0,.25);max-width:80vw';
    d.innerText=msg;document.body.appendChild(d);
    setTimeout(function(){if(d.parentNode)d.parentNode.removeChild(d)},2600);
  }catch(e){}
}
function _sync(){
  /* 保存/删除成功后: 触发 owner 搜索(K() 已在搜索前自动重拉手动记录), 原地刷新不刷新页面 */
  setTimeout(function(){
    try{
      var bs=document.querySelectorAll('button');
      for(var i0=0;i0<bs.length;i0++){if((bs[i0].innerText||'').trim()==='搜索'){bs[i0].click();break}}
    }catch(e){console.log('[UMC-SYNC]',e&&e.message)}
  },700);
}
function _normLab(v){
  /* 房间号自动规范: s404->S404B s724->S724B s819->S819B s214->S214B s105b->S105B ... */
  v=String(v||'').trim();
  if(!v)return v;
  var low=v.toLowerCase();
  var KN={s404:'S404B',s404b:'S404B',s724:'S724B',s724b:'S724B',s819:'S819B',s819b:'S819B',s726:'S726',s214:'S214B',s214b:'S214B',s105:'S105',s105b:'S105B',s502:'S502'};
  if(KN[low])return KN[low];
  var m=low.match(/^s(\d+)(b)?$/);
  if(m)return 'S'+m[1]+(m[2]?'B':'');
  if(v.charAt(0)==='s')return 'S'+v.slice(1);
  return v;
}
function _openForm(rec,isEdit){
  _close();
  var ov=document.createElement('div');
  ov.style.cssText='position:fixed;inset:0;z-index:99990;display:flex;align-items:center;justify-content:center;background:rgba(0,0,0,.4)';
  var box=document.createElement('div');
  box.style.cssText='width:560px;max-height:92vh;overflow:auto;background:#fff;border-radius:14px;padding:20px 22px;box-shadow:0 8px 30px rgba(0,0,0,.3);font-family:system-ui,-apple-system,"Segoe UI",sans-serif';
  var title=document.createElement('div');
  title.style.cssText='font-size:16px;font-weight:600;margin-bottom:16px;color:#1f2937';
  title.innerText=isEdit?'编辑手动补充记录':'新增手动补充记录';
  function row(label,ctrl){
    var r=document.createElement('div');
    r.style.cssText='display:flex;align-items:center;gap:10px;margin-bottom:12px';
    var lb=document.createElement('label');
    lb.style.cssText='width:80px;flex-shrink:0;font-size:13px;color:#4b5563;text-align:right';
    lb.innerText=label;
    r.appendChild(lb);r.appendChild(ctrl);
    return r;
  }
  function input(ph,val,extra){
    var inp=document.createElement('input');
    inp.value=val||'';
    inp.placeholder=ph||'';
    inp.style.cssText='flex:1;border:1px solid #d1d5db;border-radius:8px;padding:7px 10px;font-size:13px;outline:none'+(extra||'');
    return inp;
  }
  var fName=input('如：邓娜',isEdit?rec.teacherName:'');
  var fLab=input('必填，如 S404B / s105b',isEdit?rec.labCode:'');
  fLab.style.borderColor='#f59e0b';
  var fQty=input('1',isEdit?String(rec.quantity||1):'1');
  fQty.type='number';fQty.min='1';fQty.style.cssText+=';width:90px';
  var fStart=input('',isEdit?(rec.startMonth||''):'');
  fStart.type='month';fStart.style.cssText+=';width:170px';
  var fEnd=input('',isEdit?(rec.endMonth||''):'');
  fEnd.type='month';fEnd.style.cssText+=';width:170px';
  var fArea=input('可空，如 2.13',isEdit&&rec.area!=null?String(rec.area):'');
  fArea.type='number';fArea.step='any';fArea.style.cssText+=';width:140px';
  var fRem=input('备注（可空）',isEdit?(rec.remark||''):'');
  fRem.style.cssText+=';flex:1';
  var errBox=document.createElement('div');
  errBox.style.cssText='color:#dc2626;font-size:12px;min-height:16px;margin-bottom:8px;display:none';
  function showErr(m){errBox.style.display='block';errBox.innerText=m}
  function ok(){
    var lab=_normLab(fLab.value);
    fLab.value=lab;
    if(!lab){showErr('房间号不能为空');return}
    var sm=fStart.value,em=fEnd.value;
    if(!sm||!em){showErr('请选择占用期间（起始/结束月）');return}
    if(sm>em){showErr('结束月不能早于起始月');return}
    var qty=parseInt(fQty.value,10);
    if(isNaN(qty)||qty<1){qty=1}
    var area=fArea.value===''?null:Number(fArea.value);
    if(area!=null&&(isNaN(area)||area<0)){showErr('面积需为非负数字');return}
    var body={teacherName:(fName.value||'').trim()||null,labCode:lab,quantity:qty,startMonth:sm,endMonth:em,area:area,remark:(fRem.value||'').trim()||null};
    var saveBtn=document.getElementById('__umc_save');
    if(saveBtn){saveBtn.disabled=true;saveBtn.style.opacity='.6';saveBtn.innerText='保存中…'}
    var p=isEdit?_api('PUT','/api/usage-manual-records/'+encodeURIComponent(rec.nanoId),body):_api('POST','/api/usage-manual-records',body);
    p.then(function(){_close();_toast2('已保存');_sync()})
     .catch(function(e){showErr('保存失败：'+(e&&e.message||'网络错误'));if(saveBtn){saveBtn.disabled=false;saveBtn.style.opacity='1';saveBtn.innerText='保存'}});
  }
  function btnRow(){
    var r=document.createElement('div');
    r.style.cssText='display:flex;justify-content:flex-end;gap:10px;margin-top:14px';
    var c1=document.createElement('button');
    c1.type='button';c1.innerText='取消';
    c1.style.cssText='border:1px solid #d1d5db;background:#fff;color:#374151;border-radius:8px;padding:7px 18px;font-size:13px;cursor:pointer';
    c1.onclick=function(){_close()};
    var c2=document.createElement('button');
    c2.type='button';c2.id='__umc_save';c2.innerText='保存';
    c2.style.cssText='border:none;background:#2563eb;color:#fff;border-radius:8px;padding:7px 22px;font-size:13px;cursor:pointer';
    c2.onclick=ok;
    r.appendChild(c1);r.appendChild(c2);
    return r;
  }
  box.appendChild(title);
  box.appendChild(row('姓名（选填）',fName));
  box.appendChild(row('房间号 *',fLab));
  var qr=row('数量 *',fQty);box.appendChild(qr);
  var mr=row('占用期间 *',(function(){var w=document.createElement('div');w.style.cssText='display:flex;align-items:center;gap:6px;flex:1';var s=document.createElement('span');s.innerText='至';s.style.cssText='color:#6b7280;font-size:12px';w.appendChild(fStart);w.appendChild(s);w.appendChild(fEnd);return w})());
  box.appendChild(mr);
  box.appendChild(row('人均面积',fArea));
  box.appendChild(row('备注',fRem));
  box.appendChild(errBox);
  box.appendChild(btnRow());
  ov.appendChild(box);
  ov.addEventListener('click',function(e){if(e.target===ov)_close()});
  document.body.appendChild(ov);
  _box=ov;
}
/* ---------- 接管按钮 ---------- */
function _take(btn,fn){
  if(btn.__umc)return;
  btn.__umc=1;
  btn.addEventListener('click',function(ev){
    try{ev.stopImmediatePropagation();ev.preventDefault();ev.stopPropagation()}catch(e){}
    fn(btn);
  },true);
}
function _rowData(tr){
  var c=tr.querySelectorAll('td');
  var get=function(i){return c[i]?(c[i].innerText||'').trim():''};
  return {name:get(0),lab:get(1),qty:get(2),period:get(3),area:get(4),rem:get(5)};
}
function _parsePeriod(p){
  var m=String(p||'').match(/(\d{4}-\d{2})\s*~\s*(\d{4}-\d{2})/);
  return m?[m[1],m[2]]:null;
}
function _doEdit(btn){
  var tr=btn.closest('tr');
  if(!tr){_toast2('找不到该行数据');return}
  var rd=_rowData(tr);
  var pm=_parsePeriod(rd.period);
  var rowKey=[rd.name,rd.lab,rd.qty,pm?pm[0]:'',pm?pm[1]:''].join('|');
  _loadRecs().then(function(){
    var rec=_findNano(rowKey);
    if(!rec){_toast2('未找到对应记录（可能已被刷新），请刷新页面后重试');return}
    _openForm(rec,true);
  }).catch(function(e){_toast2('加载记录失败：'+(e&&e.message||'网络错误'))});
}
function _doDelete(btn){
  var tr=btn.closest('tr');
  if(!tr){_toast2('找不到该行数据');return}
  var rd=_rowData(tr);
  var pm=_parsePeriod(rd.period);
  var rowKey=[rd.name,rd.lab,rd.qty,pm?pm[0]:'',pm?pm[1]:''].join('|');
  var ov=document.createElement('div');
  ov.style.cssText='position:fixed;inset:0;z-index:99995;display:flex;align-items:center;justify-content:center;background:rgba(0,0,0,.4)';
  var box=document.createElement('div');
  box.style.cssText='width:360px;background:#fff;border-radius:14px;padding:20px;box-shadow:0 8px 30px rgba(0,0,0,.3);font-family:system-ui,sans-serif;text-align:center';
  var t=document.createElement('div');
  t.style.cssText='font-size:14px;color:#1f2937;margin-bottom:18px;line-height:1.6';
  t.innerText='确定删除该手动补充记录？\n'+rd.name+' ｜ '+rd.lab+' ｜ '+rd.qty+' 卡 ｜ '+rd.period;
  t.style.whiteSpace='pre-line';
  var rr=document.createElement('div');
  rr.style.cssText='display:flex;justify-content:center;gap:10px';
  var c1=document.createElement('button');c1.type='button';c1.innerText='取消';
  c1.style.cssText='border:1px solid #d1d5db;background:#fff;color:#374151;border-radius:8px;padding:7px 18px;font-size:13px;cursor:pointer';
  c1.onclick=function(){ov.parentNode&&ov.parentNode.removeChild(ov)};
  var c2=document.createElement('button');c2.type='button';c2.innerText='删除';
  c2.style.cssText='border:none;background:#dc2626;color:#fff;border-radius:8px;padding:7px 22px;font-size:13px;cursor:pointer';
  c2.onclick=function(){
    c2.disabled=true;c2.innerText='删除中…';
    _loadRecs().then(function(){
      var rec=_findNano(rowKey);
      if(!rec){_toast2('未找到对应记录，可能已变化，请刷新页面');ov.parentNode&&ov.parentNode.removeChild(ov);return}
      return _api('DELETE','/api/usage-manual-records/'+encodeURIComponent(rec.nanoId));
    }).then(function(){_close();_toast2('已删除');_sync()}
    ).catch(function(e){_toast2('删除失败：'+(e&&e.message||'网络错误'));c2.disabled=false;c2.innerText='删除'});
  };
  rr.appendChild(c1);rr.appendChild(c2);
  box.appendChild(t);box.appendChild(rr);
  ov.appendChild(box);
  ov.addEventListener('click',function(e){if(e.target===ov&&ov.parentNode)ov.parentNode.removeChild(ov)});
  document.body.appendChild(ov);
}
/* ---------- 扫描接管(轻量轮询) ---------- */
var _scanDone=0;
function _scan(){
  var ts=document.querySelectorAll('.el-table');
  var mt=null;
  for(var i=0;i<ts.length;i++){
    var tx=ts[i].innerText||'';
    if(tx.indexOf('房间号')>=0&&tx.indexOf('占用期间')>=0&&tx.indexOf('导师组')<0){mt=ts[i];break}
  }
  if(!mt)return;
  var rows=mt.querySelectorAll('tbody tr');
  for(var j=0;j<rows.length;j++){
    var bts=rows[j].querySelectorAll('button');
    for(var k=0;k<bts.length;k++){
      var tx2=(bts[k].innerText||'').trim();
      if(tx2==='编辑')_take(bts[k],_doEdit);
      else if(tx2==='删除')_take(bts[k],_doDelete);
    }
  }
  /* 手动新增按钮(owner 汇总条区,文本含"手动新增") */
  var all=document.querySelectorAll('button');
  for(var m2=0;m2<all.length;m2++){
    var tx3=(all[m2].innerText||'').trim();
    if(tx3.indexOf('手动新增')>=0){_take(all[m2],function(){_loadRecs().then(function(){_openForm(null,false)}).catch(function(e){_toast2('加载失败：'+(e&&e.message||'网络错误'))})})}
  }
  _scanDone++;
}
setInterval(function(){try{_scan()}catch(e){console.log('[UMC-ERR]',e&&e.message)}},1200);
/* 立即先加载记录列表备用 */
_loadRecs().catch(function(){});
window.__ulOpenAdd=function(){_loadRecs().then(function(){_openForm(null,false)}).catch(function(e){_toast2('加载失败：'+(e&&e.message||'网络错误'))})};
})();
/* ===== ULM: 双模式(统计/手动记录) ===== */
;(function(){
try{
if(window.__ulModeInit)return;window.__ulModeInit=1;
function _q(s){try{return document.querySelector(s)}catch(e){return null}}
function _qa(s){try{return Array.prototype.slice.call(document.querySelectorAll(s))}catch(e){return []}}
function _pageMode(){var h=location.hash||'';if(h.indexOf('usage-manual')>=0)return 'manual';if(h.indexOf('/lab/usage')>=0)return 'stats';return null}
function _isMain(t){var x=t.innerText||'';return x.indexOf('导师组')>=0&&x.indexOf('占用面积')>=0&&x.indexOf('labCode')<0}
function _isMan(t){var x=t.innerText||'';return x.indexOf('占用期间')>=0&&x.indexOf('人均面积')>=0&&x.indexOf('导师组')<0}
function _findMan(){var ts=_qa('.el-table');for(var i=0;i<ts.length;i++){if(_isMan(ts[i]))return ts[i]}return null}
function _findMain(){var ts=_qa('.el-table');for(var i=0;i<ts.length;i++){if(_isMain(ts[i]))return ts[i]}return null}
function _findStat(){var es=_qa('div');for(var i=0;i<es.length;i++){var x=es[i].innerText||'';if(x.indexOf('正在使用学生')>=0&&x.indexOf('总计使用面积')>=0&&x.length<800)return es[i]}return null}
function _findCard(){var es=_qa('div');for(var i=0;i<es.length;i++){var x=es[i].innerText||'';if(x.indexOf('全部实验室')>=0&&x.indexOf('搜索')>=0&&x.length<600){var p=es[i];for(var k=0;k<8&&p;k++){var c=String(p.className||'');if(c.indexOf('bg-card')>=0)return p;p=p.parentElement}return es[i]}}return null}
function _hide(el){if(el&&el.style)el.style.display='none'}
function _show(el){if(el&&el.style)el.style.display=''}
function _mkHead(){
  var card=document.createElement('div');
  card.setAttribute('data-ulmh','1');
  card.style.cssText='display:flex;align-items:center;justify-content:space-between;gap:12px;background:#fff;border-radius:10px;padding:14px 18px;margin:0 0 12px;box-shadow:0 1px 2px rgba(0,0,0,.05)';
  var l=document.createElement('div');
  var t1=document.createElement('div');t1.style.cssText='font-size:15px;font-weight:600;color:#1f2937';t1.innerText='工位手动记录';
  var t2=document.createElement('div');t2.style.cssText='font-size:12px;color:#9ca3af;margin-top:3px';t2.innerText='人工登记的搬迁 / 收费 / 存量卡补录记录，已自动并入占用统计';
  l.appendChild(t1);l.appendChild(t2);
  var b=document.createElement('button');
  b.type='button';
  b.style.cssText='border:none;background:#1677ff;color:#fff;border-radius:8px;padding:8px 18px;font-size:13px;cursor:pointer;white-space:nowrap';
  b.innerText='＋ 手动新增';
  b.onclick=function(){
    var fired=false;
    try{var bs=_qa('button');for(var i=0;i<bs.length;i++){if(bs[i]===b)continue;var x=(bs[i].innerText||'').trim();if(x.indexOf('手动新增')>=0){bs[i].click();fired=true;break}}}catch(e){}
    if(!fired){try{window.__ulOpenAdd&&window.__ulOpenAdd()}catch(e2){}}
  };
  card.appendChild(l);card.appendChild(b);
  return card;
}
function _mkEmpty(){
  var e=document.createElement('div');
  e.setAttribute('data-ulme','1');
  e.style.cssText='background:#fff;border-radius:10px;padding:46px 20px;text-align:center;color:#9ca3af;font-size:13px';
  e.innerText='暂无手动记录，点击右上角「＋ 手动新增」添加';
  return e;
}
function _tick(){
  var m=_pageMode();
  if(!m)return;
  var mm=_findMan(),mt=_findMain(),st=_findStat(),sc=_findCard();
  if(m==='manual'){
    _hide(sc);_hide(mt);_hide(st);
    var cont=(mm&&mm.parentNode)?mm.parentNode:(st?st.parentNode:null);
    if(!cont)return;
    var hd=_q('[data-ulmh]');
    if(!hd){hd=_mkHead();cont.insertBefore(hd,mm||cont.firstChild);}
    else if(mm&&hd.nextSibling!==mm){cont.insertBefore(hd,mm);}
    var emp=_q('[data-ulme]');
    if(mm&&emp&&emp.parentNode)emp.parentNode.removeChild(emp);
    if(!mm&&!emp){var emp2=_mkEmpty();cont.insertBefore(emp2,hd.nextSibling);}
  }else{
    _show(sc);_show(mt);_show(st);
    if(mt){try{mt.style.maxHeight='none';mt.style.height='auto';var wrs=mt.querySelectorAll('.el-scrollbar__wrap');for(var wi=0;wi<wrs.length;wi++){wrs[wi].style.maxHeight='none';wrs[wi].style.height='auto';wrs[wi].style.overflow='visible'}}catch(e){}}
    if(mm)_hide(mm);
    if(st){var bs=_qa('button',st);for(var i=0;i<bs.length;i++){var x=(bs[i].innerText||'').trim();if(x.indexOf('手动新增')>=0)bs[i].style.display='none';}}
  }
}
setInterval(function(){try{_tick()}catch(e){console.log('[ULM-ERR]',e&&e.message)}},600);
}catch(_e){console.log('[ULM-ERR2]',_e&&_e.message)}})();
;(function(){try{
if(window.__uljx)return;window.__uljx=1;
/* --- 工位数跳转修正:数据层 fallback 补 lab nanoId + DOM 层对齐 href/点击(修复 el-table 行复用导致 href 串行,点 404B 行跳到 726 视图) --- */
var _labTs=0,_labMap={},_labArea={},_labAreaFilled=0;
function _norm(v){return String(v||'').replace(/\s+/g,'').toUpperCase()}
function _tok(){try{return window.__mgTok||window.__ulTok||''}catch(e){return ''}}
function _loadLabs(){
  var _n=Date.now();
  if(_labTs>0&&_n-_labTs<60000)return;
  _labTs=_n;
  try{
    var h={'Content-Type':'application/json','X-Requested-With':'XMLHttpRequest'};
    var tk=_tok();if(tk)h['Authorization']=tk;
    fetch('/api/labs',{headers:h,credentials:'same-origin'}).then(function(r){
      if(!r.ok){_labTs=_n-55000;return null}
      return r.json();
    }).then(function(j){
      if(!j)return;
      var ls=(j&&j.data!=null)?j.data:j;
      if(Array.isArray(ls)){
        var m={};
        ls.forEach(function(l){
          if(!l)return;
          if(l.code){m[_norm(l.code)]=l.nanoId||l.id||'';if(l.area!=null&&l.capacity){_labArea[_norm(l.code)]={a:Number(l.area),c:Number(l.capacity)}}}
          if(l.name)m[_norm(l.name)]=l.nanoId||l.id||'';
        });
        if(Object.keys(m).length){
          _labMap=m;_fixDom();
          if(!_labAreaFilled){
            _labAreaFilled=1;
            setTimeout(function(){
              try{var bs=document.querySelectorAll('button');for(var i=0;i<bs.length;i++){if((bs[i].innerText||'').trim()==='搜索'){bs[i].click();break}}}catch(e){}
            },500);
          }
        }
      }
    }).catch(function(){_labTs=_n-55000});
  }catch(e){_labTs=_n-55000}
}
function _labNano(code){
  var c=_norm(code);if(!c)return '';
  if(_labMap[c])return _labMap[c];
  for(var k in _labMap){if(k.indexOf(c)===0||c.indexOf(k)===0)return _labMap[k]}
  return '';
}
function _singleArea(code){
  try{
    var x=_labArea[_norm(code)];
    if(!x||!(Number(x.a)>0)||!(Number(x.c)>0))return 0;
    return Math.ceil(Number(x.a)/Number(x.c)*100)/100;
  }catch(e){return 0}
}
/* 面积补算:聚合行中无面积贡献的段(手动录入等)按 lab 总面积/座位数 补足,与 rental 单座面积口径一致 */
function _areaFix(rows){
  if(!rows||!Object.keys(_labArea).length)return;
  (rows||[]).forEach(function(r){
    try{
      if(!r||r._ovr)return;
      var sub=r._autoSub||r.aSub||'';
      var segs=String(r.nSub||'').split('+');
      var adds=[],addAr=0;
      for(var i=0;i<segs.length;i++){
        var mm=segs[i].match(/^(\d+)\((.+)\)$/);
        if(!mm)continue;
        var n=parseInt(mm[1],10)||0,code=mm[2];
        var sa=_singleArea(code);
        if(!sa||!n)continue;
        if(sub.indexOf('('+code+')')>=0)continue;
        adds.push((sa*n).toFixed(2)+'\u33a1('+code+')');
        addAr+=sa*n;
      }
      if(adds.length){
        r.area=Math.round((Number(r.area)||0)*100+Math.round(addAr*100))/100;
        r.aBig=(r.area>0?String(Math.round(r.area*100)/100)+'\u33a1':'-');
        r.aSub=(sub?sub+'+':'')+adds.join('+');
        r._autoSub=r.aSub;
      }
    }catch(e){}
  });
}
window.__uljxRows={};
function _hook(){
  if(window.__uljxHooked||!window.__uagApply)return;
  var orig=window.__uagApply;
  window.__uljxHooked=1;
  window.__uagApply=function(rows,y){
    try{
      var out={};
      (rows||[]).forEach(function(r){
        if(!r)return;
        if(r.jump&&r.jump.indexOf('#/lab/workstation?mentorName=')===0&&r.nSub){
          var mm=String(r.nSub).match(/\(([^)]+)\)/);
          var code=mm?mm[1]:'';
          var nid=_labNano(code);
          if(nid){r.jump='#/lab/workstations/'+nid+'?m='+encodeURIComponent(r.name||'')+'&auto=1'}
        }
        out[r.name]=r.jump;
      });
      window.__uljxRows=out;
    }catch(e){}
    var _res=orig.apply(this,arguments);
    try{_areaFix(_res)}catch(e){}
    return _res;
  };
}
function _fitDom(){try{
  var sc=null,cs=Array.prototype.slice.call(document.querySelectorAll('div'));
  for(var i=0;i<cs.length;i++){var e=cs[i];if(e.clientHeight>300&&String(e.className).indexOf('h-full p-4')>=0){sc=e;break}}
  if(!sc)return;
  var t=null,ts=Array.prototype.slice.call(document.querySelectorAll('.el-table'));
  for(var j=0;j<ts.length;j++){var tx=ts[j].innerText||'';if(tx.indexOf('导师组')>=0&&tx.indexOf('占用面积')>=0&&tx.indexOf('labCode')<0&&getComputedStyle(ts[j]).display!=='none'){t=ts[j];break}}
  if(!t)return;
  if(String(sc.className).indexOf('uljx-fit')<0)sc.className+=' uljx-fit';
  var sid='uljx-fit-css';
  if(!document.getElementById(sid)){
    var st=document.createElement('style');st.id=sid;
    st.textContent=['.uljx-fit{display:flex;flex-direction:column;min-height:0}',
      '.uljx-fit>.bg-card{flex:0 0 auto}',
      '.uljx-fit>.el-table{flex:1 1 auto;min-height:0;display:flex;flex-direction:column;margin-bottom:12px}',
      '.uljx-fit>.el-table .el-table__inner-wrapper{display:flex;flex-direction:column;flex:1 1 auto;min-height:0}',
      '.uljx-fit>.el-table .el-table__header-wrapper{flex:0 0 auto}',
      '.uljx-fit>.el-table .el-table__body-wrapper{flex:1 1 auto;min-height:0;overflow:hidden}',
      '.uljx-fit>.el-table .el-table__body-wrapper .el-scrollbar{height:100%;display:block}',
      '.uljx-fit>.el-table .el-table__body-wrapper .el-scrollbar__wrap{height:100%!important;overflow:auto!important;scrollbar-width:thin!important;scrollbar-color:rgba(148,163,184,.55) transparent!important}',
      '.uljx-fit>.el-table .el-table__body-wrapper .el-scrollbar__wrap::-webkit-scrollbar{width:9px;height:9px}',
      '.uljx-fit>.el-table .el-table__body-wrapper .el-scrollbar__wrap::-webkit-scrollbar-thumb{background:rgba(148,163,184,.55);border-radius:5px;border:2px solid transparent;background-clip:content-box}',
      '.uljx-fit>.el-table .el-table__body-wrapper .el-scrollbar__wrap::-webkit-scrollbar-track{background:transparent}'].join('\n');
    document.head.appendChild(st);
  }
}catch(e){}}
function _fixDom(){
  try{
    var t=null,ts=Array.prototype.slice.call(document.querySelectorAll('.el-table'));
    for(var i=0;i<ts.length;i++){
      var tx=ts[i].innerText||'';
      if(tx.indexOf('导师组')>=0&&tx.indexOf('占用面积')>=0&&tx.indexOf('labCode')<0&&getComputedStyle(ts[i]).display!=='none'){t=ts[i];break}
    }
    if(!t)return;
    var trs=Array.prototype.slice.call(t.querySelectorAll('tbody tr')).filter(function(r){return r.style.display!=='none'});
    trs.forEach(function(r){
      var cells=Array.prototype.slice.call(r.querySelectorAll('td'));
      if(cells.length<2)return;
      var name=(cells[0].innerText||'').trim();
      var a=cells[1].querySelector('a');
      if(!a)return;
      var jump=window.__uljxRows[name]||a.getAttribute('href');
      if(jump&&a.getAttribute('href')!==jump){try{a.setAttribute('href',jump)}catch(e){}}
      if(!a.__uljxBound){
        a.__uljxBound=1;
        a.addEventListener('click',function(ev){
          var j=window.__uljxRows[name]||a.getAttribute('href');
          if(!j)return;
          try{ev.preventDefault();ev.stopImmediatePropagation();location.hash=j}catch(e){}
        },true);
      }
    });
  }catch(e){}
}
setInterval(function(){try{_hook()}catch(e){}try{_fixDom()}catch(e){}try{_fitDom()}catch(e){}try{var _sl=document.querySelector('select.__ul-lab'),_sm=document.querySelector('select.__ul-men');var _cl=_sl?(_sl.value||''):'',_cm=_sm?(_sm.value||''):'';var _oldLab=window.__ulLab||'',_oldMen=window.__ulMen||'';var _need=false;if(_cl!==_oldLab){window.__ulLab=_cl;if(_oldLab&&!_cl)_need=true}if(_cm!==_oldMen){window.__ulMen=_cm;if(_oldMen&&!_cm)_need=true}if(_need){setTimeout(function(){try{var _bs=document.querySelectorAll('button');for(var _i=0;_i<_bs.length;_i++){if((_bs[_i].innerText||'').trim()==='\u641c\u7d22'){_bs[_i].click();break}}}catch(e){}},500);}}catch(e){}try{_loadLabs()}catch(e){}},500);
try{_hook()}catch(e){}
setTimeout(function(){try{_loadLabs()}catch(e){}},1500);
}catch(_e){console.log('[ULJX-ERR]',_e&&_e.message)}})();
/*__ULROOMAREA_V1__*/
;(function(){try{
  if(window.__ulRoomArea)return;window.__ulRoomArea=1;
  var TOK=null,LABS=null;
  try{
    var _osh=XMLHttpRequest.prototype.setRequestHeader;
    XMLHttpRequest.prototype.setRequestHeader=function(k,v){try{if(/^authorization$/i.test(k)&&v&&String(v).length>10)TOK=String(v);}catch(_e){}return _osh.apply(this,arguments)};
  }catch(_e){}
  try{
    if(window.fetch){var _of=window.fetch;window.fetch=function(){try{var a=arguments[1];if(a&&a.headers){var h=a.headers;var v=h.get?h.get('Authorization'):(h.Authorization||h.authorization);if(v&&String(v).length>10)TOK=String(v);}}catch(_e){}return _of.apply(this,arguments)};}
  }catch(_e){}
  function _num(a){if(a==null)return null;var n=Number(a);return isNaN(n)?null:n;}
  function _fmt(n){return String(Math.round(n*100)/100);}
  function _load(){
    if(LABS)return;
    try{
      fetch('/api/labs',{headers:TOK?{Authorization:TOK}:{}}).then(function(r){return r.json()}).then(function(j){
        if(j&&j.data&&j.data.length){LABS=j.data.map(function(l){return {name:l.name,code:l.code,area:_num(l.area)}});_schedule();}
      }).catch(function(){});
    }catch(_e){}
  }
  function _cur(){var s=document.querySelector('select.__ul-lab');return s?String(s.value||''):'';}
  function _set(el,txt){try{if(el&&(el.textContent||'')!==txt)el.textContent=txt;}catch(_e){}}
  function _apply(){
    try{
      if(!LABS||!LABS.length)return;
      var v=_cur(),label,val;
      if(v){
        var hit=null;for(var i=0;i<LABS.length;i++){if(LABS[i].name===v){hit=LABS[i];break;}}
        if(!hit)return;
        label='房间总面积：';
        val=(hit.area==null)?'—':_fmt(hit.area)+' m²';
      }else{
        var sum=0,n=0;for(var j=0;j<LABS.length;j++){if(LABS[j].area!=null){sum+=LABS[j].area;n++;}}
        if(!n)return;
        label='房间总面积（'+n+' 个实验室合计）：';
        val=_fmt(sum)+' m²';
      }
      var scope=document.querySelector('div.mt-4.flex.flex-wrap')||document.body;
      var spans=scope.querySelectorAll('span');
      for(var k=0;k<spans.length;k++){
        var el=spans[k];
        if(el.children.length===0&&/(总计使用面积|房间总面积)/.test(el.textContent||'')){
          var sib=el.parentElement?el.parentElement.querySelector('span.text-lg'):null;
          _set(el,label);
          _set(sib,val);
          break;
        }
      }
    }catch(_e){}
  }
  var _timer=null;
  function _schedule(){ if(_timer)return; _timer=setTimeout(function(){_timer=null;_apply();},200); }
  function _tick(){_load();_apply();}
  try{new MutationObserver(function(){_schedule();}).observe(document.body,{childList:true,subtree:true,characterData:true});}catch(_e){}
  try{document.addEventListener('change',function(e){try{if(e&&e.target&&e.target.classList&&e.target.classList.contains('__ul-lab'))setTimeout(_tick,60);}catch(_e){}},true);}catch(_e){}
  setTimeout(_tick,300);setTimeout(_tick,1200);setTimeout(_tick,3000);
  setInterval(_tick,1200);
}catch(e){console.log('[ULROOMAREA-FATAL]',e&&e.message)}})();
/*__ULFILL_V1__*/
;(function(){try{
if(window.__ulfill)return;window.__ulfill=1;
/* 占用面积列加 Excel 式数据条, 把该列左侧大片空白变成有用信息; 内容块定宽保证各行条尾对齐 */
var CW=300;
function _mainTbl(){
  var ts=document.querySelectorAll('.el-table'),i;
  /* 优先按结构找(与语言无关): 含跳往工位视图链接的那张表 */
  for(i=0;i<ts.length;i++){
    var o=ts[i];
    if(getComputedStyle(o).display==='none')continue;
    if(o.querySelector('a[href*="#/lab/workstation"]'))return o;
  }
  /* 回退: 中文表头文本匹配 */
  for(i=0;i<ts.length;i++){var t=ts[i],tx=t.innerText||'';
    if(tx.indexOf('\u5bfc\u5e08\u7ec4')>=0&&tx.indexOf('\u5360\u7528\u9762\u79ef')>=0&&tx.indexOf('labCode')<0&&getComputedStyle(t).display!=='none')return t}
  return null;
}
function _num(x){var m=String(x||'').replace(/,/g,'').match(/\d+(\.\d+)?/);return m?parseFloat(m[0]):NaN;}
function _run(){
  var t=_mainTbl();if(!t)return;
  var trs=[].slice.call(t.querySelectorAll('tbody tr'));
  var items=[],max=0;
  for(var i=0;i<trs.length;i++){
    var r=trs[i];if(r.style.display==='none')continue;
    var tds=r.children;if(!tds||tds.length<3)continue;
    var cell=tds[2].querySelector('.cell')||tds[2];
    var outer=null,ch=cell.children;
    for(var q=0;q<ch.length;q++){if(!/__ulfill/.test(ch[q].className||'')){outer=ch[q];break}}
    if(!outer)continue;
    var v=_num(outer.innerText||'');
    items.push({cell:cell,outer:outer,v:v});
    if(!isNaN(v)&&v>max)max=v;
  }
  if(!items.length||!(max>0))return;
  for(var k=0;k<items.length;k++){
    var o=items[k];
    var bar=o.cell.querySelector(':scope>.__ulfill');
    if(!bar){
      bar=document.createElement('div');
      bar.className='__ulfill';
      bar.style.cssText='flex:1 1 auto;min-width:0;margin-right:14px;height:10px;display:flex;align-items:center;justify-content:flex-start';
      var ib=document.createElement('div');
      ib.className='__ulfill-b';
      ib.style.cssText='height:10px;border-radius:5px;background:linear-gradient(90deg,#bfdbfe,#3b82f6);transition:width .25s';
      bar.appendChild(ib);
      o.cell.insertBefore(bar,o.cell.firstChild);
      o.cell.style.display='flex';
      o.cell.style.alignItems='center';
      o.cell.style.justifyContent='flex-end';
      o.outer.style.flex='0 0 auto';
      o.outer.style.width=CW+'px';
      o.outer.style.textAlign='right';
      o.outer.style.minWidth='0';
    }
    var b=bar.firstElementChild;
    if(isNaN(o.v)){ if(b.style.display!=='none')b.style.display='none'; }
    else{
      if(b.style.display==='none')b.style.display='';
      var pct=Math.max(3,Math.round(o.v/max*100));
      var w=pct+'%';
      if(b.style.width!==w)b.style.width=w;
    }
  }
}
setInterval(function(){try{_run()}catch(e){}},500);
try{_run()}catch(e){}
}catch(_e){console.log('[ULFILL-ERR]',_e&&_e.message)}})();
