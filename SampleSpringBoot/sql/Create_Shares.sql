CREATE TABLE `conygre`.`shares` (
                                    `ID` INT NOT NULL AUTO_INCREMENT,
                                    `Symbol` VARCHAR(45) NOT NULL,
                                    `Volume` INT NOT NULL,
                                    `transaction_price` DOUBLE NOT NULL,
                                    `transaction_type` VARCHAR(45) NOT NULL,
                                    `transaction_date` DATE NOT NULL,
                                    PRIMARY KEY (`ID`));
INSERT INTO `conygre`.`shares` (`Symbol`, `Volume`, `transaction_price`, `transaction_type`, transaction_date) VALUES ('FB', '10', '340.2', 'buy', '2020-06-24');
INSERT INTO `conygre`.`shares` (`Symbol`, `Volume`, `transaction_price`, `transaction_type`, transaction_date)  VALUES ('AAPL', '20', '110.14', 'buy', '2021-06-24');
INSERT INTO `conygre`.`shares` (`Symbol`, `Volume`, `transaction_price`, `transaction_type`, transaction_date)  VALUES ('AMZN', '1', '3100.1', 'buy', '2021-06-30');
INSERT INTO `conygre`.`shares` (`Symbol`, `Volume`, `transaction_price`, `transaction_type`, transaction_date)  VALUES ('NFLX', '15', '483.44',  'buy', '2020-08-01');
INSERT INTO `conygre`.`shares` (`Symbol`, `Volume`, `transaction_price`, `transaction_type`, transaction_date)  VALUES ('GOOGL', '2', '2685.35', 'buy', '2021-07-24');
INSERT INTO `conygre`.`shares` (`ID`, `Symbol`, `Volume`, `transaction_price`, `transaction_type`, `transaction_date`) VALUES ('6', 'FB', '2', '342', 'sell', '2021-07-10');







