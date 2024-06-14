CREATE TABLE customers
(
    phone_number VARCHAR(50)  NOT NULL PRIMARY KEY,
    email        VARCHAR(500) NOT NULL UNIQUE,
    password     VARCHAR(500) NOT NULL,
    enabled      BOOLEAN      NOT NULL
);

COMMENT ON TABLE customers IS 'Таблица пользователей';
COMMENT ON COLUMN customers.phone_number IS 'Номер телефона пользователя';
COMMENT ON COLUMN customers.email IS 'Почта пользователя';
COMMENT ON COLUMN customers.password IS 'Пароль пользователя';
COMMENT ON COLUMN customers.enabled IS 'Пользователь активирован';

CREATE TABLE authority
(
    phone_number VARCHAR(50) NOT NULL,
    role         VARCHAR(50) NOT NULL
);

COMMENT ON TABLE authority IS 'Таблица ролей пользователя';
COMMENT ON COLUMN authority.phone_number IS 'Внешний ключ на таблицу customers';
COMMENT ON COLUMN authority.role IS 'Роль пользователя';
