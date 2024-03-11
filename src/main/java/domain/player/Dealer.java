package domain.player;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Hand;
import java.util.List;

public class Dealer extends Player {

    static final String DEALER_NAME = "딜러";

    private static final int MAX_SCORE_TO_HIT = 16;

    private final CardDeck cardDeck;

    public Dealer(CardDeck cardDeck) {
        super(new Name(DEALER_NAME), cardDeck.initHand());
        this.cardDeck = cardDeck;
    }

    public Dealer(List<Card> cards) {
        super(new Name(DEALER_NAME), new Hand(cards));
        this.cardDeck = null;
    }

    public Hand dealHand() {
        return cardDeck.initHand();
    }

    public Card dealCard() {
        return cardDeck.draw();
    }

    @Override
    public boolean isHittable() {
        return this.getTotalScore().compareTo(MAX_SCORE_TO_HIT) <= 0;
    }
}
