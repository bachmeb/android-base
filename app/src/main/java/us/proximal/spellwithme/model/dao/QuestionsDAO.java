package us.proximal.spellwithme.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import us.proximal.spellwithme.model.def.IQuestionsDAO;
import us.proximal.spellwithme.model.dto.QuestionDTO;

/**
 * Created by b on 11/26/14.
 */

public class QuestionsDAO extends SQLiteOpenHelper implements IQuestionsDAO {

    private static final String TABLE_NAME = "questions";
    private static final String PRIMARY_KEY = "questionId";
    private static final String TEXT = "text";
    private static final String DESCRIPTION = "description";

//    public ThingDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    public QuestionsDAO(Context context) {

        super(context, "spellwithme.db", null, 1);
    }

    @Override
    public boolean create(QuestionDTO q) throws Exception {

        // create an insert statement to insert this thing into the database
        // content values is like a hashamp.
        ContentValues cv = new ContentValues(6);
        cv.put(TEXT, q.getText());
        cv.put(DESCRIPTION, q.getDescription());

        // put into the database
        getWritableDatabase().insert(TABLE_NAME, PRIMARY_KEY, cv);

        return true;
    }

    @Override
    public QuestionDTO read(int accountId) throws Exception {

        // our flexible query.
        String fetchQuery  = "select * from " + TABLE_NAME + " where "+ PRIMARY_KEY +" = '" + accountId +"' ";

        // run the query.
        Cursor cursor = getReadableDatabase().rawQuery(fetchQuery, null);


        if (cursor.getCount() == 1) {
            // find out who our user is.

            // move our cursor to the first position.
            cursor.moveToFirst();

            QuestionDTO q = populateThingFromCursor(cursor);

            // return the populated user.
            return q;
        } else if (cursor.getCount() > 1){
            throw new Exception("Too many results returned.  Expected 1, got " + cursor.getCount());
        } else {
            // if we got here, we have 0 results.  Return null.
            return null;
        }
    }

    private QuestionDTO populateThingFromCursor(Cursor cursor) {

        // create a new user object to hold our data.
        QuestionDTO q = new QuestionDTO();

        // populate this user object from our database data.
        String text = cursor.getString(cursor.getColumnIndex(TEXT));
        String description = cursor.getString(cursor.getColumnIndex(DESCRIPTION));

        // populate the thing.
        q.setText(text);
        q.setDescription(description);

        return q;
    }

    @Override
    public QuestionDTO read(String qText) throws Exception {

        // our flexible query.
        String fetchQuery  = "select * from " + TABLE_NAME + " where "+ TEXT +" = '" + qText +"' ";

        // run the query.
        Cursor cursor = getReadableDatabase().rawQuery(fetchQuery, null);


        if (cursor.getCount() == 1) {
            // find out who our user is.

            // move our cursor to the first position.
            cursor.moveToFirst();

            QuestionDTO q = populateThingFromCursor(cursor);

            // return the populated user.
            return q;
        } else if (cursor.getCount() > 1){
            throw new Exception("Too many results returned.  Expected 1, got " + cursor.getCount());
        } else {
            // if we got here, we have 0 results.  Return null.
            return null;
        }
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
    public ArrayList<QuestionDTO> list() throws Exception {

        // Declare the variable that will hold our results.
        ArrayList<QuestionDTO> qs = new ArrayList<QuestionDTO>();

        // our flexible query.
        String sql  = "select * from " + TABLE_NAME;

        // run the query.
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        // move the cursor to the first result.
        cursor.moveToFirst();

        // iterate over the cursor until it reaches the last row.
        // notice the ! which negates true to false.
        while(!cursor.isAfterLast()) {
            // populate plant from cursor.
            QuestionDTO q = populateThingFromCursor(cursor);

            // add to our collection.
            qs.add(q);

            // move to the next result.
            cursor.moveToNext();
        }
        return qs;
    }

    public void onUpdate(SQLiteDatabase db) {

        //Run a DROP IF EXISTS statement to drop the ACCOUNTS table.
        //Invoke onCreate.

        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //drop the USERS table if it exists
        this.onUpdate(db);

        // define the schema.
        String userSchema = "CREATE TABLE "
                + TABLE_NAME
                + " ("
                + PRIMARY_KEY
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TEXT
                + " TEXT, "
                + DESCRIPTION
                + " TEXT );";

        // create our table.
        db.execSQL(userSchema);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Empty method from SQLiteOpenHelper
    }
}
