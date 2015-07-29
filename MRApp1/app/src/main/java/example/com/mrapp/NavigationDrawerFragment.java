package example.com.mrapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static example.com.mrapp.R.array;
import static example.com.mrapp.R.id;
import static example.com.mrapp.R.layout;
import static example.com.mrapp.R.string;

//import android.support.v4.app.Fragment;


public class NavigationDrawerFragment extends android.support.v4.app.Fragment {

    ListView listView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    public boolean mUserLearnedDrawer;
    public boolean mFromSavedInstanceState;
    public static final String PREF_FILE_NAME = "test_pref";
    public static final String KEY_USER = "user_learned_drawer";
    public View containerView;
    public MyAdapter myAdapter;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserLearnedDrawer = Boolean.valueOf(readFromPref(getActivity(), KEY_USER, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(layout.fragment_navigation_drawer, container, false);

        listView = (ListView) rootView.findViewById(id.drawer_list);
        myAdapter = new MyAdapter(getActivity());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                android.support.v4.app.Fragment fragment = null;
                switch (position) {

                    case 0:
                        fragment = new UpdateFragment();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }if (fragment != null) {

                    // android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                    //fragmentManager.beginTransaction().replace(R.id.container_layout,fragment).commit();
                    //updating selected item.app
                    listView.setItemChecked(position, true);
                    listView.setSelection(position);
                    //setTitle(titles[position]);

                } else {
                    Log.e("MainActivity", "......Error craeting fragment");

                }

            }
        });
        return rootView;
    }


    public void setUp(int fragment_id, DrawerLayout drawerlayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragment_id);
        mDrawerLayout = drawerlayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerlayout, toolbar, string.drawer_open, string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mFromSavedInstanceState) {
                    mFromSavedInstanceState = true;
                    saveToPrefernces(getActivity(), KEY_USER, "");
                }
                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                if (slideOffset < 0.65) {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }
        };
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();

            }
        });
    }

    public void saveToPrefernces(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public String readFromPref(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedpreference = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedpreference.getString(preferenceName, defaultValue);
    }

    class MyAdapter extends BaseAdapter {
        private Context context;
        String[] actionlists;

        // int[] images = {R.drawable.a_home, R.drawable.add, R.drawable.view, R.drawable.update, R.drawable.delete,
        //  R.drawable.schedule, R.drawable.logout};
        public MyAdapter(Context context) {
            this.context = context;
            actionlists = context.getResources().getStringArray(array.action_list);

        }

        @Override
        public int getCount() {
            return actionlists.length;
        }

        @Override
        public Object getItem(int position) {
            return actionlists[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = null;
            if (convertView == null) {

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.custom_list, parent, false);
            } else {
                row = convertView;
            }
            TextView text1 = (TextView) row.findViewById(id.custom_textview);
            // ImageView image1 = (ImageView)row.findViewById(id.imageView);
            text1.setText(actionlists[position]);
            //image1.setImageResource(images[position]);

            return row;
        }
    }


}
