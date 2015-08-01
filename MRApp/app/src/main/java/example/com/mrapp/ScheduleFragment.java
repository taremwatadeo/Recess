package example.com.mrapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ScheduleFragment extends android.support.v4.app.Fragment {

    ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList arrayList;
    Database database;



    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        database = new Database(getActivity());
        try {
            database.open();
            arrayList = database.getScheduledPatients();
            arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayList);
            database.close();

        }catch (Exception z){
            z.printStackTrace();
        }

        listView = (ListView)rootView.findViewById(R.id.list_schedule);
        listView.setAdapter(arrayAdapter);
        return rootView;
    }


}
