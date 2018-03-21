package com.skripsi.user.etm.pagehome;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.skripsi.user.etm.adapter.TelemarketingAdapter;
import com.skripsi.user.etm.model.TelemarketingModel;
import com.skripsi.user.etm.util.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class TelemarketingFragment extends Fragment {

    RecyclerView rvSearch;
    ImageView ivExport;
    ImageView ivRefresh;
    Intent intent;
    TextInputEditText tieSearch;
    ProgressDialog loading;
    private TelemarketingAdapter telemarketingAdapter;
    List<TelemarketingModel> telemarketingModels = new ArrayList<>();

    public TelemarketingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_telemarketing, container, false);

        /** ini untuk membinding menu di toolbar **/
        ivExport = (ImageView) view.findViewById(R.id.iv_exp_call);
        ivExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://emark.yippytech.com/etmweb/download_data_telemarketing.php"));
                startActivity(viewIntent);
            }
        });

        rvSearch = (RecyclerView) view.findViewById(R.id.rv_siswa_call);
        rvSearch.setLayoutManager(new LinearLayoutManager(this.getContext()));
        loading = ProgressDialog.show(getActivity(),null,"Loading...",false,false);
        HashMap<String, Integer> mapIndex = calculateIndexesForName(telemarketingModels);
        rvSearch.setAdapter(new TelemarketingAdapter(TelemarketingFragment.this.getContext(),telemarketingModels,mapIndex));
        loading.dismiss();

        getSiswa();

        tieSearch = (TextInputEditText) view.findViewById(R.id.txt_call_search);
        tieSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getSiswaBySearch(tieSearch.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ivRefresh = (ImageView) view.findViewById(R.id.iv_refresh_call);
        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSiswa();
            }
        });

        return view;
    }

    public void getSiswaBySearch(String key) {
        loading = new ProgressDialog(getContext());
        loading.setMessage("Loading...");
        loading.show();
        String url = Constant.ENDPOINT_GET_TELEMARKETING_BY_ID+key;
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

    public void getSiswa() {
        loading = new ProgressDialog(getContext());
        loading.setMessage("Loading...");
        loading.show();
        String url = Constant.ENDPOINT_GET_TELEMARKETING;
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
                Log.e("Error", "Error get view siswa");
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
                        Type listType = new TypeToken<List<TelemarketingModel>>(){}.getType();
                        telemarketingModels = (List<TelemarketingModel>) new Gson().fromJson(jsonOutput, listType);
                        HashMap<String, Integer> mapIndex = calculateIndexesForName(telemarketingModels);
                        telemarketingAdapter = new TelemarketingAdapter(getContext(), telemarketingModels, mapIndex);
                        rvSearch.setAdapter(telemarketingAdapter);
                        FastScrollRecyclerViewItemDecoration decoration =
                                new FastScrollRecyclerViewItemDecoration(TelemarketingFragment.this.getContext());
                        rvSearch.addItemDecoration(decoration);
                        rvSearch.setItemAnimator(new DefaultItemAnimator());
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

    private HashMap<String, Integer> calculateIndexesForName(List<TelemarketingModel> items){
        HashMap<String, Integer> mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i<items.size(); i++){

            String name = items.get(i).getNama();
            String index = name.substring(0,1);
            index = index.toUpperCase();

            if (!mapIndex.containsKey(index)) {
                mapIndex.put(index, i);
            }
        }
        return mapIndex;
    }

}
