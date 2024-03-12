package domain.player;

import static domain.game.Score.BLACK_JACK_SCORE;

import domain.card.Card;
import domain.card.Hand;
import domain.game.Score;
import java.util.List;

public class Player {

    private final Name name;
    private final Hand hand;

    public Player(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public Player(List<Card> cards) {
        this.name = new Name("Test");
        this.hand = new Hand(cards);
    }

    public void hit(Card card) {
        this.hand.add(card);
    }

    public boolean isBust() {
        return getTotalScore().isBust();
    }

    public boolean isHittable() {
        return getTotalScore().compareTo(BLACK_JACK_SCORE) < 0;
    }

    public Score getTotalScore() {
        return hand.calculateScore();
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name.name();
    }

}
