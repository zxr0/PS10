package sg.edu.rp.c346.id20000892.ps10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    Song data;
    TextView idtext;
    EditText Etitle,Esinger,Eyear;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    RadioButton r4;
    RadioButton r5;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        idtext = findViewById(R.id.idtext2);
        Etitle = findViewById(R.id.Etitle);
        Esinger = findViewById(R.id.Esinger);
        Eyear = findViewById(R.id.Eyear);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r11);
        r3 = findViewById(R.id.r2);
        r4 = findViewById(R.id.r3);
        r5 = findViewById(R.id.r4);
        confirm = findViewById(R.id.confirm);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        idtext.setText("ID: " + data.getId());
        Etitle.setText(data.getTitle());
        Esinger.setText(data.getSingers());
        Eyear.setText(data.getYear());
        int star = data.getStar();

        if (star == 1){
            r1.setChecked(true);
        }
        else if (star == 2){
            r2.setChecked(true);
        }
        else if (star == 3){
            r3.setChecked(true);
        }
        else if (star == 4){
            r4.setChecked(true);
        }
        else if (star == 5){
            r5.setChecked(true);
        }


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int star = 0;
                if (r1.isChecked()){
                    star = 1;
                }
                if (r2.isChecked()){
                    star = 2;
                }
                if (r3.isChecked()){
                    star = 3;
                }
                if (r4.isChecked()){
                    star = 4;
                }
                if (r5.isChecked()){
                    star = 5;
                }
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setTitle(Etitle.getText().toString());
                data.setSingers(Esinger.getText().toString());
                String year1 = Eyear.getText().toString();
                int year2 = Integer.parseInt(year1);
                data.setYear(year2);
                data.setStar(star);
                dbh.updateSong(data);
                dbh.close();

                finish();
            }
        });
    }

}