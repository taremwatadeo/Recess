package example.com.mrapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class DeleteFragment extends android.support.v4.app.Fragment {

    ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList arrayList;
    Database database;
    long ids;

    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView = inflater.inflate(R.layout.fragment_delete, container, false);

        database = new Database(getActivity());
        try {
            database.open();
            arrayList = database.getLists();
            arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayList);
            database.close();

        }catch (Exception z){
            z.printStackTrace();
        }
        if (arrayList == null){
            System.out.println("Please Add patients records to delete");
        }


        listView = (ListView)rootView.findViewById(R.id.list_delete);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ids = id + 1;
                Log.v("Error....", "" + ids + "\t" + position);
                final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setMessage("Are you sure you want to Delete");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            database.open();
                            database.deleteEntry(ids);
                            database.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                            String a = e.getMessage().toString();
                            Log.v("Error...",""+a);
                        }

                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alerts = alert.create();
                alerts.show();


            }
        });
        return rootView;
    }


}
