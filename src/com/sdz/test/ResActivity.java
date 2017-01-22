package com.sdz.test;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * Ressources et animation
 *
 */
public class ResActivity extends Activity {
  Button button = null;
  String hist = null;
    
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.test);
    
    // On r�cup�re notre ressource au format String
    hist = getResources().getString(R.string.histoire);
    // On le convertit en Spanned
    Spanned marked_up = Html.fromHtml(hist);

    Button button = (Button)findViewById(R.id.button);
    // Et on attribue le Spanned au bouton
    button.setText(marked_up);
    
    Resources res = getResources();
    // Ana�s se mettra dans %1 et 22 ira dans %2, mais le reste changera en fonction de la langue du terminal !
    String chaine = res.getString(R.string.welcome, "Ana�s", 22);
    TextView vue = (TextView)findViewById(R.id.vue);
    vue.setText(chaine);
    
    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim1);
    /*animation.setAnimationListener(new AnimationListener() {
    	  public void onAnimationEnd(Animation _animation) {
    	    // Que faire quand l'animation se termine ? (n'est pas lanc� � la fin d'une r�p�tition)
    	  }

    	  public void onAnimationRepeat(Animation _animation) {
    	    // Que faire quand l'animation se r�p�te ?
    	  }

    	  public void onAnimationStart(Animation _animation) {
    	    // Que faire au premier lancement de l'animation ?
    	  }
    });*/
    button.startAnimation(animation);
    
  }
}