package us.proximal.spellwithme.controller.prime;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import us.proximal.spellwithme.model.dao.QuestionsDAO;
import us.proximal.spellwithme.model.dao.WordsDAO;
import us.proximal.spellwithme.model.def.IQuestionsDAO;
import us.proximal.spellwithme.model.def.IWordsDAO;
import us.proximal.spellwithme.model.dto.QuestionDTO;
import us.proximal.spellwithme.model.dto.WordDTO;

/**
 * Created by b on 11/26/14.
 */
public class PrimeQuestions {
    IWordsDAO daoWords;
    IQuestionsDAO daoQuestions;
    ArrayList<WordDTO> words;

    private static final String TAG = "PrimeQuestions";

    public void fillTheDatabaseWithQuestions(Context ctx){

        //This is important. The DAO needs the Context object for DB access
        daoWords = new WordsDAO(ctx);
        daoQuestions = new QuestionsDAO(ctx);

        try {
            //Get an array list of words
            words = daoWords.list();
        } catch (Exception e) {
            makeToast(ctx, "Failed on daoWords.list()");
            makeToast(ctx, e.toString() );
            e.printStackTrace();
        }

        for(WordDTO word : words){
            QuestionDTO question = new QuestionDTO();

            question.setWord( word.getWord() );
            question.setDescription("This is a spelling question.");
            question.setText("How do you spell the word '" + word.getWord() + "'?");
            question.setWordId( word.getWordId() );
            try {
                daoQuestions.create(question);
                //Log.d(TAG, "Created question: " + question.getText() );
            } catch (Exception e) {
                makeToast(ctx, e.toString());
                e.printStackTrace();
            }

        }

    }

    /*
    General method for making toast
    */
    public void makeToast(Context ctx, String toast){

        Toast.makeText(ctx.getApplicationContext(), toast, Toast.LENGTH_LONG).show();


    }

}
