# Algoritmo de Cifrado y Descifrado con Hill Cipher
Este repositorio contiene una implementación del algoritmo Hill Cipher, una técnica de cifrado biyectiva que se basa en la multiplicación de matrices y que fue desarrollada en 1929 por Lester S. Hill. Hill Cipher, la cual es un ejemplo clásico de cifrado de sustitución poligráfica, lo que significa que en lugar de reemplazar un solo carácter por otro, reemplaza grupos de caracteres (en este caso, bloques de letras) por otros grupos de caracteres representados como números.

El procedimiento para **encriptar** un mensaje consiste en:

1. Dividir las caracteres del mensaje en grupos (en esta implementacion se utilizaran grupos de tres).
2. Convertir cada grupo en una cadena de numeros asignando un numero a cada caracter del mensaje (segun indica la **Regla de Cifrado**).

> Importante: Se le deben asignar numeros a los espacios en blanco.

3. Convertir cada grupo de numeros en matrices de columna.
4. Convertir estas matrices de columna en un nuevo conjunto de matrices de columna multiplicandolas por una matriz cuadrada compatible de su eleccion que tenga una inversa (debe tener inversa ya que necesitamos que la matriz de encriptacion sirva tanto para encriptar como desencriptar pues esta tecnica es biyectiva). Este nuevo conjunto de numeros o matrices representa el mensaje codificado.

> Importante: La matriz elegida como la matriz de encriptacion debe tener inversa, y la misma debe tener como componentes solo numeros enteros.

6. Transformar el conjunto de matrices con numeros a matrices con caracteres para obtener el mensaje codificado (formado por las columnas de esta matriz de caracteres).

El procedimiento para **descencriptar** un mensaje consiste en:
1. Tomar la cadena de numeros codificados y multiplıcalo por el modulo 30 de la inversa de la matriz que se utilizo para codificar el mensaje.
2. Asociar los numeros con sus caracteres correspondientes.

**Regla de Cifrado:**

| A | B | C | D | E | F | G | H | I | J | K | L | M | N | Ñ | O | P | Q | R | S | T | U | V | W | X | Y | Z | Espacio | . | , |
| :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: |
| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 | 24 | 25 | 26 | 27 | 28 | 29 | 30 |

 **Modulo 30:** El módulo 30 de una matriz se refiere a una operación matemática aplicada a cada elemento de la matriz, donde el resultado se reduce al resto/residuo cuando se divide por 30. Esta operación es útil en diversos contextos, como en criptografía y matemáticas, para limitar el rango de los valores resultantes y mantenerlos dentro del intervalo requerido.

## Utilizacion del Repositorio
En este repositorio, encontrarás una implementación de Hill Cipher en Java. Puedes utilizar este código para cifrar y descifrar mensajes de forma segura siguiendo el algoritmo de Hill Cipher. 
Ejecutando [Main](https://github.com/gonblas/Hill_Cypher_Algorithm/Main.java):

![Window](assets/window.svg)

> Aclaracion: Los caracteres a utilizar deben estar en la Regla de Cifrado y el largo de la cadena de caracteres debe ser multiplo de tres (por la implementacion realizada).

#### Matriz de encriptación por defecto

$$
M = 
\begin{bmatrix}
35 & 53 & 12\\
12 & 21 & 5 \\
2 & 4 & 1 \\
\end{bmatrix} 
$$


## Como utilizarlo 

1. Utiliza el siguiente comando para clonar este repositorio en tu sistema:
``` bash
git clone https://github.com/gonblas/Hill_Cypher_Algorithm
```

2. Navega al directorio del repositorio clonado:
``` bash
cd Hill_Cypher_Algorithm
```

3. Compilar:

``` bash
javac Main.java
```
4. Ejecutar:
``` bash
java Main
```

