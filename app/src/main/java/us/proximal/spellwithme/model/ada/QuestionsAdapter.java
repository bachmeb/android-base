package us.proximal.spellwithme.model.ada;

import android.app.Activity;
import android.content.ContentValues;

import us.proximal.spellwithme.model.dto.QuestionDTO;

/**
 * Created by b on 11/28/14.
 */
public class QuestionsAdapter extends dbAdapter {

//    public static final String KEY_NUMBER = "PhoneNumber";
//    public static final String KEY_DESCRIPTION = "Description";
//    public static final String KEY_CONTACTID = "ContactName_id";
//
//    public static final String DATABASE_TABLE = "PhoneNumber";

    public static final String TABLE_NAME = "questions";
    public static final String PRIMARY_KEY = "questionId";

    public static final String WORD_ID = "wordId";
    public static final String DESCRIPTION = "description";
    public static final String TEXT = "text";
    public static final String TYPE = "type";
    public static final String WORD = "word";

    public QuestionsAdapter(Activity a){
        super(a);
    }

    public long create(QuestionDTO dto){
        ContentValues cv = new ContentValues();
        cv.put(WORD_ID, dto.getWordId());
        cv.put(DESCRIPTION, dto.getDescription());
        cv.put(TEXT, dto.getText());
        cv.put(TYPE, dto.getType());
        cv.put(WORD, dto.getWord());

        //return appDatabase.insert(DATABASE_TABLE, null,cv);
        return appDatabase.insert(TABLE_NAME, PRIMARY_KEY,cv);
    }

}
