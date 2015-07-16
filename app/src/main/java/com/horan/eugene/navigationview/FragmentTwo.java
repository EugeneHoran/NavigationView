package com.horan.eugene.navigationview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTwo extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_holder, container, false);
        TextView txtHolder = (TextView) v.findViewById(R.id.txtHolder);
        txtHolder.setText("Fragment Two");
        return v;
    }
}
