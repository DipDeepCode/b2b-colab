CREATE TABLE brands (
    id BIGSERIAL PRIMARY KEY,
    customer_phoneNumber VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    tariff_id BIGINT NOT NULL,
    FOREIGN KEY (tariff_id) REFERENCES tariff_plan(id)
)