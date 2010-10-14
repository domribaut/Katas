import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class GameTest extends TestCase {

    Game game;

    @Override
    protected void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void testThatNewGameHasScore_love_A() throws Exception {
        Assert.assertEquals("love a", game.score());
    }

    @Test
    public void testThatWhenBothPlayersScoreOnceThenScoreIS_15_a() throws Exception {
        bothPlayerScore(1);
        Assert.assertEquals("15 a", game.score());
    }

    private void bothPlayerScore(int times) {
        for (int i = 0; i < times; i++) {
            game.player1Scores();
            game.player2Scores();
        }
    }

    @Test
    public void testThatWhenBothPlayersScoreTwiceThenScoreIS_30_a() throws Exception {
        bothPlayerScore(2);
        Assert.assertEquals("30 a", game.score());
    }

    @Test
    public void testThatWhenBothPlayersScore3timesThenScoreIS_40_a() throws Exception {
        bothPlayerScore(3);
        Assert.assertEquals("40 a", game.score());
    }

    @Test
    public void testThatWhenPlayer1ScoresOnceThenScoreIS_15_love() throws Exception {
        game.player1Scores();
        Assert.assertEquals("15 love", game.score());
    }
    @Test
    public void testThatWhenPlayer2ScoresOnceThenScoreIS_love_15() throws Exception {
        game.player2Scores();
        Assert.assertEquals("love 15", game.score());
    }
}
