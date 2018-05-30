drop table autor;
drop table ksiazka;
drop table czytelnik;
drop table wypozyczenie;

begin;
CREATE TABLE autor (
	id_autor INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    imie VARCHAR(20) NOT NULL,
    nazwisko VARCHAR(30) NOT NULL
);

CREATE TABLE ksiazka (
	id_ksiazka INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	tytul VARCHAR(20) NOT NULL,
    id_autor INT NOT NULL REFERENCES autor
);

CREATE TABLE czytelnik (
	id_czytelnik INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    imie VARCHAR(20) NOT NULL,
    nazwisko VARCHAR(30) NOT NULL
);

CREATE TABLE wypozyczenie (
	id_wypozyczenie INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_czytelnik INT NOT NULL REFERENCES czytelnik,
    id_ksiazka INT NOT NULL REFERENCES ksiazka,
    data_wypozyczenia DATE NOT NULL,
    data_zwrotu DATE
);

insert into autor values
	(1,'ImieAAA', 'NazwiskoAAA'),
	(2,'ImieBBB', 'NazwiskoBBB'),
	(3,'ImieCCC', 'NazwiskoCCC'),
	(4,'ImieDDD', 'NazwiskoDDD'),
	(5,'ImieEEE', 'NazwiskoEEE'),
	(6,'ImieFFF', 'NazwiskoFFF');

insert into czytelnik values
	(1,'ImieGGG', 'NazwiskoGGG'),
	(2,'ImieHHH', 'NazwiskoHHH'),
	(3,'ImieIII', 'NazwiskoIII'),
	(4,'ImieJJJ', 'NazwiskoJJJ'),
	(5,'ImieKKK', 'NazwiskoKKK'),
	(6,'ImieLLL', 'NazwiskoLLL');

insert into ksiazka(tytul,id_ksiazka,id_autor) values
	('Tytul1',1,1),
	('Tytul2',2,1),
	('Tytul3',3,2),
	('Tytul4',4,2),
	('Tytul5',5,3),
	('Tytul6',6,3),
	('Tytul7',7,4),
	('Tytul8',8,4),
	('Tytul9',9,5),
	('Tytul10',10,5),
	('Tytul11',11,6),
	('Tytul12',12,6);

insert into wypozyczenie values
	(1,1,5,'2017-05-27','2017-07-11'),
	(2,1,7,'2017-03-11','2017-03-22'),
	(3,1,1,'2017-09-27','2017-12-30'),
	(4,2,2,'2015-09-27','2015-12-11'),
	(5,5,12,'2018-01-11',null),
	(6,6,1,'2018-03-16',null),
	(7,3,1,'2018-01-11','2018-03-15');

commit;



