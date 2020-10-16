package com.tobiasstrom.mappe2contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText tittel;
    TextView show;
    private Uri newUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tittel=(EditText)findViewById(R.id.tittel);
        show=(TextView)findViewById(R.id.showContact);

    }

    public void getAllContact(View view) {
        String out = "";
        Cursor cur = getContentResolver().query(Constants.CONTENT_URI, null, null, null, Constants.KEY_CONTACT_LASTNAME + " DESC");
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
        Uri CONTENT_URI_NEW = Uri.parse("content://" + Constants.PROVIDER + "/contact/"+ tittel.getText().toString());

        Log.e(TAG, "getOneContact: kommer den hit" );
        Cursor cur = getContentResolver().query(
                CONTENT_URI_NEW,
                null,
                null,
                null,null);
        if(tittel.getText().toString().isEmpty()){
            show.setText("Du må skrive inn ett tall");
        } else {
            if(cur.moveToFirst()){
                show.setText((cur.getString(1)) + " " +(cur.getString(2)));
            }else {
                show.setText("Det er ikke så mange kontakter");
            }
        }
        cur.close();



    }

}