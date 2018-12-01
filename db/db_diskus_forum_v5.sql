-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 01, 2018 at 12:33 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_diskus_forum`
--
CREATE DATABASE IF NOT EXISTS `db_diskus_forum` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db_diskus_forum`;

-- --------------------------------------------------------

--
-- Table structure for table `t_auth`
--

DROP TABLE IF EXISTS `t_auth`;
CREATE TABLE `t_auth` (
  `id_user` varchar(10) NOT NULL,
  `email` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` text NOT NULL,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_auth`
--

INSERT INTO `t_auth` (`id_user`, `email`, `username`, `password`, `modified_date`) VALUES
('USER-63277', 'a@a.com', 'a', '86f7e437faa5a7fce15d1ddcb9eaeaea377667b8', '2018-11-30 10:42:54'),
('USER-92760', 'maqiel2600@gmail.com', 'maqielhm', '0269f76ab5c6e9429888960b985ab50bcaa3f03b', '2018-11-30 16:01:17'),
('USER-94540', 'almerfandriyanto98@gmail.com', 'almer98', '5086ac2261e668f0f94c51473c1b393b1862219d', '2018-12-01 09:11:04');

-- --------------------------------------------------------

--
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id_comment` varchar(10) NOT NULL,
  `id_member` varchar(10) NOT NULL,
  `id_thread` varchar(10) NOT NULL,
  `id_reply` varchar(10) DEFAULT NULL,
  `comment` text NOT NULL,
  `like` int(11) DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_comment`
--

INSERT INTO `t_comment` (`id_comment`, `id_member`, `id_thread`, `id_reply`, `comment`, `like`, `created_date`) VALUES
('COM-110570', 'MEM-123123', 'THR-000002', NULL, 'a', 0, '2018-12-01 05:30:57'),
('COM-121767', 'MEM-123123', 'THR-000002', NULL, 'aasdasdasd', 0, '2018-12-01 05:31:11'),
('COM-140391', 'MEM-123123', 'THR-000002', NULL, 'asu', 0, '2018-12-01 09:12:15'),
('COM-566603', 'MEM-123123', 'THR-000003', NULL, 'hehe', 0, '2018-12-01 09:22:13'),
('COM-736502', 'MEM-123123', 'THR-000003', NULL, 'au', 0, '2018-12-01 05:57:08'),
('COM-835626', 'MEM-123123', 'THR-000003', NULL, 'woi', 0, '2018-12-01 05:55:28'),
('COM-853814', 'MEM-123123', 'THR-000003', NULL, 'aaa', 0, '2018-12-01 05:54:32'),
('COM-953941', 'MEM-123123', 'THR-000001', NULL, 'aaaaa', 0, '2018-12-01 05:53:19');

--
-- Triggers `t_comment`
--
DROP TRIGGER IF EXISTS `after_delete_comment`;
DELIMITER $$
CREATE TRIGGER `after_delete_comment` AFTER DELETE ON `t_comment` FOR EACH ROW BEGIN
   UPDATE t_thread set total_comment = total_comment-1 WHERE id_thread = OLD.id_thread;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `after_insert_comment`;
DELIMITER $$
CREATE TRIGGER `after_insert_comment` AFTER INSERT ON `t_comment` FOR EACH ROW BEGIN
   UPDATE t_thread set total_comment = total_comment+1 WHERE id_thread = NEW.id_thread;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `t_community`
--

DROP TABLE IF EXISTS `t_community`;
CREATE TABLE `t_community` (
  `id_community` varchar(10) NOT NULL,
  `title` varchar(40) NOT NULL,
  `description` text NOT NULL,
  `total_thread` int(11) DEFAULT '0',
  `total_member` int(11) DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_community`
--

INSERT INTO `t_community` (`id_community`, `title`, `description`, `total_thread`, `total_member`, `created_date`) VALUES
('COM-123123', 'ADMIN', 'For Admin Only', 1, 0, '2018-11-30 12:30:26'),
('COM-123124', 'Loream Ipsum', 'lorem ipsum', 0, 0, '2018-11-30 12:05:42');

-- --------------------------------------------------------

--
-- Table structure for table `t_member`
--

DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `id_member` varchar(10) NOT NULL,
  `id_user` varchar(10) NOT NULL,
  `id_community` varchar(10) NOT NULL,
  `id_privileges` varchar(10) NOT NULL,
  `join_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_member`
--

INSERT INTO `t_member` (`id_member`, `id_user`, `id_community`, `id_privileges`, `join_date`) VALUES
('MEM-123123', 'USER-63277', 'COM-123123', 'PRI-000000', '2018-11-30 10:48:50');

--
-- Triggers `t_member`
--
DROP TRIGGER IF EXISTS `after_delete_member`;
DELIMITER $$
CREATE TRIGGER `after_delete_member` AFTER DELETE ON `t_member` FOR EACH ROW BEGIN
   UPDATE t_user set total_community = total_community-1 WHERE id_user = OLD.id_user;
   UPDATE t_community set total_member = total_member-1 WHERE id_community = OLD.id_community;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `after_insert_member`;
DELIMITER $$
CREATE TRIGGER `after_insert_member` AFTER INSERT ON `t_member` FOR EACH ROW BEGIN
   UPDATE t_user set total_community = total_community+1 WHERE id_user = NEW.id_user;
   UPDATE t_community set total_member = total_member+1 WHERE id_community = NEW.id_community;
   
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `t_privileges`
--

DROP TABLE IF EXISTS `t_privileges`;
CREATE TABLE `t_privileges` (
  `id_privileges` varchar(10) NOT NULL,
  `privileges_name` varchar(40) NOT NULL,
  `canAddThread` tinyint(1) NOT NULL,
  `canDeleteThread` tinyint(1) NOT NULL,
  `canEditThread` tinyint(1) NOT NULL,
  `canAddMember` tinyint(1) NOT NULL,
  `canDeleteMember` tinyint(1) NOT NULL,
  `canAddComment` tinyint(1) NOT NULL,
  `canRemoveComment` tinyint(1) NOT NULL,
  `canEditComment` tinyint(1) NOT NULL,
  `canRemoveUser` tinyint(1) NOT NULL,
  `canEditUser` tinyint(1) NOT NULL,
  `canAddCommunity` tinyint(1) NOT NULL,
  `canEditCommunity` tinyint(1) NOT NULL,
  `canRemoveCommunity` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_privileges`
--

INSERT INTO `t_privileges` (`id_privileges`, `privileges_name`, `canAddThread`, `canDeleteThread`, `canEditThread`, `canAddMember`, `canDeleteMember`, `canAddComment`, `canRemoveComment`, `canEditComment`, `canRemoveUser`, `canEditUser`, `canAddCommunity`, `canEditCommunity`, `canRemoveCommunity`) VALUES
('PRI-000000', 'SUPERMAN', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `t_thread`
--

DROP TABLE IF EXISTS `t_thread`;
CREATE TABLE `t_thread` (
  `id_thread` varchar(10) NOT NULL,
  `id_community` varchar(10) NOT NULL,
  `id_member` varchar(10) NOT NULL,
  `title` varchar(100) NOT NULL,
  `body` text NOT NULL,
  `total_like` int(11) NOT NULL DEFAULT '0',
  `total_comment` int(11) NOT NULL DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_thread`
--

INSERT INTO `t_thread` (`id_thread`, `id_community`, `id_member`, `title`, `body`, `total_like`, `total_comment`, `created_date`) VALUES
('THR-000001', 'COM-123123', 'MEM-123123', 'Lorem Ipsum', 'Lorem Ipsum', 0, 1, '2018-12-01 05:53:19'),
('THR-000002', 'COM-123123', 'MEM-123123', 'a', 'a', 0, 3, '2018-12-01 09:12:15'),
('THR-000003', 'COM-123123', 'MEM-123123', 'qqwasd', 'asdasdasd', 0, 4, '2018-12-01 09:22:13');

--
-- Triggers `t_thread`
--
DROP TRIGGER IF EXISTS `after_delete_thread`;
DELIMITER $$
CREATE TRIGGER `after_delete_thread` AFTER DELETE ON `t_thread` FOR EACH ROW BEGIN
   UPDATE t_user set total_post = total_post-1 WHERE id_user = (select id_user from t_member where id_member = OLD.id_member);
   UPDATE t_community set total_thread = total_thread-1 WHERE id_community = OLD.id_community;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `after_insert_thread`;
DELIMITER $$
CREATE TRIGGER `after_insert_thread` AFTER INSERT ON `t_thread` FOR EACH ROW BEGIN
   UPDATE t_user set total_post = total_post+1 WHERE id_user = (select id_user from t_member where id_member = NEW.id_member);
   UPDATE t_community set total_thread = total_thread+1 WHERE id_community = NEW.id_community;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `t_trusted`
--

DROP TABLE IF EXISTS `t_trusted`;
CREATE TABLE `t_trusted` (
  `id_trusted` varchar(10) NOT NULL,
  `id_thread` varchar(10) NOT NULL,
  `id_member` varchar(10) NOT NULL,
  `isTrusted` tinyint(1) NOT NULL DEFAULT '0',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Triggers `t_trusted`
--
DROP TRIGGER IF EXISTS `after_delete_trusted`;
DELIMITER $$
CREATE TRIGGER `after_delete_trusted` AFTER DELETE ON `t_trusted` FOR EACH ROW BEGIN
   UPDATE t_thread set total_like = total_like-1 WHERE id_thread = OLD.id_thread;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `after_insert_trusted`;
DELIMITER $$
CREATE TRIGGER `after_insert_trusted` AFTER INSERT ON `t_trusted` FOR EACH ROW BEGIN
   UPDATE t_thread set total_like = total_like+1 WHERE id_thread = NEW.id_thread;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id_user` varchar(10) NOT NULL,
  `name` varchar(40) NOT NULL,
  `gender` enum('L','P') NOT NULL DEFAULT 'L',
  `phone` varchar(15) DEFAULT NULL,
  `biograph` text,
  `address` varchar(40) DEFAULT NULL,
  `country` varchar(40) DEFAULT NULL,
  `title` varchar(40) DEFAULT NULL,
  `url_photo` text,
  `total_post` int(11) DEFAULT '0',
  `total_community` int(11) DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_user`
--

INSERT INTO `t_user` (`id_user`, `name`, `gender`, `phone`, `biograph`, `address`, `country`, `title`, `url_photo`, `total_post`, `total_community`, `created_date`) VALUES
('USER-63277', 'a', 'L', NULL, NULL, NULL, NULL, NULL, NULL, 3, 1, '2018-11-30 10:42:54'),
('USER-92760', 'Muchamad Aqiel Hilman Maulandany', 'L', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '2018-11-30 16:01:17'),
('USER-94540', 'Almer Fandriyanto Hendi', 'L', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '2018-12-01 09:11:03');

-- --------------------------------------------------------

--
-- Table structure for table `t_verification`
--

DROP TABLE IF EXISTS `t_verification`;
CREATE TABLE `t_verification` (
  `id_user` varchar(10) NOT NULL,
  `url_photo` text NOT NULL,
  `idVertified` tinyint(1) NOT NULL DEFAULT '0',
  `verified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_auth`
--
ALTER TABLE `t_auth`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `t_comment`
--
ALTER TABLE `t_comment`
  ADD PRIMARY KEY (`id_comment`),
  ADD KEY `FK_ID_MEM_MEM_COM` (`id_member`),
  ADD KEY `FK_ID_THR_THR_COM` (`id_thread`),
  ADD KEY `FK_ID_REP_COM_COM` (`id_reply`);

--
-- Indexes for table `t_community`
--
ALTER TABLE `t_community`
  ADD PRIMARY KEY (`id_community`);

--
-- Indexes for table `t_member`
--
ALTER TABLE `t_member`
  ADD PRIMARY KEY (`id_member`),
  ADD UNIQUE KEY `uq_id_user_com` (`id_user`,`id_community`),
  ADD KEY `FK_ID_USER_USER_MEM` (`id_user`),
  ADD KEY `FK_ID_COM_COM_MEM` (`id_community`),
  ADD KEY `FK_ID_PRI_PRI_MEM` (`id_privileges`);

--
-- Indexes for table `t_privileges`
--
ALTER TABLE `t_privileges`
  ADD PRIMARY KEY (`id_privileges`);

--
-- Indexes for table `t_thread`
--
ALTER TABLE `t_thread`
  ADD PRIMARY KEY (`id_thread`),
  ADD KEY `FK_ID_COM_COM_THR` (`id_community`),
  ADD KEY `DK_ID_MEM_MEM_THR` (`id_member`);

--
-- Indexes for table `t_trusted`
--
ALTER TABLE `t_trusted`
  ADD PRIMARY KEY (`id_trusted`),
  ADD UNIQUE KEY `uq_id_thread_member` (`id_thread`,`id_member`),
  ADD KEY `FK_ID_THR_THR_TRU` (`id_thread`),
  ADD KEY `FK_ID_MEM_MEM_TRU` (`id_member`);

--
-- Indexes for table `t_user`
--
ALTER TABLE `t_user`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `t_verification`
--
ALTER TABLE `t_verification`
  ADD PRIMARY KEY (`id_user`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_auth`
--
ALTER TABLE `t_auth`
  ADD CONSTRAINT `FK_ID_USER_USER_AUTH` FOREIGN KEY (`id_user`) REFERENCES `t_user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t_comment`
--
ALTER TABLE `t_comment`
  ADD CONSTRAINT `FK_ID_MEM_MEM_COM` FOREIGN KEY (`id_member`) REFERENCES `t_member` (`id_Member`),
  ADD CONSTRAINT `FK_ID_REP_COM_COM` FOREIGN KEY (`id_reply`) REFERENCES `t_comment` (`id_comment`),
  ADD CONSTRAINT `FK_ID_THR_THR_COM` FOREIGN KEY (`id_thread`) REFERENCES `t_thread` (`id_thread`);

--
-- Constraints for table `t_member`
--
ALTER TABLE `t_member`
  ADD CONSTRAINT `FK_ID_COM_COM_MEM` FOREIGN KEY (`id_community`) REFERENCES `t_community` (`id_community`),
  ADD CONSTRAINT `FK_ID_PRI_PRI_MEM` FOREIGN KEY (`id_privileges`) REFERENCES `t_privileges` (`id_privileges`),
  ADD CONSTRAINT `FK_ID_USER_USER_MEM` FOREIGN KEY (`id_user`) REFERENCES `t_user` (`id_user`);

--
-- Constraints for table `t_thread`
--
ALTER TABLE `t_thread`
  ADD CONSTRAINT `DK_ID_MEM_MEM_THR` FOREIGN KEY (`id_member`) REFERENCES `t_member` (`id_Member`),
  ADD CONSTRAINT `FK_ID_COM_COM_THR` FOREIGN KEY (`id_community`) REFERENCES `t_community` (`id_community`);

--
-- Constraints for table `t_trusted`
--
ALTER TABLE `t_trusted`
  ADD CONSTRAINT `FK_ID_MEM_MEM_TRU` FOREIGN KEY (`id_member`) REFERENCES `t_member` (`id_Member`),
  ADD CONSTRAINT `FK_ID_THR_THR_TRU` FOREIGN KEY (`id_thread`) REFERENCES `t_thread` (`id_thread`);

--
-- Constraints for table `t_verification`
--
ALTER TABLE `t_verification`
  ADD CONSTRAINT `FK_ID_USER_USER_VER` FOREIGN KEY (`id_user`) REFERENCES `t_user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
