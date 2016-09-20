package leitorrss.cnbatalha.leitorrss.server;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.thoughtworks.xstream.XStream;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import leitorrss.cnbatalha.leitorrss.adapter.AdapterListView;
import leitorrss.cnbatalha.leitorrss.model.Item;
import leitorrss.cnbatalha.leitorrss.model.RssNoticia;

public class HttpGetAsyncTask extends AsyncTask<String, Integer, RssNoticia> {

	
	private ProgressDialog progressDlg;
	private Context context;

	@Override
	protected void onPreExecute() {
		
		try {

			progressDlg = ProgressDialog.show(context, "Download", "Atualizando Noticias");
			
		} catch (Exception e) {
			 
		}	
			
		super.onPreExecute();
	}	
	
	@Override
	protected RssNoticia doInBackground(String... urls) {
	
		// TODO Auto-generated method stub
		String url = urls[0];
		String data = "";

		RssNoticia rss= null;
		
		HttpURLConnection httpUrlConnection = null;

		try {

			httpUrlConnection = (HttpURLConnection) new URL(url)
					.openConnection();

			InputStream in = new BufferedInputStream(
					httpUrlConnection.getInputStream());

			data = readStream(in);

		} catch (MalformedURLException exception) {
			Log.e("LeitorRSS", "MalformedURLException");
		} catch (IOException exception) {
			Log.e("LeitorRSS", "IOException");
		} finally {
			if (null != httpUrlConnection)
				httpUrlConnection.disconnect();
		}

		// processando lista de noticias
		XStream xstream = new XStream();
		xstream.processAnnotations(RssNoticia.class);
		xstream.autodetectAnnotations(false);

		try {

			String xmlStream = data;
			rss = (RssNoticia) xstream.fromXML(xmlStream);

			for (Item i : rss.channel.items) {
				while (i.title.contains("\t"))
					i.title = i.title.replace("\t", "");

				while (i.title.contains("\n"))
					i.title = i.title.replace("\n", "");
			}


		} catch (Exception ex) {
			System.out.print("Serialization Read Error : " + ex.getMessage());
			ex.printStackTrace();
		} 		
		
		return rss;
	}
	
	@Override
	protected void onPostExecute(RssNoticia result) {
	
		AdapterListView adp = new AdapterListView( NoticiasActivity.noticiaActivityContext, result.channel.items);

		NoticiasActivity.lvNoticias.setAdapter(adp);
				
		progressDlg.dismiss();
		
		super.onPostExecute(result);
	}

	private String readStream(InputStream in) {
		BufferedReader reader = null;
		StringBuffer data = new StringBuffer("");
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				data.append(line);
			}
		} catch (IOException e) {
			Log.e("LeitorRSS", "IOException");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data.toString();
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
