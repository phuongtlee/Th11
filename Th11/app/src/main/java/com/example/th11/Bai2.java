package com.example.th11;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Bai2 extends AppCompatActivity {

    ArrayList<Person> arrayList = new ArrayList<Person>();
    ListView listView;
    dbDatasource datasource;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        listView = findViewById(R.id.list);

        datasource = new dbDatasource(this);
        datasource.open();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, loadData());
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Person sp = getItem(position);
                AlertDialog alertDialog = alertInfo(sp).create();
                alertDialog.show();
                return true;
            }
        });
    }

    private List<String> loadData() {
        List<Person> spList = datasource.loadData();
        List<String> productNames = new ArrayList<>();

        for (Person sanPham : spList) {
            productNames.add(sanPham.getName());
        }

        return productNames;
    }

    public Person getItem(int position){
        ArrayList<Person> listSP= (ArrayList<Person>) datasource.loadData();
        Person p = listSP.get(position);
        return p;
    }

    public AlertDialog.Builder alertInfo(Person sp){
        AlertDialog.Builder alert = new AlertDialog.Builder(Bai2.this);
        alert.setTitle("Thông tin chi tiết");
        alert.setMessage("Mã nhân viên: " + sp.getId() + "\n"
                + "Tên nhân viên: " + sp.getName() + "\n"
                + "Tuổi: " + sp.getAge());
        alert.setPositiveButton("Xóa nhân viên", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                datasource.delete(sp);
                finish();
                Intent intent = new Intent(Bai2.this, Bai2.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("Trở về", null);
        alert.show();
        return alert;
    }
}