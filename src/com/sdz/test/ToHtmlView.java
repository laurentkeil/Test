package com.sdz.test;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
 
public class ToHtmlView extends LinearLayout {
  /** Pour insérer du texte */
  private EditText mEdit = null;
  /** Pour écrire le résultat */
  private TextView mText = null;

  /**
   * Constructeur utilisé quand on construit la vue dans le code
   * @param context
   */
  public ToHtmlView(Context context) {
    super(context);
    init();
  }

  /**
   * Constructeur utilisé quand on inflate la vue depuis le XML
   * @param context
   * @param attrs
   */
  public ToHtmlView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public void init() {
    // Paramètres utilisés pour indiquer la taille voulue pour la vue
    int wrap = LayoutParams.WRAP_CONTENT;
    int fill = LayoutParams.FILL_PARENT;

    // On veut que notre layout soit de haut en bas
    setOrientation(LinearLayout.VERTICAL);
    // Et qu'il remplisse tout le parent.
    setLayoutParams(new LayoutParams(fill, fill));

    // On construit les widgets qui sont dans notre vue
    mEdit = new EditText(getContext());
    // Le texte sera de type web et peut être long
    mEdit.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
    // Il fera au maximum dix lignes
    mEdit.setMaxLines(10);
    // On interdit le scrolling horizontal pour des questions de confort
    mEdit.setHorizontallyScrolling(false);

    // Listener qui se déclenche dès que le texte dans l'EditText change
    mEdit.addTextChangedListener(new TextWatcher() {

      // À chaque fois que le texte est édité
      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        // On change le texte en Spanned pour que les balises soient interprétées    
        mText.setText(Html.fromHtml(s.toString()));
      }

      // Après que le texte a été édité
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      // Après que le texte a été édité
      @Override
      public void afterTextChanged(Editable s) {
                
      }
    });

    mText = new TextView(getContext());
    mText.setText("");

    // Puis on rajoute les deux widgets à notre vue
    addView(mEdit, new LinearLayout.LayoutParams(fill, wrap));
    addView(mText, new LinearLayout.LayoutParams(fill, wrap));
  }
}
