use conygre;
drop table if exists shares;

CREATE TABLE `conygre`.`shares` (
                                    `Symbol` VARCHAR(45) NOT NULL,
                                    `Volume` INT NULL,
                                    `Purchased_price` DOUBLE NULL,
                                    `Current_price` DOUBLE NULL,
                                    PRIMARY KEY (`Symbol`));

INSERT INTO `conygre`.`shares` (`Symbol`, `Volume`, `Purchased_price`, `Current_price`) VALUES ('FB', '10', '340.2', '362.75');
INSERT INTO `conygre`.`shares` (`Symbol`, `Volume`, `Purchased_price`, `Current_price`) VALUES ('AAPL', '20', '110.14', '148.37');
INSERT INTO `conygre`.`shares` (`Symbol`, `Volume`, `Purchased_price`, `Current_price`) VALUES ('AMZN', '1', '3100.1', '3284.77');
INSERT INTO `conygre`.`shares` (`Symbol`, `Volume`, `Purchased_price`, `Current_price`) VALUES ('NFLX', '15', '483.44', '517.33');
INSERT INTO `conygre`.`shares` (`Symbol`, `Volume`, `Purchased_price`, `Current_price`) VALUES ('GOOGL', '2', '2685.35', '2749.27');

