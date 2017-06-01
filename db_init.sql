-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema twt
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `twt` ;

-- -----------------------------------------------------
-- Schema twt
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `twt` DEFAULT CHARACTER SET utf8 ;
USE `twt` ;

-- -----------------------------------------------------
-- Table `twt`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `twt`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(250) NOT NULL,
  `password` VARCHAR(250) NOT NULL,
  `enabled` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`iduser`))
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `twt`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `twt`.`post` (
  `idpost` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NULL,
  `content` VARCHAR(250) NULL,
  `create_date` DATETIME NULL,
  `edit_date` DATETIME NULL,
  `user_iduser` INT NOT NULL,
  PRIMARY KEY (`idpost`),
  INDEX `fk_post_user1_idx` (`user_iduser` ASC),
  CONSTRAINT `fk_post_user1`
  FOREIGN KEY (`user_iduser`)
  REFERENCES `twt`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `twt`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `twt`.`comment` (
  `idcomment` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(250) NULL,
  `create_date` DATETIME NULL,
  `edit_date` DATETIME NULL,
  `user_iduser` INT NOT NULL,
  PRIMARY KEY (`idcomment`),
  INDEX `fk_comment_user1_idx` (`user_iduser` ASC),
  CONSTRAINT `fk_comment_user1`
  FOREIGN KEY (`user_iduser`)
  REFERENCES `twt`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `twt`.`follow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `twt`.`follow` (
  `user_iduser` INT NOT NULL,
  `followed_user` INT NOT NULL,
  INDEX `fk_follow_user1_idx` (`user_iduser` ASC),
  INDEX `fk_follow_user2_idx` (`followed_user` ASC),
  PRIMARY KEY (`user_iduser`, `followed_user`),
  CONSTRAINT `fk_follow_user1`
  FOREIGN KEY (`user_iduser`)
  REFERENCES `twt`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_follow_user2`
  FOREIGN KEY (`followed_user`)
  REFERENCES `twt`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `twt`.`post_like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `twt`.`post_like` (
  `user_iduser` INT NOT NULL,
  `post_idpost` INT NOT NULL,
  INDEX `fk_post_like_user1_idx` (`user_iduser` ASC),
  INDEX `fk_post_like_post1_idx` (`post_idpost` ASC),
  PRIMARY KEY (`user_iduser`, `post_idpost`),
  CONSTRAINT `fk_post_like_user1`
  FOREIGN KEY (`user_iduser`)
  REFERENCES `twt`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_like_post1`
  FOREIGN KEY (`post_idpost`)
  REFERENCES `twt`.`post` (`idpost`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `twt`.`comment_like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `twt`.`comment_like` (
  `user_iduser` INT NOT NULL,
  `comment_idcomment` INT NOT NULL,
  INDEX `fk_comment_like_user1_idx` (`user_iduser` ASC),
  INDEX `fk_comment_like_comment1_idx` (`comment_idcomment` ASC),
  PRIMARY KEY (`user_iduser`, `comment_idcomment`),
  CONSTRAINT `fk_comment_like_user1`
  FOREIGN KEY (`user_iduser`)
  REFERENCES `twt`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_like_comment1`
  FOREIGN KEY (`comment_idcomment`)
  REFERENCES `twt`.`comment` (`idcomment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `twt`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `twt`.`role` (
  `idrole` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(250) NULL,
  PRIMARY KEY (`idrole`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `twt`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `twt`.`tag` (
  `idtag` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idtag`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `twt`.`post_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `twt`.`post_tag` (
  `post_idpost` INT NOT NULL,
  `tag_idtag` INT NOT NULL,
  INDEX `fk_post_tag_post1_idx` (`post_idpost` ASC),
  INDEX `fk_post_tag_tag1_idx` (`tag_idtag` ASC),
  PRIMARY KEY (`post_idpost`, `tag_idtag`),
  CONSTRAINT `fk_post_tag_post1`
  FOREIGN KEY (`post_idpost`)
  REFERENCES `twt`.`post` (`idpost`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_tag_tag1`
  FOREIGN KEY (`tag_idtag`)
  REFERENCES `twt`.`tag` (`idtag`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `twt`.`comment_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `twt`.`comment_tag` (
  `comment_idcomment` INT NOT NULL,
  `tag_idtag` INT NOT NULL,
  INDEX `fk_comment_tag_comment1_idx` (`comment_idcomment` ASC),
  INDEX `fk_comment_tag_tag1_idx` (`tag_idtag` ASC),
  PRIMARY KEY (`comment_idcomment`, `tag_idtag`),
  CONSTRAINT `fk_comment_tag_comment1`
  FOREIGN KEY (`comment_idcomment`)
  REFERENCES `twt`.`comment` (`idcomment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_tag_tag1`
  FOREIGN KEY (`tag_idtag`)
  REFERENCES `twt`.`tag` (`idtag`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `twt`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `twt`.`user_role` (
  `role_idrole` INT NOT NULL,
  `user_iduser` INT NOT NULL,
  PRIMARY KEY (`role_idrole`, `user_iduser`),
  INDEX `fk_user_role_role_idx` (`role_idrole` ASC),
  INDEX `fk_user_role_user1_idx` (`user_iduser` ASC),
  CONSTRAINT `fk_user_role_role`
  FOREIGN KEY (`role_idrole`)
  REFERENCES `twt`.`role` (`idrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_user1`
  FOREIGN KEY (`user_iduser`)
  REFERENCES `twt`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
  DISABLE KEYS */;
INSERT INTO `user` VALUES (1, 'admin', 'admin', 1), (2, 'mod', 'mod', 1), (3, 'user@email.com', 'user', 1),
  (4, 'adam@email.com', 'adam', 1), (5, 'kamil@email.com', 'kamil', 1);
/*!40000 ALTER TABLE `user`
  ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role`
  DISABLE KEYS */;
INSERT INTO `role` VALUES (1, 'admin', 'administrator'), (2, 'mod', 'moderator'), (3, 'user', 'default user');
/*!40000 ALTER TABLE `role`
  ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag`
  DISABLE KEYS */;
INSERT INTO `tag` VALUES (1, 'katowice'), (2, 'sosnowiec'), (3, 'haszTag'), (4, 'fajnaApka');
/*!40000 ALTER TABLE `tag`
  ENABLE KEYS */;
UNLOCK TABLES;


SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';


LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role`
  DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1, 1), (2, 2), (3, 3), (4, 3), (5, 3);
/*!40000 ALTER TABLE `user_role`
  ENABLE KEYS */;
UNLOCK TABLES;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
