package com.skripsi.user.etm.pagehome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skripsi.user.etm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TelemarketingFragment extends Fragment {


    public TelemarketingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_telemarketing, container, false);
    }

}
