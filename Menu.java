import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Converter.initMap(); // Initialize the character map

        boolean exit = false;
        loop:
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Encriptar");
            System.out.println("2. Descencriptar");
            System.out.println("3. Cambiar la matriz de encripacion");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the blank line

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el mensaje a encriptar: ");
                    String messageToEncrypt = scanner.nextLine();
                    System.out.println("Encriptando...");
                    String encryptedMessage = Converter.encrypt(messageToEncrypt);
                    System.out.println("Mensaje encriptado: " + encryptedMessage);
                    break;
                case 2:
                    System.out.print("Ingrese el mensaje a descencriptar: ");
                    String messageToDecrypt = scanner.nextLine();
                    System.out.println("Descencriptando...");
                    String decryptedMessage = Converter.decrypt(messageToDecrypt);
                    System.out.println("Mensaje descencriptado: " + decryptedMessage);
                    break;
                case 3:
                    System.out.println("La matriz de encriptacion debe cumplir con los siguientes requisitos:");
                    System.out.println("a. La matriz debe estar compuesta por numeros enteros.");
                    System.out.println("b. Debe tener inversa.");
                    System.out.println("c. La inversa tambien debe estar compuesta por numeros enteros.");
                    System.out.println("\nCambiando matriz de encriptacion...\n");
                    double[][] matrix = new double[3][3];
                    //Ingreso de la matriz
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Ingrese la fila " + (i + 1) + " de la matriz separada por espacios: ");
                        String[] row = scanner.nextLine().split(" ");
                        for (int j = 0; j < 3; j++) {
                            matrix[i][j] = Double.parseDouble(row[j]);
                        }
                    }
                    double[][] inverse = Converter.findInverse(matrix);
                    if(Converter.isMatrixOfIntegers(inverse, 0.00000001)){
                        Converter.setPwd(matrix);
                        Converter.setPwd_inverse(inverse);
                        Converter.setPwd_inverse_mod30(Converter.module_30(inverse));
                        System.out.println("Matriz de encriptacion cambiada exitosamente.");
                    }
                    else{
                        System.out.println("La matriz de encriptacion no cumple con los requisitos.");
                    }
                    break;
                case 4:
                    break loop;
                default:
                    System.out.println("Opcion invalida. Por favor, ingrese nuevamente.");
            }
        }

        scanner.close();
    }
}
