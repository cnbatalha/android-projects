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

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import leitorrss.cnbatalha.leitorrss.R;
import leitorrss.cnbatalha.leitorrss.database.DataBaseHelper;
import leitorrss.cnbatalha.leitorrss.database.RssLocalData;
import leitorrss.cnbatalha.leitorrss.model.Categoria;


public class MainActivity extends Activity {

    private RssLocalData rssLocalData;
    private DataBaseHelper dataBaseHelper;

    public ArrayList<Categoria> Categories;
    public ListView lvCategorais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicializando database
        // dataBaseHelper = new DataBaseHelper(getApplicationContext());

        ActiveAndroid.initialize(this);

        //Categoria categoria = new Categoria("Categoria 03", "url");
        //categoria.save();
        //addCategoriasDefult();

        System.out.print("salvado categoria");

        setViewCategories();
    }


    private void addCategoriasDefult()
    {
        Categoria categoria = null;

        categoria = new Categoria("Esporte",
                "http://tecnologia.uol.com.br/ultnot/index.xml");
        categoria.save();
        categoria = new Categoria("Economia",
                "http://rss.uol.com.br/feed/economia.xml");
        categoria.save();
        categoria = new Categoria("Tecnologia",
                "http://tecnologia.uol.com.br/ultnot/index.xml");
        categoria.save();
        categoria = new Categoria("Economia",
                "http://rss.uol.com.br/feed/economia.xml");
        categoria.save();
        categoria = new Categoria("Cinema",
                "http://cinema.uol.com.br/ultnot/index.xml");
        categoria.save();
        categoria = new Categoria("Esporte",
                "http://esporte.uol.com.br/ultimas/index.xml");
        categoria.save();
        categoria = new Categoria("Futebol",
                "http://esporte.uol.com.br/futebol/ultimas/index.xml");
        categoria.save();
        categoria = new Categoria("Jogos",
                "http://jogos.uol.com.br/ultnot/index.xml");
        categoria.save();
        categoria = new Categoria("Estilo",
                "http://estilo.uol.com.br/ultnot/index.xml");
        categoria.save();
        categoria = new Categoria("Categoria 12",
                "http://estilo.uol.com.br/ultnot/index.xml");
        categoria.save();
        categoria = new Categoria("Estilo",
                "http://estilo.uol.com.br/ultnot/index.xml");
        categoria.save();
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
        // rssLocalData = new RssLocalData();

        // Inicializando Categorias
        List<Categoria> lista = new Select().from(Categoria.class).execute();
        // Categories = rssLocalData.getCategories();

        // link com componente no activate
        lvCategorais = (ListView) findViewById(R.id.lvCategories);

        // link com Adapter
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(this,
                android.R.layout.simple_list_item_1, lista);

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

                    Intent iNoticias = new Intent( MainActivity.this, NoticiasActivity.class );
                    // iNoticias.putExtra(URL_NOTICIA, ctg.url);
                    // iNoticias.putExtra(CATEGORIA_NOTICIA, ctg.titulo);

                    startActivity(iNoticias);

                }
            });
        }
    }

}
