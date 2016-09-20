package leitorrss.cnbatalha.leitorrss.database;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.ArrayList;

import leitorrss.cnbatalha.leitorrss.model.Categoria;

/**
 * Created by Carlos on 05/11/2014.
 */
public class RssLocalData {

    private ArrayList<Categoria> Categorias;

    public RssLocalData() {
        super();

        // abastecendo categorias
        loadCategories();
    }

    public void getItens(String loXML) {

    }

    private void loadCategories() {
        this.Categorias = new ArrayList<Categoria>();

        Categorias.add(new Categoria("Esporte",
                "http://tecnologia.uol.com.br/ultnot/index.xml"));
        Categorias.add(new Categoria("Economia",
                "http://rss.uol.com.br/feed/economia.xml"));
        Categorias.add(new Categoria("Tecnologia",
                "http://tecnologia.uol.com.br/ultnot/index.xml"));
        Categorias.add(new Categoria("Economia",
                "http://rss.uol.com.br/feed/economia.xml"));
        Categorias.add(new Categoria("Cinema",
                "http://cinema.uol.com.br/ultnot/index.xml"));
        Categorias.add(new Categoria("Esporte",
                "http://esporte.uol.com.br/ultimas/index.xml"));
        Categorias.add(new Categoria("Futebol",
                "http://esporte.uol.com.br/futebol/ultimas/index.xml"));
        Categorias.add(new Categoria("Jogos",
                "http://jogos.uol.com.br/ultnot/index.xml"));
        Categorias.add(new Categoria("Estilo",
                "http://estilo.uol.com.br/ultnot/index.xml"));
    }

    /**
     * @return the categorias
     */
    public ArrayList<Categoria> getCategories() {
        return Categorias;
    }

    public String getFeeds(Context ctx, int indexCategoria) {

		/* Verificando conexao com internet */
        if (testaConexao(ctx)) {

            String resp = null;

            // Categoria selecionada
            Categoria ctg = Categorias.get(indexCategoria);

            // criando URI com URL da categoria selecionada
            HttpGet uri = new HttpGet(ctg.url);

            HttpClient client = new DefaultHttpClient();
            try {

                // ResponseHandler<String> responseHandler = new
                // BasicResponseHandler();
                HttpResponse response = client.execute(uri);
                // resp = client.execute(uri, responseHandler);

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.d("error", e.getLocalizedMessage());
                e.printStackTrace();
            }

            return resp;

        } else {
            Toast.makeText(ctx, "Sem conex�o", Toast.LENGTH_LONG).show();
            return "";
        }

    }

    protected boolean testaConexao(Context ctx) {
        boolean retorno = true;

        try {

            ConnectivityManager cm = (ConnectivityManager) ctx
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            if (!cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                if (!cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                        .isConnected()) {
                    return false;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
            // Toast.makeText(LeitorRSS.this, e.getMessage(),
            // Toast.LENGTH_SHORT).show();
            return false;
        }

        return retorno;
    }

}
