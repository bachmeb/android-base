package us.proximal.spellwithme.model.spo;

import java.util.ArrayList;

import us.proximal.spellwithme.model.def.IWordsDAO;
import us.proximal.spellwithme.model.dto.WordDTO;

/**
 * Created by b on 11/26/14.
 */
public class SWordsDAO implements IWordsDAO {


    @Override
    public long create(WordDTO word) throws Exception {
        return 0;
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
