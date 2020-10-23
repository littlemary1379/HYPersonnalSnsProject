package com.example.hypersonnalsnsapp.selectSMSNumber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.main.MainActivity;
import com.example.hypersonnalsnsapp.selectBookNumber.SelectBookNumberActivity;
import com.example.hypersonnalsnsapp.selectSMSNumber.adapter.SelectSMSAdapter;
import com.example.hypersonnalsnsapp.selectSMSNumber.model.Message;
import com.example.hypersonnalsnsapp.util.ActivityUtil;
import com.example.hypersonnalsnsapp.util.CheckPermissionUtil;
import com.example.hypersonnalsnsapp.util.Constant;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SelectSmsActivity extends AppCompatActivity {

    private static final String TAG = "SelectSmsActivity";

    private RecyclerView recyclerView;
    private ImageView imageViewContact;

    private List<Message> msgList=new ArrayList<>();
    private SelectSMSAdapter selectSMSAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sms);
        findView();
        init();
        setListener();
        readSMSMessage();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.in_left,R.anim.out_right);
    }

    private void findView(){
        recyclerView = findViewById(R.id.recyclerview);
        imageViewContact = findViewById(R.id.imageViewContact);
    }

    private void init(){
        selectSMSAdapter = new SelectSMSAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(SelectSmsActivity.this));
        recyclerView.setAdapter(selectSMSAdapter);
    }

    private void setListener(){
        imageViewContact.setOnClickListener(v -> {
            DebugLogUtil.logD(TAG, "imageViewContact 클릭");
            ActivityUtil.startActivityNoFinish(SelectSmsActivity.this, SelectBookNumberActivity.class);
        });
    }


    public void readSMSMessage() {
        Uri allMessage = Uri.parse("content://mms-sms/conversations");
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(allMessage,
                new String[]{"_id", "thread_id", "address", "person", "date", "body"},
                null, null,
                "date DESC");

        while (c.moveToNext()) {
            Message msg = new Message();

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
            msg.setTimeStamp(timestamp);

            String body = c.getString(5);
            msg.setBody(body);

            msgList.add(msg);
        }

        c.close();

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMDD");
        DebugLogUtil.logD(TAG, "msgList.size() : "+msgList.size());


        selectSMSAdapter.loadMsg(msgList);

    }
}