package com.redcarddev.kickshot_real;

import java.util.Random;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;


/**
 * Created by Jordan on 12/3/13.
 */
public class Winning extends Activity implements OnClickListener{

    String LOGTAG = this.getClass().getName();

    protected int state = -1;

    //still need these for the message
    final static int COMPUTER_WON = 0;
    final static int PLAYER_WON = 1;

    Button playButton = null;
    Button menuButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.winning);

        this.playButton = (Button)findViewById(R.id.play_again);
        this.playButton.setOnClickListener(this);

        this.menuButton = (Button)findViewById(R.id.main_menu);
        this.menuButton.setOnClickListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.finish();
        return true;
    }

    @Override
    public void onClick(View view) {

        Log.v(LOGTAG, "onClick e");

        if (view.getId() == R.id.play_again) {

            Intent intent = new Intent(Winning.this, PlayGame.class);
            startActivity(intent);
        }else if(view.getId() == R.id.main_menu){
            Intent intent = new Intent(Winning.this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Log.v(LOGTAG, Integer.toString(view.getId()));
        }

        Log.v(LOGTAG, "onClick e");
    }



}
