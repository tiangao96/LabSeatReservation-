# -*- coding: utf-8 -*-
# 幂等:把 /_mentor_patch.js 的 script 标签插到 /_i18n_patch.js 之后;已存在则只校正版本号
import io, re, sys
P = '/usr/share/nginx/lab-reservation/index.html'
VER = '20260913c'
TAG = '<script src="/_mentor_patch.js?v=%s"></script>' % VER
s = io.open(P, encoding='utf-8').read()
chg = False
if '_mentor_patch.js' in s:
    old = re.search(r'<script src="/_mentor_patch\.js[^"]*"></script>', s)
    if old and old.group(0) != TAG:
        s = s[:old.start()] + TAG + s[old.end():]
        print('VERSION_BUMPED %s -> %s' % (old.group(0), TAG))
        chg = True
    else:
        print('ALREADY_PATCHED')
else:
    m = re.search(r'<script src="/_i18n_patch\.js[^"]*"></script>', s)
    if m:
        s = s[:m.end()] + TAG + s[m.end():]
        print('PATCHED_AFTER_I18N')
        chg = True
    elif '<head>' in s:
        s = s.replace('<head>', '<head>' + TAG, 1)
        print('PATCHED_AFTER_HEAD')
        chg = True
    else:
        print('ANCHOR_NOT_FOUND')
        sys.exit(3)
if chg:
    io.open(P, 'w', encoding='utf-8', newline='').write(s)
tail = io.open(P, encoding='utf-8').read()
print('has_tag=%s' % ('_mentor_patch.js' in tail))
print('tag_now=%s' % (re.search(r'<script src="/_mentor_patch\.js[^"]*"></script>', tail) or '').group(0)
      if re.search(r'<script src="/_mentor_patch\.js[^"]*"></script>', tail) else 'tag_now=NONE')
