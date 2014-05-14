package com.redcarddev.kickshot_real;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by Jordan on 2/18/14.
 */
public class CoolVideos extends Activity implements View.OnClickListener {
    protected String LOGTAG = "CorrectForm";

    Button aboutKickShot = null;
    Button boardGame = null;
    Button boardGamePlay = null;
    Button RadioAd = null;
    Button boardGameReview = null;
    Button credits = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cool_videos);

        this.aboutKickShot = (Button)findViewById(R.id.about);
        this.aboutKickShot.setOnClickListener(this);

        this.boardGame = (Button)findViewById(R.id.inside);
        this.boardGame.setOnClickListener(this);

        this.boardGamePlay = (Button)findViewById(R.id.live_action);
        this.boardGamePlay.setOnClickListener(this);

        this.RadioAd = (Button)findViewById(R.id.ad);
        this.RadioAd.setOnClickListener(this);

        this.boardGameReview = (Button)findViewById(R.id.review);
        this.boardGameReview.setOnClickListener(this);

        this.credits = (Button)findViewById(R.id.credits);
        this.credits.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Log.v(LOGTAG, "onClick e");
        if(view.getId() == R.id.about){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Li6UloHKsK4&feature=youtu.be")));
        }
        else if(view.getId() == R.id.inside){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=QT04as_DXuk&feature=youtu.be")));
        }
        else if(view.getId() == R.id.live_action){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Ea_x5cdFi2w&feature=youtu.be")));
        }
        else if(view.getId() == R.id.ad){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=wneTRgTkBOU&feature=youtu.be")));
        }
        else if(view.getId() == R.id.review){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=M6z5dSjdpJI&feature=youtu.be")));
        }
        else if(view.getId() == R.id.credits){
            Intent intent = new Intent(CoolVideos.this, credits.class);
            startActivity(intent);
        }
        else {
            Log.v(LOGTAG, Integer.toString(view.getId()));
        }

        Log.v(LOGTAG, "onClick e");
    }
}
