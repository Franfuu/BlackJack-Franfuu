// Este es el controlador principal de la aplicación.
package Controller;

// Importación de clases y paquetes necesarios.
import Model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Importación de un método específico desde la clase View.Menu.
import static View.Menu.FirstMessage;

// Clase que contiene el método principal de la aplicación.
public class MainController {

    // Método que inicia la aplicación.
    public static void StartApp() {
        // Creación de un objeto Scanner para la entrada de datos.
        Scanner teclado = new Scanner(System.in);

        // Llamada al método FirstMessage de la clase Menu para mostrar un mensaje inicial.
        FirstMessage();

        // Inicialización de la variable numPlayers que almacena el número de jugadores.
        int numPlayers = 0;

        // Bucle do-while para solicitar al usuario el número de jugadores (entre 1 y 4).
        do {
            System.out.print("Ingrese el número de jugadores (máximo 4): ");
            try {
                // Lectura y conversión de la entrada del usuario a un entero.
                numPlayers = Integer.parseInt(teclado.nextLine());

                // Validación del rango del número de jugadores.
                if (numPlayers < 1 || numPlayers > 4) {
                    System.out.println("Error: Ingrese un número válido entre 1 y 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        } while (numPlayers < 1 || numPlayers > 4);

        // Creación de listas para almacenar jugadores y sus nombres.
        List<Player> players = new ArrayList<>();
        List<String> playerNames = new ArrayList<>();

        // Obtención de nombres de jugadores.
        for (int i = 0; i < numPlayers; i++) {
            String playerName;
            boolean validName;

            // Bucle do-while para validar y obtener nombres únicos de jugadores.
            do {
                System.out.print("Ingrese el nombre del jugador " + (i + 1) + ": ");
                playerName = teclado.nextLine().trim();

                // Validación de nombre, prohibiendo "Dealer" y nombres repetidos o vacíos.
                validName = isValidName(playerName, playerNames);

                if (playerName.equalsIgnoreCase("Dealer")) { //equalsignorecase, sirve para decir que no se puede usar este nombre porque ya hay un player(IA) usandolo
                    System.out.println("¡Error! El nombre 'Dealer' no está permitido. Ingrese otro nombre.");
                    validName = false;  // Forzar la repetición del bucle
                } else if (!validName) {
                    System.out.println("¡Error! Nombre repetido o vacío. Ingrese un nombre único.");
                }
            } while (!validName);

            // Conversión a minúsculas y creación del objeto Player, luego agregado a las listas.
            playerNames.add(playerName.toLowerCase());
            Player player = new Player(playerName);
            players.add(player);
        }

        // Creación del mazo de cartas.
        Deck deck = new Deck();

        // Distribución inicial de cartas a los jugadores.
        for (Player player : players) {
            player.addCard(deck.drawCard());
            player.addCard(deck.drawCard());
        }

        // Turnos de los jugadores.
        for (Player player : players) {
            boolean continuePlaying = true;

            // Bucle while para los turnos de cada jugador.
            while (continuePlaying) {
                player.displayHand();

                System.out.print(player.getName() + ", ¿Quieres tomar otra carta? (s/n): ");
                String AnsCard = teclado.nextLine();

                // Validación de la entrada del jugador (s/n).
                if (AnsCard.equals("s") || AnsCard.equals("n")) {
                    if (AnsCard.equals("s")) {
                        player.addCard(deck.drawCard());
                        if (player.getScore() > 21) {
                            System.out.println("¡Te has pasado de 21! " + player.getName() + " ha perdido.");
                            continuePlaying = false;
                        }
                    } else {
                        continuePlaying = false;
                    }
                } else {
                    System.out.println("Por favor, ingresa 's' o 'n' solamente.");
                }
            }
        }

        // Turno de la IA (Crupier).
        Player IA = new Player("Dealer");
        while (IA.getScore() < 17) {
            IA.addCard(deck.drawCard());
        }

        // Mostrar las manos finales de los jugadores y la IA.
        for (Player player : players) {
            player.displayHand();
        }
        IA.displayHand();

        // Determinar el ganador y mostrar resultados finales.
        int IAScore = IA.getScore();

        // Determinar el ganador y mostrar resultados finales.
        boolean dealerDefeat = IAScore > 21;
        boolean anyPlayerWins = false;

        System.out.println("Resultados finales:");

        if (dealerDefeat) {
            System.out.println("El Dealer se ha pasado de 21. ¡Todos los jugadores ganan!");
            anyPlayerWins = true;
        } else {
            System.out.println("Determinando ganadores y perdedores...");
        }

        for (Player player : players) {
            int playerScore = player.getScore();

            if (playerScore > 21) {
                System.out.println("¡" + player.getName() + " ha perdido!");
            } else if (!dealerDefeat && playerScore <= 21) {
                if (playerScore > IAScore) {
                    System.out.println("¡" + player.getName() + " gana!");
                    anyPlayerWins = true;
                } else if (playerScore == IAScore) {
                    System.out.println("¡Es un empate entre " + player.getName() + " y el Dealer!");
                } else {
                    System.out.println("¡" + player.getName() + " pierde ante el Dealer!");
                }
            }
        }

        if (!anyPlayerWins && !dealerDefeat) {
            System.out.println("Todos los jugadores han perdido contra el Dealer.");
        }


        // Cierre del objeto Scanner.
        teclado.close();
    }

    // Método privado que valida si un nombre es único y no está vacío.
    private static boolean isValidName(String playerName, List<String> existingNames) {
        return !playerName.isEmpty() && !existingNames.contains(playerName.toUpperCase()) && !existingNames.contains(playerName.toLowerCase());
    }
}
