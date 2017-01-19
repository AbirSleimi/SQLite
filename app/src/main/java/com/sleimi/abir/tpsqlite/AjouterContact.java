package com.sleimi.abir.tpsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AjouterContact extends AppCompatActivity {
//
    private EditText editText;
    private EditText editText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_contact);
        editText = (EditText) findViewById(R.id.editText);
        editText3= (EditText) findViewById(R.id.editText3);
        Button btn = (Button) findViewById(R.id.button);
        final DatabaseHandler db = new DatabaseHandler(this);
        btn.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
                String str2 = editText3.getText().toString();
                if(editText.length()!=0 && editText3.length()!=0){

                db.addContact(new Contact(str, str2));
                //Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                Toast.makeText(AjouterContact.this, "Contact Ajouter!"+str+str2, Toast.LENGTH_LONG).show();
                Intent i = new Intent(AjouterContact.this, MainActivity.class);
                startActivity(i);}
				else
				{
					Toast.makeText(AjouterContact.this, "Vous devez entrer les données!", Toast.LENGTH_LONG).show();
                }
                /*
                // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));
                 */
            }
        });
    }
}
