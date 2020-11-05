package com.example.hypersonnalsnsapp.selectProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.selectProduct.adapter.SelectProductAdapter;
import com.example.hypersonnalsnsapp.selectProduct.adapter.SelectProductViewHolder;
import com.example.hypersonnalsnsapp.util.Constant;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;
import com.example.hypersonnalsnsapp.util.SharedPreferenceUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SelectProductActivity extends AppCompatActivity {

    private static final String TAG = "SelectProductActivity";

    private RecyclerView recyclerView;
    private TextView textViewNextButton;

    private SelectProductAdapter selectProductAdapter;

    public static List<Integer> productAllCostList;
    public static List<Integer> productCount;
    public String phone="";

    public SelectProductActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);
        checkBundle();
        findView();
        init();
        setListener();
    }

    private void checkBundle() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        phone = bundle.getString("phone");
    }


    private void findView() {
        recyclerView = findViewById(R.id.recyclerview);
        textViewNextButton = findViewById(R.id.textViewNextButton);
    }

    private void init() {
        productAllCostList = new ArrayList<>();
        productCount = new ArrayList<>();
        selectProductAdapter = new SelectProductAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(selectProductAdapter);
    }

    private void setListener() {
        textViewNextButton.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = SelectProductActivity.this.getSharedPreferences(SharedPreferenceUtil.SHARED_PREFERENCES_KEY, MODE_PRIVATE);

            StringBuilder sb = new StringBuilder();
            sb.append("안녕하세요 제품 판매자 입니다. \n");
            sb.append("이번 달 명세서입니다. \n");
            int productAllCost = 0;
            DebugLogUtil.logD(TAG, "textViewNextButton 클릭");
            for (int i = 0; i < productAllCostList.size(); i++) {
                if(productCount.get(i)!=0) {
                    sb.append(Constant.productList.get(i) + " " + productCount.get(i) + "개, " +productAllCostList.get(i)+"\n");
                    productAllCost += productAllCostList.get(i);
                }
            }

            if(productAllCost==0){
                Toast.makeText(this, "입력된 값이 없습니다", Toast.LENGTH_SHORT).show();
                sb.delete(0, sb.length());
                return;
            }
            sb.append("총 "+productAllCost+"원 입니다. \n");
            sb.append("계좌는 "+sharedPreferences.getString("bank","")+" "+sharedPreferences.getString("account","")+ " 입니다. \n");
            sb.append("감사합니다.");

            sendMMSIntent(sb.toString());
        });
    }

//    private void SendSMS(String phonenumber, String message) {
//        SmsManager smsManager = SmsManager.getDefault();
//        String sendTo = phonenumber;
//        ArrayList partMessage = smsManager.divideMessage(message);
//        smsManager.sendMultipartTextMessage(sendTo, null, partMessage, null, null);
//        Toast.makeText(SelectProductActivity.this, "전송되었습니다.", Toast.LENGTH_SHORT).show();
//        finish();
//    }

    private void sendMMSIntent(String message) {
        Uri uri = Uri.parse("sms: " + phone);
        DebugLogUtil.logD(TAG, phone);
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
        sendIntent.putExtra("subject", "MMS TEST");
        sendIntent.putExtra("sms_body", message);
        startActivity(sendIntent);
        finish();
//            if(sendIntent.resolveActivity(getPackageManager())!=null){
//                DebugLogUtil.logD(TAG, "인텐트가 안열리는거니?");
//
//            }else{
//                DebugLogUtil.logD(TAG, "패키지 매니저가 비었니?");
//            }
    }


}