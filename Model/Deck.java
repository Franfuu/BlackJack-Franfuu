// Definición de la clase Deck en el paquete Model.
package Model;

import java.util.ArrayList;
import java.util.Collections;

// Clase que representa un mazo de cartas.
public class Deck {
    private ArrayList<Card> cards;

    // Constructor que inicializa el mazo con todas las cartas y las baraja.
    public Deck() {
        String[] suits = {"♥", "♦", "♠", "♣"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        // Creación de todas las cartas y adición al mazo. Utilización del For each.
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }

        // Barajar las cartas del mazo.
        Collections.shuffle(cards);
    }

    // Método para extraer una carta del mazo.
    public Card drawCard() {
        return cards.remove(0);
    }
}
