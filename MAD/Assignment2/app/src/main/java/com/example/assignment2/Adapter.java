package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    String Name[],Desc[];
    int Img[];
    String Url [] = {
            "Lamb_of_God_(band)",
            "Fossils_(band)",
            "Aurthohin",
            "Tool_(band)",
            "Porcupine_Tree",
            "Dream_Theater",
            "Thaikkudam_Bridge",
            "Ne_Obliviscaris_(band)",
            "Pink_Floyd",
            "Trivium",
            "Metallica"
    };
    Context context;
    public Adapter(Context ctx, String s1[], String s2[], int s3[]) {
        this.context = ctx;
        this.Name = s1;
        this.Img = s3;
        this.Desc = s2;
        Log.d("DEBUG","On Create "+ Name.length +" Recycler");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout, parent,false);
        Log.d("DEBUG","On Create "+ Name.length +" Recycler2 ");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Text.setText(Name[position]);
        holder.DescText.setText(Desc[position]);
        holder.Img.setImageResource(Img[position]);
        holder.mainLayout.setOnClickListener( new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
                  if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                          connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                      Intent intent = new Intent(context, main_activity.class);
                      intent.putExtra("Url", Url[position]);
                      context.startActivity(intent);
                  } else {
                      Toast.makeText(context,"Internet Not Available", Toast.LENGTH_SHORT).show();
                  }
              }
          }
        );
    }

    @Override
    public int getItemCount() {
        return this.Name.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Text, DescText;
        ImageView Img;
        ConstraintLayout mainLayout;
        public  ViewHolder(@NonNull View itemView) {
            super(itemView);
            Text = itemView.findViewById(R.id.Name);
            DescText = itemView.findViewById(R.id.Desc);
            Img = itemView.findViewById(R.id.Img);
            mainLayout = itemView.findViewById(R.id.con);
        }
    }
}
