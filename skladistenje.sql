-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 17, 2018 at 06:27 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 5.6.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `skladistenje`
--

-- --------------------------------------------------------

--
-- Table structure for table `artikal`
--

CREATE TABLE `artikal` (
  `ID` int(11) NOT NULL,
  `Naziv` varchar(20) NOT NULL,
  `Opis` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `artikal`
--

INSERT INTO `artikal` (`ID`, `Naziv`, `Opis`) VALUES
(1, 'Kemijska', 'Uredski materijal'),
(2, 'Olovka', 'asasda');

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `ID` int(11) NOT NULL,
  `ID_Tip_Korisnika` int(11) NOT NULL,
  `ID_Objekta` int(11) NOT NULL,
  `Ime` varchar(20) NOT NULL,
  `Sifra` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`ID`, `ID_Tip_Korisnika`, `ID_Objekta`, `Ime`, `Sifra`) VALUES
(5, 1, 1, 'Admin', 'Admin'),
(6, 2, 1, 'Pero', '12345'),
(7, 3, 1, 'Lidija', '12345'),
(8, 2, 13, 'PeroPeric', '12345'),
(9, 2, 15, 'IvoIvic', '12345'),
(12, 2, 15, 'MaraMaric', '12345'),
(13, 2, 16, 'SimeSimic', '12345'),
(14, 2, 15, 'asas', 'asas'),
(15, 2, 18, 'fff', 'fff'),
(16, 2, 19, 'www', 'www'),
(17, 2, 15, 'qqq', 'qqq'),
(18, 3, 14, 'Andrea', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `narudzba`
--

CREATE TABLE `narudzba` (
  `ID` int(11) NOT NULL,
  `ID_Objekta` int(11) NOT NULL,
  `ID_Artikla` int(11) NOT NULL,
  `Kolicina` int(11) NOT NULL,
  `Cijena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `narudzba`
--

INSERT INTO `narudzba` (`ID`, `ID_Objekta`, `ID_Artikla`, `Kolicina`, `Cijena`) VALUES
(1, 1, 1, 5, 12),
(2, 1, 1, 50, 10),
(3, 1, 1, 5, 20),
(4, 1, 1, 5, 20),
(5, 1, 1, 5, 20),
(6, 1, 1, 5, 20),
(7, 1, 1, 5, 20),
(8, 1, 1, 5, 20),
(9, 1, 1, 5, 20),
(10, 1, 1, 5, 20),
(11, 1, 1, 5, 20),
(12, 1, 1, 5, 20),
(13, 1, 1, 5, 20),
(14, 1, 1, 2, 2),
(15, 1, 1, 4, 1),
(16, 1, 1, 10, 2),
(17, 1, 1, 10, 2),
(18, 1, 1, 10, 2),
(19, 1, 1, 10, 2),
(20, 1, 1, 10, 2),
(21, 1, 1, 2, 2),
(22, 1, 1, 20, 2),
(23, 1, 1, 30, 2),
(24, 1, 1, 33, 1),
(25, 1, 1, 22, 1),
(26, 1, 1, 5, 5),
(27, 1, 1, 2, 12);

-- --------------------------------------------------------

--
-- Table structure for table `objekat`
--

CREATE TABLE `objekat` (
  `ID` int(11) NOT NULL,
  `ID_Tip_Objekta` int(11) NOT NULL,
  `Grad` varchar(20) NOT NULL,
  `Naziv` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `objekat`
--

INSERT INTO `objekat` (`ID`, `ID_Tip_Objekta`, `Grad`, `Naziv`) VALUES
(1, 1, 'Mostar', 'Skladište 1'),
(13, 1, 'Siroki Briejg', 'Skladiste trn'),
(14, 2, 'Siroki', 'Poslovnica 1'),
(15, 2, 'mostar', 'poslovnica23'),
(16, 1, 'Grude', 'Grude22'),
(17, 2, 'Siroki', 'p1'),
(18, 2, 'fff', 'fff'),
(19, 1, 'Makarska', 'Skladistebb'),
(20, 1, 'ww', 'ww'),
(21, 2, 'qqqqq', 'qqqq');

-- --------------------------------------------------------

--
-- Table structure for table `stanje`
--

CREATE TABLE `stanje` (
  `ID_Objekta` int(11) NOT NULL,
  `ID_Artikla` int(11) NOT NULL,
  `Kolicina` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stanje`
--

INSERT INTO `stanje` (`ID_Objekta`, `ID_Artikla`, `Kolicina`) VALUES
(1, 1, 17),
(14, 1, 25),
(17, 1, 10);

-- --------------------------------------------------------

--
-- Table structure for table `tip_korisnika`
--

CREATE TABLE `tip_korisnika` (
  `ID` int(11) NOT NULL,
  `Naziv` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tip_korisnika`
--

INSERT INTO `tip_korisnika` (`ID`, `Naziv`) VALUES
(1, 'Administrator'),
(2, 'Skladistar'),
(3, 'Trgovac');

-- --------------------------------------------------------

--
-- Table structure for table `tip_objekta`
--

CREATE TABLE `tip_objekta` (
  `Id` int(11) NOT NULL,
  `Naziv` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tip_objekta`
--

INSERT INTO `tip_objekta` (`Id`, `Naziv`) VALUES
(1, 'Skladište'),
(2, 'Poslovnica');

-- --------------------------------------------------------

--
-- Table structure for table `zahtjev`
--

CREATE TABLE `zahtjev` (
  `ID` int(11) NOT NULL,
  `ID_Korisnika` int(11) NOT NULL,
  `ID_Artikla` int(11) NOT NULL,
  `ID_Objekta` int(11) NOT NULL,
  `Kolicina` int(11) NOT NULL,
  `Poruka` varchar(100) NOT NULL,
  `Status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `zahtjev`
--

INSERT INTO `zahtjev` (`ID`, `ID_Korisnika`, `ID_Artikla`, `ID_Objekta`, `Kolicina`, `Poruka`, `Status`) VALUES
(4, 7, 2, 1, 50, 'Ocu da psiem', '0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `artikal`
--
ALTER TABLE `artikal`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `korisnik_Objekat` (`ID_Objekta`),
  ADD KEY `korisnik_TipKorisnika` (`ID_Tip_Korisnika`);

--
-- Indexes for table `narudzba`
--
ALTER TABLE `narudzba`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Narudzba_Artikal` (`ID_Artikla`),
  ADD KEY `Narudzba_Objekat` (`ID_Objekta`);

--
-- Indexes for table `objekat`
--
ALTER TABLE `objekat`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `objekat_tipObjekta` (`ID_Tip_Objekta`);

--
-- Indexes for table `stanje`
--
ALTER TABLE `stanje`
  ADD PRIMARY KEY (`ID_Objekta`,`ID_Artikla`),
  ADD KEY `Stanje_Artikal` (`ID_Artikla`);

--
-- Indexes for table `tip_korisnika`
--
ALTER TABLE `tip_korisnika`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tip_objekta`
--
ALTER TABLE `tip_objekta`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `zahtjev`
--
ALTER TABLE `zahtjev`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ZahtjevArtikal` (`ID_Artikla`),
  ADD KEY `ZahtjevObjekt` (`ID_Objekta`),
  ADD KEY `Korisnik_Zahtjev` (`ID_Korisnika`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `artikal`
--
ALTER TABLE `artikal`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `narudzba`
--
ALTER TABLE `narudzba`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `objekat`
--
ALTER TABLE `objekat`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `tip_korisnika`
--
ALTER TABLE `tip_korisnika`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tip_objekta`
--
ALTER TABLE `tip_objekta`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `zahtjev`
--
ALTER TABLE `zahtjev`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD CONSTRAINT `korisnik_Objekat` FOREIGN KEY (`ID_Objekta`) REFERENCES `objekat` (`ID`),
  ADD CONSTRAINT `korisnik_TipKorisnika` FOREIGN KEY (`ID_Tip_Korisnika`) REFERENCES `tip_korisnika` (`ID`);

--
-- Constraints for table `narudzba`
--
ALTER TABLE `narudzba`
  ADD CONSTRAINT `Narudzba_Artikal` FOREIGN KEY (`ID_Artikla`) REFERENCES `artikal` (`ID`),
  ADD CONSTRAINT `Narudzba_Objekat` FOREIGN KEY (`ID_Objekta`) REFERENCES `objekat` (`ID`);

--
-- Constraints for table `objekat`
--
ALTER TABLE `objekat`
  ADD CONSTRAINT `objekat_tipObjekta` FOREIGN KEY (`ID_Tip_Objekta`) REFERENCES `tip_objekta` (`Id`);

--
-- Constraints for table `stanje`
--
ALTER TABLE `stanje`
  ADD CONSTRAINT `Stanje_Artikal` FOREIGN KEY (`ID_Artikla`) REFERENCES `artikal` (`ID`),
  ADD CONSTRAINT `Stanje_Objekat` FOREIGN KEY (`ID_Objekta`) REFERENCES `objekat` (`ID`);

--
-- Constraints for table `zahtjev`
--
ALTER TABLE `zahtjev`
  ADD CONSTRAINT `Korisnik_Zahtjev` FOREIGN KEY (`ID_Korisnika`) REFERENCES `korisnik` (`ID`),
  ADD CONSTRAINT `ZahtjevArtikal` FOREIGN KEY (`ID_Artikla`) REFERENCES `artikal` (`ID`),
  ADD CONSTRAINT `ZahtjevObjekt` FOREIGN KEY (`ID_Objekta`) REFERENCES `objekat` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
