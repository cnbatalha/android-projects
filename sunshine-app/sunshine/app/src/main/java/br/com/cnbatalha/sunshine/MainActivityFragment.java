package br.com.cnbatalha.sunshine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
}
