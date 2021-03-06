import junit.framework.TestCase;


public class GameTest extends TestCase {

    Game game;
    Score score = this.new Score();

    @Override
    protected void setUp() throws Exception {
        game = new Game();
    }

    public void testNewGameHasScore_love_A() throws Exception {
        score.ShouldEquals("love a");
    }

    public void testBothPlayersScoreOnceThenScoreIs_15_a() throws Exception {
        bothPlayerScore(1);
        score.ShouldEquals("15 a");
    }

    public void testBothPlayersScoreTwiceThenScoreIs_30_a() throws Exception {
        bothPlayerScore(2);
        score.ShouldEquals("30 a");
    }

    public void testBothPlayersScore3timesThenScoreIs_40_a() throws Exception {
        bothPlayerScore(3);
        assertEquals("40 a", game.score());
    }

    public void testPlayer1ScoresOnceThenScoreIs_15_love() throws Exception {
        game.player1Scores();
        assertEquals("15 love", game.score());
    }

    public void testPlayer2ScoresOnceThenScoreIs_love_15() throws Exception {
        game.player2Scores();
        assertEquals("love 15", game.score());
    }

    public void testPlayer1ScoresTwiceThenScoreIs_30_love() throws Exception {
        game.player1Scores();
        game.player1Scores();
        assertEquals("30 love", game.score());
    }

    public void testPlayer1Scores3TimesThenScoreIs_40_love() throws Exception {
        game.player1Scores();
        game.player1Scores();
        game.player1Scores();
        assertEquals("40 love", game.score());
    }

    public void testPlayer1WinsInitialGamesThenScoreIs_Player_1_wins() throws Exception {
        game.player1Scores();
        game.player1Scores();
        game.player1Scores();
        game.player1Scores();
        assertEquals("player 1 wins", game.score());
    }

    public void testPlayer2WinsInitialGamesThenScoreIs_Player_2_wins() throws Exception {
        game.player2Scores();
        game.player2Scores();
        game.player2Scores();
        game.player2Scores();
        assertEquals("player 2 wins", game.score());
    }

    public void testPlayer1CannotScoreGivenTheirIsAWinner() {
        game.player2Scores();
        game.player2Scores();
        game.player2Scores();
        game.player2Scores();

        try {
            game.player1Scores();
            fail("Loser should not be able to score given that there is a winner.");
        } catch (TennisException e) {
            //expected
        }
    }

    public void testPlayer2CannotScoreGivenTheirIsAWinner() {
        game.player2Scores();
        game.player2Scores();
        game.player2Scores();
        game.player2Scores();

        try {
            game.player2Scores();
            fail("Loser should not be able to score given that there is a winner.");
        } catch (TennisException e) {
            //expected
        }
    }

    public void testPlayer1ScoresFrom40_a_ThenScoreIs_advantage_1() {
        bothPlayerScore(3);
        game.player1Scores();
        assertEquals("advantage player 1", game.score());
    }

    public void testPlayer1ScoresFromAdvantage_1_ThenScoreIs_equality() {
        //ctx
        bothPlayerScore(3);
        game.player1Scores();
        //act
        game.player2Scores();
        assertEquals("equality", game.score());
    }

    private void bothPlayerScore(int times) {
        for (int i = 0; i < times; i++) {
            game.player1Scores();
            game.player2Scores();
        }
    }

    private class Score {
        public void ShouldEquals(String expected) {
            assertEquals(expected, game.score());
        }
    }

}
