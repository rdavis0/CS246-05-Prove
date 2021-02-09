package com.example.cs246_05_prove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class DisplayScriptureActivity extends AppCompatActivity {
    private final String TAG = "DisplayScriptureAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_scripture);

        Intent intent = getIntent();
        String scripture = intent.getStringExtra(MainActivity.EXTRA_SCRIPTURE);

        Log.d(TAG, "Received intent with " + scripture);

        TextView textView = findViewById(R.id.favScripture);
        textView.setText(scripture);
    }

    public void saveScripture(View view) { //need view param?
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.PREF_FILE_KEY), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Intent intent = getIntent();

        String book = intent.getStringExtra(MainActivity.EXTRA_BOOK);
        String chapter = intent.getStringExtra(MainActivity.EXTRA_CHAPTER);
        String verse = intent.getStringExtra(MainActivity.EXTRA_VERSE);
        editor.putString(getString(R.string.FAV_SCRIPTURE_BOOK_KEY), book);
        editor.putString(getString(R.string.FAV_SCRIPTURE_CHAPTER_KEY), chapter);
        editor.putString(getString(R.string.FAV_SCRIPTURE_VERSE_KEY), verse);
        editor.commit();

        Context context = getApplicationContext();
        CharSequence text = "Favorite scripture saved";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}