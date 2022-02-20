package net.phipe.albumvolley.ReciclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import net.phipe.albumvolley.Model.Heroe;
import net.phipe.albumvolley.R;
import net.phipe.albumvolley.Request.MySingleton;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    ArrayList<Heroe> listaAlbum;
    Context context;

    public RecyclerViewAdapter(Context context, ArrayList<Heroe> albums){
        this.context=context;
        listaAlbum = albums;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtName.setText(listaAlbum.get(position).getName());
        holder.txturl.setText(listaAlbum.get(position).getImgURL());
        requestImageMethod(listaAlbum.get(position).getImgURL(),holder.img);
    }

    @Override
    public int getItemCount() {
        return listaAlbum.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtName, txturl;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.item_text);
            txturl = itemView.findViewById(R.id.item_url);
            img = itemView.findViewById(R.id.item_img);
        }
    }

    void requestImageMethod(String url, ImageView img){
        ImageRequest imgReq = new
                ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        img.setImageBitmap(response);
                    }

                },80,80,
                ImageView.ScaleType.CENTER,
                Bitmap.Config.ALPHA_8,
                error -> {
                    error.printStackTrace();
                }
        );
        MySingleton.getInstance(context).addToRequestQueue(imgReq);
    }
}
