-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2015 at 03:43 PM
-- Server version: 5.6.15-log
-- PHP Version: 5.5.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `racunalni_servis`
--

-- --------------------------------------------------------

--
-- Table structure for table `komponenteusluge`
--

CREATE TABLE IF NOT EXISTS `komponenteusluge` (
  `idKomponenteUsluge` int(11) NOT NULL AUTO_INCREMENT,
  `idZaposlenika` int(11) NOT NULL,
  `naziv` varchar(50) COLLATE utf8_bin NOT NULL,
  `kratkiOpis` varchar(250) COLLATE utf8_bin NOT NULL,
  `cijena` int(11) NOT NULL,
  PRIMARY KEY (`idKomponenteUsluge`),
  KEY `fk10` (`idZaposlenika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `kupac`
--

CREATE TABLE IF NOT EXISTS `kupac` (
  `idKupca` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) COLLATE utf8_bin NOT NULL,
  `prezime` varchar(50) COLLATE utf8_bin NOT NULL,
  `korisnickoIme` varchar(50) COLLATE utf8_bin NOT NULL,
  `lozinka` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idKupca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `narudzba`
--

CREATE TABLE IF NOT EXISTS `narudzba` (
  `idNarudzbe` int(11) NOT NULL AUTO_INCREMENT,
  `idKupca` int(11) NOT NULL,
  `datum` date NOT NULL,
  PRIMARY KEY (`idNarudzbe`),
  KEY `fk1` (`idKupca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `racun`
--

CREATE TABLE IF NOT EXISTS `racun` (
  `idRacuna` int(11) NOT NULL AUTO_INCREMENT,
  `idKupca` int(11) NOT NULL,
  `idZaposlenika` int(11) NOT NULL,
  `datum` date NOT NULL,
  `ukupnaCIjena` int(11) NOT NULL,
  PRIMARY KEY (`idRacuna`),
  KEY `fk3` (`idKupca`),
  KEY `fk11` (`idZaposlenika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `stavkenarudzbe`
--

CREATE TABLE IF NOT EXISTS `stavkenarudzbe` (
  `idStavkeNarudzbe` int(11) NOT NULL AUTO_INCREMENT,
  `idNarudzbe` int(11) NOT NULL,
  `idKomponenteUsluge` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL,
  PRIMARY KEY (`idStavkeNarudzbe`),
  KEY `fk6` (`idNarudzbe`),
  KEY `fk7` (`idKomponenteUsluge`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `stavkeracuna`
--

CREATE TABLE IF NOT EXISTS `stavkeracuna` (
  `idStavkeRacuna` int(11) NOT NULL AUTO_INCREMENT,
  `idRacuna` int(11) NOT NULL,
  `idKomponenteUsluge` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL,
  PRIMARY KEY (`idStavkeRacuna`),
  KEY `fk8` (`idKomponenteUsluge`),
  KEY `fk9` (`idRacuna`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `zaposlenik`
--

CREATE TABLE IF NOT EXISTS `zaposlenik` (
  `idZaposlenika` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) COLLATE utf8_bin NOT NULL,
  `prezime` varchar(50) COLLATE utf8_bin NOT NULL,
  `korisnickoIme` varchar(50) COLLATE utf8_bin NOT NULL,
  `lozinka` varchar(50) COLLATE utf8_bin NOT NULL,
  `mjestoStanovanja` varchar(250) COLLATE utf8_bin NOT NULL,
  `administrator` bit(1) NOT NULL,
  PRIMARY KEY (`idZaposlenika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `komponenteusluge`
--
ALTER TABLE `komponenteusluge`
  ADD CONSTRAINT `fk10` FOREIGN KEY (`idZaposlenika`) REFERENCES `zaposlenik` (`idZaposlenika`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `narudzba`
--
ALTER TABLE `narudzba`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`idKupca`) REFERENCES `kupac` (`idKupca`) ON UPDATE CASCADE;

--
-- Constraints for table `racun`
--
ALTER TABLE `racun`
  ADD CONSTRAINT `fk11` FOREIGN KEY (`idZaposlenika`) REFERENCES `zaposlenik` (`idZaposlenika`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk3` FOREIGN KEY (`idKupca`) REFERENCES `kupac` (`idKupca`) ON UPDATE CASCADE;

--
-- Constraints for table `stavkenarudzbe`
--
ALTER TABLE `stavkenarudzbe`
  ADD CONSTRAINT `fk6` FOREIGN KEY (`idNarudzbe`) REFERENCES `narudzba` (`idNarudzbe`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk7` FOREIGN KEY (`idKomponenteUsluge`) REFERENCES `komponenteusluge` (`idKomponenteUsluge`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `stavkeracuna`
--
ALTER TABLE `stavkeracuna`
  ADD CONSTRAINT `fk8` FOREIGN KEY (`idKomponenteUsluge`) REFERENCES `komponenteusluge` (`idKomponenteUsluge`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk9` FOREIGN KEY (`idRacuna`) REFERENCES `racun` (`idRacuna`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
