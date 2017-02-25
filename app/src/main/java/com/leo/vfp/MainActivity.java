package com.leo.vfp;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.leo.vfp.model.Device;
import com.leo.vfp.model.Timecard;
import com.leo.vfp.model.WorkOrder;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    public static final String USERID = "666";
    public static final String TAG = "MainActivity";
    private Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCalendar = Calendar.getInstance();

        findViewById(R.id.main_timecard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<Timecard> query = new BmobQuery<>();
                query.addWhereEqualTo("userId", USERID).setLimit(20).order("-createdAt");
                query.findObjects(new FindListener<Timecard>() {
                    @Override
                    public void done(List<Timecard> list, BmobException e) {
                        if (e != null) {
                            Toast.makeText(MainActivity.this, "查询错误：" + e.getMessage() + ",错误码：" + e.getErrorCode(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (list.size() == 0) {
                            Toast.makeText(MainActivity.this, "记录为0", Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(MainActivity.this, TimecardActivity.class);
                            intent.putExtra(TAG, (Serializable) list);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        findViewById(R.id.main_device).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<Device> query = new BmobQuery<>();
                query.setLimit(20).order("-createdAt");
                query.findObjects(new FindListener<Device>() {
                    @Override
                    public void done(List<Device> list, BmobException e) {
                        if (e != null) {
                            Toast.makeText(MainActivity.this, "查询错误：" + e.getMessage() + ",错误码：" + e.getErrorCode(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (list.size() == 0) {
                            Toast.makeText(MainActivity.this, "记录为0", Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(MainActivity.this, DeviceActivity.class);
                            intent.putExtra(TAG, (Serializable) list);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        findViewById(R.id.main_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<WorkOrder> query = new BmobQuery<>();
                query.setLimit(20).order("-createdAt");
                query.findObjects(new FindListener<WorkOrder>() {
                    @Override
                    public void done(List<WorkOrder> list, BmobException e) {
                        if (e != null) {
                            Toast.makeText(MainActivity.this, "查询错误：" + e.getMessage() + ",错误码：" + e.getErrorCode(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (list.size() == 0) {
                            Toast.makeText(MainActivity.this, "记录为0", Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(MainActivity.this, WorkorderActivity.class);
                            intent.putExtra(TAG, (Serializable) list);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        findViewById(R.id.main_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendar.setTime(new Date());
                int currentHour = mCalendar.get(Calendar.HOUR_OF_DAY);
                if (currentHour<7||currentHour>21) {
                    Toast.makeText(MainActivity.this, "不在考勤时间内", Toast.LENGTH_SHORT).show();
                    return;
                }
                Timecard timecard = new Timecard();
                timecard.setUserId(USERID);
                timecard.setUsername("哈哈儿");
                timecard.setLocation("实验室");
                timecard.setType(0);
                if (currentHour<18) {
                    timecard.setOvertime(false);
                } else {
                    timecard.setOvertime(true);
                }
                timecard.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e==null) {
                            Toast.makeText(MainActivity.this, "签到成功！", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "签到失败：" + e.getMessage() + ",错误码：" + e.getErrorCode(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        findViewById(R.id.main_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivityPermissionsDispatcher.showCameraIntentWithCheck(MainActivity.this,
                        new Intent(MainActivity.this, CaptureActivity.class));
            }
        });
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void showCameraIntent(Intent intent) {
        startActivityForResult(intent, 0);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void showDeniedCamera() {
        Toast.makeText(this, R.string.permission_camera_denied, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        Bundle bundle = data.getExtras();
        if (bundle == null) {
            return;
        }

        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
            String result = bundle.getString(CodeUtils.RESULT_STRING);
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra(TAG, result);
            startActivity(intent);
        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
            Toast.makeText(MainActivity.this, "条码解析失败！", Toast.LENGTH_LONG).show();
        }
    }
}
