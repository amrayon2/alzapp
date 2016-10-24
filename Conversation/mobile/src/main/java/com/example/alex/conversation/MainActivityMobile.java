package com.example.alex.conversation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivityMobile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mobile);
        ConversationHandler conv = new ConversationHandler(getApplicationContext());
        addButtonListener(conv);

    }

    private String getTextFromEditText()
    {
        EditText editText = (EditText) findViewById(R.id.editText);
        return editText.getText().toString();
    }

    private void addButtonListener(final ConversationHandler conv)
    {
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = getTextFromEditText();
                String output = conv.execute(inputText);
                TextView txtView = (TextView) findViewById(R.id.textView3);
                txtView.setText(output, TextView.BufferType.NORMAL);

            }
        });
    }
}




