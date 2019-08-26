CREATE TABLE player
    ( 
        id_player      INT(4) PRIMARY KEY NOT NULL,
        first_name    VARCHAR(25)  NOT NULL,
        last_name     VARCHAR(25) NOT NULL,
        pseudo        VARCHAR(25) NOT NULL,
        birthday      DATE NOT NULL,
        nationality   VARCHAR(25) NOT NULL,
        isBench       boolean NOT NULL,
        CHECK (CURRENT_DATE() > birthday),
        CHECK (id_player > 0)   
    )ENGINE = InnoDB;

CREATE TABLE membership
    (
        id_player INT(4) NOT NULL REFERENCES player(id_player) ,
        id_team INT(4)  NOT NULL REFERENCES team(id_team),
        entry_date DATE NOT NULL,
        leave_date DATE ,
        CHECK(id_player > 0),
        CHECK(id_team > 0),
        CHECK(entry_date < leave_date)
    )ENGINE = InnoDB;

CREATE TABLE team 
    (
        id_team INT(4) PRIMARY KEY NOT NULL,
        team_name VARCHAR(25) NOT NULL,
        world_ranking INT(4) UNIQUE,
        CHECK(id_team > 0),
        CHECK(world_ranking > 0)
    )
    ENGINE =  InnoDB;

CREATE TABLE participation
    (
        id_team INT(4) NOT NULL REFERENCES team(id_team),
        id_event INT(4) NOT NULL REFERENCES event(id_event),
        ranking INT(4) UNIQUE NOT NULL,
        CHECK(id_team > 0),
        CHECK(id_event > 0),
        CHECK(ranking > 0),
    )
    ENGINE =  InnoDB;

CREATE TABLE event 
    (
        id_event INT(4) PRIMARY KEY NOT NULL AUTO_INCREMENT,
        event_name VARCHAR(25) NOT NULL,
        nb_team INT(4) NOT NULL,
        cash_prize INT(10) NOT NULL,
        locality VARCHAR(25) NOT NULL,
        start_date DATE NOT NULL,
        end_date DATE NOT NULL,
        isMajor boolean NOT NULL,
        sponsor1 VARCHAR(25),
        sponsor2 VARCHAR(25),
        sponsor3 VARCHAR(25),
        sponsor4 VARCHAR(25),
        sponsor5 VARCHAR(25),
        caster1_name VARCHAR(25),
        caster2_name VARCHAR(25),
        caster3_name VARCHAR(25),
        caster4_name VARCHAR(25),
        caster5_name VARCHAR(25),
        CHECK(id_team > 0),
        CHECK(nb_team > 1),        
        CHECK(cash_prize > 0),
        CHECK(start_date < end_date)
    )ENGINE = InnoDB;

CREATE TABLE showdown
    (
        id_match INT(4) NOT NULL,
        id_team1 INT(4) NOT NULL REFERENCES team(id_team),
        id_team2 INT (4) NOT NULL REFERENCES team(id_team),
        CHECK(id_match > 0),
        CHECK(id_team1 > 0),
        CHECK(id_team2 > 0)
     )ENGINE = InnoDB;

CREATE TABLE game
    (
        id_match INT(4) PRIMARY KEY NOT NULL,
        match_format VARCHAR(15),
        start_date DATE NOT NULL, 
        id_event INT(4) NOT NULL REFERENCES event(id_event),
        CHECK(id_match > 0),
        CHECK(id_event > 0)
    )ENGINE = InnoDB;

CREATE TABLE result
    (
        id_match INT(4) NOT NULL REFERENCES game(id_match),
        map_name VARCHAR(25)  NOT NULL REFERENCES mappool(name),
        result_team1 INT(4) NOT NULL,
        result_team2 INT (4) NOT NULL,
        CHECK(id_match > 0),
        CHECK(result_team1 > 0), 
        CHECK(result_team2 > 0),
        CHECK(result_team1 + result_team2 = 30)
     )ENGINE = InnoDB;    

CREATE TABLE mappool
    (
        name VARCHAR(25) PRIMARY KEY NOT NULL
    )ENGINE = InnoDB;


INSERT INTO player VALUES
(
    1, 'Mathieu', 'Herbeumont', 'Zywoo', str_to_date('21-SEP-1989', '%e-%b-%Y'),
    'french', false
);

INSERT INTO player VALUES
(
    2, 'Fabien', 'Fiey', 'Kioshima', str_to_date('02-JUN-1993', '%e-%b-%Y'),
    'french', true
);
INSERT INTO player VALUES
(
    3, 'Nathan', 'Schmit', 'NBK', str_to_date('12-MAR-1993', '%e-%b-%Y'),
    'french', false
);
INSERT INTO player VALUES
(
    4, 'Cedric', 'Guipouy', 'RpK', str_to_date('22-OCT-1995', '%e-%b-%Y'),
    'french', false
);
INSERT INTO player VALUES
(
    5, 'Dan', 'Madesclaire', 'apEX', str_to_date('25-JAN-1990', '%e-%b-%Y'),
    'french', false
);
INSERT INTO player VALUES
(
    6, 'Alex', 'McMeekin', 'ALEX', str_to_date('20-SEP-1998', '%e-%b-%Y'),
    'english', false
);
INSERT INTO player VALUES
(
    7, 'Audric', 'Jug', 'Jackz', str_to_date('02-NOV-1993', '%e-%b-%Y'),
    'french', false
);
INSERT INTO player VALUES
(
    8, 'Richard', 'Papillon', 'shox', str_to_date('04-AVR-1995', '%e-%b-%Y'),
    'french', false
);
INSERT INTO player VALUES
(
    9, 'Kenny', 'Schrub', 'KennyS', str_to_date('22-DEC-1995', '%e-%b-%Y'),
    'french', false
);
INSERT INTO player VALUES
(
    10, 'François', 'Delaunay', 'Amanek', str_to_date('30-MAY-1993', '%e-%b-%Y'),
    'french', false
);
INSERT INTO player VALUES
(
    11, 'Lucas', 'Chastang', 'Lucky', str_to_date('15-FEB-1998', '%e-%b-%Y'),
    'french', false
);
INSERT INTO player VALUES
(
    12, 'Andreas', 'Hojsleth', 'Xyp9X', str_to_date('15-OCT-1990', '%e-%b-%Y'),
    'danish', false
);
INSERT INTO player VALUES
(
    13, 'Peter', 'Rasmussen', 'dupreeh', str_to_date('25-NOV-1994', '%e-%b-%Y'),
    'danish', false
);
INSERT INTO player VALUES
(
    14, 'Lukas', 'Rossander', 'gla1ve', str_to_date('07-JUL-1998', '%e-%b-%Y'),
    'danish', false
);
INSERT INTO player VALUES
(
    15, 'Nicolai', 'Reedtz', 'device', str_to_date('12-AUG-1995', '%e-%b-%Y'),
    'danish', false
);
INSERT INTO player VALUES
(
    16, 'Emil', 'Reif', 'Magisk', str_to_date('02-MAY-1999', '%e-%b-%Y'),
    'danish', false
);
INSERT INTO player VALUES
(
    17, 'Kévin', 'Rabier', 'Misutaa', str_to_date('02-APR-2000', '%e-%b-%Y'),
    'french', true
);

INSERT INTO player VALUES
(
    18, 'Spencer', 'Martin', 'Hiko', str_to_date('18-OCT-1991', '%e-%b-%Y'),
    'american', true
);

INSERT INTO team VALUES
(
    1, 'Vitality', 2
);
INSERT INTO team VALUES
(
    2, 'G2'
);
INSERT INTO team VALUES
(
    3, 'Astralis', 1
);

INSERT INTO team VALUES
(
    4,'Navi', 3
);

//team vitality
INSERT INTO membership VALUES
(1, 1, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);
INSERT INTO membership VALUES
(3, 1, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);
INSERT INTO membership VALUES
(4, 1, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);
INSERT INTO membership VALUES
(5, 1, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);
INSERT INTO membership VALUES
(6, 1, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);

//team G2
INSERT INTO membership VALUES
(7, 2, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);
INSERT INTO membership VALUES
(8, 2, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);
INSERT INTO membership VALUES
(9, 2, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);
INSERT INTO membership VALUES
(10, 2, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);
INSERT INTO membership VALUES
(11, 2, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);

//team Astralis
INSERT INTO membership VALUES
(12, 3, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);
INSERT INTO membership VALUES
(13, 3, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);
INSERT INTO membership VALUES
(14, 3, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);
INSERT INTO membership VALUES
(15, 3, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);
INSERT INTO membership VALUES
(16, 3, str_to_date('01-OCT-2018', '%e-%b-%Y'), null);


INSERT INTO event VALUES
(1 , 'Starseries Season 7', 16, 250000, 'Boston', str_to_date('01-OCT-2018', '%e-%b-%Y'), str_to_date('10-OCT-2018', '%e-%b-%Y'), false, 'Nike',null, null, null, null, 'XTQZZ', null, null,null,null);

Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('E-League Saison 6', 16, 200000, 'Cluj-Napoca', str_to_date('20-SEP-2018', '%e-%b-%Y'), str_to_date('27-SEP-2018', '%e-%b-%Y'), true, 'ASUS', 'BenJ');
Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('E-League Saison 5', 16, 200000, 'Cluj-Napoca', str_to_date('20-SEP-2017', '%e-%b-%Y'), str_to_date('27-SEP-2017', '%e-%b-%Y'), false, 'MSI', 'Manu');
Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('E-League Saison 4', 16, 200000, 'Cluj-Napoca', str_to_date('20-SEP-2016', '%e-%b-%Y'), str_to_date('27-SEP-2016', '%e-%b-%Y'), false, 'Razer', 'Andre');
Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('ESL-ONE', 16, 200000, 'Cologne', str_to_date('01-JUN-2019', '%e-%b-%Y'), str_to_date('09-Jun-2019', '%e-%b-%Y'), true, 'MSI', 'Manu');
Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('ESL-ONE', 16, 200000, 'Cologne', str_to_date('01-JUN-2018', '%e-%b-%Y'), str_to_date('09-Jun-2018', '%e-%b-%Y'), true, 'ASUS', 'PM');
Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('ESL-ONE', 16, 200000, 'Cologne', str_to_date('01-JUN-2017', '%e-%b-%Y'), str_to_date('09-Jun-2017', '%e-%b-%Y'), true, 'Thermaltake', 'Cnd');
Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('ESL-ONE', 16, 200000, 'Cologne', str_to_date('01-JUN-2016', '%e-%b-%Y'), str_to_date('09-Jun-2016', '%e-%b-%Y'), true, 'HyperX', 'Francois');
Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('Starladder Saison 2', 16, 150000, 'Paris', str_to_date('19-JUN-2015', '%e-%b-%Y'), str_to_date('29-Jun-2015', '%e-%b-%Y'), true, 'Apple', 'Cnd');
Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('Starladder Saison 1', 16, 100000, 'Paris', str_to_date('19-JUN-2014', '%e-%b-%Y'), str_to_date('29-Jun-2014', '%e-%b-%Y'), true, 'Apple', 'Cnd');
Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('Dreamhack ', 16, 100000, 'Marseille', str_to_date('07-MAI-2017', '%e-%b-%Y'), str_to_date('17-Mai-2017', '%e-%b-%Y'), false, 'Corsair', 'Andre');
Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('Dreamhack ', 16, 150000, 'Marseille', str_to_date('07-MAI-2018', '%e-%b-%Y'), str_to_date('17-Mai-2018', '%e-%b-%Y'), false, 'MSI', 'Maniac');
Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('Dreamhack ', 16, 200000, 'Marseille', str_to_date('07-MAI-2019', '%e-%b-%Y'), str_to_date('17-Mai-2019', '%e-%b-%Y'), false, 'Corsair', 'Andre');
Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('E', 16, 200000, 'Cologne', str_to_date('01-JUN-2019', '%e-%b-%Y'), str_to_date('09-Jun-2019', '%e-%b-%Y'), false, 'MSI', 'Manu');
Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('StarSeries Saison 6', 16, 200000, 'Cologne', str_to_date('01-JUN-2019', '%e-%b-%Y'), str_to_date('09-Jun-2019', '%e-%b-%Y'), false, 'MSI', 'Manu');
Insert into event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor, sponsor1, caster1_name)
VALUES ('StarSeries Saison 8', 16, 250000, 'Chicago', str_to_date('01-JUN-2017', '%e-%b-%Y'), str_to_date('09-Jun-2017', '%e-%b-%Y'), false, 'Roccat', 'Pomf');

INSERT into participation VALUES
(1, 1, 1);
INSERT into participation VALUES
(2, 1, 2);
INSERT into participation VALUES
(3, 1, 3);
INSERT into participation VALUES
(4, 1, 8);
INSERT into participation VALUES
(5, 1, 16);
INSERT into participation VALUES
(1, 2, 1);
INSERT into participation VALUES
(2, 2, 2);
INSERT into participation VALUES
(3, 2, 3);
INSERT into participation VALUES
(4, 2, 8);
INSERT into participation VALUES
(5, 2, 16);
INSERT into participation VALUES
(1, 3, 1);
INSERT into participation VALUES
(2, 3, 2);
INSERT into participation VALUES
(3, 3, 3);
INSERT into participation VALUES
(4, 3, 8);
INSERT into participation VALUES
(5, 3, 16);
INSERT into participation VALUES
(1, 4, 1);
INSERT into participation VALUES
(2, 4, 2);
INSERT into participation VALUES
(3, 4, 3);
INSERT into participation VALUES
(4, 4, 8);
INSERT into participation VALUES
(5, 4, 16);
INSERT into participation VALUES
(1, 5, 1);
INSERT into participation VALUES
(2, 5, 2);
INSERT into participation VALUES
(3, 5, 3);
INSERT into participation VALUES
(4, 5, 8);
INSERT into participation VALUES
(5, 5, 16);
INSERT into participation VALUES
(1, 6, 1);
INSERT into participation VALUES
(2, 6, 2);
INSERT into participation VALUES
(3, 6, 3);
INSERT into participation VALUES
(4, 6, 8);
INSERT into participation VALUES
(5, 6, 16);
INSERT into participation VALUES
(1, 7, 1);
INSERT into participation VALUES
(2, 7, 2);
INSERT into participation VALUES
(3, 7, 3);
INSERT into participation VALUES
(4, 8, 8);
INSERT into participation VALUES
(5, 8, 16);
INSERT into participation VALUES
(1, 9, 1);
INSERT into participation VALUES
(2, 9, 2);
INSERT into participation VALUES
(3, 9, 3);
INSERT into participation VALUES
(4, 9, 8);
INSERT into participation VALUES
(5, 9, 16);
INSERT into participation VALUES
(1, 10, 1);
INSERT into participation VALUES
(2, 10, 2);
INSERT into participation VALUES
(3, 10, 3);
INSERT into participation VALUES
(4, 10, 8);
INSERT into participation VALUES
(5, 10, 16);
INSERT into participation VALUES
(1, 10, 1);
INSERT into participation VALUES
(2,10, 2);
INSERT into participation VALUES
(3, 10, 3);
INSERT into participation VALUES
(4, 10, 8);
INSERT into participation VALUES
(5, 10, 16);