package sg.edu.rp.c346.id20000892.ps10;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnList;
    EditText editTitle,editSinger,editYear;
    RadioButton r1,r2,r3,r4,r5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTitle = findViewById(R.id.editTitle);
        editSinger = findViewById(R.id.editSinger);
        editYear = findViewById(R.id.editYear);
        btnList = findViewById(R.id.btnList);
        btnAdd = findViewById(R.id.btnReset);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r11);
        r3 = findViewById(R.id.r2);
        r4 = findViewById(R.id.r3);
        r5 = findViewById(R.id.r4);




        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check = "";
                if(r1.isChecked()){
                    check = "1";
                }
                if(r2.isChecked()){
                    check = "2";
                }
                if(r3.isChecked()){
                    check = "3";
                }
                if(r4.isChecked()){
                    check = "4";
                }
                if(r5.isChecked()){
                    check = "5";
                }

                String title = editTitle.getText().toString();
                String singer = editSinger.getText().toString();
                String year = editYear.getText().toString();
                String star = check;
                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.insertSong(title, singer, year, star);

            }

        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        list.class);
                startActivity(i);
            }
        });

    }

}