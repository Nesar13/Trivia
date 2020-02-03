package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    private  TextView scoreText;
    private int scoreCounter=0;

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
        scoreText=findViewById(R.id.score_textView);


        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);


        questionList = new QuestionBank().getQuestions(new answerListAsync() {
            @Override
            public void processFinished(ArrayList<Question> question) {

                questionTextview.setText(question.get(currentQuestionIndex).getAnswer());
                updateQuestion();
                Log.d("MAIN: ", "onCreate: " + question);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.previous_button:
                if (currentQuestionIndex == 0) break;

                currentQuestionIndex = ((currentQuestionIndex - 1) % questionList.size());

                updateQuestion();

                break;
            case R.id.next_button:
                currentQuestionIndex = ((currentQuestionIndex + 1) % questionList.size());
                updateQuestion();
                break;
            case R.id.false_button:
                isAnswerCorrect(false);
                updateQuestion();
                break;
            case R.id.true_button:
                isAnswerCorrect(true);
                updateQuestion();
                break;


        }

    }


    public void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getAnswer();
        questionTextview.setText(question);
        questionCounterTextview.setText(currentQuestionIndex + 1 + " / " + questionList.size());
    }
    public void scoreIncrease(){
        if (scoreCounter>0) {
            scoreCounter += 10;

            scoreText.setText(String.valueOf(scoreCounter));
        } else{
            scoreCounter=0;
            scoreText.setText(String.valueOf(scoreCounter));
        }

        


    }

    public void isAnswerCorrect(boolean answer) {
        int toastID = 0;
        boolean trueAnswer = questionList.get((currentQuestionIndex)).isAnswerTrue();
        if (trueAnswer == answer) {
            fadeView();
            toastID = R.string.correct_answer;
        } else {
            shakeAnimation();
            toastID = R.string.incorrect_answer;
        }

        Toast.makeText(this, toastID, Toast.LENGTH_SHORT).show();

    }

    //This will fade the card to green when the answer is correct
    private void fadeView() {
        final CardView cardView = findViewById(R.id.cardView);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f); //Initial value 1.0 is to fade, and 0 to unfade

        alphaAnimation.setDuration(450); //in ms
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE); //reversing the animation at the end
        cardView.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    // This will shake when a user gets the question incorrect

    private void shakeAnimation() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        final CardView card = findViewById(R.id.cardView);
        card.setAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                card.setCardBackgroundColor(getResources().getColor(R.color.cardColor));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                card.setCardBackgroundColor(Color.WHITE);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}
