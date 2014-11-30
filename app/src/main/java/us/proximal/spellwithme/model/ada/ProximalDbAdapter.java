package us.proximal.spellwithme.model.ada;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import us.proximal.spellwithme.model.dao.AnswersDAO;
import us.proximal.spellwithme.model.dao.PeopleDAO;
import us.proximal.spellwithme.model.dao.QuestionsDAO;
import us.proximal.spellwithme.model.dao.WordsDAO;

/**
 * Created by b on 11/28/14.
 *
 */
public abstract class ProximalDbAdapter {

    public static String DATABASE_NAME = "spellwithme.db";
    public static final int DATABASE_VERSION = 3;

    public static DbHelper dbHelper;
    public static Context appContext;
    public static SQLiteDatabase appDatabase;

    boolean isConstructor = false;
    boolean isDb = false;

    public static final String SQL_QUESTIONS = "CREATE TABLE " + QuestionsDAO.TABLE_NAME + " (" +
            QuestionsDAO.PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuestionsDAO.WORD_ID + " INTEGER, " +
            QuestionsDAO.WORD + " TEXT, " +
            QuestionsDAO.DESCRIPTION + " TEXT, " +
            QuestionsDAO.TEXT + " TEXT, " +
            QuestionsDAO.TYPE + " TEXT " +
            ");";

    public static final String SQL_WORDS = "CREATE TABLE " + WordsDAO.TABLE_NAME + " (" +
            WordsDAO.PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            WordsDAO.WORD + " TEXT, " +
            WordsDAO.LETTERS + " TEXT, " +
            WordsDAO.LENGTH + " TEXT, " +
            WordsDAO.BEGINS_WITH + " TEXT, " +
            WordsDAO.ENDS_WITH + " TEXT, " +
            WordsDAO.CATEGORY + " TEXT, " +
            WordsDAO.LANGUAGE + " TEXT, " +
            WordsDAO.DOLCH + " TEXT " +
            ");";

    public static final String SQL_ANSWERS = "CREATE TABLE "
            + AnswersDAO.TABLE_NAME
            + " ("
            + AnswersDAO.PRIMARY_KEY
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AnswersDAO.QUESTION_ID
            + " INTEGER, "
            + AnswersDAO.STUDENT_ID
            + " INTEGER, "
            + AnswersDAO.MKO_ID
            + " INTEGER, "
            + AnswersDAO.DATE
            + " TEXT, "
            + AnswersDAO.GRADE
            + " TEXT );";

    public static final String SQL_PEOPLE = "CREATE TABLE "
            + PeopleDAO.TABLE_NAME
            + " ("
            + PeopleDAO.PRIMARY_KEY
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PeopleDAO.FIRST_NAME
            + " TEXT, "
            + PeopleDAO.LAST_NAME
            + " TEXT, "
            + PeopleDAO.BIRTH_DATE
            + " TEXT );";

    /*
    Constructor
     */
    public ProximalDbAdapter(Activity a){
        if(!isConstructor == true){
            appContext = a;
            isConstructor = true;
        }
    }

    public ProximalDbAdapter open() throws SQLException {
        if(!isDb == true){
            dbHelper = new DbHelper(appContext);
            isDb = true;
        }
        appDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){

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
            db.execSQL(SQL_ANSWERS);
            db.execSQL(SQL_PEOPLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
            db.execSQL("DROP TABLE IF EXISTS " + QuestionsDAO.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + WordsDAO.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + AnswersDAO.TABLE_NAME);
            onCreate(db);
        }

    }

}