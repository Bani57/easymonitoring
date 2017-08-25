-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema em_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema em_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `em_db` DEFAULT CHARACTER SET utf8 ;
USE `em_db` ;

-- -----------------------------------------------------
-- Table `em_db`.`screen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `em_db`.`screen` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `project_id` INT(10) NOT NULL,
  `priority_id` INT(10) NOT NULL,
  `status_id` INT(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
