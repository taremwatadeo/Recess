package tadz.example.com.musawo;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class Deletepatient extends ActionBarActivity {

    Database pat;
    AlertDialog.Builder alert;
    RecyclerView listView;
    MyListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletepatient);

        pat = new Database(this);
        try {
            pat.open();
            adapter = new MyListAdapter(this, pat.getList());
            //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
            pat.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        listView = (RecyclerView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
        /*

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int x = position;
                alert = new AlertDialog.Builder(Deletepatient.this);
                alert.setTitle("Are you sure you want to delete");
                alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            pat.open();
                            pat.deleteP(x);
                            pat.close();
                            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();
                        } catch (Exception x) {
                            x.printStackTrace();
                        }

                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                alert.show();

            }
        });
        */

       /* button = (Button)findViewById(R.id.pdelete_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Deletepatient.this);
                builder.setTitle("Enter row id");

                final EditText input = new EditText(Deletepatient.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String m_text = input.getText().toString();
                                long row = Long.parseLong(m_text);
                                MyListAdapter dat = new MyListAdapter(Deletepatient.this);
                                try {
                                    dat.open();
                                    returnname = dat.getPatientName(row);
                                    dat.close();
                                } catch (Exception e) {
                                    Dialog tad = new Dialog(Deletepatient.this);
                                    tad.setTitle("HELLO");
                                    TextView textView = new TextView(Deletepatient.this);
                                    textView.setText(e.getMessage());
                                    textView.setTextSize(25);
                                    tad.setContentView(textView);
                                    tad.show();

                                }
                                alert = new AlertDialog.Builder(Deletepatient.this);
                                alert.setMessage(R.string.message + returnname);
                                alert.setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        long rrow = Long.parseLong(m_text);
                                        MyListAdapter dat = new MyListAdapter(Deletepatient.this);
                                        try {
                                            dat.open();
                                            dat.deleteEntry(rrow);
                                            dat.close();
                                            Intent intent = new Intent(getApplicationContext(), Home.class);
                                            startActivity(intent);

                                        } catch (Exception a) {

                                            Dialog tad = new Dialog(Deletepatient.this);
                                            tad.setTitle("HELLO");
                                            TextView textView = new TextView(Deletepatient.this);
                                            textView.setText(a.getMessage());
                                            textView.setTextSize(25);
                                            tad.setContentView(textView);
                                            tad.show();
                                        }


                                    }
                                });


                                alert.setNegativeButton(R.string.negative, new DialogInterface.OnClickListener()

                                        {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(getApplicationContext(), Home.class);
                                                startActivity(intent);
                                            }
                                        }

                                );


                            }
                        }

                );
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()

                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }

                );
                builder.show();

            }

        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_addpatient, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
