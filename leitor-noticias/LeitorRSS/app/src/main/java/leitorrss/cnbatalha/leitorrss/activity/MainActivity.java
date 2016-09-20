package leitorrss.cnbatalha.leitorrss.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import leitorrss.cnbatalha.leitorrss.R;
import leitorrss.cnbatalha.leitorrss.database.RssLocalData;
import leitorrss.cnbatalha.leitorrss.model.Categoria;


public class MainActivity extends Activity {

    private RssLocalData rssLocalData;

    public ArrayList<Categoria> Categories;
    public ListView lvCategorais;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    private void setViewCategories() {

        // carregando Dados do APP
        rssLocalData = new RssLocalData();

        // Inicializando Categorias
        Categories = rssLocalData.getCategories();

        // link com componente no activate
        lvCategorais = (ListView) findViewById(R.id.lvCategories);

        // link com Adapter
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(this,
                android.R.layout.simple_list_item_1, Categories);

        if (lvCategorais != null) {

            // setando conteudo
            lvCategorais.setAdapter(adapter);

            // Comando click da Categoria selecionada
            lvCategorais.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int arg2, long arg3) {

                    Categoria ctg = (Categoria) lvCategorais
                            .getItemAtPosition(arg2);

                    // Intent iNoticias = new Intent( LeitorRSS.this, NoticiasActivity.class );
                    // iNoticias.putExtra(URL_NOTICIA, ctg.url);
                    // iNoticias.putExtra(CATEGORIA_NOTICIA, ctg.titulo);

                    // startActivity(iNoticias);

                }
            });
        }
    }

}
