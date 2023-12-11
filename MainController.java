package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);


        System.out.println("BBBBB   L      AAAAA  CCCCC  K   K  JJJJJ  AAAAA  CCCCC  K   K");
        System.out.println("B    B  L      A   A  C      K  K     J    A   A  C      K  K ");
        System.out.println("BBBBB   L      AAAAA  C      KKK      J    AAAAA  C      KKK  ");
        System.out.println("B    B  L      A   A  C      K  K  J  J    A   A  C      K  K ");
        System.out.println("BBBBB   LLLLL  A   A  CCCCC  K   K  JJ     A   A  CCCCC  K   K");

        System.out.println("¡Bienvenido al Casino La Caprichosa!");

        int numPlayers = 0;
        do {
            System.out.print("Ingrese el número de jugadores (máximo 4): ");
            try {
                numPlayers = Integer.parseInt(teclado.nextLine());
                if (numPlayers < 1 || numPlayers > 4) {
                    System.out.println("Error: Ingrese un número válido entre 1 y 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        } while (numPlayers < 1 || numPlayers > 4);

        List<Player> players = new ArrayList<>();
        List<String> playerNames = new ArrayList<>();

        // Obtener nombres de jugadores
        for (int i = 0; i < numPlayers; i++) {
            String playerName;
            boolean validName;

            do {
                System.out.print("Ingrese el nombre del jugador " + (i + 1) + ": ");
                playerName = teclado.nextLine().trim();

                validName = isValidName(playerName, playerNames);
                if (!validName) {
                    System.out.println("¡Error! Nombre repetido o vacío. Ingrese un nombre único.");
                }
            } while (!validName);

            playerNames.add(playerName.toLowerCase());  // Convertir a minúsculas antes de agregar
            Player player = new Player(playerName);
            players.add(player);
        }

        Deck deck = new Deck();


        for (Player player : players) {
            player.addCard(deck.drawCard());
            player.addCard(deck.drawCard());
        }

        // Turnos de los jugadores
        for (Player player : players) {
            boolean continuePlaying = true;

            while (continuePlaying) {
                player.displayHand();

                System.out.print(player.getName() + ", ¿Quieres tomar otra carta? (s/n): ");
                String bj = teclado.nextLine();

                if (bj.equals("s")) {
                    player.addCard(deck.drawCard());
                    if (player.getScore() > 21) {
                        System.out.println("¡Te has pasado de 21! " + player.getName() + " ha perdido.");
                        continuePlaying = false;
                    }
                } else {
                    continuePlaying = false;
                }
            }
        }

        // Turno de la IA
        Player IA = new Player("Crupier");
        while (IA.getScore() < 17) {
            IA.addCard(deck.drawCard());
        }

        // Mostrar las manos finales
        for (Player player : players) {
            player.displayHand();
        }
        IA.displayHand();


// Determinar el ganador
        int IAScore = IA.getScore();
        boolean Wn = IAScore > 21;

        System.out.println("Resultados finales:");

// Determinar el ganador entre los jugadores y la IA
        for (Player player : players) {
            int playerScore = player.getScore();

            if (playerScore > 21) {
                System.out.println("¡" + player.getName() + " ha perdido!");
            } else if (Wn || (IAScore <= 21 && IAScore > playerScore)) {
                System.out.println("¡" + player.getName() + " pierde ante el Crupier!");
            } else if (playerScore == IAScore) {
                System.out.println("¡Es un empate entre " + player.getName() + " y el Crupier!");
            } else {
                System.out.println("¡" + player.getName() + " gana!");
            }
        }

// Determinar el ganador entre cada jugador y la IA
        if (IAScore > 21) {
            System.out.println("El Crupier se ha pasado de 21. ¡Todos los jugadores ganan!");
        } else {
            for (Player player : players) {
                int playerScore = player.getScore();

                if (playerScore <= 21 && playerScore > IAScore) {
                    System.out.println("¡" + player.getName() + " gana al Crupier!");
                } else if (playerScore <= 21 && playerScore == IAScore) {
                    System.out.println("¡" + player.getName() + " empata con el Crupier!");
                } else {
                    System.out.println("¡" + player.getName() + " pierde ante el Crupier!");
                }
            }
        }

        teclado.close();
    }

    private static boolean isValidName(String playerName, List<String> existingNames) {
        // Validar que el nombre no esté repetido o vacío
        return !playerName.isEmpty() && !existingNames.contains(playerName.toUpperCase()) && !existingNames.contains(playerName.toLowerCase());
    }
}
