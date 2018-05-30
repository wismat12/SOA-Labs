CREATE TABLE zawodnik (
    id INT NOT NULL AUTO_INCREMENT,
    imie CHAR(20) NOT NULL,
    nazwisko CHAR(30) NOT NULL,
    punkty INT NOT NULL,
    narodowosc CHAR(3) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO zawodnik (imie, nazwisko, punkty, narodowosc)
VALUES
('Herman', 'Maier', 1265, 'AUT'),
('Stefan', 'Eberharter', 1223, 'AUT'),
('Benjamin', 'Raich', 1113, 'AUT'),
('Bode', 'Miller', 1098, 'USA'),
('Daron', 'Rahlves', 1004, 'USA'),
('Kalle', 'Pallander', 844, 'FIN'),
('Michael', 'Walchhofer', 828, 'AUT'),
('Lasse', 'Kjus', 824, 'NOR'),
('Hans', 'Knauss', 796, 'AUT'),
('Bjarne', 'Solbakken', 696, 'NOR');