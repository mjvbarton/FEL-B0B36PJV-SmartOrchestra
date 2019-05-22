/* User */
DROP TABLE IF EXISTS Users CASCADE;
CREATE TABLE Users (
	Uid 				SERIAL PRIMARY KEY,
	Email 				VARCHAR(255) UNIQUE NOT NULL,
	Passwd				VARCHAR(255) NOT NULL,
	BirthDate   		DATE NOT NULL,
	FirstName			VARCHAR(255) NOT NULL,
	FamilyName			VARCHAR(255) NOT NULL,
	Phone				VARCHAR(32) NOT NULL,
	AddrStreet			VARCHAR(64) NULL,
	AddrHouseNumber	    VARCHAR(32) NULL,
	AddrTown			VARCHAR(255) NULL,
	AddrZipCode			INT NULL CHECK (AddrZipCode >= 10000 AND AddrZipCode <= 999999),
	Active				BOOLEAN DEFAULT True
);

/* Administrator */
DROP TABLE IF EXISTS Administrators CASCADE;
CREATE TABLE Administrators (
    Uid     INT PRIMARY KEY REFERENCES Users(Uid) ON UPDATE RESTRICT ON DELETE CASCADE
);

/* Section type */
DROP TYPE IF EXISTS SECTION_TYPE CASCADE;
CREATE TYPE SECTION_TYPE AS ENUM('STRINGS', 'WINDS', 'OTHER');

/* Section */
DROP TABLE IF EXISTS Sections CASCADE;
CREATE TABLE Sections(
	SeId			SERIAL PRIMARY KEY,
	SectionName		VARCHAR(64) UNIQUE NOT NULL,
	SectionType 	SECTION_TYPE NOT NULL,
	Active			BOOLEAN DEFAULT True NOT NULL
);

/* Player */
DROP TABLE IF EXISTS Player CASCADE;
CREATE TABLE Player (
    Uid                 INT PRIMARY KEY REFERENCES Users(Uid) ON UPDATE RESTRICT ON DELETE CASCADE,
    Seid                INT NOT NULL REFERENCES Sections(Seid) ON UPDATE RESTRICT ON DELETE CASCADE,
    ConcertMaster       BOOLEAN NULL,

    CONSTRAINT IC_PlayerSection UNIQUE (Uid, Seid),
	CONSTRAINT IC_ConcertMaster UNIQUE (Seid, ConcertMaster)
    
);

/* Event */
DROP TABLE IF EXISTS Events CASCADE;
CREATE TABLE Events(
	EvId				SERIAL PRIMARY KEY,
	EventName			VARCHAR(255) NOT NULL,
	Begins				TIMESTAMP NOT NULL,
	Ends				TIMESTAMP NOT NULL, 
	AddrInstitution		VARCHAR(255) NULL,
	AddrStreet			VARCHAR(64) NULL,
	AddrHouseNumber		VARCHAR(32) NULL,
	AddrTown			VARCHAR(255) NOT NULL,
	AddrZipCode			INT NULL CHECK (AddrZipCode >= 10000 AND AddrZipCode <= 999999),
	Active				BOOLEAN	DEFAULT True,
	
	CONSTRAINT IC_SearchIndex UNIQUE (EventName, Begins)
);

/* Participant */
DROP TABLE IF EXISTS Participants CASCADE;
CREATE TABLE Participants(
	Uid				INT REFERENCES Users(Uid) ON UPDATE RESTRICT ON DELETE CASCADE,
	SeId			INT REFERENCES Sections(Seid) ON UPDATE RESTRICT ON DELETE CASCADE NOT NULL,
	EvId			INT REFERENCES Events(EvId) ON UPDATE RESTRICT ON DELETE CASCADE,
	Message			VARCHAR(255) NULL,
	Active			BOOLEAN NULL,

	CONSTRAINT	IC_SingleSectionParticipation UNIQUE (Uid, SeId, EvId),
	CONSTRAINT  IC_Participant_PK PRIMARY KEY (Uid, EvId)
);

/* Default Configuration */
WITH rows AS (INSERT INTO Users(Email, Passwd, FirstName, FamilyName, BirthDate, Phone) 
			  VALUES ('admin@smartorchestra.net', '$2a$12$S3CxfM6NX3QQBg3K4jPOc.IWVIs7UtAIzi3cPIxMpqgAlVw5.rugm',
					  'Super', 'Administrator', CURRENT_DATE, '+420602123123') RETURNING Uid)
	INSERT INTO Administrators(Uid) VALUES ((SELECT Uid FROM rows));	