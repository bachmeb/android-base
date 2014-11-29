package us.proximal.spellwithme.controller.prime;

import android.app.Activity;
import android.content.Context;

import java.io.File;

import us.proximal.spellwithme.model.ada.QuestionsAdapter;
import us.proximal.spellwithme.model.ada.dbAdapter;

/**
 * Created by b on 11/28/14.
 */
public class PrimeDatabase {

    public boolean exists(Context ctx){

        boolean value = false;

        File database = ctx.getApplicationContext().getDatabasePath(dbAdapter.DATABASE_NAME);

        if (database.exists()) {
            value = true;
        }

        return value;
    }
    public void create(Activity act){

        int i = 0;

        QuestionsAdapter adapter = new QuestionsAdapter(act);
        adapter.open();

        i = 1;

    }

    public boolean delete(Context ctx) {
        boolean result = false;

        try {

            ctx.deleteDatabase(dbAdapter.DATABASE_NAME);
            result = true;


        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;

    }



}
