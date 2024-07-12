INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
--
INSERT INTO usuarios (enabled, password, username, nombre, apellido, email) VALUES (true, '$2a$10$eGMNjg5VFqw9xqYwgOBzqeOHxtLGgsicXGT6mx1S5H80DGXZKYfSq', 'scalde4', 'steven', 'calderon', 'scalde@gmail.com');
INSERT INTO usuarios (enabled, password, username, nombre, apellido, email) VALUES (true, '$2a$10$318nOb4ldEwRohtVN0anVuMA3C9y3Eujz0MytwRl4k.BAn4BUV.AO', 'scl3452', 'daniel', 'calderon', 'dcalde@gmail.com');
--
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);