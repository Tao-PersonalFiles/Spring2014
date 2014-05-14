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
public class CorrectForm extends Activity implements OnClickListener {
    protected String LOGTAG = "CorrectForm";

    Button headerButton = null;
    Button throwinButton = null;
    Button slidetackleButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correct_form);

        this.headerButton = (Button)findViewById(R.id.header);
        this.headerButton.setOnClickListener(this);

        this.throwinButton = (Button)findViewById(R.id.throw_in);
        this.throwinButton.setOnClickListener(this);

        this.slidetackleButton = (Button)findViewById(R.id.slide_tackle);
        this.slidetackleButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Log.v(LOGTAG, "onClick e");

        if (view.getId() == R.id.header) {
            //Intent intent = new Intent(CorrectForm.this, HeaderHelp.class);
            //startActivity(intent);
        }else if(view.getId() == R.id.throw_in){
            //Intent intent = new Intent(CorrectForm.this, ThrowInHelp.class);
            //startActivity(intent);
        }else if(view.getId() == R.id.slide_tackle){
            //Intent intent = new Intent(CorrectForm.this, SlideTackleHelp.class);
            //startActivity(intent);
        }
        else {
            Log.v(LOGTAG, Integer.toString(view.getId()));
        }

        Log.v(LOGTAG, "onClick e");
    }
}
