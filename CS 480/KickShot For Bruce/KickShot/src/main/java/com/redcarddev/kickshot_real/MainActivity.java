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

public class MainActivity extends Activity implements OnClickListener {
	
	protected String LOGTAG = "MainActivity";
	
	Button playButton = null;
    Button r1 = null;
    Button facebook = null;
    Button twitter = null;
    Button buy = null;
    Button website = null;
    Button videos = null;
    Button form = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.playButton = (Button)findViewById(R.id.play);
		this.playButton.setOnClickListener(this);

        this.facebook = (Button)findViewById(R.id.fb);
        this.facebook.setOnClickListener(this);

        this.twitter = (Button)findViewById(R.id.twitter);
        this.twitter.setOnClickListener(this);

        this.buy = (Button)findViewById(R.id.buy);
        this.buy.setOnClickListener(this);

        this.website = (Button)findViewById(R.id.website);
        this.website.setOnClickListener(this);

        this.videos = (Button)findViewById(R.id.videos);
        this.videos.setOnClickListener(this);

        this.form = (Button)findViewById(R.id.form);
        this.form.setOnClickListener(this);

        //this.r1 = (Button)findViewById(R.id.r1);
        //this.r1.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	@Override
	public void onClick(View view) {
		
		Log.v(LOGTAG, "onClick e");
		
		if (view.getId() == R.id.play) {
			
			Intent intent = new Intent(MainActivity.this, PlayGame.class);
			startActivity(intent);
			
			
		}else if(view.getId() == R.id.fb){
            openWebUrl("https://www.facebook.com/KickShotSoccer");
        }else if(view.getId() == R.id.twitter){
            openWebUrl("https://twitter.com/kickshotsoccer");
        }else if(view.getId() == R.id.buy){
            openWebUrl("http://kickshot.org/products/");
        }else if(view.getId() == R.id.website){
            openWebUrl("http://kickshot.org/");
        }else if(view.getId() == R.id.videos){
            Intent intent = new Intent(MainActivity.this, CoolVideos.class);
            startActivity(intent);
        }else if(view.getId() == R.id.form){
            Intent intent = new Intent(MainActivity.this, CorrectForm.class);
            startActivity(intent);
        }/*else if (view.getId() == R.id.r1) {

            Intent intent = new Intent(MainActivity.this, LevelOneRules.class);
            startActivity(intent);

        }*/
        else {
            Log.v(LOGTAG, Integer.toString(view.getId()));
        }
		
		Log.v(LOGTAG, "onClick e");
	}

    public void openWebUrl(String inURL ){
        Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(inURL));
        startActivity(browse);
    }

}
