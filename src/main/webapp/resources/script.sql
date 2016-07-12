DROP DATABASE joker;

CREATE DATABASE joker;

USE joker;

CREATE TABLE users (
  id INT (10) AUTO_INCREMENT,
  login VARCHAR(45) NOT NULL UNIQUE,
  mail VARCHAR(45) NOT NULL UNIQUE,
  telephone VARCHAR(45),
  password VARCHAR(45) NOT NULL,
  mark VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE jokes (
  id INT (10) AUTO_INCREMENT,
  mark VARCHAR(45) NOT NULL,
  date DATETIME NOT NULL,
  likes INT (10) NOT NULL,
  dislikes INT (10) NOT NULL,
  text LONGTEXT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_user) REFERENCES users (id)
);

INSERT INTO users VALUES ('1','admin','tymchenkoivan@gmail.com', '0506631485', 'password', 'user');
INSERT INTO users VALUES ('2','anna','anna@gmail.com', '0506631485', 'anna', 'user');
INSERT INTO users VALUES ('3','ivan','ivan@gmail.com', '0506631485', 'ivan', 'user');

commit;