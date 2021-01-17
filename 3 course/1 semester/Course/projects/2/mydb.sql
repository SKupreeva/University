CREATE TABLE `user` (
	`iduser` INT(11) NOT NULL AUTO_INCREMENT,
	`login` varchar(45) NOT NULL,
	`password` varchar(45) NOT NULL,
	`status` INT(2) NOT NULL DEFAULT '0',
	PRIMARY KEY (`iduser`)
);

CREATE TABLE `employes` (
	`ideml` INT(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(45) NOT NULL,
	`experince` INT(2) NOT NULL DEFAULT '4',
	PRIMARY KEY (`ideml`)
);

CREATE TABLE `production` (
	`idprod` INT(11) NOT NULL AUTO_INCREMENT,
	`idempl` INT(11) NOT NULL,
	`id_pr` INT(11) NOT NULL,
	`percent_flaw` INT(3) NOT NULL,
	PRIMARY KEY (`idprod`)
);

CREATE TABLE `name_product` (
	`id_pr` INT(11) NOT NULL AUTO_INCREMENT,
	`name_product` varchar(45) NOT NULL,
	`price` INT(20) NOT NULL,
	PRIMARY KEY (`id_pr`)
);

ALTER TABLE `employes` ADD CONSTRAINT `employes_fk0` FOREIGN KEY (`ideml`) REFERENCES `user`(`iduser`);

ALTER TABLE `production` ADD CONSTRAINT `production_fk0` FOREIGN KEY (`idempl`) REFERENCES `employes`(`ideml`);

ALTER TABLE `production` ADD CONSTRAINT `production_fk1` FOREIGN KEY (`id_pr`) REFERENCES `name_product`(`id_pr`);

