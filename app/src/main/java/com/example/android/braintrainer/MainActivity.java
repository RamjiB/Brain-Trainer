package com.example.android.braintrainer;

import android.content.res.ColorStateList;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.android.braintrainer.R.id.finalMessage;
import static com.example.android.braintrainer.R.id.playAgain;
import static com.example.android.braintrainer.R.id.timer;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    CountDownTimer countDownTimer;
    TextView math;
    Button a1;
    Button a2;
    Button a3;
    Button a4;
    Button start;
    Button playAgain;
    Random random;
    Boolean Clicked = false;
    TextView score;
    TextView correctMessage;
    TextView wrongMessage;
    String correctoption;
    String buttonText;
    TextView finalMessage;

    int answer;
    int i=1;
    int j =0;

    public void playAgain(View view){

        timer.setText("60s");
        countDownTimer.cancel();
        math.setText(" ");
        a1.setText("a1");
        a2.setText("a2");
        a3.setText("a3");
        a4.setText("a4");
        score.setText("0/0");
        correctMessage.setVisibility(View.INVISIBLE);
        wrongMessage.setVisibility(View.INVISIBLE);
        finalMessage.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        start.setVisibility(View.VISIBLE);
    }

    public void math() {
        
        score = (TextView) findViewById(R.id.score);

        if (Clicked == false){

            math = (TextView) findViewById(R.id.math);
            random = new Random();
            int number1 = random.nextInt(50) + 1;
            int number2 = random.nextInt(50) + 1;
            math.setText(String.valueOf(number1) + " + " + String.valueOf(number2));
            score.setText(String.valueOf(j) + "/" + String.valueOf(i));
            answer = number1 + number2;
            check(number1,number2);
        }
//        score();
    }

    public void check(int number1,int number2){

        int answer = number1 + number2;
        a1 = (Button) findViewById(R.id.a1);
        a2 = (Button) findViewById(R.id.a2);
        a3 = (Button) findViewById(R.id.a3);
        a4 = (Button) findViewById(R.id.a4);

        int o1 = (int) random.nextInt(50)+1;
        int o2 = (int) random.nextInt(50)+1;
        int o4 = (int) random.nextInt(50)+1;

        //storing answers in list
        ArrayList<String> answers;

        if ((o1 == answer) || (o2 == answer) || (o4 == answer)){

            answers = new ArrayList<String>();
            answers.add(String.valueOf(o1+1));
            answers.add(String.valueOf(o2+2));
            answers.add(String.valueOf(answer));
            answers.add(String.valueOf(o4+3));
            Collections.shuffle(answers);
        }else{
            answers = new ArrayList<String>();
            answers.add(String.valueOf(o1));
            answers.add(String.valueOf(o2));
            answers.add(String.valueOf(answer));
            answers.add(String.valueOf(o4));
            Collections.shuffle(answers);
        }

        // options

        if (Clicked == false){
            a1.setText(answers.get(0));
            a2.setText(answers.get(1));
            a3.setText(answers.get(2));
            a4.setText(answers.get(3));
            Clicked = true;
        }
    }


    public void isClicked(View view){
        correctoption = String.valueOf(answer);
        // string of a pressed button

        Button pressed = (Button)view;
        buttonText = pressed.getText().toString();


        if(buttonText == correctoption){

            wrongMessage.setVisibility(View.INVISIBLE);
            correctMessage = (TextView) findViewById(R.id.correctMessage);
            correctMessage.setVisibility(View.VISIBLE);
            Clicked = false;
            j++;
            score.setText(String.valueOf(j) + "/" + String.valueOf(i));
            i++;
            math();
        }else{
            correctMessage.setVisibility(View.INVISIBLE);
            wrongMessage = (TextView)findViewById(R.id.wrongMessage);
            wrongMessage.setVisibility(View.VISIBLE);
            Clicked = false;
            score.setText(String.valueOf(j) + "/" + String.valueOf(i));
            i++;
            math();
        }

    }


    public void start(View view) {


        countDownTimer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timer = (TextView) findViewById(R.id.timer);
                timer.setText(String.valueOf(millisUntilFinished /1000)+"s");
                start.setVisibility(View.INVISIBLE);
                math();

            }

            @Override
            public void onFinish() {

                finalMessage.setText("Your Score\n" + String.valueOf(j) + "/" + String.valueOf(i) );
                finalMessage.setVisibility(View.VISIBLE);
                correctMessage.setVisibility(View.INVISIBLE);
                wrongMessage.setVisibility(View.INVISIBLE);
                start = (Button) findViewById(R.id.Start);
                playAgain.setVisibility(View.VISIBLE);
                }
        }.start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        correctMessage = (TextView) findViewById(R.id.correctMessage);
        correctMessage.setVisibility(View.INVISIBLE);

        wrongMessage = (TextView)findViewById(R.id.wrongMessage);
        wrongMessage.setVisibility(View.INVISIBLE);

        finalMessage = (TextView) findViewById(R.id.finalMessage);
        finalMessage.setVisibility(View.INVISIBLE);

        start = (Button) findViewById(R.id.Start);
        start.setVisibility(View.VISIBLE);

        playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
    }
}
