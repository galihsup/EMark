package com.skripsi.user.etm.pagehome;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skripsi.user.etm.AppsController;
import com.skripsi.user.etm.R;
import com.skripsi.user.etm.adapter.JadwalAdapter;
import com.skripsi.user.etm.model.JadwalModel;
import com.skripsi.user.etm.util.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JadwalFragment extends Fragment {
    ProgressDialog loading;
    private JadwalAdapter jadwalAdapter;
    List<JadwalModel> jadwalModels = new ArrayList<>();
    RecyclerView rvKonten;
    ImageView ivRefresh;

    public JadwalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jadwal, container, false);
        rvKonten = (RecyclerView) view.findViewById(R.id.rv_jadwal_sms);
        rvKonten.setLayoutManager(new LinearLayoutManager(this.getContext()));
        loading = ProgressDialog.show(getActivity(),null,"Loading...",false,false);
        rvKonten.setAdapter(new JadwalAdapter(JadwalFragment.this.getContext(),jadwalModels));
        loading.dismiss();

        ivRefresh = (ImageView) view.findViewById(R.id.iv_refresh_jadwal);
        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJadwal();
            }
        });

        getJadwal();
        return view;
    }

    public void getJadwal() {
        loading = new ProgressDialog(getContext());
        loading.setMessage("Loading...");
        loading.show();
        String url = Constant.ENDPOINT_GET_JADWAL;
        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String token = sharedPrefs.getString(Constant.KEY_SHAREDPREFS_TOKEN, null);
        if (token != null) {

            StringRequest req = new StringRequest(Request.Method.GET, url, successListener(), errListener()) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", "Bearer " + token);
                    return params;
                }
            };
            AppsController.getInstance().addToRequestQueue(req);
        }
    }

    private Response.ErrorListener errListener() {
        return new Response.ErrorListener() {
            @Override
            public  void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Log.e("Error", "Error get view jadwal sms");
                Log.e("Error", String.valueOf(error));
            }
        };
    }
    private Response.Listener<String> successListener(){
        return new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                loading.dismiss();
                try{
                    JSONObject json = new JSONObject(response);
                    String msg = json.getString("message");
                    if (json.getInt("status") == 200) {
                        String jsonOutput = json.getString("data");
                        Log.d("json_out", jsonOutput);
                        Type listType = new TypeToken<List<JadwalModel>>(){}.getType();
                        jadwalModels = (List<JadwalModel>) new Gson().fromJson(jsonOutput, listType);
                        jadwalAdapter = new JadwalAdapter(getContext(), jadwalModels);
                        rvKonten.setAdapter(jadwalAdapter);
                    } else {
                        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }



}
