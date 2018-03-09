package com.trinityempire.a20180306_mdr_nycschools.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trinityempire.a20180306_mdr_nycschools.R;
import com.trinityempire.a20180306_mdr_nycschools.logs.SchoolsLog;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;

import java.util.List;

public class AdapterFragSchool extends RecyclerView.Adapter<AdapterFragSchool.ViewHolder>
        implements OnBottomReachedListener {
    private int mTag = 0;
    private List<Schools> list;
    private ItemClickListener itemClickListener;
    private LongClickListener longClickListener;
    private OnBottomReachedListener onBottomReachedListener;

    public AdapterFragSchool(List<Schools> list) {
        this.list = list;
    }

    @Override
    public void onBottomReached(int position) {
        SchoolsLog.d("onBottomReached()");

    }
    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener){
        SchoolsLog.d("setOnBottomReachedListener()");
        this.onBottomReachedListener = onBottomReachedListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView mTitle, mSubTitle, extra_info;
        public ViewHolder(View itemView) {
            super(itemView);
            SchoolsLog.d("ViewHolder()");
            mTitle = (TextView) itemView.findViewById(R.id.mTitle);
            mSubTitle = (TextView) itemView.findViewById(R.id.subTitle);
            extra_info = (TextView) itemView.findViewById(R.id.extra_info);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            SchoolsLog.d("onClick " + getAdapterPosition() + " " );
            itemClickListener.OnItemClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            longClickListener.LongClickListener(getAdapterPosition());
            return true;
        }
    }

    @Override
    public AdapterFragSchool.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SchoolsLog.d("onCreateViewHolder()");

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_frag_schools, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterFragSchool.ViewHolder holder, int position) {
        SchoolsLog.d("onBindViewHolder()");
        holder.mTitle.setText(list.get(position).getSchool_name());
        holder.mSubTitle.setText(list.get(position).getOverview_paragraph());
        holder.extra_info.setText(list.get(position).getCity());

        if (position == getItemCount() - 1){

            onBottomReachedListener.onBottomReached(position + 1);

        }
    }

    @Override
    public int getItemCount() {
        SchoolsLog.d("getItemCount()");

        return list.size();
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        SchoolsLog.d("setOnitemClickListener()-> an item was clicked on");
        this.itemClickListener = itemClickListener;
    }
    public void setOnLongClickListener(LongClickListener longClickListener) {
        SchoolsLog.d("setOnLongClickListener(0 -> item is long clicked on");
        this.longClickListener = longClickListener;
    }

}
