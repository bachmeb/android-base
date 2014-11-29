package us.proximal.spellwithme.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import us.proximal.spellwithme.R;
import us.proximal.spellwithme.controller.def.IQuestionsService;
import us.proximal.spellwithme.controller.def.IWordsService;
import us.proximal.spellwithme.controller.svc.WordsService;

public class Spell extends BaseActivity {

    Button btnCorrect;
    Button btnIncorrect;
    TextView txtWord;

    IWordsService svcWords;
    IQuestionsService svcQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell);

        //Initialize words service
        svcWords = new WordsService(this);

        //Initialize ui objects
        btnCorrect = (Button) findViewById(R.id.buttonSpellIncorrect);
        btnIncorrect = (Button) findViewById(R.id.buttonSpellCorrect);
        txtWord = (TextView) findViewById(R.id.textSpellWord);

        //Set the spelling word
        setSpellingWord();

        //attach onClick listener to the button object
        btnCorrect.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View currentView) {
                                                   answerCorrect();
                                               }
                                           }
        );

        //attach onClick listener to the button object
        btnIncorrect.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View currentView) {
                                                   answerIncorrect();
                                               }
                                           }
        );

    }

    public void answerCorrect(){
        //make a record in the database to note this event


        //makeToast("correct");
        setSpellingWord();
    }

    public void answerIncorrect(){
        //make a record in the database to note this event


        //makeToast("incorrect");
        setSpellingWord();
    }


    public void setSpellingWord(){

        txtWord.setText(svcWords.getRandomWord().getWord());


    }


}
