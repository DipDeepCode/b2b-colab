CREATE TABLE IF NOT EXISTS tariffs (
                         id SERIAL PRIMARY KEY,
                         name_tariff VARCHAR(50) NOT NULL,
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

CREATE TABLE IF NOT EXISTS subscriptions (
    customer_username VARCHAR(255) NOT NULL REFERENCES customers(username) ,
    current_plan VARCHAR(255) NOT NULL REFERENCES tariffs(name_tariff),
    subscription_start_date DATE NOT NULL,
    subscription_end_date DATE NOT NULL,
    is_active BOOLEAN NOT NULL
);
