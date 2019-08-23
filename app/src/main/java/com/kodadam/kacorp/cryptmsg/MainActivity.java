package com.kodadam.kacorp.cryptmsg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private Button btnGetMsg;
    private EditText edtUsername;
    private DatabaseReference mdbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetMsg = (Button) findViewById(R.id.btnGoMsg);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        db = FirebaseDatabase.getInstance();
        mdbRef = db.getReference();
        btnGetMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username.name = edtUsername.getText().toString();
                HashMap hashMap = new HashMap<String,String>();
                hashMap.put("Message","aTyH+l0BvFAx8Pidhe7cR7y0YpJbHxLyPmDBAR+7BmI");
                hashMap.put("Writer",Username.name);
                mdbRef.child("Room").setValue(hashMap);
                Intent myIntent = new Intent(MainActivity.this,Message.class);
                startActivity(myIntent);
            }
        });

    }
}
