
CREATE TABLE IF NOT EXISTS users
(
    ID                 INT,
    CREATED_BY         VARCHAR(255),
    CREATED_DATE       DATE,
    LAST_MODIFIED_BY   VARCHAR(255),
    LAST_MODIFIED_DATE DATE,
    IS_ACTIVE          BOOLEAN,
    CATCH_LIST_ID      INT,
    WISH_LIST_ID       INT,
    USERNAME           VARCHAR(255),
    PASSWORD           VARCHAR(255),
    EMAIL              VARCHAR(255),
    NAME               VARCHAR(255),
    SURNAME            VARCHAR(255),
    ROLE               VARCHAR(255),
    THUMBNAIL          LONGTEXT,
    ABOUT              VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS pokemon
(
    ID                 INT,
    CREATED_BY         VARCHAR(255),
    CREATED_DATE       DATE,
    LAST_MODIFIED_BY   VARCHAR(255),
    LAST_MODIFIED_DATE DATE,
    IS_ACTIVE          BOOLEAN,
    NAME               VARCHAR(255),
    SPECIE             VARCHAR(255),
    ABILITIES          VARCHAR(255),
    THUMBNAIL          LONGTEXT,
    ABOUT              VARCHAR(255),
    HEIGHT             DOUBLE,
    WEIGHT             DOUBLE,
    ATTACK             INT,
    DEFENSE            INT,
    HP                 INT,
    SPEED              INT,
    SPECIAL_ATTACK     INT,
    SPECIAL_DEFENSE    INT

);

CREATE TABLE IF NOT EXISTS pokemon_type
(
    POKEMON_ID INT,
    TYPES      VARCHAR(255)

);

CREATE TABLE IF NOT EXISTS catch_list
(
    ID                 INT,
    CREATED_BY         VARCHAR(255),
    CREATED_DATE       DATE,
    LAST_MODIFIED_BY   VARCHAR(255),
    LAST_MODIFIED_DATE DATE,
    IS_ACTIVE          BOOLEAN
);

CREATE TABLE IF NOT EXISTS wish_list
(
    ID                 INT,
    CREATED_BY         VARCHAR(255),
    CREATED_DATE       DATE,
    LAST_MODIFIED_BY   VARCHAR(255),
    LAST_MODIFIED_DATE DATE,
    IS_ACTIVE          BOOLEAN
);

CREATE TABLE IF NOT EXISTS catch_list_pokemons
(
    POKEMONS_ID   INT,
    CATCH_LIST_ID INT

);

CREATE TABLE IF NOT EXISTS wish_list_pokemons
(
    POKEMONS_ID   INT,
    WISH_LIST_ID INT

);

INSERT INTO catch_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO catch_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO catch_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO catch_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO catch_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO catch_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO catch_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO catch_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO catch_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO catch_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO catch_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());

INSERT INTO wish_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO wish_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO wish_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO wish_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO wish_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO wish_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO wish_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO wish_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO wish_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO wish_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO wish_list (IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES (true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());

INSERT INTO users (USERNAME, EMAIL, wish_list_ID, catch_list_ID, NAME, SURNAME, THUMBNAIL, PASSWORD, ROLE, IS_ACTIVE,
                   CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('admin', 'admin@gmail.com', 1, 1, 'System', 'Admin',
        'https://thumbs.dreamstime.com/b/admin-sign-laptop-icon-stock-vector-166205404.jpg',
        '$2a$10$JKJKCH4/v67.0I2ETLbj8utWedgMNlkS3Zl3okF4uGt0Gx80OWXZe', 'ADMIN', true,
        'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());


INSERT INTO users (USERNAME, EMAIL, wish_list_ID, catch_list_ID, NAME, SURNAME, THUMBNAIL, PASSWORD, ROLE, IS_ACTIVE,
                   CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('ashketchum', 'ashketchum@gmail.com', 2, 2, 'Ash', 'Ketchum',
        'https://sm.ign.com/ign_ap/screenshot/default/ashwins_78um.jpg',
        '$2a$10$JKJKCH4/v67.0I2ETLbj8utWedgMNlkS3Zl3okF4uGt0Gx80OWXZe', 'TRAINER', true,
        'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());

INSERT INTO users (USERNAME, EMAIL, wish_list_ID, catch_list_ID, NAME, SURNAME, THUMBNAIL, PASSWORD, ROLE, IS_ACTIVE,
                   CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('serena', 'serena@gmail.com', 3, 3, 'Serena', '',
        'https://fictionhorizon.com/wp-content/uploads/2023/01/Serena.jpg',
        '$2a$10$JKJKCH4/v67.0I2ETLbj8utWedgMNlkS3Zl3okF4uGt0Gx80OWXZe', 'TRAINER', true,
        'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());

INSERT INTO users (USERNAME, EMAIL, wish_list_ID, catch_list_ID, NAME, SURNAME, THUMBNAIL, PASSWORD, ROLE, IS_ACTIVE,
                   CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('misty', 'misty@gmail.com', 4, 4, 'Misty', '',
        'https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/4aae6c71-b26f-4541-0f41-69b58ec91800/width=450/276282.jpeg',
        '$2a$10$JKJKCH4/v67.0I2ETLbj8utWedgMNlkS3Zl3okF4uGt0Gx80OWXZe', 'TRAINER', true,
        'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());

INSERT INTO users (USERNAME, EMAIL, wish_list_ID, catch_list_ID, NAME, SURNAME, THUMBNAIL, PASSWORD, ROLE, IS_ACTIVE,
                   CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('brock', 'brock@gmail.com', 5, 5, 'Brock', '',
        'https://images.squarespace-cdn.com/content/v1/5a15ad16b7411ccec818471e/1564882967570-6RUY1NWUIKZPN7RC82ST/public.jpeg',
        '$2a$10$JKJKCH4/v67.0I2ETLbj8utWedgMNlkS3Zl3okF4uGt0Gx80OWXZe', 'TRAINER', true,
        'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());

INSERT INTO users (USERNAME, EMAIL, wish_list_ID, catch_list_ID, NAME, SURNAME, THUMBNAIL, PASSWORD, ROLE, IS_ACTIVE,
                   CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('cynthia', 'cynthia@gmail.com', 6, 6, 'Cynthia', '',
        'https://www.dexerto.com/cdn-cgi/image/width=3840,quality=75,format=auto/https://editors.dexerto.com/wp-content/uploads/2022/08/20/Pokemon-Worlds-Cynthia-Cosplay-Is-Powerful-And-Perfect.jpg',
        '$2a$10$JKJKCH4/v67.0I2ETLbj8utWedgMNlkS3Zl3okF4uGt0Gx80OWXZe', 'TRAINER', true,
        'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());

INSERT INTO users (USERNAME, EMAIL, wish_list_ID, catch_list_ID, NAME, SURNAME, THUMBNAIL, PASSWORD, ROLE, IS_ACTIVE,
                   CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('hapu', 'hapu@gmail.com', 7, 7, 'Hapu', '',
        'https://gamepress.gg/pokemonmasters/sites/pokemonmasters/files/2019-08/ch0013_00_hapu_256_battle.ktx.png',
        '$2a$10$JKJKCH4/v67.0I2ETLbj8utWedgMNlkS3Zl3okF4uGt0Gx80OWXZe', 'TRAINER', true,
        'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());

INSERT INTO users (USERNAME, EMAIL, wish_list_ID, catch_list_ID, NAME, SURNAME, THUMBNAIL, PASSWORD, ROLE, IS_ACTIVE,
                   CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('blueoak', 'blueoak@gmail.com', 8, 8, 'Blue', 'Oak',
        'https://i.pinimg.com/474x/62/ae/12/62ae12ac11437d7bc007bbe6edad3101.jpg',
        '$2a$10$JKJKCH4/v67.0I2ETLbj8utWedgMNlkS3Zl3okF4uGt0Gx80OWXZe', 'TRAINER', true,
        'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());

INSERT INTO users (USERNAME, EMAIL, wish_list_ID, catch_list_ID, NAME, SURNAME, THUMBNAIL, PASSWORD, ROLE, IS_ACTIVE,
                   CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('barry', 'barry@gmail.com', 9, 9, 'Barry', '', 'https://i.ytimg.com/vi/amhFgjmCHDY/maxresdefault.jpg',
        '$2a$10$JKJKCH4/v67.0I2ETLbj8utWedgMNlkS3Zl3okF4uGt0Gx80OWXZe', 'TRAINER', true,
        'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());


INSERT INTO users (USERNAME, EMAIL, wish_list_ID, catch_list_ID, NAME, SURNAME, THUMBNAIL, PASSWORD, ROLE, IS_ACTIVE,
                   CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('ethan', 'ethan@gmail.com', 10, 10, 'Ethan', '',
        'https://w0.peakpx.com/wallpaper/507/181/HD-wallpaper-pokemon-ethan-pokemon-cap.jpg',
        '$2a$10$JKJKCH4/v67.0I2ETLbj8utWedgMNlkS3Zl3okF4uGt0Gx80OWXZe', 'TRAINER', true,
        'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());

INSERT INTO users (USERNAME, EMAIL, wish_list_ID, catch_list_ID, NAME, SURNAME, THUMBNAIL, PASSWORD, ROLE, IS_ACTIVE,
                   CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('brendan', 'brendan@gmail.com', 11, 11, 'Brendan', '',
        'https://i.pinimg.com/originals/2c/c7/a6/2cc7a6d19c8131270b129d1d8d6b7c31.jpg',
        '$2a$10$JKJKCH4/v67.0I2ETLbj8utWedgMNlkS3Zl3okF4uGt0Gx80OWXZe', 'TRAINER', true,
        'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Bulbasaur', 'https://img.pokemondb.net/artwork/large/bulbasaur.jpg', 0.7, 6.9, 'Seed Pokemon',
        'Overgrow ,Chlorophyll (hidden ability)', 45, 49, 49, 45, 65, 65, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (1, 'GRASS');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (1, 'POISON');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Ivysaur', 'https://img.pokemondb.net/artwork/large/ivysaur.jpg', 1.0, 13.0, 'Seed Pokemon',
        'Overgrow ,Chlorophyll (hidden ability)', 60, 62, 63, 60, 80, 80, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (2, 'GRASS');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (2, 'POISON');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Venusaur', 'https://img.pokemondb.net/artwork/large/venusaur.jpg', 2.0, 100.0, 'Seed Pokemon',
        'Overgrow ,Chlorophyll (hidden ability)', 80, 82, 83, 80, 100, 100, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (3, 'GRASS');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (3, 'POISON');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Charmander', 'https://img.pokemondb.net/artwork/large/charmander.jpg', 0.6, 8.5, 'Lizard Pokemon',
        'Blaze , Solar Power (hidden ability)', 39, 52, 43, 65, 60, 50, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (4, 'FIRE');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Charmeleon', 'https://img.pokemondb.net/artwork/large/charmeleon.jpg', 1.1, 19.0, 'Lizard Pokemon',
        'Blaze , Solar Power (hidden ability)', 58, 64, 58, 80, 80, 65, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (5, 'FIRE');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Charizard', 'https://img.pokemondb.net/artwork/large/charizard.jpg', 1.7, 90.5, 'Lizard Pokemon',
        'Blaze , Solar Power (hidden ability)', 78, 84, 78, 100, 109, 85, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (6, 'FIRE');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (6, 'FLYING');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Squirtle', 'https://img.pokemondb.net/artwork/large/squirtle.jpg', 0.5, 9.0, 'Tiny Turtle Pokemon',
        'Torrent , Rain Dish (hidden ability)', 44, 48, 65, 43, 50, 64, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (7, 'WATER');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Wartortle', 'https://img.pokemondb.net/artwork/large/wartortle.jpg', 1.0, 22.5, 'Turtle Pokemon',
        'Torrent , Rain Dish (hidden ability)', 59, 63, 80, 58, 65, 80, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (8, 'WATER');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Blastoise', 'https://img.pokemondb.net/artwork/large/blastoise.jpg', 1.6, 85.5, 'Shellfish Pokemon',
        'Torrent , Rain Dish (hidden ability)', 79, 83, 100, 78, 85, 105, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (9, 'WATER');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Caterpie', 'https://img.pokemondb.net/artwork/large/caterpie.jpg', 0.3, 2.9, 'Worm  Pokemon',
        'Shield Dust , Run Away (hidden ability)', 45, 30, 35, 45, 20, 20, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (10, 'BUG');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Metapod', 'https://img.pokemondb.net/artwork/large/metapod.jpg', 0.7, 9.9, 'Cocoon Pokemon', 'Shed Skin', 50,
        20, 55, 30, 25, 25, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (11, 'BUG');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Butterfree', 'https://img.pokemondb.net/artwork/large/butterfree.jpg', 1.1, 32.0, 'Butterfly Pokemon',
        'Compound Eyes, Tinted Lens (hidden ability)', 60, 45, 50, 70, 90, 80, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (12, 'BUG');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (12, 'FLYING');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Weedle', 'https://img.pokemondb.net/artwork/large/weedle.jpg', 0.3, 3.2, 'Hairy Bug Pokemon',
        'Shield Dust, Run Away (hidden ability)', 40, 35, 30, 50, 20, 20, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (13, 'BUG');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (13, 'POISON');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Kakuna', 'https://img.pokemondb.net/artwork/large/kakuna.jpg', 0.6, 10.0, 'Cocoon Pokemon', 'Shed Skin', 45,
        25, 50, 35, 25, 25, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (14, 'BUG');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (14, 'POISON');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Beedrill', 'https://img.pokemondb.net/artwork/large/beedrill.jpg', 0.6, 10.0, 'Poison Bee Pokemon',
        'Swarm, Sniper (hidden ability)', 65, 90, 40, 75, 80, 45, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (15, 'BUG');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (15, 'POISON');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Pidgey', 'https://img.pokemondb.net/artwork/large/pidgey.jpg', 0.3, 1.8, 'Tiny Bird Pokemon',
        ' Keen Eye,  Tangled Feet, Big Pecks (hidden ability)', 40, 45, 40, 56, 35, 35, true, 'SYSTEM', CURRENT_DATE(),
        'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (16, 'NORMAL');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (16, 'FLYING');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Pidgeotto', 'https://img.pokemondb.net/artwork/large/pidgeotto.jpg', 1.1, 30.0, 'Bird Pokemon',
        ' Keen Eye,  Tangled Feet, Big Pecks (hidden ability)', 63, 60, 55, 71, 50, 50, true, 'SYSTEM', CURRENT_DATE(),
        'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (17, 'NORMAL');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (17, 'FLYING');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Pidgeot', 'https://img.pokemondb.net/artwork/large/pidgeot.jpg', 1.5, 39.5, 'Bird Pokemon',
        ' Keen Eye,  Tangled Feet, Big Pecks (hidden ability)', 83, 80, 75, 101, 70, 70, true, 'SYSTEM', CURRENT_DATE(),
        'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (18, 'NORMAL');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (18, 'FLYING');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Pikachu', 'https://img.pokemondb.net/artwork/large/pikachu.jpg', 0.4, 6.0, 'Mouse  Pokemon',
        ' Static, Lightning Rod (hidden ability)', 35, 55, 40, 90, 50, 50, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (19, 'ELECTRIC');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Raichu', 'https://img.pokemondb.net/artwork/large/raichu.jpg', 0.8, 30.0, 'Mouse  Pokemon',
        ' Static, Lightning Rod (hidden ability)', 60, 90, 55, 110, 80, 90, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (20, 'ELECTRIC');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Sandshrew', 'https://img.pokemondb.net/artwork/large/sandshrew.jpg', 0.6, 12.0, 'Mouse  Pokemon',
        ' Sand Veil, Sand Rush (hidden ability)', 50, 75, 85, 40, 30, 20, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (21, 'GROUND');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Alolan Sandshrew', 'https://img.pokemondb.net/artwork/large/sandshrew-alolan.jpg', 0.7, 40.0, 'Mouse  Pokemon',
        'Snow Cloak, Slush Rush (hidden ability)', 50, 75, 90, 40, 35, 10, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (22, 'ICE');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (22, 'STEEL');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Clefairy', 'https://img.pokemondb.net/artwork/large/clefairy.jpg', 0.6, 7.5, 'Fairy   Pokemon',
        'Cute Charm, Magic Guard, Friend Guard (hidden ability)', 70, 45, 48, 35, 65, 60, true, 'SYSTEM',
        CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (23, 'FAIRY');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Vulpix', 'https://img.pokemondb.net/artwork/large/vulpix.jpg', 0.6, 9.9, 'Fox Pokemon',
        'Flash Fire, Drought (hidden ability)', 38, 41, 40, 65, 65, 50, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (24, 'FIRE');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Alolan Meowth', 'https://img.pokemondb.net/artwork/large/meowth-alolan.jpg', 0.4, 4.2, 'Scratch Cat Pokemon',
        ' Pickup, Technician,  Rattled (hidden ability)', 40, 35, 35, 90, 40, 50, true, 'SYSTEM', CURRENT_DATE(),
        'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (25, 'DARK');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Mankey', 'https://img.pokemondb.net/artwork/large/mankey.jpg', 0.5, 28.0, 'Pig Monkey Pokemon',
        ' Vital Spirit, Anger Point, Defiant (hidden ability)', 40, 80, 35, 70, 45, 35, true, 'SYSTEM', CURRENT_DATE(),
        'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (26, 'FIGHTING');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Hisuian Arcanine', 'https://img.pokemondb.net/artwork/large/arcanine-hisuian.jpg', 2.0, 168.0,
        'Legendary  Pokemon', ' Intimidate, Flash Fire, Rock Head (hidden ability)', 95, 115, 80, 90, 95, 80, true,
        'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (27, 'FIRE');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (27, 'ROCK');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Abra', 'https://img.pokemondb.net/artwork/large/abra.jpg', 0.9, 19.5, 'Psi  Pokemon',
        'Synchronize, Inner Focus, Magic Guard (hidden ability)', 25, 20, 15, 90, 55, 105, true, 'SYSTEM',
        CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (28, 'PSYCHIC');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Gastly', 'https://img.pokemondb.net/artwork/large/gastly.jpg', 1.3, 0.1, 'Gas Pokemon', 'Levitate', 30, 35, 30,
        80, 35, 100, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (29, 'GHOST');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (29, 'POISON');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Alolan Exeggutor', 'https://img.pokemondb.net/artwork/large/exeggutor-alolan.jpg', 10.9, 415.6,
        'Coconut Pokemon', 'Frisk, Harvest (hidden ability)', 95, 105, 85, 45, 75, 125, true, 'SYSTEM', CURRENT_DATE(),
        'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (30, 'GRASS');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (30, 'DRAGON');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Tauros', 'https://img.pokemondb.net/artwork/large/tauros.jpg', 1.4, 88.4, 'Wild Bull Pokemon',
        'Intimidate, Anger Point, Sheer Force (hidden ability)', 75, 100, 95, 110, 70, 40, true, 'SYSTEM',
        CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (31, 'NORMAL');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Blaze Tauros', 'https://img.pokemondb.net/artwork/large/tauros-blaze.jpg', 1.4, 88.4, 'Wild Bull Pokemon',
        'Intimidate, Anger Point, Cud Chew (hidden ability)', 75, 110, 105, 30, 70, 100, true, 'SYSTEM', CURRENT_DATE(),
        'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (32, 'FIRE');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (32, 'FIGHTING');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Iron Leaves', 'https://img.pokemondb.net/artwork/large/iron-leaves.jpg', 1.5, 125.0, 'Paradox  Pokemon',
        'Quark Drive', 90, 130, 88, 104, 108, 70, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (33, 'GRASS');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (33, 'PSYCHIC');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Walking Wake', 'https://img.pokemondb.net/artwork/large/walking-wake.jpg', 3.5, 280.0, 'Paradox Pokemon',
        'Protosynthesis', 99, 83, 91, 109, 83, 125, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (34, 'WATER');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (34, 'DRAGON');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Koraidon', 'https://img.pokemondb.net/artwork/large/koraidon.jpg', 2.5, 303.0, 'Paradox Pokemon',
        'Orichalcum Pulse', 100, 135, 115, 135, 100, 85, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (35, 'FIGHTING');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (35, 'DRAGON');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Ting-Lu', 'https://img.pokemondb.net/artwork/large/ting-lu.jpg', 2.7, 699.7, 'Ruinous Pokemon',
        'Vessel of Ruin', 155, 110, 125, 55, 80, 45, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (36, 'DARK');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (36, 'GROUND');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Baxcalibur', 'https://img.pokemondb.net/artwork/large/baxcalibur.jpg', 2.1, 210.0, 'Ice Dragon Pokemon',
        'Thermal Exchange, Ice Body (hidden ability)', 115, 145, 92, 87, 86, 75, true, 'SYSTEM', CURRENT_DATE(),
        'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (37, 'ICE');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (37, 'DRAGON');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Annihilape', 'https://img.pokemondb.net/artwork/large/annihilape.jpg', 1.2, 56.0, 'Rage Monkey Pokemon',
        'Vital Spirit, Inner Focus, Defiant (hidden ability)', 110, 115, 80, 90, 90, 50, true, 'SYSTEM', CURRENT_DATE(),
        'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (38, 'FIGHTING');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (38, 'GHOST');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Crowned Shield Zamazenta', 'https://img.pokemondb.net/artwork/large/zamazenta-crowned.jpg', 2.9, 785.0,
        'Warrior Pokemon', 'Dauntless Shield', 92, 120, 140, 128, 140, 85, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (39, 'FIGHTING');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (39, 'STEEL');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Registeel', 'https://img.pokemondb.net/artwork/large/registeel.jpg', 1.9, 205.0, 'Iron Pokemon',
        'Clear Body, Light Metal (hidden ability)', 80, 75, 150, 75, 150, 50, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (40, 'STEEL');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Dialga', 'https://img.pokemondb.net/artwork/large/dialga.jpg', 5.4, 683.0, 'Temporal Pokemon',
        'Pressure, Telepathy (hidden ability)', 100, 120, 120, 90, 100, 150, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (41, 'STEEL');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (41, 'DRAGON');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Uxie', 'https://img.pokemondb.net/artwork/large/uxie.jpg', 0.3, 0.3, 'Knowledge Pokemon', 'Levitate', 75, 75,
        130, 75, 130, 95, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (42, 'PSYCHIC');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Heatran', 'https://img.pokemondb.net/artwork/large/heatran.jpg', 1.7, 430.0, 'Lava Dome Pokemon',
        'Flash Fire, Flame Body (hidden ability)', 91, 90, 106, 77, 106, 130, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (43, 'FIRE');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (43, 'STEEL');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Ninetales', 'https://img.pokemondb.net/artwork/large/ninetales.jpg', 1.1, 19.9, 'Fox Pokemon',
        'Flash Fire, Drought (hidden ability)', 73, 76, 75, 100, 81, 100, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (44, 'FIRE');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Alolan Ninetales', 'https://img.pokemondb.net/artwork/large/ninetales-alolan.jpg', 1.1, 19.9, 'Fox Pokemon',
        'Snow Cloak, Snow Warning (hidden ability)', 73, 67, 75, 109, 81, 100, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (45, 'ICE');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (45, 'FAIRY');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Jigglypuff', 'https://img.pokemondb.net/artwork/large/jigglypuff.jpg', 0.5, 5.5, 'Balloon Pokemon',
        'Cute Charm,Competitive, Friend Guard (hidden ability)', 115, 45, 20, 20, 25, 45, true, 'SYSTEM',
        CURRENT_DATE(), 'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (46, 'NORMAL');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (46, 'FAIRY');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Wigglytuff', 'https://img.pokemondb.net/artwork/large/wigglytuff.jpg', 1.0, 12.0, 'Balloon Pokemon',
        'Cute Charm,Competitive,Frisk (hidden ability)', 140, 70, 45, 45, 85, 50, true, 'SYSTEM', CURRENT_DATE(),
        'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (47, 'NORMAL');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (47, 'FAIRY');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Zubat', 'https://img.pokemondb.net/artwork/large/zubat.jpg', 0.8, 7.5, 'Balloon Pokemon',
        'Inner Focus,Infiltrator (hidden ability)', 40, 45, 35, 55, 40, 30, true, 'SYSTEM', CURRENT_DATE(), 'SYSTEM',
        CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (48, 'POISON');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (48, 'FLYING');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Tentacool', 'https://img.pokemondb.net/artwork/large/tentacool.jpg', 0.9, 45.5, 'Jellyfish  Pokemon',
        'Clear Body,Liquid Ooze, Rain Dish (hidden ability)', 40, 40, 35, 70, 50, 100, true, 'SYSTEM', CURRENT_DATE(),
        'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (49, 'WATER');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (49, 'POISON');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Tentacruel', 'https://img.pokemondb.net/artwork/large/tentacruel.jpg', 1.6, 55.0, 'Jellyfish  Pokemon',
        'Clear Body,Liquid Ooze, Rain Dish (hidden ability)', 80, 70, 65, 100, 80, 120, true, 'SYSTEM', CURRENT_DATE(),
        'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (50, 'WATER');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (50, 'POISON');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Golem', 'https://img.pokemondb.net/artwork/large/golem.jpg', 1.4, 300.0, 'Megaton  Pokemon',
        'Rock Head,Sturdy, Sand Veil (hidden ability)', 80, 120, 130, 45, 55, 65, true, 'SYSTEM', CURRENT_DATE(),
        'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (51, 'ROCK');
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (51, 'GROUND');

INSERT INTO pokemon (NAME, THUMBNAIL, HEIGHT, WEIGHT, SPECIE, ABILITIES, HP, ATTACK, DEFENSE, SPEED, SPECIAL_ATTACK,
                     SPECIAL_DEFENSE, IS_ACTIVE, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE)
VALUES ('Ponyta', 'https://img.pokemondb.net/artwork/large/ponyta.jpg', 1.0, 30.0, 'Fire Horse Pokemon',
        'Run Away, Flash Fire,Flame Body (hidden ability)', 50, 85, 55, 90, 65, 65, true, 'SYSTEM', CURRENT_DATE(),
        'SYSTEM', CURRENT_DATE());
INSERT INTO pokemon_type (POKEMON_ID, TYPES)
VALUES (52, 'FIRE');

INSERT INTO wish_list_pokemons (wish_list_id, pokemons_id)
VALUES (2, 4);
INSERT INTO wish_list_pokemons (wish_list_id, pokemons_id)
VALUES (2, 8);
INSERT INTO wish_list_pokemons (wish_list_id, pokemons_id)
VALUES (2, 6);
INSERT INTO wish_list_pokemons (wish_list_id, pokemons_id)
VALUES (2, 12);
INSERT INTO wish_list_pokemons (wish_list_id, pokemons_id)
VALUES (3, 2);
INSERT INTO wish_list_pokemons (wish_list_id, pokemons_id)
VALUES (3, 4);
INSERT INTO wish_list_pokemons (wish_list_id, pokemons_id)
VALUES (3, 8);
INSERT INTO wish_list_pokemons (wish_list_id, pokemons_id)
VALUES (4, 6);
INSERT INTO wish_list_pokemons (wish_list_id, pokemons_id)
VALUES (4, 12);
INSERT INTO wish_list_pokemons (wish_list_id, pokemons_id)
VALUES (4, 18);
INSERT INTO wish_list_pokemons (wish_list_id, pokemons_id)
VALUES (4, 1);
INSERT INTO wish_list_pokemons (wish_list_id, pokemons_id)
VALUES (8, 1);


INSERT INTO catch_list_pokemons (catch_list_id, pokemons_id)
VALUES (2, 1);
INSERT INTO catch_list_pokemons (catch_list_id, pokemons_id)
VALUES (2, 9);
INSERT INTO catch_list_pokemons (catch_list_id, pokemons_id)
VALUES (2, 19);
INSERT INTO catch_list_pokemons (catch_list_id, pokemons_id)
VALUES (2, 11);
INSERT INTO catch_list_pokemons (catch_list_id, pokemons_id)
VALUES (3, 1);
INSERT INTO catch_list_pokemons (catch_list_id, pokemons_id)
VALUES (3, 9);
INSERT INTO catch_list_pokemons (catch_list_id, pokemons_id)
VALUES (3, 19);
INSERT INTO catch_list_pokemons (catch_list_id, pokemons_id)
VALUES (5, 11);
INSERT INTO catch_list_pokemons (catch_list_id, pokemons_id)
VALUES (5, 1);
INSERT INTO catch_list_pokemons (catch_list_id, pokemons_id)
VALUES (5, 9);
INSERT INTO catch_list_pokemons (catch_list_id, pokemons_id)
VALUES (6, 19);
INSERT INTO catch_list_pokemons (catch_list_id, pokemons_id)
VALUES (7, 24);
INSERT INTO catch_list_pokemons (catch_list_id, pokemons_id)
VALUES (7, 1);
INSERT INTO catch_list_pokemons (catch_list_id, pokemons_id)
VALUES (10, 1);



