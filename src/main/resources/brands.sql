CREATE TABLE brands (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    social_media_link VARCHAR(255) NOT NULL,
    brand_values_character TEXT,
    target_audience TEXT,
    contact_person_name VARCHAR(255),
    founder_interests TEXT,
    category VARCHAR(255),
    subscriber_count INT,
    geo VARCHAR(255),
    brand_value_id INT REFERENCES brand_value (id),
    brand_character_id INT REFERENCES brand_character (id),
    target_audience_id INT REFERENCES target_audience_description (id),
    interests_id INT REFERENCES interests (id),
    category_id INT REFERENCES category (id)
);

CREATE TABLE brand_value (
    id SERIAL PRIMARY KEY,
    valueBrand VARCHAR(255)
);

CREATE TABLE brand_character (
    id SERIAL PRIMARY KEY,
    nameValue VARCHAR(255)
);

CREATE TABLE target_audience_description (
    id SERIAL PRIMARY KEY,
    startup TEXT,
    wives_of_rich_husbands TEXT,
    salaried_employees TEXT,
    agencies_and_freelance TEXT
);

CREATE TABLE interests (
    id SERIAL PRIMARY KEY,
    interes VARCHAR(255)
);

CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    interes VARCHAR(255)
);

