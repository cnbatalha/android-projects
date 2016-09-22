package com.example.carlos.agenda;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.agenda.model.Contact;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ContatoActivity extends Activity {

    public static final String pNOME = "NOME";
    //public static final String pNOME = "TELEFONE";
    //public static final String pNOME = "EMAIL";
    static String uriFoto;
    private EditText edNome;
    private EditText edFone;
    private EditText edEmail;
    private ImageView ivFoto;
    // private Uri camUri;
    private Uri imagePath;
    private int idContato;
    private Button btnSalvar;

    private boolean modAdd = false;

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "AgendaUEA");

        if (!mediaStorageDir.exists()) {
            if (mediaStorageDir.mkdirs()) {
                try {
                    throw new Exception("Diretorio inexistente");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());

        File mediaFile;

        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        uriFoto = new String();

        edNome = (EditText) findViewById(R.id.etNome);
        edFone = (EditText) findViewById(R.id.etFone);
        edEmail = (EditText) findViewById(R.id.etEmail);
        ivFoto = (ImageView) findViewById(R.id.ivFoto);

        //btnSalvar = (Button) findViewById(R.id.btnSalvar);

        Intent i = getIntent();
        String nome = i.getStringExtra(Contact.FLD_NAME);
        idContato = i.getIntExtra(Contact.FLD_ID, 0);

        if (idContato == -1) {
            modAdd = true;
            idContato = 0;
        }


        edNome.setText(nome);
        edFone.setText(i.getStringExtra(Contact.FLD_PHONE));
        edEmail.setText(i.getStringExtra(Contact.FLD_EMAIL));
        uriFoto = AgendaPrincipal.PATH_HOST + "media/" + i.getStringExtra(Contact.FLD_URL_PHOTO);

        if (i.getStringExtra(Contact.FLD_URL_PHOTO) != null) {
            // ivFoto.setImageURI(Uri.parse(uriFoto));
            DownloadImageTask downloadImageTask = new DownloadImageTask(ivFoto);
            downloadImageTask.execute(uriFoto);
        }
    }

    public void onClickSalvar(View v) {
        Intent resultIntent = new Intent();

        boolean result = false;

        String nome = edNome.getText().toString();
        resultIntent.putExtra("NOME", nome);

        try {
            send(v);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Agenda", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Agenda", e.getMessage());
        }

        /*SQLiteDatabase mDb = DbInstance.getDbInstance().getWritableDatabase();


        ContentValues args = new ContentValues();
        // args.put(Contact.FLD_ID, idContato);
        args.put(Contact.FLD_NAME, nome);
        args.put(Contact.FLD_PHONE, edFone.getText().toString());
        args.put(Contact.FLD_EMAIL, edEmail.getText().toString());
        args.put(Contact.FLD_URL_PHOTO, uriFoto);

        boolean result = false;

        if ( modAdd )
        {
            result = mDb.insert(Contact.TABLE_NAME, null, args) > 0;
        }
        else
         {
             result = mDb.update(Contact.TABLE_NAME, args, Contact.FLD_ID + " = " + idContato, null) > 0;
        }*/

        setResult((result ? Activity.RESULT_OK : Activity.RESULT_CANCELED), resultIntent);

        finish();
    }

    // Click Button call camera
    public void onClickFoto(View v) {
        // Create Intent to Capture Image
        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        new Intent( MediaStore.INTENT_ACTION_MEDIA_SEARCH);

        File fl = getOutputMediaFile();
        Uri camUri = Uri.fromFile(fl);
        imagePath = camUri;

        //uriFoto = "";
        uriFoto = camUri.toString();

        camIntent.putExtra(MediaStore.EXTRA_OUTPUT, camUri);
        ///this.uriFoto = camUri.getPath().toString();

        startActivityForResult(camIntent, 1);

        ivFoto.setImageURI(imagePath);
    }

    public void onClickVoltar(View v) {
        setResult(Activity.RESULT_CANCELED);

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.contato, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(Gallery.LayoutParams.FILL_PARENT, Gallery.LayoutParams.FILL_PARENT);
        ivFoto.setLayoutParams(vp);
        ivFoto.setMaxHeight(100);
        ivFoto.setMaxWidth(100);
        ivFoto.setImageURI(null);
        ivFoto.setImageURI(Uri.parse(uriFoto));


        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    private void send(View v) throws JSONException, IOException {
        InputStream inputStream = null;

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(AgendaPrincipal.PATH_HOST + "agenda/contatos/");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", edNome.getText().toString());
        jsonObject.put("fone", edFone.getText().toString());
        jsonObject.put("email", edEmail.getText().toString());
        jsonObject.put("photo", "");

        String json = jsonObject.toString();

        StringEntity se = new StringEntity(json);
        //se.setContentType("application/json");

        // 7. Set some headers to inform server about the type of the content   
        httppost.setHeader("Accept", "application/json");
        httppost.setHeader("Content-type", "application/json");
        httppost.setEntity(se);

        HttpResponse httpResponse = httpclient.execute(httppost);

        inputStream = httpResponse.getEntity().getContent();

        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));

        /*String readLine;
        StringBuffer buf = new StringBuffer();
        boolean body =  false;

        while ((readLine = bf.readLine()) != null)
        {
            if ( body )
            {
                buf.append(readLine);
            }
            else {
                body = (readLine.indexOf("<body>") != -1);
            }

        }



        //Log.e("Agenda",buf.substring(0)); */

        //HttpPost httpPostFoto = new HttpPost(AgendaPrincipal.PATH_HOST +  "agenda/postimage/");

        //httpPostFoto.setEntity( new FileEntity( new File(uriFoto), "plain/text"));

        //httpclient.execute(httpPostFoto);


        // send photo


    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            if (bmImage != null) {
                this.bmImage = bmImage;
            }
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
