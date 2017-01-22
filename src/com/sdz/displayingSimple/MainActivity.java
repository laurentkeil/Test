package com.sdz.displayingSimple;

import com.sdz.test.R;
import com.sdz.test.R.id;
import com.sdz.test.R.layout;
import com.sdz.test.R.string;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/** Simple affichage
 * layout, widget et vue / vue perso + changement de config et recup
 *
 */
public class MainActivity extends Activity {
  private TextView texte = null;
  private String hello = null;  
  RelativeLayout layout = null;

  @Override 
  protected void onCreate(Bundle savedInstanceState) { 
      super.onCreate(savedInstanceState); //le bundle sert a enregistrer des infos pour pas qu'elles soient effac�es...
      
      /* 
      hello = getResources().getString(R.string.hello_world);
       
      // Au lieu d'afficher "Hello World!" on va afficher "Hello les Z�ros !"
      hello = hello.replace("world", "les Z�ros ");
      
      texte = new TextView(this);
      texte.setText(hello);
      
      setContentView(texte); */
      
      // On r�cup�re notre layout par d�s�rialisation. La m�thode inflate retourne un View
      // C'est pourquoi on caste (on convertit) le retour de la m�thode avec le vrai type de notre layout, c'est-�-dire RelativeLayout
      layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_main, null);
      
      // � puis on r�cup�re TextView gr�ce � son identifiant
      texte = (TextView) layout.findViewById(R.id.text); //mettre en layout pour changer la vue
      texte.setText("Le texte de notre TextView");
      texte.setTextSize(15);
      texte.setPadding(50, 60, 70, 90);
      
      EditText editText = (EditText) layout.findViewById(R.id.editText);
      editText.setHint(R.string.editText);
      editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
      editText.setLines(1);
      editText.setPadding(50, 0, 70, 30); 
      
      RadioGroup radioGroup = new RadioGroup(this);
      RadioButton radioButton1 = new RadioButton(this);
      RadioButton radioButton2 = new RadioButton(this);
      RadioButton radioButton3 = new RadioButton(this);

      // On ajoute les boutons au RadioGroup
      radioGroup.addView(radioButton1, 0);
      radioGroup.addView(radioButton2, 1);
      radioGroup.addView(radioButton3, 2);

      // On s�lectionne le premier bouton
      radioGroup.check(0);

      // On r�cup�re l'identifiant du bouton qui est coch�
      int idRadio = radioGroup.getCheckedRadioButtonId();
      
      CheckBox checkBox = (CheckBox) layout.findViewById(R.id.checkbox);
      checkBox.setText("Se souvenir");
      checkBox.setChecked(true);
      if(checkBox.isChecked()){}
        // Faire quelque chose si le bouton est coch�
      
      Button button = (Button) layout.findViewById(R.id.button);
      button.setText(R.string.button);
      button.setPadding(0, 20, 0, 20); 
      setContentView(layout); // On aurait tr�s bien pu utiliser � setContentView(R.layout.activity_main) � bien s�r !
      
      
      //DonneesConservees data = (DonneesConservees) getLastNonConfigurationInstance();
      // S'il ne s'agit pas d'un retour depuis un changement de configuration, alors data est null
      //if(data == null)
    	  
      //setContentView(new ChessBoardView(this)); //vue perso
  } 
  
  /*@Override
  public Object onRetainNonConfigurationInstance() {
    // La classe � DonneesConservees � permet de contenir tous les objets voulus
    // Et la m�thode "constituerDonnees" va construire un objet
    // En fonction de ce que devra savoir la nouvelle instance de l'activit�
    DonneesConservees data = constituerDonnees();
    return data;
  }*/
}