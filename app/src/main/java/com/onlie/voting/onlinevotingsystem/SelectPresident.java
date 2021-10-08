package com.onlie.voting.onlinevotingsystem;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;



public class SelectPresident extends AppCompatActivity {


    private RadioGroup radioGroup2;
    private RadioButton rb4;
    private RadioButton rb5;
    private RadioButton rb6;

    private RadioGroup radioGroup1;
    private RadioButton rb7;
    private RadioButton rb8;
    private RadioButton rb9;

    private Button btn;
    String Phone;
    private DatabaseReference mref;
    private ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_your_vote_president);


        this.radioGroup2 = this.findViewById(R.id.radioGroup2);
        this.rb4 = this.findViewById(R.id.rb4);
        this.rb5 = this.findViewById(R.id.rb5);
        this.rb6 = this.findViewById(R.id.rb6);

        this.radioGroup1 = this.findViewById(R.id.radioGroup1);
        this.rb7 = this.findViewById(R.id.rb7);
        this.rb8 = this.findViewById(R.id.rb8);
        this.rb9 = this.findViewById(R.id.rb9);




        final Intent i=getIntent();
        Phone=i.getStringExtra("phone");
        mref= FirebaseDatabase.getInstance().getReference();
        LoadingBar=new ProgressDialog(this);


        this.radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                doOnradioGroup1Changed(group, checkedId);

                final AlertDialog.Builder builder=new AlertDialog.Builder(SelectPresident.this);
                builder.setTitle("Confirm Your Party");
                builder.setMessage("Do you want to give your vote to those candidate");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LoadingBar.setTitle("Voting Inprogress");
                        LoadingBar.setMessage("Please wait..");
                        LoadingBar.setCanceledOnTouchOutside(false);
                        LoadingBar.show();

                       mref.child("President").child(Phone).child("Voted").setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Intent i=new Intent(SelectPresident.this,FinalActivity.class);
                                i.putExtra("phone",Phone);


                                startActivity(i);

                               LoadingBar.dismiss();
                                Toast.makeText(SelectPresident.this, "Your Vote is Submitted to our database..", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                builder.show();


            }
        });


    }

    private void doOnradioGroup1Changed(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();


        this.radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                doOnradioGroup1Changed(group, checkedId);
                final AlertDialog.Builder builder=new AlertDialog.Builder(SelectPresident.this);
                builder.setTitle("Confirm Your Party");
                builder.setMessage("Do you want to give your vote to those candidate");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LoadingBar.setTitle("Voting Inprogress");
                        LoadingBar.setMessage("Please wait..");
                        LoadingBar.setCanceledOnTouchOutside(false);
                        LoadingBar.show();

                        mref.child("Vice President").child(Phone).child("Voted").setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Intent i=new Intent(SelectPresident.this,FinalActivity.class);
                                i.putExtra("phone",Phone);


                                startActivity(i);
                                LoadingBar.dismiss();
                                Toast.makeText(SelectPresident.this, "Your Vote is Submitted to our database..", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                builder.show();



            }
        });
    }

    private void doOnradioGroup2Changed(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();

        if (checkedRadioId == R.id.rb4) {
            Toast.makeText(this, "You choose this candidate", Toast.LENGTH_SHORT).show();
        } else if (checkedRadioId == R.id.rb5) {
            Toast.makeText(this, "You choose this candidate", Toast.LENGTH_SHORT).show();
        } else if (checkedRadioId == R.id.rb6) {
            Toast.makeText(this, "You choose this candidate", Toast.LENGTH_SHORT).show();
        }

    }


    private void doOnradioGroupChanged(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();

        if (checkedRadioId == R.id.rb7) {
            Toast.makeText(this, "You choose this candidate", Toast.LENGTH_SHORT).show();
        } else if (checkedRadioId == R.id.rb8) {
            Toast.makeText(this, "You choose this candidate", Toast.LENGTH_SHORT).show();
        } else if (checkedRadioId == R.id.rb9) {
            Toast.makeText(this, "You choose this candidate", Toast.LENGTH_SHORT).show();
        }

    }

    private void doSave() {
        int radioGroup1 = this.radioGroup1.getCheckedRadioButtonId();
        int radioGroup2 = this.radioGroup2.getCheckedRadioButtonId();

        RadioButton rb7 = this.findViewById(radioGroup1);
        RadioButton rb8 = this.findViewById(radioGroup1);
        RadioButton rb9 = this.findViewById(radioGroup1);

        RadioButton rb4 = this.findViewById(radioGroup2);
        RadioButton rb5 = this.findViewById(radioGroup2);
        RadioButton rb6 = this.findViewById(radioGroup2);

    }

    @Override
    public void onBackPressed() {

    }
}







