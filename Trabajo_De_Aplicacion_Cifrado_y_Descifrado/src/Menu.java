import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Converter.initMap(); // Initialize the character map

        boolean exit = false;
        loop:
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the blank line

            switch (choice) {
                case 1:
                    System.out.print("Enter the message to encrypt: ");
                    String messageToEncrypt = scanner.nextLine();
                    String encryptedMessage = Converter.encrypt(messageToEncrypt);
                    System.out.println("Encrypted message: " + encryptedMessage);
                    break;
                case 2:
                    System.out.print("Enter the message to decrypt: ");
                    String messageToDecrypt = scanner.nextLine();
                    String decryptedMessage = Converter.decrypt(messageToDecrypt);
                    System.out.println("Decrypted message: " + decryptedMessage);
                    break;
                case 3:
                    break loop;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}