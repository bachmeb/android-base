package us.proximal.spellwithme.model.ada;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import us.proximal.spellwithme.model.def.IWordsDAO;
import us.proximal.spellwithme.model.dto.WordDTO;

/**
 * Created by b on 11/28/14.
 */
public class WordsAdapter extends dbAdapter implements IWordsDAO {

    //Define the table properties
    public static final String TABLE_NAME = "words";
    public static final String PRIMARY_KEY = "wordId";

    public static final String WORD = "word";
    public static final String LETTERS = "letters";
    public static final String LENGTH = "length";
    public static final String BEGINS_WITH = "beginsWith";
    public static final String ENDS_WITH = "endsWith";
    public static final String CATEGORY = "category";
    public static final String LANGUAGE = "language";
    public static final String DOLCH = "dolch";

    /*
    Constructor
     */
    public WordsAdapter(Activity a) {
        super(a);
    }

    /*

     */
    @Override
    public long create(WordDTO dto) {
        // Create CV object
        ContentValues cv = new ContentValues();
        // Add values from DTO fields
        cv.put(WORD, dto.getWord());
        //cv.put(LETTERS, dto.getLetters().toString() );
        cv.put(LENGTH, dto.getLength());
        cv.put(BEGINS_WITH, dto.getBeginsWith());
        cv.put(ENDS_WITH, dto.getEndsWith());
        cv.put(CATEGORY, dto.getCategory());
        cv.put(LANGUAGE, dto.getLanguage());
        cv.put(DOLCH, dto.isDolch());

        // put the values into database
        //getWritableDatabase().insert(TABLE_NAME, PRIMARY_KEY, cv);


        //return appDatabase.insert(DATABASE_TABLE, null,cv);
        super.open();

        return appDatabase.insert(TABLE_NAME, PRIMARY_KEY, cv);

    }

    /*

     */
    @Override
    public ArrayList<WordDTO> list() {

        // Make a new ArrayList
        ArrayList<WordDTO> words = new ArrayList<WordDTO>();

        // Write the SQL query
        String sql = "select * from " + TABLE_NAME;

        // Execute the query
        super.open();
        Cursor cursor = appDatabase.rawQuery(sql, null);

        // Move the cursor to the first record
        cursor.moveToFirst();

        // Loop for as long as the cursor is not after the last record
        while (!cursor.isAfterLast()) {
            // populate the object from cursor
            WordDTO dto = null;
            try {
                dto = populateObjectFromCursor(cursor);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // add the DTO to the collection of DTOs
            words.add(dto);

            // Move the cursor to the next record
            cursor.moveToNext();
        }
        // Return the collection
        return words;

    }

    /*

     */
    @Override
    public WordDTO read(int key) throws Exception {

        // Write the query
        String sql = "select * from " + TABLE_NAME + " where " + PRIMARY_KEY + " = '" + key + "' ";

        // Run the query
        //Cursor cursor = getReadableDatabase().rawQuery(fetchQuery, null);
        Cursor cursor = appDatabase.rawQuery(sql, null);

        if (cursor.getCount() == 1) {

            // move cursor to the first record
            cursor.moveToFirst();

            WordDTO dto = null;
            try {
                dto = populateObjectFromCursor(cursor);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // return the object
            return dto;

        } else if (cursor.getCount() > 1) {
            // More than one record
            throw new Exception("Too many results returned.  Expected 1, got " + cursor.getCount());
        } else {
            // Less than one record
            return null;
        }
    }

    @Override
    public WordDTO read(String text) throws Exception {

        // Write the query
        String fetchQuery  = "select * from " + TABLE_NAME + " where "+ WORD +" = '" + text +"' ";

        // Run the query
        super.open();
        Cursor cursor = appDatabase.rawQuery(fetchQuery, null);


        if (cursor.getCount() == 1) {
            // Move cursor to first record
            cursor.moveToFirst();

            // Make the object
            WordDTO dto = populateObjectFromCursor(cursor);

            // Return the object
            return dto;

        } else if (cursor.getCount() > 1){
            // More than one record
            throw new Exception("Too many results returned.  Expected 1, got " + cursor.getCount());
        } else {
            // Less than one record
            return null;
        }
    }

    @Override
    public void update(WordDTO word) throws Exception {
        //TODO implement delete
    }

    @Override
    public void delete(WordDTO word) throws Exception {
        //TODO implement delete
    }

    private WordDTO populateObjectFromCursor(Cursor cursor) throws Exception {

        // Create a new DTO
        WordDTO dto = new WordDTO();

        // Create variables for each cursor field
        int wid = cursor.getInt(cursor.getColumnIndex(PRIMARY_KEY));
        String wor = cursor.getString(cursor.getColumnIndex(WORD));
        //String let = cursor.getString(cursor.getColumnIndex(LETTERS));
        int len = cursor.getInt(cursor.getColumnIndex(LENGTH));
        String beg = cursor.getString(cursor.getColumnIndex(BEGINS_WITH));
        String end = cursor.getString(cursor.getColumnIndex(ENDS_WITH));
        String cat = cursor.getString(cursor.getColumnIndex(CATEGORY));
        String lan = cursor.getString(cursor.getColumnIndex(LANGUAGE));
        int dol = cursor.getInt(cursor.getColumnIndex(DOLCH));

        // Populate the DTO with the variables
        dto.setWordId(wid);
        dto.setWord(wor);
        //dto.setLetters(let.toString());
        dto.setLength(len);
        dto.setBeginsWith(beg);
        dto.setEndsWith(end);
        dto.setCategory(cat);
        dto.setLanguage(lan);
        dto.setDolch((dol == 1) ? true : false);

        // Return the object
        return dto;
    }

}