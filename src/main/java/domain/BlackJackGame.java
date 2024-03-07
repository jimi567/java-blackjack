package domain;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {

    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer;

    public BlackJackGame(PlayerNames playerNames, CardDeck cardDeck) {

        this.dealer = new Dealer(cardDeck);

        List<String> names = playerNames.names();
        for (String s : names) {
            Name name = new Name(s);
            Player player = new Player(name, dealer.dealHand());
            this.players.add(player);
        }
    }

    public boolean hitPlayer(String name) {
        Player targetPlayer = players.stream()
                .filter(player -> player.getName().name().equals(name))
                .findFirst()
                .get();
        if (targetPlayer.isHittable()) {
            targetPlayer.hit(dealer.dealCard());
            return true;
        }
        return false;
    }
}
