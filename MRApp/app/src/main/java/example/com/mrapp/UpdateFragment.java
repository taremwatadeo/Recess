package example.com.mrapp;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class UpdateFragment extends android.support.v4.app.Fragment {

    ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList arrayList;
    Database database;
    EditText patname,patlname,patage,patdisease,patprescription,patdatetoday,patreturndate,patremarks;


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
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.fragment_settings);
                dialog.setCancelable(true);
                dialog.show();
                patname = (EditText) dialog.findViewById(R.id.patientname_edt_update);
                patlname = (EditText) dialog.findViewById(R.id.patientlname_edt_update);
                patage = (EditText) dialog.findViewById(R.id.age_edt_update);
                patdisease = (EditText) dialog.findViewById(R.id.disease_edt_update);
                patprescription = (EditText) dialog.findViewById(R.id.prescription_edt_update);
                patdatetoday = (EditText) dialog.findViewById(R.id.datetoday_edt_update);
                patreturndate = (EditText) dialog.findViewById(R.id.returndate_edt_update);
                patremarks = (EditText) dialog.findViewById(R.id.remarks_edt_update);

                Button but = (Button) dialog.findViewById(R.id.update_btn);
                Button but2 = (Button)dialog.findViewById(R.id.cancel_update);
                but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String pat = patname.getText().toString();
                        String pat1 = patlname.getText().toString();
                        String pat2 = patage.getText().toString();
                        String pat3 = patdisease.getText().toString();
                        String pat4 = patprescription.getText().toString();
                        String pat5 = patdatetoday.getText().toString();
                        String pat6 = patreturndate.getText().toString();
                        String pat7 = patremarks.getText().toString();
                        try {
                            database.open();
                            database.updatep(id, pat, pat2, pat3, pat4, pat5, pat6, pat7);
                            database.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
                but2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });


            }
        });
        return view;
    }


}
