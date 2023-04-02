CREATE TABLE Ville (
    villeid int NOT NULL AUTO_INCREMENT,
    name varchar(255) ,
    paysname varchar(255) ,
    paysid int,
    PRIMARY KEY (villeid),
    FOREIGN KEY (paysid) REFERENCES Pays(paysid)
);