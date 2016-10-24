package com.example.alex.conversation;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

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
    private Map<String, Object> contextConv;
    private String msgReturn;

    public void setMsgReturn(String msgReturn) {
        this.msgReturn = msgReturn;
    }

    public String getMsgReturn() {
        return msgReturn;
    }




    public ConversationHandler(Context appContext)
    {
        this.appContext = appContext;
        service = new ConversationService("2016-09-20");
        service.setUsernameAndPassword(USERNAME, PASSWORD);
    }
    public String execute(String input) {
        new TheTask().execute(input);
        return getMsgReturn();
    }

    class TheTask extends AsyncTask<String, Void, MessageResponse> {
        @Override
        protected MessageResponse doInBackground(String... userInput) {
            //System.out.println(userInput[0]);
            MessageRequest newMessage;
            if(contextConv != null){
                newMessage = new MessageRequest.Builder().inputText(userInput[0]).context(contextConv).build();
            }
            else{
                newMessage = new MessageRequest.Builder().inputText(userInput[0]).build();
            }
            MessageResponse response = service.message(wrkSpaceID, newMessage).execute();
            contextConv = response.getContext();
            //String convID = (String)context.get("conversation_id");
            //System.out.println(convID);
            //newMessage = new MessageRequest.Builder().inputText("Who is my dad?").build();
            //response = service.message(wrkSpaceID, newMessage).execute();
            concatOutput(response);
            return response;
        }

        public void concatOutput(MessageResponse result) {
            Map<String, Object> output = result.getOutput();
            ArrayList concat = (ArrayList) output.get("text");
            String reply = "";
            for(int i = 0; i < concat.size(); i++) {
                reply = reply + concat.get(i);
            }
            msgReturn = reply;
        }//end playFile
        @Override

        protected void onPostExecute(MessageResponse result) {

            System.out.println(result);

        }
    }
}