package com.sdz.notepad;

import com.sdz.test.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * TP avec widget, style, event et anim
 *
 */
public class NotepadActivity extends Activity {
	/* Recup�ration des �l�ments du GUI */
	private Button hideShow = null;
	private Slider slider = null;
	private RelativeLayout toHide = null;
	private EditText editer = null;
	private TextView text = null;
	private RadioGroup colorChooser = null;
	
	private Button bold = null;
	private Button italic = null;
	private Button underline = null;
	
	private ImageButton smile = null;
	private ImageButton heureux = null;
	private ImageButton clin = null;
	
	/* Utilis� pour planter les smileys dans le texte */
	private SmileyGetter getter = null;
	
	/* Couleur actuelle du texte */
	private String currentColor = "#000000";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        getter = new SmileyGetter(this);
        
        //On r�cup�re le bouton pour cacher/afficher le menu
        hideShow = (Button) findViewById(R.id.hideShow);
        //Puis, on recup�re la vue racine de l'application et on change sa couleur
        hideShow.getRootView().setBackgroundColor(getResources().getColor(R.color.background));
        //On rajoute un Listener sur le clic du bouton...
        hideShow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View vue) {
				//...pour afficher ou cacher le menu - on fait sliger toggle et en fonction du resultat...
				if(slider.toggle())
				{
					//Si le Slider est ouvert...
					//... on change le texte en "Cacher"
					hideShow.setText(R.string.hide);
				}else
				{
					//Sinon on met "Afficher"
					hideShow.setText(R.string.show);
				}
			}
		});

        //On r�cup�re le menu
        toHide = (RelativeLayout) findViewById(R.id.toHide);
        //On r�cup�re le layout principal
        slider = (Slider) findViewById(R.id.slider);
        //On donne le menu au layout principal
        slider.setToHide(toHide);

        //On r�cup�re le TextView qui affiche le texte final
        text = (TextView) findViewById(R.id.text);
        //On permet au TextView de d�filer
        text.setMovementMethod(new ScrollingMovementMethod());
        
        //On r�cup�re l'�diteur de texte
        editer = (EditText) findViewById(R.id.edit);
        //On ajouter un Listener sur l'appui de touches
        editer.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				//On r�cup�re la position du d�but de la s�lection dans le texte
				int cursorIndex = editer.getSelectionStart();
				//Ne r�agir qu'� l'appui sur une touche (et pas le rel�chement)
				if(event.getAction() == 0)
					//S'il s'agit d'un appui sur la touche "entr�e"
					if(keyCode == 66)
						//On ins�re une balise de retour � la ligne marche pas TODO
						editer.getText().insert(cursorIndex, "<br />");
				return true;
			}
		});
        //On ajoute un autre Listener sur le changement dans le texte cette fois
        editer.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//Le Textview interpr�te le texte dans l'�diteur en une certaine couleur
				text.setText(Html.fromHtml("<font color=\"" + currentColor + "\">" + editer.getText().toString() + "</font>", getter, null));
			}
			
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
        
        
        //On r�cup�re le RadioGroup qui g�re la couleur du texte
        colorChooser = (RadioGroup) findViewById(R.id.colors);
        //On rajoute un Listener sur le changement de RadioButton s�lectionn�
        colorChooser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				//En fonction de l'identifiant du RadioButton s�lectionn�...
				switch(checkedId)
				{
				//On change la couleur actuelle pour noir
				case R.id.black:
					currentColor = "#000000";
					break;
				//On change la couleur actuelle pour bleu
				case R.id.blue:
					currentColor = "#0022FF";
					break;
				//On change la couleur actuelle pour rouge
				case R.id.red:
					currentColor = "#FF0000";
				}
				/*
				 * On met dans l'�diteur son texte actuel
				 * pour activer le Listener de changement de texte
				 */
				editer.setText(editer.getText().toString());
			}
		});
        
        smile = (ImageButton) findViewById(R.id.smile);
        smile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//On r�cup�re la position du d�but de la s�lection dans le texte
				int selectionStart = editer.getSelectionStart();
				//Et on ins�re � cette position une balise pour afficher l'image du smiley
				editer.getText().insert(selectionStart, "<img src=\"smile\" >");
			}
		});
        
        heureux =(ImageButton) findViewById(R.id.heureux);
        heureux.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//On r�cup�re la position du d�but de la s�lection
				int selectionStart = editer.getSelectionStart();
				editer.getText().insert(selectionStart, "<img src=\"heureux\" >");
			}
		});

        clin = (ImageButton) findViewById(R.id.clin);
        clin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//On r�cup�re la position du d�but de la s�lection
				int selectionStart = editer.getSelectionStart();
				editer.getText().insert(selectionStart, "<img src=\"clin\" >");
			}
		});
        
        bold = (Button) findViewById(R.id.bold);
        bold.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View vue) { 
		        
				//On r�cup�re la position du d�but de la s�lection
				int selectionStart = editer.getSelectionStart();
				//On r�cup�re la position de la fin de la s�lection
				int selectionEnd = editer.getSelectionEnd();
				
				Editable editable = editer.getText();
				
				//Si les deux positions sont identiques (pas de s�lection de plusieurs caract�res)
				if(selectionStart == selectionEnd)
					//On ins�re les balises ouvrantes et fermantes avec rien dedans
					editable.insert(selectionStart, "<b></b>");
				else
				{
					//On met la balise avant la s�lection
					editable.insert(selectionStart, "<b>");
					//On rajoute la balise apr�s la s�lection (et les 3 caract�res de la balise <b>)
					editable.insert(selectionEnd + 3, "</b>");
				}
					
			}
		});
        
        italic = (Button) findViewById(R.id.italic);
        italic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View vue) {
				//On r�cup�re la position du d�but de la s�lection
				int selectionStart = editer.getSelectionStart();
				//On r�cup�re la position de la fin de la s�lection
				int selectionEnd = editer.getSelectionEnd();
				
				Editable editable = editer.getText();
				
				//Si les deux positions sont identiques (pas de s�lection de plusieurs caract�res)
				if(selectionStart == selectionEnd)
					//On ins�re les balises ouvrantes et fermantes avec rien dedans
					editable.insert(selectionStart, "<i></i>");
				else
				{
					//On met la balise avant la s�lection
					editable.insert(selectionStart, "<i>");
					//On rajoute la balise apr�s la s�lection (et les 3 caract�res de la balise <b>)
					editable.insert(selectionEnd + 3, "</i>");
				}
					
			}
		});
        
        underline = (Button) findViewById(R.id.underline);
        underline.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View vue) {
				//On r�cup�re la position du d�but de la s�lection
				int selectionStart = editer.getSelectionStart();
				//On r�cup�re la position de la fin de la s�lection
				int selectionEnd = editer.getSelectionEnd();
				
				Editable editable = editer.getText();
				
				//Si les deux positions sont identiques (pas de s�lection de plusieurs caract�res)
				if(selectionStart == selectionEnd)
					//On ins�re les balises ouvrantes et fermantes avec rien dedans
					editable.insert(selectionStart, "<u></u>");
				else
				{
					//On met la balise avant la s�lection
					editable.insert(selectionStart, "<u>");
					//On rajoute la balise apr�s la s�lection (et les 3 caract�res de la balise <b>)
					editable.insert(selectionEnd + 3, "</u>");
				}
					
			}
		});
    }
}