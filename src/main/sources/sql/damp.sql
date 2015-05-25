DROP TABLE IF EXISTS participant;
DROP TABLE IF EXISTS manager;
DROP TABLE IF EXISTS meeting;
DROP TABLE IF EXISTS user_total;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS log;

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
  last_name  VARCHAR(40)        NOT NULL
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

CREATE TABLE log(
  message TEXT,
  creation_time TIMESTAMP WITHOUT TIME ZONE
);

create or replace function add_log_message_for_meetigns()
  returns trigger as $$
declare
	mstr varchar(30);
	astr varchar(100);
	retstr varchar(254);
begin
	if TG_OP = 'INSERT' then
		astr = NEW.name;
		mstr := 'Add new meeting: ';
		retstr := mstr || astr;
		INSERT INTO log(message, creation_time) values (retstr, NOW());
		return NEW;
	elsif TG_OP = 'UPDATE' then
		astr = NEW.name;
		mstr := 'Update meeting: ';
		retstr := mstr || astr;
		INSERT INTO log(message, creation_time) values (retstr, NOW());
		return NEW;
	elsif TG_OP = 'DELETE' then
		astr = OLD.name;
		mstr := 'Remove meeting: ';
		retstr := mstr || astr;
		INSERT INTO log(message, creation_time) values (retstr, NOW());
		return OLD;
	end if;
end;
$$ language plpgsql;

create trigger meetings_logger
after insert or update or delete on meeting
for each row execute procedure
  add_log_message_for_meetigns();

create or replace function add_log_message_for_users()
  returns trigger as $$
declare
	mstr varchar(30);
	astr varchar(100);
	retstr varchar(254);
begin
	if TG_OP = 'INSERT' then
		astr = NEW.login;
		mstr := 'Add new user: ';
		retstr := mstr || astr;
		INSERT INTO log(message, creation_time) values (retstr, NOW());
		return NEW;
	elsif TG_OP = 'UPDATE' then
		astr = NEW.login;
		mstr := 'Update user: ';
		retstr := mstr || astr;
		INSERT INTO log(message, creation_time) values (retstr, NOW());
		return NEW;
	elsif TG_OP = 'DELETE' then
		astr = OLD.login;
		mstr := 'Remove user: ';
		retstr := mstr || astr;
		INSERT INTO log(message, creation_time) values (retstr, NOW());
		return OLD;
	end if;
end;
$$ language plpgsql;

create trigger users_logger
after insert or update or delete on user_total
for each row execute procedure
  add_log_message_for_users();

INSERT INTO public.role (role_name) VALUES ('admin');
INSERT INTO public.role (role_name) VALUES ('user');

INSERT INTO public.user_total (login, password, role_id, first_name, last_name)
VALUES ('admin', 'admin', 1, 'Andrey', 'Selivanov');
INSERT INTO public.user_total (login, password, role_id, first_name, last_name)
VALUES ('max', 'petrov', 2, 'Maxim', 'Petrov');
INSERT INTO public.user_total (login, password, role_id, first_name, last_name)
VALUES ('dima', 'korobov', 2, 'Dmitriy', 'Korobov');
INSERT INTO public.user_total (login, password, role_id, first_name, last_name)
VALUES ('nikita', 'sheba', 2, 'Nikita', 'Shebunyaev');
INSERT INTO public.user_total (login, password, role_id, first_name, last_name)
VALUES ('alex', 'samarin', 2, 'Alexander', 'Samarin');
INSERT INTO public.user_total (login, password, role_id, first_name, last_name)
VALUES ('pasha', 'lebed', 2, 'Pavel', 'Lebed');
INSERT INTO public.user_total (login, password, role_id, first_name, last_name)
VALUES ('volodya', 'sushkov', 2, 'Vladimir', 'Sushkov');
INSERT INTO public.user_total (login, password, role_id, first_name, last_name)
VALUES ('dima2', 'topornin', 2, 'Dmitriy', 'Topornin');
INSERT INTO public.user_total (login, password, role_id, first_name, last_name)
VALUES ('vanya', 'linder', 2, 'Ivan', 'Linder');
INSERT INTO public.user_total (login, password, role_id, first_name, last_name)
VALUES ('anton', 'pavliv', 2, 'Anton', 'Pavliv');
INSERT INTO public.user_total (login, password, role_id, first_name, last_name)
VALUES ('leha', 'plate', 2, 'Alexey', 'Plate');
INSERT INTO public.user_total (login, password, role_id, first_name, last_name)
VALUES ('leha2', 'gladkiy', 2, 'Alexey', 'Gladkiy');

INSERT INTO public.meeting (name, start_time, end_time, description) VALUES
  ('Военная кафедра', '2015-04-18 09:50:04.000000', '2015-04-18 15:44:40.000000',
   'Военная кафедра в ЛЭТИ, третий этаж, 1 корпус');
INSERT INTO public.meeting (name, start_time, end_time, description) VALUES
  ('Гитарный вечер', '2015-04-19 18:45:00.000000', '2015-04-18 20:00:42.000000',
   'В общежитии номер восемь состоится вечер гитарной песни');
INSERT INTO public.meeting (name, start_time, end_time, description)
VALUES ('9 Мая. День Победы', '2015-05-09 00:00:00.000000', '2015-05-10 00:00:00.000000', NULL);
INSERT INTO public.meeting (name, start_time, end_time, description)
VALUES ('1 Мая', '2015-05-01 00:00:00.000000', '2015-05-02 00:00:00.000000', 'День Весны и Труда');

INSERT INTO public.manager (meeting_id, user_id, description) VALUES (1, 6, 'Командир взвода');
INSERT INTO public.manager (meeting_id, user_id, description) VALUES (2, 6, 'Культорг');
INSERT INTO public.manager (meeting_id, user_id, description) VALUES (4, 1, NULL);
INSERT INTO public.manager (meeting_id, user_id, description) VALUES (3, 4, NULL);
INSERT INTO public.manager (meeting_id, user_id, description) VALUES (1, 11, 'Командир отделения');

INSERT INTO public.participant (meeting_id, user_id) VALUES (1, 1);
INSERT INTO public.participant (meeting_id, user_id) VALUES (1, 8);
INSERT INTO public.participant (meeting_id, user_id) VALUES (2, 1);
INSERT INTO public.participant (meeting_id, user_id) VALUES (2, 12);
INSERT INTO public.participant (meeting_id, user_id) VALUES (2, 3);
INSERT INTO public.participant (meeting_id, user_id) VALUES (4, 12);
INSERT INTO public.participant (meeting_id, user_id) VALUES (4, 10);
INSERT INTO public.participant (meeting_id, user_id) VALUES (4, 9);
INSERT INTO public.participant (meeting_id, user_id) VALUES (4, 2);
INSERT INTO public.participant (meeting_id, user_id) VALUES (4, 7);
INSERT INTO public.participant (meeting_id, user_id) VALUES (3, 1);
INSERT INTO public.participant (meeting_id, user_id) VALUES (3, 2);
INSERT INTO public.participant (meeting_id, user_id) VALUES (3, 3);
INSERT INTO public.participant (meeting_id, user_id) VALUES (3, 5);
INSERT INTO public.participant (meeting_id, user_id) VALUES (3, 6);
INSERT INTO public.participant (meeting_id, user_id) VALUES (3, 7);
INSERT INTO public.participant (meeting_id, user_id) VALUES (3, 8);
INSERT INTO public.participant (meeting_id, user_id) VALUES (3, 9);
INSERT INTO public.participant (meeting_id, user_id) VALUES (3, 10);
INSERT INTO public.participant (meeting_id, user_id) VALUES (3, 11);
INSERT INTO public.participant (meeting_id, user_id) VALUES (3, 12);