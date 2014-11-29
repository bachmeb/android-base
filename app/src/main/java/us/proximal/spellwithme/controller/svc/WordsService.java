package us.proximal.spellwithme.controller.svc;

import android.app.Activity;

import java.util.ArrayList;

import us.proximal.spellwithme.model.dao.WordsDAO;
import us.proximal.spellwithme.model.def.IWordsDAO;
import us.proximal.spellwithme.model.dto.WordDTO;

/**
 * Created by b on 11/24/14.
 */
public class WordsService implements us.proximal.spellwithme.controller.def.IWordsService {

    //Declare an instance of the DAO
    private IWordsDAO dao;
    private ArrayList<WordDTO> words;

    /*
    Constructor
     */
    public WordsService(Activity act){
        //Instantiate the DAO
        dao = new WordsDAO(act);

    }

    @Override
    public WordDTO getRandomWord() {
        WordDTO word;
        int count = words.size();
        int rand = (int)(count * Math.random());

        try {
            word = dao.read(rand);
            return word;
        } catch (Exception e) {
            System.out.println( e.toString() );
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public WordDTO getWordById(int wordId) {
        WordDTO word;

        try {
            word = dao.read(wordId);
            return word;
        } catch (Exception e) {
            System.out.println( e.toString() );
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public WordDTO getWordByText(String text) {
        WordDTO word;

        try {
            word = dao.read(text);
            return word;
        } catch (Exception e) {

            System.out.println( e.toString() );
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public WordDTO getWordByLength(int length) {
        WordDTO word;

        try {
            word = dao.read(0);
            return word;
        } catch (Exception e) {

            System.out.println( e.toString() );
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public WordDTO getWordByType(String type) {
        WordDTO word;

        try {
            word = dao.read(0);
            return word;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<WordDTO> getDolchWords() {
        ArrayList<WordDTO> words;

        try {
            words = dao.list();
            return words;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<WordDTO> getFryWords() {
        ArrayList<WordDTO> words;

        try {
            words = dao.list();
            return words;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
