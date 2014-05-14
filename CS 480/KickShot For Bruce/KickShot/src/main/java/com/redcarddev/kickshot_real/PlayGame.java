package com.redcarddev.kickshot_real;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.util.Random;

/**
 * Created by Jordan on 2/18/14.
 */

import com.redcarddev.kickshot_real.utils.LevelOneState;
import com.redcarddev.kickshot_real.utils.SoundManager;

public class PlayGame extends Activity implements OnClickListener {
    protected String LOGTAG = "MainActivity";

    Button juniorButton = null;
    Button instructions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_game);

        this.juniorButton = (Button)findViewById(R.id.junior);
        this.juniorButton.setOnClickListener(this);

        this.instructions = (Button)findViewById(R.id.instruction);
        this.instructions.setOnClickListener(this);

        SoundManager.Instance().SetOwner(this);
        try {
            SoundManager.Instance().LoadSound("crowd", R.raw.huge_crowd);
        } catch (Exception e) {
            // WHAT TO DO IF SOUNDS FAIL TO LOAD?
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {

        Log.v(LOGTAG, "onClick e");

        if (view.getId() == R.id.junior) {

            Intent intent = new Intent(PlayGame.this, LevelOne.class);

            Random random = new Random();
            LevelOneState state = new LevelOneState();
            intent.putExtra(LevelOne.PARAM_RANDOM, random);
            intent.putExtra(LevelOne.PARAM_STATE, state);

            SoundManager.Instance().PlaySound("crowd");

            startActivity(intent);


        }else if(view.getId() == R.id.instruction){
            Intent intent = new Intent(PlayGame.this, LevelOneRules.class);
            startActivity(intent);
        }
        else {
            Log.v(LOGTAG, Integer.toString(view.getId()));
        }

        Log.v(LOGTAG, "onClick e");
    }
}
