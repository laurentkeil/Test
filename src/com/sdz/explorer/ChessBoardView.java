package com.sdz.explorer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;


/**
 * Vue perso
 *
 */
public class ChessBoardView extends View {
	
	/** Pour la premi�re couleur */
	private Paint mPaintOne = null;
	/** Pour la seconde couleur */
	private Paint mPaintTwo = null;

	public ChessBoardView(Context context) {
	  super(context);
	  init(-1, -1);
	}
		
	public ChessBoardView(Context context, int one, int two) {
	  super(context);
	  init(one, two);
	}

	private void init(int one, int two) {
	  mPaintTwo = new Paint(Paint.ANTI_ALIAS_FLAG);
	  if(one == -1)
	    mPaintTwo.setColor(Color.LTGRAY);
	  else
	    mPaintTwo.setColor(one);
			
	  mPaintOne = new Paint(Paint.ANTI_ALIAS_FLAG);
	  if(two == -1)
	    mPaintOne.setColor(Color.WHITE);
	  else
	    mPaintOne.setColor(two);
	}

	/**
	 * Calcule la bonne mesure sur un axe uniquement
	 * @param spec - Mesure sur un axe
	 * @param screenDim - Dimension de l'�cran sur cet axe
	 * @return la bonne taille sur cet axe
	 */
	private int singleMeasure(int spec, int screenDim) {
	  int mode = MeasureSpec.getMode(spec);
	  int size = MeasureSpec.getSize(spec);
		
	  // Si le layout n'a pas pr�cis� de dimensions, la vue prendra la moiti� de l'�cran
	  if(mode == MeasureSpec.UNSPECIFIED)
	    return screenDim/2;
	  else
	    // Sinon, elle prendra la taille demand�e par le layout
	    return size;
	}

	@Override
	protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
	  // On r�cup�re les dimensions de l'�cran
	  DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
	  // Sa largeur�
	  int screenWidth = metrics.widthPixels;
	  // � et sa hauteur
	  int screenHeight = metrics.heightPixels;
			
	  int retourWidth = singleMeasure(widthMeasureSpec, screenWidth);
	  int retourHeight = singleMeasure(heightMeasureSpec, screenHeight);
			
	  // Comme on veut un carr�, on n'aura qu'une taille pour les deux axes, la plus petite possible
	  int retour = Math.min(retourWidth, retourHeight);
			
	  setMeasuredDimension(retour, retour);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
	  // Largeur de la vue
	  int width = getWidth();
	  // Hauteur de la vue
	  int height = getHeight();
			
	  int step = 0, min = 0;
	  // La taille minimale entre la largeur et la hauteur
	  min = Math.min(width, height);
		
	  // Comme on ne veut que 8 cases par ligne et 8 lignes, on divise la taille par 8
	  step = min / 8;
		
	  // D�termine quand on doit changer la couleur entre deux cases
	  boolean switchColor = true;
	  for(int i = 0 ; i < min ; i += step) {
	    for(int j = 0 ; j < min ; j += step) {
	      if(switchColor)
	        canvas.drawRect(i, j, i + step, j + step, mPaintTwo);
	      else
	        canvas.drawRect(i, j, i + step, j + step, mPaintOne);
	      // On change de couleur � chaque ligne�
	      switchColor = !switchColor;
	    }
	    // � et � chaque case
	    switchColor = !switchColor;
	  }
	}
	
}
