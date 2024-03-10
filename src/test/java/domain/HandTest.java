package domain;

import static domain.FixtureCard.ACE_HEARTS;
import static domain.FixtureCard.SEVEN_HEARTS;
import static domain.FixtureCard.TEN_HEARTS;
import static domain.FixtureCard.TWO_HEARTS;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("손에 들고 있는 카드의 합계를 반환한다.")
    @Test
    void calculateSum() {
        List<Card> cards = List.of(SEVEN_HEARTS, TEN_HEARTS);

        Hand hand = new Hand(cards);

        assertThat(hand.calculateSum())
                .isEqualTo(SEVEN_HEARTS.getRankValue() + TEN_HEARTS.getRankValue());
    }

    @DisplayName("카드 한 장을 손 패로 가지고 온다.")
    @Test
    void add() {
        List<Card> cards = List.of(SEVEN_HEARTS, TEN_HEARTS);
        Hand hand = new Hand(cards);
        Card addCard = TWO_HEARTS;

        hand.add(addCard);

        assertThat(hand.getCards()).contains(addCard);
    }

    @DisplayName("손 패에 Ace카드가 몇개 있는지 반환한다.")
    @Test
    void containAce() {
        List<Card> hasAceCard = List.of(ACE_HEARTS, TEN_HEARTS);
        Hand hasAceHand = new Hand(hasAceCard);
        int actual1 = hasAceHand.countAceCard();

        assertThat(actual1).isEqualTo(1);
    }

    @Nested
    @DisplayName("손패의 점수를 계산한다.")
    class calculateScore {

        @DisplayName("블랙잭인 경우")
        @Test
        void blackJack() {
            List<Card> cards = List.of(
                    ACE_HEARTS,
                    TEN_HEARTS
            );

            Hand hand = new Hand(cards);
            int score = hand.calculateScore();

            assertThat(score).isEqualTo(21);
        }

        @DisplayName("A가 하나 있고, A를 1로 계산해야 하는 경우")
        @Test
        void oneAceOneRegard() {
            List<Card> cards = List.of(
                    ACE_HEARTS,
                    TEN_HEARTS,
                    TEN_HEARTS
            );

            Hand hand = new Hand(cards);
            int score = hand.calculateScore();

            assertThat(score).isEqualTo(21);
        }

        @DisplayName("A가 두개 이상이고, 모두 1로 계산해야 하는 경우")
        @Test
        void TwoAceTwoRegard() {
            List<Card> cards = List.of(
                    ACE_HEARTS,
                    ACE_HEARTS,
                    TEN_HEARTS
            );

            Hand hand = new Hand(cards);
            int score = hand.calculateScore();

            assertThat(score).isEqualTo(12);
        }

        @DisplayName("A가 두개 이상이고, 하나만 11로 계산해야 하는 경우")
        @Test
        void ThreeAceTwoRegard() {
            List<Card> cards = List.of(
                    ACE_HEARTS,
                    ACE_HEARTS,
                    TWO_HEARTS
            );

            Hand hand = new Hand(cards);
            int score = hand.calculateScore();

            assertThat(score).isEqualTo(14);
        }

        @DisplayName("A가 없는 경우 - 버스트")
        @Test
        void NoAceBust() {
            List<Card> cards = List.of(
                    TEN_HEARTS,
                    TEN_HEARTS,
                    TWO_HEARTS
            );

            Hand hand = new Hand(cards);
            int score = hand.calculateScore();

            assertThat(score).isEqualTo(22);
        }

        @DisplayName("A가 없는 경우 - 버스트아님")
        @Test
        void NoAceNotBust() {
            List<Card> cards = List.of(
                    TEN_HEARTS,
                    TEN_HEARTS
            );

            Hand hand = new Hand(cards);
            int score = hand.calculateScore();

            assertThat(score).isEqualTo(20);
        }

        @DisplayName("A가 있으면서, A를 1로 생각해도 버스트가 나는 경우")
        @Test
        void HasAceBust() {
            List<Card> cards = List.of(
                    ACE_HEARTS,
                    TWO_HEARTS,
                    TEN_HEARTS,
                    TEN_HEARTS
            );

            Hand hand = new Hand(cards);
            int score = hand.calculateScore();

            assertThat(score).isEqualTo(23);
        }
    }
}
