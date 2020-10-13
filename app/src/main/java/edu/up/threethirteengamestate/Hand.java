package edu.up.threethirteengamestate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hand {



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
     *  Create grouping based on complete or incomplete sets and runs. Would be seperate from making a set / run.
     *  Only check if they have a legal set or run in hand. arrange cards based on user preference (no button) and then checks if the hands are set or run legal
     *  create group button: creates grouping without it being a set or run.
     *  Check would be implemented when user wants to go out / end of round
     */

    public boolean checkIfSet(ArrayList<Card> set){
        //TODO: checks if input set is a usable set
    }

    public boolean checkIfRun(ArrayList<Card> run){
        //TODO: Check if input run is a usable run
    }

    public void createGrouping(ArrayList<Card> Hand){

    }
}
