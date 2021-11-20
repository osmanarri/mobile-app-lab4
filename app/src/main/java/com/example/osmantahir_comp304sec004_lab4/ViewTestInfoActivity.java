package com.example.osmantahir_comp304sec004_lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewTestInfoActivity extends AppCompatActivity {

    ListView lv;
    int pid;
    TestViewModel testViewModel;
    SharedPreferences sp;
    ArrayList<TestModel> tar;
    custtest ct;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test_info);
        setTitle("Student Test List");
        lv=findViewById(R.id.viewlist);
        pid=getIntent().getIntExtra("pid",0);
        sp=getSharedPreferences("pref",MODE_PRIVATE);
        tar=new ArrayList<>();
        ct=new custtest(tar);
        lv.setAdapter(ct);

        testViewModel= ViewModelProviders.of(this).get(TestViewModel.class);

        testViewModel.getpateintTestData(pid,sp.getInt("nid",0)).observe(this, new Observer<List<TestModel>>() {
            @Override
            public void onChanged(@Nullable List<TestModel> testModels) {
                tar.clear();
                tar.addAll(testModels);
                ct.notifyDataSetChanged();
            }
        });

    }
    class custtest extends BaseAdapter {
        ArrayList<TestModel> ar;

        custtest(ArrayList<TestModel> ar) {
            this.ar = ar;
        }

        @Override
        public int getCount() {
            return ar.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            viewholder v;
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.custlist, viewGroup, false);
                v = new viewholder();
                v.fname = view.findViewById(R.id.patientname);
                v.dept = view.findViewById(R.id.dept);
                v.room = view.findViewById(R.id.room);
                view.setTag(v);
            } else {
                v = (viewholder) view.getTag();
            }
            v.fname.setText("Term : "+ar.get(i).getBPL());
            v.dept.setText("Test : "+ar.get(i).getBHP());
            v.room.setText("Grade : "+ar.get(i).getTemperature());
            return view;

        }

        class viewholder {
            TextView fname,dept,room;

        }
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,PatientActivity.class);
        startActivity(i);
    }
}