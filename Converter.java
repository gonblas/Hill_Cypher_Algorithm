import java.util.HashMap;
import java.util.Map;


public class Converter {
    static final char[] characters = {'a', 'b', 'c', 'd','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z',' ', '.',','};

    static final int[][] pwd = {{35,53,12},{12,21,5},{2,4,1}};
    static final int[][] pwd_inverse = {{11,-5,13},{-2,11,-31},{6,-34,99}};
    static final int[][] pwd_inverse_mod30 = {{1,25,13},{28,11,29},{6,26,9}};

    static Map<Character, Integer> values = new HashMap<>();

    static void initMap(){
        // Definir los valores asociados para cada carácter
        for (int i = 0; i < characters.length; i++) {
            values.put(characters[i], i + 1);
        }
    }

    public static String encrypt(String message){
        //Verifico si el mensaje es multiplo de 3
        if(message.length()%3 != 0){
            throw new IllegalArgumentException("La longitud del mensaje no es un múltiplo de 3.");
        }

        message = message.toLowerCase();

        //Convertir el mensaje a una matriz de numeros asociados
        int[][] column_matrix = toNumberMatrix(message);
        //Multiplicar la matriz por la matriz de encriptacion
        int[][] column_matrix_encrypted = matrix_prod(pwd, column_matrix);

        column_matrix_encrypted = module_30(column_matrix_encrypted);

        return toMessage(column_matrix_encrypted);
    }

    private static int[][] module_30(int[][] column_matrix){
        for(int i = 0; i < column_matrix.length;i++){
            for(int j = 0; j < column_matrix[0].length;j++){
                column_matrix[i][j]= column_matrix[i][j] % 30;
                if(column_matrix[i][j] < 0){
                    column_matrix[i][j] += 30;
                } else if (column_matrix[i][j] == 0) {
                    column_matrix[i][j] = 30;
                }
            }
        }
        return column_matrix;
    }

    private static int[][] matrix_prod(int[][] M, int[][] N) {
        int rowsM = M.length;
        int columnsM = M[0].length;
        int rowsN = N.length;
        int columnsN = N[0].length;

        if (columnsM != rowsN) {
            throw new IllegalArgumentException("Las matrices no son compatibles para la multiplicación.");
        }

        int[][] result = new int[rowsM][columnsN];

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

    private static int[][] toNumberMatrix(String message){
        //Dividimos el mensaje en bloques de 3 y lo pasamos al valor asociado
        int[][] column_matrix = new int[3][message.length()/3];
        for(int i = 0;i < message.length()/3;i++){
            column_matrix[0][i] = values.get(message.charAt(i*3));
            column_matrix[1][i] = values.get(message.charAt(i*3+1));
            column_matrix[2][i] = values.get(message.charAt(i*3+2));
        }

        return column_matrix;
    }

    public static String decrypt(String message_encrypted){
        int[][] message_encrypted_matrix = toNumberMatrix(message_encrypted);

        //Multiplicar la matriz por la inversa de la matriz de encriptacion
        int[][] column_matrix_decrypted = matrix_prod(pwd_inverse_mod30, message_encrypted_matrix);

        column_matrix_decrypted = module_30(column_matrix_decrypted);

        return toMessage(column_matrix_decrypted);
    }

    private static String toMessage(int[][] column_matrix_decrypted){
        String message_decrypted = "";
        for(int i = 0; i < column_matrix_decrypted[0].length;i++){
            message_decrypted += characters[column_matrix_decrypted[0][i]-1];
            message_decrypted += characters[column_matrix_decrypted[1][i]-1];
            message_decrypted += characters[column_matrix_decrypted[2][i]-1];
        }
        return message_decrypted;
    }
}