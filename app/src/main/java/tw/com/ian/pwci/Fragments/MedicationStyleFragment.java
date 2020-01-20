package tw.com.ian.pwci.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tw.com.ian.pwci.R;


public class MedicationStyleFragment extends Fragment {


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
         return inflater.inflate(R.layout.fragment_medication_style, container, false);
    }


}
