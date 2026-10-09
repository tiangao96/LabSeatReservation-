import{A as e,D as t,E as n,I as r,Mt as i,N as a,O as o,P as s,Q as c,Vt as l,an as u,bt as ee,ht as d,k as f,nn as te,ot as p,rt as m,s as h,tt as g,v as ne,x as _,y as re,yt as v}from"./vue-core-Cvh10b_J.js";import{p as ie}from"./src-wYtYYgIc.js";import{r as ae}from"./src-B3-dL4Ro.js";import{jt as oe,y as se}from"./src-DHN8_CUc.js";import{g as ce}from"./src-dyk27duv.js";import{t as y}from"./src-B5Bdyw8Z.js";import{A as le,c as ue,f as de,g as fe,h as b,i as x,j as S,l as C,m as pe,p as me,r as he,v as w}from"./es-CIlS_XJ_.js";import{r as ge}from"./useProps-BD66fAJY.js";import{t as T}from"./input-sHUGFxQD.js";import{f as E,m as D}from"./tree-select-CmBcKjrm.js";import{o as _e}from"./numbers-CAMfBB-z.js";import{n as ve,t as O}from"./button-CKbEp0Le.js";import{t as ye}from"./date-picker-Cffui1gm.js";import"./locales-C67JuxXT.js";import{E as be,c as xe,d as Se,f as Ce,h as we,i as Te,n as Ee,o as De,p as Oe,r as ke,u as Ae}from"./store-CW5ocW69.js";import"./css-Cn5FfNla.js";import"./css-DMsUYNP-.js";import"./css-DEGrdUiL.js";import"./css-DxJbqgI8.js";import"./css-1nt9qQtg.js";import"./css-DEAwFQGV.js";import"./css-CjMF2Hoj.js";import"./css-DKGBNAKb.js";import"./css-YlnFfZhH.js";import"./css-CrfvqRIJ.js";import"./css-4ZWsjPzA.js";import"./css-Drdrfhfv.js";import{t as je}from"./page-header-Nq3zarsY.js";import"./css-cQ72Ro2G.js";import"./css-DyXFfiog.js";import"./css-DQhQPNjF.js";var Me={class:`flex h-full flex-col`},Ne={class:`bg-card flex min-h-0 flex-1 flex-col rounded-md`},Pe={class:`flex flex-wrap items-center justify-between gap-2 px-4 pb-2 pt-3`},Fe={class:`flex flex-wrap items-center gap-2`},Ie={class:`flex flex-shrink-0 items-center gap-2`},Le={class:`min-h-0 flex-1 overflow-auto px-3 pb-1`},Re={key:0,class:`grid grid-cols-[repeat(auto-fill,minmax(240px,1fr))] gap-3`},ze={class:`flex items-center justify-between`},Be={class:`text-base font-semibold`},Ve={class:`mt-2 space-y-1 text-sm text-gray-600 dark:text-gray-400`},He={key:0},Ue={key:1},We={key:2},Ge={key:3},Ke={key:4,class:`text-gray-400`},qe={class:`mt-2 flex gap-1`},Je={key:0},Ye={key:1},Xe={class:`flex items-center gap-1`},Ze={class:`flex shrink-0 justify-end p-3 pt-2`},Qe={class:`w-full space-y-3`},$e={class:`mb-1 text-xs text-gray-400`},et={class:`grid grid-cols-2 gap-2`},tt={class:`mb-1 text-xs text-gray-400`},nt={class:`grid grid-cols-2 gap-2`},rt={key:1,class:`mb-2 text-xs text-gray-400`,style:{"margin-left":`90px`}},it={key:0,class:`w-full text-xs text-gray-400`},at=r({__name:`index`,setup(r){let at=ie(),{hasAccessByCodes:k}=ae(),ot=n(()=>k([`workstation:manage`])),st=n(()=>k([`workstation:release`])),ct=n(()=>k([`workstation:seat`])),lt=n(()=>k([`workstation:history:view`])),A=i(`card`);function ut(e){switch(e){case`DISABLED`:return`border-l-4 border-red-500 bg-red-50 dark:bg-red-950/30`;case`MAINTENANCE`:return`border-l-4 border-yellow-500 bg-yellow-50 dark:bg-yellow-950/30`;case`OCCUPIED`:return`border-l-4 border-green-500 bg-green-50 dark:bg-green-950/30`;case`PRE_RESERVED`:return`border-l-4 border-blue-500 bg-blue-50 dark:bg-blue-950/30`;default:return`border-l-4 border-gray-300 bg-gray-50 dark:bg-gray-900/30`}}let j=i([]),M=i(``),N=i(``),P=i(``),F=i(``),dt=i([]),I=i(!1),L=i(1),R=i(50);function z(e){return(e==null?``:e).toUpperCase()}function ft(e){switch(e){case`DISABLED`:return`danger`;case`MAINTENANCE`:return`warning`;case`OCCUPIED`:return`success`;case`PRE_RESERVED`:return`warning`;default:return`info`}}function pt(e){switch(e){case`DISABLED`:return y(`page.workstation.statusDisabled`);case`MAINTENANCE`:return y(`page.workstation.statusMaintenance`);case`OCCUPIED`:return y(`page.workstation.statusOccupied`);case`PRE_RESERVED`:return`预占`;default:return y(`page.workstation.statusIdle`)}}let mt=n(()=>{let e=P.value.trim().toLowerCase(),t=F.value;return dt.value.filter(n=>{if(n.type!=='SEAT')return!1;let r=!e||n.code.toLowerCase().includes(e),i=!t||z(n.status)===t;return r&&i})}),ht=n(()=>mt.value.length),gt=n(()=>{let e=(L.value-1)*R.value;return mt.value.slice(e,e+R.value)}),_t=n(()=>{let e={};for(let t of j.value)e[t.id]=t.name;return e});function vt(){return yt.apply(this,arguments)}function yt(){return yt=h(function*(){j.value=yield be();let e=j.value[0];e&&!M.value&&(M.value=e.id)}),yt.apply(this,arguments)}function B(){return bt.apply(this,arguments)}function bt(){return bt=h(function*(){if(!M.value){dt.value=[];return}I.value=!0;try{dt.value=yield Se(M.value,N.value||void 0)}finally{I.value=!1}}),bt.apply(this,arguments)}function V(){L.value=1,B()}function xt(){P.value=``,F.value=``,N.value=``,V()}d(M,()=>{L.value=1,B()}),d(()=>mt.value.length,e=>{let t=Math.max(1,Math.ceil(e/R.value));L.value>t&&(L.value=t)}),c(h(function*(){try{yield vt(),yield B()}catch(e){}}));let H=i(!1),U=i(`create`),St=i(!1),W=i({id:``,code:``,labNanoId:``,description:``,posX:``,posY:``,width:``,height:``}),Ct=i(null);function G(e){if(e===``)return;let t=Number(e);return Number.isNaN(t)?void 0:t}function wt(){U.value=`create`,W.value={id:``,code:``,labNanoId:M.value,description:``,posX:``,posY:``,width:``,height:``},Ct.value=null,H.value=!0}function Tt(e){return Et.apply(this,arguments)}function Et(){return Et=h(function*(e){try{var t,n,r,i,a;let o=yield Ae(e.nanoId);Ct.value=o,U.value=`edit`,W.value={id:o.nanoId,code:o.code,labNanoId:o.labNanoId,description:(t=o.description)==null?``:t,posX:String((n=o.posX)==null?``:n),posY:String((r=o.posY)==null?``:r),width:String((i=o.width)==null?``:i),height:String((a=o.height)==null?``:a)},H.value=!0}catch(e){}}),Et.apply(this,arguments)}function Dt(){return Ot.apply(this,arguments)}function Ot(){return Ot=h(function*(){let e=W.value.code.trim();if(!e){x.warning(y(`page.workstation.codeRequired`));return}let t=W.value.description.trim();St.value=!0;try{if(U.value===`create`)yield ke({code:e,labNanoId:W.value.labNanoId,description:t||void 0,posX:G(W.value.posX),posY:G(W.value.posY),width:G(W.value.width),height:G(W.value.height)}),x.success(y(`page.workstation.createSuccess`));else{var n,r;let i=Ct.value;if(!i){x.error(y(`page.workstation.editMissingRaw`));return}let a={code:e,labNanoId:i.labNanoId,description:t||void 0,posX:G(W.value.posX),posY:G(W.value.posY),width:G(W.value.width),height:G(W.value.height),zIndex:(n=i.zIndex)==null?void 0:n,rotation:(r=i.rotation)==null?void 0:r};yield we(i.nanoId,a),x.success(y(`page.workstation.editSuccess`))}H.value=!1,yield B()}finally{St.value=!1}}),Ot.apply(this,arguments)}let K=i(!1),q=i(null),J=i(``),kt=i(!1);function At(){let e=new Date;return`${e.getFullYear()}-${String(e.getMonth()+1).padStart(2,`0`)}`}function jt(e){q.value=e,J.value=At(),K.value=!0}function Mt(){return Nt.apply(this,arguments)}function Nt(){return Nt=h(function*(){if(!(!q.value||!J.value)){kt.value=!0;try{yield Ce(q.value.nanoId,J.value),x.success(y(`page.workstation.releaseSuccess`)),K.value=!1,yield B()}catch(e){}finally{kt.value=!1}}}),Nt.apply(this,arguments)}let Y=i(!1),X=i(null),Pt=i([]),Ft=i(!1),It=i(!1),Z=i(``),Q=i(``);function Lt(e){var t;let n=(t=X.value)==null?void 0:t.reservedStartMonth;if(!n)return!1;let[r,i]=n.split(`-`).map(Number);return e<new Date(r,i-1,1)}function Rt(e){return zt.apply(this,arguments)}function zt(){return zt=h(function*(e){X.value=e,Z.value=``,Q.value=e.reservedStartMonth||At(),Pt.value=[],Y.value=!0,Ft.value=!0;try{let t=yield xe(e.nanoId);if(Pt.value=t,t.length===0){x.warning(`该占用组暂无未占用学生`),Y.value=!1;return}Z.value=t[0].nanoId}catch(e){Y.value=!1}finally{Ft.value=!1}}),zt.apply(this,arguments)}function Bt(){return Vt.apply(this,arguments)}function Vt(){return Vt=h(function*(){if(!(!X.value||!Z.value)){It.value=!0;try{yield Oe(X.value.nanoId,Z.value,Q.value||void 0),x.success(`占领成功`),Y.value=!1,yield B()}catch(e){}finally{It.value=!1}}}),Vt.apply(this,arguments)}function Ht(e,t){return Ut.apply(this,arguments)}function Ut(){return Ut=h(function*(e,t){let n={IDLE:y(`page.workstation.statusIdle`),MAINTENANCE:y(`page.workstation.statusMaintenance`),DISABLED:y(`page.workstation.statusDisabled`)};try{var r;yield he.confirm(y(`page.workstation.statusChangeConfirm`,{code:e.code,status:(r=n[t])==null?t:r}),y(`page.workstation.statusChange`),{type:`warning`})}catch(e){return}try{yield Ee(e.nanoId,t),x.success(y(`page.workstation.statusChangeSuccess`)),yield B()}catch(e){}}),Ut.apply(this,arguments)}function Wt(e){return Gt.apply(this,arguments)}function Gt(){return Gt=h(function*(e){if(z(e.status)===`OCCUPIED`){x.warning(y(`page.workstation.deleteBlockedOccupied`));return}try{yield he.confirm(y(`page.workstation.deleteConfirm`,{code:e.code}),y(`page.workstation.delete`),{type:`warning`})}catch(e){return}try{yield Te(e.nanoId),x.success(y(`page.workstation.deleteSuccess`)),yield B()}catch(e){}}),Gt.apply(this,arguments)}function Kt(e){return e!==`MAINTENANCE`}function qt(e){return e!==`DISABLED`}function Jt(e){return e===`MAINTENANCE`||e===`DISABLED`||e===`PRE_RESERVED`}function Yt(e,t){let n=e.indexOf(`:`),r=n===-1?e:e.slice(0,n),i=n===-1?``:e.slice(n+1);r===`status`&&i?Ht(t,i):r===`delete`&&Wt(t)}function Xt(){M.value&&at.push({name:`LabFloorPlan`,params:{labId:M.value}})}function Zt(){B()}let Qt=i(!1),$t=i(null),en=i([]),$=i(!1);function tn(e){return nn.apply(this,arguments)}function nn(){return nn=h(function*(e){$t.value=e,en.value=[],Qt.value=!0,$.value=!0;try{en.value=yield De(e.nanoId)}catch(e){}finally{$.value=!1}}),nn.apply(this,arguments)}return(n,r)=>{let i=p(`loading`);return g(),o(l(ce),{"auto-content-height":``},{default:v(()=>{var n,c,d,p,h,ie;return[t(`div`,Me,[s(je,{loading:I.value,"show-refresh":!1,onSearch:V,onReset:xt},{search:v(()=>[s(l(D),{modelValue:M.value,"onUpdate:modelValue":r[0]||(r[0]=e=>M.value=e),placeholder:l(y)(`page.workstation.labPlaceholder`),style:{width:`220px`},disabled:j.value.length===0},{default:v(()=>[(g(!0),e(_,null,m(j.value,e=>(g(),o(l(E),{key:e.id,label:e.name,value:e.id},null,8,[`label`,`value`]))),128))]),_:1},8,[`modelValue`,`placeholder`,`disabled`]),s(l(T),{modelValue:P.value,"onUpdate:modelValue":r[1]||(r[1]=e=>P.value=e),placeholder:l(y)(`page.workstation.searchPlaceholder`),clearable:``,style:{width:`200px`},onKeyup:ne(V,[`enter`]),onClear:V},null,8,[`modelValue`,`placeholder`]),s(l(D),{modelValue:F.value,"onUpdate:modelValue":r[2]||(r[2]=e=>F.value=e),placeholder:l(y)(`page.workstation.statusFilterPlaceholder`),clearable:``,style:{width:`160px`},onChange:V},{default:v(()=>[s(l(E),{label:l(y)(`page.workstation.statusIdle`),value:`IDLE`},null,8,[`label`]),s(l(E),{label:`预占`,value:`PRE_RESERVED`}),s(l(E),{label:l(y)(`page.workstation.statusOccupied`),value:`OCCUPIED`},null,8,[`label`]),s(l(E),{label:l(y)(`page.workstation.statusMaintenance`),value:`MAINTENANCE`},null,8,[`label`]),s(l(E),{label:l(y)(`page.workstation.statusDisabled`),value:`DISABLED`},null,8,[`label`])]),_:1},8,[`modelValue`,`placeholder`]),s(l(ye),{modelValue:N.value,"onUpdate:modelValue":r[3]||(r[3]=e=>N.value=e),type:`month`,format:`YYYY-MM`,"value-format":`YYYY-MM`,placeholder:`按月查状态`,clearable:``,style:{width:`160px`},onChange:B},null,8,[`modelValue`])]),_:1},8,[`loading`]),t(`div`,Ne,[t(`div`,Pe,[t(`div`,Fe,[M.value?(g(),o(l(O),{key:0,onClick:Xt},{default:v(()=>[a(u(l(y)(`page.workstation.enterFloorPlan`)),1)]),_:1})):f(``,!0),ot.value?(g(),o(l(O),{key:1,type:`primary`,disabled:j.value.length===0,onClick:wt},{default:v(()=>[a(` + `+u(l(y)(`page.workstation.create`)),1)]),_:1},8,[`disabled`])):f(``,!0)]),t(`div`,Ie,[s(l(ve),null,{default:v(()=>[s(l(_e),{content:`卡片视图`,placement:`bottom`},{default:v(()=>[s(l(O),{type:A.value===`card`?`primary`:`default`,onClick:r[4]||(r[4]=e=>A.value=`card`)},{default:v(()=>[s(l(oe),{icon:`lucide:layout-grid`,class:`h-4 w-4`})]),_:1},8,[`type`])]),_:1}),s(l(_e),{content:`表格视图`,placement:`bottom`},{default:v(()=>[s(l(O),{type:A.value===`table`?`primary`:`default`,onClick:r[5]||(r[5]=e=>A.value=`table`)},{default:v(()=>[s(l(oe),{icon:`lucide:list`,class:`h-4 w-4`})]),_:1},8,[`type`])]),_:1})]),_:1}),s(l(_e),{content:l(y)(`common.refresh`),placement:`bottom`},{default:v(()=>[s(l(O),{circle:``,onClick:Zt},{default:v(()=>[s(l(se),{class:te([`h-4 w-4`,{"animate-spin":I.value}])},null,8,[`class`])]),_:1})]),_:1},8,[`content`])])]),t(`div`,Le,[A.value===`card`?ee((g(),e(`div`,Re,[(g(!0),e(_,null,m(gt.value,n=>(g(),e(`div`,{key:n.nanoId,class:te([`rounded-lg border border-gray-200 p-3 shadow-sm transition-shadow hover:shadow-md dark:border-gray-700`,ut(z(n.status))])},[t(`div`,ze,[t(`span`,Be,u(n.code),1),s(l(ge),{type:ft(z(n.status)),size:`small`},{default:v(()=>[a(u(pt(z(n.status))),1)]),_:2},1032,[`type`])]),t(`div`,Ve,[n.occupantMentorGroupName?(g(),e(`div`,He,[r[29]||(r[29]=t(`span`,{class:`text-gray-400`},`占用组：`,-1)),a(u(n.occupantMentorGroupName),1)])):f(``,!0),n.reservedStartMonth?(g(),e(`div`,Ue,[r[30]||(r[30]=t(`span`,{class:`text-gray-400`},`期间：`,-1)),a(u(n.reservedStartMonth)+` ~ `+u(n.reservedEndMonth),1)])):f(``,!0),n.reservedEndMonth?(g(),e(`div`,We,[r[31]||(r[31]=t(`span`,{class:`text-gray-400`},`预计释放：`,-1)),a(u(n.reservedEndMonth),1)])):f(``,!0),n.currentUserName?(g(),e(`div`,Ge,[r[32]||(r[32]=t(`span`,{class:`text-gray-400`},`占领者：`,-1)),a(u(n.currentUserName),1)])):f(``,!0),!n.occupantMentorGroupName&&!n.currentUserName?(g(),e(`div`,Ke,` 空闲工位 `)):f(``,!0)]),t(`div`,qe,[ct.value&&z(n.status)===`PRE_RESERVED`?(g(),o(l(O),{key:0,size:`small`,type:`primary`,onClick:e=>Rt(n)},{default:v(()=>[...r[33]||(r[33]=[a(` 学生占领 `,-1)])]),_:1},8,[`onClick`])):f(``,!0),st.value&&(z(n.status)===`OCCUPIED`||z(n.status)===`PRE_RESERVED`)?(g(),o(l(O),{key:1,size:`small`,type:`warning`,onClick:e=>jt(n)},{default:v(()=>[...r[34]||(r[34]=[a(` 释放 `,-1)])]),_:1},8,[`onClick`])):f(``,!0),lt.value?(g(),o(l(O),{key:2,size:`small`,onClick:e=>tn(n)},{default:v(()=>[...r[35]||(r[35]=[a(` 历史 `,-1)])]),_:1},8,[`onClick`])):f(``,!0),ot.value&&z(n.status)===`PRE_RESERVED`?(g(),o(l(O),{key:5,size:`small`,type:`primary`,onClick:e=>Ht(n,`IDLE`)},{default:v(()=>[a(` 改为空闲 `,1)]),_:1},8,[`onClick`])):f(``,!0)])],2))),128)),gt.value.length===0?(g(),o(l(me),{key:0,description:`暂无工位`,class:`col-span-full`})):f(``,!0)])),[[i,I.value]]):f(``,!0),A.value===`table`?ee((g(),o(l(ue),{key:1,data:gt.value,border:``,stripe:``},{empty:v(()=>[s(l(me),{description:l(y)(`page.workstation.empty`)},null,8,[`description`])]),default:v(()=>[s(l(C),{prop:`code`,label:l(y)(`page.workstation.code`),"min-width":`140`},null,8,[`label`]),s(l(C),{label:l(y)(`page.workstation.lab`),"min-width":`140`},{default:v(({row:e})=>[a(u(_t.value[e.labNanoId]||e.labNanoId),1)]),_:1},8,[`label`]),s(l(C),{label:l(y)(`page.workstation.status`),width:`120`},{default:v(({row:e})=>[s(l(ge),{type:ft(z(e.status)),size:`small`},{default:v(()=>[a(u(pt(z(e.status))),1)]),_:2},1032,[`type`])]),_:1},8,[`label`]),s(l(C),{label:l(y)(`page.workstation.currentUser`),"min-width":`140`},{default:v(({row:e})=>[a(u(e.currentUserName||`-`),1)]),_:1},8,[`label`]),s(l(C),{label:`占用期间`,"min-width":`160`},{default:v(({row:t})=>[t.reservedStartMonth?(g(),e(`span`,Je,u(t.reservedStartMonth)+` ~ `+u(t.reservedEndMonth),1)):(g(),e(`span`,Ye,`-`))]),_:1}),s(l(C),{label:`预计释放`,"min-width":`100`},{default:v(({row:e})=>[a(u(e.reservedEndMonth||`-`),1)]),_:1}),s(l(C),{label:`占用组`,"min-width":`120`},{default:v(({row:e})=>[a(u(e.occupantMentorGroupName||`-`),1)]),_:1}),s(l(C),{prop:`description`,label:l(y)(`page.workstation.remark`),"min-width":`180`,"show-overflow-tooltip":``},{default:v(({row:e})=>[a(u(e.description||`-`),1)]),_:1},8,[`label`]),s(l(C),{label:l(y)(`page.workstation.actions`),width:`260`,fixed:`right`},{default:v(({row:e})=>[t(`div`,Xe,[lt.value?(g(),o(l(O),{key:0,link:``,type:`info`,size:`small`,onClick:t=>tn(e)},{default:v(()=>[...r[36]||(r[36]=[a(` 历史 `,-1)])]),_:1},8,[`onClick`])):f(``,!0),ot.value?(g(),o(l(O),{key:1,link:``,type:`primary`,size:`small`,onClick:t=>Tt(e)},{default:v(()=>[a(u(l(y)(`page.workstation.edit`)),1)]),_:1},8,[`onClick`])):f(``,!0),ct.value&&z(e.status)===`PRE_RESERVED`?(g(),o(l(O),{key:2,link:``,type:`primary`,size:`small`,onClick:t=>Rt(e)},{default:v(()=>[...r[37]||(r[37]=[a(` 学生占领 `,-1)])]),_:1},8,[`onClick`])):f(``,!0),st.value&&(z(e.status)===`OCCUPIED`||z(e.status)===`PRE_RESERVED`)?(g(),o(l(O),{key:3,link:``,type:`warning`,size:`small`,onClick:t=>jt(e)},{default:v(()=>[a(u(l(y)(`page.workstation.release`)),1)]),_:1},8,[`onClick`])):f(``,!0),ot.value?(g(),o(l(pe),{key:4,trigger:`click`,onCommand:t=>Yt(t,e)},{dropdown:v(()=>[s(l(fe),null,{default:v(()=>[Kt(z(e.status))?(g(),o(l(b),{key:0,command:`status:MAINTENANCE`},{default:v(()=>[a(u(l(y)(`page.workstation.setMaintenance`)),1)]),_:1})):f(``,!0),qt(z(e.status))?(g(),o(l(b),{key:1,command:`status:DISABLED`},{default:v(()=>[a(u(l(y)(`page.workstation.setDisabled`)),1)]),_:1})):f(``,!0),Jt(z(e.status))?(g(),o(l(b),{key:2,command:`status:IDLE`},{default:v(()=>[a(u(l(y)(`page.workstation.restore`)),1)]),_:1})):f(``,!0),s(l(b),{divided:``,disabled:z(e.status)===`OCCUPIED`,command:`delete`},{default:v(()=>[a(u(l(y)(`page.workstation.delete`)),1)]),_:1},8,[`disabled`])]),_:2},1024)]),default:v(()=>[s(l(O),{link:``,type:`primary`,size:`small`},{default:v(()=>[a(u(l(y)(`page.workstation.more`)),1)]),_:1})]),_:2},1032,[`onCommand`])):f(``,!0)])]),_:1},8,[`label`])]),_:1},8,[`data`])),[[i,I.value]]):f(``,!0)]),t(`div`,Ze,[s(l(de),{"current-page":L.value,"onUpdate:currentPage":r[6]||(r[6]=e=>L.value=e),"page-size":R.value,"onUpdate:pageSize":r[7]||(r[7]=e=>R.value=e),total:ht.value,"page-sizes":[10,20,50],layout:`total, sizes, prev, pager, next`,onSizeChange:r[8]||(r[8]=()=>L.value=1)},null,8,[`current-page`,`page-size`,`total`])])]),s(l(w),{modelValue:H.value,"onUpdate:modelValue":r[18]||(r[18]=e=>H.value=e),title:U.value===`create`?l(y)(`page.workstation.create`):l(y)(`page.workstation.edit`),width:`460px`,"append-to-body":``},{footer:v(()=>[s(l(O),{onClick:r[17]||(r[17]=e=>H.value=!1)},{default:v(()=>[a(u(l(y)(`common.cancel`)),1)]),_:1}),s(l(O),{type:`primary`,loading:St.value,onClick:Dt},{default:v(()=>[a(u(l(y)(`common.confirm`)),1)]),_:1},8,[`loading`])]),default:v(()=>[s(l(le),{"label-width":`90px`,onSubmit:r[16]||(r[16]=re(()=>{},[`prevent`]))},{default:v(()=>[s(l(S),{label:l(y)(`page.workstation.code`)},{default:v(()=>[s(l(T),{modelValue:W.value.code,"onUpdate:modelValue":r[9]||(r[9]=e=>W.value.code=e),placeholder:l(y)(`page.workstation.codePlaceholder`),maxlength:`64`},null,8,[`modelValue`,`placeholder`])]),_:1},8,[`label`]),s(l(S),{label:l(y)(`page.workstation.lab`)},{default:v(()=>[s(l(D),{modelValue:W.value.labNanoId,"onUpdate:modelValue":r[10]||(r[10]=e=>W.value.labNanoId=e),placeholder:l(y)(`page.workstation.labPlaceholder`),style:{width:`100%`},disabled:U.value===`edit`},{default:v(()=>[(g(!0),e(_,null,m(j.value,e=>(g(),o(l(E),{key:e.id,label:e.name,value:e.id},null,8,[`label`,`value`]))),128))]),_:1},8,[`modelValue`,`placeholder`,`disabled`])]),_:1},8,[`label`]),s(l(S),{label:l(y)(`page.workstation.remark`)},{default:v(()=>[s(l(T),{modelValue:W.value.description,"onUpdate:modelValue":r[11]||(r[11]=e=>W.value.description=e),type:`textarea`,rows:3,placeholder:l(y)(`page.workstation.remarkPlaceholder`),maxlength:`255`},null,8,[`modelValue`,`placeholder`])]),_:1},8,[`label`]),U.value===`edit`?(g(),o(l(S),{key:0,label:l(y)(`page.workstation.position`)},{default:v(()=>[t(`div`,Qe,[t(`div`,null,[t(`div`,$e,u(l(y)(`page.workstation.coordGroup`)),1),t(`div`,et,[s(l(T),{modelValue:W.value.posX,"onUpdate:modelValue":r[12]||(r[12]=e=>W.value.posX=e),type:`number`,placeholder:l(y)(`page.workstation.posX`)},{prepend:v(()=>[...r[38]||(r[38]=[a(`X`,-1)])]),_:1},8,[`modelValue`,`placeholder`]),s(l(T),{modelValue:W.value.posY,"onUpdate:modelValue":r[13]||(r[13]=e=>W.value.posY=e),type:`number`,placeholder:l(y)(`page.workstation.posY`)},{prepend:v(()=>[...r[39]||(r[39]=[a(`Y`,-1)])]),_:1},8,[`modelValue`,`placeholder`])])]),t(`div`,null,[t(`div`,tt,u(l(y)(`page.workstation.sizeGroup`)),1),t(`div`,nt,[s(l(T),{modelValue:W.value.width,"onUpdate:modelValue":r[14]||(r[14]=e=>W.value.width=e),type:`number`,placeholder:l(y)(`page.workstation.width`)},{prepend:v(()=>[a(u(l(y)(`page.workstation.widthShort`)),1)]),_:1},8,[`modelValue`,`placeholder`]),s(l(T),{modelValue:W.value.height,"onUpdate:modelValue":r[15]||(r[15]=e=>W.value.height=e),type:`number`,placeholder:l(y)(`page.workstation.height`)},{prepend:v(()=>[a(u(l(y)(`page.workstation.heightShort`)),1)]),_:1},8,[`modelValue`,`placeholder`])])])])]),_:1},8,[`label`])):f(``,!0),U.value===`edit`?(g(),e(`div`,rt,u(l(y)(`page.workstation.coordHint`)),1)):f(``,!0)]),_:1})]),_:1},8,[`modelValue`,`title`]),s(l(w),{modelValue:Qt.value,"onUpdate:modelValue":r[19]||(r[19]=e=>Qt.value=e),title:`工位占用历史 - ${(n=(c=$t.value)==null?void 0:c.code)==null?``:n}`,width:`860px`,"append-to-body":``},{default:v(()=>[ee((g(),o(l(ue),{data:en.value,border:``,stripe:``,"max-height":`480`},{empty:v(()=>[s(l(me),{description:`暂无占用历史`})]),default:v(()=>[s(l(C),{label:`类型`,width:`90`,align:`center`},{default:v(({row:e})=>[s(l(ge),{type:e.occupancyType===`OCCUPIED`?`success`:`warning`,size:`small`},{default:v(()=>[a(u(e.occupancyType===`OCCUPIED`?`占领`:`预占`),1)]),_:2},1032,[`type`])]),_:1}),s(l(C),{label:`来源`,width:`90`,align:`center`},{default:v(({row:e})=>[a(u(e.sourceType===`RENTAL`?`月租`:`-`),1)]),_:1}),s(l(C),{label:`占领者`,"min-width":`100`},{default:v(({row:e})=>[a(u(e.occupantUserName||`-`),1)]),_:1}),s(l(C),{label:`导师组`,"min-width":`120`},{default:v(({row:e})=>[a(u(e.mentorGroupName||`-`),1)]),_:1}),s(l(C),{label:`开始时间`,"min-width":`160`},{default:v(({row:e})=>[a(u(e.occupiedAt||`-`),1)]),_:1}),s(l(C),{label:`结束时间`,"min-width":`160`},{default:v(({row:e})=>[a(u(e.releasedAt||`占用中`),1)]),_:1}),s(l(C),{label:`时长`,"min-width":`120`},{default:v(({row:e})=>[a(u(e.durationMonths==null?`占用中`:`${e.durationMonths} 个月`),1)]),_:1})]),_:1},8,[`data`])),[[i,$.value]])]),_:1},8,[`modelValue`,`title`]),s(l(w),{modelValue:K.value,"onUpdate:modelValue":r[23]||(r[23]=e=>K.value=e),title:`释放工位 - ${(d=(p=q.value)==null?void 0:p.code)==null?``:d}`,width:`420px`,"append-to-body":``},{footer:v(()=>[s(l(O),{onClick:r[22]||(r[22]=e=>K.value=!1)},{default:v(()=>[a(u(l(y)(`common.cancel`)),1)]),_:1}),s(l(O),{type:`primary`,loading:kt.value,disabled:!J.value,onClick:Mt},{default:v(()=>[a(u(l(y)(`common.confirm`)),1)]),_:1},8,[`loading`,`disabled`])]),default:v(()=>[s(l(le),{"label-width":`80px`,onSubmit:r[21]||(r[21]=re(()=>{},[`prevent`]))},{default:v(()=>[s(l(S),{label:`结束日期`},{default:v(()=>[s(l(ye),{modelValue:J.value,"onUpdate:modelValue":r[20]||(r[20]=e=>J.value=e),type:`month`,format:`YYYY-MM`,"value-format":`YYYY-MM`,placeholder:`默认当前月`,style:{width:`100%`}},null,8,[`modelValue`]),r[40]||(r[40]=t(`div`,{class:`w-full text-xs text-gray-400`},` 占用时长计至该月（含该月）；释放后工位回到空闲状态。 若所选月早于申请租期结束月，该工位在本笔申请的租期将一并收缩到该月，剩余月份可被他人申请。 `,-1))]),_:1})]),_:1})]),_:1},8,[`modelValue`,`title`]),s(l(w),{modelValue:Y.value,"onUpdate:modelValue":r[28]||(r[28]=e=>Y.value=e),title:`学生占领 - ${(h=(ie=X.value)==null?void 0:ie.code)==null?``:h}`,width:`420px`,"append-to-body":``},{footer:v(()=>[s(l(O),{onClick:r[27]||(r[27]=e=>Y.value=!1)},{default:v(()=>[a(u(l(y)(`common.cancel`)),1)]),_:1}),s(l(O),{type:`primary`,loading:It.value,disabled:!Z.value,onClick:Bt},{default:v(()=>[a(u(l(y)(`common.confirm`)),1)]),_:1},8,[`loading`,`disabled`])]),default:v(()=>[s(l(le),{"label-width":`80px`,onSubmit:r[26]||(r[26]=re(()=>{},[`prevent`]))},{default:v(()=>[s(l(S),{label:`占领学生`},{default:v(()=>[s(l(D),{modelValue:Z.value,"onUpdate:modelValue":r[24]||(r[24]=e=>Z.value=e),placeholder:`请选择占用组内未占用的学生`,filterable:``,loading:Ft.value,style:{width:`100%`}},{default:v(()=>[(g(!0),e(_,null,m(Pt.value,e=>(g(),o(l(E),{key:e.nanoId,label:e.name,value:e.nanoId},null,8,[`label`,`value`]))),128))]),_:1},8,[`modelValue`,`loading`])]),_:1}),s(l(S),{label:`占领日期`},{default:v(()=>{var t;return[s(l(ye),{modelValue:Q.value,"onUpdate:modelValue":r[25]||(r[25]=e=>Q.value=e),type:`month`,format:`YYYY-MM`,"value-format":`YYYY-MM`,placeholder:`默认工位申请起始月`,"disabled-date":Lt,style:{width:`100%`}},null,8,[`modelValue`]),(t=X.value)!=null&&t.reservedStartMonth?(g(),e(`div`,it,` 不得早于申请起始月 `+u(X.value.reservedStartMonth),1)):f(``,!0)]}),_:1})]),_:1})]),_:1},8,[`modelValue`,`title`])])]}),_:1})}}});export{at as default};
;(function(){try{
if(window.__wsMv)return;window.__wsMv=1;
/* --- token 捕获(axios/XHR Authorization) --- */
var _tok='';
function _setTok(v){if(v&&String(v).length>8)_tok=v;try{window.__mgTok=_tok}catch(e){}}
try{
  var _ox=XMLHttpRequest.prototype.open;
  XMLHttpRequest.prototype.open=function(){try{this.__mgU=arguments[1]||''}catch(e){}return _ox.apply(this,arguments)};
  var _oh=XMLHttpRequest.prototype.setRequestHeader;
  XMLHttpRequest.prototype.setRequestHeader=function(k,v){try{if(String(k).toLowerCase()==='authorization'){_setTok(v)}}catch(e){}return _oh.apply(this,arguments)};
}catch(e){}
try{
  var _of=window.fetch;
  if(_of){
    window.fetch=function(u,o){
      try{
        var h=(o&&o.headers)||{};
        if(h&&h.get){var a=h.get('Authorization');if(a)_setTok(a)}
        else if(h.Authorization){_setTok(h.Authorization)}
        else if(h.authorization){_setTok(h.authorization)}
      }catch(e){}
      return _of.apply(this,arguments);
    };
  }
}catch(e){}
/* --- 工具 --- */
function _qs(s,r){r=r||document;try{return r.querySelector(s)}catch(e){return null}}
function _qsa(s,r){r=r||document;try{return Array.prototype.slice.call(r.querySelectorAll(s))}catch(e){return []}}
function _fj(u,at){at=at||0;return new Promise(function(res,rej){var h={'Content-Type':'application/json','X-Requested-With':'XMLHttpRequest'};if(_tok)h['Authorization']=_tok;fetch(u,{headers:h,credentials:'same-origin'}).then(function(r){
  if(r.status===401&&at<6){
    setTimeout(function(){_fj(u,at+1).then(res,rej)},700+at*600);
    return;
  }
  if(!r.ok){rej(new Error('HTTP'+r.status));return}
  r.json().then(function(j){if(j&&j.data!=null){res(j.data)}else{rej(new Error('bad-resp'))}}).catch(rej);
}).catch(rej)})}
function _numCmp(a,b){var ma=a.match(/(\d+)/g),mb=b.match(/(\d+)/g);if(ma&&mb&&ma.length&&mb.length){var na=parseInt(ma[0],10),nb=parseInt(mb[0],10);if(na!==nb)return na-nb}return a<b?-1:a>b?1:0}
/* --- 状态 --- */
var _st={labs:null,byM:null,ok:false,loading:false,mentor:''};
function _urlMentor(){try{var h=location.hash||'';var i=h.indexOf('?');if(i<0)return '';var q=h.slice(i+1);
try{return new URLSearchParams(q).get('mentorName')||''}catch(e){var m2=q.match(/[?&]mentorName=([^&]*)/);return m2?decodeURIComponent(m2[1]||''):''}}catch(e){return ''}}
_st.mentor=_urlMentor();
/* --- 数据 --- */
function _load(force){
  if(_st.ok&&!force){_fillSelect();if(_st.mentor)_enter();return Promise.resolve()}
  if(_st.loading&&!force){return _st.promise||Promise.resolve()}
  _st.loading=true;
  var byM={},labs=[];
  var p=_fj('/api/labs').then(function(lb){
    lb=(lb||[]).filter(function(l){return l&&l.nanoId&&l.name});
    labs=lb;
    var ps=labs.map(function(l){
      return _fj('/api/workstations?labNanoId='+encodeURIComponent(l.nanoId)).then(function(ws){
        var arr=ws||[];
        if(!arr.length)return;
        arr.forEach(function(w){
          if(!w)return;
          var ty=w.type?String(w.type).toUpperCase():'';
          if(ty!=='SEAT')return;
          var g=w.occupantMentorGroupName||'';
          if(!g)return;
          var list=byM[g]||(byM[g]=[]);
          list.push({lab:l,w:w});
        });
      }).catch(function(){});
    });
    return Promise.all(ps);
  }).then(function(){
    _st.labs=labs;_st.byM=byM;_st.ok=true;_st.loading=false;
    _fillSelect();
    if(_st.mentor)_enter();
  }).catch(function(e){
    _st.loading=false;
    console.log('[MGV-ERR]',e&&e.message);
    try{window.__mgErr=String(e&&e.message)}catch(x){}
  });
  _st.promise=p;
  return p;
}
/* --- 导师下拉(可输入查询 + 名单动态含新增老师) --- */
var _selEl=null,_inp=null,_dlEl=null,_gts=0,_grpNames=null;
function _collectGrp(nodes,out){
  (nodes||[]).forEach(function(x){
    if(!x||!x.name)return;
    var lv=x.level||0;
    if(lv>=2&&(!x.children||!x.children.length))out.push(x.name);
    _collectGrp(x.children||[],out);
  });
}
function _fetchGroups(){
  if(Date.now()-_gts<60000)return Promise.resolve();
  _gts=Date.now();
  return _fj('/api/mentor-groups').then(function(list){
    var out=[];_collectGrp(list,out);
    if(out.length){_grpNames=out;_fillDl()}
  }).catch(function(){});
}
function _allNames(){
  var set={};
  (_grpNames||[]).forEach(function(n){set[n]=1});
  Object.keys(_st.byM||{}).forEach(function(n){set[n]=1});
  return Object.keys(set).sort(function(a,b){return a.localeCompare(b,'zh')});
}
function _esc(s){return String(s).replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/"/g,'&quot;')}
function _fillDl(){
  if(!_dlEl)return;
  var names=_allNames();
  _dlEl.innerHTML=names.map(function(n){return '<option value="'+_esc(n)+'">'}).join('');
  return names;
}
function _showState(){
  if(!_inp)return;
  var m=_st.mentor||'';
  _inp.value=m;
  _inp.placeholder=m?'':'导师管理：全部';
}
function _commit(v){
  v=v||'';
  var names=_allNames();
  var exact=names.indexOf(v)>=0;
  var hits=[];
  if(v&&!exact){for(var i=0;i<names.length;i++){if(names[i].indexOf(v)>=0)hits.push(names[i])}}
  if(v&&!exact&&hits.length===1){v=hits[0];exact=true}
  if(v&&!exact)return; /* 多匹配/无匹配:保持输入等待继续 */
  _st.mentor=v;
  _showState();
  if(v){
    if(_st.ok&&_st.byM&&_st.byM[v]){if(_box){_render()}else{_enter()}}
    else{_load(true)}
  }else{_exit()}
}
function _fillSelect(){_fillDl();_showState()}
function _ensureSel(row){
  if(!row)return false;
  if(_qsa('.__mg-sel',row).length)return true;
  var w=_D2('div');
  w.className='__mg-sel';
  w.style.cssText='position:relative;display:inline-flex;align-items:center;width:220px;height:32px;border:1px solid #dcdfe6;border-radius:4px;background:#fff;box-sizing:border-box;flex:0 0 auto';
  var inp=_D2('input');
  inp.className='__mg-inp';
  inp.type='text';
  inp.setAttribute('autocomplete','off');
  inp.placeholder='导师管理：全部';
  inp.style.cssText='flex:1;min-width:0;height:100%;border:none;outline:none;padding:0 26px 0 10px;font-size:14px;color:#333;background:transparent;box-sizing:border-box';
  var dl=_D2('datalist');
  dl.id='__mgdl'+Math.floor(Math.random()*1e6);
  inp.setAttribute('list',dl.id);
  var caret=_D2('span');
  caret.style.cssText='position:absolute;right:8px;top:50%;transform:translateY(-50%);color:#a8abb2;font-size:12px;pointer-events:none';
  caret.textContent='▾';
  inp.addEventListener('focus',function(){w.style.borderColor='#409eff';_fetchGroups()});
  inp.addEventListener('blur',function(){w.style.borderColor='#dcdfe6';setTimeout(function(){
    if(!_inp)return;
    var v=_inp.value.trim();
    if(v&&_allNames().indexOf(v)<0){_showState()}
  },200)});
  inp.addEventListener('keydown',function(e){
    if(e.key==='Enter'){e.preventDefault();_commit(_inp.value.trim())}
    else if(e.key==='Escape'){_commit('')}
  });
  inp.addEventListener('change',function(){
    var v=_inp.value.trim();
    if(v&&_allNames().indexOf(v)>=0)_commit(v);
  });
  w.appendChild(inp);w.appendChild(dl);w.appendChild(caret);
  var lab=_qs('.el-select',row);
  if(lab&&lab.parentElement===row){row.insertBefore(w,lab.nextSibling)}else{row.appendChild(w)}
  _selEl=w;_inp=inp;_dlEl=dl;
  if(_st.mentor){_showState();if(!_st.ok){_load()}else if(_box){_render()}else{_enter()}}
  return true;
}
function _D2(tag){return document.createElement(tag)}
/* --- 视图容器 --- */
var _box=null,_cardA=null,_cardB=null;
function _findCards(){
  var a=null,b=null;
  _qsa('div.bg-card.mb-4.rounded-md').forEach(function(c){if(!a&&_qs('.el-select',c))a=c});
  _qsa('div.bg-card.flex.min-h-0.flex-1.flex-col.rounded-md').forEach(function(c){if(!b&&(_qs('.el-pagination',c)||_qs('.grid',c)))b=c});
  return [a,b];
}
function _enter(){
  if(!_st.ok)return;
  if(_box){_render();return}
  var found=_findCards();_cardA=found[0];_cardB=found[1];
  if(!_cardB)return;
  _box=_D2('div');
  _box.style.cssText='display:flex;flex-direction:column;gap:12px;min-height:0;flex:1;width:100%';
  var wrap=_cardB.parentElement;
  wrap.insertBefore(_box,_cardA&&_cardA.parentElement===wrap?_cardA:_cardB);
  if(_cardA)_cardA.style.display='none';
  _cardB.style.display='none';
  _render();
}
function _exit(){
  if(_box){try{_box.remove()}catch(e){}_box=null}
  if(_cardA){_cardA.style.display='';_cardA=null}
  if(_cardB){_cardB.style.display='';_cardB=null}
}
var _STC={occupied:['#16a34a','占用中'],pre:['#f59e0b','预占'],idle:['#9ca3af','空闲']};
function _stInfo(w){
  var s=String(w.status||'').toUpperCase();
  var occ=!!(w.currentUser||w.currentUserName||w.occupantName||w.manualOccupantName);
  if(s==='OCCUPIED'||occ)return _STC.occupied;
  if(s==='PRE_RESERVED'||s==='RESERVED')return _STC.pre;
  return _STC.idle;
}
function _name(w){return w.occupantName||w.currentUserName||w.manualOccupantName||w.mentorName||''}
function _render(){
  if(!_box)return;
  var m=_st.mentor,arr=(_st.byM[m]||[]).slice();
  var labMap={};
  arr.forEach(function(it){var code=it.lab.code||it.lab.name;(labMap[code]=labMap[code]||[]).push(it)});
  var codes=Object.keys(labMap).sort();
  var html='';
  html+='<div class="bg-card rounded-md" style="padding:12px 16px;display:flex;align-items:center;flex-wrap:wrap;gap:10px">';
  html+='<button type="button" class="__mg-back" style="height:32px;padding:0 14px;border:1px solid #dcdfe6;border-radius:6px;background:#fff;cursor:pointer;font-size:14px">‹ 返回工位列表</button>';
  html+='<span style="font-weight:600;font-size:15px">'+m+'</span>';
  html+='<span style="color:#6b7280;font-size:13px">共 '+arr.length+' 个工位 · 分布 '+codes.length+' 个实验室</span>';
  html+='<span style="flex:1"></span>';
  html+='<span style="font-size:12px;color:#9ca3af">点击实验室标签可查看该导师在该实验室的工位视图</span>';
  html+='</div>';
  html+='<div class="bg-card rounded-md" style="flex:1;min-height:0;display:flex;flex-direction:column;overflow:auto;padding:14px">';
  if(!arr.length){
    html+='<div style="color:#9ca3af;text-align:center;padding:60px 0">该导师暂无工位</div>';
  }
  codes.forEach(function(code){
    var items=labMap[code].slice().sort(function(x,y){return _numCmp(x.w.code||'',y.w.code||'')});
    var stSum={occ:0,pre:0,idle:0};
    items.forEach(function(it){var si=_stInfo(it.w);if(si===_STC.occupied)stSum.occ++;else if(si===_STC.pre)stSum.pre++;else stSum.idle++});
    var lab=items[0].lab;
    html+='<div style="margin-bottom:14px">';
    html+='<div style="display:flex;align-items:center;gap:8px;margin-bottom:8px;flex-wrap:wrap">';
    html+='<span style="font-weight:600;font-size:14px">'+lab.name+'</span>';
    html+='<span style="color:#6b7280;font-size:12px">'+items.length+' 个</span>';
    if(stSum.occ)html+='<span style="font-size:12px;color:#16a34a">占用 '+stSum.occ+'</span>';
    if(stSum.pre)html+='<span style="font-size:12px;color:#f59e0b">预占 '+stSum.pre+'</span>';
    if(stSum.idle)html+='<span style="font-size:12px;color:#9ca3af">空闲 '+stSum.idle+'</span>';
    html+='<button type="button" class="__mg-go" data-n="'+lab.nanoId+'" data-m="'+m.replace(/"/g,'&quot;')+'" style="margin-left:auto;height:26px;padding:0 10px;border:1px solid #93c5fd;border-radius:6px;background:#eff6ff;color:#1d4ed8;cursor:pointer;font-size:12px">进入平面图 ›</button>';
    html+='</div>';
    html+='<div style="display:grid;grid-template-columns:repeat(auto-fill,minmax(210px,1fr));gap:10px">';
    items.forEach(function(it){
      var w=it.w,si=_stInfo(w),nm=_name(w);
      html+='<div data-code="'+(w.code||'')+'" style="border:1px solid '+(si===_STC.idle?'#e5e7eb':si[0])+';border-radius:8px;padding:10px;background:#fff">';
      html+='<div style="display:flex;align-items:center;justify-content:space-between;gap:6px">';
      html+='<span style="font-weight:600;font-size:13px">'+(w.code||'')+'</span>';
      html+='<span style="font-size:11px;padding:1px 8px;border-radius:10px;color:#fff;background:'+si[0]+'">'+si[1]+'</span>';
      html+='</div>';
      html+='<div style="margin-top:6px;font-size:13px;color:#374151">'+(nm||'—')+'</div>';
      html+='<div style="margin-top:6px;font-size:11px;color:#6b7280">'+(lab.code||lab.name)+'</div>';
      html+='</div>';
    });
    html+='</div></div>';
  });
  html+='</div>';
  _box.innerHTML=html;
  _qsa('.__mg-back',_box).forEach(function(b){b.addEventListener('click',function(){_st.mentor='';_showState();_exit()})});
  _qsa('.__mg-go',_box).forEach(function(b){b.addEventListener('click',function(){var n=b.getAttribute('data-n'),m2=b.getAttribute('data-m');if(!n)return;location.hash='#/lab/workstations/'+n+'?m='+encodeURIComponent(m2||'')})});
}
/* --- 注入/挂载 --- */
var _loadedOnce=false;
function _isMgPage(){var h=location.hash||'';return h.indexOf('#/lab/workstation')===0&&(h.length===17||h.charAt(17)==='?'||h.charAt(17)==='#')}
function _dropSel(){try{var _sels=_qsa('.__mg-sel');for(var _i=0;_i<_sels.length;_i++){var _p=_sels[_i].parentNode;if(_p)_p.removeChild(_sels[_i])}}catch(e){}}
function _tryInject(){
  if(!_isMgPage()){_dropSel();return false}
  if(_box&&!_box.isConnected){_box=null}
  if(_cardA&&!_cardA.isConnected){_cardA=null}
  if(_cardB&&!_cardB.isConnected){_cardB=null}
  if(_selEl&&!_selEl.isConnected){_selEl=null}
  var row=_qs('div.flex.flex-1.flex-wrap.items-center.gap-2');
  if(!row)return false;
  if(!_qs('.el-select',row))return false;
  var ok=_ensureSel(row);
  if(ok&&!_loadedOnce){_loadedOnce=true;_load()}
  return ok;
}
function _watch(){
  _tryInject();
  try{
    new MutationObserver(function(){_tryInject()}).observe(document.body,{childList:true,subtree:true});
  }catch(e){}
  setInterval(function(){if(!_isMgPage()){_dropSel();return}if(!_qs('.__mg-sel'))_tryInject()},1200);
}
if(document.readyState==='loading'){document.addEventListener('DOMContentLoaded',_watch)}else{_watch()}
window.addEventListener('hashchange',function(){try{
  var m=_urlMentor();
  if(m&&m!==_st.mentor){_st.mentor=m;_showState()
    if(_st.ok){if(_box){_render()}else{_enter()}}else{_load()}}
}catch(e){console.log('[MGV-ERR]',e&&e.message)}});
}catch(_e){console.log('[MGV-ERR]',_e&&_e.message)}})();
;(function(){try{
if(window.__wrn)return;window.__wrn=1;
/* === 工位管理卡片:已释放备注(0908 v2) ===
   仅对「当前空闲」且历史上被释放过(占用历史存在 releasedAt)的卡,
   卡片信息区追加一行「YYYY年M月已释放」 */
function _qsa(s,r){r=r||document;try{return Array.prototype.slice.call(r.querySelectorAll(s))}catch(e){return []}}
function _tok(){try{return window.__mgTok||''}catch(e){return ''}}
function _waitTok(cb,tries){tries=tries||0;if(_tok()){cb();return}if(tries>26){cb();return}setTimeout(function(){_waitTok(cb,tries+1)},300)}
function _fj2(u,at){at=at||0;return new Promise(function(res,rej){var h={'Content-Type':'application/json','X-Requested-With':'XMLHttpRequest'};var tk=_tok();if(tk)h['Authorization']=tk;fetch(u,{headers:h,credentials:'same-origin'}).then(function(r){
  if(r.status===401&&at<6){setTimeout(function(){_fj2(u,at+1).then(res,rej)},600+at*500);return}
  if(!r.ok){rej(new Error('HTTP'+r.status));return}
  r.json().then(function(j){if(j&&j.data!=null){res(j.data)}else{rej(new Error('bad'))}}).catch(rej)
}).catch(rej)})}
function _mon(dt){var s=String(dt||'');var m=s.match(/^(\d{4})-(\d{2})/);if(m)return m[1]+'年'+parseInt(m[2],10)+'月';var d=new Date(s);if(!isNaN(d.getTime()))return d.getFullYear()+'年'+(d.getMonth()+1)+'月';return ''}
var _cache=null; /* {labNanoId,ts,map:{code:txt}} */
var _pending=null;
function _listBox(){
  var box=null;
  _qsa('div.bg-card.flex.min-h-0.flex-1.flex-col.rounded-md').forEach(function(c){if(!box&&(_qsa('.el-pagination',c).length||_qsa('.grid',c).length))box=c});
  return box||null;
}
function _visible(box){try{return !!(box&&(box.offsetWidth||box.offsetHeight||box.getClientRects().length))}catch(e){return false}}
function _cards(){
  var box=_listBox();
  if(!_visible(box))return [];
  return _qsa('div.rounded-lg.border.border-gray-200',box).filter(function(d){
    var t=(d.innerText||'');
    return t.indexOf('占用组')>=0||t.indexOf('空闲工位')>=0||t.indexOf('期间')>=0||t.indexOf('预计释放')>=0||t.indexOf('占领者')>=0;
  });
}
function _codeOf(card){
  var t=(card.innerText||'').trim();
  var m=t.match(/^([A-Za-z]{1,6}\d{1,4}[A-Za-z]?-\d{1,4})/);
  return m?m[1]:'';
}
function _curSets(){
  var all={},idle={};
  _cards().forEach(function(c){
    var cd=_codeOf(c);
    if(!cd)return;
    all[cd]=1;
    if((c.innerText||'').indexOf('空闲工位')>=0)idle[cd]=1;
  });
  return {all:all,idle:idle};
}
function _onList(u,data){
  try{
    var q=String(u||'').split('?')[1]||'';
    var lab='';
    try{lab=new URLSearchParams(q).get('labNanoId')||''}catch(e){var mm=q.match(/labNanoId=([^&]*)/);lab=mm?decodeURIComponent(mm[1]||''):''}
    if(!lab||!data||!data.length)return;
    var arr=(data||[]).filter(function(w){return w&&String(w.type||'').toUpperCase()==='SEAT'&&w.code&&w.nanoId});
    if(!arr.length)return;
    setTimeout(function(){
      try{
        var sets=_curSets();
        /* 只查「当前可视且空闲」的卡 */
        var need=arr.filter(function(w){return sets.all[w.code]&&sets.idle[w.code]});
        if(!need.length)return;
        var now=Date.now();
        if(_cache&&_cache.labNanoId===lab&&now-_cache.ts<2000)return;
        if(_pending&&_pending.lab===lab&&now-_pending.ts<2000)return;
        _pending={lab:lab,ts:now};
        _waitTok(function(){
          var map={};
          var qs=need.map(function(w){
            return _fj2('/api/workstations/'+encodeURIComponent(w.nanoId)+'/occupancy-history').then(function(hs){
              var hs2=hs||[];
              for(var i=0;i<hs2.length;i++){var hh=hs2[i];if(hh&&hh.releasedAt){var mo=_mon(hh.releasedAt);if(mo){map[w.code]=mo+'已释放'}break}}
            }).catch(function(){});
          });
          Promise.all(qs).then(function(){
            if(_pending&&_pending.lab!==lab)return;
            _pending=null;
            _cache={labNanoId:lab,ts:Date.now(),map:map};
            _apply();
          });
        });
      }catch(e){}
    },700);
  }catch(e){}
}
function _apply(){
  if(!_cache)return;
  var map=_cache.map||{};
  var cards=_cards();
  for(var i=0;i<cards.length;i++){
    var c=cards[i];
    _qsa('.__wrn-note',c).forEach(function(x){try{x.remove()}catch(e){}});
    if((c.innerText||'').indexOf('空闲工位')<0)continue; /* 仅空闲卡 */
    var cd=_codeOf(c);
    var txt=map[cd];
    if(!txt)continue;
    var info=null;
    var kids=c.children;
    for(var k=0;k<kids.length;k++){var kd=kids[k];var kt=(kd.innerText||'');if(kt.indexOf('占用组：')>=0||kt.indexOf('空闲工位')>=0||kt.indexOf('期间：')>=0||kt.indexOf('预计释放：')>=0||kt.indexOf('占领者：')>=0){info=kd;break}}
    if(!info)info=c;
    var n=document.createElement('div');
    n.className='__wrn-note';
    n.style.cssText='color:#9ca3af;font-size:12px;line-height:1.5;margin-top:1px';
    n.textContent=txt;
    info.appendChild(n);
  }
}
/* XHR 响应捕获: /api/workstations?labNanoId= */
try{
  var _os=XMLHttpRequest.prototype.send;
  XMLHttpRequest.prototype.send=function(){
    var self=this;
    try{self.addEventListener('load',function(){
      try{
        var u=self.__mgU||'';
        if(u.indexOf('/api/workstations?')>=0&&u.indexOf('labNanoId=')>=0){
          var j=null;
          try{j=JSON.parse(self.responseText||'')}catch(e){}
          if(j&&j.data&&Array.isArray(j.data))_onList(u,j.data);
        }
      }catch(e){}
    });}catch(e){}
    return _os.apply(this,arguments);
  };
}catch(e){}
/* 观察/轮询标注 */
var _deb=null;
try{
  new MutationObserver(function(){if(_deb)return;_deb=setTimeout(function(){_deb=null;try{_apply()}catch(e){}},500)}).observe(document.body,{childList:true,subtree:true});
}catch(e){}
setInterval(function(){try{_apply()}catch(e){}},3000);
}catch(_e){try{console.log('[WRN]',_e&&_e.message)}catch(x){}}})();
