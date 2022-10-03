-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 20, 2022 at 11:24 AM
-- Server version: 10.4.16-MariaDB
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wg_stop`
--

-- --------------------------------------------------------

--
-- Table structure for table `sodadrink`
--

CREATE TABLE `sodadrink` (
  `itemID` char(5) NOT NULL,
  `itemName` varchar(255) NOT NULL,
  `itemPrice` int(11) NOT NULL,
  `withIce` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sodadrink`
--

INSERT INTO `sodadrink` (`itemID`, `itemName`, `itemPrice`, `withIce`) VALUES
('SD001', 'Fanta', 7500, 1),
('SD002', 'Coca-Cola', 8500, 0),
('SD003', 'Pepsi', 7250, 0),
('SD004', 'Mountain Dew', 10000, 0),
('SD005', 'Sprite', 6500, 1);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transactionID` char(5) NOT NULL,
  `itemID` char(5) NOT NULL,
  `customerName` varchar(255) NOT NULL,
  `customerEmail` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `wing`
--

CREATE TABLE `wing` (
  `itemID` char(5) NOT NULL,
  `itemName` varchar(255) NOT NULL,
  `itemPrice` int(11) NOT NULL,
  `sauceName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `wing`
--

INSERT INTO `wing` (`itemID`, `itemName`, `itemPrice`, `sauceName`) VALUES
('WN001', 'Small Original Wing', 17500, 'Original'),
('WN002', 'Large Original Wing', 25500, 'Original'),
('WN003', 'Honey Wing', 20000, 'Honey'),
('WN004', 'BBQ Wing', 18500, 'Barbecue'),
('WN005', 'Cheese Wing', 25000, 'Cheese');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sodadrink`
--
ALTER TABLE `sodadrink`
  ADD PRIMARY KEY (`itemID`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transactionID`);

--
-- Indexes for table `wing`
--
ALTER TABLE `wing`
  ADD PRIMARY KEY (`itemID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
