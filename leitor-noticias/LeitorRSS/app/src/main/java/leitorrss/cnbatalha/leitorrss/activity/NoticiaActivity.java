package leitorrss.cnbatalha.leitorrss.activity;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import leitorrss.cnbatalha.leitorrss.R;
import leitorrss.cnbatalha.leitorrss.model.Consts;

public class NoticiaActivity extends Activity {

    String title;
    String content;

    TextView tvTitle;
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // carregando parametros
        Bundle extras = getIntent().getExtras();
        title = extras.getString(Consts.TITULO);
        content = extras.getString(Consts.CONTENT);

        // carregando edits
        tvTitle = (TextView) findViewById( R.id.tvTitleNews);
        tvContent = (TextView) findViewById( R.id.tvContent);

        // abastecendo
        tvTitle.setText(title);
        tvContent.setText(content);


    }

}
