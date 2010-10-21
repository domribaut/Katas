public class Game {

    private int player1Scored;
    private int player2Scored;

    public Game() {
        player1Scored = 0;
        player2Scored = 0;
    }

    public String score() {

        if (isOnePlayerWins())
            return getWinner();
        if (isInitialGame())
            return getScoreForInitialGame();

        return getScoreForExtendedGame();
    }

    private String getScoreForExtendedGame() {
        if (isEquality())
            return "equality";
        
        int advantagePlayer = player1Scored > player2Scored ? 1 : 2;
        return "advantage player " + advantagePlayer;
    }

    private boolean isInitialGame() {
        return player1Scored <= 3 && player2Scored <= 3;
    }

    private String getScoreForInitialGame() {
        return isEquality() ?
                getScoreForEquality()
                : getBothScores();
    }

    private boolean isOnePlayerWins() {
        return Math.max(player1Scored, player2Scored) > 3 && Math.abs(player1Scored - player2Scored) >= 2;
    }

    private String getWinner() {
        int winner = player1Scored > player2Scored ? 1 : 2;
        return String.format("player %d wins", winner);
    }

    private boolean isEquality() {
        return player1Scored == player2Scored;
    }

    private String getScoreForEquality() {
        return String.format("%s a", getScore(player1Scored));
    }

    private String getBothScores() {
        return String.format("%s %s", getScore(player1Scored), getScore(player2Scored));
    }

    private String getScore(int scored) {
        String s = "";
        switch (scored) {
            case 0:
                s = "love";
                break;
            case 1:
                s = "15";
                break;
            case 2:
                s = "30";
                break;
            case 3:
                s = "40";
                break;

        }
        return s;
    }

    public void player1Scores() {
        ThrowIfExistsWinner();
        player1Scored++;
    }

    public void player2Scores() {
        ThrowIfExistsWinner();
        player2Scored++;
    }

    private void ThrowIfExistsWinner() {
        if (isOnePlayerWins())
            throw new TennisException("Player can't score when there is a winner.");
    }
}
