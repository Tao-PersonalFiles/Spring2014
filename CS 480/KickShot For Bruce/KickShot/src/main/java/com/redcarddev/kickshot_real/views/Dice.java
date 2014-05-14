package com.redcarddev.kickshot_real.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.redcarddev.kickshot_real.R;

public class Dice {
	
	public final static int ONE = 1;
	public final static int TWO = 2;
	public final static int THREE = 3;
	public final static int FOUR = 4;
	public final static int FIVE = 5;
	public final static int SIX = 6;
	
	protected Context context = null;
	
	protected Bitmap face = null;
	protected int currentDiceFace = 0;
	
	public Dice(Context context) {
		this.context = context;
		
		this.setDiceFace(Dice.ONE);
	}
	
	public void setDiceFace(int face) {
		
		switch (face) {
		
			case Dice.ONE:
				currentDiceFace = R.drawable.dice1;
				break;
			case Dice.TWO:
				currentDiceFace = R.drawable.dice2;
				break;
			case Dice.THREE:
				currentDiceFace = R.drawable.dice3;
				break;
			case Dice.FOUR:
				currentDiceFace = R.drawable.dice4;
				break;
			case Dice.FIVE:
				currentDiceFace = R.drawable.dice5;
				break;
			case Dice.SIX:
				currentDiceFace = R.drawable.dice6;
				break;
		}
		
		Resources res = context.getResources();
		
		this.face = BitmapFactory.decodeResource(res, this.currentDiceFace);
		
	}
	
	/**
	 * The current dice face
	 * @return
	 */
	public Bitmap getCurrent() {
		return this.face;
	}
	
	

}
