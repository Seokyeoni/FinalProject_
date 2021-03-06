DROP TEMPORARY TABLE IF EXISTS EXCHANGES;
DROP TEMPORARY TABLE IF EXISTS SECTORS;
DROP TEMPORARY TABLE IF EXISTS INDUSTRIES;
DROP TEMPORARY TABLE IF EXISTS TEST_STOCKS_LISTING;
DROP TEMPORARY TABLE IF EXISTS TEST_STOCKS_PRICES;

CREATE TABLE EXCHANGES (
	Exchange VARCHAR (100) NOT NULL,
	Nation VARCHAR (100) NOT NULL,
	Acronym VARCHAR (100) NOT NULL,
	Name VARCHAR (100) NOT NULL,
	Currency VARCHAR (100) NOT NULL,
	WebSite VARCHAR (100) NOT NULL
);

CREATE TABLE SECTORS (
	Code VARCHAR (100) NOT NULL,
	Sector VARCHAR (100) NOT NULL,
	Sector_ENG VARCHAR (100) NOT NULL,
	PRIMARY KEY (Code)
);

CREATE TABLE INDUSTRIES (
	Industry VARCHAR (100) NOT NULL,
	Sector VARCHAR (100) NOT NULL,
	Sector_code VARCHAR (100) NOT NULL,
	PRIMARY KEY (Industry)
);

CREATE TABLE TEST_STOCKS_LISTING (
	Name VARCHAR(100) NOT NULL,
	Symbol VARCHAR(100) NOT NULL,
	Exchange VARCHAR(100) NOT NULL,
	Code VARCHAR (100) NOT NULL,
	Sector VARCHAR (100) NOT NULL,
	Industry VARCHAR (100) NOT NULL,
	Listing_status VARCHAR (100) NOT NULL,
	Delisting_date VARCHAR (100,
	New_listing_date VARCHAR (100),	
	FOREIGN KEY (Code) REFERENCES SECTORS (Code)
);

CREATE TABLE TEST_STOCKS_PRICES (
	Name VARCHAR(100) NOT NULL,
	Symbol VARCHAR(100) NOT NULL,
	Exchange VARCHAR(100) NOT NULL,
	Code VARCHAR (100) NOT NULL,
	Sector VARCHAR (100) NOT NULL,
	Industry VARCHAR (100) NOT NULL,
	Date VARCHAR(100) NOT NULL,
	Currency VARCHAR(50) NOT NULL,
	Open DOUBLE NOT NULL,
	High DOUBLE NOT NULL,
	Low DOUBLE NOT NULL,
	Close DOUBLE NOT NULL,
	Rtn DOUBLE NOT NULL,
	Volume DOUBLE NOT NULL,
	FOREIGN KEY (Code) REFERENCES SECTORS (Code)
);