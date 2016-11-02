package leitorrss.cnbatalha.leitorrss.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import leitorrss.cnbatalha.leitorrss.R;
import leitorrss.cnbatalha.leitorrss.adapter.AdapterListView;
import leitorrss.cnbatalha.leitorrss.model.Categoria;
import leitorrss.cnbatalha.leitorrss.model.Consts;
import leitorrss.cnbatalha.leitorrss.model.Item;
import leitorrss.cnbatalha.leitorrss.model.RssNoticia;
import leitorrss.cnbatalha.leitorrss.server.HttpGetAsyncTask;


public class NoticiasActivity extends Activity {

    RssNoticia rssNoticia;

    String urlNoticia;
    String noticiaTitle;

    ListView lvNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        // link com componente no activate
        lvNoticias = (ListView) findViewById(R.id.lvNoticias);

        // carregando parametros
        Bundle extras = getIntent().getExtras();
        urlNoticia = extras.getString(Consts.URL_NOTICIA);
        noticiaTitle = extras.getString(Consts.CATEGORIA_NOTICIA);

        // criando objeto de noticias
        rssNoticia = new RssNoticia();
        rssNoticia.channel.setTitle(noticiaTitle);

        // link com Adapter
        AdapterListView adapter = new AdapterListView(this,   rssNoticia.channel.getItems());
                //ArrayAdapter<Item>(this,
                //android.R.layout.simple_list_item_1, rssNoticia.channel.getItems());
        this.lvNoticias.setAdapter(adapter);

        // Comando click da Categoria selecionada
        this.lvNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int arg2, long arg3) {

                Item ctg = (Item) rssNoticia.channel.items.get(arg2);
                //        .getItemAtPosition(arg2);

                //Intent iNoticias = new Intent( NoticiasActivity.this, NoticiaActivity.class );
                //iNoticias.putExtra(Consts.TITULO, ctg.getTitle()  );
                //iNoticias.putExtra(Consts.CONTENT, ctg.getDescription());

                //startActivity(iNoticias);

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ctg.getLink()));
                startActivity(browserIntent);
            }
        });

        // thread para carregar notícias
        HttpGetAsyncTask httpGetAsyncTask = new HttpGetAsyncTask(adapter);
        httpGetAsyncTask.execute(urlNoticia);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
