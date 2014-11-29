package us.proximal.spellwithme.model.ada;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by b on 11/28/14.
 *
 */
public abstract class dbAdapter {

    public static String DATABASE_NAME = "spellwithme.db";
    public static final int DATABASE_VERSION = 2;

    public static DbHelper dbHelper;
    public static Context appContext;
    public static SQLiteDatabase appDatabase;

    boolean isConstructor = false;
    boolean isDb = false;

    public static final String SQL_QUESTIONS = "CREATE TABLE " + QuestionsAdapter.TABLE_NAME + " (" +
            QuestionsAdapter.PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuestionsAdapter.WORD_ID + " INTEGER, " +
            QuestionsAdapter.DESCRIPTION + " TEXT, " +
            QuestionsAdapter.TEXT + " TEXT, " +
            QuestionsAdapter.TYPE + " TEXT " +
            ");";

    public static final String SQL_WORDS = "CREATE TABLE "+ WordsAdapter.TABLE_NAME +" (" +
            WordsAdapter.PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            WordsAdapter.WORD + " TEXT, " +
            WordsAdapter.LETTERS + " TEXT, " +
            WordsAdapter.LENGTH + " TEXT, " +
            WordsAdapter.BEGINS_WITH + " TEXT, " +
            WordsAdapter.ENDS_WITH + " TEXT, " +
            WordsAdapter.CATEGORY + " TEXT, " +
            WordsAdapter.LANGUAGE + " TEXT, " +
            WordsAdapter.DOLCH + " TEXT " +
            ");";
    /*
    Constructor
     */
    public dbAdapter(Activity a){
        if(!isConstructor == true){
            appContext = a;
            isConstructor = true;
        }
    }

    public dbAdapter open() throws SQLException {
        if(!isDb == true){
            dbHelper = new DbHelper(appContext);
            isDb = true;
        }
        appDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        int i = 0;

        if(appDatabase.isOpen())
            dbHelper.close();
    }

    /*
    Nested SQLiteOpenHelper class
     */
    static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_QUESTIONS);
            db.execSQL(SQL_WORDS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
            db.execSQL("DROP TABLE IF EXISTS " + QuestionsAdapter.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + WordsAdapter.TABLE_NAME);
            onCreate(db);
        }

    }

}