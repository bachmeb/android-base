package us.proximal.spellwithme.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import us.proximal.spellwithme.R;

import static us.proximal.spellwithme.utility.ToastUtil.makeToast;

public class MyStudents extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_students);


        populateListView();

        registerClickCallback();




    }

    private void populateListView(){
        // Create list of items
        String[] items = {"Blue", "Purple", "Red"};

        // Build adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item_color,
                items
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

}
