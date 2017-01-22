package com.sdz.intent;

import com.sdz.test.R;
import com.sdz.test.R.id;
import com.sdz.test.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AfficherSelectionActivity extends Activity {

	private Button mButton1 = null;
	private Button mButton2 = null;
	  
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_afficher_selection);

	    // On r�cup�re l'intent qui a lanc� cette activit�
	    Intent i = getIntent();

	    // Puis on r�cup�re l'�ge donn� dans l'autre activit�, ou 0 si cet extra n'est pas dans l'intent
	    int age = i.getIntExtra(SelectionMultipleActivity.AGE, 0);

	    // S'il ne s'agit pas de l'�ge par d�faut
	    if(age != 0)
	      // Traiter l'�ge
	      age = 2;
	    

	    mButton1 = (Button) findViewById(R.id.button1);
	    mButton1.setOnClickListener(new View.OnClickListener() {
	      @Override
	      public void onClick(View v) {
	        Intent result = new Intent();
	        result.putExtra(SelectionMultipleActivity.BUTTONS, "1");
	        setResult(RESULT_OK, result);
	        finish();
	      }
	    });
	    
	    mButton2 = (Button) findViewById(R.id.button2);
	    mButton2.setOnClickListener(new View.OnClickListener() {
	      @Override
	      public void onClick(View v) {
	        Intent result = new Intent();
	        result.putExtra(SelectionMultipleActivity.BUTTONS, "2");
	        setResult(RESULT_OK, result);
	        finish();
	      }
	    });
	    
	  }
}
