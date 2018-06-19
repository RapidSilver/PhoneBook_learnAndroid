package ru.android.kira.phonebook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactScreen extends AppCompatActivity {

    TextView textViewName;
    TextView textViewFamily;
    TextView textViewPhone;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_screen);

        textViewName = (TextView) findViewById(R.id.name);
        textViewFamily = (TextView) findViewById(R.id.family);
        textViewPhone = (TextView) findViewById(R.id.phone);
        imageView = (ImageView) findViewById(R.id.photo);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String family = intent.getStringExtra("family");
        String phone = intent.getStringExtra("phone");
        Bundle bundle = getIntent().getExtras();
        Bitmap bitmap = (Bitmap)bundle.getParcelable("photo");

        textViewName.setText(name);
        textViewFamily.setText(family);
        textViewPhone.setText(phone);
        imageView.setImageBitmap(bitmap);
    }
}
