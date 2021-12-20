-- создание таблицы ролей
CREATE TABLE if not exists loader.roles
(
    id int NOT NULL CONSTRAINT roles_pk PRIMARY KEY,
    name varchar NOT NULL,
    authorities loader.authority[] NOT NULL
);

-- вставка готовых дефолтных ролей
INSERT INTO loader.roles (id, name, authorities) VALUES (1, 'Owner', ARRAY['manage_users', 'manage_settings', 'view_some_pages', 'view_all_pages']::loader.authority[]);
INSERT INTO loader.roles (id, name, authorities) VALUES (2, 'Admin', ARRAY['manage_settings', 'view_some_pages', 'view_all_pages']::loader.authority[]);
INSERT INTO loader.roles (id, name, authorities) VALUES (3, 'Advanced user', ARRAY['view_some_pages', 'view_all_pages']::loader.authority[]);
INSERT INTO loader.roles (id, name, authorities) VALUES (4, 'Default user', ARRAY['view_some_pages']::loader.authority[]);

-- auto-generated definition
CREATE TABLE if not exists  loader.users
(
    id serial CONSTRAINT users_pk PRIMARY KEY,
    role_id int REFERENCES loader.roles(id) NOT NULL,
    name varchar(50),
    password varchar,
    is_active boolean DEFAULT TRUE NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp,
    deleted_at timestamp
);

-- вставка дефолтных пользователей
INSERT INTO loader.users (role_id, name, password, is_active, created_at) VALUES (1, 'owner', crypt('1234', gen_salt('bf', 8)), true, now());
INSERT INTO loader.users (role_id, name, password, is_active, created_at) VALUES (2, 'admin', crypt('admin', gen_salt('bf', 8)), true, now());
INSERT INTO loader.users (role_id, name, password, is_active, created_at) VALUES (3, 'advanced_user', crypt('advanced_user', gen_salt('bf', 8)), true, now());
INSERT INTO loader.users (role_id, name, password, is_active, created_at) VALUES (4, 'default_user', crypt('default_user', gen_salt('bf', 8)), true, now());