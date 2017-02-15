package com.leo.vfp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.leo.vfp.model.WorkOrder;

import java.util.List;

public class WorkorderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workorder);
        List<WorkOrder> list= (List<WorkOrder>) getIntent().getSerializableExtra(MainActivity.TAG);
        ListView listView = (ListView) findViewById(R.id.workorder_list);
        QuickAdapter<WorkOrder> adapter = new QuickAdapter<WorkOrder>(this, R.layout.list_workorder,list) {
            @Override
            protected void convert(BaseAdapterHelper helper, WorkOrder item) {
                helper.setText(R.id.item_workorder_number,item.getNumber());
                helper.setText(R.id.item_workorder_contract,item.getContract());
                helper.setText(R.id.item_workorder_workshop,item.getWorkshop());
                helper.setText(R.id.item_workorder_planProduce,item.getPlanProduce()+"");
                helper.setText(R.id.item_workorder_materail_name,item.getMaterialName());
                helper.setText(R.id.item_workorder_materail_code,item.getMaterialCode());
                helper.setText(R.id.item_workorder_createdAt,item.getCreatedAt());
                helper.setText(R.id.item_workorder_finishedAt,item.getFinishedAt());

            }
        };
        listView.setAdapter(adapter);
    }
}
