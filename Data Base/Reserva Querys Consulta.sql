-- Consultar todos los vuelos

SELECT v.id, 
o.nombre_ciudad as ciudad_destino, d.nombre_ciudad as ciudad_llegada, 
fecha_salida, fecha_llegada,
asientos_disponibles, a.nombre_aerolinea, precio, horario, t.tipo 
FROM vuelo v 
JOIN ciudad_origen o ON o.id = v.id
JOIN ciudad_destino d ON d.id = v.id
JOIN aerolinea a ON a.id = v.id
JOIN tipo_vuelo t ON t.id = v.id;

-- Consultar vuelo a través de ciudad origen

SELECT v.id, 
o.nombre_ciudad as ciudad_destino, d.nombre_ciudad as ciudad_llegada, 
fecha_salida, fecha_llegada,
asientos_disponibles, a.nombre_aerolinea, precio, horario, t.tipo 
FROM vuelo v 
JOIN ciudad_origen o ON o.id = v.id
JOIN ciudad_destino d ON d.id = v.id
JOIN aerolinea a ON a.id = v.id
JOIN tipo_vuelo t ON t.id = v.id
WHERE o.nombre_ciudad = ""; 

-- Consultar vuelo a través de ciudad destino

SELECT v.id, 
o.nombre_ciudad as ciudad_destino, d.nombre_ciudad as ciudad_llegada, 
fecha_salida, fecha_llegada,
asientos_disponibles, a.nombre_aerolinea, precio, horario, t.tipo 
FROM vuelo v 
JOIN ciudad_origen o ON o.id = v.id
JOIN ciudad_destino d ON d.id = v.id
JOIN aerolinea a ON a.id = v.id
JOIN tipo_vuelo t ON t.id = v.id
WHERE d.nombre_ciudad = ""; 

-- Consultar vuelo a través de un rango de fechas de salida

SELECT v.id, 
o.nombre_ciudad as ciudad_destino, d.nombre_ciudad as ciudad_llegada, 
fecha_salida, fecha_llegada,
asientos_disponibles, a.nombre_aerolinea, precio, horario, t.tipo 
FROM vuelo v 
JOIN ciudad_origen o ON o.id = v.id
JOIN ciudad_destino d ON d.id = v.id
JOIN aerolinea a ON a.id = v.id
JOIN tipo_vuelo t ON t.id = v.id
WHERE v.fecha_salida BETWEEN 'YYYY-MM-DD' AND 'YYYY-MM-DD';

-- Consultar vuelo a través de un rango de fechas de llegada

SELECT v.id, 
o.nombre_ciudad as ciudad_destino, d.nombre_ciudad as ciudad_llegada, 
fecha_salida, fecha_llegada,
asientos_disponibles, a.nombre_aerolinea, precio, horario, t.tipo 
FROM vuelo v 
JOIN ciudad_origen o ON o.id = v.id
JOIN ciudad_destino d ON d.id = v.id
JOIN aerolinea a ON a.id = v.id
JOIN tipo_vuelo t ON t.id = v.id
WHERE v.fecha_llegada BETWEEN 'YYYY-MM-DD' AND 'YYYY-MM-DD';

-- Consultar vuelo a través de fecha de asientos disponibles

SELECT v.id, 
o.nombre_ciudad as ciudad_destino, d.nombre_ciudad as ciudad_llegada, 
fecha_salida, fecha_llegada,
asientos_disponibles, a.nombre_aerolinea, precio, horario, t.tipo 
FROM vuelo v 
JOIN ciudad_origen o ON o.id = v.id
JOIN ciudad_destino d ON d.id = v.id
JOIN aerolinea a ON a.id = v.id
JOIN tipo_vuelo t ON t.id = v.id
WHERE v.asientos_disponibles != 0;

-- Consultar vuelo a través de nombre de aerolinea

SELECT v.id, 
o.nombre_ciudad as ciudad_destino, d.nombre_ciudad as ciudad_llegada, 
fecha_salida, fecha_llegada,
asientos_disponibles, a.nombre_aerolinea, precio, horario, t.tipo 
FROM vuelo v 
JOIN ciudad_origen o ON o.id = v.id
JOIN ciudad_destino d ON d.id = v.id
JOIN aerolinea a ON a.id = v.id
JOIN tipo_vuelo t ON t.id = v.id
WHERE a.nombre_aerolinea = "";

-- Consultar vuelo a través de un rango de precios

SELECT v.id, 
o.nombre_ciudad as ciudad_destino, d.nombre_ciudad as ciudad_llegada, 
fecha_salida, fecha_llegada,
asientos_disponibles, a.nombre_aerolinea, precio, horario, t.tipo 
FROM vuelo v 
JOIN ciudad_origen o ON o.id = v.id
JOIN ciudad_destino d ON d.id = v.id
JOIN aerolinea a ON a.id = v.id
JOIN tipo_vuelo t ON t.id = v.id
WHERE v.precio BETWEEN 0 AND 0;

-- Consultar vuelo a través de fecha de horario

SELECT v.id, 
o.nombre_ciudad as ciudad_destino, d.nombre_ciudad as ciudad_llegada, 
fecha_salida, fecha_llegada,
asientos_disponibles, a.nombre_aerolinea, precio, horario, t.tipo 
FROM vuelo v 
JOIN ciudad_origen o ON o.id = v.id
JOIN ciudad_destino d ON d.id = v.id
JOIN aerolinea a ON a.id = v.id
JOIN tipo_vuelo t ON t.id = v.id
WHERE v.horario = "YYYY-MM-DD";

-- Consultar vuelo a través de fecha de tipo

SELECT v.id, 
o.nombre_ciudad as ciudad_destino, d.nombre_ciudad as ciudad_llegada, 
fecha_salida, fecha_llegada,
asientos_disponibles, a.nombre_aerolinea, precio, horario, t.tipo 
FROM vuelo v 
JOIN ciudad_origen o ON o.id = v.id
JOIN ciudad_destino d ON d.id = v.id
JOIN aerolinea a ON a.id = v.id
JOIN tipo_vuelo t ON t.id = v.id
WHERE v.tipo = "";