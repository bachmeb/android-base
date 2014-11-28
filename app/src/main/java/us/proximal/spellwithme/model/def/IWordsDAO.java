package us.proximal.spellwithme.model.def;

import java.util.ArrayList;

import us.proximal.spellwithme.model.dto.WordDTO;

/**
 * Created by b on 11/24/14.
 */
public interface IWordsDAO {

    public boolean create(WordDTO word) throws Exception;

    public WordDTO read(int wordId) throws Exception;

    public WordDTO read(String word) throws Exception;

    public void update(WordDTO word) throws Exception;

    public void delete(WordDTO word) throws Exception;

    public ArrayList<WordDTO> list() throws Exception;


}
