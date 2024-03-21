CREATE TABLE `instructor_detail`(
`id` int(11) NOT NULL AUTO_INCREMENT,
`youtube_channel` varchar(128) DEFAULT NULL,
`hobby` varchar(45) DEFAULT NULL,
PRIMARY KEY(`id`)
);

desc instructor_detail;

CREATE table `instructor` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`first_name` varchar(45) DEFAULT NULL,
`last_name` varchar(45) DEFAULT NULL,
`email` varchar(45) DEFAULT NULL,
`instructor_detail_id` int(11) DEFAULT NULL,
PRIMARY KEY(`id`),
CONSTRAINT `FK_DETAIL` FOREIGN KEY(`instructor_detail_id`)
REFERENCES `instructor_detail`(`id`)
);


CREATE TABLE `course`(
`id` int(11) NOT NULL AUTO_INCREMENT,
`title` varchar(45) DEFAULT NULL,
`instructor_id` int(11) DEFAULT NULL,
PRIMARY KEY(`id`),
UNIQUE KEY `TITLE_UNIQUE` (`title`),  -- make title unique to preven duplicte cousre title
KEY `FK_INSTRUCTOR_idx` (`instructor_id`), -- creates index to improve query performance
CONSTRAINT `FK_INSTRUCTOR` FOREIGN KEY (`instructor_id`)
REFERENCES `instructor`(`id`)

ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


CREATE TABLE `review`(
`id` int(11) NOT NULL AUTO_INCREMENT,
`comment` varchar(45) DEFAULT NULL,
`course_id` int(11) DEFAULT NULL,
PRIMARY KEY(`id`),
KEY `FK_COURSE_idx` (`course_id`), -- creates index to improve query performance
CONSTRAINT `FK_COURSE` FOREIGN KEY (`course_id`)
REFERENCES `course`(`id`)

ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

CREATE TABLE `student`(
`id` int(11) NOT NULL AUTO_INCREMENT,
`first_name` varchar(45) DEFAULT NULL,
`last_name` varchar(45) DEFAULT NULL,
`email` varchar(45) DEFAULT NULL,
PRIMARY KEY(`id`)
);


CREATE TABLE `course_student`(
    
`fk_course_id` int(11) NOT NULL,
`fk_student_id` int(11) NOT NULL,

KEY `FK_STUDENT_idx` (`fk_student_id`),
CONSTRAINT `FK_COURSE_05` FOREIGN KEY (`fk_course_id`)   -- 05 because FK_COURSE already exists
REFERENCES `course`(`id`)
ON DELETE NO ACTION ON UPDATE NO ACTION,

CONSTRAINT `FK_STUDENT` FOREIGN KEY (`fk_student_id`)
REFERENCES `student`(`id`)
ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
