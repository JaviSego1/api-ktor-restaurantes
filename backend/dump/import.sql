CREATE TABLE Restaurante(
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100),
    descripcion TEXT,
    token VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tabla de restaurantes';

INSERT INTO Restaurante (titulo, descripcion, token) VALUES
('Disfrutar', 'Desde Barcelona, galardonado con estrellas Michelin, ofrece una experiencia gastronómica de vanguardia con platos creativos.', ''),
('Etxebarri', 'Desde Vizcaya, es conocido por su cocina de autor basada en la parrilla.', ''),
('Table by Bruno Verjus', 'Desde París, restaurante que fusiona ingredientes locales con técnicas modernas, ofreciendo menús degustación innovadores.', ''),
('DiverXO', 'Desde Madrid, el chef David Muñoz crea platos únicos que combinan la cocina asiática y mediterránea.', ''),
('Maido', 'Desde Lima, Perú, restaurante que fusiona lo mejor de Japón y Perú, dirigido por el chef Mitsuharu Tsumura.', ''),
('Atomix', 'Desde Nueva York, ofrece una experiencia gastronómica coreana moderna, con menús degustación innovadores.', ''),
('Quintonil', 'Desde Ciudad de México, dirigido por el chef Jorge Vallejo, destaca por su reinterpretación de la cocina mexicana.', ''),
('Alchemist', 'Desde Copenhague, combina arte, ciencia y gastronomía en una experiencia multisensorial única.', '');
