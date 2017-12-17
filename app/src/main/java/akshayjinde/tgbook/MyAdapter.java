package akshayjinde.tgbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Akshay Jinde on 23-09-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<ListItem> listitems;
    Context context;

    public MyAdapter(List<ListItem> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ListItem listItem = listitems.get(position);

        holder.student_name.setText(listItem.getStudent_name());
        holder.student_year.setText(listItem.getStudent_year());
        holder.student_div.setText(listItem.getStudent_div());
        holder.student_roll_no.setText(listItem.getStudent_roll_no());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    default:
                        Intent i =new Intent(context, student_info.class);
                        //i.putExtra("roll_no",listItem.getStudent_roll_no());
                        //i.putExtra("class",listItem.getStudent_year());
                        //i.putExtra("div",listItem.getStudent_div());
                        context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView student_name;
        public TextView student_year;
        public TextView student_div;
        public TextView student_roll_no;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            student_name = (TextView) itemView.findViewById(R.id.student_name);
            student_year = (TextView) itemView.findViewById(R.id.student_year);
            student_div = (TextView) itemView.findViewById(R.id.student_div);
            student_roll_no = (TextView) itemView.findViewById(R.id.student_roll_no);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}
