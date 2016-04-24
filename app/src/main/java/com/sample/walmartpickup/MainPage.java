package com.sample.walmartpickup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.sample.walmartpickup.R;

/**
 * Created by Saiteja on 4/23/2016.
 */
public class MainPage extends Activity {
    ImageButton s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        addButtonListener();
    }
    public void addButtonListener(){
        s1 = (ImageButton)findViewById(R.id.mid1);
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainPage.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
