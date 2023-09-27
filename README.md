# Trabajo de Aplicacion Matematica C
Repositorio para almacenar el trabajo de aplicacion sobre Sistemas de ecuaciones, Matrices y Determinantes.


1. **Cifrado y decifrado de un mensaje secreto usando matrices**
Los mensajes cifrados utilizando matrices fueron inventados por Hill en 1929 y consisten en asignarles un numero a cada letra del alfabeto, el espacio entre las mismas y los signos de puntuacion. Esto nos genera una funcion biyectiva, es decir que podemos ir de un lado a otro.

El ejercicio consiste en:

***(a)*** Mandar un mensaje cifrado con la clave al **Grupo 16** de estudiantes.

***(b)*** Decifrar el mensaje que les llego del **Grupo 14**.

Para codificar un mensaje utilizaremos el siguiente procedimiento:

-  Divida las letras del mensaje en grupos de tres.
-  Convierte cada grupo en una cadena de numeros asignando un numero a cada letra del mensaje.

> Recuerda asignar letras a espacios en blanco.

- Convierte cada grupo de numeros en matrices de columna.
- Convierta estas matrices de columna en un nuevo conjunto de matrices de columna multiplic ́andolas por una matriz cuadrada compatible de su eleccion que tenga una inversa (¿Por que es necesario esto? Porque necesitamos que la matriz de encriptacion sirva tanto para encriptar como desencriptar). Este nuevo conjunto de numeros o matrices representa el mensaje codificado.
- Transformarlo a letras para obtener el mensaje codificado
Para decodificar un mensaje:
- Toma la cadena de numeros codificados y multiplıcalo por la inversa de la matriz que se utilizo para codificar el mensaje.
- Asociar los numeros con sus letras correspondientes.

**Regla de Cifrado:**

| A | B | C | D | E | F | G | H | I | J | K | L | M | N | Ñ | O | P | Q | R | S | T | U | V | W | X | Y | Z | Espacio | . | , |
| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 | 24 | 25 | 26 | 27 | 28 | 29 | 30 |
 