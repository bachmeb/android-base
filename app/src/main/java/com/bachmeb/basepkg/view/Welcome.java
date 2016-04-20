package com.bachmeb.basepkg.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bachmeb.basepkg.R;


public class Welcome extends BaseActivity {

    Button btnSecondView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //call the super class constructor
        super.onCreate(savedInstanceState);
        //grab the resource xml
        setContentView(R.layout.activity_welcome);

        //instantiate the buttons in the onCreate method
        btnSecondView = (Button) findViewById(R.id.buttonWelcomeSecondView);


        //attach onClick listener to the button object
        btnSecondView.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View currentView) {

                                           openSecondView(currentView);

                                       }
                                   }
        );

    }

    private void openSecondView(View view){

        // declare the intent
        Intent intent = new Intent(view.getContext(), SecondView.class);
        // act on the intent
        startActivity(intent);
    }


}
