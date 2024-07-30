-- DROP DATABASE skel;
-- CREATE DATABASE skel OWNER scott;
-- \c skel

CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO privilege (name) VALUES ('READ_PRIVILEGE'), ('WRITE_PRIVILEGE');
INSERT INTO role (name) VALUES ('USER'), ('ADMIN');

--INSERT INTO roles_privileges (role_id, privilege_id) VALUES(1, 1);
WITH pid AS (SELECT id AS pid FROM privilege WHERE name = 'READ_PRIVILEGE'), rid AS (SELECT id AS rid FROM role WHERE name = 'USER') INSERT INTO roles_privileges (role_id, privilege_id) SELECT pid, rid FROM pid, rid;
--INSERT INTO roles_privileges (role_id, privilege_id) VALUES(2, 1), (2, 2);
WITH pid AS (SELECT id AS pid FROM privilege WHERE name = 'READ_PRIVILEGE' OR name = 'WRITE_PRIVILEGE'), rid AS (SELECT id AS rid FROM role WHERE name = 'ADMIN') INSERT INTO roles_privileges (role_id, privilege_id) SELECT pid, rid FROM pid, rid;

INSERT INTO users (email, password, first_name, last_name, enabled, locked, mobile, created_at, updated_at) VALUES ('u1@dom.com', 'password', 'First', 'User', true, false, '1234567890', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
WITH salt AS (SELECT gen_salt('bf') AS salt) UPDATE users SET password = crypt('p1', (TABLE salt)) WHERE email = 'u1@dom.com';
--INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
WITH usid AS (SELECT id AS usid FROM users WHERE email = 'u1@dom.com'), rid AS (SELECT id AS rid FROM role WHERE name = 'USER') INSERT INTO users_roles (user_id, role_id) SELECT usid, rid FROM usid, rid;

INSERT INTO users (email, password, first_name, last_name, enabled, locked, mobile, created_at, updated_at) VALUES ('a1@dom.com', 'password', 'First', 'Admin', true, false, '2345678901', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
WITH salt AS (SELECT gen_salt('bf') AS salt) UPDATE users SET password = crypt('p1', (TABLE salt)) WHERE email = 'a1@dom.com';
--INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
WITH usid AS (SELECT id AS usid FROM users WHERE email = 'a1@dom.com'), rid AS (SELECT id AS rid FROM role WHERE name = 'ADMIN') INSERT INTO users_roles (user_id, role_id) SELECT usid, rid FROM usid, rid;
