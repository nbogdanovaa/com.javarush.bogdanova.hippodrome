import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class HippodromeTest {
    @Test
    public void nullHorsesException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void nullHorsesMessage() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void emptyHorsesException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void emptyHorsesMessage() {
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for (Horse hors : horses) {
            Mockito.verify(hors).move();
        }
    }

    @Test
    public void getWinner() {
        Horse horse1 = new Horse("horse1", 1, 2);
        Horse horse2 = new Horse("horse2", 1, 3);
        Horse horse3 = new Horse("horse3", 1, 1);
        Horse horse4 = new Horse("horse4", 1, 4);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4));

        Assertions.assertSame(horse4, hippodrome.getWinner());
    }
}
