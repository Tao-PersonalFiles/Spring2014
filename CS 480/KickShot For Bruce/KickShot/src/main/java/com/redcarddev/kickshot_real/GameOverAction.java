package com.redcarddev.kickshot_real;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by otternq on 10/27/13.
 */
public class GameOverAction extends Activity {

    String LOGTAG = this.getClass().getName();

    final static int PLAYER_WIN = 1;
    final static int PLAYER_LOSE = 0;

    int state = 0;

    protected TextView actionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.level_one_actions);

        Intent mIntent = getIntent();
        this.state = mIntent.getIntExtra("player_state", -1);

        this.actionText = (TextView)findViewById(R.id.actionText);

        this.setActionView();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.finish();
        return true;
    }

    protected boolean setActionView() {

        String action = "";
        // every time this is called randomize if it's a shot to the left or right
        switch (this.state) {
            case GameOverAction.PLAYER_WIN:
                action = "YOU WIN!";
                break;
            case GameOverAction.PLAYER_LOSE:
                action = "They WIN!";
                break;
            default:
                return false;
        }

        this.setActionText(action);

        return true;

    }

    protected boolean setActionText(String action) {

        this.actionText.setText(action);

        return true;

    }



}
