package example.com.mrapp;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ViewFragment extends android.support.v4.app.Fragment {
    Database database;
    TextView textView,detailtxt;
    String output,output2;
    static String detailed = null;


    public ViewFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_view, container, false);
        textClickListener();
        textView = (TextView)rootView.findViewById(R.id.textView_view);
        //detailtxt = (TextView)rootView.findViewById(R.id.textView_detail);
        database = new Database(getActivity());

        try{
            database.open();
            output = database.getPatient();

        }catch (Exception e){
            output = e.getMessage().toString();
        }
        textView.setText(output);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    database.open();
                    output2 = output.toString();
                    detailed = database.getDetail(output2);
                    database.close();

                if (detailed != null){

                    Dialog dialog = new Dialog(getActivity());
                    dialog.setTitle("Details");
                    TextView viewtext = new TextView(getActivity());
                    viewtext.setText(detailed);
                    viewtext.setTextSize(25);
                    dialog.setContentView(viewtext);
                    dialog.show();
                }else {
                    Dialog dialog = new Dialog(getActivity());
                    dialog.setTitle("Details");
                    TextView viewtext = new TextView(getActivity());
                    viewtext.setText("ERROR");
                    viewtext.setTextSize(25);
                    dialog.setContentView(viewtext);
                    dialog.show();
                }
                }catch (Exception x) {
                   String vv = x.getMessage();
                    Dialog dialog = new Dialog(getActivity());
                    dialog.setTitle("Details");
                    TextView viewtext = new TextView(getActivity());
                    viewtext.setText(vv);
                    viewtext.setTextSize(25);
                    dialog.setContentView(viewtext);
                    dialog.show();

                }

               // detailtxt = (TextView)dialog.findViewById(R.id.textView_detail);

            }
        });

        return rootView;
    }
    public void textClickListener(){

    }

}
