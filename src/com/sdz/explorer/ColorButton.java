package com.sdz.explorer;

import com.sdz.test.R;
import com.sdz.test.R.array;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Boutton perso
 *
 */
public class ColorButton extends Button {
  /** Liste des couleurs disponibles **/
  private TypedArray mCouleurs = null;
  /** Position dans la liste des couleurs **/
  private int position = 0;

  /** Rectangle qui d�limite le dessin */
  private Rect mRect = null;
  
  /** Outil pour peindre */
  private Paint mPainter = new Paint(Paint.ANTI_ALIAS_FLAG);

  /**
   * Constructeur utilis� pour inflater avec un style
   */
  public ColorButton(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
	init();
  }

  /**
   * Constructeur utilis� pour inflater sans style
   */
  public ColorButton(Context context, AttributeSet attrs) {
	super(context, attrs);
	init();
  }

  /**
   * Constructeur utilis� pour construire dans le code
   */
  public ColorButton(Context context) {
	super(context);
	init();
  }
	
  private void init() {
	// Je r�cup�re mes ressources
	Resources res = getResources();
	// Et dans ces ressources je r�cup�re mon tableau de couleurs
	mCouleurs = res.obtainTypedArray(R.array.colors);
	
	setText("Changer de couleur");
  }

  @Override
  protected void onLayout (boolean changed, int left, int top, int right, int bottom)
  {
    //Si le layout a chang�
    if(changed)
      //On redessine un nouveau carr� en fonction des nouvelles dimensions
        mRect = new Rect(Math.round(0.5f * getWidth() - 50), 
                         Math.round(0.75f * getHeight() - 50), 
                         Math.round(0.5f * getWidth() + 50), 
                         Math.round(0.75f * getHeight() + 50));
    //Ne pas oublier
    super.onLayout(changed, left, top, right, bottom);
  }
  
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    // Uniquement si on appuie sur le bouton
    if(event.getAction() == MotionEvent.ACTION_DOWN) {
      // On passe � la couleur suivante
      position ++;
      // �vite de d�passer la taille du tableau
      // (d�s qu'on arrive � la longueur du tableau, on repasse � 0)
      position %= mCouleurs.length();
  		
      // Change la couleur du pinceau
      mPainter.setColor(mCouleurs.getColor(position, -1));
  		
      // Redessine la vue
      invalidate();
    }
    // Ne pas oublier
    return super.onTouchEvent(event);
  }
  
  @Override
  protected void onDraw(Canvas canvas) {
    // Dessine le rectangle � l'endroit voulu avec la couleur voulue
    canvas.drawRect(mRect, mPainter);
    // Ne pas oublier
    super.onDraw(canvas);
  }

}