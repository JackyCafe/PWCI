package tw.com.ian.pwci.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tw.com.ian.pwci.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralSetupFragment extends Fragment {


    public GeneralSetupFragment() {
        // Required empty public constructor
    }

    public static GeneralSetupFragment newInstance()
    {
        GeneralSetupFragment fragment = new GeneralSetupFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general_setup, container, false);
    }

}
