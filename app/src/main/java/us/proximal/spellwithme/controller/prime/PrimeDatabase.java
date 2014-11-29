package us.proximal.spellwithme.controller.prime;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

/**
 * Created by b on 11/28/14.
 */
public class PrimeDatabase {

    private static final String DATABASE_NAME = "spellwithme.db";

    private static final int DATABASE_VERSION = 2;

    private static final String CREATE_WORDS_TABLE =

    "CREATE TABLE "
            + " words "
    + " ("
            + " wordId "
    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " word "
    + " TEXT, "
            + " letters "
    + " TEXT, "
            + " length "
    + " INTEGER, "
            + " beginsWith "
    + " TEXT, "
            + " endsWith "
    + " TEXT, "
            + " category "
    + " TEXT, "
            + " language "
    + " TEXT, "
            + " dolch "
    + " TEXT );"
            ;

    private static final String CREATE_QUESTIONS_TABLE = "SELECT * from words";
    private static final String CREATE_ANSWERS_TABLE = " SELECT * FROM words";


    public PrimeDatabase(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WORDS_TABLE);
        db.execSQL(CREATE_QUESTIONS_TABLE);
        db.execSQL(CREATE_ANSWERS_TABLE);
    }

    public boolean exists(Context ctx){

        boolean value = false;

        File database = ctx.getApplicationContext().getDatabasePath(DATABASE_NAME);

        if (database.exists()) {
            value = true;
        }

        return value;
    }
    public void create(Context ctx){

        int i = 0;

        getWritableDatabase();
        getReadableDatabase();

        i = 1;

    }

    public boolean delete(Context ctx) {
        boolean result = false;

        try {

            ctx.deleteDatabase(DATABASE_NAME);
            result = true;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        int j = 0;

    }

}
