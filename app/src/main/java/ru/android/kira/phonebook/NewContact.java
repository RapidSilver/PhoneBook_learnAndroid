package ru.android.kira.phonebook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class NewContact extends AppCompatActivity {

    private TextInputEditText nameInput;
    private TextInputEditText familyInput;
    private TextInputEditText phoneInput;
    private ImageView imageView;
    private Uri selectedImage = null;
//    Button save;
//    private Contact contact;
    private BDHelper bdHelper;
    static final int GALLERY_REQUEST = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.galaryFab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);

            }
        });

        nameInput = (TextInputEditText) findViewById(R.id.nameInput);
        familyInput = (TextInputEditText) findViewById(R.id.familyInput);
        phoneInput = (TextInputEditText) findViewById(R.id.phoneInput);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;
         imageView = (ImageView) findViewById(R.id.photoInput);


        switch(requestCode) {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    selectedImage = imageReturnedIntent.getData();
                    imageView.setImageURI(null);
                    imageView.setImageURI(selectedImage);

                }
        }
    }


    public void insertContact(View view){
        String name = nameInput.getText().toString().trim();
        String family = familyInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();
        String photo = selectedImage.toString();


        bdHelper = new BDHelper(this);
        bdHelper.addContact(new Contact(name, family, phone, photo));
        Intent intent = new Intent(NewContact.this, MainActivity.class);
        startActivity(intent);
    }
}
