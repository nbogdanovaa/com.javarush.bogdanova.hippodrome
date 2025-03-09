import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


public class HorseTest {
    @Test
    public void nullNameException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void nullNameMessage() {
        try {
            new Horse(null, 1, 1);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n"})
    public void blankNameException(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n"})
    public void blankNameMessage(String name) {
        try {
            new Horse(name, 1, 1);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    public void negativeSpeedException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("horse", -1, 1));
    }

    @Test
    public void negativeSpeedMessage() {
        try {
            new Horse("Horse", -1, 1);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void negativeDistanceException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 1, -1));
    }

    @Test
    public void negativeDistanceMessage() {
        try {
            new Horse("Horse", 1, -1);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void getName() {
        Horse horse = new Horse("horse", 1, 1);
        Assertions.assertEquals("horse", horse.getName());
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("horse", 1, 2);
        Assertions.assertEquals(1, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("horse", 1, 2);
        Assertions.assertEquals(2, horse.getDistance());
    }

    @Test
    public void getDistanceZero() {
        Horse horse = new Horse("horse", 1);
        Assertions.assertEquals(0, horse.getDistance());
    }

    @Test
    public void moveUseGetRandom() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            new Horse("horse", 31, 283).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0, 999.999, 0.0})
    public void move(double random) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("horse", 15, 100);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            horse.move();
            Assertions.assertEquals(283 * 31 * random, horse.getDistance());
        }
    }
}
