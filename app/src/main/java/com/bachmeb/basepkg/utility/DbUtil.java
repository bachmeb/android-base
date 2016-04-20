package com.bachmeb.basepkg.utility;

import android.app.Activity;
import android.content.Context;

import java.io.File;

import com.bachmeb.basepkg.model.ada.LocalDbAdapter;
import com.bachmeb.basepkg.model.ada.LocalDbCreate;

/**
 * Created by b on 11/28/14.
 */
public class DbUtil {

    public boolean exists(Context ctx){

        boolean value = false;

        File database = ctx.getApplicationContext().getDatabasePath(LocalDbAdapter.DATABASE_NAME);

        if (database.exists()) {
            value = true;
        }

        return value;
    }
    public void create(Activity act){

        int i = 0;

        //dbAdapter is abstract and cannot be instantiated
        //dbAdapter adapter = new dbAdapter(act);
        LocalDbCreate adapter = new LocalDbCreate(act);

        //QuestionsDAO adapter = new QuestionsDAO(act);
        //Calling open() causes the database to be created on first run
        adapter.open();

        i = 1;

    }

    public boolean delete(Context ctx) {
        boolean result = false;

        try {

            ctx.deleteDatabase(LocalDbAdapter.DATABASE_NAME);
            result = true;


        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;

    }



}
