package edu.up.threethirteengamestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toggler tog = new Toggler((EditText) findViewById(R.id.editText));
        Button runTest = findViewById(R.id.button);
        runTest.setOnClickListener(tog);


    }
}