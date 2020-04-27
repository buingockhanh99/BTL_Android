package com.hktstudio.thibanglaixe.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hktstudio.thibanglaixe.Adapter.AdapterRecyclerViewBienBao;
import com.hktstudio.thibanglaixe.Adapter.AdapterRecyclerViewLichSuBaiThi;
import com.hktstudio.thibanglaixe.MyItemDecoration;
import com.hktstudio.thibanglaixe.Object.DeThi;
import com.hktstudio.thibanglaixe.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;

public class LichSuBaiThiActivity extends AppCompatActivity {
    RecyclerView rcv_lichSuBaiThi;
    AdapterRecyclerViewLichSuBaiThi adapterRecyclerViewLichSuBaiThi;
    List<DeThi> listDeThi;
    Toolbar toolbar;
    Button btn_menu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsubaithi1);

        btn_menu = findViewById(R.id.btn_menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        rcv_lichSuBaiThi = findViewById(R.id.rcv_lichSuBaiThi);
        listDeThi = docFile("lichsu.txt");
        adapterRecyclerViewLichSuBaiThi = new AdapterRecyclerViewLichSuBaiThi(this,listDeThi);
        rcv_lichSuBaiThi.setLayoutManager(new GridLayoutManager(this,4));
        rcv_lichSuBaiThi.addItemDecoration(new MyItemDecoration(this, R.dimen.item_offset));
        rcv_lichSuBaiThi.setAdapter(adapterRecyclerViewLichSuBaiThi);
        Log.d("SIZE",listDeThi.size()+"");

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    public List<DeThi> docFile(final String fileName){
        List<DeThi> listDeThi = new ArrayList<>();

        try {
            File file = getFileStreamPath(fileName);
            if (file==null||!file.exists()){
                file = new File(fileName);
            }
            else {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                listDeThi = (List<DeThi>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return listDeThi;
    }


}
