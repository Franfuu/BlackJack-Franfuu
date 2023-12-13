// Definición de la clase Card en el paquete Model.
package Model;

// Clase que representa una carta.
public class Card {
    String suit;
    String rank;

    // Constructor que inicializa una carta con un palo y un rango.
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Método para obtener el valor numérico de la carta.
    public int getValue() {
        switch (rank) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
            case "J":
            case "Q":
            case "K":
                return 10;
            case "A":
                return 11;
            default:
                return 0;
        }
    }

    // Método toString para representar gráficamente la carta.
    @Override
    public String toString() {
        return "╔════════╗\n" + "  ║ " + rank + "      ║\n" + "  ║        ║\n" + "  ║   " + suit + "    ║\n" + "  ║        ║\n" + "  ║      " + rank + " ║\n" + "  ╚════════╝";
    }
}