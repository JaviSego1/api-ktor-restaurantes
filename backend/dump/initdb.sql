CREATE DATABASE IF NOT EXISTS dbRestaurante;

USE dbRestaurante;

CREATE TABLE IF NOT EXISTS Restaurante(
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100),
    descripcion TEXT,
    imagen VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tabla de restaurantes';

CREATE TABLE IF NOT EXISTS Usuarios(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255),
    token VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tabla de usuarios';

INSERT INTO Restaurante (titulo, descripcion, imagen) VALUES
('Disfrutar', 'Desde Barcelona, galardonado con estrellas Michelin, ofrece una experiencia gastronómica de vanguardia con platos creativos.', 'disfrutar.jpg'),
('Etxebarri', 'Desde Vizcaya, es conocido por su cocina de autor basada en la parrilla.', 'vizcaya.jpg'),
('Table by Bruno Verjus', 'Desde París, restaurante que fusiona ingredientes locales con técnicas modernas, ofreciendo menús degustación innovadores.', 'brunoverjus.jpg'),
('DiverXO', 'Desde Madrid, el chef David Muñoz crea platos únicos que combinan la cocina asiática y mediterránea.', 'driverxo.jpg'),
('Maido', 'Desde Lima, Perú, restaurante que fusiona lo mejor de Japón y Perú, dirigido por el chef Mitsuharu Tsumura.', 'maido.jpg'),
('Atomix', 'Desde Nueva York, ofrece una experiencia gastronómica coreana moderna, con menús degustación innovadores.', 'atomix.jpg'),
('Quintonil', 'Desde Ciudad de México, dirigido por el chef Jorge Vallejo, destaca por su reinterpretación de la cocina mexicana.', 'quintonil.jpg'),
('Alchemist', 'Desde Copenhague, combina arte, ciencia y gastronomía en una experiencia multisensorial única.', 'alchemist.jpg');

INSERT INTO Usuarios (name, email, password, token) VALUES
('Juan Perez', 'juan.perez@email.com', 'b460b1982188f11d175f60ed670027e1afdd16558919fe47023ecd38329e0b7f', ''), -- hola123
('Maria Gomez', 'maria.gomez@email.com', '5301e4e7d2950ab5936ebdb2031b3a986c277acce0891db13642b57ba6eebf10', ''); -- clavesegura

