package sg.edu.rp.c346.id20000892.ps10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class list extends AppCompatActivity {
    ArrayList<Song> al;
    CustomAdapter aa;
    Button btn;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv = findViewById(R.id.list);
        btn = findViewById(R.id.btn);

        al = new ArrayList<>();
        aa = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(aa);

        al.clear();
        DBHelper dbh = new DBHelper(list.this);
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(list.this);
                al.clear();
                al.addAll(dbh.getAllSongs5());
                aa.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song target = al.get(position);
                Intent i = new Intent(list.this,
                        EditActivity.class);
                i.putExtra("data", target);
                startActivity(i);
            }
        });
    }
}