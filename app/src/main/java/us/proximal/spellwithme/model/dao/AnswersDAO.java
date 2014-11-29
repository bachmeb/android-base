package us.proximal.spellwithme.model.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import us.proximal.spellwithme.model.ada.ProximalDbAdapter;
import us.proximal.spellwithme.model.def.IAnswersDAO;
import us.proximal.spellwithme.model.dto.AnswerDTO;

/**
 * Created by b on 11/26/14.
 */
public class AnswersDAO extends ProximalDbAdapter implements IAnswersDAO {

    public static final String TABLE_NAME = "answers";
    public static final String PRIMARY_KEY = "answerId";

    public static final String QUESTION_ID = "questionId";
    public static final String STUDENT_ID = "studentId";
    public static final String MKO_ID = "mkoId";
    public static final String DATE = "date";
    public static final String GRADE = "grade";

    public AnswersDAO(Activity a) {
        super(a);
    }

    @Override
    public boolean create(AnswerDTO dto) throws Exception {

        //
        ContentValues cv = new ContentValues();
        // Add fields from DTO
        cv.put(QUESTION_ID, dto.getQuestionId() );
        cv.put(STUDENT_ID, dto.getStudentId() );
        cv.put(MKO_ID, dto.getMkoId() );
        cv.put(DATE, dto.getDate() );
        cv.put(GRADE,  dto.getGrade() );

        // put the values into database
        super.open();
        appDatabase.insert(TABLE_NAME, PRIMARY_KEY, cv);

        return true;

    }

    @Override
    public AnswerDTO read(int key) throws Exception {

        // Write the query
        String sql  = "select * from " + TABLE_NAME + " where "+ PRIMARY_KEY +" = '" + key +"' ";

        // Run the query
        super.open();
        Cursor cursor = appDatabase.rawQuery(sql, null);


        if (cursor.getCount() == 1) {
            //
            cursor.moveToFirst();

            //
            AnswerDTO dto = populateObjectFromCursor(cursor);

            //
            return dto;
        } else if (cursor.getCount() > 1){
            //
            throw new Exception("Too many results returned.  Expected 1, got " + cursor.getCount());
        } else {
            //
            return null;
        }

    }

    private AnswerDTO populateObjectFromCursor(Cursor cursor) {

        // Create a new DTO
        AnswerDTO dto = new AnswerDTO();

        // Create variables for each cursor field
        int answerId = cursor.getInt(cursor.getColumnIndex(PRIMARY_KEY) );
        int questionId = cursor.getInt(cursor.getColumnIndex(QUESTION_ID));
        int studentId = cursor.getInt(cursor.getColumnIndex(STUDENT_ID));
        int mkoId = cursor.getInt(cursor.getColumnIndex(MKO_ID));
        String date = cursor.getString(cursor.getColumnIndex(DATE));
        String grade = cursor.getString(cursor.getColumnIndex(GRADE));

        // Populate the DTO with the variables
        dto.setAnswerId(answerId);
        dto.setDate(date);
        dto.setGrade(grade);
        dto.setMkoId(mkoId);
        dto.setQuestionId(questionId);
        dto.setStudentId(studentId);

        // Return the object
        return dto;
    }


    @Override
    public boolean update(AnswerDTO answer) throws Exception {
        //TODO: implement
        return false;
    }

    @Override
    public void delete(AnswerDTO answer) throws Exception {
        //TODO: implement

    }

    @Override
    public ArrayList<AnswerDTO> list() {

        // Declare the ArrayList
        ArrayList<AnswerDTO> alist = new ArrayList<AnswerDTO>();

        // Write the query
        String sql  = "select * from " + TABLE_NAME;

        // Run the query
        super.open();
        Cursor cursor = appDatabase.rawQuery(sql, null);

        // Move the cursor to the first result
        cursor.moveToFirst();

        // iterate over the cursor until it reaches the last row.
        // notice the ! which negates true to false.
        while(!cursor.isAfterLast()) {
            // populate plant from cursor.
            AnswerDTO dto = populateObjectFromCursor(cursor);

            // add to our collection.
            alist.add(dto);

            // move to the next result.
            cursor.moveToNext();
        }
        return alist;

    }

}
