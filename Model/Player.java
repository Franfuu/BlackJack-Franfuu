// Definición de la clase Player en el paquete Model.
package Model;

import java.util.ArrayList;

// Clase que representa a un jugador en el juego.
public class Player {
    private String name;
    private ArrayList<Card> hand;

    // Constructor que inicializa el jugador con un nombre y una mano vacía.
    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    // Método para agregar una carta a la mano del jugador.
    public void addCard(Card card) {
        hand.add(card);
    }

    // Método para calcular y obtener la puntuación total del jugador.
    public int getScore() {
        int score = 0;
        int numAs = 0;

        // Recorre las cartas en la mano para calcular la puntuación.
        for (Card card : hand) {
            score += card.getValue();
            if (card.rank.equals("A")) {
                numAs++;
            }
        }

        // Ajuste del valor de los As si el puntaje supera 21.
        int valorAs = 11;

        while (score > 21 && numAs > 0) {
            score = (valorAs - 1); // Restar la diferencia para cambiar de 11 a 1
            numAs--;
        }

        return score;
    }

    // Método para mostrar la mano actual del jugador.
    public void displayHand() {
        System.out.println("La mano de " + name + " es:");
        for (Card card : hand) {
            System.out.println("  " + card);
        }
        System.out.println("Puntuación total: " + getScore());
    }

    // Método para obtener el nombre del jugador.
    public String getName() {
        return name;
    }
}
