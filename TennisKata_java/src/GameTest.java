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
    public void testNewGameHasScore_love_A() throws Exception {
        Assert.assertEquals("love a", game.score());
    }

    @Test
    public void testBothPlayersScoreOnceThenScoreIs_15_a() throws Exception {
        bothPlayerScore(1);
        Assert.assertEquals("15 a", game.score());
    }

    @Test
    public void testBothPlayersScoreTwiceThenScoreIs_30_a() throws Exception {
        bothPlayerScore(2);
        Assert.assertEquals("30 a", game.score());
    }

    @Test
    public void testBothPlayersScore3timesThenScoreIs_40_a() throws Exception {
        bothPlayerScore(3);
        Assert.assertEquals("40 a", game.score());
    }

     private void bothPlayerScore(int times) {
        for (int i = 0; i < times; i++) {
            game.player1Scores();
            game.player2Scores();
        }
    }

    @Test
    public void testPlayer1ScoresOnceThenScoreIs_15_love() throws Exception {
        game.player1Scores();
        Assert.assertEquals("15 love", game.score());
    }
    @Test
    public void testPlayer2ScoresOnceThenScoreIs_love_15() throws Exception {
        game.player2Scores();
        Assert.assertEquals("love 15", game.score());
    }
    @Test
    public void testPlayer1ScoresTwiceThenScoreIs_30_love() throws Exception {
        game.player1Scores();
        game.player1Scores();
        Assert.assertEquals("30 love", game.score());
    }

    @Test
    public void testPlayer1Scores3TimesThenScoreIs_40_love() throws Exception {
        game.player1Scores();
        game.player1Scores();
        game.player1Scores();
        Assert.assertEquals("40 love", game.score());
    }

    @Test
    public void testPlayer1WinsInitialGamesThenScoreIs_Player_1_wins() throws Exception {
        game.player1Scores();
        game.player1Scores();
        game.player1Scores();
        game.player1Scores();
        Assert.assertEquals("player 1 wins", game.score());
    }
}
