package us.proximal.spellwithme.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import us.proximal.spellwithme.R;
import us.proximal.spellwithme.controller.def.IPeopleService;
import us.proximal.spellwithme.controller.def.IRelationshipsService;
import us.proximal.spellwithme.controller.svc.PeopleService;
import us.proximal.spellwithme.controller.svc.RelationshipsService;
import us.proximal.spellwithme.model.dto.PersonDTO;
import us.proximal.spellwithme.model.dto.RelationshipDTO;

public class MyStudentsAdd extends Activity {

    IPeopleService svcPeople;
    IRelationshipsService svcRelationships;

    EditText edtFirstName;
    EditText edtLastName;
    EditText edtBirthDate;

    Button btnSave;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_students_add);

        svcPeople = new PeopleService(this);
        svcRelationships = new RelationshipsService(this);


        edtFirstName = (EditText) findViewById(R.id.editMyStudentsAddFirstName);
        edtLastName = (EditText) findViewById(R.id.editMyStudentsAddLastName);
        edtBirthDate = (EditText) findViewById(R.id.editMyStudentsAddBirthDate);

        btnSave = (Button) findViewById(R.id.buttonMyStudentsAddSave);
        btnCancel = (Button) findViewById(R.id.buttonMyStudentsAddCancel);

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

    }

    private void saveChanges(View view) {
        long result =0 ;
        PersonDTO newStudent = new PersonDTO();

        newStudent.setFirstName(edtFirstName.getText().toString());
        newStudent.setLastName(edtLastName.getText().toString());
        newStudent.setBirthDate(edtBirthDate.getText().toString());

        try {
            result = svcPeople.addPerson(newStudent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RelationshipDTO newRelationship = new RelationshipDTO();

        int myId=0;
        try {
            myId = svcPeople.getMyPersonObject().getPersonId();

            newRelationship.setPrimaryId(myId);
            newRelationship.setSecondaryId((int)result);
            newRelationship.setType("MKO");

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            svcRelationships.makeRelationship(newRelationship);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Go back to the Me activity
        // declare the intent
        Intent intent = new Intent(view.getContext(), MyStudents.class);
        // act on the intent
        startActivity(intent);
    }

    private void cancelChanges(View view) {

        // Go back to the Me activity
        // declare the intent
        Intent intent = new Intent(view.getContext(), MyStudents.class);
        // act on the intent
        startActivity(intent);

    }

}
