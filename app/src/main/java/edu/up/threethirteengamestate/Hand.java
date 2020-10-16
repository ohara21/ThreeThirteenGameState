package edu.up.threethirteengamestate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hand {
    private ArrayList<Card> userHand = null;
    private ArrayList<ArrayList<Card>> groupings = null;
    private static final int MAX_NUM_GROUPS = 4;

    /**
     * Hand Class default constructor
     * new instance of a hand
     * creates an ArrayList that contains players cards and
     * 2D ArrayList that contains groups of cards
     */
    public Hand(){
        this.userHand = new ArrayList<Card>();
        this.groupings = new ArrayList<ArrayList<Card>>(MAX_NUM_GROUPS);
        for(int i=0; i<MAX_NUM_GROUPS; i++){
            groupings.add(new ArrayList<Card>());
        }
    }

    /**
     * copy constructor
     * copies the hand and groupings ArrayLists
     * @param orig
     */
    public Hand(Hand orig){
        //copy the user hand
        this.userHand = new ArrayList<Card>();
        for(Card c : orig.userHand){
            this.userHand.add(new Card(c));
        }

        //copy the groupings
        int count = 0;
        this.groupings = new ArrayList<ArrayList<Card>>(MAX_NUM_GROUPS);
        for(ArrayList<Card> group: orig.groupings){
            for(Card c : group){
                this.groupings.get(count).add(new Card(c));
            }
            count++;
        }

    }

    public void addToHand(Card c){
        userHand.add(c);
    }

    public void setHand(ArrayList<Card> hand){
        this.userHand = hand;
    }

    public ArrayList<Card> getHand(){return this.userHand;}

    public int getSize(){
       return this.userHand.size();
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
     *  Create grouping based on complete or incomplete sets and runs. Would be separate from making a set / run.
     *  Only check if they have a legal set or run in hand. arrange cards based on user preference (no button)
     *  and then checks if the hands are set or run legal
     *  create group button: creates grouping without it being a set or run.
     *  Check would be implemented when user wants to go out / end of round
     */

    public boolean checkIfSet(ArrayList<Card> set){
        //TODO: checks if input set is a usable set
        for (Card card : set) {
            // Gets index of first card so the next two cards can be compared by index
            int indexStorage = set.indexOf(card);
            Card secondCard = set.get(indexStorage + 1);
            Card thirdCard = set.get(indexStorage + 2);
            // If card one and two have the same rank, continue
            if (card.getCardRank() == secondCard.getCardRank()) {
                // If card two and three also have the same rank, a set is legal
                if (secondCard.getCardRank() == thirdCard.getCardRank()) {
                    return true;
                }
            }
        }
        // Returns false if there are no cases of legal sets
        return false;

        //the difference between each consecutive card should be 0 in a set
        int[] checkSet = checkHand(set);
        for(int i=0; i<checkSet.length;i++){
            if(checkSet[i] != 0){
                return false;
            }
        }
        return true;
    }

    public boolean checkIfRun(ArrayList<Card> run){
        //TODO: Check if input run is a usable run
        
        // Makes sure cards are in order by rank before comparison
        ArrayList<Card> sortedRun = sortByRank(run);
        for (Card card : run) {
            // Gets index of first card so the next two cards can be compared by index
            int indexStorage = run.indexOf(card);
            Card secondCard = run.get(indexStorage + 1);
            Card thirdCard = run.get(indexStorage + 2);
            // Confirms that all cards are in the same suit
            if (card.getCardSuit() == secondCard.getCardSuit() && secondCard.getCardSuit() == thirdCard.getCardSuit()) {
                // If the ranks increase by one for each card, a run is legal
                if (card.getCardRank() == secondCard.getCardRank() + 1 && secondCard.getCardRank() == thirdCard.getCardRank() + 1) {
                    return true;
                }
            }
        }
        return false;

        //get the first card's suit
        char checkSuit = run.get(0).getCardSuit();
        //iterate through through the run to check if they all have the same suit
        for(Card c : run){
            if(c.getCardRank() != checkSuit){
                return false;
            }
        }
        //check to make sure the difference between consective cards is 1
        int[] checkRun = checkHand(run);
        for(int i=0; i<checkRun.length;i++){
            if(checkRun[i] != 1){
                return false;
            }
        }
        return true;
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

    public void createGrouping(ArrayList<Card> Hand){

    }

    public void addToGroup(Card add, ArrayList<Card> group){

    }
}
