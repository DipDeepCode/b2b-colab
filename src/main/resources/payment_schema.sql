CREATE TABLE IF NOT EXISTS tariffs (
                         id SERIAL PRIMARY KEY,
                         name_tariff VARCHAR(50) NOT NULL UNIQUE,
                         telegram BOOLEAN NOT NULL,
                         access_to_lc BOOLEAN NOT NULL,
                         brand_catalog BOOLEAN NOT NULL,
                         telegram_tags BOOLEAN NOT NULL,
                         telegram_likes BOOLEAN NOT NULL,
                         modern_collab BOOLEAN NOT NULL,
                         selection_brands BOOLEAN NOT NULL,
                         message BOOLEAN NOT NULL,
                         price INT NOT NULL,
                         period VARCHAR(255) NOT NULL
);

CREATE TABLE  subscriptions (
    customer_email VARCHAR(255) NOT NULL REFERENCES customers(email) NOT NULL PRIMARY KEY,
    current_plan VARCHAR(255) NOT NULL REFERENCES tariffs(name_tariff),
    subscription_start_date DATE NOT NULL,
    subscription_end_date DATE NOT NULL,
    is_active BOOLEAN NOT NULL
);

INSERT INTO subscriptions (customer_email, current_plan, subscription_start_date, subscription_end_date, is_active)
VALUES ('qwe@qwe20.qwe', 'Lite Match', '2021-01-01', '2022-01-01', true);


INSERT INTO tariffs (name_tariff, telegram, access_to_lc, brand_catalog, telegram_tags, telegram_likes,
                     modern_collab, selection_brands, message, price, period) VALUES (
                                                                                         'Lite Match', TRUE, TRUE, TRUE, TRUE, FALSE, FALSE, FALSE, FALSE, 12000, '1 год');

INSERT INTO tariffs (name_tariff, telegram, access_to_lc, brand_catalog, telegram_tags, telegram_likes,
                     modern_collab, selection_brands, message, price, period) VALUES (
                                                                                         'Comfort Match', TRUE, TRUE, TRUE, TRUE, TRUE, FALSE, FALSE, TRUE, 24000, '1 год');

INSERT INTO tariffs (name_tariff, telegram, access_to_lc, brand_catalog, telegram_tags, telegram_likes,
                     modern_collab, selection_brands, message, price, period) VALUES (
                                                                                         'Business Match', TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, 60000, '1 год');