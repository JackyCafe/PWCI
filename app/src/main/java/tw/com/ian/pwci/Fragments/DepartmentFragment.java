package tw.com.ian.pwci.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import tw.com.ian.pwci.DAO.DepartmentDAO;
import tw.com.ian.pwci.Object.Department;
import tw.com.ian.pwci.R;
import tw.com.ian.pwci.Util.Initializer;


public class DepartmentFragment extends Fragment {

    TextInputEditText text_department,text_doctor;
    Initializer app;
    Button btn;
    String str_department,str_doctor;
    Department department;
    DepartmentDAO dao;
    public DepartmentFragment() {
        // Required empty public constructor
    }


     public static DepartmentFragment newInstance() {
        DepartmentFragment fragment = new DepartmentFragment();
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        app = (Initializer)getActivity().getApplication();
        dao = new DepartmentDAO(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.fragment_department, container, false);
         text_department = v.findViewById(R.id.department);
         text_doctor = v.findViewById(R.id.doctor);

         btn = v.findViewById(R.id.save);

         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 str_department = text_department.getText().toString();
                 str_doctor = text_doctor.getText().toString();
                department = new Department(0,str_department,str_doctor);
                dao.insert(department);

                app.logv(dao.getAll().toString());
             }
         });
         return v;
    }

}
