package com.codepath;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //fyi: declare fields here


    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    //RecyclerView rvItems;
    //RecyclerView.Adapter mAdapter;
    //RecyclerView.LayoutManager layoutManager;
    ListView lvItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //rvItems = (RecyclerView) findViewById(R.id.rvItems);
        //layoutManager = new LinearLayoutManager(this);
        //rvItems.setLayoutManager(layoutManager);

        //mAdapter = new MyAdapter(myDataset);
        //rvItems.setAdapter(mAdapter);

        readItems();
        //items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        //rvItems.setAdapter(itemsAdapter);
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);

        //items.add("First item");
        //items.add("Second item");

        setupListViewListener();
    }
    //fyi: add methods here

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        writeItems();
        Toast.makeText(getApplicationContext(), "Item added to list", Toast.LENGTH_SHORT).show();

    }

    private void setupListViewListener() {
        Log.i("MainActivity", "Setting up listener on list view");
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override

            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                items.remove(i);
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                Log.i("MainActivity", "Removed item " + i);
                return true;
            }
        });
    }

    private File getDataFile() {
        return new File(getFilesDir(), "todo.txt");
    }
    private void readItems() {
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            //e.printStackTrace();
            Log.e("MainActivity", "Error reading file", e);
            items = new ArrayList<>();
        }
    }
    private void writeItems() {
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch (IOException e) {
            //e.printStackTrace();
            Log.e("MainActivity", "Error writing file", e);
        }
    }
}

//@string/my_string refs string in res/values/string.xml
//@drawable/cool_image refs img in res/drawable
//eg: System.out.println(R.string.my_string);

//methods: onCreate, onPause, onResume