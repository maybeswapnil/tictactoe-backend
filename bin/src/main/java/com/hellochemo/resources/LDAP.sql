USE chemosenpai;

DROP DATABASE IF EXISTS tictactoe;
CREATE DATABASE tictactoe;
USE tictactoe;

DROP TABLE IF EXISTS tictactoe;


CREATE TABLE IF NOT EXISTS tictactoe (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    name varchar(20) DEFAULT NULL,
    Session int(20) DEFAULT NULL,
    Points int(20) DEFAULT NULL,
    uPass varchar(11),
    highScore int(20) DEFAULT NULL,
    PRIMARY KEY (uName)
    ) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;

INSERT INTO tictactoe (uName, Session, Points, uPass, highScore) VALUES
(1001,'chemo', 0, 0,'welcome',1);

commit;