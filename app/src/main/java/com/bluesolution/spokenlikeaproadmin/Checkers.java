package com.bluesolution.spokenlikeaproadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Checkers extends AppCompatActivity {



    String strEmail;
    Member member;
    private DatabaseReference databaseReference;
    ListView lvCheckers;
    ArrayList<String> myArrayList = new ArrayList<>();
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkers);
        Log.d("tag", "checkers");
        fab = findViewById(R.id.add_fab);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        member = new Member();

        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(Checkers.this, android.R.layout.simple_list_item_1, myArrayList);

        lvCheckers = findViewById(R.id.lvCheckers);
        lvCheckers.setAdapter(myArrayAdapter);
        databaseReference.child("checkers").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    String email = ds.getValue(String.class);
                    Log.d("tag", "email" + email);
                    myArrayList.add(email);
                    myArrayAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText email  = new EditText(view.getContext());
                final AlertDialog.Builder addChecker = new AlertDialog.Builder(view.getContext());
                addChecker.setTitle("Add a checker email!");
                addChecker.setMessage("Please enter the email of the new checker!");
                addChecker.setView(email);

                addChecker.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String Stremail = email.getText().toString();
                        member.setCheckerEmail(Stremail);
                        Log.d("tag", "et" + strEmail);
                        Log.d("tag", "member" + member.toString());
                        databaseReference.child("checkers").push().setValue(member).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"UPLOADED", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "ERROR: EXECPTION " + e, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                addChecker.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //close
                    }
                });
                addChecker.create().show();
            }
        });
    }
}