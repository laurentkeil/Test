package com.sdz.test;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * appli avec widget, agencement et �v�nement + int�raction
 *
 */
public class ImcActivity extends Activity {
	
	  // La cha�ne de caract�res par d�faut
	  private final String defaut = "Vous devez cliquer sur le bouton � Calculer l'IMC � pour obtenir un r�sultat.";
	  // La cha�ne de caract�res de la megafonction
	  private final String megaString = "Vous faites un poids parfait !"; 
		
	  Button envoyer = null;
	  Button raz = null;
		
	  EditText poids = null;
	  EditText taille = null;
		
	  RadioGroup group = null;
		
	  TextView result = null;
		
	  CheckBox mega = null;
		
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    
		    setContentView(R.layout.imc); //Cr�ation de la vue imc
		    	
		    // On r�cup�re toutes les vues dont on a besoin
		    envoyer = (Button)findViewById(R.id.calcul);
		    	
		    raz = (Button)findViewById(R.id.raz);
		    	
		    taille = (EditText)findViewById(R.id.taille);
		    poids = (EditText)findViewById(R.id.poids);
		    	
		    mega = (CheckBox)findViewById(R.id.mega);
		    	
		    group = (RadioGroup)findViewById(R.id.radioGroup);
	
		    result = (TextView)findViewById(R.id.result);
	
		    // On attribue un listener adapt� aux vues qui en ont besoin
		    envoyer.setOnClickListener(envoyerListener);
		    raz.setOnClickListener(razListener);
		    taille.addTextChangedListener(textWatcher);
		    poids.addTextChangedListener(textWatcher);
	
		    // Solution avec des onKey
		    //taille.setOnKeyListener(modificationListener);
		    //poids.setOnKeyListener(modificationListener);
		    
		    mega.setOnClickListener(checkedListener);
	  }

	  /*
	  // Se lance � chaque fois qu'on appuie sur une touche en �tant sur un EditText
	  private OnKeyListener modificationListener = new OnKeyListener() {
		    @Override
		    public boolean onKey(View v, int keyCode, KeyEvent event) {
			      // On remet le texte � sa valeur par d�faut pour ne pas avoir de r�sultat incoh�rent
			      result.setText(defaut);
			      return false;
		    }
	  };*/

	  private TextWatcher textWatcher = new TextWatcher() {

		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
			    // On remet le texte � sa valeur par d�faut pour ne pas avoir de r�sultat incoh�rent
		    	result.setText(defaut);
		    }
				
		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count,
		      int after) {
		  
		    }
		  
		    @Override
		    public void afterTextChanged(Editable s) {
		  
		    }
	  };
		
	  // Uniquement pour le bouton "envoyer"
	  private OnClickListener envoyerListener = new OnClickListener() {
		    @Override
		    public void onClick(View v) {
			        // On r�cup�re la taille
			        String t = taille.getText().toString();
			        // On r�cup�re le poids
			        String p = poids.getText().toString();
						
			        // Puis on v�rifie que la taille est coh�rente
			        if (t.isEmpty() || p.isEmpty()) {
			        		Toast.makeText(ImcActivity.this, "Veuillez entrez une taille et un poids valide.", Toast.LENGTH_SHORT).show();			        
			        } else {
						
				        float tValue = Float.valueOf(t);
				        float pValue = Float.valueOf(p);
				        
			        	if (tValue <= 0 || pValue <= 0) {
			        		  Toast.makeText(ImcActivity.this, "H�ho, tu es un Minipouce ou quoi ?", Toast.LENGTH_SHORT).show();
			        	} else {
					          // Si l'utilisateur a indiqu� que la taille �tait en centim�tres
					          // On v�rifie que la Checkbox s�lectionn�e est la deuxi�me � l'aide de son identifiant
					          if(group.getCheckedRadioButtonId() == R.id.cm)
					        	  tValue = tValue / 100;
				
					          tValue = (float)Math.pow(tValue, 2);
					          float imc = pValue / tValue;

						      if(!mega.isChecked()) {
						    	  	result.setText("Votre IMC est " + String.valueOf(imc));
						      } else {
						    	  if(imc > 18 && imc < 27)
						    		  result.setText(megaString);
						    	  else 
								      result.setText("qu� gros porc !");
						      }
			        	}
			        }
		    }
	  };
		
	  // Listener du bouton de remise � z�ro
	  private OnClickListener razListener = new OnClickListener() {
		    @Override
		    public void onClick(View v) {
			      poids.getText().clear();
			      taille.getText().clear();
			      result.setText(defaut);
		    }
	  };
		
	  // Listener du bouton de la megafonction.
	  private OnClickListener checkedListener = new OnClickListener() {
		    @Override
		    public void onClick(View v) {
			      // On remet le texte par d�faut si c'�tait le texte de la megafonction qui �tait �crit
			      //if(!((CheckBox)v).isChecked() && result.getText().equals(megaString))
			        result.setText(defaut);
		    }
	  };
  
}