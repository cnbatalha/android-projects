package com.example.carlos.agenda;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.agenda.database.DatabaseHelper;
import com.agenda.database.DbInstance;
import com.agenda.model.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class AgendaPrincipal extends ListActivity {

    //public static final String PATH_HOST =  "http://192.168.0.66:8000/";
    public static final String PATH_HOST = "http://172.30.17.74:8000/";
    // Elemento Lista
    public static ListView lvContatos = null;
    public List<Contact> contactList;
    private SQLiteDatabase mDB = null;
    private DatabaseHelper mDbHelper;
    private SimpleCursorAdapter mAdapter;
    private Cursor crConatos;
    private WebClientContact wbCliente;
    private TextView footerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_agenda_principal);

        // ActionBar actionBar = getActionBar();
        // actionBar.show();

        getListView().setFooterDividersEnabled(true);

        LayoutInflater lInflate = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        footerView = (TextView) lInflate.inflate(R.layout.footer, null);

        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgendaPrincipal.this, ContatoActivity.class);
                i.putExtra(Contact.FLD_ID, -1);
                startActivityForResult(i, 1);
            }
        });

        getListView().addFooterView(footerView);

        // Criando Manipulador de Banco de Dados
        // mDbHelper =  new DatabaseHelper(this);
        DbInstance.InicializaDb(this);
        mDbHelper = DbInstance.getDbInstance();

        // carregando instancia do banco de dados
        mDB = mDbHelper.getReadableDatabase();

        // addContacts();

        // reloadContacts();

        wbCliente = new WebClientContact();
        //wbCliente.execute("http://172.30.20.20:8000/agenda/contatos/");
        wbCliente.execute(PATH_HOST + "agenda/contatos/");

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent i = new Intent(AgendaPrincipal.this, ContatoActivity.class);
        /* Retirando Bando de Dados
        i.putExtra(Contact.FLD_URL_PHOTO, crConatos.getString(4));
        i.putExtra(Contact.FLD_EMAIL, crConatos.getString(3));
        i.putExtra(Contact.FLD_PHONE, crConatos.getString(2));
        i.putExtra(Contact.FLD_NAME, crConatos.getString(1));
        i.putExtra(Contact.FLD_ID, crConatos.getInt(0));*/

        i.putExtra(Contact.FLD_URL_PHOTO, contactList.get(position).getPhoto());
        i.putExtra(Contact.FLD_EMAIL, contactList.get(position).getEmail());
        i.putExtra(Contact.FLD_PHONE, contactList.get(position).getTelefone());
        i.putExtra(Contact.FLD_NAME, contactList.get(position).getNome());
        i.putExtra(Contact.FLD_ID, contactList.get(position).getId());

        startActivityForResult(i, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            reloadContacts();
        }

    }

    protected Contact getItem() {
        Contact ct = new Contact();

        ct.setNome(crConatos.getString(1));

        return ct;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.agenda_principal, menu);
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


    // alterar para um padrao de projeto
    private Cursor readContacts() {
        return mDB.query(Contact.TABLE_NAME, Contact.columns, null, new String[]{}, null, null, null);

    }

    private void reloadContacts() {
        // carregando lista de contatos
        crConatos = readContacts();

        mAdapter = new SimpleCursorAdapter(this, R.layout.list_layout, crConatos, new String[]{Contact.FLD_ID, Contact.FLD_NAME}, new int[]{R.id.lvTxId, R.id.lvTxName});

        setListAdapter(mAdapter);
    }

    private void addContacts() {
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.CONTACT_NAME, "Aldair ");
        mDB.insert(DatabaseHelper.TABLE_NAME, null, values);

        values.clear();

        values.put(DatabaseHelper.CONTACT_NAME, "Diego");
        mDB.insert(DatabaseHelper.TABLE_NAME, null, values);

    }

    public class WebClientContact extends AsyncTask<String, Void, List<Contact>> {


        @Override
        protected void onPostExecute(List<Contact> contacts) {
            super.onPostExecute(contacts);

            AdapterContato adapterContato = new AdapterContato(AgendaPrincipal.this, contactList);

            setListAdapter(adapterContato);
        }

        @Override
        protected List<Contact> doInBackground(String... params) {

            try {

                return download(params[0]);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }


        private List<Contact> download(String urlWs) throws IOException {
            InputStream inputStream = null;
            BufferedReader bf;

            URL url = new URL(urlWs);

            try {
                inputStream = url.openStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            bf = new BufferedReader(new InputStreamReader(inputStream));

            String readLine;
            StringBuffer buf = new StringBuffer();

            while ((readLine = bf.readLine()) != null) {
                buf.append(readLine);
            }

            // readLine = "['lista':'" + buf + "']";

            contactList = new ArrayList<Contact>();

            // jSon to Contact
            try {

                //JSONObject jlista = new JSONObject(readLine);
                JSONArray jsonArray = new JSONArray(buf.substring(0));

                for (int index = 0; index < jsonArray.length(); index++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(index);

                    Contact contact = new Contact();
                    contact.setId(jsonObject.getString("id"));
                    contact.setNome(jsonObject.getString("name"));
                    contact.setTelefone(jsonObject.getString("fone"));
                    contact.setEmail(jsonObject.getString("email"));
                    contact.setPhoto(jsonObject.getString("photo"));

                    contactList.add(contact);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            if (null != bf) {
                bf.close();
            }

            return contactList;
        }
    }


}
