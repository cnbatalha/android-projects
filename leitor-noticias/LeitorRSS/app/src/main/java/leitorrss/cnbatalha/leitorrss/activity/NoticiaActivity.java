package leitorrss.cnbatalha.leitorrss.activity;

import android.os.Bundle;
import android.app.Activity;

import leitorrss.cnbatalha.leitorrss.R;

public class NoticiaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
