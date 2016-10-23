package com.example.alex.conversation;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class ConversationHandler {
    private final String WEBSERVICE_URL = "https://gateway.watsonplatform.net/conversation/api";
    private final String USERNAME = "5e051c4e-6829-43a3-b279-193c85339aca";
    private final String PASSWORD = "6tKNmYL0f0Oo";
    private String type = "POST";
    private Context appContext;
    private File convertedFile;
    private String inputText;
    private String wrkSpaceID = "b0c5feaa-7d25-4acc-b7ff-341663c4791e";
    private ConversationService service;


    public ConversationHandler(Context appContext)
    {
        this.appContext = appContext;
        service = new ConversationService("2016-09-20");
        service.setUsernameAndPassword("5e051c4e-6829-43a3-b279-193c85339aca", "6tKNmYL0f0Oo");
    }
    public void execute(String input) {
        new TheTask().execute(input);
    }

    class TheTask extends AsyncTask<String, Void, MessageResponse> {
        @Override
        protected MessageResponse doInBackground(String... userInput) {
            System.out.println(userInput[0]);
            MessageRequest newMessage = new MessageRequest.Builder().inputText(userInput[0]).build();
            MessageResponse response = service.message(wrkSpaceID, newMessage).execute();
            System.out.println(response);
            //newMessage = new MessageRequest.Builder().inputText("Who is my dad?").build();
            //response = service.message(wrkSpaceID, newMessage).execute();
            return response;
        }
        public void playFile() {


        }//end playFile
        @Override
        protected void onPostExecute(MessageResponse result) {

            System.out.println(result);
        }
    }
}