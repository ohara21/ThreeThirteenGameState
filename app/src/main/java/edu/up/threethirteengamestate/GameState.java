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
    char suite[] = new char[] {'c','s','h','d'};
    ArrayList<Card> deck = new ArrayList<Card>();
    ArrayList<Card> discardPile = new ArrayList<Card>();
    ArrayList<Card> player0Hand = new ArrayList<Card>();
    ArrayList<Card> player1Hand = new ArrayList<Card>();
    int roundNum;
    int player0Score;
    int player1Score;
    int isPlayerTurn;

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

        //populate player 0 and player 1 hands with three cards from deck
        for(int i = 0; i<3; i++){
            player0Hand.add(deck.get(0));
            player1Hand.add(deck.get(1));
            deck.remove(0);
            deck.remove(0);
        }
        roundNum = 1;
        player0Score = 0;
        player1Score = 0;
    }

    // GameState clone constructor
    public GameState(GameState gameState) {
        this.deck = gameState.getDeck();
        this.discardPile = gameState.getDiscardPile();
        this.player0Hand = gameState.getPlayer0Hand();
        this.player1Hand = gameState.getPlayer1Hand();
        this.player0Score = gameState.getPlayer0Score();
        this.player1Score = gameState.getPlayer1Score();
        this.roundNum = gameState.getRoundNum();
    }

    // Getter methods
    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    public ArrayList<Card> getPlayer1Hand() {
        return player1Hand;
    }

    public ArrayList<Card> getPlayer0Hand() {
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

    public void setPlayer1Hand(ArrayList<Card> player1Hand) {
        this.player1Hand = player1Hand;
    }

    public void setPlayer0Hand(ArrayList<Card> player0Hand) {
        this.player0Hand = player0Hand;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void setPlayer0Score(int player0Score) {
        this.player0Score = player0Score;
    }

    public void setRoundNum(int roundNum) {
        this.roundNum = roundNum;
    }

    @Override
    public String toString() {
        String round = "Round number: " + roundNum;
        String deckSize = "Deck card amount: " + deck.size();
        String discardSize = "Discard pile amount: " + discardPile.size();
        String playerCard = "Player card amount: " + player0Hand.size();
        String computerCard = "Computer card amount: " + player1Hand.size();
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
        if(canMove(gameState) == true){
            if(this.isPlayerTurn == 0){
                player0Hand.add(deck.get(0));
                deck.remove(0);
            }
            else {
                player1Hand.add(deck.get(0));
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
        if(canMove(gameState) == true){
            if(this.isPlayerTurn == 0){
                player0Hand.add(discardPile.get(0));
                discardPile.remove(0);
            }
            else {
                player1Hand.add(discardPile.get(0));
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
        if(canMove(gameState) == true) {
            if((this.isPlayerTurn == 0) && (this.player0Hand.size() == (this.roundNum + 3))){
                Log.d("player 0 can discard",String.valueOf(player0Hand.size()));
                return true;
            }
            else if((this.isPlayerTurn == 1) && (this.player1Hand.size() == (this.roundNum + 3))){
                Log.d("player 1 can discard",String.valueOf(player1Hand.size()));
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
            if(this.isPlayerTurn == 0){
                checkGoOut = checkHand(player0Hand);
            }
            else {
                checkGoOut = checkHand(player1Hand);
            }
        }
        return false;
    }

    /**
     * sorts a given hand by their rank in ascending order
     * @param hand
     * @return a sorted array list of a given hand
     *
     * External Citation:
     * Problem: Wanted to sort an array list
     * Date: 10/11/20
     * Source:https://stackoverflow.com/questions/9109890/android-java-how-to-sort-a-list-of-objects-by-a-certain-value-within-the-object
     * Solution: used the code
     */
    public ArrayList<Card> sortByRank(final ArrayList<Card> hand){
        Collections.sort(hand, new Comparator<Card>() {
            @Override
            public int compare(Card card1, Card card2) {
                return Integer.valueOf(card1.getCardRank()).compareTo(Integer.valueOf(card2.getCardRank()));
            }
        });
        return hand;
    }

    /**
     * sorts a given hand by their suit
     * @param hand
     * @return a sorted array list of a given hand
     */
    public ArrayList<Card> sortBySuit(final ArrayList<Card> hand){
        Collections.sort(hand, new Comparator<Card>() {
            @Override
            public int compare(Card card1, Card card2) {
                //TODO: this just sorts by suit, doesn't numerically sort in each suit
                return Integer.valueOf(card1.getCardSuit()).compareTo(Integer.valueOf(card2.getCardSuit()));
            }
        });
        return hand;
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
     * checks the given hand by returning an array with the differences between each consecutive card
     * @param hand
     * @return an int array with calculated differences in rank
     */
    public int[] checkHand(ArrayList<Card> hand){
        int[] checkHand = new int[hand.size()-1];
        ArrayList<Card> sortedHand = sortByRank(hand);
        for(int i=0; i<checkHand.length; i++){
            checkHand[i] = sortedHand.get(i+1).getCardRank()-sortedHand.get(i).getCardRank();
        }
        return checkHand;
    }
}
