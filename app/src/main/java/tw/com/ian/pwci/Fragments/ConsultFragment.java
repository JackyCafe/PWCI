package tw.com.ian.pwci.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import tw.com.ian.pwci.R;
import tw.com.ian.pwci.Util.Initializer;


public class ConsultFragment extends Fragment {

    CalendarView calendarView ;
    Initializer app;

    public ConsultFragment() {

     }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        app = (Initializer)getActivity().getApplication();
    }

    public static ConsultFragment newInstance() {
        ConsultFragment fragment = new ConsultFragment();
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
        View v =  inflater.inflate(R.layout.fragment_consult, container, false);
        calendarView = v.findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date1 = year+"/"+(month+1)+"/"+dayOfMonth;
                ConsultDialogFragment fragment = ConsultDialogFragment.newInstance(date1);
                fragment.show(getChildFragmentManager(),"dialog_fragment");

            }
        });

         return v;
    }

}
