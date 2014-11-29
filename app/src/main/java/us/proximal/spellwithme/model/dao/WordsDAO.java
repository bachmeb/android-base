package us.proximal.spellwithme.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import us.proximal.spellwithme.model.def.IWordsDAO;
import us.proximal.spellwithme.model.dto.WordDTO;

/**
 * Created by b on 11/24/14.
 */

public class WordsDAO extends SQLiteOpenHelper implements IWordsDAO {

    private static final String TABLE_NAME = "words";
    private static final String PRIMARY_KEY = "wordId";

    private static final String WORD = "word";
    private static final String LETTERS = "letters";
    private static final String LENGTH = "length";
    private static final String BEGINS_WITH = "beginsWith";
    private static final String ENDS_WITH = "endsWith";
    private static final String CATEGORY = "category";
    private static final String LANGUAGE = "language";
    private static final String DOLCH = "dolch";


//    public ThingDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    public WordsDAO(Context context) {

        super(context, "spellwithme.db", null, 1);
    }


    @Override
    public long create(WordDTO dto) throws Exception {

        // Create a ContentValues object with same number of elements as DTO fields (minus 2)
        ContentValues cv = new ContentValues(8);

        // Add values from DTO fields
        cv.put(WORD, dto.getWord() );
        //cv.put(LETTERS, dto.getLetters().toString() );
        cv.put(LENGTH, dto.getLength() );
        cv.put(BEGINS_WITH, dto.getBeginsWith() );
        cv.put(ENDS_WITH,  dto.getEndsWith() );
        cv.put(CATEGORY,  dto.getCategory() );
        cv.put(LANGUAGE,  dto.getLanguage() );
        cv.put(DOLCH,  dto.isDolch() );

        // put the values into database
        return getWritableDatabase().insert(TABLE_NAME, PRIMARY_KEY, cv);


    }

    @Override
    public WordDTO read(int key) throws Exception {

        // our flexible query.
        String fetchQuery  = "select * from " + TABLE_NAME + " where "+ PRIMARY_KEY +" = '" + key +"' ";

        // run the query.
        Cursor cursor = getReadableDatabase().rawQuery(fetchQuery, null);

        if (cursor.getCount() == 1) {

            // move cursor to the first record
            cursor.moveToFirst();

            WordDTO dto = populateObjectFromCursor(cursor);

            // return the object
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
    public WordDTO read(String text) throws Exception {
        return null;
    }

    @Override
    public void update(WordDTO word) throws Exception {
        //TODO implement update
    }

    @Override
    public void delete(WordDTO word) throws Exception {
        //TODO implement delete
    }

    @Override
    public ArrayList<WordDTO> list() throws Exception {

        // Make a new ArrayList
        ArrayList<WordDTO> words = new ArrayList<WordDTO>();

        // Write the SQL query
        String sql  = "select * from " + TABLE_NAME;

        // Execute the query
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        // Move the cursor to the first record
        cursor.moveToFirst();

        // Loop for as long as the cursor is not after the last record
        while(!cursor.isAfterLast()) {
            // populate the object from cursor
            WordDTO dto = populateObjectFromCursor(cursor);

            // add the DTO to the collection of DTOs
            words.add(dto);

            // Move the cursor to the next record
            cursor.moveToNext();
        }
        // Return the collection
        return words;
    }

    private WordDTO populateObjectFromCursor(Cursor cursor) throws Exception {

        // Create a new DTO
        WordDTO dto = new WordDTO();
        
        // Create variables for each cursor field
        int wid = cursor.getInt(cursor.getColumnIndex(PRIMARY_KEY) );
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
        dto.setDolch(  (dol==1) ? true : false );

        // Return the object
        return dto;
    }

    public void onUpdate(SQLiteDatabase db) {

        //Run a DROP IF EXISTS statement to drop the TABLE_NAME table.
        //Invoke onCreate.

        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //drop the table if it already exists
        this.onUpdate(db);

//        // define the schema
//        String schema = "CREATE TABLE "
//                + TABLE_NAME
//                + " ("
//                + PRIMARY_KEY
//                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + WORD
//                + " TEXT, "
//                + LETTERS
//                + " TEXT, "
//                + LENGTH
//                + " INTEGER, "
//                + BEGINS_WITH
//                + " TEXT, "
//                + ENDS_WITH
//                + " TEXT, "
//                + CATEGORY
//                + " TEXT, "
//                + LANGUAGE
//                + " TEXT, "
//                + DOLCH
//                + " TEXT );";
//
//        // create the table
//        db.execSQL(schema);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Empty method from SQLiteOpenHelper
        int i = 0;
    }
}
