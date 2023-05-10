CREATE TABLE IF NOT EXISTS rarity
(
    rarity_name VARCHAR
(
    45
) NOT NULL UNIQUE,
    rarity_prefix VARCHAR
(
    15
) NOT NULL,
    rarity_percentage DOUBLE NOT NULL
    );