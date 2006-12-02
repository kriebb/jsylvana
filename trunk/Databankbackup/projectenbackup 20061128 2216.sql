-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.18-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO,ANSI_QUOTES' */;


--
-- Create schema projecten
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ projecten;
USE projecten;

--
-- Table structure for table "projecten"."docent"
--

DROP TABLE IF EXISTS "docent";
CREATE TABLE "docent" (
  "docentID" varchar(5) NOT NULL,
  "naam" varchar(50) NOT NULL,
  "voornaam" varchar(50) NOT NULL,
  "email" varchar(50) NOT NULL,
  "admin" bit(1) NOT NULL,
  "paswoord" char(10) default NULL,
  PRIMARY KEY  ("docentID")
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table "projecten"."docent"
--

/*!40000 ALTER TABLE "docent" DISABLE KEYS */;
/*!40000 ALTER TABLE "docent" ENABLE KEYS */;


--
-- Table structure for table "projecten"."docentindocentteam"
--

DROP TABLE IF EXISTS "docentindocentteam";
CREATE TABLE "docentindocentteam" (
  "docentTeamID" int(11) NOT NULL,
  "projectluikID" int(11) NOT NULL,
  "docentID" varchar(5) NOT NULL,
  PRIMARY KEY  ("docentTeamID","projectluikID"),
  KEY "projectluikID" ("projectluikID")
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table "projecten"."docentindocentteam"
--

/*!40000 ALTER TABLE "docentindocentteam" DISABLE KEYS */;
INSERT INTO "docentindocentteam" ("docentTeamID","projectluikID","docentID") VALUES 
 (1,8,''),
 (1,9,''),
 (1,10,'');
/*!40000 ALTER TABLE "docentindocentteam" ENABLE KEYS */;


--
-- Table structure for table "projecten"."docentteam"
--

DROP TABLE IF EXISTS "docentteam";
CREATE TABLE "docentteam" (
  "docentTeamID" int(11) NOT NULL,
  "opgaveID" int(11) NOT NULL,
  PRIMARY KEY  ("docentTeamID"),
  KEY "opgaveID" ("opgaveID")
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table "projecten"."docentteam"
--

/*!40000 ALTER TABLE "docentteam" DISABLE KEYS */;
INSERT INTO "docentteam" ("docentTeamID","opgaveID") VALUES 
 (1,1),
 (0,28);
/*!40000 ALTER TABLE "docentteam" ENABLE KEYS */;


--
-- Table structure for table "projecten"."project"
--

DROP TABLE IF EXISTS "project";
CREATE TABLE "project" (
  "projectID" int(11) NOT NULL,
  "projectTitel" varchar(50) NOT NULL,
  "studiejaar" smallint(6) NOT NULL,
  "inschrijvingVan" datetime default NULL,
  "inschrijvingTot" datetime default NULL,
  PRIMARY KEY  ("projectID")
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table "projecten"."project"
--

/*!40000 ALTER TABLE "project" DISABLE KEYS */;
INSERT INTO "project" ("projectID","projectTitel","studiejaar","inschrijvingVan","inschrijvingTot") VALUES 
 (4,'DynWeb',2,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
 (5,'SQL',2,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
 (6,'Netwerken',2,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
 (7,'Datacommunicatie',3,'0000-00-00 00:00:00','0000-00-00 00:00:00');
/*!40000 ALTER TABLE "project" ENABLE KEYS */;


--
-- Table structure for table "projecten"."projectluik"
--

DROP TABLE IF EXISTS "projectluik";
CREATE TABLE "projectluik" (
  "luikID" int(11) NOT NULL,
  "luikTitel" varchar(50) NOT NULL,
  "projectID" int(11) NOT NULL,
  "percentage" int(11) default NULL,
  PRIMARY KEY  ("luikID"),
  KEY "projectID" ("projectID")
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table "projecten"."projectluik"
--

/*!40000 ALTER TABLE "projectluik" DISABLE KEYS */;
INSERT INTO "projectluik" ("luikID","luikTitel","projectID","percentage") VALUES 
 (8,'Analyse',4,30),
 (9,'Programmatie',4,50),
 (10,'Presentatie',4,20),
 (11,'Analyse',5,30),
 (12,'Programmatie',5,50),
 (13,'Presentatie',5,20),
 (14,'Analyse',6,30),
 (15,'Programmatie',6,50),
 (16,'Presentatie',6,20),
 (17,'Presentatie',7,20);
/*!40000 ALTER TABLE "projectluik" ENABLE KEYS */;


--
-- Table structure for table "projecten"."projectopgave"
--

DROP TABLE IF EXISTS "projectopgave";
CREATE TABLE "projectopgave" (
  "opgaveID" int(11) NOT NULL auto_increment,
  "opgaveTitel" varchar(50) NOT NULL,
  "korteOmschrijving" varchar(255) default NULL,
  "aantalstudentenPerGroep" smallint(6) default NULL,
  "aantalGroepen" smallint(6) default NULL,
  "projectID" int(11) NOT NULL,
  PRIMARY KEY  ("opgaveID"),
  UNIQUE KEY "opgaveID" ("opgaveID"),
  KEY "projectID" ("projectID")
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table "projecten"."projectopgave"
--

/*!40000 ALTER TABLE "projectopgave" DISABLE KEYS */;
INSERT INTO "projectopgave" ("opgaveID","opgaveTitel","korteOmschrijving","aantalstudentenPerGroep","aantalGroepen","projectID") VALUES 
 (1,'titeltjeUpdatejen',NULL,0,0,4),
 (2,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (3,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (4,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (5,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (6,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (7,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (8,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (9,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (10,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (11,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (12,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (13,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (14,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (15,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (16,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (17,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (18,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (19,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (20,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (21,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (22,'titeltjeUpdatejenNIEUW',NULL,0,0,4);
INSERT INTO "projectopgave" ("opgaveID","opgaveTitel","korteOmschrijving","aantalstudentenPerGroep","aantalGroepen","projectID") VALUES 
 (23,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (24,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (25,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (26,'titeltjeUpdatejenNIEUW',NULL,0,0,4),
 (28,'titeltjeUpdatejenNIEUW',NULL,0,0,4);
/*!40000 ALTER TABLE "projectopgave" ENABLE KEYS */;


--
-- Table structure for table "projecten"."student"
--

DROP TABLE IF EXISTS "student";
CREATE TABLE "student" (
  "studentNr" varchar(8) NOT NULL,
  "naam" varchar(50) NOT NULL,
  "voornaam" varchar(50) NOT NULL,
  "email" varchar(50) NOT NULL,
  "paswoord" varchar(10) NOT NULL,
  "studiejaar" smallint(6) NOT NULL,
  "geindividualiseerdStudietraject" tinyint(1) NOT NULL default '0',
  PRIMARY KEY  ("studentNr")
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table "projecten"."student"
--

/*!40000 ALTER TABLE "student" DISABLE KEYS */;
INSERT INTO "student" ("studentNr","naam","voornaam","email","paswoord","studiejaar","geindividualiseerdStudietraject") VALUES 
 ('20060001','student1','student1','student1@student.hogent.be','student1',2,0),
 ('20060002','student2','student2','student2@student.hogent.be','student2',2,0),
 ('20060003','student3','student3','student3@student.hogent.be','student3',2,1),
 ('20060004','student4','student4','student4@student.hogent.be','student4',2,1),
 ('20060005','student5','student5','student5@student.hogent.be','student5',3,1),
 ('20060006','student6','student6','student6@student.hogent.be','student6',3,0),
 ('20060007','student7','student7','student7@student.hogent.be','student7',2,0),
 ('20060008','student8','student8','student8@student.hogent.be','student8',2,0),
 ('20060009','student9','student9','student9@student.hogent.be','student9',2,0),
 ('20060010','student10','student10','student10@student.hogent.be','student10',2,0),
 ('20060011','student11','student11','student11@student.hogent.be','student11',2,0),
 ('20060012','student12','student12','student12@student.hogent.be','student12',2,0);
INSERT INTO "student" ("studentNr","naam","voornaam","email","paswoord","studiejaar","geindividualiseerdStudietraject") VALUES 
 ('20060013','student13','student13','student13@student.hogent.be','student13',2,0),
 ('20060014','student14','student14','student14@student.hogent.be','student14',2,0),
 ('20060015','student15','student15','student15@student.hogent.be','student15',2,0),
 ('20060016','student16','student16','student16@student.hogent.be','student16',2,0),
 ('20060017','student17','student17','student17@student.hogent.be','student17',2,0),
 ('20060018','student18','student18','student18@student.hogent.be','student18',2,0);
/*!40000 ALTER TABLE "student" ENABLE KEYS */;


--
-- Table structure for table "projecten"."studentgroep"
--

DROP TABLE IF EXISTS "studentgroep";
CREATE TABLE "studentgroep" (
  "studentgroepID" int(11) NOT NULL,
  "opgaveID" int(11) NOT NULL,
  "docentTeamID" int(11) default NULL,
  "inschrijvingsDatum" datetime NOT NULL,
  PRIMARY KEY  ("studentgroepID"),
  KEY "docentTeamID" ("docentTeamID")
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table "projecten"."studentgroep"
--

/*!40000 ALTER TABLE "studentgroep" DISABLE KEYS */;
/*!40000 ALTER TABLE "studentgroep" ENABLE KEYS */;


--
-- Table structure for table "projecten"."studentinstudentgroep"
--

DROP TABLE IF EXISTS "studentinstudentgroep";
CREATE TABLE "studentinstudentgroep" (
  "studentgroepID" int(11) NOT NULL,
  "studentNr" varchar(8) NOT NULL,
  PRIMARY KEY  ("studentgroepID","studentNr"),
  KEY "studentNr" ("studentNr")
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table "projecten"."studentinstudentgroep"
--

/*!40000 ALTER TABLE "studentinstudentgroep" DISABLE KEYS */;
/*!40000 ALTER TABLE "studentinstudentgroep" ENABLE KEYS */;


--
-- Table structure for table "projecten"."studietraject"
--

DROP TABLE IF EXISTS "studietraject";
CREATE TABLE "studietraject" (
  "studentNr" varchar(8) NOT NULL,
  "projectID" int(11) NOT NULL,
  PRIMARY KEY  ("studentNr","projectID"),
  KEY "studentNr" ("studentNr")
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table "projecten"."studietraject"
--

/*!40000 ALTER TABLE "studietraject" DISABLE KEYS */;
INSERT INTO "studietraject" ("studentNr","projectID") VALUES 
 ('20060003',4),
 ('20060003',5),
 ('20060003',7),
 ('20060004',7),
 ('20060005',4),
 ('20060005',7);
/*!40000 ALTER TABLE "studietraject" ENABLE KEYS */;


--
-- Procedure "projecten"."usp_DocentInDocentteam_Select_By_DocentteamidEnProjectid"
--

DROP PROCEDURE IF EXISTS "usp_DocentInDocentteam_Select_By_DocentteamidEnProjectid";
DELIMITER $$

CREATE PROCEDURE "usp_DocentInDocentteam_Select_By_DocentteamidEnProjectid"(
            p_docentteamid int ,
            p_projectid int )
BEGIN
  DECLARE projectluikid int;
  DECLARE fetch_status INTEGER default 0;
  DECLARE projectluikeninserter cursor for
  SELECT pl.luikid
  FROM projectluik pl
  WHERE pl.projectid= p_projectid;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET fetch_status = 100;
    START TRANSACTION;
    BEGIN
      OPEN projectluikeninserter;
      FETCH next
        FROM projectluikeninserter INTO projectluikid;
      WHILE FETCH_STATUS= 0 DO
        IF not exists(
          SELECT *
          FROM docentindocentteam didt
          WHERE didt.projectluikid= projectluikid AND didt.docentteamid= 
            p_docentteamid) THEN
          INSERT docentindocentteam( docentteamid, projectluikid) values( p_docentteamid,
             projectluikid);
        END IF;
        FETCH NEXT
          FROM projectluikeninserter INTO projectluikid;
      END WHILE;
    END;
    COMMIT;
    SELECT *
      FROM DocentInDocentTeam didt
      WHERE didt.docentteamid= p_docentteamid;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_DocentInDocentteam_Update"
--

DROP PROCEDURE IF EXISTS "usp_DocentInDocentteam_Update";
DELIMITER $$

CREATE PROCEDURE "usp_DocentInDocentteam_Update"(
            p_docentteamid int,
            p_projectluikid int,
            p_docentid VARCHAR(5))
BEGIN


  START transaction;


  IF exists(


    SELECT *


    FROM docentindocentteam


    WHERE projectluikid= projectluikid AND docentteamid= docentteamid) THEN


    IF docentid= '-1' THEN


      UPDATE docentindocentteam


        SET docentid = null;


    ELSE


      UPDATE docentindocentteam


        SET docentid= p_docentid


        WHERE docentteamid= p_docentteamid AND projectluikid= p_projectluikid;


    END IF;


  ELSE


    INSERT docentindocentteam( docentteamid, projectluikid, docentid) values( p_docentteamid,


       p_projectluikid, p_docentid);


  END IF;


  COMMIT;


END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_DocentInTeam_SelectByLuikAndTeam"
--

DROP PROCEDURE IF EXISTS "usp_DocentInTeam_SelectByLuikAndTeam";
DELIMITER $$

CREATE PROCEDURE "usp_DocentInTeam_SelectByLuikAndTeam"(
            p_luikid int,
            p_teamid int)
BEGIN
  SELECT dt.docentid, d.naam, d.voornaam
    FROM DocentInDocentTeam dt
    JOIN Docent d ON dt.docentid= d.docentid
    WHERE dt.projectluikid= p_luikid AND dt.docentteamid= p_teamid;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Docentteam_By_Opgave"
--

DROP PROCEDURE IF EXISTS "usp_Docentteam_By_Opgave";
DELIMITER $$

CREATE PROCEDURE "usp_Docentteam_By_Opgave"(
            p_opgaveid int  )
BEGIN
  SELECT dt.docentTeamID, dt.opgaveID, po.projectID
    FROM docentteam dt
    JOIN projectopgave po ON dt.opgaveID= po.opgaveID
    WHERE dt.opgaveid= p_opgaveid;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Docentteam_Delete"
--

DROP PROCEDURE IF EXISTS "usp_Docentteam_Delete";
DELIMITER $$

CREATE PROCEDURE "usp_Docentteam_Delete"(
            p_docentteamid int)
BEGIN
  START transaction;
  DELETE From docentteam
    WHERE docentteamid= p_docentteamid;
  DELETE From docentindocentteam
    WHERE docentteamid= p_docentteamid;
  DELETE From studentgroep
    WHERE docentteamid= p_docentteamid;
  COMMIT;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Docentteam_Insert"
--

DROP PROCEDURE IF EXISTS "usp_Docentteam_Insert";
DELIMITER $$

CREATE PROCEDURE "usp_Docentteam_Insert"(
            p_opgaveid int)
BEGIN
  START transaction;
  INSERT docentteam( opgaveid) values( p_opgaveid);
  COMMIT;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Docentteam_SelectAll"
--

DROP PROCEDURE IF EXISTS "usp_Docentteam_SelectAll";
DELIMITER $$

CREATE PROCEDURE "usp_Docentteam_SelectAll"(
)
BEGIN
  SELECT *
    FROM docentteam;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Docentteam_Update"
--

DROP PROCEDURE IF EXISTS "usp_Docentteam_Update";
DELIMITER $$

CREATE PROCEDURE "usp_Docentteam_Update"(
            p_docentteamid int,
            p_opgaveid int)
BEGIN
  START transaction;
  UPDATE docentteam
    SET opgaveid= p_opgaveid
    WHERE docentteamid= p_docentteamid;
  COMMIT;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Docent_SelectAll"
--

DROP PROCEDURE IF EXISTS "usp_Docent_SelectAll";
DELIMITER $$

CREATE PROCEDURE "usp_Docent_SelectAll"(
)
BEGIN
  SELECT *
    FROM docent;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Projectluik_SelectAll"
--

DROP PROCEDURE IF EXISTS "usp_Projectluik_SelectAll";
DELIMITER $$

CREATE PROCEDURE "usp_Projectluik_SelectAll"(
            p_projectid int)
BEGIN
    SELECT *
      FROM projectluik pl
      WHERE pl.projectid= p_projectid;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Projectluik_SelectByID"
--

DROP PROCEDURE IF EXISTS "usp_Projectluik_SelectByID";
DELIMITER $$

CREATE PROCEDURE "usp_Projectluik_SelectByID"(
            p_luikid int)
BEGIN
  SELECT *
    FROM projectluik pl
    JOIN project p ON p.projectid= pl.projectid
    WHERE pl.luikid= p_luikid;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_ProjectOpgave_Delete"
--

DROP PROCEDURE IF EXISTS "usp_ProjectOpgave_Delete";
DELIMITER $$

CREATE PROCEDURE "usp_ProjectOpgave_Delete"(IN p_opgaveID INTEGER(11), OUT p_gelukt BOOLEAN)
BEGIN
	START TRANSACTION;
	SET p_gelukt = TRUE;
				IF EXISTS(SELECT * FROM  DocentTeam WHERE DocentTeam.opgaveID = p_opgaveID)
                THEN
					SET p_gelukt = FALSE;
				END IF;

                IF EXISTS(SELECT * FROM StudentGroep WHERE StudentGroep.opgaveID = p_opgaveID)
                THEN
					SET p_gelukt = FALSE;
                END IF;	

				IF(p_gelukt = true)
                THEN
						DELETE From ProjectOpgave
						WHERE opgaveID = p_opgaveID;			
				END IF;

	COMMIT;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_ProjectOpgave_DeleteAll"
--

DROP PROCEDURE IF EXISTS "usp_ProjectOpgave_DeleteAll";
DELIMITER $$

CREATE PROCEDURE "usp_ProjectOpgave_DeleteAll"(
            p_opgaveID int
            )
BEGIN
    START TRANSACTION;
          DELETE From DocentTeam
            WHERE DocentTeam.opgaveID= p_opgaveID;
          DELETE From StudentGroep
              WHERE StudentGroep.opgaveID= p_opgaveID;
          DELETE From ProjectOpgave
              WHERE ProjectOpgave.opgaveID= p_opgaveID;
    COMMIT;    
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_ProjectOpgave_Insert"
--

DROP PROCEDURE IF EXISTS "usp_ProjectOpgave_Insert";
DELIMITER $$

CREATE PROCEDURE "usp_ProjectOpgave_Insert"(
            p_opgaveTitel VARCHAR(50),
            p_korteOmschrijving VARCHAR (255),
            p_aantalStudentenPerGroep smallint,
            p_aantalGroepen smallint,
            p_projectID int,
            out p_opgaveID int)
BEGIN
  START TRANSACTION;

  select max(opgaveid)+1 into p_opgaveID from projectopgave;
  INSERT ProjectOpgave( opgaveid, opgaveTitel, korteOmschrijving, aantalStudentenPerGroep,

     aantalGroepen, projectID) values( p_opgaveID, p_opgaveTitel, p_korteOmschrijving, p_aantalStudentenPerGroep,

     p_aantalGroepen, p_projectID);

  COMMIT;

END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_ProjectOpgave_SelectAll"
--

DROP PROCEDURE IF EXISTS "usp_ProjectOpgave_SelectAll";
DELIMITER $$

CREATE PROCEDURE "usp_ProjectOpgave_SelectAll"(

)
BEGIN

  START TRANSACTION;

  SELECT *

    FROM PROJECTOPGAVE

    ORDER BY opgaveTitel;

  COMMIT;

END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_ProjectOpgave_SelectByOpgaveID"
--

DROP PROCEDURE IF EXISTS "usp_ProjectOpgave_SelectByOpgaveID";
DELIMITER $$

CREATE PROCEDURE "usp_ProjectOpgave_SelectByOpgaveID"(
            p_opgaveID int  )
BEGIN
    START TRANSACTION;
    SELECT *
      FROM ProjectOpgave po
      WHERE po.opgaveID= p_opgaveID;
    COMMIT;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_ProjectOpgave_SelectByProjectID"
--

DROP PROCEDURE IF EXISTS "usp_ProjectOpgave_SelectByProjectID";
DELIMITER $$

CREATE PROCEDURE "usp_ProjectOpgave_SelectByProjectID"(
            p_projectID int  )
BEGIN
    START TRANSACTION;
    SELECT p.projectID, p.projectTitel, p.studieJaar, p.inschrijvingVan, p.inschrijvingTot,
       po.opgaveID, po.opgaveTitel, po.korteOmschrijving, po.aantalStudentenPerGroep,
       po.aantalGroepen
      FROM ProjectOpgave po
      JOIN Project p ON po.projectID= p.projectID
      WHERE p.projectID= p_projectID
      ORDER BY po.opgaveTitel;
    COMMIT;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_ProjectOpgave_SelectByProjectIDForStudent"
--

DROP PROCEDURE IF EXISTS "usp_ProjectOpgave_SelectByProjectIDForStudent";
DELIMITER $$

CREATE PROCEDURE "usp_ProjectOpgave_SelectByProjectIDForStudent"(
            p_projectID int ,
            p_studentnr int )
BEGIN

START TRANSACTION;
IF EXISTS(
      SELECT inschrijvingVan
      FROM project
      WHERE projectid= p_projectid) 
	THEN
		IF EXISTS(
			SELECT inschrijvingTot
			FROM project
			WHERE projectid= p_projectid) 
		THEN
			SELECT p.projectID, p.projectTitel, p.studieJaar, p.inschrijvingVan, p.inschrijvingTot,
			po.opgaveID, po.opgaveTitel, po.korteOmschrijving, po.aantalStudentenPerGroep,
			po.aantalGroepen
			FROM ProjectOpgave po
			JOIN Project p ON po.projectID= p.projectID
			WHERE p.projectID= p_projectID AND p.inschrijvingVan < 
				Now() AND p.inschrijvingTot > Now() AND po.aantalGroepen >(
				SELECT count( *)
				FROM studentgroep
				WHERE opgaveID= po.opgaveID) AND po.opgaveID not in(
					SELECT ssg.opgaveid
					FROM StudentGroep ssg
					JOIN StudentInStudentGroep sss ON ssg.studentgroepid= sss.studentgroepid
					WHERE sss.studentnr= p_studentnr)
				ORDER BY po.opgaveTitel;		
		ELSE
			SELECT p.projectID, p.projectTitel, p.studieJaar, p.inschrijvingVan, p.inschrijvingTot,
				po.opgaveID, po.opgaveTitel, po.korteOmschrijving, po.aantalStudentenPerGroep,
				po.aantalGroepen
				FROM ProjectOpgave po
				JOIN Project p ON po.projectID= p.projectID
				WHERE p.projectID= projectID AND p.inschrijvingVan < Now() AND aantalGroepen>(
					SELECT count( *)
					FROM studentgroep
					WHERE opgaveID= po.opgaveID) AND po.opgaveID not in(
						SELECT ssg.opgaveid
						FROM StudentGroep ssg
						JOIN StudentInStudentGroep sss ON ssg.studentgroepid= sss.studentgroepid
						WHERE sss.studentnr= p_studentnr)
					ORDER BY po.opgaveTitel;
	    END IF;
ELSE
	IF EXISTS(
		SELECT inschrijvingTot
		FROM project
		WHERE projectid= p_projectid) 
	THEN
		SELECT p.projectID, p.projectTitel, p.studieJaar, p.inschrijvingVan, p.inschrijvingTot,
		po.opgaveID, po.opgaveTitel, po.korteOmschrijving, po.aantalStudentenPerGroep,
		po.aantalGroepen
		FROM ProjectOpgave po
		JOIN Project p ON po.projectID= p.projectID
		WHERE p.projectID= projectID AND p.inschrijvingTot> Now() AND aantalGroepen>(
			SELECT count( *)
			FROM studentgroep
			WHERE opgaveID= po.opgaveID) AND po.opgaveID not in(
				SELECT ssg.opgaveid
				FROM StudentGroep ssg
				JOIN StudentInStudentGroep sss ON ssg.studentgroepid= sss.studentgroepid
				WHERE sss.studentnr= p_studentnr)
	        ORDER BY po.opgaveTitel;
	ELSE
		SELECT p.projectID, p.projectTitel, p.studieJaar, p.inschrijvingVan, p.inschrijvingTot,
			po.opgaveID, po.opgaveTitel, po.korteOmschrijving, po.aantalStudentenPerGroep,
			po.aantalGroepen
			FROM ProjectOpgave po
			JOIN Project p ON po.projectID= p.projectID
			WHERE p.projectID= p_projectID AND po.aantalGroepen>(
				SELECT count( *)
				FROM studentgroep
				WHERE opgaveID= po.opgaveID) AND po.opgaveID not in(
					SELECT ssg.opgaveid
					FROM StudentGroep ssg
					JOIN StudentInStudentGroep sss ON ssg.studentgroepid= sss.studentgroepid
					WHERE sss.studentnr= p_studentnr)
				ORDER BY po.opgaveTitel;
	END IF;
END IF;
COMMIT;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_ProjectOpgave_Update"
--

DROP PROCEDURE IF EXISTS "usp_ProjectOpgave_Update";
DELIMITER $$

CREATE PROCEDURE "usp_ProjectOpgave_Update"(
in p_opgaveID int,
in p_opgaveTitel VARCHAR(50),
in p_korteOmschrijving VARCHAR(255),
in p_aantalStudentenPerGroep smallint,
in p_aantalGroepen smallint,
in p_projectID int
)
BEGIN

    START TRANSACTION;

    UPDATE ProjectOpgave

      SET
      opgaveTitel= p_opgaveTitel,
      korteOmschrijving= p_korteOmschrijving,
      aantalStudentenPerGroep= p_aantalStudentenPerGroep,
      aantalGroepen= p_aantalGroepen,
      projectID= p_projectID

      WHERE opgaveID= p_opgaveID;

    COMMIT;

END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Project_SelectAll"
--

DROP PROCEDURE IF EXISTS "usp_Project_SelectAll";
DELIMITER $$

CREATE PROCEDURE "usp_Project_SelectAll"(
)
BEGIN
  START TRANSACTION;
  SELECT *
    FROM PROJECT
    ORDER BY projectID;
  COMMIT;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Project_SelectByProjectID"
--

DROP PROCEDURE IF EXISTS "usp_Project_SelectByProjectID";
DELIMITER $$

CREATE PROCEDURE "usp_Project_SelectByProjectID"(
            p_projectID int )
BEGIN
  START TRANSACTION;
  SELECT *
    FROM project
    WHERE projectID= p_projectID;
  COMMIT;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Project_SelectByStudent"
--

DROP PROCEDURE IF EXISTS "usp_Project_SelectByStudent";
DELIMITER $$

CREATE PROCEDURE "usp_Project_SelectByStudent"(
            p_studentID int )
BEGIN
  START TRANSACTION;
  SELECT *
    FROM PROJECT p
    WHERE p.studieJaar=(
    SELECT studiejaar
    FROM student
    WHERE studentNR= p_studentID)
    UNION
    SELECT *
    FROM PROJECT p2
    WHERE p2.projectID IN(
    SELECT projectID
    FROM StudieTraject
    WHERE studentNr= p_studentID)
    ORDER BY projectID;
  COMMIT;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Student_GetMaxGroepID"
--

DROP PROCEDURE IF EXISTS "usp_Student_GetMaxGroepID";
DELIMITER $$

CREATE PROCEDURE "usp_Student_GetMaxGroepID"(
)
BEGIN
  START TRANSACTION;
  SELECT count( *)
    FROM studentGroep;
  COMMIT;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Student_InsertGroep"
--

DROP PROCEDURE IF EXISTS "usp_Student_InsertGroep";
DELIMITER $$

CREATE PROCEDURE "usp_Student_InsertGroep"(
            p_opgaveid int,
            p_inschrijvingsdatum datetime )
BEGIN
  START TRANSACTION;
  INSERT studentGroep( opgaveID, inschrijvingsDatum) values( p_opgaveid,
     p_inschrijvingsdatum);
  COMMIT;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Student_InsertStudentInGroep"
--

DROP PROCEDURE IF EXISTS "usp_Student_InsertStudentInGroep";
DELIMITER $$

CREATE PROCEDURE "usp_Student_InsertStudentInGroep"(
            p_studentgroepid int,
            p_studentnr int )
BEGIN
  START TRANSACTION;
  INSERT StudentInStudentGroep( studentGroepID, studentNr) values( p_studentgroepid,
     p_studentnr);
  COMMIT;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Student_SelectByProjectID"
--

DROP PROCEDURE IF EXISTS "usp_Student_SelectByProjectID";
DELIMITER $$

CREATE PROCEDURE "usp_Student_SelectByProjectID"(
            p_projectID int )
BEGIN
  START TRANSACTION;
  SELECT s.studentNr, s.naam, s.voornaam, s.email, s.paswoord, s.studiejaar,
     s.geindividualiseerdstudietraject
    FROM student s
    WHERE s.geindividualiseerdStudieTraject= 0 AND s.studiejaar=(
    SELECT studiejaar
    FROM project
    WHERE projectid= p_projectid) AND s.studentNr not in(
    SELECT sg.studentnr
    FROM StudentInStudentgroep sg
    JOIN studentgroep st ON sg.studentgroepid= st.studentgroepid
    JOIN projectopgave p ON st.opgaveid= p.opgaveid
    WHERE p.projectid= p_projectid)
    UNION
    SELECT s.studentNr, s.naam, s.voornaam, s.email, s.paswoord, s.studiejaar,
       s.geindividualiseerdstudietraject
    FROM student s
    JOIN studietraject st ON s.studentNr= st.studentNr
    WHERE s.geindividualiseerdStudieTraject= 1 AND st.projectid= 
      p_projectid AND s.studentNr not in(
    SELECT sg.studentnr
    FROM StudentInStudentgroep sg
    JOIN studentgroep st ON sg.studentgroepid= st.studentgroepid
    JOIN projectopgave p ON st.opgaveid= p.opgaveid
    WHERE p.projectid= p_projectid);
  COMMIT;
END $$

DELIMITER ;

--
-- Procedure "projecten"."usp_Student_SelectByStudentID"
--

DROP PROCEDURE IF EXISTS "usp_Student_SelectByStudentID";
DELIMITER $$

CREATE PROCEDURE "usp_Student_SelectByStudentID"(
            p_studentID int )
BEGIN
  START TRANSACTION;
  SELECT *
    FROM Student
    WHERE studentNr= p_studentID;
  COMMIT;
END $$

DELIMITER ;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
