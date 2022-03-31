package com.example.samplelinearlayout;

import android.widget.PopupMenu;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    //Deklarasi variabel dengan jenis data listview
    private ListView list;

    //Memanggil class listviewAdapter dan diinisiasi menjadi variabel adapter
    private ListViewAdapter adapter;

    //Deklarasi array untui menyimpan nama
    String[] listNama;

    //Memanggil class ClassNama
    public static ArrayList<ClassNama> classNamaArrayList = new ArrayList<ClassNama>();

    //Membuat objek Bundle
    Bundle bundle = new Bundle();

    //Membuat objek intent
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Menyimpan nama didalam array listnama
        listNama = new String[]{"Inayah", "Ilham", "Eris",
                "Fikri","Maul","Intan","Vina","Gita",
                "Vian","Lutfi"};

        list = findViewById(R.id.listkontak);

        //membuat objek dari class Classnama menjadi arraylist
        classNamaArrayList = new ArrayList<>();

        //Membaca seluruh data pada array ListNama
        for (int i = 0; i < listNama.length; i++) {
            //Membuat objek class nama data
            ClassNama classNama = new ClassNama(listNama[i]);
            //Binds strings ke array
            classNamaArrayList.add(classNama);
        }

        //Membuat objek dari listviewAdapter;
        adapter = new ListViewAdapter(this);

        //Binds Adapter ke Listview
        list.setAdapter(adapter);

            //Membuat event dari list onclick
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //Deklarasi variabel nama yang berisi item yang diklik
                    String nama = classNamaArrayList.get(position).getName();

                    //memasukan value dari variabel nama dengan kunci "a"
                    //dan dimasukkan ke dalam bundle
                    bundle.putString("a",nama.trim());

                    //membuat objek popup menu
                    PopupMenu pm = new PopupMenu(getApplicationContext(),view);

                    //Membuat event untuk popup menu ketika dipilih
                    pm.setOnMenuItemClickListener(Home_Activity.this);

                    //Menampilkan popup menu dari layout menu
                    pm.inflate(R.menu.popup_menu);

                    //Menampilkan popup menu
                    pm.show();
                }
            });


    }
    //event yang terjadi ketika menu dipilih

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.mnview:
                intent = new Intent(getApplicationContext(),ActivityLihatData.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.mnedit:
                Toast.makeText(getApplicationContext(),"Ini untuk edit kontak",
                        Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }

}
