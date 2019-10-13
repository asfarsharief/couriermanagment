-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 13, 2019 at 08:53 AM
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
-- Database: `faa_db`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `adddate` (IN `name` VARCHAR(30), IN `name1` VARCHAR(30))  NO SQL
UPDATE orders SET date=CURRENT_DATE WHERE sender_name=name and receiver_name=name1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `cost` (IN `wt` INT)  NO SQL
BEGIN
SELECT cost FROM cost WHERE wt<=weight;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `findname` (IN `name` VARCHAR(20))  NO SQL
BEGIN
SELECT sender_name,sender_phone, receiver_name,receiver_phone,tracking_number,origin,destination,address,parcel_type,date_delivery FROM track_details td, orders od WHERE td.tracking_number = od.tracking_no and od.sender_name = name;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `tracking` (IN `ID` VARCHAR(20))  BEGIN SELECT * FROM track_details WHERE tracking_number = ID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updet` (IN `tracking` VARCHAR(50))  NO SQL
BEGIN
UPDATE track_details
	SET date_delivery = CURRENT_DATE+7,
    	status_order = "On Time"
    WHERE tracking_number=tracking;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updetnight` (IN `tracking` INT(20))  NO SQL
BEGIN
UPDATE track_details
	SET date_delivery = CURRENT_DATE+1,
    	status_order = "On Time"
    WHERE tracking_number=tracking;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updetsame` (IN `tracking` INT(20))  NO SQL
BEGIN
UPDATE track_details
	SET date_delivery = CURRENT_DATE+2,
    	status_order = "On Time"
    WHERE tracking_number=tracking;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `cost`
--

CREATE TABLE `cost` (
  `index_no` int(15) NOT NULL,
  `weight` int(30) NOT NULL,
  `cost` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cost`
--

INSERT INTO `cost` (`index_no`, `weight`, `cost`) VALUES
(1, 5, 100),
(2, 10, 200),
(3, 15, 300),
(4, 20, 400),
(5, 25, 500),
(6, 30, 600),
(7, 0, 1500),
(8, 0, 800);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `tracking_no` int(11) NOT NULL,
  `sender_name` varchar(15) NOT NULL,
  `sender_phone` int(30) NOT NULL,
  `receiver_name` varchar(15) NOT NULL,
  `receiver_phone` int(30) NOT NULL,
  `origin` varchar(20) NOT NULL,
  `destination` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `pincode` int(20) NOT NULL,
  `parcel_type` varchar(20) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`tracking_no`, `sender_name`, `sender_phone`, `receiver_name`, `receiver_phone`, `origin`, `destination`, `address`, `pincode`, `parcel_type`, `date`) VALUES
(5478, '', 0, '', 0, '', '', '', 0, '', '2011-01-01'),
(5512, 'jhgjh', 56465, 'vhvhnbn', 145615, 'New Delhi', 'New Delhi', 'hjbh', 412, 'Standard', '2018-11-13'),
(5513, 'kjhjk', 451, 'jnjn', 4515, 'New Delhi', 'New Delhi', 'kjbnj', 4513, 'Standard', '2018-11-13'),
(5514, 'vghh', 111, 'hgjh', 422, 'New Delhi', 'New Delhi', 'gjhh', 4242, 'Standard', '2018-11-13'),
(5515, 'yui', 5, 'khb', 25, 'New Delhi', 'New Delhi', 'gvgn', 45, 'Standard', '2018-11-13'),
(5516, 'Abhi', 45852, 'dfcfg', 215465, 'New Delhi', 'Chennai', 'vgvg', 154, 'Overnight', '2018-11-13'),
(5517, 'ghghj', 56465, 'bjhhj', 44545, 'New Delhi', 'Kolkata', 'gygj', 5868, 'Standard', '2018-11-13'),
(5518, 'jhj', 45646, 'kjhhkj', 53, 'New Delhi', 'Chennai', 'jhj', 564, 'Standard', '2018-11-13'),
(5519, 'guygjy', 565, 'ghjgj', 565, 'New Delhi', 'Chennai', 'gjhghj', 545, 'Standard', '2018-11-13'),
(5520, 'bbjh', 454, 'hjj', 354, 'Chennai', 'Kolkata', 'gjhgjh', 5, 'Standard', '2018-11-13'),
(5521, 'fgfhg', 4546, 'jkj', 2312, 'New Delhi', 'Chennai', 'kkhj', 424, 'Ship Same', '2018-11-14'),
(5522, 'Asfar', 973978, 'Abhi', 973850, 'Bangalore', 'New Delhi', 'CP,New Delhi', 109991, 'Standard', '2018-12-04'),
(5523, 'xyz', 669898, 'oiu', 86687679, 'New Delhi', 'Chennai', 'lhkhl', 9808, 'Standard', '2018-12-31'),
(5525, 'hgh', 7574, 'jhbjh', 4745, 'New Delhi', 'New Delhi', 'bhjb', 5475, 'Standard', '2018-12-31'),
(5526, 'gvhgv', 4585, 'vghv', 4457, 'New Delhi', 'New Delhi', 'vgvg', 5242, 'Standard', '2018-12-31'),
(5527, 'gvgh', 4545, 'gggj', 85785, 'New Delhi', 'New Delhi', 'jhbjhb', 868, 'Standard', '2018-12-31'),
(5528, 'vbh', 5757, 'bjhbjh', 754557, 'New Delhi', 'Mumbai', 'njhbjhb', 5454, 'Standard', '2018-12-03'),
(5529, 'arun', 45455, 'nano', 8345, 'New Delhi', 'Chennai', 'hbnm', 4521, 'Standard', '2018-12-04'),
(5530, 'Gujju', 8546122, 'Ayush', 455454, 'Bangalore', 'Chandigarh', 'hhjzdbjhvb', 54521, 'Ship Same', '2018-12-08'),
(5531, 'Gujju', 8546122, 'Ayush', 455454, 'Bangalore', 'Chandigarh', 'hhjzdbjhvb', 54521, 'Overnight', '2018-12-08'),
(5532, 'Gujju', 8546122, 'Ayush', 455454, 'Bangalore', 'Chandigarh', 'hhjzdbjhvb', 54521, 'Standard', '2018-12-08'),
(5533, 'Gujju', 8546122, 'Ayushh', 455454, 'Bangalore', 'Chandigarh', 'hhjzdbjhvb', 54521, 'Standard', '2018-12-08'),
(5534, 'yug', 84684, 'knjhk', 846, 'New Delhi', 'Chennai', 'hbhjkj', 5645, 'Standard', '2018-12-08'),
(5535, 'nkjkjk', 54541, 'bjn', 1513, 'Bangalore', 'Kolkata', 'bhjn,', 2512, 'Ship Same', '2018-12-08'),
(5536, 'Abhi', 56565, 'cchggh', 787585, 'New Delhi', 'Bangalore', 'uhhjh', 5533, 'Standard', '2018-12-09'),
(5537, 'anzar', 2154865, 'asfar', 455322, 'New Delhi', 'Bangalore', 'gashgshh', 560037, 'Standard', '2019-01-15'),
(5538, 'abc', 265, 'njn', 44, 'New Delhi', 'Mumbai', 'm', 554, 'Overnight', '2019-08-02');

--
-- Triggers `orders`
--
DELIMITER $$
CREATE TRIGGER `trigger1` AFTER INSERT ON `orders` FOR EACH ROW INSERT into track_details(tracking_number,date_in,date_dispatched) 
SELECT tracking_no, CURRENT_DATE,CURRENT_DATE FROM orders WHERE tracking_no=new.tracking_no
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `index_no` int(100) NOT NULL,
  `emp_id` varchar(20) NOT NULL,
  `firstname` varchar(15) NOT NULL,
  `lastname` varchar(15) NOT NULL,
  `birthdate` date NOT NULL,
  `emailid` varchar(15) NOT NULL,
  `us` varchar(15) NOT NULL,
  `pass` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`index_no`, `emp_id`, `firstname`, `lastname`, `birthdate`, `emailid`, `us`, `pass`) VALUES
(1, 'A001', 'Asfar', 'Sharief', '1998-07-15', 'asfar@faa.com', 'asfar015', 'asfar'),
(2, 'E002', 'Abhimanyu', 'Agarwala', '1998-08-24', 'abhi@faa.com', 'abhi', 'abhi'),
(3, 'E003', 'Faaiz', 'Ahmed', '1998-01-28', 'faiz@faa.com', 'faaiz', 'faaiz');

-- --------------------------------------------------------

--
-- Table structure for table `track_details`
--

CREATE TABLE `track_details` (
  `index_no` int(11) NOT NULL,
  `tracking_number` int(20) NOT NULL,
  `date_in` date NOT NULL,
  `date_dispatched` date NOT NULL,
  `status_order` varchar(15) NOT NULL,
  `date_delivery` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `track_details`
--

INSERT INTO `track_details` (`index_no`, `tracking_number`, `date_in`, `date_dispatched`, `status_order`, `date_delivery`) VALUES
(27, 5515, '2018-11-13', '2018-11-13', '', '2018-12-03'),
(28, 5516, '2018-11-13', '2018-11-13', '', '2018-12-06'),
(29, 5517, '2018-11-13', '2018-11-13', 'On Time', '2018-12-06'),
(30, 5518, '2018-11-13', '2018-11-13', '', '0000-00-00'),
(31, 5519, '2018-11-13', '2018-11-13', '', '0000-00-00'),
(32, 5520, '2018-11-13', '2018-11-13', '', '0000-00-00'),
(33, 5521, '2018-11-14', '2018-11-14', '', '0000-00-00'),
(34, 5522, '2018-11-14', '2018-11-14', 'On time', '2018-11-16'),
(35, 5523, '2018-12-31', '2018-12-31', 'On Time', '2018-12-06'),
(37, 5525, '2018-12-31', '2018-12-31', '', '0000-00-00'),
(38, 5526, '2018-12-31', '2018-12-31', '', '0000-00-00'),
(39, 5527, '2018-12-31', '2018-12-31', 'On Time', '2018-12-06'),
(40, 5528, '2018-12-03', '2018-12-03', 'On Time', '2018-12-06'),
(41, 5529, '2018-12-04', '2018-12-04', 'On Time', '2018-12-07'),
(42, 5530, '2018-12-08', '2018-12-08', 'On Time', '2018-12-11'),
(43, 5531, '2018-12-08', '2018-12-08', '', '0000-00-00'),
(44, 5532, '2018-12-08', '2018-12-08', 'Delayed', '2018-12-09'),
(45, 5533, '2018-12-08', '2018-12-08', 'Delivered', '2018-12-08'),
(46, 5534, '2018-12-08', '2018-12-08', 'On Time', '2018-12-15'),
(47, 5535, '2018-12-08', '2018-12-08', 'On Time', '2018-12-10'),
(48, 5536, '2018-12-09', '2018-12-09', 'On Time', '2018-12-16'),
(49, 5537, '2019-01-15', '2019-01-15', 'On Time', '2019-01-22'),
(50, 5538, '2019-08-02', '2019-08-02', 'On Time', '2019-08-03');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cost`
--
ALTER TABLE `cost`
  ADD PRIMARY KEY (`index_no`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`tracking_no`);

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`emp_id`);

--
-- Indexes for table `track_details`
--
ALTER TABLE `track_details`
  ADD PRIMARY KEY (`index_no`),
  ADD KEY `tracking_number` (`tracking_number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cost`
--
ALTER TABLE `cost`
  MODIFY `index_no` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `tracking_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5539;

--
-- AUTO_INCREMENT for table `track_details`
--
ALTER TABLE `track_details`
  MODIFY `index_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `track_details`
--
ALTER TABLE `track_details`
  ADD CONSTRAINT `track_details_ibfk_1` FOREIGN KEY (`tracking_number`) REFERENCES `orders` (`tracking_no`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
