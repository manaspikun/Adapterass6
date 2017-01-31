package com.techpalle.adapterass6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2;
    Button b1,b2;
    Spinner sp1,sp2;
    HashMap<String,ArrayList<String>>mymap;
    String stateSelected;
    ArrayList<String>alstate,valuecitylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1= (EditText) findViewById(R.id.edittext1);
        ed2= (EditText) findViewById(R.id.edittext2);
        b1= (Button) findViewById(R.id.button1);
        b2= (Button) findViewById(R.id.button2);
        sp1= (Spinner) findViewById(R.id.spinner1);
        sp2= (Spinner) findViewById(R.id.spinner2);
        mymap=new HashMap<String,ArrayList<String>>();
        alstate=new ArrayList<String>();
        alstate.add(0,"select state");
        valuecitylist=new ArrayList<String>();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mymap.containsKey(ed1.getText().toString())) {
                    valuecitylist = new ArrayList<String>();
                    alstate.add(ed1.getText().toString());
                    mymap.put(ed1.getText().toString(), valuecitylist);
                    ed1.setText("");
                    ed1.requestFocus();
                    if (!alstate.isEmpty()) {
                        sp1.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, alstate));
                    }

                }else {
                    Toast.makeText(MainActivity.this,"State is already entered",Toast.LENGTH_LONG).show();
                }
            }
        });
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ed2.requestFocus();
                if(position==0){
                    return;
                }else {
                    ArrayList<String>alcity=new ArrayList<String>();

                    stateSelected=parent.getSelectedItem().toString();
                    alcity=mymap.get(stateSelected);
                    mymap.put(stateSelected,alcity);
                    sp2.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,mymap.get(stateSelected)));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mymap.containsKey(stateSelected)){
                    ArrayList<String>alcity=new ArrayList<String>();
                    alcity=mymap.get(stateSelected);
                    alcity.add(ed2.getText().toString());
                    mymap.put(stateSelected,alcity);
                    alcity=mymap.get(stateSelected);
                    sp2.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,alcity));
                    ed2.setText("");
                    ed2.requestFocus();

                }else {
                    Toast.makeText(MainActivity.this,"please enter state",Toast.LENGTH_LONG).show();
                    ed1.requestFocus();
                }
            }
        });

    }
}
