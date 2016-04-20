package com.bachmeb.basepkg.utility;

import android.content.Context;
import android.widget.Toast;

import com.bachmeb.basepkg.view.BaseActivity;

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
