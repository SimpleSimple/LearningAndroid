package myapp.androidthreadtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import myapp.androidthreadtest.utils.HttpUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int UPDATE_TEXT=0;
    Button btnChangeText;
    TextView tvContent;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    tvContent.setText(msg.obj.toString());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChangeText = (Button)findViewById(R.id.start_button);
        tvContent = (TextView) findViewById(R.id.textview1);
        btnChangeText.setOnClickListener(this);
        //String url="https://www.zhihu.com";
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.start_button: {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String result = HttpUtil.getRequest("https://www.zhihu.com");
                            Message msg = new Message();
                            msg.what = UPDATE_TEXT;
                            msg.obj =result;
                            handler.sendMessage(msg);
                        }catch(Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();
                break;
            }
            default:
                break;
        }
    }
}
