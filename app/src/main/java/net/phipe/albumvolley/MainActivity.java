package net.phipe.albumvolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.phipe.albumvolley.Model.Heroe;
import net.phipe.albumvolley.ReciclerView.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Heroe> list;
    private RequestQueue rq;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        rq = Volley.newRequestQueue(this);
        loadData();
        recyclerView = findViewById(R.id.album_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    private void loadData(){
        String url = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String value = response.get("heroes").toString();
                    JSONArray array = new JSONArray(value);
                    for(int i=0; i<array.length(); i++){
                        JSONObject object = new JSONObject(array.get(i).toString());
                        Log.d("JSON", array.get(i).toString());
                        Heroe heroe = new Heroe(object.getString("name"), object.getString("imageurl"));
                        list.add(heroe);
                        recyclerViewAdapter.notifyItemRangeInserted(list.size(), 1);
                    }
                }catch (JSONException exception){
                    exception.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq.add(jsonObjectRequest);
    }
}