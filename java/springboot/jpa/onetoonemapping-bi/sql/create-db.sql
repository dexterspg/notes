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
`second_name` varchar(45) DEFAULT NULL,
`last_name` varchar(45) DEFAULT NULL,
`email` varchar(45) DEFAULT NULL,
`instructor_detail_id` int(11) DEFAULT NULL,
PRIMARY KEY(`id`),
CONSTRAINT `FK_DETAIL` FOREIGN KEY(`instructor_detail_id`)
REFERENCES `instructor_detail`(`id`)
);

