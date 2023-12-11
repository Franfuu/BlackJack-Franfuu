package Model;

public class Card {
    String suit;
    String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

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

    @Override
    public String toString() {
        return"╔════════╗\n" + "  ║ " + rank + "      ║\n" + "  ║        ║\n" + "  ║   " + suit + "    ║\n" + "  ║        ║\n" + "  ║      " + rank + " ║\n" + "  ╚════════╝";

    }
}
