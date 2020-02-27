-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 25, 2020 at 05:21 PM
-- Server version: 5.7.29-0ubuntu0.16.04.1
-- PHP Version: 7.2.25-1+ubuntu16.04.1+deb.sury.org+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mentorondemand`
--

-- --------------------------------------------------------

--
-- Table structure for table `fee`
--

CREATE TABLE `fee` (
  `id` int(11) NOT NULL,
  `training_id` int(11) NOT NULL,
  `total_fees` double NOT NULL,
  `recived_amount` double NOT NULL,
  `remaining_payment` double NOT NULL,
  `payment` double NOT NULL,
  `installment_status` int(2) NOT NULL DEFAULT '0',
  `date` datetime NOT NULL,
  `payment_status` int(2) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fee`
--

INSERT INTO `fee` (`id`, `training_id`, `total_fees`, `recived_amount`, `remaining_payment`, `payment`, `installment_status`, `date`, `payment_status`) VALUES
(1, 2, 2000, 400, 1600, 400, 1, '2020-02-21 03:07:33', 1),
(2, 3, 1500, 300, 1200, 300, 1, '2020-02-21 03:11:47', 1),
(3, 2, 2000, 800, 1200, 400, 2, '2020-02-21 03:13:35', 1),
(4, 3, 1500, 600, 900, 300, 2, '2020-02-21 03:13:46', 1),
(5, 2, 2000, 1200, 800, 400, 3, '2020-02-21 03:14:26', 1),
(6, 3, 1500, 900, 600, 300, 3, '2020-02-21 03:16:01', 1),
(7, 2, 2000, 1600, 400, 400, 4, '2020-02-21 03:17:05', 1),
(8, 2, 2000, 2000, 0, 400, 5, '2020-02-21 03:17:46', 1),
(9, 3, 1500, 1200, 300, 300, 4, '2020-02-21 05:18:00', 1),
(10, 3, 1500, 1500, 0, 300, 5, '2020-02-21 05:18:34', 1);

-- --------------------------------------------------------

--
-- Table structure for table `mentorskills`
--

CREATE TABLE `mentorskills` (
  `id` int(11) NOT NULL,
  `mentor_id` int(11) NOT NULL,
  `technology_id` int(11) NOT NULL,
  `avg_rating` double NOT NULL DEFAULT '5' COMMENT 'average rating',
  `toc` varchar(50) NOT NULL,
  `prerequisites` varchar(50) NOT NULL,
  `fee` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mentorskills`
--

INSERT INTO `mentorskills` (`id`, `mentor_id`, `technology_id`, `avg_rating`, `toc`, `prerequisites`, `fee`) VALUES
(2, 6, 1, 4, 'toc', 'prerequisite', 2000),
(3, 6, 2, 5, 'toc', 'prerequisite', 1500),
(4, 7, 3, 5, 'toc', 'prerequisite', 5000),
(5, 7, 6, 5, 'toc', 'prerequisite', 5000),
(6, 7, 5, 5, 'toc', 'prerequisite', 1000),
(7, 13, 11, 5, 'toc', 'prerequisite', 3000),
(8, 13, 12, 5, 'toc', 'prerequisite', 5000),
(10, 7, 1, 5, 'toc', 'prerequisite', 1000),
(12, 5, 15, 5, 'toc', 'prerequisite', 1000);

-- --------------------------------------------------------

--
-- Table structure for table `mentor_slots`
--

CREATE TABLE `mentor_slots` (
  `id` int(11) NOT NULL,
  `mentor_id` int(11) NOT NULL,
  `time_from` time NOT NULL,
  `time_to` time DEFAULT NULL,
  `isactive` int(1) NOT NULL DEFAULT '0' COMMENT '0-inActive, 1-active'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mentor_slots`
--

INSERT INTO `mentor_slots` (`id`, `mentor_id`, `time_from`, `time_to`, `isactive`) VALUES
(8, 5, '09:00:00', '11:00:00', 1),
(9, 5, '11:00:00', '13:00:00', 0),
(11, 5, '18:00:00', '20:00:00', 0),
(12, 6, '16:00:00', '18:00:00', 1),
(13, 6, '18:00:00', '20:00:00', 0),
(14, 6, '07:00:00', '08:00:00', 0),
(15, 6, '08:00:00', '09:00:00', 1),
(16, 7, '07:00:00', '09:00:00', 1),
(17, 7, '19:30:00', '20:30:00', 0),
(18, 13, '07:00:00', '08:00:00', 1),
(19, 13, '08:00:00', '09:00:00', 1),
(20, 13, '09:00:00', '10:00:00', 1),
(21, 13, '10:00:00', '11:00:00', 0),
(22, 13, '16:00:00', '17:00:00', 1),
(23, 13, '17:00:00', '18:00:00', 1),
(24, 13, '18:00:00', '19:00:00', 0),
(25, 16, '19:00:00', '20:00:00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `role_name` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `role_name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_MENTOR');

-- --------------------------------------------------------

--
-- Table structure for table `technologies`
--

CREATE TABLE `technologies` (
  `id` int(11) NOT NULL,
  `technology_name` varchar(50) NOT NULL,
  `isactive` int(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `technologies`
--

INSERT INTO `technologies` (`id`, `technology_name`, `isactive`) VALUES
(1, 'C', 1),
(2, 'C++', 1),
(3, 'Java', 1),
(4, 'Python', 1),
(5, 'JavaScript', 1),
(6, 'Spring', 1),
(7, 'Django', 1),
(8, 'Node.js', 1),
(11, 'Physics', 1),
(12, 'Maths', 1),
(14, 'Angular', 1),
(15, 'Political Science', 1);

-- --------------------------------------------------------

--
-- Table structure for table `training`
--

CREATE TABLE `training` (
  `id` int(11) NOT NULL,
  `mentor_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `slot_id` int(11) NOT NULL,
  `tech_id` int(11) NOT NULL,
  `progress` int(2) NOT NULL DEFAULT '0',
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `total_fee` double NOT NULL,
  `amount_received` double DEFAULT '0',
  `installment_status` int(2) NOT NULL DEFAULT '0',
  `rating` enum('1','2','3','4','5') DEFAULT '5',
  `request` int(11) DEFAULT '0',
  `isactive` int(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `training`
--

INSERT INTO `training` (`id`, `mentor_id`, `user_id`, `slot_id`, `tech_id`, `progress`, `start_date`, `end_date`, `total_fee`, `amount_received`, `installment_status`, `rating`, `request`, `isactive`) VALUES
(1, 7, 3, 16, 1, 0, NULL, NULL, 0, 0, 0, '5', 1, 1),
(2, 6, 3, 15, 1, 100, '2020-03-01', '2020-03-31', 2000, 2000, 5, '4', 4, 1),
(3, 6, 2, 12, 2, 100, '2020-03-01', '2020-03-31', 1500, 1500, 5, '5', 3, 1),
(4, 6, 12, 15, 1, 0, NULL, NULL, 0, 0, 0, '5', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `contact` varchar(15) NOT NULL,
  `role_id` int(3) NOT NULL,
  `linkedin_url` varchar(50) NOT NULL,
  `year_of_exp` int(11) NOT NULL,
  `isactive` int(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `contact`, `role_id`, `linkedin_url`, `year_of_exp`, `isactive`) VALUES
(1, 'admin.admin@mail.com', '$2y$12$fOoxsy.As1AzubT3WfUpEuQrEmDCqubZoRSQ/NJOGAv.nR/Bdirvi', 'Admin', 'Das', '9560214587', 1, 'admin.admin@linkedin.com', 0, 1),
(2, 'rahul.gandhi@mail.com', '$2y$12$fOoxsy.As1AzubT3WfUpEuQrEmDCqubZoRSQ/NJOGAv.nR/Bdirvi', 'Rahul', 'Gandhi', '9876543210', 2, 'rahul.gandhi@linkedin.com', 0, 1),
(3, 'raju.vaishnav@mail.com', '$2y$12$fOoxsy.As1AzubT3WfUpEuQrEmDCqubZoRSQ/NJOGAv.nR/Bdirvi', 'Raju', 'Vaishnav', '9828785321', 2, 'raju.vaishnav@linkedin.com', 0, 1),
(5, 'narendra.modi@mail.com', '$2y$12$fOoxsy.As1AzubT3WfUpEuQrEmDCqubZoRSQ/NJOGAv.nR/Bdirvi', 'Narendra', 'Modi', '8559967008', 3, 'narendra.modi@linkedin.com', 10, 1),
(6, 'abhishek.parjapat@mail.com', '$2y$12$fOoxsy.As1AzubT3WfUpEuQrEmDCqubZoRSQ/NJOGAv.nR/Bdirvi', 'Abhishek', 'Prajapat', '9234567809', 3, 'abhi.prajapat@linkedin.com', 6, 1),
(7, 'abhishek.shrivastav@mail.com', '$2y$12$fOoxsy.As1AzubT3WfUpEuQrEmDCqubZoRSQ/NJOGAv.nR/Bdirvi', 'Abhishek', 'Shrivastav', '9079099277', 3, 'abhishek.shrivastav@linkedin.com', 5, 1),
(8, 'harish.kumar@mail.com', '$2y$12$fOoxsy.As1AzubT3WfUpEuQrEmDCqubZoRSQ/NJOGAv.nR/Bdirvi', 'Harish', 'Kumar', '9654321870', 2, 'harish.kumar@linkedin.com', 0, 1),
(9, 'deepak.raj@mail.com', '$2y$12$fOoxsy.As1AzubT3WfUpEuQrEmDCqubZoRSQ/NJOGAv.nR/Bdirvi', 'Deepak', 'Raj', '9012345678', 2, 'deepak.raj@linkedin.com', 0, 1),
(10, 'deepak.singh@mail.com', '$2y$12$fOoxsy.As1AzubT3WfUpEuQrEmDCqubZoRSQ/NJOGAv.nR/Bdirvi', 'Deepak', 'Singh', '9876543201', 2, 'deepak.singh@linkedin.com', 0, 1),
(11, 'kapil.sharma@mail.com', '$2y$12$fOoxsy.As1AzubT3WfUpEuQrEmDCqubZoRSQ/NJOGAv.nR/Bdirvi', 'Kapil', 'Sharma', '9856321450', 2, 'kapil.sharma@linkedin.com', 0, 1),
(12, 'abhishek.patel@mail.com', '$2y$12$fOoxsy.As1AzubT3WfUpEuQrEmDCqubZoRSQ/NJOGAv.nR/Bdirvi', 'Abhishek', 'Patel', '9630258741', 2, 'abhishek.patel@linkedin.com', 0, 1),
(13, 'pradeep.singh@mail.com', '$2y$12$fOoxsy.As1AzubT3WfUpEuQrEmDCqubZoRSQ/NJOGAv.nR/Bdirvi', 'Pradeep', 'Singh', '9654785496', 3, 'pradeep.singh@linkedin.com', 7, 1),
(14, 'himanshu.solanki@mail.com', '$2a$10$ZxdzknVVM1ZU5U485pOCWu6VRAagcR3mQVw7jGnFjl44dAePsKBDm', 'Himanshu', 'Solanki', '8559967006', 2, 'himanshu.solanki@linkedin.com', 0, 1),
(16, 'prithviraj.chauhan@mail.com', '$2a$10$uRm00a6nZhP2aa3OSw6GPO0LpSYIMIQQh/ljHGuVmL.RwntDQtGJq', 'Prithvi Raj', 'Chauhan', '9828785320', 3, 'prithviraj.chauhan@linkedin.com', 15, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2),
(12, 2),
(14, 2),
(5, 3),
(6, 3),
(7, 3),
(13, 3),
(16, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fee`
--
ALTER TABLE `fee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `training_id` (`training_id`);

--
-- Indexes for table `mentorskills`
--
ALTER TABLE `mentorskills`
  ADD PRIMARY KEY (`id`),
  ADD KEY `foreign_key_mentor_id` (`mentor_id`),
  ADD KEY `foreign_key_tech_id` (`technology_id`);

--
-- Indexes for table `mentor_slots`
--
ALTER TABLE `mentor_slots`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mentor_id` (`mentor_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `technologies`
--
ALTER TABLE `technologies`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `training`
--
ALTER TABLE `training`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `tech_id` (`tech_id`),
  ADD KEY `slot_id` (`slot_id`),
  ADD KEY `mentor_id` (`mentor_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `contact` (`contact`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fee`
--
ALTER TABLE `fee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `mentorskills`
--
ALTER TABLE `mentorskills`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `mentor_slots`
--
ALTER TABLE `mentor_slots`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `technologies`
--
ALTER TABLE `technologies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `training`
--
ALTER TABLE `training`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `fee`
--
ALTER TABLE `fee`
  ADD CONSTRAINT `fee_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`id`);

--
-- Constraints for table `mentorskills`
--
ALTER TABLE `mentorskills`
  ADD CONSTRAINT `foreign_key_mentor_id` FOREIGN KEY (`mentor_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `foreign_key_tech_id` FOREIGN KEY (`technology_id`) REFERENCES `technologies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `mentor_slots`
--
ALTER TABLE `mentor_slots`
  ADD CONSTRAINT `mentor_slots_ibfk_1` FOREIGN KEY (`mentor_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `training`
--
ALTER TABLE `training`
  ADD CONSTRAINT `training_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `training_ibfk_2` FOREIGN KEY (`tech_id`) REFERENCES `technologies` (`id`),
  ADD CONSTRAINT `training_ibfk_3` FOREIGN KEY (`slot_id`) REFERENCES `mentor_slots` (`id`),
  ADD CONSTRAINT `training_ibfk_4` FOREIGN KEY (`mentor_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
