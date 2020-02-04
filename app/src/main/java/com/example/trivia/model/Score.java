package com.example.trivia.model;

/**
 * Score created to keep track of score
 */
public class Score {

    private int score=0;

    public Score() {
    }

    public Score(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
