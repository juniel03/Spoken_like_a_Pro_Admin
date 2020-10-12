package com.bluesolution.spokenlikeaproadmin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class FirebaseAdapter extends FirebaseRecyclerAdapter<Member, FirebaseAdapter.FirebaseViewHolder> {

    public FirebaseAdapter(@NonNull FirebaseRecyclerOptions<Member> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final FirebaseViewHolder firebaseViewHolder, final int item, @NonNull final Member member) {
        firebaseViewHolder.tvEmail.setText(member.getCheckerEmail());

        firebaseViewHolder.ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tag", " edit ref: " + getRef(item).getKey());
                final DialogPlus dialogPlus = DialogPlus.newDialog(firebaseViewHolder.ibEdit.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialog_content))
                        .setExpanded(true)
                        .create();
                View myView = dialogPlus.getHolderView();
                final EditText etEmail = myView.findViewById(R.id.upmail);
                Button submit = myView.findViewById(R.id.btnUpdate);

                etEmail.setText(member.getCheckerEmail());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("checkerEmail",etEmail.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("checkers")
                                .child(getRef(item).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                        Log.d("tag", "SUCCESS");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                                Log.d("tag", "ERROR");
                            }
                        });
                    }
                });
            }
        });
        firebaseViewHolder.ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tag", "ref: " + getRef(item).getKey());
                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(firebaseViewHolder.tvEmail.getContext());
                deleteDialog.setTitle("Delete email");
                deleteDialog.setMessage("Delete this idiot??..");

                deleteDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("checkers")
                        .child(getRef(item).getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("tag", "SUCCESS");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("tag", "ERROR EXCEPTION IS: " + e);
                            }
                        });

                    }
                });
                deleteDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                deleteDialog.show();
            }
        });
    }

    @Override
    public void onChildChanged(@NonNull ChangeEventType type, @NonNull DataSnapshot snapshot, int newIndex, int oldIndex) {
        super.onChildChanged(type, snapshot, newIndex, oldIndex);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new FirebaseViewHolder(view);
    }

    class FirebaseViewHolder extends RecyclerView.ViewHolder{

        TextView tvEmail;
        ImageButton ibEdit, ibDelete;

        public FirebaseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            ibEdit = itemView.findViewById(R.id.ibEdit);
            ibDelete = itemView.findViewById(R.id.ibDelete);
        }
    }


}
