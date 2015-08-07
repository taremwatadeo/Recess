package example.com.mrapp;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


public class SearchActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private static String search_query = null;
    private TextView fname, lname, dob, prescription, disease, remarks, return_date;
    private TextView fnam, lnam, dobs, prescriptio, diseas, remark, return_dat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        whenQueryComes(getIntent());

        // text views that will hold results
        fname = (TextView) findViewById(R.id.first_name);  lname = (TextView) findViewById(R.id.last_value);
        dob = (TextView) findViewById(R.id.dob_value);    prescription = (TextView) findViewById(R.id.prescription_value);
        disease = (TextView) findViewById(R.id.disease_value);  remarks = (TextView) findViewById(R.id.remarks_value);
        return_date = (TextView) findViewById(R.id.return_value);

        // text view that act as headings
        fnam = (TextView) findViewById(R.id.first);  lnam = (TextView) findViewById(R.id.l_name);
        dobs = (TextView) findViewById(R.id.dob);    prescriptio = (TextView) findViewById(R.id.prescription);
        diseas = (TextView) findViewById(R.id.disease);  remark = (TextView) findViewById(R.id.remarks);
        return_dat = (TextView) findViewById(R.id.retut);

        Cursor cursor = null;
        try{
            Database my_db = new Database(this);
            my_db.open();
            cursor = my_db.searchMyDb(search_query);

            // if there exists at least one item
            if (cursor.moveToFirst()) {
                do {
                    // set values to the text views
                    fname.setText(cursor.getString(0));
                    lname.setText(cursor.getString(1));
                    dob.setText(cursor.getString(2));
                    prescription.setText(cursor.getString(3));
                    disease.setText(cursor.getString(4));
                    remarks.setText(cursor.getString(6));
                    return_date.setText(cursor.getString(5));
                } while (cursor.moveToNext());
            }else {
                /*
                * f no value is present, make the text values invisible
                * make headings invisible
                * make the values invisinle
                * display no rsults found on disease because it is in the middle
                * */

                fname.setVisibility(View.INVISIBLE); lname.setVisibility(View.INVISIBLE); dob.setVisibility(View.INVISIBLE); prescription.setVisibility(View.INVISIBLE);
                remarks.setVisibility(View.INVISIBLE); return_date.setVisibility(View.INVISIBLE); disease.setText("No Results Found !!");

                fnam.setVisibility(View.INVISIBLE); lnam.setVisibility(View.INVISIBLE); dobs.setVisibility(View.INVISIBLE); prescriptio.setVisibility(View.INVISIBLE);
                remark.setVisibility(View.INVISIBLE); return_dat.setVisibility(View.INVISIBLE);
                diseas.setVisibility(View.INVISIBLE);
            }
            my_db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        whenQueryComes(intent);
    }

    private void whenQueryComes(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            search_query = intent.getStringExtra(SearchManager.QUERY);
        }
    }
}
