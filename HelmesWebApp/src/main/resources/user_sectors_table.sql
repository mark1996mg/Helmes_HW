CREATE TABLE user_sectors(
  userId VARCHAR(64) NOT NULL,
  sectorId INTEGER NOT NULL,
  CONSTRAINT pk_user_sectors PRIMARY KEY (userId, sectorId),
  CONSTRAINT fk_user_sectors_user_id FOREIGN KEY (userId) REFERENCES users (userId) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_user_sectors_sector_id FOREIGN KEY (sectorId) REFERENCES sectors (sectorId) ON UPDATE CASCADE ON DELETE NO ACTION
);