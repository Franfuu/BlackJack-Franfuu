
package Model;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int getScore() {
        int score = 0;
        int numAs = 0;

        for (Card card : hand) {
            score += card.getValue();
            if (card.rank.equals("A")) {
                numAs++;
            }
        }

        // Si hay Ases y el puntaje supera 21, se asume que al menos uno es un 1.
        while (score > 21 && numAs > 0) {
            score -= 10;
            numAs--;
        }

        return score;
    }


    public void displayHand() {
        System.out.println("La mano de " + name + " es");
        for (Card card : hand) {
            System.out.println("  " + card);
        }
        System.out.println("Puntuacion total: " + getScore());
    }

    public String getName() {
        return name;
    }

}
