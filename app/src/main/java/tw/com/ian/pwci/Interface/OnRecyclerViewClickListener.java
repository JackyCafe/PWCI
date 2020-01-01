package tw.com.ian.pwci.Interface;

import android.view.View;

//Recycleviewer 的item 被點擊時
public interface OnRecyclerViewClickListener {
    void onItemClickListener(View view);
    void onItemLongClickListener(View view);
}
