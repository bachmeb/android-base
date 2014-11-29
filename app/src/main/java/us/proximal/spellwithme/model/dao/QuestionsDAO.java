package us.proximal.spellwithme.model.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import us.proximal.spellwithme.model.ada.ProximalDbAdapter;
import us.proximal.spellwithme.model.def.IQuestionsDAO;
import us.proximal.spellwithme.model.dto.QuestionDTO;

/**
 * Created by b on 11/28/14.
 */
public class QuestionsDAO extends ProximalDbAdapter implements IQuestionsDAO {

    public static final String TABLE_NAME = "questions";
    public static final String PRIMARY_KEY = "questionId";

    public static final String WORD_ID = "wordId";
    public static final String DESCRIPTION = "description";
    public static final String TEXT = "text";
    public static final String TYPE = "type";
    public static final String WORD = "word";

    /*
    Constructor needs Activity
     */
    public QuestionsDAO(Activity a){
        super(a);
    }

    @Override
    public long create(QuestionDTO dto) throws Exception {

        // Create a CV object
        ContentValues cv = new ContentValues();

        // Add values from DTO fields
        cv.put(WORD_ID, dto.getWordId() );
        cv.put(DESCRIPTION, dto.getDescription() );
        cv.put(TEXT, dto.getText() );
        cv.put(TYPE, dto.getType() );
        cv.put(WORD,  dto.getWord() );

        // put the values into database
        long result =  appDatabase.insert(TABLE_NAME, PRIMARY_KEY, cv);

        return result;

    }

    @Override
    public QuestionDTO read(int key) throws Exception {

        // Write the query
        String sql  = "select * from " + TABLE_NAME + " where "+ PRIMARY_KEY +" = '" + key +"' ";

        // Run the query
        super.open();
        Cursor cursor = appDatabase.rawQuery(sql, null);

        //Test cursor count
        if (cursor.getCount() == 1) {

            // Move the cursor to the first record
            cursor.moveToFirst();

            // Make a DTO object
            QuestionDTO dto = populateObjectFromCursor(cursor);

            // return DTO
            return dto;

        } else if (cursor.getCount() > 1){
            // More than one
            throw new Exception("Too many results returned.  Expected 1, got " + cursor.getCount());
        } else {
            // Less than one
            return null;
        }
    }

    @Override
    public QuestionDTO read(String text) throws Exception {
        //TODO: implement me
        return null;
    }

    private QuestionDTO populateObjectFromCursor(Cursor cursor) {

        // Create a new DTO
        QuestionDTO dto = new QuestionDTO();

        // Create variables for each cursor field
        int qid = cursor.getInt(cursor.getColumnIndex(PRIMARY_KEY) );
        int wid = cursor.getInt(cursor.getColumnIndex(WORD_ID));
        String des = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
        String tex = cursor.getString(cursor.getColumnIndex(TEXT));
        String typ = cursor.getString(cursor.getColumnIndex(TYPE));
        String wor = cursor.getString(cursor.getColumnIndex(WORD));

        // Populate the DTO with the variables
        dto.setQuestionId(qid);
        dto.setWordId(wid);
        dto.setDescription(des);
        dto.setText(tex);
        dto.setType(typ);
        dto.setWord(wor);

        // Return the object
        return dto;
    }

    @Override
    public boolean update(QuestionDTO q) throws Exception {
        //TODO: Implement me...
        return false;
    }

    @Override
    public void delete(QuestionDTO q) throws Exception {
        //TODO: Implement me...
    }

    @Override
    public ArrayList<QuestionDTO> list() {

        // Make a new ArrayList
        ArrayList<QuestionDTO> list = new ArrayList<QuestionDTO>();

        // Write the SQL query
        String sql  = "select * from " + TABLE_NAME;

        // Execute the query
        super.open();
        Cursor cursor = appDatabase.rawQuery(sql, null);

        // Move the cursor to the first record
        cursor.moveToFirst();

        // Loop for as long as the cursor is not after the last record
        while(!cursor.isAfterLast()) {

            // populate the object from the cursor
            QuestionDTO dto = populateObjectFromCursor(cursor);

            // add the DTO to the collection of DTOs
            list.add(dto);

            // Move the cursor to the next record
            cursor.moveToNext();
        }
        // Return the collection
        return list;
    }

}
