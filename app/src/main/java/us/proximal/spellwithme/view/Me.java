package us.proximal.spellwithme.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import us.proximal.spellwithme.R;
import us.proximal.spellwithme.controller.def.IPeopleService;
import us.proximal.spellwithme.controller.svc.PeopleService;
import us.proximal.spellwithme.model.dto.PersonDTO;

import static us.proximal.spellwithme.utility.ToastUtil.makeToast;

public class Me extends BaseActivity {

    private Button btnEdit;
    private TextView txtFirstName;
    private TextView txtLastName;
    private TextView txtBirthDate;

    private PersonDTO me;

    private IPeopleService svcPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        // Initialize service
        svcPeople = new PeopleService(this);

        // Initialize me
        try {
            me = svcPeople.getMyPersonObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize objects
        btnEdit = (Button) findViewById(R.id.buttonMeEdit);
        txtFirstName = (TextView) findViewById(R.id.textMeFirstName);
        txtLastName = (TextView) findViewById(R.id.textMeLastName);
        txtBirthDate = (TextView) findViewById(R.id.textMeBirthDate);

        // Display me
        displayMe();

        // Attach onClick listener
        btnEdit.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View currentView) {

                                              editMe(currentView);

                                          }
                                      }
        );
    }


    private void editMe(View view) {

        // declare the intent
        Intent intent = new Intent(view.getContext(), MeEdit.class);
        // act on the intent
        startActivity(intent);

    }

    private void displayMe(){

        try {
            txtFirstName.setText(me.getFirstName());
            txtLastName.setText(me.getLastName());
            txtBirthDate.setText(me.getBirthDate());
        } catch (Exception e) {
            e.printStackTrace();
            makeToast(this, "No person");
        }

    }
}
