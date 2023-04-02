CREATE TABLE Employee (
    employeeid int NOT NULL AUTO_INCREMENT,
    lastname varchar(255) ,
    firstname varchar(255),
    age int,
    villeid int,
    PRIMARY KEY (employeeid),
    FOREIGN KEY (villeid) REFERENCES Ville(villeid)
);