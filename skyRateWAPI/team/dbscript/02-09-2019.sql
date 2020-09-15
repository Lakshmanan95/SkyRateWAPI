use skyrate;

ALTER TABLE `skyrate`.`user_alerts` ADD COLUMN `PRIORITY` INT NOT NULL DEFAULT 1  AFTER `BUTTON_NO` ;
ALTER TABLE `skyrate`.`user_alerts` CHANGE COLUMN `USER_ID` `IS_USER_ALERT` INT(11) NULL DEFAULT NULL  ;


UPDATE `skyrate`.`user_alerts` SET `ID`='1' WHERE `ID`='2';
INSERT INTO `skyrate`.`user_alerts` (`ID`, `ALERT_MSG`, `ALERT_TYPE`, `ALERT_FOR`, `TOTAL_REMINDER_TIMES`, `REMINDER_FREQUENCY`, `ACTIVE`, `RESPONSE_YES_URL`, `HEADER`, `BUTTON_YES`, `BUTTON_NO`) VALUES ('2', 'Test', '1', 'Upload', '5', '5', '1', 'dashboard/feedback', 'TEST', 'Yes, I\'ll do it now', 'No, I\'ll do it later');
UPDATE `skyrate`.`user_alerts` SET `ALERT_MSG`='Dear user, Please upload your parts ', `ALERT_FOR`='PartsUpload', `HEADER`='Parts Upload Reminder' WHERE `ID`='2';

CREATE  TABLE `skyrate`.`user_alerts_mapping` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `alert_id` INT NOT NULL ,
  `user_id` INT NOT NULL ,
  `active` INT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) );
ALTER TABLE `skyrate`.`user_alerts_mapping` DROP COLUMN `active` ;


ALTER TABLE `skyrate`.`user_login_history` ADD COLUMN `ACTIVITY` VARCHAR(45) NULL  AFTER `LOGIN_TIME` ;


INSERT INTO `user_alerts` (`ID`,`ALERT_MSG`,`ALERT_TYPE`,`ALERT_FOR`,`START_DATE`,`END_DATE`,`TOTAL_REMINDER_TIMES`,`REMINDER_FREQUENCY`,`USER_GROUP`,`IS_USER_ALERT`,`ACTIVE`,`RESPONSE_YES_URL`,`HEADER`,`BUTTON_YES`,`BUTTON_NO`,`PRIORITY`) VALUES (1,'Dear user, <BR>we truly care about your user experience & need your help to improve this site.  Please use the feedback tab to inform us of any errors you may encounter with the site, any recommendations or suggestions you may have or any feedback to help improve the site.  Without your feedback we are unable to make <a href=\"www.aviationrating.com\"> www.aviationrating.com</a> perform better for you.',1,'Feedback',NULL,NULL,2,5,NULL,NULL,1,'dashboard/feedback','Please provide your valuable feedback','Yes, I\'ll do it now','No, I\'ll do it later',1);
INSERT INTO `user_alerts` (`ID`,`ALERT_MSG`,`ALERT_TYPE`,`ALERT_FOR`,`START_DATE`,`END_DATE`,`TOTAL_REMINDER_TIMES`,`REMINDER_FREQUENCY`,`USER_GROUP`,`IS_USER_ALERT`,`ACTIVE`,`RESPONSE_YES_URL`,`HEADER`,`BUTTON_YES`,`BUTTON_NO`,`PRIORITY`) VALUES (2,'Dear user, Please upload your parts ',1,'PartsUpload',NULL,NULL,3,2,NULL,1,1,'dashboard/feedback','Parts Upload Reminder','Yes, I\'ll do it now','No, I\'ll do it later',2);
INSERT INTO `user_alerts` (`ID`,`ALERT_MSG`,`ALERT_TYPE`,`ALERT_FOR`,`START_DATE`,`END_DATE`,`TOTAL_REMINDER_TIMES`,`REMINDER_FREQUENCY`,`USER_GROUP`,`IS_USER_ALERT`,`ACTIVE`,`RESPONSE_YES_URL`,`HEADER`,`BUTTON_YES`,`BUTTON_NO`,`PRIORITY`) VALUES (3,'Dear user, Please upload your capabilities ',1,'CapabilitiesUpload',NULL,NULL,2,2,NULL,1,1,'dashboard/feedback','Capabilities Upload Reminder','Yes, I\'ll do it now','No, I\'ll do it later',1);
