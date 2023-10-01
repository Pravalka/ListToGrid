package com.example.krk;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int[] images = {R.drawable.one, R.drawable.two, R.drawable.three,
            R.drawable.four, R.drawable.five, R.drawable.six};

    String[] texts = {"PLU","Chicken Curry Cut","Tare","MRP","Weight","Price"};

    private boolean isGridView = false;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the custom adapter
        customAdapter = new CustomAdapter(getApplicationContext(), images, texts);

        // Initialize the ListView
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(customAdapter);

        // Check the initial orientation and switch layout if necessary
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            switchLayout(true);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Handle orientation changes
        switchLayout(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE);
    }

    private void switchLayout(boolean useGridView) {
        if (useGridView) {
            setContentView(R.layout.activity_main_grid);
            GridView gridView = findViewById(R.id.grid_View);
            gridView.setAdapter(customAdapter);
            isGridView = true;
        } else {
            setContentView(R.layout.activity_main);
            ListView listView = findViewById(R.id.listView);
            listView.setAdapter(customAdapter);
            isGridView = false;
        }
    }

    // CustomAdapter and other methods as before
    class CustomAdapter extends BaseAdapter {

        Context context;
        int[] images;
        String[] texts;
        LayoutInflater inflater;

        public CustomAdapter(Context context, int[] images, String[] texts) {
            this.context = context;
            this.images = images;
            this.texts = texts;
            inflater = (LayoutInflater.from(getApplicationContext()));
        }


        @Override
        public int getCount() {
            return texts.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            view = inflater.inflate(R.layout.custom_grid, null);

            ImageView imageView = view.findViewById(R.id.image);
            TextView textView = view.findViewById(R.id.text_view);

            imageView.setImageResource(images[position]);

            textView.setText(texts[position]);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (position) {
                        case 0:
                            Toast.makeText(getApplicationContext(), "Item One", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), "Item two", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Item Three", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(getApplicationContext(), "Item Four", Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(getApplicationContext(), "Item Five", Toast.LENGTH_SHORT).show();
                            break;
                        case 5:
                            Toast.makeText(getApplicationContext(), "Item Six", Toast.LENGTH_SHORT).show();


                            /* Tip :-
                             *
                             * If you need to use Intent Try this code.
                             *
                             * Intent intent = new Intent(context,Example.class);
                             * context.startActivity(intent);
                             * */
                    }
                }
            });

            return view;
        }
    }
}
