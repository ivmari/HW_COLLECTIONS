import org.junit.jupiter.api.Test;
import ru.netology.stats.NotRegisteredException;
import ru.netology.stats.Player;
import ru.netology.stats.Game;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game registeredPlayers = new Game();

    Player player1 = new Player(1, "Ира", 300);
    Player player2 = new Player(22, "Марина", 100);
    Player player3 = new Player(333, "Катя", 250);
    Player player4 = new Player(4, "Наташа", 99);
    Player player5 = new Player(55, "Лена", 300);

    @Test
    public void compareStrengthPlayersIfBothAreUnregistered() {

        assertThrows(NotRegisteredException.class, () -> {
            registeredPlayers.round(player2.getName(), player3.getName());
        });
    }

    @Test
    public void compareStrengthPlayersIfOneIsUnregistered() {
        registeredPlayers.register(player3);

        assertThrows(NotRegisteredException.class, () -> {
            registeredPlayers.round(player3.getName(), player1.getName());
        });
    }

    @Test
    public void comparePlayersWhenFirstIsStrongerThanSecond() {
        registeredPlayers.register(player1);
        registeredPlayers.register(player2);

        assertEquals(1, registeredPlayers.round(player1.getName(), player2.getName()));
    }

    @Test
    public void comparePlayersWhenFirstIsWeakerThanSecond() {
        registeredPlayers.register(player4);
        registeredPlayers.register(player5);

        assertEquals(2, registeredPlayers.round(player4.getName(), player5.getName()));
    }

    @Test
    public void comparePlayersOfEqualStrength() {
        registeredPlayers.register(player1);
        registeredPlayers.register(player5);

        assertEquals(0, registeredPlayers.round(player1.getName(), player5.getName()));
    }
}