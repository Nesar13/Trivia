package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.example.trivia.data.QuestionBank;
import com.example.trivia.data.answerListAsync;
import com.example.trivia.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton nextButton;
    private ImageButton prevButton;
    private Button trueButton;
    private Button falseButton;
    private TextView questionCounterTextview;
    private TextView questionTextview;

    private int currentQuestionIndex = 0;

    private List<Question> questionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.previous_button);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        questionCounterTextview = findViewById(R.id.count_text);
        questionTextview = findViewById(R.id.question_textview);

        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);


        questionList = new QuestionBank().getQuestions(new answerListAsync() {
            @Override
            public void processFinished(ArrayList<Question> question) {

                Log.d("MAIN: ", "onCreate: " + question);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.previous_button:
                break;
            case R.id.next_button:
                break;
            case R.id.false_button:
                break;
            case R.id.true_button:
                break;


        }

    }
}
