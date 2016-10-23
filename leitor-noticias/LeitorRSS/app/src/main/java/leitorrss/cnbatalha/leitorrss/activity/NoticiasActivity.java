package leitorrss.cnbatalha.leitorrss.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    private RssNoticia rssNoticia;

    private String urlNoticia;
    private String noticiaTitle;

    private ListView lvNoticias;

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


        Item item = new Item();
        item.setTitle("Teste descricao");

        // criando objeto de noticias
        rssNoticia = new RssNoticia();
        rssNoticia.channel.items.add(item);

        // Todo: Atualizar lista
        // link com Adapter
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this,
                android.R.layout.simple_list_item_1, rssNoticia.channel.getItems());
        this.lvNoticias.setAdapter(adapter);

        rssNoticia.channel.items.add(item);

        HttpGetAsyncTask httpGetAsyncTask = new HttpGetAsyncTask(rssNoticia);
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
