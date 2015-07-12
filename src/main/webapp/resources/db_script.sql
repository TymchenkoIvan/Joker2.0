DROP DATABASE joker2;

CREATE DATABASE joker2;

USE joker2;

CREATE TABLE users (
  id INT (10) AUTO_INCREMENT,
  role VARCHAR(20) NOT NULL,
  login VARCHAR(20) NOT NULL UNIQUE,
  mail VARCHAR(25) NOT NULL UNIQUE,
  telephone VARCHAR(20),
  password VARCHAR(20) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE jokes (
  id INT (10) AUTO_INCREMENT,
  id_user INT (10) NOT NULL,
  status VARCHAR(20) NOT NULL,
  date DATETIME NOT NULL,
  likes INT (10) NOT NULL,
  dislikes INT (10) NOT NULL,
  text LONGTEXT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_user) REFERENCES users (id)
);

INSERT INTO users VALUES ('1','admin','admin','tymchenkoivan@gmail.com', '0506631485', 'password');
INSERT INTO users VALUES ('2','user','anna','anna@gmail.com', '0506631485', 'anna');
INSERT INTO users VALUES ('3','user','ivan','ivan@gmail.com', '0506631485', 'ivan');

commit;