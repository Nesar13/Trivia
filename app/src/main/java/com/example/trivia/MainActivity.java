package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.toolbox.Volley;
import com.example.trivia.data.QuestionBank;
import com.example.trivia.model.Question;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Question>  questionList=new QuestionBank().getQuestions();

        Log.d("MAIN: ", "onCreate: "+questionList);

    }
}
