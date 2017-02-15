package com.leo.vfp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.leo.vfp.model.Device;

import java.util.List;

public class DeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        List<Device> deviceList= (List<Device>) getIntent().getSerializableExtra(MainActivity.TAG);
        ListView listView = (ListView) findViewById(R.id.device_list);
        QuickAdapter<Device>adapter = new QuickAdapter<Device>(this, R.layout.list_device,deviceList) {
            @Override
            protected void convert(BaseAdapterHelper helper, Device item) {
                helper.setText(R.id.item_device_number,item.getNumber());
                helper.setText(R.id.item_device_name,item.getName());
            }
        };
        listView.setAdapter(adapter);
    }
}
