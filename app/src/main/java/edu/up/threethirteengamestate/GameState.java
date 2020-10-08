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
    int isPlayerTurn;
    boolean isSet;
    boolean isRun;

    // Gamestate initialization constructor
    public GameState() {
        //populate deck with 52 card objects
        for(int s = 0; s < 4; s++) {
            for (int v = 1; v <= 13; v++) {
                deck.add(new Card(1, suite[s], v));
            }
        }
        Collections.shuffle(deck);

        //start the discard pile
        discardPile.add(deck.get(0));
        deck.remove(0);

        //populate player 0 and player 1 hands with three cards from deck
        for(int i = 0; i<3; i++){
            playerZeroHand.add(deck.get(0));
            playerOneHand.add(deck.get(1));
            deck.remove(0);
            deck.remove(0);
        }
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

    public int getIsPlayerTurn() {return isPlayerTurn;}

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

    /**
     * determines if the player can draw a card from deck
     * @param gameState
     * @return
     */
    public boolean playerDrawDeck(GameState gameState){
        //checks if there are cards in deck
        if(deck.size() == 0){
            return false;
        }

        //checks if it is currently the player's turn
        if(gameState.getIsPlayerTurn() == this.isPlayerTurn){
            if(this.isPlayerTurn == 0){
                playerZeroHand.add(deck.get(0));
                deck.remove(0);
            }
            else {
                playerOneHand.add(deck.get(0));
                deck.remove(0);
            }
            return true;
        }
        return false;
    }

    /**
     * determines if player can draw a card from discard pile
     * @param gameState
     * @return
     */
    public boolean playerDrawDiscard(GameState gameState){
        //checks if there are cards in discard
        if(discardPile.size() == 0){
            return false;
        }

        //checks if it is currently the player's turn
        if(gameState.getIsPlayerTurn() == this.isPlayerTurn){
            if(this.isPlayerTurn == 0){
                playerZeroHand.add(discardPile.get(0));
                discardPile.remove(0);
            }
            else {
                playerOneHand.add(discardPile.get(0));
                discardPile.remove(0);
            }
            return true;
        }
        return false;
    }

    /**
     * determines if player can discard a card from their current hand after drawing card
     * @param gameState
     * @return
     */
    public boolean playerDiscard(GameState gameState){
        //checks if it is currently the player's turn
        if(gameState.getIsPlayerTurn() == this.isPlayerTurn){
            if((this.isPlayerTurn == 0) && (this.playerZeroHand.size() == (this.roundNum + 3))){
                Log.d("player 0 can discard",String.valueOf(playerZeroHand.size()));
                return true;
            }
            else if((this.isPlayerTurn == 1) && (this.playerOneHand.size() == (this.roundNum + 3))){
                Log.d("player 1 can discard",String.valueOf(playerOneHand.size()));
                return true;
            }
        }
        return false;
    }

    /**
     * determines if player can Go Out
     * Go Out: all of player's cards except one must be in run/set to Go Out
     * @param gameState
     * @return
     */
    public boolean playerGoOut(GameState gameState){
        //checks if it is currently the player's turn
        if(gameState.getIsPlayerTurn() == this.isPlayerTurn){
            //need to check if player's hand can Go Out
            if(this.isPlayerTurn == 0){
            }
            else {
            }
            return true;
        }
        return false;
    }

    /**
     *
     */
    public void sortBySuit(){

    }

    public void sortByRank(){

    }
}
