package com.practice.quizapp.model;

public class Question {
    private boolean answer_true;
    private String quesn;

    public Question(String quesn,boolean answer_true) {
        this.answer_true = answer_true;
        this.quesn = quesn;
    }

    public Question() {
    }

    public boolean isAnswer_true() {
        return answer_true;
    }

    public void setAnswer_true(boolean answer_true) {
        this.answer_true = answer_true;
    }

    public String getQuesn() {
        return quesn;
    }

    public void setQuesn(String quesn) {
        this.quesn = quesn;
    }

    @Override
    public String toString() {
        return "Question{" +
                "answer_true=" + answer_true +
                ", quesn='" + quesn + '\'' +
                '}';
    }
}
