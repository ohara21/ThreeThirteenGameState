package edu.up.threethirteengamestate;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class GameState {
/**
     * External Citation: fill in later
     * https://stackoverflow.com/questions/4233626/allow-multi-line-in-edittext-view-in-android
     */
    //creating a deck of 52 cards
    private static char suite[] = new char[] {'c','s','h','d'};
    private ArrayList<Card> deck = new ArrayList<Card>();
    private ArrayList<Card> discardPile = new ArrayList<Card>();
    private Hand player0Hand  = new Hand();
    private  Hand player1Hand = new Hand();
    private int roundNum;
    private int player0Score;
    private int player1Score;
    private int isPlayerTurn;

    // Gamestate initialization constructor
    public GameState() {
        //populate deck with 52 card objects then shuffle deck randomly
        for(int s = 0; s < 4; s++) {
            for (int v = 1; v <= 13; v++) {
                deck.add(new Card(1, suite[s], v));
            }
        }
        Collections.shuffle(deck);

        //start the discard pile
        discardPile.add(deck.get(0));
        deck.remove(0);

        roundNum = 1;

        //populate player 0 and player 1 hands with three cards from deck
        dealHand(deck, player0Hand, roundNum);
        dealHand(deck, player1Hand, roundNum);

        player0Score = 0;
        player1Score = 0;

        isPlayerTurn = 1;
    }

    // GameState clone constructor
    public GameState(GameState orig) {
        //copy deck ArrayList
        this.deck = new ArrayList<>();
        for(Card cDeck : orig.deck){
            this.deck.add(new Card(cDeck));
        }

        //copy discard ArrayList
        this.discardPile = new ArrayList<>();
        for(Card cDisc : orig.discardPile){
            this.discardPile.add(new Card (cDisc));
        }

        //copy the other instance variables
        this.player0Hand = new Hand(orig.getPlayer0Hand());
        this.player1Hand = new Hand(orig.getPlayer1Hand());
        this.player0Score = orig.getPlayer0Score();
        this.player1Score = orig.getPlayer1Score();
        this.roundNum = orig.getRoundNum();
        this.isPlayerTurn = orig.getIsPlayerTurn();
    }

    // Getter methods
    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    public Hand getPlayer1Hand() {
        return player1Hand;
    }

    public Hand getPlayer0Hand() {
        return  player0Hand;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer0Score() {
        return player0Score;
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

    public void setPlayer1Hand(Hand player1Hand) {
        this.player1Hand = player1Hand;
    }

    public void setPlayer0Hand(Hand player0Hand) {
        this.player0Hand = player0Hand;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void setPlayer0Score(int player0Score) {
        this.player0Score = player0Score;
    }

    public void nextTurn(){
        if(isPlayerTurn == 1)
            isPlayerTurn = 0;
        else
            isPlayerTurn = 1;
    }

    public void setRoundNum(int roundNum) {
        this.roundNum = roundNum;
    }

    @Override
    public String toString() {
        String round = "Round number: " + roundNum;
        String deckSize = "Deck card amount: " + deck.size();
        String discardSize = "Discard pile amount: " + discardPile.size();
        String playerCard = "Player card amount: " + player0Hand.getSize();
        String computerCard = "Computer card amount: " + player1Hand.getSize();
        String turn = "Your turn? T/F: " + isPlayerTurn;
        String playerScoreString = "Your score: " + player0Score;
        String computerScoreString = "Computer's score: " + player1Score;
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
                player0Hand.addToHand(deck.get(0));
                deck.remove(0);
            }
            else {
                player1Hand.addToHand(deck.get(0));
                deck.remove(0);
            }
            return true;
        }
        return false;
    }

    /**
     *  draw a card from discard pile
     * @param gameState
     * @return
     */
    public void playerDrawDiscard(GameState gameState){
        //checks if there are cards in discard
        if(discardPile.size() == 0){
            return;
        }

        //checks if it is currently the player's turn
        if(canMove(gameState) == true){
            if(this.isPlayerTurn == 0){
                player0Hand.addToHand(discardPile.get(0));
            }
            else {
                player1Hand.addToHand(discardPile.get(0));
            }
            discardPile.remove(0);
        }
    }

    /**
     * determines if player can discard a card from their current hand after drawing card
     * @param gameState
     * @return
     */
    public boolean playerDiscard(GameState gameState){
        //checks if it is currently the player's turn
        if(canMove(gameState) == true) {
            if((this.isPlayerTurn == 0) && (this.player0Hand.getSize() == (this.roundNum + 2))){
                Log.d("player 0 can discard",String.valueOf(player0Hand.getSize()));
                return true;
            }
            else if((this.isPlayerTurn == 1) && (this.player1Hand.getSize() == (this.roundNum + 2))){
                Log.d("player 1 can discard",String.valueOf(player1Hand.getSize()));
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
        int[] checkGoOut = null;
        //checks if it is currently the player's turn
        if(canMove(gameState) == true){
            //need to check if player's hand can Go Out
        }
        return false;
    }



    /**
     * determines if the player can take action
     * @param gameState
     * @return
     */
    public boolean canMove(GameState gameState){
        if(gameState.getIsPlayerTurn() == this.isPlayerTurn){
            return true;
        }
        return false;
    }


    /**
     * Sets a card value to the wild card based on the hand count
     * @param wildCard
     */
    public void setWild(int wildCard){
        //TODO: set wild card to round # + 2
        wildCard = roundNum + 2;
    }


    /**
     * checks the given hand by returning an array with the differences between each consecutive card
     * @param hand
     * @return an int array with calculated differences in rank
     */
    public int[] checkHand(ArrayList<Card> hand){
        int[] checkHand = new int[hand.size()-1];
        ArrayList<Card> sortedHand = player0Hand.sortByRank(hand);
        for(int i=0; i<checkHand.length; i++){
            checkHand[i] = sortedHand.get(i+1).getCardRank()-sortedHand.get(i).getCardRank();
        }
        return checkHand;
    }

    /**
     * adds card to a users hand
     */
    public void dealHand(ArrayList<Card> inputDeck, Hand user, int round){
        for(int i = 1; i <= round + 1; i++){
            user.addToHand(inputDeck.get(0));
            inputDeck.remove(0);
        }
    }

    /**
     * looks through hand and removes card to discard pile
     * @param user
     * @param card
     */
    public void discardCard(Hand user, Card card){
        for(int i = 0; i < user.getSize(); i++){
            if(user.getHand().get(i) == card){
                discardPile.add(user.getHand().get(i));
                user.getHand().remove(i);

            }
        }
    }



}
