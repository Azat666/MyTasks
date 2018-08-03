package com.example.student.mytasks.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.student.mytasks.R;
import com.example.student.mytasks.adapter.MyAdapterPager;
import com.example.student.mytasks.models.ModelPager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<ModelPager> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list.add(new ModelPager(R.drawable.pager1));
        list.add(new ModelPager(R.drawable.pager2));
        list.add(new ModelPager(R.drawable.pager3));
        list.add(new ModelPager(R.drawable.pager4));
        list.add(new ModelPager(R.drawable.pager5));

        MyAdapterPager adapterPager = new MyAdapterPager(this, list);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapterPager);


        TabLayout tabLayout = findViewById(R.id.tab_design);
        tabLayout.setupWithViewPager(viewPager);
    }

}
