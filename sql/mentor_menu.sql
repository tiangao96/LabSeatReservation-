-- 导师查询页:注册侧边栏菜单 + 按现有「实验室管理」授权面自动授权
-- 幂等:重复执行不会产生第二条
SELECT '--- parent(lab catalog) ---' AS step;
SET @pid := (SELECT id FROM core_menu WHERE nano_id = 'wFavRiokXc9Q8hI-HtwJv' AND (is_deleted IS NULL OR is_deleted = 0) LIMIT 1);
SELECT @pid AS parent_id;

SELECT '--- insert menu ---' AS step;
INSERT INTO core_menu
  (parent_id, name, title, path, type, component, icon, `order`, is_system, hide_in_menu, nano_id, is_deleted)
SELECT @pid, 'lab_mentor', '导师查询', '/lab/mentor', 'menu', 'IFrameView', 'lucide:user-search', 5, 1, 0,
       'MPmentorQuery00000001', 0
FROM DUAL
WHERE @pid IS NOT NULL
  AND (SELECT COUNT(*) FROM core_menu WHERE name = 'lab_mentor' AND (is_deleted IS NULL OR is_deleted = 0)) = 0;

SET @mid := (SELECT id FROM core_menu WHERE name = 'lab_mentor' AND (is_deleted IS NULL OR is_deleted = 0) ORDER BY id DESC LIMIT 1);
SELECT @mid AS menu_id;

SELECT '--- grant to roles holding lab catalog ---' AS step;
INSERT IGNORE INTO core_role_menu (role_id, menu_id)
SELECT DISTINCT rm.role_id, @mid
FROM core_role_menu rm
WHERE rm.menu_id = @pid AND @mid IS NOT NULL;

SELECT '--- verify menu ---' AS step;
SELECT id, nano_id, parent_id, name, title, path, type, component, icon, `order`, is_system, iframe_src
FROM core_menu WHERE name = 'lab_mentor';

SELECT '--- verify grants ---' AS step;
SELECT COUNT(*) AS granted_roles FROM core_role_menu WHERE menu_id = @mid;
SELECT rm.role_id, r.code, r.name FROM core_role_menu rm JOIN core_role r ON r.id = rm.role_id WHERE rm.menu_id = @mid;
