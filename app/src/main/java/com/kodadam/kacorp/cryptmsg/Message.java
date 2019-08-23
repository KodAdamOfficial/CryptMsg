package com.kodadam.kacorp.cryptmsg;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;

public class Message extends AppCompatActivity {

    private FirebaseDatabase db;
    private Button btnSend;
    private EditText edtSend;
    private TextView txtMessage;
    private DatabaseReference mdbRef;
    String Msg;
    String key = "yunusturulcengiz";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        btnSend = (Button) findViewById(R.id.btnSend);
        edtSend = (EditText) findViewById(R.id.edtSend);
        txtMessage = (TextView) findViewById(R.id.txtMsg);
        db = FirebaseDatabase.getInstance();
        mdbRef = db.getReference();
        txtMessage.setText("********************TYCrypt*********************");
        mdbRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String decryptMsg = AES.decrypt(dataSnapshot.child("Room").child("Message").getValue().toString(),key);
                txtMessage.setText(txtMessage.getText().toString()+"\n"+dataSnapshot.child("Room").child("Writer").getValue().toString()+" : "+decryptMsg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Msg = edtSend.getText().toString();
                HashMap hashMap = new HashMap<String,String>();

                String cryptMsg = AES.encrypt(Msg,key);
                hashMap.put("Message",cryptMsg);
                hashMap.put("Writer",Username.name);
                mdbRef.child("Room").setValue(hashMap);
            }
        });

    }
}
