package com.n11.tech.endlessadapter.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.n11.tech.endlessadapter.R;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by onurtaskin on 14/01/14.
 */
public class SampleAdapter extends EndlessAdapter<String> {
    LayoutInflater layoutInflater;
    private int nextPage;

    public SampleAdapter(Context context,List<String> list){
        super(context,list,true);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addRowsHandler = new Handler();
    }

    @Override
    protected void loadMoreData() {
        super.loadMoreData();
        if(nextPage <= pageCount){
            addRowsHandler.postDelayed(addRowsRunnable,2000);
        }
    }

    protected void notifyAdapter(List<String> list) {
        super.notifyAdapter(list, nextPage, pageCount);
        nextPage++;
    }

    @Override
    protected View getRowView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list_row,null);

            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(getItem(position));

        return convertView;
    }

    private static class ViewHolder{
        TextView textView;
    }

    private static final int itemCountPerPage = 10;
    private static final int pageCount = 10;

    private List<String> getStringList(int startIndex){
        List<String> list = new ArrayList<String>();
        for (int i = 0;i<itemCountPerPage; i++) {
            int rowPosition = startIndex*itemCountPerPage+i;
            list.add("Row : "+rowPosition);
        }
        return list;
    };

    private Handler addRowsHandler;

    private Runnable addRowsRunnable = new Runnable() {

        @Override
        public void run() {

            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    notifyAdapter(getStringList(nextPage));
                }
            });
        }
    };
}
