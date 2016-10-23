package com.example.alex.conversation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivityMobile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mobile);
        ConversationHandler conv = new ConversationHandler(getApplicationContext());
        conv.execute("hello");

    }

}




