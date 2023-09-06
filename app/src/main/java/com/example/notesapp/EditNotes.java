package com.example.notesapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class EditNotes extends AppCompatActivity {


    EditText editText1, editText2;
    Button button;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        editText1 = findViewById(R.id.editTextTextPersonName);
        editText2 = findViewById(R.id.editTextTextPersonName2);
        button = findViewById(R.id.button3);
        constraintLayout = findViewById(R.id.cons);

        Intent intent = getIntent();
        String heading = intent.getStringExtra("name");
        int color = intent.getIntExtra("color", 0);
        constraintLayout.setBackgroundColor(EditNotes.this.getResources().getColor(color));

        editText1.setText(heading);
        FileInputStream fileInputStream;
        try {
            fileInputStream = openFileInput(heading + ".txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            String line;
            String whole = "";
            while ((line = reader.readLine()) != null) {
                if (whole == "") {
                    whole = whole + line;
                } else {
                    whole = whole + "\n" + line;
                }
            }
            reader.close();
            fileInputStream.close();
            editText2.setText(whole);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String filename = heading + ".txt";
                    if (!editText1.getText().toString().trim().isEmpty()) {
                        File dir = getFilesDir();
                        File file = new File(dir, filename);

                        FileOutputStream fout;

                        try {
                            fout = openFileOutput(filename, Context.MODE_PRIVATE);
                            fout.write(editText2.getText().toString().trim().getBytes());
                            fout.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Intent i = new Intent(EditNotes.this, MainActivity.class);
                        startActivity(i);

                        Toast.makeText(EditNotes.this, "SAVED!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditNotes.this, "Content can't be empty", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}