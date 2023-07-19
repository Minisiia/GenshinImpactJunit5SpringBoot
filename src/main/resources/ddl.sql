CREATE TABLE genshin_regions (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    location VARCHAR(255)
);

INSERT INTO genshin_regions (name, description, location)
VALUES
    ('Mondstadt', 'The City of Wind and Freedom, where the windblades and songs soar', 'Eastern Teyvat'),
    ('Liyue', 'The bustling harbor city, where the rocks and mountains are rich in minerals', 'Central Teyvat'),
    ('Inazuma', 'The land of the eternal lightning, where the thunderous drums of war echo', 'Southern Teyvat'),
    ('Sumeru', 'The land of unending wisdom, where the sands of time carry ancient secrets', 'Western Teyvat'),
    ('Snezhnaya', 'The land of perpetual winter, where the snow and ice conceal many secrets', 'Northern Teyvat');


CREATE TABLE genshin_heroes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    element VARCHAR(10),
    weapon VARCHAR(20),
    genshin_region_id INT,
    rarity INT,
    FOREIGN KEY (genshin_region_id) REFERENCES genshin_regions(id)
);

INSERT INTO genshin_heroes
    (name, element, weapon, genshin_region_id, rarity)
VALUES
    ('Diluc', 'Pyro', 'Claymore', 1, 5),
    ('Sayu', 'Anemo', 'Claymore', 3, 4),
    ('Venti', 'Anemo', 'Bow', 1,  5),
    ('Keqing', 'Electro', 'Sword', 2, 5),
    ('Klee', 'Pyro', 'Catalyst', 1, 5),
    ('Barbara', 'Gydro', 'Catalyst', 1, 4),
    ('Xiangling', 'Pyro', 'Polearm', 2, 4),
    ('Colley', 'Dendro', 'Bow', 4, 4),
    ('Kazuha', 'Anemo', 'Sword', 3, 5),
    ('Albedo', 'Geo', 'Sword', 1, 5),
    ('Ayaka', 'Cryo', 'Sword', 3, 5),
    ('Yoimiya', 'Pyro', 'Bow', 3, 5),
    ('Raiden Shogun', 'Electro', 'Polearm', 3, 5),
    ('Zhongli', 'Geo', 'Polearm', 2 , 5);