package us.proximal.spellwithme.controller.svc;

import android.content.Context;

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

    public WordsService(Context ctx){
        //This is important. The DAO needs the Context object for DB access
        dao = new WordsDAO(ctx);
        //get a list of all the words
        try {
            words = dao.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
