package us.proximal.spellwithme.model.def;

import java.util.ArrayList;

import us.proximal.spellwithme.model.dto.QuestionDTO;

/**
 * Created by b on 11/26/14.
 */
public interface IQuestionsDAO {
    long create(QuestionDTO q) throws Exception;

    QuestionDTO read(int accountId) throws Exception;

    QuestionDTO read(String qText) throws Exception;

    boolean update(QuestionDTO q) throws Exception;

    void delete(QuestionDTO q) throws Exception;

    ArrayList<QuestionDTO> list() throws Exception;
}
