import game.Game;

/**
 * ArkanoidGame manage the game.
 */
public class ArkanoidGame {
    /**
     * main.
     *
     * @param args avoid
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
