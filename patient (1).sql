-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 08:21 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `healthcare_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `pid` int(20) NOT NULL,
  `fName` varchar(20) NOT NULL,
  `lName` varchar(20) NOT NULL,
  `nic` varchar(20) NOT NULL,
  `dob` varchar(20) NOT NULL,
  `phone` varchar(14) NOT NULL,
  `email` varchar(100) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `bloodGroup` varchar(10) NOT NULL,
  `allergies` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`pid`, `fName`, `lName`, `nic`, `dob`, `phone`, `email`, `gender`, `bloodGroup`, `allergies`) VALUES
(30, 'ravihari', 'jayasekara', '876787678v', '1996/06/17', '0774975569', 'raviharisj.22@gmail.com', 'Male', 'A', 'dust'),
(32, 'kalana', 'jayasekara', '967876787v', '1995/05/05', '0774975569', 'user@gmail.com', 'Male', 'B', 'dust'),
(33, 'kalhari', 'jayasekara', '876787678v', '1996/05/06', '0774975569', 'raviharisj.22@gmail.com', 'Male', 'AB', 'Food Allergy.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`pid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `pid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
