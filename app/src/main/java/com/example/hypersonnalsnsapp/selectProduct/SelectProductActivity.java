package com.example.hypersonnalsnsapp.selectProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
import com.example.hypersonnalsnsapp.util.DebugLogUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SelectProductActivity extends AppCompatActivity {

    private static final String TAG = "SelectProductActivity";

    private RecyclerView recyclerView;
    private TextView textViewNextButton;

    private SelectProductAdapter selectProductAdapter;

    public static List<Integer> productAllCostList;

    public SelectProductActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);
        findView();
        init();
        setListener();
    }



    private void findView() {
        recyclerView = findViewById(R.id.recyclerview);
        textViewNextButton=findViewById(R.id.textViewNextButton);
    }

    private void init(){
        productAllCostList = new ArrayList<>();
        selectProductAdapter = new SelectProductAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(selectProductAdapter);
    }

    private void setListener(){
        textViewNextButton.setOnClickListener(v -> {
            int productAllCost = 0;
            DebugLogUtil.logD(TAG, "textViewNextButton 클릭");
            for(int i = 0; i<productAllCostList.size(); i++){
                productAllCost+=productAllCostList.get(i);
            }
            DebugLogUtil.logD(TAG, "allCost : "+productAllCost);
            String message = "안녕 나는 ㅃ님이고 삐님은 얼른 퇴근하고 싶어 퇴근시켜줘 11분 남았는데 할 일이 없어서 이걸 지금 테스트 하고 있어 엉엉엉 사람살려 끼야아앙 그게 뭐야 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ " +
                    "왜 자꾸 저걸로 날아가지 짧아서 그런가 오늘은 10월 23일이다 하하호호깔깔 삐님은 요정이 아니고 이것은 삐님의 부캐이다 하하하" +
                    "뭐야 제한 사라졋어????????????????????????????????????????? 80자 넘게 쓴거 같은데????????????????????????????????????????????????????????????????????????????????????????????????";
            SendSMS("010-8463-0021", message);
        });
    }

    private void SendSMS(String phonenumber, String message) {
        SmsManager smsManager = SmsManager.getDefault();
        String sendTo = phonenumber;
        ArrayList partMessage = smsManager.divideMessage(message);
        smsManager.sendMultipartTextMessage(sendTo, null, partMessage, null, null);
        Toast.makeText(SelectProductActivity.this, "전송되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }


}