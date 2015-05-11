package com.ri.bullsandcows;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Play extends ActionBarActivity {

    int[] answer = {10,10,10,10};
    TextView tAnswer;
    int tryTime = 0;
//    final static String RESULT_RECORD = "resultRecord";
//    final static String TRY_COUNT = "tryCount";
//    final static String ANSWER_RECORD = "answerRecord";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        tAnswer = (TextView) findViewById(R.id.result);
        Random r = new Random();
        for(int i = 0; i < 4;){
            int n = r.nextInt(10);
            answer[i] = n;
            if(i == 0){
                i++;
                continue;
            }
            for(int j = 0; j <= i; j++){
                if(i == j){
                    i++;
                    break;
                }
                if(answer[j] == answer[i]){
                    break;
                }
            }
        }
//        String ans = Arrays.toString(answer).replaceAll("[^\\d]","");
//        tAnswer.setText(ans);
    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState){
//        savedInstanceState.putString(RESULT_RECORD, tAnswer.getText().toString());
//        savedInstanceState.putInt(TRY_COUNT, tryTime);
//        savedInstanceState.putIntArray(ANSWER_RECORD, answer);
//        super.onSaveInstanceState(savedInstanceState);
//    }

//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        tAnswer.setText(savedInstanceState.getString(RESULT_RECORD));
//        tryTime = savedInstanceState.getInt(TRY_COUNT);
//        answer = savedInstanceState.getIntArray(ANSWER_RECORD);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.answer) {
            String ans = Arrays.toString(answer).replaceAll("[^\\d]","");
            ContextThemeWrapper cw = new ContextThemeWrapper(this, R.style.AlertDialogTheme);
            AlertDialog.Builder dialog = new AlertDialog.Builder(cw);
            dialog.setTitle("Answer");
            dialog.setMessage(ans);
            dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void send(View view) {
        tryTime++;
        int HA = 0;
        int HB = 0;
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String in = editText.getText().toString();
        char[] message = in.toCharArray();
        int len = message.length;
        int[] input = new int[len];
        int count = 0;
        for(char s : message){
            input[count] = Integer.parseInt(String.valueOf(s));
            count++;
        }
        for(int i = 0; i < len; i++){
            if(input[i] == answer[i]){
                HA++;
                HB--;
            }
        }
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(input[i] == answer[j]){
                    HB++;
                }
            }
        }
        if(tryTime == 1){
            String s;
            if(HA == 4){
//                s = "No." + tryTime + ":「" + in + "」→「" + String.valueOf(HA) + "A" + String.valueOf(HB) + "B」\nYou win!!!";
                s = "No." + tryTime + ":「" + in + "」→「" + String.valueOf(HA) + "A" + String.valueOf(HB) + "B」";
                tAnswer.setText(s);
                ContextThemeWrapper cw = new ContextThemeWrapper(this, R.style.AlertDialogTheme);
                AlertDialog.Builder dialog = new AlertDialog.Builder(cw);
                dialog.setTitle("You Win!!!");
                dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.show();
//                Timer t = new Timer();
//                t.schedule(new TimerTask() {
//
//                    @Override
//                    public void run() {
//                        finish();
//                    }
//                }, 5000);
            } else{
                s = "No." + tryTime + ":「" + in + "」→「" + String.valueOf(HA) + "A" + String.valueOf(HB) + "B」";
                tAnswer.setText(s);
            }
        } else{
            String edi;
            if(HA == 4){
//                edi = tAnswer.getText().toString() + "\nNo." + tryTime + ":「" + in + "」→「" + String.valueOf(HA) + "A" + String.valueOf(HB) + "B」\nYou win!!!";
                edi = tAnswer.getText().toString() + "\nNo." + tryTime + ":「" + in + "」→「" + String.valueOf(HA) + "A" + String.valueOf(HB) + "B」";
                tAnswer.setText(edi);
                ContextThemeWrapper cw = new ContextThemeWrapper(this, R.style.AlertDialogTheme);
                AlertDialog.Builder dialog = new AlertDialog.Builder(cw);
                dialog.setTitle("You Win!!!");
                dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.show();
//                Timer t = new Timer();
//                t.schedule(new TimerTask() {
//
//                    @Override
//                    public void run() {
//                        finish();
//                    }
//                }, 5000);
            } else{
                edi = tAnswer.getText().toString() + "\nNo." + tryTime + ":「" + in + "」→「" + String.valueOf(HA) + "A" + String.valueOf(HB) + "B」";
                tAnswer.setText(edi);
            }
        }
        editText.setText("");
        final ScrollView scrollview = ((ScrollView) findViewById(R.id.scroll));
        scrollview.post(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }
}
