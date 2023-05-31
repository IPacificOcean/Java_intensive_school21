package edu.school21.numbers;

import edu.school21.numbers.IllegalNumberException;
import edu.school21.numbers.NumberWorker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {
    private NumberWorker numberWorker;

    @BeforeEach
    void createTestsClass() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 19, 23, 47, 59, 71, 89, 103, 127, 191})
    void isPrimeForPrimes(int value) {
        assertTrue(numberWorker.isPrime(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 14, 16, 27, 35, 40, 65, 77, 58, 96})
    void isPrimeForNotPrimes(int value) {
        assertFalse(numberWorker.isPrime(value));
    }
    @ParameterizedTest
    @ValueSource(ints = {0, 1, -4, -2})
    void isPrimeForIncorrectNumbers(int value) {
        assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(value));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void digitsSum(int input, int expected) {
        assertEquals(numberWorker.digitSum(input), expected);
    }


}
