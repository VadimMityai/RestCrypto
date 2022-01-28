use crypto;

CREATE TABLE user (
                      id  INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(45),
                      price double,
                      crypto_id int);
CREATE TABLE Crypto (
                             id  INT NOT NULL PRIMARY KEY,
                             symbol VARCHAR(45),
                             price double);

insert into Crypto(id, symbol) values (90, 'BTC'), (80, 'ETH'), (48543, 'SOL');
select * from user;