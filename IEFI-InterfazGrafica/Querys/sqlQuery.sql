CREATE DATABASE IF NOT EXISTS juego_batallas;
USE juego_batallas;

-- ===============================================
-- TABLA: personajes
-- ===============================================
CREATE TABLE personajes (
    id_personaje INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apodo VARCHAR(50) NOT NULL,
    tipo ENUM('Héroe', 'Villano') NOT NULL,
    vida_final INT DEFAULT 0,
    ataque INT DEFAULT 0,
    defensa INT DEFAULT 0,
    victorias INT DEFAULT 0,
    supremos_usados INT DEFAULT 0,
    armas_invocadas INT DEFAULT 0
);
-- ===============================================
-- TABLA: batallas
-- ===============================================
CREATE TABLE batallas (
    id_batalla INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    heroe_id INT NOT NULL,
    villano_id INT NOT NULL,
    ganador_id INT NULL,
    turnos INT DEFAULT 0,

    mayor_ataque INT DEFAULT 0,
    id_jugador_mayor_ataque INT NULL,

    FOREIGN KEY (heroe_id) REFERENCES personajes(id_personaje)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (villano_id) REFERENCES personajes(id_personaje)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ganador_id) REFERENCES personajes(id_personaje)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (id_jugador_mayor_ataque) REFERENCES personajes(id_personaje)
        ON DELETE SET NULL ON UPDATE CASCADE
);

-- ===============================================
-- TABLA: estado_partida
-- ===============================================
CREATE TABLE estado_partida (
    id_estado INT AUTO_INCREMENT PRIMARY KEY,
    id_batalla INT,
    fecha_guardado DATETIME DEFAULT CURRENT_TIMESTAMP,
    heroe_id INT NOT NULL,
    villano_id INT NOT NULL,
    vida_heroe INT DEFAULT 0,
    vida_villano INT DEFAULT 0,
    bendicion_heroe INT DEFAULT 0,
    bendicion_villano INT DEFAULT 0,
    ataque_heroe INT DEFAULT 0,
    defensa_heroe INT DEFAULT 0,
    ataque_villano INT DEFAULT 0,
    defensa_villano INT DEFAULT 0,
    turno_actual INT DEFAULT 1,
    finalizada BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_batalla) REFERENCES batallas(id_batalla)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (heroe_id) REFERENCES personajes(id_personaje)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (villano_id) REFERENCES personajes(id_personaje)
        ON DELETE CASCADE ON UPDATE CASCADE
);

