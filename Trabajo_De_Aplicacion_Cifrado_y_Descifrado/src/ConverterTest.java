import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Before
    public void setUp() {
        Main.initMap(); // Inicializa el mapa de caracteres antes de las pruebas
    }

    @Test
    public void testEncryptAndDecrypt() {
        String originalMessage = "hello world.";
        String encryptedMessage = Main.encrypt(originalMessage);
        String decryptedMessage = Main.decrypt(encryptedMessage);

        assertEquals(originalMessage.toLowerCase(), decryptedMessage);
    }

    @Test
    public void testEncryptWithNonMultipleOf3() {
        try {
            String message = "abcde"; // Longitud no es múltiplo de 3
            Main.encrypt(message);
            fail("Se esperaba una excepción IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Excepción esperada
        }
    }

    @Test
    public void testEncryptWithSpecialCharacters() {
        String message = "hola, mundo.";
        String encryptedMessage = Main.encrypt(message);
        String decryptedMessage = Main.decrypt(encryptedMessage);

        assertEquals(message.toLowerCase(), decryptedMessage);
    }

    @Test
    public void testDecryptWithInvalidCharacter() {
        try {
            String encryptedMessage = "10 20 99"; // Caracteres no válidos
            Main.decrypt(encryptedMessage);
            fail("Se esperaba una excepción IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Excepción esperada
        }
    }
}