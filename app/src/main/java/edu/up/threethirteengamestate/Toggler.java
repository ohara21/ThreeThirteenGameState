package edu.up.threethirteengamestate;
/**
 * @description: Toggler class shows the GameState's methods by calling toString
 * @author: Nick Ohara, Adrian Muth, Shane Matsushima, Lindsey Warren
 * @version: 10/20/2020
 */

import android.view.View;
import android.widget.EditText;

public class Toggler implements View.OnClickListener {

    EditText text;

    public Toggler(EditText initText){this.text = initText;}
   
    @Override
    public void onClick(View view) {
        //clear text
        text.getText().clear();

        //create gamestate called firstInstance
        GameState firstInstance = new GameState();

        //create deep copy and call it secondInstance
        GameState secondInstance = new GameState(firstInstance);

        //using firstInstance, call all methods in gamestate to make legal move.
        //after each action, append to EditText
        //player 0 draws card from deck
        text.append("Initialization: Both players are dealt three cards and the discard pile is created.\n");
        firstInstance.playerDrawDeck(firstInstance);
        text.append("Player 0 draws a card from the deck\n");

        //player 0 discards a card
        if(firstInstance.playerDiscard(firstInstance)) {
            firstInstance.discardCard(firstInstance.getPlayer0Hand(), firstInstance.getPlayer0Hand().getHand().get(0));
            text.append("Player 0 discards the first card in their hand to the discard pile\n");
        }

        //set player turn to next
        firstInstance.nextTurn();
        text.append("turn is switched over to the other player\n");

        //player 1 draws card from discard pile
        firstInstance.playerDrawDiscard(firstInstance);
        text.append("Player 1 draws from the discard pile\n");

        //player 1 tries to go out
        if(firstInstance.playerGoOut(firstInstance)){
            text.append("Player 1 tried Going Out successfully\n\n");
        }
        else{
            text.append("Player 1 tried Going Out but they didn't have the right hand\n\n");
        }

        //create new instance using default called thirdInstance
        GameState thirdInstance = new GameState();

        //deep copy thirdInstance and call it fourthInstance
        GameState fourthInstance = new GameState(thirdInstance);

        //call toString() on secondInstance and fourthInstance. compare and print out result
        if(secondInstance.toString().equals(fourthInstance.toString())) {
            text.append("Both instances are the same\n\n");
        }
        else {
            text.append("Instances have different Strings\n\n");
        }


        //print both strings to EditTExt appending
        text.append("First Instance toString:\n");
        text.append(firstInstance.toString());
        text.append("\n\n");
        text.append("Second Instance toString:\n");
        text.append(secondInstance.toString());
        text.append("\n\n");
        text.append("Fourth Instance toString:\n");
        text.append(fourthInstance.toString());
    }
}
