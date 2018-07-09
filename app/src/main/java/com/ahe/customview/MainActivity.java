package com.ahe.customview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(new PathView(this));

       //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //final ImageView ımageView = (ImageView)findViewById(R.id.imageView);
        //final PathView ımageView = (PathView) findViewById(R.id.imageView);
        final CustomLayout ımageView = (CustomLayout) findViewById(R.id.imageView);




        ımageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, ımageView, "test");
                    startActivity(intent, compat.toBundle());
                    //Intent i=new Intent(MainActivity.this,TestActivity.class);
                    //startActivity(i);
                }
            }
        });
     }
}
