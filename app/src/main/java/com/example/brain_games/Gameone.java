package com.example.brain_games;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Random;

public class Gameone extends AppCompatActivity {
    MediaPlayer mysong, mysongs;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button btn0, btn1, btn2, btn3, btnPlayAgain, btn9;
    TextView tvCorrect, tvScore, tvSum, tvTimer;
    RelativeLayout gameRelativeLayout;
    GridLayout gridLayout;
    int locationOfCorrectAnswer;
    int numberOfQuestions = 0;
    int score = 0;
    Button btnStart;
    ToggleButton buttona;


    //TODO PLAY AGAIN BUTTON CODE START HERE:
    public void btnPlayAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        tvTimer.setText("30s");
        tvScore.setText("0/0");
        tvCorrect.setText("");
        btnPlayAgain.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
//        imagea.setVisibility(View.INVISIBLE)
        tvScore.setVisibility(View.VISIBLE);
        tvTimer.setVisibility(View.VISIBLE);
        tvSum.setVisibility(View.VISIBLE);
        generateQuestion();

        //TODO COUNTDOWNTIMER CODE START HERE :
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                gridLayout.setVisibility(View.INVISIBLE);
                btnPlayAgain.setVisibility(View.VISIBLE);
//                imagea.setVisibility(View.VISIBLE);
                btn9.setVisibility(View.VISIBLE);
                tvScore.setVisibility(View.INVISIBLE);
                tvTimer.setVisibility(View.INVISIBLE);
                tvSum.setVisibility(View.INVISIBLE);
                tvTimer.setText("0s");
               tvCorrect.setText("your score " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
//                if (btnPlayAgain.isPressed()) {
//                   gridLayout.setVisibility(View.VISIBLE);
//
//                }
                btnPlayAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(Gameone.this,Sub.class);
                                startActivity(intent);
                    }
                });


                btn9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                        System.exit(0);
                    }
                });


            }
        }.start();
    }

    //TODO GENERATE RANDOM QUESTION CODE START HERE :
    public void generateQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);


                tvSum.setText(Integer.toString(a) + " + " + Integer.toString(b));


                locationOfCorrectAnswer = rand.nextInt(4);

                //TODO answers.clear(); function working to clear the previous answer in the arrayList;
                answers.clear();

                int inCorrectAnswer;
                for (int i = 0; i < 4; i++) {

                    if (i == locationOfCorrectAnswer) {
                        answers.add(a + b);
                    } else {

                        inCorrectAnswer = rand.nextInt(41);
                        while (inCorrectAnswer == a + b) {
                            inCorrectAnswer = rand.nextInt(41);
                        }
                        answers.add(inCorrectAnswer);


                    }
                }


                btn0.setText(Integer.toString(answers.get(0)));
                btn1.setText(Integer.toString(answers.get(1)));
                btn2.setText(Integer.toString(answers.get(2)));
                btn3.setText(Integer.toString(answers.get(3)));
            }


            // TODO CHOOSE ANSWER BUUTON CODE START HERE :
    public void btnChooseAnswer(View view) {

        Log.i("Tag", (String) view.getTag());
               if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            tvCorrect.setText("correct!");
            mysongs.start();

               Log.i("correct", "correct");

        } else {
            tvCorrect.setText("Wrong!");
            mysong.start();
        }
        numberOfQuestions++;
        tvScore.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }


    // TODO BUTTON BTNsTART ONCLICK CODE START HERE :
    public void Start(View view) {
        btnStart.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        btnPlayAgain(findViewById(R.id.btnPlayAgain));
        if (btnPlayAgain.isPressed()){
            gridLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mysong=MediaPlayer.create(Gameone.this,R.raw.wrong);
        mysongs=MediaPlayer.create(Gameone.this,R.raw.right);
        setContentView(R.layout.activity_gameone);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        btnStart = (Button) findViewById(R.id.btnStart);
        tvCorrect = (TextView) findViewById(R.id.tvCorrect);
        tvScore = (TextView) findViewById(R.id.tvScore);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        btn9 = (Button)findViewById(R.id.btn9);
        tvSum = (TextView) findViewById(R.id.tvSum);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
//        imagea = (ImageView)findViewById(R.id.imagew);
        buttona =(ToggleButton) findViewById(R.id.wmute);

        buttona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttona.isChecked()){
                    Toast.makeText(getApplicationContext(),"Mute:-"+buttona.getText().toString(),Toast.LENGTH_SHORT).show();
                    mysong.start();
                    mysongs.start();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Voice:-"+buttona.getText().toString(),Toast.LENGTH_LONG).show();
                    mysongs.stop();
                    mysong.stop();
                }

            }

        });

        }


    }



