package com.practice.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.practice.quizapp.controller.AppController;
import com.practice.quizapp.data.AnswerListAsyncResponse;
import com.practice.quizapp.data.Repository;
import com.practice.quizapp.databinding.ActivityMainBinding;
import com.practice.quizapp.model.Question;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        private ActivityMainBinding binding;
        LottieAnimationView lottieAnimationView;
        private int currentQuestionIndex=0;
        List<Question> questionList;
        String url="https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        lottieAnimationView=binding.myanim;

                /**
                 * Same Code for JsonRequest is written in Repository.java
                 */
//        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//
//                }
//        }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//        });
//

        //sare questions one by one  repository.java se questionList me save honge aakr
        questionList=new Repository().getQuestion(new AnswerListAsyncResponse() {
                @Override
                public void processFinished(ArrayList<Question> questionArrayList) {
                        Log.d("QuestionList: ","onCreate : "+ questionList);
                        binding.questionTextview.setText(questionList.
                                get(currentQuestionIndex).getQuesn());

                        binding.textView3.setText(String.format("Question : %d/%d", currentQuestionIndex + 1, questionList.size() + 1));

                }


        });
        binding.next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        currentQuestionIndex=(currentQuestionIndex+1)%questionList.size();
                        updateQuestion();
                }
                private void updateCounter(ArrayList<Question> arrayList) {
                        binding.textView3.setText(String.format("Question : %d/%d", currentQuestionIndex + 1, questionList.size() + 1));
                }
                private void updateQuestion() {
                        String question=questionList.get(currentQuestionIndex).getQuesn();
                        binding.questionTextview.setText(question);
                        updateCounter((ArrayList<Question>) questionList);
                }
        });



//        binding.prev.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                        if (currentQuestionIndex>0)
//                        {
//                                currentQuestionIndex=(currentQuestionIndex-1)%questionList.size();
//                                updateQuestion();
//                        }
//                }
//                private void updateCounter(ArrayList<Question> arrayList) {
//                        binding.textView3.setText(String.format("Question : %d/%d", currentQuestionIndex - 1, questionList.size() + 1));
//                }
//                private void updateQuestion() {
//                        String question=questionList.get(currentQuestionIndex).getQuesn();
//                        binding.questionTextview.setText(question);
//                        updateCounter((ArrayList<Question>) questionList);
//                }
//        });


                binding.prev.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Toast.makeText(MainActivity.this,"Previous Button is not Allowed in this test!!!",Toast.LENGTH_SHORT).show();
                        }
                });

        binding.truebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        checkAnswer(true);

                }
                private void checkAnswer(boolean userChoose) {
                        boolean answer=questionList.get(currentQuestionIndex).isAnswer_true() ;
                        int snackMessageId=0;
                        if(userChoose==answer)
                        {
                                snackMessageId=R.string.correct_answer;
                                lottieAnimationView.setAnimation(R.raw.correct_answer);
                                lottieAnimationView.playAnimation();
                                fadeAnimation();
                        }
                        else
                        {
                                snackMessageId=R.string.incorrect_Answer;
                                lottieAnimationView.setAnimation(R.raw.wrong_answer);
                                lottieAnimationView.playAnimation();
                                shakeAnimation();
                        }
//                        Snackbar.make(binding.cardView,snackMessageId, BaseTransientBottomBar.LENGTH_SHORT)
//                                .show();
                }
        });


        binding.falsebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        checkAnswer(false);
                }

                private void checkAnswer(boolean userChoose) {
                        boolean answer=questionList.get(currentQuestionIndex).isAnswer_true() ;
                        int snackMessageId=0;
                        if(userChoose==answer)
                        {
                                snackMessageId=R.string.correct_answer;
                                lottieAnimationView.setAnimation(R.raw.correct_answer);
                                lottieAnimationView.playAnimation();
                                fadeAnimation();
                        }
                        else
                        {
                                snackMessageId=R.string.incorrect_Answer;
                                lottieAnimationView.setAnimation(R.raw.wrong_answer);
                                lottieAnimationView.playAnimation();
                                shakeAnimation();
                        }
//                        Snackbar.make(binding.cardView,snackMessageId, BaseTransientBottomBar.LENGTH_SHORT)
//                                .show();
                }
        });
    }




        private void fadeAnimation() {
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(300);
                alphaAnimation.setRepeatCount(1);
                alphaAnimation.setRepeatMode(Animation.REVERSE);

                binding.cardView.setAnimation(alphaAnimation);

                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                                binding.questionTextview.setTextColor(Color.GREEN);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                                binding.questionTextview.setTextColor(Color.WHITE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                });


        }


        private void shakeAnimation() {
                Animation shake = AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.shake_animation);
                binding.cardView.setAnimation(shake);

                shake.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                                binding.questionTextview.setTextColor(Color.RED);

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                                binding.questionTextview.setTextColor(Color.WHITE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                });


        }


}