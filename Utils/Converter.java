package Utils;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Converter {
    static final char[] characters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', '.', ','};

    private static double[][] pwd = {{35, 53, 12}, {12, 21, 5}, {2, 4, 1}};
    private static double[][] pwd_inverse = {{1, -5, 13}, {-2, 11, -31}, {6, -34, 99}};
    private static double[][] pwd_inverse_mod30 = {{1, 25, 13}, {28, 11, 29}, {6, 26, 9}};

    static Map<Character, Integer> values = new HashMap<>();

    static {
        initMap();
    }

    public static double[][] getPwd() {
        return pwd;
    }

    public static void setPwd(double[][] pwd) {
        Converter.pwd = pwd;
    }

    public static double[][] getPwd_inverse() {
        return pwd_inverse;
    }

    public static void setPwd_inverse(double[][] pwd_inverse) {
        Converter.pwd_inverse = pwd_inverse;
    }

    public static double[][] getPwd_inverse_mod30() {
        return pwd_inverse_mod30;
    }

    public static void setPwd_inverse_mod30(double[][] pwd_inverse_mod30) {
        Converter.pwd_inverse_mod30 = pwd_inverse_mod30;
    }

    public static void initMap() {
        for (int i = 0; i < characters.length; i++) {
            values.put(characters[i], i + 1);
        }
    }

    public static double[][] findInverse(double[][] matrix) throws MatrixOperationException {
        int n = matrix.length;
        double[][] inverse = new double[n][n];

        // Inicializar la matriz identidad
        double[][] identity = new double[n][n];
        for (int i = 0; i < n; i++) {
            identity[i][i] = 1.0;
        }

        // Hacer una matriz de copia
        double[][] matrix_copy = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, matrix_copy[i], 0, n);
        }

        // Realizar la eliminación de Gauss-Jordan
        for (int i = 0; i < n; i++) {
            if (matrix_copy[i][i] == 0) {
                // Buscar una fila no nula para intercambiar
                int swapRow = -1;
                for (int j = i + 1; j < n; j++) {
                    if (matrix_copy[j][i] != 0) {
                        swapRow = j;
                        break;
                    }
                }

                if (swapRow == -1) {
                    throw new MatrixOperationException("La matriz no es invertible.");
                }

                // Intercambiar filas en la matriz original y la identidad
                double[] temp = matrix_copy[i];
                matrix_copy[i] = matrix_copy[swapRow];
                matrix_copy[swapRow] = temp;

                temp = identity[i];
                identity[i] = identity[swapRow];
                identity[swapRow] = temp;
            }

            double pivot = matrix_copy[i][i];
            double pivotInverse = 1.0 / pivot;

            // Escalar la fila actual en la matriz original y la identidad
            for (int j = 0; j < n; j++) {
                matrix_copy[i][j] *= pivotInverse;
                identity[i][j] *= pivotInverse;
            }

            // Eliminar otros elementos de la columna
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = matrix_copy[k][i];
                    for (int j = 0; j < n; j++) {
                        matrix_copy[k][j] -= factor * matrix_copy[i][j];
                        identity[k][j] -= factor * identity[i][j];
                    }
                }
            }
        }

        return identity;
    }

    public static boolean isMatrixOfIntegers(double[][] matrix, double epsilon) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                double value = matrix[i][j];
                if (Math.abs(value - Math.round(value)) > epsilon) {
                    return false;
                }
                matrix[i][j] = Math.round(value);
            }
        }

        return true;
    }

    public static String encrypt(String message) throws MatrixOperationException {
        if(message.length() == 0){
            throw new IllegalArgumentException("Envía un mensaje.");
        }
        // Verificar si el mensaje es múltiplo de 3
        if (message.length() % 3 != 0) {
            throw new IllegalArgumentException("La longitud del mensaje a descencriptar no es un múltiplo de 3.");
        }

        message = message.toLowerCase();

        // Convertir el mensaje a una matriz de números asociados
        double[][] column_matrix = toNumberMatrix(message);
        // Multiplicar la matriz por la matriz de encriptación
        double[][] column_matrix_encrypted = matrix_prod(pwd, column_matrix);

        column_matrix_encrypted = module_30(column_matrix_encrypted);

        return toMessage(column_matrix_encrypted);
    }

    public static double[][] module_30(double[][] column_matrix) {
        for (int i = 0; i < column_matrix.length; i++) {
            for (int j = 0; j < column_matrix[0].length; j++) {
                column_matrix[i][j] = column_matrix[i][j] % 30;
                if (column_matrix[i][j] < 0) {
                    column_matrix[i][j] += 30;
                } else if (column_matrix[i][j] == 0) {
                    column_matrix[i][j] = 30;
                }
            }
        }
        return column_matrix;
    }

    private static double[][] matrix_prod(double[][] M, double[][] N) throws MatrixOperationException {
        int rowsM = M.length;
        int columnsM = M[0].length;
        int rowsN = N.length;
        int columnsN = N[0].length;

        if (columnsM != rowsN){
            throw new MatrixOperationException("Error al multiplicar matrices.");
        }

        double[][] result = new double[rowsM][columnsN];

        for (int i = 0; i < rowsM; i++) {
            for (int j = 0; j < columnsN; j++) {
                int sum = 0;
                for (int k = 0; k < columnsM; k++) {
                    sum += M[i][k] * N[k][j];
                }
                result[i][j] = sum;
            }
        }

        return result;
    }

    private static double[][] toNumberMatrix(String message) {
        try {
            if (message == null) {
                throw new IllegalArgumentException("Envía un mensaje.");
            }
            for (int i = 0; i < message.length(); i++) {
                char currentChar = message.charAt(i);
                if (!values.containsKey(currentChar)) {
                    throw new IllegalArgumentException("Carácter no válido: " + currentChar);
                }
            }
            // Dividimos el mensaje en bloques de 3 y lo pasamos al valor asociado
            double[][] column_matrix = new double[3][message.length() / 3];
            for (int i = 0; i < message.length() / 3; i++) {
                column_matrix[0][i] = values.get(message.charAt(i * 3));
                column_matrix[1][i] = values.get(message.charAt(i * 3 + 1));
                column_matrix[2][i] = values.get(message.charAt(i * 3 + 2));
            }

            return column_matrix;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    public static String decrypt(String message_encrypted) throws MatrixOperationException {
        if(message_encrypted.length() == 0){
            throw new IllegalArgumentException("Envía un mensaje.");
        }
        if (message_encrypted.length() % 3 != 0) {
            throw new IllegalArgumentException("La longitud del mensaje encriptado no es un múltiplo de 3.");
        }

        message_encrypted = message_encrypted.toLowerCase();

        double[][] message_encrypted_matrix = toNumberMatrix(message_encrypted);

        // Multiplicar la matriz por la inversa de la matriz de encriptación
        double[][] column_matrix_decrypted = matrix_prod(pwd_inverse_mod30, message_encrypted_matrix);

        column_matrix_decrypted = module_30(column_matrix_decrypted);

        return toMessage(column_matrix_decrypted);
    }

    private static String toMessage(double[][] column_matrix_decrypted) {
        StringBuilder message_decrypted = new StringBuilder();
        for (int i = 0; i < column_matrix_decrypted[0].length; i++) {
            message_decrypted.append(characters[(int) column_matrix_decrypted[0][i] - 1]);
            message_decrypted.append(characters[(int) column_matrix_decrypted[1][i] - 1]);
            message_decrypted.append(characters[(int) column_matrix_decrypted[2][i] - 1]);
        }
        return message_decrypted.toString();
    }
}
