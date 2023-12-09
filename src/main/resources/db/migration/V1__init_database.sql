CREATE TABLE IF NOT EXISTS products
(
    id                 BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name               VARCHAR(32) UNIQUE NOT NULL,
    description        VARCHAR(256),
    has_discount       BOOL               NOT NULL DEFAULT FALSE,
    discount_condition VARCHAR(128),
    price_per_unit     NUMERIC(16, 2)     NOT NULL,
    available          BOOL               NOT NULL DEFAULT TRUE,
    created_at         TIMESTAMP,
    updated_at         TIMESTAMP
);

INSERT INTO products(name, description, has_discount, discount_condition, price_per_unit, available)
VALUES ('Издание книги', 'Минимальный заказ от 200 экземпляров', true,
        'При заказе тиража более 1тыс, предоставляется скидка 7% на все книги', 9.70, true);

