package myapp.extendbaseadaptertest;

import android.app.Activity;
import android.app.ActivityManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private final static String TAG="BaseAdapterTest";
    private ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myList=(ListView)findViewById(R.id.my_list);

        BaseAdapter adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return 40;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LinearLayout line=new LinearLayout(MainActivity.this);
                line.setOrientation(LinearLayout.HORIZONTAL);
                ImageView imageView=new ImageView(MainActivity.this);
                imageView.setImageResource(R.drawable.ic_launcher_background);
                line.addView(imageView);
                TextView textView=new TextView(MainActivity.this);
                textView.setText("第 "+(position+1)+" 项");
                //textView.setTextColor(Color.RED);
                textView.setTypeface(Typeface.MONOSPACE);
                textView.setGravity(Gravity.CENTER);
                line.addView(textView);
                Log.d(TAG,"-------------");
                return line;
            }
        };
        myList.setAdapter(adapter);
    }
}
