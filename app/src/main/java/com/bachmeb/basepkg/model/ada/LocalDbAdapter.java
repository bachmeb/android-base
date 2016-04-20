package com.bachmeb.basepkg.model.ada;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bachmeb.basepkg.model.dao.PeopleDAO;

/**
 * Created by b on 11/28/14.
 *
 */
public abstract class LocalDbAdapter {

    public static String DATABASE_NAME = "local.db";
    public static final int DATABASE_VERSION = 6; // 2016 April 20 1335

    public static DbHelper dbHelper;
    public static Context appContext;
    public static SQLiteDatabase appDatabase;

    boolean isConstructor = false;
    boolean isDb = false;

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
    public LocalDbAdapter(Activity a){
        if(!isConstructor == true){
            appContext = a;
            isConstructor = true;
        }
    }

    public LocalDbAdapter open() throws SQLException {
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
            db.execSQL(SQL_PEOPLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
            db.execSQL("DROP TABLE IF EXISTS " + PeopleDAO.TABLE_NAME);
            onCreate(db);
        }

    }

}