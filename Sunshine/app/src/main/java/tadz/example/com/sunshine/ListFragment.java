package tadz.example.com.sunshine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class ListFragment extends Fragment {

    Button button;
    private ArrayAdapter<String> mForecastAdapter;

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        //creating a list and putting it in an array

        String[] forecastArray = {
                "ghh", "ddd", "hjfkfj", "jhfjkl", "ffjjvgl", "jflf", "hfjf",
                "hfkf", "hfd;l", "jfkf", "fhfk", "hffl", "fjfk", "hfkkf",
                "bfhjk", "hfjkl", "hfjkl", "jfk", "bfnm", "hfjk", "bnm"
        };
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));

        //populate the listview to arrayadapter

        mForecastAdapter =
                new ArrayAdapter<String>(
                        getActivity(),
                        R.layout.list_item_fragment,
                        R.id.list_item_fragment_textview,
                        weekForecast);

        ListView listView = (ListView) rootView.findViewById(R.id.list_view_forecast);
        listView.setAdapter(mForecastAdapter);

        return rootView;
    }




}
