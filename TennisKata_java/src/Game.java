public class Game {

    private int player1Scored;
    private int player2Scored;

    public Game() {
        player1Scored = 0;
        player2Scored = 0;
    }

    public String score() {
        if (isEquality())
            return String.format("%s a", getScore(player1Scored));

        return String.format("%s %s", getScore(player1Scored), getScore(player2Scored));

    }

    private boolean isEquality() {
        return player1Scored == player2Scored;
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
        player1Scored++;
    }

    public void player2Scores() {
        player2Scored++;
    }
}
