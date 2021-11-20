package com.example.osmantahir_comp304sec004_lab4;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PatientActivity extends AppCompatActivity
{
    FloatingActionButton add;
    ListView lv;
    TextView nid;
    ArrayList<PatientModel> arp;
    PatientViewModel patientViewModel;
    custpatient cp;
    SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        setTitle("Student List");
        add=findViewById(R.id.add);
        lv=findViewById(R.id.plist);
        nid=findViewById(R.id.nid);
        arp=new ArrayList<>();
        cp=new custpatient(arp);
        lv.setAdapter(cp);
        sp=getSharedPreferences("pref",MODE_PRIVATE);
        nid.setText("Professor ID: "+sp.getInt("nid",0));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PatientActivity.this, AddStudentActivity.class);
                startActivity(i);
            }
        });
        patientViewModel= ViewModelProviders.of(this).get(PatientViewModel.class);
        patientViewModel.getAllPatientlist().observe(this, new Observer<List<PatientModel>>() {
            @Override
            public void onChanged(@Nullable List<PatientModel> patientModels) {
                //Toast.makeText(Patient_activity.this, patientModels.size()+"", Toast.LENGTH_SHORT).show();
                arp.clear();
                arp.addAll(patientModels);
                cp.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final PopupMenu popup = new PopupMenu(PatientActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.viewmenu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        if (i == R.id.vtest) {
                            Intent ii=new Intent(PatientActivity.this,ViewTestInfoActivity.class);
                            ii.putExtra("pid",arp.get(position).getPatientid());
                            startActivity(ii);
                            return true;
                        }
                        else if (i == R.id.update){
                            Intent ii=new Intent(PatientActivity.this,UpdateInfoActivity.class);
                            ii.putExtra("object", (Parcelable) arp.get(position));
                            startActivity(ii);
                            return true;
                        }
                        else {
                            return onMenuItemClick(item);
                        }
                    }
                });

                popup.show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==R.id.patient)
        {
            recreate();
        }
        if(item.getItemId()==R.id.test)
        {
            Intent i=new Intent(PatientActivity.this,AddTestActivity.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.logout)
        {
            SharedPreferences sp=getSharedPreferences("pref",MODE_PRIVATE);
            SharedPreferences.Editor ed =sp.edit();
            ed.clear();
            ed.apply();

            Intent i=new Intent(PatientActivity.this,NurseLoginActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    class custpatient extends BaseAdapter
    {
        ArrayList<PatientModel> ar;

        custpatient(ArrayList<PatientModel> ar) {
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
        public View getView(final int i, View view, ViewGroup viewGroup)
        {
            viewholder v;
            if (view == null)
            {
                view = getLayoutInflater().inflate(R.layout.custlist, viewGroup, false);
                v = new viewholder();
                v.fname = view.findViewById(R.id.patientname);
                v.dept = view.findViewById(R.id.dept);
                v.room = view.findViewById(R.id.room);
                view.setTag(v);
            }
            else
            {
                v = (viewholder) view.getTag();
            }

            v.fname.setText("Student Name : "+ar.get(i).getFirstname()+" "+ar.get(i).getLastname());
            v.dept.setText("Department : "+ar.get(i).getDepartment());
            v.room.setText("Room : "+ar.get(i).getRoom());
            return view;
        }

        class viewholder
        {
            TextView fname,dept,room;
        }
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder ad=new AlertDialog.Builder(PatientActivity.this);
        ad.setMessage("Are You Sure Want To Exit???");
        ad.setTitle("Alert");
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finishAffinity();
                System.exit(0);
            }

        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        }).show();
    }

}