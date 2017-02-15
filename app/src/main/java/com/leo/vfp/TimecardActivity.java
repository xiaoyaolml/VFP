package com.leo.vfp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.leo.vfp.model.Timecard;

import java.util.List;

public class TimecardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timecard);

        List<Timecard>timecardList= (List<Timecard>) getIntent().getSerializableExtra(MainActivity.TAG);
        ListView listView = (ListView) findViewById(R.id.timecard_list);

        QuickAdapter<Timecard>adapter = new QuickAdapter<Timecard>(this, R.layout.list_timecard,timecardList) {
            @Override
            protected void convert(BaseAdapterHelper helper, Timecard item) {
                helper.setText(R.id.item_timecard_username,item.getUsername());
                helper.setText(R.id.item_timecard_createAt,item.getCreatedAt());
                helper.setText(R.id.item_timecard_location,item.getLocation());
                helper.setText(R.id.item_timecard_type,(item.getType()==0)?"上班":"下班");
                helper.setText(R.id.item_timecard_overwork,item.isOvertime()?"加班":"不加班");
            }
        };
        listView.setAdapter(adapter);
    }
}
