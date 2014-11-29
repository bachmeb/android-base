package us.proximal.spellwithme.controller.svc;

import android.app.Activity;

import java.util.ArrayList;

import us.proximal.spellwithme.controller.def.IQuestionsService;
import us.proximal.spellwithme.model.dao.AnswersDAO;
import us.proximal.spellwithme.model.dao.QuestionsDAO;
import us.proximal.spellwithme.model.def.IAnswersDAO;
import us.proximal.spellwithme.model.def.IQuestionsDAO;
import us.proximal.spellwithme.model.dto.AnswerDTO;
import us.proximal.spellwithme.model.dto.QuestionDTO;

/**
 * Created by b on 11/26/14.
 */
public class QuestionsService implements IQuestionsService {

    IQuestionsDAO daoQuestions;
    IAnswersDAO daoAnswers;
    ArrayList<QuestionDTO> questions;

    /*
    Constructor
     */
    public QuestionsService(Activity act){
        daoQuestions = new QuestionsDAO(act);
        daoAnswers = new AnswersDAO(act);

        //Populate the collection field
        try {
            questions = daoQuestions.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    Ask random
     */
    @Override
    public QuestionDTO ask(int studentId, int mkoId) throws Exception {
        QuestionDTO dto;
        int count = questions.size();
        int rand = (int)( count * Math.random() );
        dto = daoQuestions.read(rand);
        return dto;
    }

    /*
    Ask specific
     */
    @Override
    public QuestionDTO ask(int studentId, int mkoId, int questionId) throws Exception {
        QuestionDTO dto;
        dto = daoQuestions.read(questionId);
        return dto;
    }

    /*
    Create an answer record
     */
    @Override
    public void answer(AnswerDTO answer) throws Exception {
        daoAnswers.create(answer);

    }
}
