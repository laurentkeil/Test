package com.sdz.displayingSimple;
import com.sdz.test.R;
import com.sdz.test.R.id;
import com.sdz.test.R.layout;
import com.sdz.test.R.menu;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Affichage simple : Dialog, datepicker et autocomplete + MENU
 *
 */
public class DialogActivity extends Activity {
  private Button bouton;
  //Variable globale, au-dessus de cette valeur c'est l'autre bo�te de dialogue qui s'exprime
  private final static int ENERVEMENT = 4;
  private int compteur = 0;
  
  private final static int ID_NORMAL_DIALOG = 0;
  private final static int ID_ENERVEE_DIALOG = 1;

  private AutoCompleteTextView complete = null; 
  
  //Notez qu'on utilise Menu.FIRST pour indiquer le premier �l�ment d'un menu
  private final static int MENU_DESACTIVER = Menu.FIRST;
  private final static int MENU_RETOUR = Menu.FIRST + 1;


  // Notre liste de mots que conna�tra l'AutoCompleteTextView 
  private static final String[] COULEUR = new String[] {
    "Bleu", "Vert", "Jaune", "Jaune canari", "Rouge", "Orange"
  };
  
  private Menu m = null;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dialog);

    // On r�cup�re l'AutoCompleteTextView d�clar� dans notre layout
    complete = (AutoCompleteTextView) findViewById(R.id.complete);
    complete.setThreshold(2);        

    // On associe un adaptateur � notre liste de couleurs�
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COULEUR);
    // � puis on indique que notre AutoCompleteTextView utilise cet adaptateur
    complete.setAdapter(adapter);
    
    bouton = (Button) findViewById(R.id.button);
    bouton.setOnClickListener(boutonClik);
    registerForContextMenu (bouton);
    
    DatePicker mDatePicker = (DatePicker) findViewById(R.id.datePicker);
    mDatePicker.updateDate(mDatePicker.getYear(), 0, 1);
    
    TimePicker mTimePicker = (TimePicker) findViewById(R.id.timePicker);
    mTimePicker.setIs24HourView(true);
    mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
      @Override
      public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(DialogActivity.this, "C'est vous qui voyez, il est donc " + String.valueOf(hourOfDay) + ":" + String.valueOf(minute), Toast.LENGTH_SHORT).show();
      }
    });
    
  }
  
  private OnClickListener boutonClik = new OnClickListener() {
    @Override
    public void onClick(View v) {
      // Tant qu'on n'a pas invoqu� la premi�re bo�te de dialogue 5 fois
      if(compteur < ENERVEMENT) {
        //on appelle la bo�te normale
        compteur ++;
        showDialog(ID_NORMAL_DIALOG);
      } else
        showDialog(ID_ENERVEE_DIALOG);
    }
  };
  
  /*
   * Appel�e qu'� la premi�re cr�ation d'une bo�te de dialogue
   * Les fois suivantes, on se contente de r�cup�rer la bo�te de dialogue d�j� cr��e�
   * Sauf si la m�thode � onPrepareDialog � modifie la bo�te de dialogue.
  */
  @Override
  public Dialog onCreateDialog (int id) {
    Dialog box = null;
    switch(id) {
    // Quand on appelle avec l'identifiant de la bo�te normale
    case ID_NORMAL_DIALOG:
      box = new Dialog(this);
      box.setTitle("Je viens tout juste de na�tre.");
      break;
      
    // Quand on appelle avec l'identifiant de la bo�te qui s'�nerve
    case ID_ENERVEE_DIALOG:
      box = new Dialog(this);
      box.setTitle("ET MOI ALORS ???");
    }
    return box;
  }
  
  @Override
  public void onPrepareDialog (int id, Dialog box) {
    if(id == ID_NORMAL_DIALOG && compteur > 1)
      box.setTitle("On est au " + compteur + "�me lancement !");
     //On ne s'int�resse pas au cas o� l'identifiant vaut 1, puisque cette bo�te affiche le m�me texte � chaque lancement
  }
  
  @Override
  public boolean onCreateOptionsMenu (Menu menu) { //actionBar est mieux de nos jours...
    super.onCreateOptionsMenu(menu);
    MenuInflater inflater = getMenuInflater();
    //R.menu.activity_dialog est l'id de notre menu
    inflater.inflate(R.menu.activity_dialog, menu);
    m = menu;
    return true;
  }

  @Override
  public boolean onOptionsItemSelected (MenuItem item)
  {
	  switch(item.getItemId())
	  {
	    case R.id.item1:
	      //Dans le Menu "m", on active tous les items dans le groupe d'identifiant "R.id.group2"
	      m.setGroupEnabled(R.id.group2, true);
	      return true;
	  }
	  return super.onOptionsItemSelected(item);
  }
 
  @Override
  public void onCreateContextMenu (ContextMenu menu, View vue, ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, vue, menuInfo);
    menu.add(Menu.NONE, MENU_DESACTIVER, Menu.NONE, "Supprimer cet �l�ment");
    menu.add(Menu.NONE, MENU_RETOUR, Menu.NONE, "Retour");
  }
  
  @Override
  public boolean onContextItemSelected (MenuItem item) {
    switch (item.getItemId()) {
      case MENU_DESACTIVER:
       // item.getMenuInfo().targetView.setEnabled(false);

      case MENU_RETOUR:
        return true;
    }
    return super.onContextItemSelected(item);
  }

}