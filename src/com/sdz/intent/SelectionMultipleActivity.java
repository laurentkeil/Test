package com.sdz.intent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sdz.test.PreferenceActivityExample;
import com.sdz.test.R;
import com.sdz.test.R.id;
import com.sdz.test.R.layout;
import com.sdz.test.R.menu;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * liste et adapter + INTET - passage a autre activit� ! + menu et pref
 *
 */
public class SelectionMultipleActivity extends Activity {
  /** Affichage de la liste des sexes **/
  private ListView mListSexe = null;
  /** Affichage de la liste des langages connus **/
  private ListView mListProg = null;
  /** Bouton pour envoyer le sondage **/
  private Button mSend = null;
  private Button mSend1 = null;
  private Button mSend2 = null;
  private Button mSend3 = null;
  
  /** Contient les deux sexes **/
  private String[] mSexes = {"Masculin", "Feminin"};
  /** Contient diff�rents langages de programmation **/
  private String[] mLangages = null;
  
  public final static String AGE = "com.sdz.intent.example.AGE"; //� passer dans intent

  // L'identifiant de notre requ�te
  public final static int CHOOSE_BUTTON_REQUEST = 0;
  // L'identifiant de la cha�ne de caract�res qui contient le r�sultat de l'intent
  public final static String BUTTONS = "com.sdz.intent.example.Boutons";
  
  private Menu m = null;

  private String PRENOM = "prenom.txt";
  private String userName = "Sniperx21x";
  private File mFile = null;
  
  private Button mWrite = null;
  private Button mRead = null;

  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.selection_multiple);
    
    //On r�cup�re les trois vues d�finies dans notre layout
    mListSexe = (ListView) findViewById(R.id.listSexe);
    mListProg = (ListView) findViewById(R.id.listProg);
    mSend = (Button) findViewById(R.id.send);
    
    //Une autre mani�re de cr�er un tableau de cha�nes de caract�res
    mLangages = new String[]{"C", "Java", "COBOL", "Perl"};
    
    //On ajoute un adaptateur qui affiche des boutons radio (c'est l'affichage � consid�rer quand on ne peut
    //s�lectionner qu'un �l�ment d'une liste)
    mListSexe.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mSexes));
    //On d�clare qu'on s�lectionne de base le premier �l�ment (Masculin)
    mListSexe.setItemChecked(0, true);
    
    //On ajoute un adaptateur qui affiche des cases � cocher (c'est l'affichage � consid�rer quand on peut s�lectionner
    //autant d'�l�ments qu'on veut dans une liste)
    mListProg.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, mLangages));
    //On d�clare qu'on s�lectionne de base le second �l�ment (F�minin)
    mListProg.setItemChecked(1, true);
    
    //Que se passe-t-il d�s qu'on clique sur le bouton ?
    mSend.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        Toast.makeText(SelectionMultipleActivity.this, "Merci ! Les donn�es ont �t� envoy�es !", Toast.LENGTH_LONG).show();
        
        //On d�clare qu'on ne peut plus s�lectionner d'�l�ment
        mListSexe.setChoiceMode(ListView.CHOICE_MODE_NONE);
        //On affiche un layout qui ne permet pas de s�lection
        mListSexe.setAdapter(new ArrayAdapter<String>(SelectionMultipleActivity.this, android.R.layout.simple_list_item_1, 
                      mSexes));
        
        //On d�clare qu'on ne peut plus s�lectionner d'�l�ment
        mListProg.setChoiceMode(ListView.CHOICE_MODE_NONE);
        //On affiche un layout qui ne permet pas de s�lection
        mListProg.setAdapter(new ArrayAdapter<String>(SelectionMultipleActivity.this, android.R.layout.simple_list_item_1, mLangages));
        
        //On d�sactive le bouton
        mSend.setEnabled(false);
      }
    });
    
    //acces a autre acti sans retour
    mSend1 = (Button) findViewById(R.id.send1);
    mSend1.setOnClickListener(new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {

          // Le premier param�tre est le nom de l'activit� actuelle
          // Le second est le nom de l'activit� de destination
          Intent afficherSelectionActivity = new Intent(SelectionMultipleActivity.this, AfficherSelectionActivity.class);
          
          // On rajoute un extra
          afficherSelectionActivity.putExtra(AGE, 31);

          // Puis on lance l'intent ! (sans retour)
          startActivity(afficherSelectionActivity);
          
        }
      });

    //acces a autre acti avec retour
    mSend2 = (Button) findViewById(R.id.send2);
    mSend2.setOnClickListener(new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {

          // Le premier param�tre est le nom de l'activit� actuelle
          // Le second est le nom de l'activit� de destination
          Intent afficherSelectionActivity = new Intent(SelectionMultipleActivity.this, AfficherSelectionActivity.class);       

          // On associe l'identifiant � notre intent (avec retour)
          startActivityForResult(afficherSelectionActivity, CHOOSE_BUTTON_REQUEST);
          
        }
      });

    //acces a autre acti (implicite) donc pas de l appli
    mSend3 = (Button) findViewById(R.id.send3);
    mSend3.setOnClickListener(new View.OnClickListener() {
    	  @Override
    	  public void onClick(View v) {
    	    Uri telephone = Uri.parse("tel:0606060606");
    	    Intent secondeActivite = new Intent(Intent.ACTION_DIAL, telephone);

			PackageManager manager = getPackageManager(); //manager qui permet de savoir si on peut g�rer le composant telephone
			
			ComponentName component = secondeActivite.resolveActivity(manager);
			
			// On v�rifie que component n'est pas null
			if(component != null)
			  //Alors c'est qu'il y a une activit� qui va g�rer l'intent
				startActivity(secondeActivite);
    	  }
    	});
    
    // On cr�e un fichier qui correspond � l'emplacement ext�rieur
    mFile = new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/ " + getPackageName() + "/files/" + PRENOM);
    
    mWrite = (Button) findViewById(R.id.write);
    mWrite.setOnClickListener(new View.OnClickListener() {
      
      public void onClick(View pView) {
        try {
          // Flux interne
          FileOutputStream output = openFileOutput(PRENOM, MODE_PRIVATE);
          
          // On �crit dans le flux interne
          output.write(userName.getBytes());
          
          if(output != null)
            output.close();
          
          // Si le fichier est lisible et qu'on peut �crire dedans
          if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
              && !Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())) {
            // On cr�e un nouveau fichier. Si le fichier existe d�j�, il ne sera pas cr��
            mFile.createNewFile();
            output = new FileOutputStream(mFile);
            output.write(userName.getBytes());
            if(output != null)
              output.close();
          }
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    
    mRead = (Button) findViewById(R.id.read);
    mRead.setOnClickListener(new View.OnClickListener() {
      
      public void onClick(View pView) {
        try {
          FileInputStream input = openFileInput(PRENOM);
          int value;
          // On utilise un StringBuffer pour construire la cha�ne au fur et � mesure
          StringBuffer lu = new StringBuffer();
          // On lit les caract�res les uns apr�s les autres
          while((value = input.read()) != -1) {
            // On �crit dans le fichier le caract�re lu
            lu.append((char)value);
          }
          Toast.makeText(SelectionMultipleActivity.this, "Interne : " + lu.toString(), Toast.LENGTH_SHORT).show();
          if(input != null)
            input.close();
          
          if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            lu = new StringBuffer();
            input = new FileInputStream(mFile);
            while((value = input.read()) != -1)
              lu.append((char)value);
            
            Toast.makeText(SelectionMultipleActivity.this, "Externe : " + lu.toString(), Toast.LENGTH_SHORT).show();
            if(input != null)
              input.close();
          }
            
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    
  }
  
  //retour d'activité
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // On v�rifie tout d'abord � quel intent on fait r�f�rence ici � l'aide de notre identifiant
    if (requestCode == CHOOSE_BUTTON_REQUEST) {
      // On v�rifie aussi que l'op�ration s'est bien d�roul�e
      if (resultCode == RESULT_OK) {
        // On affiche le bouton qui a �t� choisi
      	Toast.makeText(this, "Vous avez choisi le bouton " + data.getStringExtra(BUTTONS), Toast.LENGTH_SHORT).show();
      }
    }
  }
  

  @Override
  public boolean onCreateOptionsMenu (Menu menu) {
    super.onCreateOptionsMenu(menu);
    MenuInflater inflater = getMenuInflater();
    //R.menu.selection_multiple est l'id de notre menu
    inflater.inflate(R.menu.selection_multiple, menu);
    m = menu;
    return true;
  }

  @Override
  public boolean onOptionsItemSelected (MenuItem item)
  {
	  switch(item.getItemId())
	  {
	    case R.id.pref:
	          // Le second est le nom de l'activit� de destination
	          Intent preferenceActivityExample = new Intent(SelectionMultipleActivity.this, PreferenceActivityExample.class);
	          // Puis on lance l'intent ! (sans retour)
	          startActivity(preferenceActivityExample);
	      return true;
	  }
	  return super.onOptionsItemSelected(item);
  }
  
}