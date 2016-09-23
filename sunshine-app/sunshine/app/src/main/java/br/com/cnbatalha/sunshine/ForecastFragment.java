package br.com.cnbatalha.sunshine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    public ForecastFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        BufferedReader reader = null;
        try {
            HttpURLConnection urlConnection = null;

            // making url connection
            URL url = new URL("api.openweathermap.org/data/2.5/forecast/daily?q=9404");
            //id --> 6c40202ad868993448785e8eaf259194

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // nothing
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }


        List<String> forecastList = new ArrayList<String>();
        forecastList.add("Today - sunny - 40/30");
        forecastList.add("Tomorrow - sunny - 40/30");
        forecastList.add("Sunday - sunny - 40/30");
        forecastList.add("Monday - sunny - 40/30");
        forecastList.add("Thursday - sunny - 40/30");
        forecastList.add("Wednesday - sunny - 40/30");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_forecast, R.id.list_item_forecast_textview, forecastList);

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lView = (ListView) view.findViewById(R.id.listview_forecast);
        lView.setAdapter(arrayAdapter);

        return view;
    }


    public class FetchWeatherTask extends AsyncTask<String, String, String>
    {

        @Override
        protected String doInBackground(String... params) {
            return null;
        }
        
    }
}
