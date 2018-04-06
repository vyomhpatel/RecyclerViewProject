package b12app.vyom.com.recyclerviewproject;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ContactsData> contactsList;
    DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setHasFixedSize(true);
         dataAdapter= new DataAdapter(contactsList,MainActivity.this);
        recyclerView.setAdapter(dataAdapter);

        copyData();









    }

    public void copyData(){
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Progress");
        progressDialog.setMessage("Downloading data...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.androidhive.info/contacts/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.cancel();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("contacts");

                    for(int i = 0 ; i<jsonArray.length();i++){

                        JSONObject record = jsonArray.getJSONObject(i);
                        String name = record.getString("name");
                        String address = record.getString("address");

                        JSONObject phone  = record.getJSONObject("phone");
                        String mobile = phone.getString("mobile");

                        ContactsData contactsData = new ContactsData(name,address,mobile);
                        contactsList.add(contactsData);
                        dataAdapter.notifyDataSetChanged();



                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {




            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);

    }

}
