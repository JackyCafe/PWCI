package tw.com.ian.pwci.Fragments;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import tw.com.ian.pwci.Adapter.ConsultAdapter;
import tw.com.ian.pwci.DAO.ConsultDAO;
import tw.com.ian.pwci.Object.Consult;
import tw.com.ian.pwci.R;
import tw.com.ian.pwci.Util.Initializer;
import tw.com.ian.pwci.Util.Misc;


public class ConsultFragment extends Fragment implements ConsultDialogFragment.NoticeDialogListener{

    CalendarView calendarView;
    Initializer app;
    ConsultDAO dao;
    RecyclerView recyclerView;
    ConsultAdapter adpater;
    List<Consult> datas;
    SimpleDateFormat sdf ;
    ConsultDialogFragment dialog;
    String dd = "";
    public ConsultFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        app = (Initializer) getActivity().getApplication();
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
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_consult, container, false);
        calendarView = v.findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date1 = year + "/" + (month + 1) + "/" + dayOfMonth;
                dialog = ConsultDialogFragment.newInstance(year,month,dayOfMonth);
                dialog.setTargetFragment(ConsultFragment.this,22);
                dialog.show(getFragmentManager(),"dialog_fragment");

            }
        });

        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.logv("setOnClickListener");
            }
        });
        long calendar_date = calendarView.getDate();
        sdf = new SimpleDateFormat("yyyy/MM/dd");
        String selectedDate = sdf.format(new Date(calendar_date));
        dao = new ConsultDAO(getContext());
        recyclerView = v.findViewById(R.id.consult_recycle_view);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layout);


        try {
            sdf=new SimpleDateFormat(Misc.YYYYMM);
            dd = sdf.format(calendarView.getDate());
        }catch (Exception e){

        }
        datas = dao.getByDate(dd);
        adpater = new ConsultAdapter(datas);
        recyclerView.setAdapter(adpater);
        return v;
    }


    @Override
    public void onDialogPositiveClick() {
        datas = dao.getByDate(dd);
        adpater = new ConsultAdapter(datas);
        adpater.notifyDataSetChanged();
        recyclerView.setAdapter(adpater);
    }

    @Override
    public void onDialogNegativeClick() {

    }
}


