DROP DATABASE joker_new;

CREATE DATABASE joker_new;

USE joker_new;

CREATE TABLE roles(
  id INT(10) AUTO_INCREMENT,
  role VARCHAR(20) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE users (
  id INT(10) AUTO_INCREMENT,
  role_id INT(10) NOT NULL,
  login VARCHAR(20) NOT NULL UNIQUE,
  mail VARCHAR(45) NOT NULL UNIQUE,
  telephone VARCHAR(45),
  password VARCHAR(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE statuses(
  id INT(10) AUTO_INCREMENT,
  status VARCHAR(20) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE jokes (
  id INT (10) AUTO_INCREMENT,
  user_id INT (10) NOT NULL,
  status_id INT (10) NOT NULL,
  date DATETIME NOT NULL,
  likes INT (10) NOT NULL,
  dislikes INT (10) NOT NULL,
  text LONGTEXT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (status_id) REFERENCES statuses (id)
);

CREATE TABLE votes (
  id INT (10) AUTO_INCREMENT,
  user_id INT (10) NOT NULL,
  joke_id INT (10) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (joke_id) REFERENCES jokes (id)
);

INSERT INTO roles VALUES ('1','user');
INSERT INTO roles VALUES ('2','moderator');
INSERT INTO roles VALUES ('3','admin');

INSERT INTO statuses VALUES ('1','new');
INSERT INTO statuses VALUES ('2','archive');
INSERT INTO statuses VALUES ('3','deleted');

INSERT INTO users VALUES ('1','3','admin','tymchenkoivan@gmail.com', '0506631485', 'password');
INSERT INTO users VALUES ('2','1','anna','anna@gmail.com', '0506631485', 'anna');
INSERT INTO users VALUES ('3','1','ivan','ivan@gmail.com', '0506631485', 'ivan');

commit;