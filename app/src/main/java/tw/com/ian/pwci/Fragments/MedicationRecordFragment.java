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
public class MedicationRecordFragment extends Fragment {


    public MedicationRecordFragment() {
        // Required empty public constructor
    }

    public static MedicationRecordFragment newInstance() {
        MedicationRecordFragment fragment = new MedicationRecordFragment();
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
        return inflater.inflate(R.layout.fragment_medication_record, container, false);
    }

}
