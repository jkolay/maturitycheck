
CREATE TABLE MORTAGE_RATE (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `interest_rate` float NOT NULL,
                          `maturity_period` float NOT NULL,

                           `last_update` TIMESTAMP(6) NOT NULL,
                           PRIMARY KEY (`id`)

);