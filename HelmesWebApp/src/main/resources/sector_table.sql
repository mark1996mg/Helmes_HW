CREATE TABLE sectors(
	sectorId INTEGER NOT NULL,
	sectorName VARCHAR(255) NOT NULL,
	childSectors INTEGER[],
	CONSTRAINT pk_sector PRIMARY KEY (sectorId)
);