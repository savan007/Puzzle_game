package com.example.ticgame;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TictacActivity extends Activity {

	private tictac mGame;
	private Button mBoardButtons[];
	private TextView mInfoTextView;
	private TextView mHumanCount;
	private TextView mTieCount;
	private TextView mAndroidCount;
	
	private int mHumanCounter = 0;
	private int mTieCounter = 0;
	private int mAndroidCounter = 0;
	
	private Boolean mHumanFirst = true;
	private boolean mGameOver =false;
	 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictac);
        
        mBoardButtons = new Button[mGame.getBORD_SIZE()];
        mBoardButtons[0]=(Button)findViewById(R.id.one);
        mBoardButtons[1]=(Button)findViewById(R.id.two);
        mBoardButtons[2]=(Button)findViewById(R.id.three);
        mBoardButtons[3]=(Button)findViewById(R.id.four);
        mBoardButtons[4]=(Button)findViewById(R.id.five);
        mBoardButtons[5]=(Button)findViewById(R.id.six);
        mBoardButtons[6]=(Button)findViewById(R.id.seven);
        mBoardButtons[7]=(Button)findViewById(R.id.eight);
        mBoardButtons[8]=(Button)findViewById(R.id.nine);
        
        mInfoTextView =(TextView)findViewById(R.id.information);
        mHumanCount = (TextView)findViewById(R.id.humanCount);
        mTieCount =(TextView)findViewById(R.id.tiesCount);
        mAndroidCount=(TextView)findViewById(R.id.androidCount);
         
       
		mHumanCount.setText(Integer.toString(mHumanCounter));
		mTieCount.setText(Integer.toString(mTieCounter));
		mAndroidCount.setText(Integer.toString(mAndroidCounter));
		
		mGame= new tictac();
		startNewGame();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)   
	{
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.game_menu, menu);
	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch (item.getItemId())
    			{
		case R.id.newGame:
		startNewGame();
		break;
		
		case R.id.exitGame:
		TictacActivity.this.finish();
		break;	
    }
    	return true;
    }  

    private void startNewGame()
    {
    	mGame.clear_board();
    	
    	for(int i=0; i < mBoardButtons.length; i++)
    	{
    		mBoardButtons[i].setText(" ");
    		mBoardButtons[i].setEnabled(true);
    		mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
    	}
    	
    	if(mHumanFirst)
    	{
    		mInfoTextView.setText(R.string.first_human);
    	mHumanFirst= false;
    	}
    	else {
			mInfoTextView.setText(R.string.turn_computer);
			int move = mGame.getComputerMove();
			setMove(mGame.ANDROID_PLAYER, move);
			mHumanFirst = true;
    	}
    	mGameOver = false;
    } 	
    private class ButtonClickListener implements View.OnClickListener
    {
    	int location; 
    	public ButtonClickListener(int location)
    	{
    		this.location = location; 
    	}
    	public void onClick(View view)
    	{
   			if(!mGameOver)
   			{
    			if(mBoardButtons[location].isEnabled())
    			{
    				setMove(mGame.HUMAN_PLAYER, location);
    				
    				int winner = mGame.checkForWinner();
    				if(winner == 0)
    				{
    					mInfoTextView.setText(R.string.turn_computer);
    					int move = mGame.getComputerMove();
    					setMove(mGame.ANDROID_PLAYER, move);
    					winner = mGame.checkForWinner();
    				}
    				if(winner == 0)
    				{
    					mInfoTextView.setText(R.string.turn_human);
    				}
    				else if (winner == 1)
    				{
						mInfoTextView.setText(R.string.result_tie);
						mTieCounter++;
						mTieCount.setText(Integer.toString(mTieCounter));
						mGameOver = true;
    				}
    				else if (winner == 2)
    				{
						mInfoTextView.setText(R.string.result_human_win);
						mHumanCounter++;
						mHumanCount.setText(Integer.toString(mHumanCounter));
						mGameOver = true;
    				}
    				else
    				{
    					mInfoTextView.setText(R.string.result_android_win);
						mAndroidCounter++;
						mAndroidCount.setText(Integer.toString(mAndroidCounter));
						mGameOver = true;	
					}
    				
    			}
    		}
    	}
    }
    
    
    private void setMove(char player, int location) {
		mGame.setMove(player, location);
		mBoardButtons[location].setEnabled(false);
		mBoardButtons[location].setText(String.valueOf(player));
		
		if(player == mGame.HUMAN_PLAYER)
		{
			mBoardButtons[location].setTextColor(Color.GREEN);
		}
		else {
			mBoardButtons[location].setTextColor(Color.RED);
		}
	}

	
         
}
