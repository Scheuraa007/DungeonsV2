CREATE TABLE IF NOT EXISTS rarity(rarity_id INT NOT NULL UNIQUE AUTO_INCREMENT, rarity_name VARCHAR(45) NOT NULL UNIQUE,
    rarity_prefix VARCHAR(15) NOT NULL,
    rarity_percentage DOUBLE NOT NULL);

CREATE TABLE IF NOT EXISTS dungeon(dungeon_id INT NOT NULL UNIQUE AUTO_INCREMENT, dungeon_name VARCHAR(45) NOT NULL UNIQUE,
    dungeon_rarity_id INT NOT NULL,
    dungeon_schematic_name VARCHAR(45) NOT NULL);