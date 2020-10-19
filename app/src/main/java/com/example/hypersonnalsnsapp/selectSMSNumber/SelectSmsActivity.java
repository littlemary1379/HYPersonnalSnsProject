package com.example.hypersonnalsnsapp.selectSMSNumber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.main.MainActivity;
import com.example.hypersonnalsnsapp.selectSMSNumber.model.Message;
import com.example.hypersonnalsnsapp.util.CheckPermissionUtil;
import com.example.hypersonnalsnsapp.util.Constant;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectSmsActivity extends AppCompatActivity {

    private static final String TAG = "SelectSmsActivity";

    private List<Message> msgList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sms);
        readSMSMessage();
    }


    public void readSMSMessage() {
        Uri allMessage = Uri.parse("content://sms");
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(allMessage,
                new String[]{"_id", "thread_id", "address", "person", "date", "body"},
                null, null,
                "date DESC");

        while (c.moveToNext()) {
            Message msg = new Message(); // 따로 저는 클래스를 만들어서 담아오도록 했습니다.

            long messageId = c.getLong(0);
            msg.setMessageId(String.valueOf(messageId));

            long threadId = c.getLong(1);
            msg.setThreadId(String.valueOf(threadId));

            String address = c.getString(2);
            msg.setAddress(address);

            long contactId = c.getLong(3);
            msg.setContactId(String.valueOf(contactId));

            String contactId_string = String.valueOf(contactId);
            msg.setContactId_string(contactId_string);

            long timestamp = c.getLong(4);
            msg.setTimeStamp(String.valueOf(timestamp));

            String body = c.getString(5);
            msg.setBody(body);

            msgList.add(msg);
        }
        
        DebugLogUtil.logD(TAG, "msgList.size() : "+msgList.size());

    }
}