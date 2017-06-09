package com.meituan.danmuku.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.meituan.danmuku.DanMuHelper;
import com.meituan.danmuku.DanMuView;
import com.meituan.danmuku.DanmuActivity;
import com.meituan.danmuku.camera.Camera2BasicFragment;
import com.meituan.danmuku.model.entity.DanmakuEntity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, DanmuActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initBackGround(){
        // 建议放到Application的onCreate方法中进行初始化
//        Phoenix.init(this);
//        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById(R.id.sdv_cover);
//        ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
//        layoutParams.width = DensityUtil.getDisplayWidth(this);
//        layoutParams.height = DensityUtil.getDisplayHeight(this);
//
//        String url = "http://ww2.sinaimg.cn/large/610dc034jw1fa42ktmjh4j20u011hn8g.jpg";
//        Phoenix.with(simpleDraweeView)
//                .setWidth(DensityUtil.getDisplayWidth(this))
//                .setHeight(DensityUtil.getDisplayHeight(this))
//                .load(url);
    }

}
