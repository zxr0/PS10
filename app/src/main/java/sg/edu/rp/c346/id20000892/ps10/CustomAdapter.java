package sg.edu.rp.c346.id20000892.ps10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> SongList;


    public CustomAdapter(Context context, int resource, ArrayList<Song> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        SongList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        TextView title = rowView.findViewById(R.id.title1);
        TextView singers = rowView.findViewById(R.id.singers1);
        TextView years = rowView.findViewById(R.id.yearsreleased1);
        TextView stars = rowView.findViewById(R.id.stars1);


        title.setText(SongList.get(position).getTitle());
        singers.setText(SongList.get(position).getSingers());

        String yearsstr = Integer.toString(SongList.get(position).getYear());
        years.setText(yearsstr);

        String starstr = Integer.toString(SongList.get(position).getStar());
        stars.setText(starstr);

        return rowView;
    }

}
