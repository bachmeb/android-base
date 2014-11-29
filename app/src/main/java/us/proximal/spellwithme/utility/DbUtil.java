package us.proximal.spellwithme.utility;

import android.app.Activity;
import android.content.Context;

import java.io.File;

import us.proximal.spellwithme.model.ada.ProximalDbAdapter;
import us.proximal.spellwithme.model.ada.ProximalDbCreate;

/**
 * Created by b on 11/28/14.
 */
public class DbUtil {

    public boolean exists(Context ctx){

        boolean value = false;

        File database = ctx.getApplicationContext().getDatabasePath(ProximalDbAdapter.DATABASE_NAME);

        if (database.exists()) {
            value = true;
        }

        return value;
    }
    public void create(Activity act){

        int i = 0;

        //dbAdapter is abstract and cannot be instantiated
        //dbAdapter adapter = new dbAdapter(act);
        ProximalDbCreate adapter = new ProximalDbCreate(act);

        //QuestionsDAO adapter = new QuestionsDAO(act);
        //Calling open() causes the database to be created on first run
        adapter.open();

        i = 1;

    }

    public boolean delete(Context ctx) {
        boolean result = false;

        try {

            ctx.deleteDatabase(ProximalDbAdapter.DATABASE_NAME);
            result = true;


        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;

    }



}
