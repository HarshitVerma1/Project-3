package com.practice.quizapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.practice.quizapp.controller.AppController;
import com.practice.quizapp.model.Question;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {
     ArrayList<Question> questionArrayList=new ArrayList<>();
    String url="https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
    public List<Question> getQuestion(final AnswerListAsyncResponse callback)
    {
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url,
                null, response -> {
            Log.d("AllQuestionsAndAnswers"," onCreateRepo:  "+response.toString());

            for (int i = 0; i < response.length(); i++) {
                try
                {
                    /**
                     * This process is very important for storing data in List/ArrayList, withOut this process your app will be CRASHED ):
                     * In belowLine, We are setting to the parameter of Constructor of Question.java Class
                     */
                    Question question=new Question(response.getJSONArray(i).get(0).toString(),response.getJSONArray(i).getBoolean(1));

                    /**
                     * Add question list in ArrayList and return to this  ArrayList for accessing in MainActivity.java
                     */
                    questionArrayList.add(question);
                    Log.d("questionListQuestions"," :  "+question.toString());
                    Log.d("AllQues","onCreateRepo:  "+response.getJSONArray(i).get(0));
                    Log.d("AllANSWERS: ","onCreateRepo:  "+response.getJSONArray(i).get(1));
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            if (callback!=null)
                callback.processFinished(questionArrayList);
            /**
             * All JSON Data will printed for Debug in logcat pannel.
             */
            Log.d("Response_data",response.toString());
        },
                error -> {
//                        Log.d("Response Failure : ","Connenctivity_Error!!! ");
                });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return questionArrayList;
    }
}
