package tw.com.ian.pwci.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tw.com.ian.pwci.Interface.OnRecyclerViewClickListener;
import tw.com.ian.pwci.Object.Msg;
import tw.com.ian.pwci.R;


public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MyViewHolder>{
    List<Msg> data;
    private OnRecyclerViewClickListener listener;

    public  class MyViewHolder extends RecyclerView.ViewHolder {
        private View v;
        private LinearLayout leftLayout;
        private  LinearLayout rightLayout;
        private TextView leftMsg;
        private TextView rightMsg;
        public MyViewHolder(@NonNull View v) {
            super(v);
            this.v = v;
            leftLayout=(LinearLayout) itemView.findViewById(R.id.left_layout);
            rightLayout=(LinearLayout)itemView.findViewById(R.id.right_layout);
            leftMsg = this.v.findViewById(R.id.left_msg);
            rightMsg = this.v.findViewById(R.id.right_msg);
        }
    }

    public MsgAdapter(List<Msg> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        MyViewHolder vh = new MyViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Msg msg=data.get(position);
        if (listener != null)
        {
             holder.leftMsg.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     listener.onItemClickListener(v);        }
             });
        }
        switch (msg.getType()){
            case RECEIVED://接收的消息
                holder.leftLayout.setVisibility(View.VISIBLE);
                holder.rightLayout.setVisibility(View.GONE);
                holder.leftMsg.setText(msg.getMsg());
                break;
            case SENT://發出的消息
                holder.leftLayout.setVisibility(View.GONE);
                holder.rightLayout.setVisibility(View.VISIBLE);
                holder.rightMsg.setText(msg.getMsg());
                break;
        }

     }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(OnRecyclerViewClickListener itemClickListener)
    {
        listener = itemClickListener;
    }



}