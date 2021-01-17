create table if not exists user(
    IDUser int primary key,
    Surname varchar(20) not null,
    Name varchar(20) not null,
    Email varchar(20) not null,
    Phone varchar(20) not null,
    Login varchar(20) not null,
    Password varchar(20) not null
);

create table if not exists Report(
    IDReport int primary key,
    Date varchar(20) not null,
    IDUSD int  not null,
    X1 double not null,
    X2 double not null,
    X3 double not null,
    X4 double not null,
    X5 double not null,
    X6 double not null,
    X7 double not null,
    X8 double not null,
    X9 double not null,
    H double not null,
    IDCompany int  not null,
    foreign key(IDUSD) references USD(IDUSD)
);

create table if not exists Company(
     IDCompany int primary key,
     Name varchar(20) not null,
     Address varchar(20) not null
);

create table if not exists USD(
     BYNUSD int not null,
     EURUSD int not null,
     RUBUSD int not null,
     IDUSD int primary key,
     Date varchar(20) not null
);

create table if not exists UserCompany(
    IDUser int not null,
    IDCompany int not null,
    foreign key(IDUser) references User(IDUser),
    foreign key(IDCompany) references Company(IDCompany)
);

create table if not exists UserReport(
    IDUser int not null,
    IDReport int not null,
    foreign key(IDUser) references User(IDUser),
    foreign key(IDReport) references Report(IDReport)
);