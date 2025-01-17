package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // defining attributes of main activity class
    ListView cityList;
    EditText inputtext1;
    Button btnAdd, btnUpdate;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Integer indexVal;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); // set what u look like to be the layout file...

        EdgeToEdge.enable(this);

        // optional
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList = findViewById(R.id.city_list);
        btnAdd = findViewById(R.id.button_add);
        //btnUpdate = findViewById(R.id.button_up);
        inputtext1 = findViewById(R.id.editText);


        String[] cities = {"Edmonton", "Paris", "London"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Adding a new Item
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringval = inputtext1.getText().toString();
                dataList.add(stringval);
                cityAdapter.notifyDataSetChanged();
                inputtext1.setText("");
            }
        });

        // Selecting a new Item
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterView.getItemAtPosition(i).toString() + " has been selected";
                indexVal = i;
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });

//        // Updating the City Information
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String stringval = inputtext1.getText().toString();
//                dataList.set(indexVal, stringval);
//                cityAdapter.notifyDataSetChanged();
//            }
//        });

        // Deleting a city
        cityList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterView.getItemAtPosition(i).toString() + " has been deleted";
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                dataList.remove(i);
                cityAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}