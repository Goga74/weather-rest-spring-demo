-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               8.0.16 - MySQL Community Server - GPL
-- Операционная система:         Win64
-- HeidiSQL Версия:              10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных weather
CREATE DATABASE IF NOT EXISTS `weather` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `weather`;

-- Дамп структуры для таблица weather.weather_entity
CREATE TABLE IF NOT EXISTS `weather_entity` (
  `id` int(11) NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `temperature` double NOT NULL,
  `time` bigint(20) NOT NULL,
  `timezone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы weather.weather_entity: 1 rows
DELETE FROM `weather_entity`;
/*!40000 ALTER TABLE `weather_entity` DISABLE KEYS */;
INSERT INTO `weather_entity` (`id`, `country`, `ip`, `latitude`, `longitude`, `summary`, `temperature`, `time`, `timezone`) VALUES
	(1, 'Latvia', '212.142.110.119', 56.9496, 24.0978, 'Clear', 5.21, 1559190292, 'Europe/Riga');
/*!40000 ALTER TABLE `weather_entity` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
