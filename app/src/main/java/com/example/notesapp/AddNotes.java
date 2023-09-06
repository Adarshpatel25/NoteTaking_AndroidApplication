package com.example.notesapp;


import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;


public class AddNotes extends AppCompatActivity {

    EditText editText3;
    EditText editText4;
    Button button;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotes);
        editText3 = findViewById(R.id.editTextTextPersonName3);
        editText4 = findViewById(R.id.editTextTextPersonName4);
        button = findViewById(R.id.button2);
        constraintLayout = findViewById(R.id.cons);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String heading = editText3.getText().toString();

                String filename = heading + ".txt";
                if (!editText4.getText().toString().trim().isEmpty()) {
                    File dir = getFilesDir();
                    File file = new File(dir, filename);
                    FileOutputStream fout;
                    String text = editText4.getText().toString();
                    Toast.makeText(AddNotes.this, text, Toast.LENGTH_SHORT).show();
                    try {
                        fout = AddNotes.this.openFileOutput(filename, Context.MODE_PRIVATE);
                        fout.write(editText4.getText().toString().trim().getBytes());
                        fout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent i = new Intent(AddNotes.this, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(AddNotes.this, "SAVED!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddNotes.this, "Content can't be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
