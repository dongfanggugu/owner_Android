package com.honyum.owner.activity.dtcs;

import android.os.Bundle;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;

public class HelpContentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_content);

        initTitleBar(R.id.title, "电梯常识", R.mipmap.back, backClickListener,
                0, null);

        initView();
    }

    private void initView() {
        int help = getIntent().getIntExtra("help", -1);

        TextView tvTitle = (TextView) findViewById(R.id.tv_title_tip);
        TextView tvContent = (TextView) findViewById(R.id.tv_content);

        switch (help) {
            case 1:
                tvTitle.setText("乘坐电梯的注意事项");
                tvContent.setText(getString(R.string.help_content_1));
                break;
            case 2:
                tvTitle.setText("电梯的正确使用方法");
                tvContent.setText(getString(R.string.help_content_2));
                break;
            case 3:
                tvTitle.setText("十大家用别墅电梯排名");
                tvContent.setText(getString(R.string.help_content_3));
                break;
            case 4:
                tvTitle.setText("别墅电梯最小尺寸是多少");
                tvContent.setText(getString(R.string.help_content_4));
                break;
            case 5:
                tvTitle.setText("别墅电梯选购要点");
                tvContent.setText(getString(R.string.help_content_5));
                break;
            case 6:
                tvTitle.setText("家用别墅电梯特点");
                tvContent.setText(getString(R.string.help_content_5));
                break;
            case 7:
                tvTitle.setText("家用别墅电梯安全隐患不容忽视");
                tvContent.setText(getString(R.string.help_content_5));
                break;
            default:
                tvTitle.setText("");
                tvContent.setText("");
                break;
        }

    }
}
