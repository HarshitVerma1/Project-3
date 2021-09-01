package com.practice.quizapp.data;

import com.practice.quizapp.model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {
    void processFinished(ArrayList<Question> questionArrayList);

}
