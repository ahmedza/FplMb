CREATE TABLE aftn_msg (
	ID INT,
	RAW_MSG VARCHAR(255),
	ORIGIN VARCHAR(8),
	PRIORITY VARCHAR(4),
	FILING_TIME TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP(6)' NOT NULL,
	CALL_SIGN VARCHAR(5),
	SSR VARCHAR(4),
	RULES VARCHAR(2),
	FLIGHT_TYPE VARCHAR(1),
	NO_OF_AC INT,
	AC_TYPE VARCHAR(4),
	WTC VARCHAR(1),
	EQUIPMENT VARCHAR(10),
	ADEP VARCHAR(8),
	EOBT VARCHAR(8),
	ADES VARCHAR(8),
	ALTN_1 VARCHAR(8),
	ALTN_2 VARCHAR(8),
	ROUTE VARCHAR(2500)
);

