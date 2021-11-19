-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for sisac
CREATE DATABASE IF NOT EXISTS `sisac` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;
USE `sisac`;

-- Dumping structure for table sisac.activos
CREATE TABLE IF NOT EXISTS `activos` (
  `id_activo` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `codigo_antiguo` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(4096) COLLATE utf8_spanish_ci NOT NULL,
  `estado` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `observaciones` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `foto` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `id_categoria` int(11) NOT NULL,
  `id_ubicacion` int(11) NOT NULL,
  `id_custodio` int(11) NOT NULL,
  PRIMARY KEY (`id_activo`),
  KEY `custodios_activos_fk` (`id_custodio`),
  KEY `ubicaciones_activos_fk` (`id_ubicacion`),
  KEY `categorias_activos_fk` (`id_categoria`),
  CONSTRAINT `categorias_activos_fk` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `custodios_activos_fk` FOREIGN KEY (`id_custodio`) REFERENCES `custodios` (`id_custodio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ubicaciones_activos_fk` FOREIGN KEY (`id_ubicacion`) REFERENCES `ubicaciones` (`id_ubicacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Dumping data for table sisac.activos: ~0 rows (approximately)
/*!40000 ALTER TABLE `activos` DISABLE KEYS */;
/*!40000 ALTER TABLE `activos` ENABLE KEYS */;

-- Dumping structure for table sisac.categorias
CREATE TABLE IF NOT EXISTS `categorias` (
  `id_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `observaciones` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Dumping data for table sisac.categorias: ~1 rows (approximately)
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` (`id_categoria`, `nombre`, `observaciones`) VALUES
	(1, 'SIN CATEGORIA', '');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;

-- Dumping structure for table sisac.custodios
CREATE TABLE IF NOT EXISTS `custodios` (
  `id_custodio` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `observaciones` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_custodio`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Dumping data for table sisac.custodios: ~1 rows (approximately)
/*!40000 ALTER TABLE `custodios` DISABLE KEYS */;
INSERT INTO `custodios` (`id_custodio`, `nombre`, `observaciones`) VALUES
	(1, 'SIN CUSTODIO', '');
/*!40000 ALTER TABLE `custodios` ENABLE KEYS */;

-- Dumping structure for table sisac.ubicaciones
CREATE TABLE IF NOT EXISTS `ubicaciones` (
  `id_ubicacion` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `observaciones` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_ubicacion`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Dumping data for table sisac.ubicaciones: ~1 rows (approximately)
/*!40000 ALTER TABLE `ubicaciones` DISABLE KEYS */;
INSERT INTO `ubicaciones` (`id_ubicacion`, `nombre`, `observaciones`) VALUES
	(1, 'SIN UBICACIÓN', '');
/*!40000 ALTER TABLE `ubicaciones` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
