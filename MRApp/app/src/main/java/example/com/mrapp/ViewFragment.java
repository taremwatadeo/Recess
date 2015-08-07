package example.com.mrapp;


import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ViewFragment extends android.support.v4.app.Fragment {
    ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList arrayList;
    Database database;

    public ViewFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_view, container, false);
        //detailtxt = (TextView)rootView.findViewById(R.id.textView_detail);
        database = new Database(getActivity());

        try {
            database.open();
                arrayList = database.getListview();
                arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayList);
            //database.close();

        }catch (Exception z){
            z.printStackTrace();
        }

            listView = (ListView) rootView.findViewById(R.id.list_view_view);
            listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {

                    long ids = id + 1 ;

                    String pp = database.getDetail(ids);
                    if (pp != null) {
                        Log.v("Error.....",""+ ids);
                        Dialog dialog = new Dialog(getActivity());
                        dialog.setTitle("Details");
                        TextView viewtext = new TextView(getActivity());
                        viewtext.setText(pp);
                        viewtext.setTextSize(25);
                        dialog.setContentView(viewtext);
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.show();

                    } else {
                        Log.v("Error.....",""+id);
                        Dialog dialog = new Dialog(getActivity());
                        dialog.setTitle("Details");
                        TextView viewtext = new TextView(getActivity());
                        viewtext.setText("Info not available");
                        viewtext.setTextSize(25);
                        dialog.setContentView(viewtext);
                        dialog.show();
                    }

                } catch (Exception x) {
                    Log.v("Error.....",""+id);
                    String vv = x.getMessage();
                    Dialog dialog = new Dialog(getActivity());
                    dialog.setTitle("Details");
                    TextView viewtext = new TextView(getActivity());
                    viewtext.setText(vv);
                    viewtext.setTextSize(25);
                    dialog.setContentView(viewtext);
                    dialog.show();

                }


            }
        });
        return rootView;
    }


}
