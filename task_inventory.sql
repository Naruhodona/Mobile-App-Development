-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2024 at 02:35 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `task_inventory`
--

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `task_id` int(25) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `created_time` varchar(255) NOT NULL,
  `finished_time` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `duration` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`task_id`, `title`, `description`, `category`, `created_time`, `finished_time`, `status`, `duration`) VALUES
(22, 'task 1 ', 'desc', 'normal', '2024-05-22', '', 'new', ''),
(23, 'task 2', 'desc', 'normal', '2024-05-22', '', 'in progress', ''),
(24, 'task 3', 'desc', 'urgent', '2024-05-22', '', 'new', ''),
(25, 'task 4', 'desc', 'urgent', '2024-05-22', '', 'new', ''),
(26, 'task 5', 'desc', 'important', '2024-05-22', '2024-05-29', 'done', '7 days'),
(27, 'task 6', 'desc', 'important', '2024-05-22', '', 'new', ''),
(28, 'new task', 'desc', 'normal', '2024-05-22', '', 'in progress', ''),
(29, 'important task', 'desc', 'urgent', '2024-05-22', '', 'new', ''),
(30, 'urgent task', 'desc', 'important', '2024-05-22', '', 'new', ''),
(31, 'normal', 'desc', 'normal', '2024-05-22', '2024-05-22', 'done', '0 days'),
(32, 'urgent', 'desc', 'urgent', '2024-05-22', '', 'new', ''),
(33, 'important', 'desc', 'important', '2024-05-22', '2024-05-22', 'done', '0 days'),
(34, 'important2', 'desc', 'important', '2024-05-22', '2024-05-29', 'done', '7 days'),
(35, 'important3', 'desc', 'important', '2024-05-22', '', 'new', ''),
(36, 'important4', 'dfesc', 'important', '2024-05-22', '', 'new', ''),
(37, 'important6', 'desc', 'important', '2024-05-22', '2024-05-22', 'done', '0 days'),
(38, 'laundry', 'laundry', 'important', '2024-05-29', '2024-05-29', 'done', '0 days');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`task_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `task_id` int(25) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
