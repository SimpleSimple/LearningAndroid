package myapp.simpleadaptertest;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MainActivity extends Activity {

    private String[] names=new String[]{
            "老虎","李清照","李白"
    };
    private String[] descs=new String[]{
            "可爱的老虎","婉约派女诗人","诗仙"
    };
    private int[] imageIds=new int[]{
            R.drawable.tiger,  R.drawable.liqingzhao,R.drawable.libai
    };
    private ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Map<String, Object>> lists=new ArrayList<Map<String,Object>>();
        for (int i=0;i<names.length;i++){
            Map<String,Object> items=new HashMap<String, Object>();
            items.put("name",names[i]);
            items.put("header", imageIds[i]);
            items.put("desc",descs[i]);
            lists.add(items);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,lists,R.layout.simpleadapter_array_item,
                //对应Map中的键值
                new String[]{"header","name","desc"},
                //对应array_item布局文件中的控件id
                new int[]{R.id.header, R.id.name, R.id.desc});

        myList=(ListView)findViewById(R.id.mylist);
        myList.setAdapter(adapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(names[position]+" 被单击了");
            }
        });
        myList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(names[position]+" 被选中了");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
