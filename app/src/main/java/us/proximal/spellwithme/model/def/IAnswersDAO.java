package us.proximal.spellwithme.model.def;

import java.util.ArrayList;

import us.proximal.spellwithme.model.dto.AnswerDTO;

/**
 * Created by b on 11/26/14.
 */
public interface IAnswersDAO {

    boolean create(AnswerDTO answer) throws Exception;

    AnswerDTO read(int answerId) throws Exception;

    boolean update(AnswerDTO answer) throws Exception;

    void delete(AnswerDTO answer) throws Exception;

    ArrayList<AnswerDTO> list() throws Exception;
}
