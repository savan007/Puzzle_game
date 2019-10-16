package com.example.ticgame;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class home extends Activity {
	
	Button play,exit,about;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    
    play=(Button)findViewById(R.id.button1);
    exit=(Button)findViewById(R.id.button3);
    about=(Button)findViewById(R.id.button2);
    play.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			
			Intent i=new Intent(getApplicationContext(), TictacActivity.class);
			startActivity(i);
		}
	});
    exit.setOnClickListener(new View.OnClickListener() {
		
	

		public void onClick(View v) {
			// TODO Auto-generated method stub
	finish();		
		}
	});
    about.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			
			Intent i=new Intent(getApplicationContext(),about.class);
			startActivity(i);
			
		}
	});
    }

  

    
}
