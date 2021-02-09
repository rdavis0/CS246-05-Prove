package com.example.cs246_05_prove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_SCRIPTURE = "com.example.cs246_05_prove.SCRIPTURE";
    public static final String EXTRA_BOOK = "com.example.cs246_05_prove.BOOK";
    public static final String EXTRA_CHAPTER = "com.example.cs246_05_prove.CHAPTER";
    public static final String EXTRA_VERSE = "com.example.cs246_05_prove.VERSE";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitScripture (View view) {
        Intent intent = new Intent(this, DisplayScriptureActivity.class);
        String book;
        String chapter;
        String verse;
        String scripture;

        EditText editText = (EditText) findViewById(R.id.textEditBook);
        book = editText.getText().toString();
        editText = (EditText) findViewById(R.id.textEditChapter);
        chapter = editText.getText().toString();
        editText = (EditText) findViewById(R.id.textEditVerse);
        verse = editText.getText().toString();

        scripture = formatScripture(book, chapter, verse);

        Log.d(TAG, "About to create intent with " + scripture);

        intent.putExtra(EXTRA_BOOK, book);
        intent.putExtra(EXTRA_CHAPTER, chapter);
        intent.putExtra(EXTRA_VERSE, verse);
        intent.putExtra(EXTRA_SCRIPTURE, scripture);
        startActivity(intent);
    }

    public void loadScripture(View view) {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.PREF_FILE_KEY), Context.MODE_PRIVATE);
        String book = sharedPref.getString(getString(R.string.FAV_SCRIPTURE_BOOK_KEY), "");
        String chapter = sharedPref.getString(getString(R.string.FAV_SCRIPTURE_CHAPTER_KEY), "");
        String verse = sharedPref.getString(getString(R.string.FAV_SCRIPTURE_VERSE_KEY), "");

        EditText editText = (EditText) findViewById(R.id.textEditBook);
        editText.setText(book);
        editText = (EditText) findViewById(R.id.textEditChapter);
        editText.setText(chapter);
        editText = (EditText) findViewById(R.id.textEditVerse);
        editText.setText(verse);
    }

    public String formatScripture (String book, String chapter, String verse) {
        return book + " " + chapter + ":" + verse;
    }
}