package tw.com.ian.pwci.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.tiper.MaterialSpinner;

import tw.com.ian.pwci.R;


public class MedicationStyleFragment extends Fragment {
    MaterialSpinner  dose_spinner,style_adapter;
    ArrayAdapter<String> dose_adapter,style_adpater;

    public static MedicationStyleFragment newInstance(){
        MedicationStyleFragment fragment = new MedicationStyleFragment();
        return  fragment ;
    }

    public MedicationStyleFragment() {
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
        View v = inflater.inflate(R.layout.fragment_medication_style, container, false);
        dose_spinner = v.findViewById(R.id.dose_spinner);
        style_adapter = v.findViewById(R.id.style_spinner);
        dose_adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,new String[]{"半顆","一顆"});
        dose_spinner.setAdapter(dose_adapter);
        style_adpater= new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,new String[]{"每天早上","睡前","三餐飯前","三餐飯後"});
        style_adapter.setAdapter(style_adpater);
        return v;
    }


}
