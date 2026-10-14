#!/usr/bin/env node
// Generate from delivered v1.4; intermediate CI builds are not releases.
const fs = require("node:fs");
const cp = require("node:child_process");
const base = "056c91240ab8e870b66b7c71d1ac3c4bc46ce262";

function endParen(s,start){let depth=0,q=false,esc=false;for(let i=start;i<s.length;i++){const c=s[i];if(q){if(esc)esc=false;else if(c==="\\")esc=true;else if(c==='"')q=false;}else if(c==='"')q=true;else if(c==="(")depth++;else if(c===")"&&!--depth)return i;}throw Error("unbalanced");}
function split(s){let out=[],start=0,dep=0,q=false,esc=false;for(let i=0;i<s.length;i++){let c=s[i];if(q){if(esc)esc=false;else if(c==="\\")esc=true;else if(c==='"')q=false;}else if(c==='"')q=true;else if("([{".includes(c))dep++;else if(")]}".includes(c))dep--;else if(c===","&&!dep){out.push(s.slice(start,i).trim());start=i+1;}}if(s.slice(start).trim())out.push(s.slice(start).trim());return out;}
function str(s){if(!/^"(?:[^"\\]|\\.)*"$/s.test(s))return null;return JSON.parse(s.replace(/\\\$/g,"$"));}
function refs(files){const out={};for(const f of Object.values(files)){let s=f.now;if(!/object Batch\d+References/.test(s))continue;const obj=s.match(/object (Batch\d+References)/)[1],v={};
for(const m of s.matchAll(/(?:const )?val (\w+)\s*=\s*("(?:[^"\\]|\\.)*")/g))v[m[1]]=str(m[2]);
for(const m of s.matchAll(/val (\w+)\s*=\s*listOf\(/g)){const p=s.indexOf("(",m.index),e=endParen(s,p);v[m[1]]=split(s.slice(p+1,e)).map(x=>str(x)??v[x]);}
function val(x){x=x.trim();if(x.startsWith("listOf(")){const p=x.indexOf("("),e=endParen(x,p);return split(x.slice(p+1,e)).map(y=>str(y)??v[y]);}return v[x];}
if(obj==="Batch01References"){out[obj+"|1"]=v.masonry;out[obj+"|2"]=v.plaster;out[obj+"|3"]=v.plaster;}
else if(obj==="Batch02References"){out[obj+"|4"]=[v.project,v.sheet,v.cement];out[obj+"|5"]=[v.project,v.wool];out[obj+"|6"]=[v.project,v.screed];}
else if(obj==="Batch03References")out[obj+"|7"]=[v.project,v.ppr,v.pvc,v.frame,v.hammer];
else for(const m of s.matchAll(/(\d+)\s*->\s*(\w+)/g))out[obj+"|"+m[1]]=val(m[2]);}return out;}
function records(files,version,ref){const out={},excluded=new Set(["categoryIds","relatedStageIds","relatedMaterialIds"]);
for(const f of Object.values(files)){const s=f[version];if(!s)continue;
for(const m of s.matchAll(/\b(ExecutionStage|MaterialCard|TroubleshootingDefect)\s*\(/g)){const p=s.indexOf("(",m.index),e=endParen(s,p),args={};for(const x of split(s.slice(p+1,e))){const n=x.match(/^(\w+)\s*=\s*(.*)$/s);if(n)args[n[1]]=n[2];}const id=str(args.id??"");if(!id)continue;const kind={ExecutionStage:"stage",MaterialCard:"material",TroubleshootingDefect:"defect"}[m[1]];
for(const [field,x]of Object.entries(args)){if(excluded.has(field))continue;let a=null;if(x.startsWith("listOf(")){const z=x.indexOf("("),en=endParen(x,z);a=split(x.slice(z+1,en)).map(str);if(a.some(y=>y===null))throw Error("unparsed "+id+" "+field);}else{const call=x.match(/^(Batch\d+References)\.forCategory\((\d+)\)$/);if(call){a=ref[call[1]+"|"+call[2]];if(!a)throw Error("missing ref "+x);}}if(a)out[kind+"|"+id+"|"+field]=a;}}}return out;}

const paths=cp.execFileSync("git",["ls-tree","-r","--name-only","HEAD","app/src/main/java/com/example/data"],{encoding:"utf8"}).trim().split("\n").filter(p=>/\/(FinishingStages|FinishingMaterials|Finishing.*Defects|Batch\d+References|TechnicalIllustrations).*\.kt$/.test(p));
const files={};
for(const path of paths){files[path]={now:fs.readFileSync(path,"utf8"),old:null};if(!path.includes("/Batch")) files[path].old=cp.execFileSync("git",["show",base+":"+path],{encoding:"utf8"});}
const old=records(files,"old",{}),updated=records(files,"now",refs(files));
const merged=new Set(["mat_corner_bead_galvanized","mat_ready_mix_plaster","mat_tile_leveling_clips_and_wedges","mat_concealed_toilet_cistern_flushing_system"]);
const groups=Object.keys(old).filter(k=>updated[k]&&JSON.stringify(old[k])!==JSON.stringify(updated[k])).sort().map(scope=>({scope,old:old[scope],new:merged.has(scope.split("|")[1])?[]:updated[scope]}));
fs.writeFileSync("app/src/main/assets/content_review_edit_migration.json",JSON.stringify(groups)+"\n");
console.log("Generated",groups.length,"list scopes");
