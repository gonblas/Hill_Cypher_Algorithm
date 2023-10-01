import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Converter.initMap(); // Initialize the character map

        boolean exit = false;
        loop:
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Encriptar");
            System.out.println("2. Descencriptar");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the blank line

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el mensaje a encriptar: ");
                    String messageToEncrypt = scanner.nextLine();
                    System.out.println("Encriptando...");
                    String encryptedMessage = Converter.encrypt(messageToEncrypt);
                    System.out.println("Encrypted message: " + encryptedMessage);
                    break;
                case 2:
                    System.out.print("Ingrese el mensaje a descencriptar: ");
                    String messageToDecrypt = scanner.nextLine();
                    System.out.println("Descencriptando...");
                    String decryptedMessage = Converter.decrypt(messageToDecrypt);
                    System.out.println("Decrypted message: " + decryptedMessage);
                    break;
                case 3:
                    break loop;
                default:
                    System.out.println("Opcion invalida. Por favor, ingrese nuevamente.");
            }
        }

        scanner.close();
    }
}