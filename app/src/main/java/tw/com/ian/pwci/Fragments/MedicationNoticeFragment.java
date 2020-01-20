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
public class MedicationNoticeFragment extends Fragment {


    public MedicationNoticeFragment() {
        // Required empty public constructor
    }

    public static MedicationNoticeFragment newInstance()
    {
        MedicationNoticeFragment fragment = new MedicationNoticeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medication_notice, container, false);
    }

}
