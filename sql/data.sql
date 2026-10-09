-- >>> src: rbac-data.sql (平台种子)
-- ============================================================================
-- RBAC 初始数据 (seed)
-- 前置: 先执行 sql/schema.sql 建表
-- 说明:
--   1. 实体行显式指定 id (BIGINT)，便于关联表用确定的 id 引用
--   2. nano_id 为 21 字符 (与 VARCHAR(21) _as_cs 列匹配)，已预生成
--   3. 密码为 BCrypt 哈希 (spring-security BCryptPasswordEncoder 生成)
--   4. is_deleted=0 (匹配逻辑删除 is_deleted 过滤)、is_active=1 (匹配权限加载过滤)
--   5. 可重复执行: 开头按 id 清理旧种子数据
--
-- 预置账号:
--   admin  / admin123  → 角色 admin  (全部权限 + 全部菜单)
--   user   / user123   → 角色 user   (仅查看权限 + 部分菜单)
-- ============================================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 清理旧种子数据 (可重复执行)
-- ----------------------------
DELETE FROM core_role_permission WHERE role_id IN (1, 2);
DELETE FROM core_role_menu       WHERE role_id IN (1, 2);
DELETE FROM core_user_role       WHERE user_id IN (1, 2);
DELETE FROM core_permission      WHERE id BETWEEN 1 AND 30;
DELETE FROM core_menu            WHERE id BETWEEN 1 AND 10;
DELETE FROM core_role            WHERE id IN (1, 2);
DELETE FROM core_user            WHERE id IN (1, 2);
DELETE FROM core_dept            WHERE id IN (1, 2, 3);
DELETE FROM core_mentor_group    WHERE id IN (1, 2, 3, 4);
-- 审计日志 / 工位占用申请 / 导师组相关种子 (id 在 1-10/1-30 范围外, 单独幂等清理)
DELETE FROM core_role_permission WHERE permission_id IN (32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46);
DELETE FROM core_role_menu       WHERE menu_id IN (11, 12, 13, 14);
DELETE FROM core_permission      WHERE id IN (32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46);
DELETE FROM core_menu            WHERE id IN (11, 12, 13, 14);

-- ----------------------------
-- 部门
-- ----------------------------
INSERT INTO core_dept (id, nano_id, name, code, dept_type, status, parent_id, level, path, sort, is_deleted) VALUES
  (1, 'vDwfwiJ975GNF2MuNIEXk', '总公司', 'ROOT', 'company', 1, NULL, 1, '/1',     1, 0),
  (2, 'cuId9-XVW0eF3Ee8xeJxs', '研发部', 'DEV',  'dept',    1, 1,    2, '/1/2',   2, 0),
  (3, 'b9mKJTRNqt2W4L5jB2bu6', '运营部', 'OPS',  'dept',    1, 1,    2, '/1/3',   3, 0);

-- ----------------------------
-- 导师组 (3层树: 学院 -> 系/所 -> 导师组叶子; 业务模块关联第3层导师组)
-- ----------------------------
INSERT INTO core_mentor_group (id, nano_id, name, code, status, parent_id, lead_id, level, path, sort, is_deleted) VALUES
  (1, 'MgA1xQ8rW2dNbK7vTm0q1', '计算机学院', 'CS',    1, NULL, NULL, 1, '/1',   1, 0),
  (2, 'MgB2xQ8rW2dNbK7vTm0q2', '软件工程系', 'CS-SE', 1, 1,    NULL, 2, '/1/2', 2, 0),
  (3, 'MgC3xQ8rW2dNbK7vTm0q3', '王教授课题组', 'CS-SE-W', 1, 2, 1, 3, '/1/2/3', 3, 0),
  (4, 'MgD4xQ8rW2dNbK7vTm0q4', '李教授课题组', 'CS-SE-L', 1, 2, NULL, 3, '/1/2/4', 4, 0);

-- ----------------------------
-- 用户 (password 为 BCrypt 密文)
--   admin123 -> $2a$10$A0gdORuTwey4POo7lvo0r.eYvyhhXrOubyzoyDnziXK92FgFoQ12C
--   user123  -> $2a$10$d.aPVVprUBlmDLm8nZpj3eBd2L9NeFbH1oAEhg1r/LfUxJeOINKmu
-- ----------------------------
INSERT INTO core_user (id, nano_id, username, password, name, email, mobile, dept_id, mentor_group_id, is_active, is_superuser, user_type, user_status, gender, sort, is_deleted) VALUES
  (1, 'fdlD4zoZFT1kTyznp_Rgn', 'admin', '$2a$10$A0gdORuTwey4POo7lvo0r.eYvyhhXrOubyzoyDnziXK92FgFoQ12C', '超级管理员', 'admin@lab.com', '13800000001', 1, 3, 1, 1, 0, 1, 0, 1, 0),
  (2, 'hT38AU7iXq_MrpDhz-v_n', 'user',  '$2a$10$d.aPVVprUBlmDLm8nZpj3eBd2L9NeFbH1oAEhg1r/LfUxJeOINKmu', '普通用户',  'user@lab.com',  '13800000002', 2, 4, 1, 0, 0, 1, 0, 2, 0);

-- ----------------------------
-- 角色
-- ----------------------------
INSERT INTO core_role (id, nano_id, name, code, role_type, status, priority, description, remark, sort, is_deleted) VALUES
  (1, '6z-3eOu7gM7Tai9hYayIq', '系统管理员', 'admin', 0, 1, 100, '系统超级管理员', '拥有全部权限', 1, 0),
  (2, '3qAy8hlnQJHcCg6nJ6UGc', '普通用户',   'user',  0, 1, 10,  '普通用户',     '仅查看权限',   2, 0);

-- ----------------------------
-- 菜单 (注意 `order` 为 MySQL 保留字, 需反引号)
-- ----------------------------
INSERT INTO core_menu (id, nano_id, application_id, is_system, parent_id, name, title, path, type, component, icon, `order`, sort, is_deleted) VALUES
  (5, 'fKFynanoq1OzzW5DKlfDD', NULL, 1, NULL, 'dashboard',   '仪表盘',   '/dashboard',     'menu',     'Dashboard', 'lucide:layout-dashboard', 0,  0,  0),
  (1, 'jkuR8YHVNnu6iyH7amtde', NULL, 1, NULL, 'system',     '系统管理', '/system',        'catalog',  NULL, 'lucide:settings',   10, 10, 0),
  (2, 'WjZDsq1lXOQb0rqRIMl1U', NULL, 1, 1,    'system_user','用户管理', '/system/user',   'menu',     'system/user/index', 'lucide:users',      1,  11, 0),
  (3, 'LCs4iPA1PeQhGuunDJS9N', NULL, 1, 1,    'system_role','角色管理', '/system/role',   'menu',     'system/role/index', 'lucide:shield',      2,  12, 0),
  (4, 'nPfIVjHul5v6KUSIWZ9ul', NULL, 1, 1,    'system_menu','菜单管理', '/system/menu',   'menu',     'system/menu/index', 'lucide:menu',      3,  13, 0),
  (6, 'f2XvXhur288a2H7g3zjMa', NULL, 1, 7, 'workstation','工位管理','/lab/workstation','menu','/lab/workstation/index','lucide:monitor',2,2,0),
  (7, 'wFavRiokXc9Q8hI-HtwJv', NULL, 1, NULL, 'lab',        '实验室管理','/lab',           'catalog',  NULL,                    'lucide:flask-conical', 15, 15, 0),
  (8, 'NID00xQ8rW2dNbK7vTmL3', NULL, 1, 1, 'system_dept','部门管理','/system/dept','menu','system/dept/index','lucide:building-2',4,14,0),
  (9, 'NID01xQ8rW2dNbK7vTmL3', NULL, 1, 1, 'system_permission','权限管理','/system/permission','menu','system/permission/index','lucide:key-round',5,15,0),
  (10, 'NID13xQ8rW2dNbK7vTmL3', NULL, 1, 7, 'lab_list','实验室列表','/lab/list','menu','lab/list/index','lucide:layout-grid',1,1,0),
  (11, 'NID14xQ8rW2dNbK7vTmL3', NULL, 1, 1, 'system_audit','操作审计','/system/audit','menu','system/audit/index','lucide:scroll-text',6,16,0),
  (12, 'NID22xQ8rW2dNbK7vTmL3', NULL, 1, 7, 'lab_rental','工位申请','/lab/rental','menu','lab/rental/index','lucide:calendar-check',3,3,0),
  (13, 'NID26xQ8rW2dNbK7vTmL3', NULL, 1, 1, 'system_mentor_group','导师组管理','/system/mentor-group','menu','system/mentor-group/index','lucide:users-round',7,17,0),
  (14, 'NID32xQ8rW2dNbK7vTmL3', NULL, 1, 7, 'lab_usage','工位用时统计','/lab/usage','menu','lab/usage/index','lucide:chart-column',4,4,0);

-- ----------------------------
-- 权限 (permission_type=1 API权限; is_active=1; http_method: 0=GET,1=POST,2=PUT,3=DELETE)
-- ----------------------------
INSERT INTO core_permission (id, nano_id, menu_id, name, code, permission_type, api_path, http_method, description, is_active, sort, is_deleted) VALUES
  (1,  '9ffv2Hs_ZYpLM9GQre3yx', 2, '用户列表', 'system:user:list',   1, 'GET /api/users',                 0, '查询用户列表',   1, 1, 0),
  (2,  'DS8bynUq8HIAChAddgS8f', 2, '用户详情', 'system:user:view',   1, 'GET /api/users/{nanoId}',        0, '查询单个用户',   1, 2, 0),
  (3,  'xORHZV0C6rO_oFAEHrk-q', 2, '用户新增', 'system:user:create', 1, 'POST /api/users',                1, '新增用户',       1, 3, 0),
  (4,  'OnSYgj5sfbk035Ubh1zr_', 2, '用户修改', 'system:user:update', 1, 'PUT /api/users/{nanoId}',        2, '修改用户',       1, 4, 0),
  (5,  'eQyLczcauXShJZmIjD1hN', 2, '用户删除', 'system:user:delete', 1, 'DELETE /api/users/{nanoId}',     3, '删除用户',       1, 5, 0),
  (6,  '8RGJtRbwCu9UBI5P_JlQE', 3, '角色列表', 'system:role:list',   1, 'GET /api/roles',                 0, '查询角色列表',   1, 6, 0),
  (7,  'QDtDPbWjs_47sx7mBKzXW', 3, '角色详情', 'system:role:view',   1, 'GET /api/roles/{nanoId}',        0, '查询单个角色',   1, 7, 0),
  (8,  '6WxBE1oV9pUiI1u2c2vME', 3, '角色分配', 'system:role:assign', 1, 'PUT /api/roles/{nanoId}/permissions', 2, '为角色分配权限/菜单', 1, 8, 0),
  (9,  'Mf_n8ehJkvFF9F2ddDcND', 3, '角色新增', 'system:role:create', 1, 'POST /api/roles',                1, '新增角色',       1, 9, 0),
  (10, 'wZSVqqxVnHCv_QDqU_Zy3', 3, '角色修改', 'system:role:update', 1, 'PUT /api/roles/{nanoId}',        2, '修改角色',       1, 10, 0),
  (11, 'vK7m2Rq1sNp0YzL9aHb3c', 6, '工位查看', 'workstation:view',    1, 'GET /api/labs, /api/workstations, /api/equipment', 0, '查看实验室/工位/设备', 1, 11, 0),
  (13, 'hL3zN6qM9vR8sT2dW5pKj', 6, '工位释放', 'workstation:release', 1, 'PUT /api/workstations/{nanoId}/release', 2, '释放占用工位',     1, 13, 0),
  (14, 'gY4fC8bD2nX7wJ1tQr6mK', 6, '工位管理', 'workstation:manage',  1, 'POST /api/labs, /api/workstations, /api/equipment', 1, '管理实验室/工位/设备', 1, 14, 0),
  (15, '7KqLm2nZ9xP4rW8sVt3Ba', 4, '菜单列表', 'system:menu:list',   1, 'GET /api/menus',             0, '查询菜单树',     1, 15, 0),
  (16, '8HrLn3oA0yQ5sX9tWu4Cb', 4, '菜单详情', 'system:menu:view',   1, 'GET /api/menus/{nanoId}',    0, '查询单个菜单',   1, 16, 0),
  (17, '9IsMo4pB1zR6tY0uXv5Dc', 4, '菜单新增', 'system:menu:create', 1, 'POST /api/menus',            1, '新增菜单',       1, 17, 0),
  (18, '0JtNp5qC2aS7uZ1vYw6Ed', 4, '菜单修改', 'system:menu:update', 1, 'PUT /api/menus/{nanoId}',    2, '修改菜单',       1, 18, 0),
  (19, '1KuOq6rD3bT8vA2wZx7Fe', 4, '菜单删除', 'system:menu:delete', 1, 'DELETE /api/menus/{nanoId}', 3, '删除菜单',       1, 19, 0),
  (20, 'NID02xQ8rW2dNbK7vTmL3', 2, '用户角色分配', 'system:user:assign', 1, 'PUT /api/users/{nanoId}/roles', 2, '为用户分配角色', 1, 20, 0),
  (21, 'NID03xQ8rW2dNbK7vTmL3', 8, '部门列表', 'system:dept:list',   1, 'GET /api/depts',             0, '查询部门树',   1, 21, 0),
  (22, 'NID04xQ8rW2dNbK7vTmL3', 8, '部门详情', 'system:dept:view',   1, 'GET /api/depts/{nanoId}',    0, '查询单个部门', 1, 22, 0),
  (23, 'NID05xQ8rW2dNbK7vTmL3', 8, '部门新增', 'system:dept:create', 1, 'POST /api/depts',            1, '新增部门',     1, 23, 0),
  (24, 'NID06xQ8rW2dNbK7vTmL3', 8, '部门修改', 'system:dept:update', 1, 'PUT /api/depts/{nanoId}',    2, '修改部门',     1, 24, 0),
  (25, 'NID07xQ8rW2dNbK7vTmL3', 8, '部门删除', 'system:dept:delete', 1, 'DELETE /api/depts/{nanoId}', 3, '删除部门',     1, 25, 0),
  (26, 'NID08xQ8rW2dNbK7vTmL3', 9, '权限列表', 'system:permission:list',   1, 'GET /api/permissions',          0, '查询权限列表',   1, 26, 0),
  (27, 'NID09xQ8rW2dNbK7vTmL3', 9, '权限详情', 'system:permission:view',   1, 'GET /api/permissions/{nanoId}', 0, '查询单个权限',   1, 27, 0),
  (28, 'NID10xQ8rW2dNbK7vTmL3', 9, '权限新增', 'system:permission:create', 1, 'POST /api/permissions',          1, '新增权限',       1, 28, 0),
  (29, 'NID11xQ8rW2dNbK7vTmL3', 9, '权限修改', 'system:permission:update', 1, 'PUT /api/permissions/{nanoId}',  2, '修改权限',       1, 29, 0),
  (30, 'NID12xQ8rW2dNbK7vTmL3', 9, '权限删除', 'system:permission:delete', 1, 'DELETE /api/permissions/{nanoId}', 3, '删除权限',    1, 30, 0),
  (32, 'NID15xQ8rW2dNbK7vTmL3', 11, '审计列表', 'system:audit:list', 1, 'GET /api/audit-logs',          0, '查询操作审计日志', 1, 32, 0),
  (33, 'NID16xQ8rW2dNbK7vTmL3', 11, '审计详情', 'system:audit:view', 1, 'GET /api/audit-logs/{nanoId}', 0, '查询单条审计日志', 1, 33, 0),
  (36, 'NID19xQ8rW2dNbK7vTmL3', 2,  '用户导入', 'system:user:import', 1, 'POST /api/users/import', 1, '批量导入用户', 1, 36, 0),
  (37, 'NID20xQ8rW2dNbK7vTmL3', 6,  '工位申请', 'workstation:rent:apply',    1, 'POST /api/workstations/{nanoId}/rent-request', 1, '导师组申请工位', 1, 37, 0),
  (38, 'NID21xQ8rW2dNbK7vTmL3', 6,  '工位审批', 'workstation:rent:approve',  1, 'PUT /api/workstation-rental-requests/{nanoId}/approve', 2, '审批工位申请', 1, 38, 0),
  (39, 'NID23xQ8rW2dNbK7vTmL3', 6,  '工位学生占领', 'workstation:seat', 1, 'POST /api/workstations/{nanoId}/seat', 1, '学生占领预占工位', 1, 39, 0),
  (40, 'NID24xQ8rW2dNbK7vTmL3', 6,  '工位用时统计', 'workstation:usage:view',   1, 'GET /api/workstations/mentor-group-student-usage', 0, '工位用时统计查询',       1, 40, 0),
  (41, 'NID25xQ8rW2dNbK7vTmL3', 6,  '工位占用历史', 'workstation:history:view', 1, 'GET /api/workstations/{nanoId}/occupancy-history', 0, '查询工位占用历史',       1, 41, 0),
  (42, 'NID27xQ8rW2dNbK7vTmL3', 13, '导师组列表', 'system:mentor-group:list',   1, 'GET /api/mentor-groups',            0, '查询导师组树',   1, 42, 0),
  (43, 'NID28xQ8rW2dNbK7vTmL3', 13, '导师组详情', 'system:mentor-group:view',   1, 'GET /api/mentor-groups/{nanoId}',   0, '查询单个导师组', 1, 43, 0),
  (44, 'NID29xQ8rW2dNbK7vTmL3', 13, '导师组新增', 'system:mentor-group:create', 1, 'POST /api/mentor-groups',           1, '新增导师组',     1, 44, 0),
  (45, 'NID30xQ8rW2dNbK7vTmL3', 13, '导师组修改', 'system:mentor-group:update', 1, 'PUT /api/mentor-groups/{nanoId}',   2, '修改导师组',     1, 45, 0),
  (46, 'NID31xQ8rW2dNbK7vTmL3', 13, '导师组删除', 'system:mentor-group:delete', 1, 'DELETE /api/mentor-groups/{nanoId}', 3, '删除导师组',    1, 46, 0);

-- ----------------------------
-- 用户-角色
-- ----------------------------
INSERT INTO core_user_role (user_id, role_id, is_deleted) VALUES
  (1, 1, 0),  -- admin -> 系统管理员
  (2, 2, 0);  -- user  -> 普通用户

-- ----------------------------
-- 角色-菜单
--   admin 角色: 全部菜单 (1,2,3,4,5,6,7,8,9,10,11)
--   user  角色: 仪表盘 + 系统管理 + 用户管理 + 角色管理 (5,1,2,3)
-- ----------------------------
INSERT INTO core_role_menu (role_id, menu_id) VALUES
  (1, 5), (1, 1), (1, 2), (1, 3), (1, 4), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12), (1, 13), (1, 14),
  (2, 5), (2, 1), (2, 2), (2, 3), (2, 6), (2, 7), (2, 10);

-- ----------------------------
-- 角色-权限
--   admin 角色: 全部权限 (1-30, 含 menu:*/dept:*/permission:*; 32-33 审计)
--   user  角色: 仅查看 (1,2 用户列表/详情; 6,7 角色列表/详情)
-- ----------------------------
INSERT INTO core_role_permission (role_id, permission_id) VALUES
  (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10),
  (1, 11), (1, 13), (1, 14),
  (1, 15), (1, 16), (1, 17), (1, 18), (1, 19),
  (1, 20), (1, 21), (1, 22), (1, 23), (1, 24), (1, 25), (1, 26), (1, 27), (1, 28), (1, 29), (1, 30),
  (1, 32), (1, 33),
  (1, 36), (1, 37), (1, 38), (1, 39), (1, 40), (1, 41),
  (1, 42), (1, 43), (1, 44), (1, 45), (1, 46),
  (2, 1), (2, 2), (2, 6), (2, 7),
  (2, 11), (2, 13);

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- 校验 (可选, 执行后查看)
-- ----------------------------
-- SELECT username, is_active, is_superuser FROM core_user;
-- SELECT code, name FROM core_role;
-- SELECT r.code AS role, p.code AS permission FROM core_role_permission rp
--   JOIN core_role r ON r.id=rp.role_id JOIN core_permission p ON p.id=rp.permission_id ORDER BY r.code, p.code;

-- >>> src: workstation-data.sql (业务种子)
-- ============================================================================
-- 业务初始数据 (seed): 实验室 / 工位 / 设备
-- 前置: sql/workstation-tables.sql (业务表 DDL) + sql/rbac-data.sql (平台种子, 提供 user/dept)
-- 说明: 仅供快速演示 occupy/release；可重复执行（开头按 id 清理）。
-- ============================================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM equipment   WHERE id IN (1, 2);
DELETE FROM workstation WHERE id IN (1, 2);
DELETE FROM lab         WHERE id = 1;

-- ----------------------------
-- 实验室 (manager_id=1 即 admin, mentor_group_id=3 即王教授课题组, dept_id=2 即研发部[二级部门], 引用自 rbac-data.sql)
-- ----------------------------
INSERT INTO lab (id, nano_id, name, code, location, capacity, status, manager_id, mentor_group_id, dept_id, description, sort, is_deleted) VALUES
  (1, 'AwI2vQxQnev0733pWEudv', '软件工程实验室', 'LAB-001', '理科楼A301', 30, 'OPEN', 1, 3, 2, '软件工程教学实验室', 1, 0);

-- ----------------------------
-- 工位 (均空闲, 供 occupy 演示)
-- ----------------------------
INSERT INTO workstation (id, nano_id, code, lab_id, status, current_user_id, occupied_at, description, sort, is_deleted) VALUES
  (1, '8UMkqGYaMtT31s74uVGGL', 'WS-001', 1, 'IDLE', NULL, NULL, '靠窗工位', 1, 0),
  (2, 'dAdnrHyqp8PXGKAhamguN', 'WS-002', 1, 'IDLE', NULL, NULL, '角落工位', 2, 0);

-- ----------------------------
-- 设备
-- ----------------------------
INSERT INTO equipment (id, nano_id, name, code, lab_id, category, model, serial_number, status, custodian_id, purchased_at, description, sort, is_deleted) VALUES
  (1, 'bdsiVyZFd7_qarIBnGGxC', '高性能工作站', 'EQ-001', 1, '计算设备', 'Dell Precision', 'SN2024001', 'NORMAL', 1, '2024-03-01', '实验室主力计算设备', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
