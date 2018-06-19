package ru.android.kira.phonebook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contact> contacts = new ArrayList<Contact>();
    MyAdapter myAdapter;


//    void  fillData(){
//        for (int i = 1; i <= 20 ; i++) {
//            contacts.add(new Contact("Иван" +i,"Иванов" +i,"+7-999-888-77-66", R.drawable.los ));
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewContact.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

//        fillData();
        BDHelper bdHelper = new BDHelper(this);
        contacts = bdHelper.getAll();
        myAdapter = new MyAdapter(this, contacts);

        ListView list = (ListView)findViewById(R.id.contactList);
        list.setAdapter(myAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickContact(View view){
        Intent intent = new Intent(this, ContactScreen.class);
        intent.putExtra("name", ((TextView) view.findViewById(R.id.name)).getText().toString());
        intent.putExtra("family", ((TextView) view.findViewById(R.id.family)).getText().toString());
        intent.putExtra("phone", ((TextView) view.findViewById(R.id.phone)).getText().toString());
        ((ImageView) view.findViewById(R.id.photo)).buildDrawingCache();
        Bitmap im =  ((ImageView) view.findViewById(R.id.photo)).getDrawingCache();
        Bundle bundle = new Bundle();
        bundle.putParcelable("photo", im);
        intent.putExtras(bundle);


        startActivity(intent);
    }
}
