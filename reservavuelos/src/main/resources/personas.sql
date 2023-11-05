-- genero: 0 = MASCULINO, 1 = FEMENINO
-- tipo_documento: 0 = TARJETA_DE_IDENTIDAD, 1 = CEDULA_DE_CIUDADANIA 2 = REGISTRO_CIVIL, 3 = CEDULA_DE_CIUDADANIA, 4 = PASAPORTE

-- Persona 1
INSERT INTO persona (nombres, apellidos, genero, tipo_documento, numero_documento, fecha_nacimiento, pais_origen, pais_residencia, email, telefono)
VALUES ('James', 'Johnson', '0', '4', '7890123', '1988-06-25', 'Reino Unido', 'Reino Unido', 'james@example.com', '7890123456');

-- Persona 2
INSERT INTO persona (nombres, apellidos, genero, tipo_documento, numero_documento, fecha_nacimiento, pais_origen, pais_residencia, email, telefono)
VALUES ('Emily', 'Smith', '1', '0', '1234567', '1995-03-12', 'Estados Unidos', 'Estados Unidos', 'emily@example.com', '555123567');

-- Persona 3
INSERT INTO persona (nombres, apellidos, genero, tipo_documento, numero_documento, fecha_nacimiento, pais_origen, pais_residencia, email, telefono)
VALUES ('Carlos', 'García', '0', '1', '4567890', '1980-09-17', 'Colombia', 'Colombia', 'carlos@example.com', '3001234567');

-- Persona 4
INSERT INTO persona (nombres, apellidos, genero, tipo_documento, numero_documento, fecha_nacimiento, pais_origen, pais_residencia, email, telefono)
VALUES ('Sophie', 'Martinez', '1', '2', '9876543', '1992-11-28', 'España', 'España', 'sophie@example.com', '678987654');

-- Persona 5
INSERT INTO persona (nombres, apellidos, genero, tipo_documento, numero_documento, fecha_nacimiento, pais_origen, pais_residencia, email, telefono)
VALUES ('Luis', 'Rodríguez', '0', '3', '1122334', '1987-05-03', 'Argentina', 'Argentina', 'luis@example.com', '91155555555');

-- Persona 6
INSERT INTO persona (nombres, apellidos, genero, tipo_documento, numero_documento, fecha_nacimiento, pais_origen, pais_residencia, email, telefono)
VALUES ('Olivia', 'López', '1', '0', '9876543', '1990-12-15', 'España', 'España', 'olivia@example.com', '622987321');

-- Persona 7
INSERT INTO persona (nombres, apellidos, genero, tipo_documento, numero_documento, fecha_nacimiento, pais_origen, pais_residencia, email, telefono)
VALUES ('Daniel', 'Brown', '0', '1', '2345678', '1985-07-19', 'Reino Unido', 'Reino Unido', 'daniel@example.com', '2012345678');

-- Persona 8
INSERT INTO persona (nombres, apellidos, genero, tipo_documento, numero_documento, fecha_nacimiento, pais_origen, pais_residencia, email, telefono)
VALUES ('Mia', 'Wang', '1', '2', '3456789', '1998-02-08', 'China', 'China', 'mia@example.com', '861087654321');

-- Persona 9
INSERT INTO persona (nombres, apellidos, genero, tipo_documento, numero_documento, fecha_nacimiento, pais_origen, pais_residencia, email, telefono)
VALUES ('Eduardo', 'Gomez', '0', '3', '2233445', '1983-04-29', 'Argentina', 'Argentina', 'eduardo@example.com', '91144443333');

-- Persona 10
INSERT INTO persona (nombres, apellidos, genero, tipo_documento, numero_documento, fecha_nacimiento, pais_origen, pais_residencia, email, telefono)
VALUES ('Isabella', 'Hernández', '1', '4', '3456789', '1991-10-05', 'Colombia', 'Colombia', 'isabella@example.com', '3115557890');

SELECT * FROM persona;
