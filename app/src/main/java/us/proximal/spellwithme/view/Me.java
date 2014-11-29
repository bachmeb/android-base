package us.proximal.spellwithme.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import us.proximal.spellwithme.R;
import us.proximal.spellwithme.model.dto.QuestionDTO;
import us.proximal.spellwithme.model.dto.WordDTO;

public class Me extends BaseActivity {

    private Button btnReadYes;
    private Button btnReadNo;
    private TextView txtWord;
    private WordDTO word;
    private QuestionDTO question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
    }



}
