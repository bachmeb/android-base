package us.proximal.spellwithme.model.test;

import java.util.ArrayList;

import us.proximal.spellwithme.model.def.IWordsDAO;
import us.proximal.spellwithme.model.dto.WordDTO;

/**
 * Created by b on 11/26/14.
 */
public class TWordsDAO implements IWordsDAO {
    @Override
    public boolean create(WordDTO word) {
        return false;
    }

    @Override
    public WordDTO read(int wordId) throws Exception {
        return null;
    }

    @Override
    public WordDTO read(String word) throws Exception {
        return null;
    }

    @Override
    public void update(WordDTO word) {

    }

    @Override
    public void delete(WordDTO word) {

    }

    @Override
    public ArrayList<WordDTO> list() {
        return null;
    }
}
