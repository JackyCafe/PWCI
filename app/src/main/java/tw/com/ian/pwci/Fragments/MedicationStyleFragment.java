package tw.com.ian.pwci.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.tiper.MaterialSpinner;

import tw.com.ian.pwci.R;
import tw.com.ian.pwci.Util.Initializer;


public class MedicationStyleFragment extends Fragment {
    MaterialSpinner  dose_spinner,style_spinner;
    ArrayAdapter<String> dose_adapter,style_adapter;
    Button btn ;
    String str_dose,str_style,str_medication ;
    TextInputEditText medical_name;
    Initializer app;

    public static MedicationStyleFragment newInstance(){
        MedicationStyleFragment fragment = new MedicationStyleFragment();
        return  fragment ;
    }

    public MedicationStyleFragment() {
     }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        app = (Initializer) getActivity().getApplication();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_medication_style, container, false);

        medical_name = v.findViewById(R.id.medical_name);

        dose_spinner = v.findViewById(R.id.dose_spinner);
        style_spinner = v.findViewById(R.id.style_spinner);
        dose_adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,new String[]{"半顆","一顆"});
        dose_spinner.setAdapter(dose_adapter);
        style_adapter= new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,new String[]{"每天早上","睡前","三餐飯前","三餐飯後"});
        style_spinner.setAdapter(style_adapter);

        btn = v.findViewById(R.id.save);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_medication = medical_name.getText().toString();
                str_dose = dose_spinner.getSelectedItem().toString();
                str_style = style_spinner.getSelectedItem().toString();

                app.logv(str_dose);

            }
        });

        return v;
    }


}
