-- создание схемы
CREATE SCHEMA IF NOT EXISTS loader;

-- подключение крипто EXTENSION
create extension if not exists pgcrypto;