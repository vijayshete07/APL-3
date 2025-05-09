package com.example.fragment_pgm;  // Ensure this matches your app's package name

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment's layout
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        // Find the TextView and set text
        TextView textView = view.findViewById(R.id.textViewFragmentOne);
        textView.setText("This is Fragment One!");

        return view;
    }
}
