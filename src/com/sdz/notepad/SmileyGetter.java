package com.sdz.notepad;

import com.sdz.test.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Html.ImageGetter;

/**
 * R�cup�re une image depuis les ressources
 * pour les ajouter dans l'interpr�teur HTML
 */
public class SmileyGetter implements ImageGetter {
	/* Context de notre activit� */
	protected Context context = null;
	
	public SmileyGetter(Context c) {
		context = c;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	/**
	 * Donne un smiley en fonction du param�tre d'entr�e
	 * @param smiley Le nom du smiley � afficher
	 */
	public Drawable getDrawable(String smiley) {
		Drawable retour = null;
		
		//On r�cup�re le gestionnaire de ressources
		Resources resources = context.getResources();
		
		//Si on d�sire le clin d'oeil..
		if(smiley.compareTo("clin") == 0)
			//... alors on r�cup�re le Drawable correspondant
			retour = resources.getDrawable(R.drawable.clin);
		else if(smiley.compareTo("smile") == 0)
			retour = resources.getDrawable(R.drawable.smile);
		else
			retour = resources.getDrawable(R.drawable.heureux);
		//On d�limite l'image (elle va de son coin en haut � gauche � son coin en bas � droite)
		retour.setBounds(0, 0, retour.getIntrinsicWidth(), retour.getIntrinsicHeight());
		return retour;
	}
}
