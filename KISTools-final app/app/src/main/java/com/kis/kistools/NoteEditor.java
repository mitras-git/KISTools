package com.kis.kistools;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

public class NoteEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText = (EditText) findViewById(R.id.editTextTextMultiLine);

        Intent intent = getIntent();
        int noteId = intent.getIntExtra("noteId", -1);

        if (noteId != -1) {

            editText.setText(Notes.notes.get(noteId));

        }

        else {

            Notes.notes.add("");
            noteId = Notes.notes.size() - 1;
            Notes.arrayAdapter.notifyDataSetChanged();

        }


        final int finalNoteId = noteId;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Notes.notes.set(finalNoteId, String.valueOf(s));
                Notes.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.kis.scheduleon", Context.MODE_PRIVATE);

                HashSet<String> set = new HashSet(Notes.notes);

                sharedPreferences.edit().putStringSet("notes", set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void saveNote(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);

        startActivity(intent);
    }
}