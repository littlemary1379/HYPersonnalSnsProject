package com.example.hypersonnalsnsapp.selectBookNumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.selectBookNumber.adapter.SelectBookNumberAdapter;
import com.example.hypersonnalsnsapp.selectBookNumber.model.PhoneBook;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectBookNumberActivity extends AppCompatActivity {

    private static final String TAG = "SelectBookNumberActivit";

    private ImageView imageViewContact;
    private RecyclerView recyclerView;

    private SelectBookNumberAdapter selectBookNumberAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_book_number);
        findView();
        init();
        setListener();

        readPhoneContact();
    }

    @Override
    public void finish() {

        super.finish();
    }

    private void findView(){
        imageViewContact = findViewById(R.id.imageViewContact);
        recyclerView = findViewById(R.id.recyclerview);
    }

    private void setListener(){
        imageViewContact.setOnClickListener(v -> {
            DebugLogUtil.logD(TAG, "imageViewContact 클릭");
            onBackPressed();
        });
    }

    private void init(){
        selectBookNumberAdapter = new SelectBookNumberAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(SelectBookNumberActivity.this));
        recyclerView.setAdapter(selectBookNumberAdapter);
    }

    public void readPhoneContact(){
        List<PhoneBook> phoneBookList = new ArrayList<>();

        ContentResolver resolver=SelectBookNumberActivity.this.getContentResolver();

        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projection = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor c= resolver.query(phoneUri, projection, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

        while (c.moveToNext()){

            String id = c.getString(c.getColumnIndex(projection[0]));
            String name = c.getString(c.getColumnIndex(projection[1]));
            String number = c.getString(c.getColumnIndex(projection[2]));

            PhoneBook phoneBook = new PhoneBook();
            phoneBook.setId(id);
            phoneBook.setName(name);
            phoneBook.setPhoneNumber(number);

            DebugLogUtil.logD(TAG, phoneBook.getPhoneNumber());

            phoneBookList.add(phoneBook);

        }

        selectBookNumberAdapter.reload(phoneBookList);

    }
}