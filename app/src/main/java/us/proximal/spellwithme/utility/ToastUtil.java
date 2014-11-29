package us.proximal.spellwithme.utility;

import android.content.Context;
import android.widget.Toast;

import us.proximal.spellwithme.view.BaseActivity;

/**
 * Created by b on 11/29/14.
 */
public class ToastUtil extends BaseActivity {

    /*
    General method for making toast
    */
    public static void makeToast(Context ctx, String toast){

        Toast.makeText(ctx, toast, Toast.LENGTH_LONG).show();


    }
}
