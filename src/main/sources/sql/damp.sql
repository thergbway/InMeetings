DROP TABLE IF EXISTS participant;
DROP TABLE IF EXISTS manager;
DROP TABLE IF EXISTS meeting;
DROP TABLE IF EXISTS user_total;
DROP TABLE IF EXISTS role;

CREATE TABLE role (
  id        SERIAL PRIMARY KEY,
  role_name VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE user_total (
  id         SERIAL PRIMARY KEY,
  login      VARCHAR(40) UNIQUE NOT NULL,
  password   VARCHAR(40)        NOT NULL,
  role_id    INT                NOT NULL REFERENCES role (id),
  first_name VARCHAR(40)        NOT NULL,
  last_name  VARCHAR(40)
);

CREATE TABLE meeting (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(40) NOT NULL,
  start_time  TIMESTAMP,
  end_time    TIMESTAMP,
  description VARCHAR(2000)
);

CREATE TABLE manager (
  id          SERIAL PRIMARY KEY,
  meeting_id  INT REFERENCES meeting (id),
  user_id     INT REFERENCES user_total (id),
  description VARCHAR(200)
);

CREATE TABLE participant (
  id         SERIAL PRIMARY KEY,
  meeting_id INT REFERENCES meeting (id),
  user_id    INT REFERENCES user_total (id)
);

INSERT INTO public.role (id, role_name) VALUES (1, 'admin');
INSERT INTO public.role (id, role_name) VALUES (2, 'user');

INSERT INTO public.user_total (id, login, password, role_id, first_name, last_name) VALUES (1, 'admin', 'admin', 1, 'Andrey', 'Selivanov');
INSERT INTO public.user_total (id, login, password, role_id, first_name, last_name) VALUES (4, 'max', 'petrov', 2, 'Maxim', 'Petrov');
INSERT INTO public.user_total (id, login, password, role_id, first_name, last_name) VALUES (5, 'dima', 'korobov', 2, 'Dmitriy', 'Korobov');
INSERT INTO public.user_total (id, login, password, role_id, first_name, last_name) VALUES (6, 'nikita', 'sheba', 2, 'Nikita', 'Shebunyaev');
INSERT INTO public.user_total (id, login, password, role_id, first_name, last_name) VALUES (7, 'alex', 'samarin', 2, 'Alexander', 'Samarin');
INSERT INTO public.user_total (id, login, password, role_id, first_name, last_name) VALUES (8, 'pasha', 'lebed', 2, 'Pavel', 'Lebed');
INSERT INTO public.user_total (id, login, password, role_id, first_name, last_name) VALUES (9, 'volodya', 'sushkov', 2, 'Vladimir', 'Sushkov');
INSERT INTO public.user_total (id, login, password, role_id, first_name, last_name) VALUES (12, 'dima2', 'topornin', 2, 'Dmitriy', 'Topornin');
INSERT INTO public.user_total (id, login, password, role_id, first_name, last_name) VALUES (13, 'vanya', 'linder', 2, 'Ivan', 'Linder');
INSERT INTO public.user_total (id, login, password, role_id, first_name, last_name) VALUES (14, 'anton', 'pavliv', 2, 'Anton', 'Pavliv');
INSERT INTO public.user_total (id, login, password, role_id, first_name, last_name) VALUES (15, 'leha', 'plate', 2, 'Alexey', 'Plate');
INSERT INTO public.user_total (id, login, password, role_id, first_name, last_name) VALUES (16, 'leha2', 'gladkiy', 2, 'Alexey', 'Gladkiy');

INSERT INTO public.meeting (id, name, start_time, end_time, description) VALUES (1, 'Военная кафедра', '2015-04-18 09:50:04.000000', '2015-04-18 15:44:40.000000', 'Военная кафедра в ЛЭТИ, третий этаж, 1 корпус');
INSERT INTO public.meeting (id, name, start_time, end_time, description) VALUES (2, 'Гитарный вечер', '2015-04-19 18:45:00.000000', '2015-04-18 20:00:42.000000', 'В общежитии номер восемь состоится вечер гитарной песни');
INSERT INTO public.meeting (id, name, start_time, end_time, description) VALUES (3, '9 Мая. День Победы', '2015-05-09 00:00:00.000000', '2015-05-10 00:00:00.000000', null);
INSERT INTO public.meeting (id, name, start_time, end_time, description) VALUES (4, '1 Мая', '2015-05-01 00:00:00.000000', '2015-05-02 00:00:00.000000', 'День Весны и Труда');

INSERT INTO public.manager (id, meeting_id, user_id, description) VALUES (1, 1, 8, 'Командир взвода');
INSERT INTO public.manager (id, meeting_id, user_id, description) VALUES (2, 2, 8, 'Культорг');
INSERT INTO public.manager (id, meeting_id, user_id, description) VALUES (3, 4, 1, null);
INSERT INTO public.manager (id, meeting_id, user_id, description) VALUES (4, 3, 6, null);
INSERT INTO public.manager (id, meeting_id, user_id, description) VALUES (5, 1, 15, 'Командир отделения');

INSERT INTO public.participant (id, meeting_id, user_id) VALUES (1, 1, 1);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (3, 1, 12);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (5, 2, 1);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (7, 2, 16);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (8, 2, 5);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (10, 4, 16);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (11, 4, 14);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (12, 4, 13);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (13, 4, 4);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (14, 4, 9);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (17, 3, 1);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (19, 3, 4);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (20, 3, 5);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (22, 3, 7);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (23, 3, 8);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (24, 3, 9);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (25, 3, 12);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (26, 3, 13);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (27, 3, 14);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (28, 3, 15);
INSERT INTO public.participant (id, meeting_id, user_id) VALUES (29, 3, 16);