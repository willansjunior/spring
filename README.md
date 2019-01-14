# spring

URL projeto: http://localhost:8080/swagger-ui.html

Script banco
-- --------------------------------------------------------
-- Servidor:                     localhost
-- Versão do servidor:           5.7.24 - MySQL Community Server (GPL)
-- OS do Servidor:               Linux
-- HeidiSQL Versão:              9.5.0.5278
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para spring
DROP DATABASE IF EXISTS `spring`;
CREATE DATABASE IF NOT EXISTS `spring` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `spring`;

-- Copiando estrutura para tabela spring.pessoa
DROP TABLE IF EXISTS `pessoa`;
CREATE TABLE IF NOT EXISTS `pessoa` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `salario` double(10,2) NOT NULL,
  `unidade` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_pessoa_unidade` (`unidade`),
  CONSTRAINT `FK_pessoa_unidade` FOREIGN KEY (`unidade`) REFERENCES `unidade` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela spring.pessoa: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
REPLACE INTO `pessoa` (`codigo`, `nome`, `salario`, `unidade`) VALUES
	(1, 'Willans', 1000.00, 1);
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;

-- Copiando estrutura para tabela spring.unidade
DROP TABLE IF EXISTS `unidade`;
CREATE TABLE IF NOT EXISTS `unidade` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela spring.unidade: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `unidade` DISABLE KEYS */;
REPLACE INTO `unidade` (`codigo`, `nome`) VALUES
	(1, 'Teste');
/*!40000 ALTER TABLE `unidade` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
