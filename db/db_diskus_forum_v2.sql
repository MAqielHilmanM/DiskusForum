-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 20, 2018 at 05:53 AM
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
('USER-00001', 'maqiel2600@gmail.com', 'maqielhm', '86f7e437faa5a7fce15d1ddcb9eaeaea377667b8', '2018-11-20 03:57:20');

-- --------------------------------------------------------

--
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id_comment` varchar(10) NOT NULL,
  `id_member` varchar(10) NOT NULL,
  `id_thread` varchar(10) NOT NULL,
  `id_reply` varchar(10) NOT NULL,
  `comment` text NOT NULL,
  `like` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `t_community`
--

DROP TABLE IF EXISTS `t_community`;
CREATE TABLE `t_community` (
  `id_community` varchar(10) NOT NULL,
  `title` text NOT NULL,
  `description` text NOT NULL,
  `total_thread` int(11) NOT NULL,
  `total_member` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `t_member`
--

DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `id_Member` varchar(10) NOT NULL,
  `id_user` varchar(10) NOT NULL,
  `id_community` varchar(10) NOT NULL,
  `id_privileges` varchar(10) NOT NULL,
  `join_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `t_privileges`
--

DROP TABLE IF EXISTS `t_privileges`;
CREATE TABLE `t_privileges` (
  `id_privileges` varchar(10) NOT NULL,
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
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `t_trusted`
--

DROP TABLE IF EXISTS `t_trusted`;
CREATE TABLE `t_trusted` (
  `id_trusted` varchar(10) NOT NULL,
  `id_thread` varchar(10) NOT NULL,
  `id_member` varchar(10) NOT NULL,
  `isTrusted` tinyint(1) NOT NULL,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id_user` varchar(10) NOT NULL,
  `name` varchar(40) NOT NULL,
  `gender` char(1) NOT NULL DEFAULT 'L',
  `phone` varchar(15) NOT NULL,
  `biograph` text NOT NULL,
  `address` varchar(40) NOT NULL,
  `country` varchar(40) NOT NULL,
  `URL_Cover` text NOT NULL,
  `URL_Photo` text NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_user`
--

INSERT INTO `t_user` (`id_user`, `name`, `gender`, `phone`, `biograph`, `address`, `country`, `URL_Cover`, `URL_Photo`, `created_date`) VALUES
('USER-00001', 'MUCHAMAD AQIEL HILMAN MAULANDANY', 'L', '82117896960', 'KECE PISAN EUY', 'ADDRESSS', 'Indonesia', 'asdasfasacs', 'asdqwdqwdqdasd', '2018-11-16 22:38:00');

-- --------------------------------------------------------

--
-- Table structure for table `t_verification`
--

DROP TABLE IF EXISTS `t_verification`;
CREATE TABLE `t_verification` (
  `id_user` varchar(10) NOT NULL,
  `URL_Photo` text NOT NULL,
  `idVertified` tinyint(1) NOT NULL,
  `verified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_verification`
--

INSERT INTO `t_verification` (`id_user`, `URL_Photo`, `idVertified`, `verified_date`) VALUES
('USER-00001', 'asdasdasdasdasddsa', 1, '2018-11-20 03:40:49');

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
  ADD PRIMARY KEY (`id_Member`),
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
  ADD CONSTRAINT `FK_ID_USER_USER_AUTH` FOREIGN KEY (`id_user`) REFERENCES `t_user` (`id_user`);

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
