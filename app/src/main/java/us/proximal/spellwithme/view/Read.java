package us.proximal.spellwithme.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import us.proximal.spellwithme.R;
import us.proximal.spellwithme.controller.def.IAnswersService;
import us.proximal.spellwithme.controller.def.IQuestionsService;
import us.proximal.spellwithme.controller.svc.AnswersService;
import us.proximal.spellwithme.controller.svc.QuestionsService;
import us.proximal.spellwithme.model.dto.AnswerDTO;
import us.proximal.spellwithme.model.dto.QuestionDTO;
import us.proximal.spellwithme.model.dto.WordDTO;

public class Read extends BaseActivity {

    //Declare objects for UI components
    private Button btnReadCorrect;
    private Button btnReadIncorrect;
    private TextView txtWord;
    private WordDTO word;
    private QuestionDTO question;

    //Declare service
    private IQuestionsService svcQuestions;
    private IAnswersService svcAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        //Instantiate service
        svcQuestions = new QuestionsService(this);
        svcAnswers = new AnswersService(this);

        //instantiate the ui components in the onCreate method
        btnReadCorrect = (Button) findViewById(R.id.buttonReadYes);
        btnReadIncorrect = (Button) findViewById(R.id.buttonReadNo);
        txtWord = (TextView) findViewById(R.id.textReadWord);

        //get a new question
        getNewQuestion();

        //attach onClick listener to the button object
        btnReadCorrect.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View currentView) {

                                              //save answer: one means got it
                                              saveAnswer("1");

                                              //get a question
                                              getNewQuestion();

                                          }
                                      }
        );

        //attach onClick listener to the button object
        btnReadIncorrect.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View currentView) {

                                             //save answer: zero means didn't get it
                                             saveAnswer("0");

                                             //get a question
                                             getNewQuestion();

                                         }
                                     }
        );


    }

    private void getNewQuestion(){

        try {
            //parameters: studentId, mkoId
            //returns random question
            question = svcQuestions.ask(0,0);

            //set the WORD to the word from the Question
            txtWord.setText(question.getWord());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveAnswer(String grade){
        //Create an AnswerDTO object
        AnswerDTO answer = new AnswerDTO();
        //Set the properties of the object
        answer.setStudentId(0);
        answer.setMkoId(0);
        answer.setDate(new Date().toString());
        //The question Id of the Answer object comes from the question object, which is a
        //property of this Activity
        answer.setQuestionId(question.getQuestionId());
        //This should come from a StudentDTO property of this Activity
        answer.setGrade(grade);
        try {
            svcQuestions.answer(answer);
        } catch (Exception e) {
            System.out.println( e.toString() );
            e.printStackTrace();
        }
    }
    public WordDTO getWord() {
        return word;
    }

    public void setWord(WordDTO word) {
        this.word = word;
    }

    public TextView getTxtWord() {
        return txtWord;
    }

    public void setTxtWord(TextView txtWord) {
        this.txtWord = txtWord;
    }

}
