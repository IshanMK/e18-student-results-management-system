DROP DATABASE IF EXISTS Students_info;
CREATE DATABASE Students_info;


DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `RegNo` int NOT NULL,
  `Password` varchar(8) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Field` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`RegNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `courses`;
CREATE TABLE IF NOT EXISTS `courses` (
  `CourseCode` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CourseName` varchar(50) NOT NULL,
  `CourseCredits` int NOT NULL,
  `Marks4Project` int DEFAULT NULL,
  `Marks4Assignment` int DEFAULT NULL,
  `Marks4Mid` int DEFAULT NULL,
  `Marks4End` int DEFAULT NULL,
  PRIMARY KEY (`CourseCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `admins`;
CREATE TABLE IF NOT EXISTS `admins` (
  `RegNo` int NOT NULL
) 


DROP TABLE IF EXISTS `usergrade`;
CREATE TABLE IF NOT EXISTS `usergrade` (
  `RegNo` int NOT NULL,
  `CourseCode` char(5) NOT NULL,
  `Marks4Project` decimal(6,2) NOT NULL,
  `Marks4Assignment` decimal(6,2) NOT NULL,
  `Marks4Mid` decimal(6,2) NOT NULL,
  `Marks4End` decimal(6,2) NOT NULL,
  `GPA` decimal(6,3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;


DROP VIEW IF EXISTS `credit`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `credit`  AS  select `usergrade`.`RegNo` AS `RegNo`,sum(`courses`.`CourseCredits`) AS `creditSum` from (`usergrade` join `courses`) where (`courses`.`CourseCode` = `usergrade`.`CourseCode`) group by `usergrade`.`RegNo` ;


DROP VIEW IF EXISTS `gpa`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `gpa`  AS  select `usergrade`.`RegNo` AS `regno`,sum(`usergrade`.`GPA`) AS `GPASum` from `usergrade` group by `usergrade`.`RegNo` ;


DROP VIEW IF EXISTS `userranks`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `userranks`  AS  select `gpa`.`regno` AS `regno`,`gpa`.`GPASum` AS `GPASum`,`credit`.`creditSum` AS `creditSum` from (`gpa` join `credit`) where (`gpa`.`regno` = `credit`.`RegNo`) ;


DROP VIEW IF EXISTS `usergpas`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `usergpas`  AS  select `userranks`.`regno` AS `regno`,(`userranks`.`GPASum` / `userranks`.`creditSum`) AS `FinalGPA` from `userranks` ;


DROP VIEW IF EXISTS `rankofbatch`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rankofbatch`  AS  select `usergpas`.`regno` AS `regno`,`usergpas`.`FinalGPA` AS `finalgpa`,rank() OVER (PARTITION BY left(`usergpas`.`regno`,2) ORDER BY `usergpas`.`FinalGPA` desc )  AS `userrank` from `usergpas` ;
