package edu.up.threethirteengamestate;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class GameState {
    /**
     * External Citation: fill in later
     * https://stackoverflow.com/questions/4233626/allow-multi-line-in-edittext-view-in-android
     */
    //creating a deck of 52 cards
    char suite[] = new char[] {'c','s','h','d'};
    ArrayList<Card> deck = new ArrayList<Card>();
    ArrayList<Card> discardPile = new ArrayList<Card>();
    ArrayList<Card> playerZeroHand = new ArrayList<Card>();
    ArrayList<Card> playerOneHand = new ArrayList<Card>();
    int roundNum;
    int playerScore;
    int computerScore;
    boolean isPlayerTurn;
    boolean isSet;
    boolean isRun;

    // Gamestate initialization constructor
    public GameState() {
        for(int s = 0; s < 4; s++) {
            for (int v = 1; v <= 13; v++) {
                deck.add(new Card(1, suite[s], v));
            }
        }
        Collections.shuffle(deck);
        discardPile.add(deck.get(0));
        deck.remove(0);
        playerZeroHand = null;
        playerOneHand = null;
        roundNum = 1;
        playerScore = 0;
        computerScore = 0;
    }

    // GameState clone constructor
    public GameState(GameState gameState) {
        this.deck = gameState.getDeck();
        this.discardPile = gameState.getDiscardPile();
        this.playerZeroHand = gameState.getPlayerZeroHand();
        this.playerOneHand = gameState.getPlayerOneHand();
        this.playerScore = gameState.getPlayerScore();
        this.computerScore = gameState.getComputerScore();
        this.roundNum = gameState.getRoundNum();
    }

    // Getter methods
    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    public ArrayList<Card> getPlayerOneHand() {
        return playerOneHand;
    }

    public ArrayList<Card> getPlayerZeroHand() {
        return  playerZeroHand;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getRoundNum() {
        return roundNum;
    }

    // Setter methods
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public void setDiscardPile(ArrayList<Card> discardPile) {
        this.discardPile = discardPile;
    }

    public void setPlayerOneHand(ArrayList<Card> playerOneHand) {
        this.playerOneHand = playerOneHand;
    }

    public void setPlayerZeroHand(ArrayList<Card> playerZeroHand) {
        this.playerZeroHand = playerZeroHand;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void setRoundNum(int roundNum) {
        this.roundNum = roundNum;
    }

    @Override
    public String toString() {
        String round = "Round number: " + roundNum;
        String deckSize = "Deck card amount: " + deck.size();
        String discardSize = "Discard pile amount: " + discardPile.size();
        String playerCard = "Player card amount: " + playerZeroHand.size();
        String computerCard = "Computer card amount: " + playerOneHand.size();
        String turn = "Your turn? T/F: " + isPlayerTurn;
        String playerScoreString = "Your score: " + playerScore;
        String computerScoreString = "Computer's score: " + computerScore;
        String toString = round + "\n" + deckSize + "\n" + discardSize + "\n" + playerCard + "\n"
                + computerCard + "\n" + turn + "\n" + playerScoreString + "\n"
                + computerScoreString;
        return toString;
    }
}
