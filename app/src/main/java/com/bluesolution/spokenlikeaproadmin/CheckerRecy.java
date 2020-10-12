package com.bluesolution.spokenlikeaproadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class CheckerRecy extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseAdapter firebaseAdapter;
    FloatingActionButton fab;
    Member member;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checker_recy);
        hideSystemUI.fullscreen(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        member = new Member();
        fab = findViewById(R.id.add_fab);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(CheckerRecy.this)
                        .setContentHolder(new ViewHolder(R.layout.add_checkers))
                        .setExpanded(true)
                        .create();
                View myView = dialogPlus.getHolderView();
                final EditText addEmail = myView.findViewById(R.id.etMail);
                final Button add = myView.findViewById(R.id.btnAdd);


                dialogPlus.show();

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String Stremail = addEmail.getText().toString();
                        member.setCheckerEmail(Stremail);
                        Log.d("tag", "member" + member.toString());
                        databaseReference.child("checkers").push().setValue(member).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"UPLOADED", Toast.LENGTH_SHORT).show();
                                    dialogPlus.dismiss();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "ERROR: EXECPTION " + e, Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });
                    }
                });
            }
        });

        FirebaseRecyclerOptions<Member> options = new FirebaseRecyclerOptions.Builder<Member>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("checkers"), Member.class)
                 .build();

        firebaseAdapter = new FirebaseAdapter(options);
        recyclerView.setAdapter(firebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAdapter.stopListening();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI.fullscreen(this);
        }
    }
}