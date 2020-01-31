package tw.com.ian.pwci.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tw.com.ian.pwci.Object.Consult;
import tw.com.ian.pwci.R;

public class ConsultAdapter extends RecyclerView.Adapter<ConsultAdapter.VH> {
    private List<Consult> datas;
    private View view;

    public ConsultAdapter(List<Consult> datas){
        this.datas = datas;
    }
    class VH extends  RecyclerView.ViewHolder{
        TextView date1,dept,doctor,date2,date3;
        public VH(@NonNull View itemView) {
           super(itemView);
           view = itemView;
           date1 = view.findViewById(R.id.consult_date1);
           dept = view.findViewById(R.id.consult_dept);
           doctor = view.findViewById(R.id.consult_doctor);
           date2  = view.findViewById(R.id.consult_date2);
           date3  = view.findViewById(R.id.consult_date3);

        }

    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consult_item,parent,false);
        VH viewholder= new VH(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.dept.setText(datas.get(position).getDepartment());
        holder.doctor.setText(datas.get(position).getDoctor());
        holder.date1.setText(datas.get(position).getDate1());
        holder.date2.setText(datas.get(position).getDate2());
        holder.date3.setText(datas.get(position).getDate3());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
