package us.proximal.spellwithme.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import us.proximal.spellwithme.R;
import us.proximal.spellwithme.controller.def.IPeopleService;
import us.proximal.spellwithme.controller.svc.PeopleService;
import us.proximal.spellwithme.model.dto.PersonDTO;

public class MeEdit extends BaseActivity {

    //
    private Button btnSave;
    private Button btnCancel;
    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtBirthDate;

    //
    PersonDTO me;

    // Declare service
    private IPeopleService svcPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_edit);

        // Instantiate service
        svcPeople = new PeopleService(this);
        try {
            me = svcPeople.getMyPersonObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize objects
        btnCancel = (Button) findViewById(R.id.buttonMeEditCancel);
        btnSave = (Button) findViewById(R.id.buttonMeEditSave);
        edtFirstName = (EditText) findViewById(R.id.editMeEditFirstName);
        edtLastName = (EditText) findViewById(R.id.editMeEditLastName);
        edtBirthDate = (EditText) findViewById(R.id.editMeEditBirthDate);

        btnSave.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View currentView) {

                                           saveChanges(currentView);

                                       }
                                   }
        );

        btnCancel.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View currentView) {

                                         cancelChanges(currentView);

                                     }
                                 }
        );

        displayPerson();

    }

    private void displayPerson(){

        edtFirstName.setText(me.getFirstName());
        edtLastName.setText(me.getLastName());
        edtBirthDate.setText(me.getBirthDate());

    }

    private void cancelChanges(View view) {

        // Go back to the Me activity
        // declare the intent
        Intent intent = new Intent(view.getContext(), Me.class);
        // act on the intent
        startActivity(intent);

    }

    private void saveChanges(View view) {
        PersonDTO newMe = new PersonDTO();

        //this value doesn't change
        newMe.setPersonId(me.getPersonId());
        //these values can change
        newMe.setFirstName(edtFirstName.getText().toString());
        newMe.setLastName(edtLastName.getText().toString());
        newMe.setBirthDate(edtBirthDate.getText().toString());

        try {
            svcPeople.updatePerson(newMe);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Go back to the Me activity
        // declare the intent
        Intent intent = new Intent(view.getContext(), Me.class);
        // act on the intent
        startActivity(intent);

    }

}
