using System;
using NUnit.Framework;
using NUnit.Framework.SyntaxHelpers;

namespace TennisKata
{
    [TestFixture]
    public class TennisTest
    {
        Game game;
        String Score { get { return game.Score; } }
        [SetUp]
        public void SetUp()
        {
            game = new Game();
        }

        [Test]
        public void Score_is_love_A_when_starting_game()
        {
            Score.ShouldBe("love a");
        }

        [Test]
        public void PlayerOne_scores_gives_score_15_love()
        {
            game.Player1Scores();
            Score.ShouldBe("15, love");
        }
        [Test]
        public void Both_player_scores_once_gives_15_a()
        {
            Both_players_score(1);
            Score.ShouldBe("15 a");
        }

        [Test]
        public void Both_player_scores_twice_gives_30_a()
        {
            Both_players_score(2);
            Score.ShouldBe("30 a");
        }

        [Test]
        public void Both_player_scores_3_gives_40_a()
        {
            Both_players_score(3);
            Score.ShouldBe("40 a");
        }

        [Test]
        public void Both_player_scores_more_than_3_gives_equality()
        {
            Both_players_score(4);
            Score.ShouldBe("equality");
        }
        [Test]
        public void Player_2_scores_gives_score_love_15()
        {
            game.Player2Scores();
            Score.ShouldBe("love, 15");
        }

        [Test]
        public void Player_2_scores_from_equality_gives_advantage_2()
        {
            Both_players_score(4);
            game.Player2Scores();
            Score.ShouldBe("advantage 2");
        }

        [Test]
        public void Player_1_scores_from_equality_gives_advantage_1()
        {
            Both_players_score(5);
            game.Player1Scores();
            Score.ShouldBe("advantage 1");
        }

        [Test]
        public void Player_1_wins_from_initial_play()
        {
            Both_players_score(2);
            game.Player1Scores();
            game.Player1Scores();
            Score.ShouldBe("player 1 wins");
        }

        [Test]
        public void Player_1_wins_from_equality()
        {
            Both_players_score(4);
            game.Player1Scores();
            game.Player1Scores();
            Score.ShouldBe("player 1 wins");
        }
        [Test]
        public void Player_2_wins_from_equality()
        {
            Both_players_score(4);
            game.Player2Scores();
            game.Player2Scores();
            Score.ShouldBe("player 2 wins");
        }
        [Test, ExpectedException(typeof(TennisException))]
        public void When_player_wins_winner_can_not_score()
        {
            player1scores(4);
            game.Player1Scores();
        }

        [Test, ExpectedException(typeof(TennisException))]
        public void When_player_wins_loser_can_not_score()
        {
            player1scores(4);
            game.Player2Scores();
        }

        private void player1scores(int times)
        {
            for (int i = 0; i < times; i++)
                game.Player1Scores();
        }

        private void Both_players_score(int times)
        {
            for (int i = 0; i < times; i++)
            {
                game.Player1Scores();
                game.Player2Scores();
            }
        }
    }
    static class UnFramework
    {
        public static void ShouldBe(this string left, string right)
        {
            Assert.That(left, Is.EqualTo(right));
        }
    }

    internal class TennisException : Exception { }

    internal class Game
    {
        private int scorePlayer1;
        private int scorePlayer2;
        public string Score { get { return GetScore(); } }
        public string GetScore()
        {
            if (is_a_winner()) return winner();
            return is_normal_game() ? score_for_normal_game() : score_for_extended_game();
        }

        private string winner()
        {
            return "player " + (scorePlayer1 > scorePlayer2 ? "1" : "2") + " wins";
        }

        private string score_for_extended_game()
        {
            if (IsEquality()) return "equality";

            return "advantage " + (scorePlayer1 > scorePlayer2 ? "1" : "2");
        }

        private string score_for_normal_game()
        {
            if (IsEquality()) return GetValue(scorePlayer1) + " a";
            return GetValue(scorePlayer1) + ", " + GetValue(scorePlayer2);
        }

        private bool is_normal_game()
        {
            return scorePlayer1 <= 3 && scorePlayer2 <= 3;
        }

        private int high_score
        {
            get { return Math.Max(scorePlayer1, scorePlayer2); }
        }
        private bool is_a_winner()
        {
            return high_score > 3 && Math.Pow(scorePlayer1 - scorePlayer2, 2) >= 4;
        }

        private bool IsEquality()
        {
            return scorePlayer1 == scorePlayer2;
        }

        private static string GetValue(int scored)
        {
            switch (scored)
            {
                case 0: return "love";
                case 1: return "15";
                case 2: return "30";
                case 3: return "40";
                default: return null;
            }

        }

        public void Player1Scores()
        {
            if (is_a_winner())
                throw new TennisException();
            scorePlayer1 += 1;
        }

        public void Player2Scores()
        {
            if (is_a_winner())
                throw new TennisException();
            scorePlayer2 += 1;
        }
    }
}
