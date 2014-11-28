package us.proximal.spellwithme.controller.prime;

import android.content.Context;

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
    IQuestionsDAO daoQuestions;
    IWordsDAO daoWords;

    private static final String TAG = "PrimeQuestions";

    public void fillTheDatabaseWithQuestions(Context ctx){

        //This is important. The DAO needs the Context object for DB access
        daoQuestions = new QuestionsDAO(ctx);
        daoWords = new WordsDAO(ctx);

        //Get an array list of words
        ArrayList<WordDTO> words = null;
        try {
            words = daoWords.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(WordDTO word : words){
            QuestionDTO question = new QuestionDTO();

            question.setWord( word.getWord() );
            question.setDescription("This is a spelling question.");
            question.setText("How do you spell " + word.getWord() + "?");
            question.setWordId( word.getWordId() );
            try {
                daoQuestions.create(question);
                //Log.d(TAG, "Created question: " + question.getText() );
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
