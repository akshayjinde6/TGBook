package akshayjinde.tgbook;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class tg_welcome extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ProgressDialog progressDialog;
    List<ListItem> listItems;
    String json="";

    private String class_stu;
    private String division;
    private String roll_no;
    private String name_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tg_welcome);

        progressDialog = new ProgressDialog(this);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        listItems = new ArrayList<>();

        //new DataLoad().execute("http://35.154.136.177/studentinfo");

        loadlist1();
        /*for(int i=0;i<=10;i++){
            ListItem item = new ListItem(
                    "student "+ i,
                    "te",
                    "A",
                    "56");
            listItems.add(item);
        }*/
    }

    public void loadlist1(){
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,"http://35.154.136.177/studentinfo",
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        try {
                            progressDialog.dismiss();
                            JSONObject main = new JSONObject(s);
                            JSONArray array = main.getJSONArray("payload");

                            for(int i=0;i<array.length();i++){
                                JSONObject sub = array.getJSONObject(i);
                                name_ = sub.getString("name");
                                class_stu = sub.getString("class");
                                division = sub.getString("division");
                                roll_no = sub.getString("rollo");

                                ListItem item = new ListItem(name_,class_stu,division,roll_no);
                                listItems.add(item);
                            }
                            adapter = new MyAdapter(listItems,getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /*public void loadList(String json){

        try {
            JSONObject main = new JSONObject(json);
            JSONArray array = main.getJSONArray("payload");
            JSONObject sub = array.getJSONObject(0);


            for(int i=0;i<array.length();i++){
                name_ = sub.getString("name");
                class_stu = sub.getString("class");
                division = sub.getString("division");
                roll_no = sub.getString("rollo");

                ListItem item = new ListItem(name_,class_stu,division,roll_no);
                listItems.add(item);
            }
            progressDialog.dismiss();
            adapter = new MyAdapter(listItems,getApplicationContext());
            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public class DataLoad extends AsyncTask<String,String,String>{

        String responce;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading Data...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuffer sb = new StringBuffer();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                responce = sb.toString();
                return responce;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            if(s.isEmpty()){
                Toast.makeText(tg_welcome.this,"No Response ! Try Again ",Toast.LENGTH_LONG).show();
                return;
            }
            else{
            loadList(s);
            }
        }
    }*/
}
