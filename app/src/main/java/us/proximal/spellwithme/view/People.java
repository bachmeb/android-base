package us.proximal.spellwithme.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import us.proximal.spellwithme.R;
import us.proximal.spellwithme.controller.def.IPeopleService;
import us.proximal.spellwithme.controller.svc.PeopleService;

public class People extends BaseActivity {


    private Button btnMe;
    private Button btnMyStudents;

    // Declare service
    private IPeopleService svcPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        // Instantiate service
        svcPeople = new PeopleService(this);

        // Initialize objects for UI components
        btnMe = (Button) findViewById(R.id.buttonPeopleMe);
        btnMyStudents = (Button) findViewById(R.id.buttonPeopleMyStudents);

        // Attach onClick listener to UI component
        btnMe.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View currentView) {

                                         openMeActivity(currentView);

                                     }
                                 }
        );

        // Attach onClick listener to UI component
        btnMyStudents.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View currentView) {

                                         openMyStudentsActivity(currentView);

                                     }
                                 }
        );

    }

    private void openMyStudentsActivity(View view) {

        // declare the intent
        Intent intent = new Intent(view.getContext(), MyStudents.class);
        // act on the intent
        startActivity(intent);
    }

    private void openMeActivity(View view) {

        // declare the intent
        Intent intent = new Intent(view.getContext(), Me.class);
        // act on the intent
        startActivity(intent);

    }


}
