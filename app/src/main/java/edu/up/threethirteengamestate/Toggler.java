package edu.up.threethirteengamestate;

import android.view.View;
import android.widget.EditText;

public class Toggler implements View.OnClickListener {

    EditText text;

    public Toggler(EditText initText){this.text = initText;}
   
    @Override
    public void onClick(View view) {
        //TODO: clear text
        text.getText().clear();

        //TODO: create gamestate called firstInstance
        GameState firstInstance = new GameState();

        //TODO: create deep copy and call it secondInstance
        GameState secondInstance = new GameState(firstInstance);

        //TODO: using firstInstance, call all methods in gamestate to make legal move. after each action print to EditText. should append

        //draw card
        firstInstance.playerDrawDeck(firstInstance);
        text.append("Player 1 draws a card from the deck");

        //discard a card
        firstInstance.discardCard(firstInstance.getPlayer1Hand(), firstInstance.getPlayer1Hand().getHand().get(0));
        text.append("Player 1 discards the first card in their hand to the discard pile");

        //set player turn to next
        firstInstance.nextTurn();
        text.append("turn is switched over to the other player");

        //TODO: create new instance using default called thirdInstance
        GameState thirdInstance = new GameState();

        //TODO: deep copy thirdInstance and call it fourthInstance
        GameState fourthInstance = new GameState(thirdInstance);

        //TODO: call toString() on secondInstance and fourthInstance. compare and print out result
        if(secondInstance.toString().equals(fourthInstance.toString()))
            text.append("Both instances are the same");
        else
            text.append("Instances have different Strings");


        //TODO: print both strings to EditTExt appending
            text.append(secondInstance.toString());
            text.append(fourthInstance.toString());

    }
}
