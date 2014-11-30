package us.proximal.spellwithme.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import us.proximal.spellwithme.R;
import us.proximal.spellwithme.controller.def.IPeopleService;
import us.proximal.spellwithme.controller.svc.PeopleService;
import us.proximal.spellwithme.model.dto.PersonDTO;

import static us.proximal.spellwithme.utility.ToastUtil.makeToast;

public class MyStudents extends BaseActivity {

    IPeopleService svcPeople;
    Button btnAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_students);

        btnAddStudent = (Button) findViewById(R.id.buttonMyStudentsAdd);

        svcPeople = new PeopleService(this);

        populateListView();

        registerClickCallback();

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View currentView) {

                                              addStudent(currentView);

                                          }
                                      }
        );


    }

    private void populateListView(){
        // Create list of items
        //String[] items = {"Blue", "Purple", "Red"};

        ArrayList<PersonDTO> people;
        String[] students = {" "};

        int count;

        try {
            people = svcPeople.getListOfMyStudents();
            count = people.size();
            students = new String[count];
            int i = 0;
            for(PersonDTO person : people){
                students[i] = person.getFirstName() + " " + person.getLastName();
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Build adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item_color,
                students
        );

        // Configure the list view
        ListView list = (ListView) findViewById(R.id.listMyStudents);
        list.setAdapter(adapter);

    }

    private void registerClickCallback(){

        ListView list = (ListView) findViewById(R.id.listMyStudents);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                String message = "You clicked # " + i +
                        " which is string: " + textView.getText().toString();
                makeToast(MyStudents.this, message);
            }
        });
    }

    private void addStudent(View view){

        // declare the intent
        Intent intent = new Intent(view.getContext(), MyStudentsAdd.class);
        // act on the intent
        startActivity(intent);

    }

}
