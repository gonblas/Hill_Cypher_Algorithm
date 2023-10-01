import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {
    @BeforeEach
    public void setUp() {
        Converter.initMap(); // Inicializa el mapa de caracteres antes de las pruebas
    }

    @Test
    public void testEncryptAndDecrypt() {
        String originalMessage = "hello world.";
        String encryptedMessage = Converter.encrypt(originalMessage);
        String decryptedMessage = Converter.decrypt(encryptedMessage);

        assertEquals(originalMessage.toLowerCase(), decryptedMessage);
    }

    @Test
    public void testEncryptWithNonMultipleOf3() {
        try {
            String message = "abcde"; // Longitud no es múltiplo de 3
            Converter.encrypt(message);
            fail("Se esperaba una excepción IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Excepción esperada
        }
    }

    @Test
    public void testEncryptWithSpecialCharacters() {
        String message = "hola, mundo.";
        String encryptedMessage = Converter.encrypt(message);
        String decryptedMessage = Converter.decrypt(encryptedMessage);

        assertEquals(message.toLowerCase(), decryptedMessage);
    }
}