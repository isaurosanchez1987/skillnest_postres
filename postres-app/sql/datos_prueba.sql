
-- Usuarios
INSERT INTO users (id, nombre, correo, password) VALUES
(1, 'Héctor', 'hector@example.com', '$2a$10$uR1BCXTCnxzk3s6m9sD9sOhRr7w.CDeFkGh6BKaYHDiWyLxn6KuWa'); -- contraseña: 1234

-- Postres
INSERT INTO postres (id, titulo, descripcion, ingredientes, imagen, usuario_id) VALUES
(1, 'Chocotorta', 'Postre clásico argentino de chocolate y dulce de leche', 'Galletitas, dulce de leche, queso crema', '', 1),
(2, 'Tres leches', 'Bizcocho bañado en tres tipos de leche', 'Leche condensada, evaporada, crema de leche', '', 1);

-- Likes
INSERT INTO likes (usuario_id, postre_id) VALUES (1, 2);
