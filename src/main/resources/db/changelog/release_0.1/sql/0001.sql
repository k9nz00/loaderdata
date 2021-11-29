-- создание типов прав
CREATE TYPE loader.authority AS enum (
    'manage_users',
    'manage_settings',
    'view_some_pages',
    'view_all_pages'
    );

