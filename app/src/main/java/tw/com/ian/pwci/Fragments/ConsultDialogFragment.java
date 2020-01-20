package tw.com.ian.pwci.Fragments;


import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.transition.Visibility;

import java.util.Date;
import java.util.List;

import tw.com.ian.pwci.DAO.ConsultDAO;
import tw.com.ian.pwci.DAO.DepartmentDAO;
import tw.com.ian.pwci.Object.Consult;
import tw.com.ian.pwci.R;
import tw.com.ian.pwci.Util.Initializer;


public class ConsultDialogFragment extends DialogFragment {
    private static String param1 = "date";
    private String date1 = null;
    private Initializer app;
    private NumberPicker numberPicker;
    private Spinner consult_department,consult_doctor,choice;
    private DepartmentDAO departmentDAO;
    private String[] department,doctor;
    private List<String> departments,doctors;
    private LinearLayout consult1,consult2;
    private DatePicker datePicker1,datePicker2,datePicker3;
    private Button save;

    public static ConsultDialogFragment newInstance(String args1) {
        ConsultDialogFragment frg = new ConsultDialogFragment();
        Bundle bundle = new Bundle();
        if (args1 != null) {
            bundle.putString(param1, args1);
        }
        frg.setArguments(bundle);
        return frg;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        app = (Initializer) getActivity().getApplication();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            date1 = getArguments().getString(param1);
            app.logv("dialog :"+date1);
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_consult_dialog,container,false);
        departmentDAO = new DepartmentDAO(getContext());
        initNumPicker(view);
        initSpinner(view);
        datePicker1 = view.findViewById(R.id.date1);
        datePicker2 = view.findViewById(R.id.date2);
        datePicker3 = view.findViewById(R.id.date3);
        save = view.findViewById(R.id.save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String department = consult_department.getSelectedItem().toString();
                String doctor = consult_doctor.getSelectedItem().toString();
                String date1 = null,date2= null,date3 = null;
                int num = numberPicker.getValue();
                if (choice.getSelectedItemPosition()==0) {
                    date1 = datePicker1.getYear() + "/" + (datePicker1.getMonth()+1)  + "/" + datePicker1.getDayOfMonth();
                }else {
                    date2 = datePicker2.getYear() + "/" + (datePicker2.getMonth()+1)  + "/" + datePicker2.getDayOfMonth();
                    date3 = datePicker3.getYear() + "/" + (datePicker3.getMonth()+1)  + "/" + datePicker3.getDayOfMonth();
                }
                Consult consult = new Consult((long)0,department,doctor,date1,num,date2,date3);
                ConsultDAO dao = new ConsultDAO(getContext());
                dao.insert(consult);
                app.logv(dao.getAll().toString());
            }
        });
        return view;
    }


    public void initNumPicker(View view)
    {
        numberPicker = view.findViewById(R.id.num);
        numberPicker.setMaxValue(200);
        numberPicker.setMinValue(0);
        numberPicker.setValue(0);
    }

    public void initSpinner(View view)
    {
        consult_department = view.findViewById(R.id.consult_department);
        consult_doctor = view.findViewById(R.id.consult_doctor);
        choice = view.findViewById(R.id.choice);
        consult_department.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, departmentDAO.getDepartments().toArray(new String[0])));
        consult_doctor.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,departmentDAO.getDoctors().toArray(new String[0])));
        choice.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,new String[]{"回診","慢簽"}));
        consult1 = view.findViewById(R.id.consult_layout); //回診
        consult2 = view.findViewById(R.id.consult2_layout); //慢簽
        choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 switch (position){
                    case 0:
                        consult1.setVisibility(View.VISIBLE);
                        consult2.setVisibility(View.GONE);
                        break;
                    case 1:
                        consult2.setVisibility(View.VISIBLE);
                        consult1.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
     }


}
