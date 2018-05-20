package cn.edu.gdmec.android.viewpagerdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private ViewPager vp;
private View view1,view2,view3;
private MyAdapter adapter;
TextView tv1,tv2,tv3;
private List<View> list;
private Handler handler;
private Thread thread;
private boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp=findViewById(R.id.vp);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        view1=layoutInflater.inflate(R.layout.page1,null);
        view2=layoutInflater.inflate(R.layout.page2,null);
        view3=layoutInflater.inflate(R.layout.page3,null);
        list=new ArrayList<View>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        adapter=new MyAdapter(list);
        vp.setAdapter(adapter);
        tv1.setTextColor(Color.parseColor("#FF6600"));
        tv2.setTextColor(Color.parseColor("#050505"));
        tv3.setTextColor(Color.parseColor("#050505"));
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what==1){
                    if (adapter.getCount()>0){
                        if (vp.getCurrentItem()==list.size()-1){
                            tv1.setTextColor(Color.parseColor("#FF6600"));
                            tv2.setTextColor(Color.parseColor("#050505"));
                            tv3.setTextColor(Color.parseColor("#050505"));
                            vp.setCurrentItem(0);
                        }else if (vp.getCurrentItem()==0){
                                tv2.setTextColor(Color.parseColor("#FF6600"));
                                tv1.setTextColor(Color.parseColor("#050505"));
                                tv3.setTextColor(Color.parseColor("#050505"));
                            vp.setCurrentItem(vp.getCurrentItem()+1);
                            }else if(vp.getCurrentItem()==1){
                                tv3.setTextColor(Color.parseColor("#FF6600"));
                                tv1.setTextColor(Color.parseColor("#050505"));
                                tv2.setTextColor(Color.parseColor("#050505"));
                            vp.setCurrentItem(vp.getCurrentItem()+1);
                            }

                        }
                    }
                }

        };
        setViewPager();
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (vp.getCurrentItem()==list.size()-1){
                    tv3.setTextColor(Color.parseColor("#FF6600"));
                    tv1.setTextColor(Color.parseColor("#050505"));
                    tv2.setTextColor(Color.parseColor("#050505"));
                }else if (vp.getCurrentItem()==0){
                    tv1.setTextColor(Color.parseColor("#FF6600"));
                    tv2.setTextColor(Color.parseColor("#050505"));
                    tv3.setTextColor(Color.parseColor("#050505"));
                }else if(vp.getCurrentItem()==1){
                    tv2.setTextColor(Color.parseColor("#FF6600"));
                    tv3.setTextColor(Color.parseColor("#050505"));
                    tv1.setTextColor(Color.parseColor("#050505"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv1:

                vp.setCurrentItem(0);
                tv1.setTextColor(Color.parseColor("#FF6600"));
                tv2.setTextColor(Color.parseColor("#050505"));
                tv3.setTextColor(Color.parseColor("#050505"));
                break;
            case R.id.tv2:
                vp.setCurrentItem(1);
                tv2.setTextColor(Color.parseColor("#FF6600"));
                tv1.setTextColor(Color.parseColor("#050505"));
                tv3.setTextColor(Color.parseColor("#050505"));
                break;
            case R.id.tv3:
                vp.setCurrentItem(2);
                tv3.setTextColor(Color.parseColor("#FF6600"));
                tv1.setTextColor(Color.parseColor("#050505"));
                tv2.setTextColor(Color.parseColor("#050505"));
                break;
        }
    }
    private void setViewPager(){
        thread=new Thread(){
            @Override
            public void run() {
                while (flag){
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg=new Message();
                    msg.what=1;
                    handler.sendMessage(msg);
                }
            }
        };
        thread.start();
    }

}
