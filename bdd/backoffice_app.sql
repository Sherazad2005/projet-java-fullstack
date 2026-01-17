-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 17 jan. 2026 à 17:22
-- Version du serveur : 8.2.0
-- Version de PHP : 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `backoffice_app`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `name`) VALUES
(1, 'Thés verts'),
(2, 'Thés noirs'),
(3, 'Oolong'),
(4, 'Infusions'),
(5, 'Matcha');

-- --------------------------------------------------------

--
-- Structure de la table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `produit_id` bigint NOT NULL,
  `quantite` int NOT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `status` enum('PENDING','PROCESSING','SHIPPED','DELIVERED') NOT NULL,
  `order_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK3vygk69mkqbq50xuccwvnv7bj` (`produit_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `orders`
--

INSERT INTO `orders` (`id`, `user_id`, `produit_id`, `quantite`, `total_amount`, `status`, `order_date`) VALUES
(1, 5, 4, 2, 13.80, 'PENDING', '2026-01-17 17:38:53'),
(2, 4, 5, 1, 12.90, 'PENDING', '2026-01-17 17:49:42'),
(3, 6, 1, 2, 19.80, 'PENDING', '2026-01-17 17:53:17');

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` text,
  `price` decimal(10,2) NOT NULL,
  `category_id` bigint DEFAULT NULL,
  `stock_quantity` int NOT NULL DEFAULT '0',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `lien_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK46602ij41airsr6h4gp8exijc` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `product`
--

INSERT INTO `product` (`id`, `name`, `description`, `price`, `category_id`, `stock_quantity`, `created_at`, `lien_image`) VALUES
(1, 'Sencha Japon', 'Thé vert japonais frais et végétal', 9.90, 1, 50, '2026-01-17 15:28:57', 'https://picsum.photos/seed/sencha/400/300'),
(2, 'Gunpowder Chine', 'Thé vert roulé, notes fumées légères', 7.50, 1, 35, '2026-01-17 15:28:57', 'https://picsum.photos/seed/gunpowder/400/300'),
(3, 'Earl Grey', 'Thé noir à la bergamote', 8.20, 2, 60, '2026-01-17 15:28:57', 'https://picsum.photos/seed/earlgrey/400/300'),
(4, 'Assam', 'Thé noir corsé, parfait au petit-déjeuner', 6.90, 2, 40, '2026-01-17 15:28:57', 'https://picsum.photos/seed/assam/400/300'),
(5, 'Oolong Milky', 'Oolong doux, notes lactées', 12.90, 3, 25, '2026-01-17 15:28:57', 'https://picsum.photos/seed/oolong/400/300'),
(6, 'Rooibos Vanille', 'Infusion sans théine, vanillée', 5.90, 4, 80, '2026-01-17 15:28:57', 'https://picsum.photos/seed/rooibos/400/300'),
(7, 'Matcha Cérémonie', 'Matcha premium pour préparation traditionnelle', 19.90, 5, 15, '2026-01-17 15:28:57', 'https://picsum.photos/seed/matcha/400/300'),
(8, 'Thé Blanc Pai Mu Tan', 'Thé blanc doux et floral, faible en théine, très délicat en bouche', 10.00, NULL, 26, NULL, 'https://picsum.photos/seed/paimutan/400/300');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password` varchar(255) NOT NULL,
  `roles` enum('USER','ADMIN') NOT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `roles`, `enabled`, `created_at`) VALUES
(1, 'admin', 'admin@demo.com', '$2a$10$REPLACE_WITH_BCRYPT_HASH', 'ADMIN', 1, '2026-01-17 15:28:57'),
(2, 'shera', 'shera@demo.com', '$2a$10$REPLACE_WITH_BCRYPT_HASH', 'USER', 1, '2026-01-17 15:28:57'),
(3, 'sherazad', 'sherazad.abdallah2005@gmail.com', '$2a$10$OV5e0KILq4Qq9J1lwiWgQudaVPQMRl0xJ//B6PlTbxy3caWL.oEp2', 'USER', 1, '2026-01-17 17:08:55'),
(4, 'shersher', 'sherazad.abdallah@gmail.com', '$2a$10$XyogUM4bhIPc1pia5ARHHeLJdRewQRmrQk3PRDnJ8OXXBHIy.AKS.', 'ADMIN', 1, '2026-01-17 17:11:32'),
(5, 'test', 'test@test.com', '$2a$10$tKUYhijgK8LgZcsGrJ0WluhNshJetqyKiQT8dhjv1CbR5NxAs8MGS', 'USER', 1, '2026-01-17 17:38:24'),
(6, 'test1', 'test1@test.com', '$2a$10$93T/0RTHFuWijCo58RXTFOP6O82o2n9EcSptlaTqXy7PCElHIv1W.', 'USER', 1, '2026-01-17 17:52:55');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
