package com.androidbegin.menuviewpagertutorial;

import java.util.ArrayList;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class Splash extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 3000;
    //ProgressBar a;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(Splash.this,MainActivity.class);
    			//a = new ProgressBar(Splash.this);
    			//a.setIndeterminate(false);
    			//a.animate();
    		
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }}
    /*
public class Splash extends AsyncTask<Void, Integer, Integer> {

	ProgressBar ProgressBar1;
    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Integer doInBackground(Void... args) {

        download();

        return 1;
    }

    private void download() {
        // We are just imitating some process thats takes a bit of time
        // (loading of resources / downloading)
        int count = 10;
        for (int i = 0; i < count; i++) {

            // Update the progress bar after every step
            int progress = (int) ((i / (float) count) * 100);
            publishProgress(progress);

            // Do some long loading things
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignore) {
            }
        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        ProgressBar1.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Integer a) {
        ProgressBar1.setVisibility(View.GONE);
    }
}
*/