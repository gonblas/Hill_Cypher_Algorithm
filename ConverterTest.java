import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    public void testMatrixInverse() {
        double[][] inputMatrix = {{35, 53, 12}, {12, 21, 5}, {2, 4, 1}};
        double[][] expectedInverse = {{1, -5, 13}, {-2, 11, -31}, {6, -34, 99}};

        double[][] inverseMatrix = Converter.findInverse(inputMatrix);

        // Verificar que la matriz inversa se haya calculado correctamente
        for (int i = 0; i < expectedInverse.length; i++) {
            assertArrayEquals(expectedInverse[i], inverseMatrix[i], 0.000001);
        }
    }

    @Test
    public void testMatrixIntegerCheck() {
        double[][] inputMatrix = {
                {1.0000000000001, 2.000000000002213},
                {3.0000000000938, 4.000000000000023}
        };


        boolean isIntegerMatrix = Converter.isMatrixOfIntegers(inputMatrix,0.000001);

        assertTrue(isIntegerMatrix);
    }
}