package com.tobiasstrom.mappe2contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText tittel;
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tittel=(EditText)findViewById(R.id.tittel);
        show=(TextView)findViewById(R.id.showContact);

    }

    public void getAllContact(View view) {
        String out = "";
        Log.e(TAG, "getAllContact: " + Constants.CONTENT_URI );
        Cursor cur = getContentResolver().query(Constants.CONTENT_URI, null, null, null, null);
        Log.e(TAG, "getAllContact: " + cur );
        if (cur.moveToFirst()) {
            do {
                out += (cur.getString(1)) + " " +(cur.getString(2)) + "\r\n";
            }
            while (cur.moveToNext());
            cur.close();
            show.setText(out);
        }
    }

    public void getOneContact(View view) {
        int tallinn = 0;
        try{
            tallinn = Integer.parseInt(tittel.getText().toString());
        }catch (NumberFormatException e){
        }
        Cursor cur = getContentResolver().query(Constants.CONTENT_URI, new String[]{Constants.KEY_CONTACT_ID,Constants.KEY_CONTACT_FIRSTNAME, Constants.KEY_CONTACT_LASTNAME, Constants.KEY_CONTACT_EMAIL, Constants.KEY_CONTACT_PHONENUMBER},
                Constants.KEY_CONTACT_ID+ "=?",new String[]{tittel.getText().toString()}," DESC");
        if(cur != null){
            cur.moveToFirst();
            Contact contact = new Contact();
            contact.setFirstName(cur.getString(cur.getColumnIndex(Constants.KEY_CONTACT_FIRSTNAME)));
            contact.setLastName(cur.getString(cur.getColumnIndex(Constants.KEY_CONTACT_LASTNAME)));
            show.setText(contact.toString());
        }else {
        }


    }
}