package com.bertoni.poker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.bertoni.poker.model.Card;
import com.bertoni.poker.model.CardHand;

public class PokerApp {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("pokerdata.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            int player1Wins = 0, player2Wins = 0, playerTies = 0;
            List<Double> player1Odds = new LinkedList<>();
            List<Double> player2Odds = new LinkedList<>();

            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                String[] cards = line.split(" ");

                // Specs define all hands are valid
                CardHand hand1 = new CardHand(Card.getBySymbol(cards[0]),
                        Card.getBySymbol(cards[1]),
                        Card.getBySymbol(cards[2]),
                        Card.getBySymbol(cards[3]),
                        Card.getBySymbol(cards[4]));
                CardHand hand2 = new CardHand(Card.getBySymbol(cards[5]),
                        Card.getBySymbol(cards[6]),
                        Card.getBySymbol(cards[7]),
                        Card.getBySymbol(cards[8]),
                        Card.getBySymbol(cards[9]));
                
                int comparison = hand1.compareTo(hand2);
                if (comparison < 0) {
                    player1Wins++;
                } else if (comparison > 0) {
                    player2Wins++;
                } else {
                    playerTies++;
                }
                
                player1Odds.add(hand1.getProbabilityWinning());
                player2Odds.add(hand2.getProbabilityWinning());
            }
            
            writer.write("1: " + player1Wins + "\r\n");
            writer.write("2: " + player2Wins + "\r\n");
            writer.write("3: " + playerTies + "\r\n");
            writer.write("4:\r\n");
            writer.write("--------- PLAYER 1 --------- | --------- PLAYER 2 ---------\r\n");
            for (int i = 0, n = player1Odds.size(); i < n; i++) {
                writer.write(String.format("           %5.2f%%            |           %5.2f%%            \r\n",
                        player1Odds.get(i), player2Odds.get(i)));
            }
            
            System.out.println("File written successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
