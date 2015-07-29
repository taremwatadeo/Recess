package example.com.mrapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class UpdateFragment extends android.support.v4.app.Fragment {

    ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList arrayList;
    Database database;


    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_update, container, false);
        database = new Database(getActivity());
        try {
            database.open();
            arrayList = database.getLists();
            arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayList);
            database.close();

        }catch (Exception z){
            z.printStackTrace();
        }

        listView = (ListView)view.findViewById(R.id.list_view1);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return view;
    }


}
