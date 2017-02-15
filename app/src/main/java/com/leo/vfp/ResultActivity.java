package com.leo.vfp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String result = getIntent().getStringExtra(MainActivity.TAG);

        ((TextView)findViewById(R.id.result_barcode)).setText(result);
        final EditText editText = (EditText) findViewById(R.id.result_number);

        findViewById(R.id.result_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = editText.getText().toString();
                if (TextUtils.isEmpty(number)) {
                    Toast.makeText(ResultActivity.this, "数量不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
            }
        });

        findViewById(R.id.result_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this,OrderActivity.class));
            }
        });
    }
}
