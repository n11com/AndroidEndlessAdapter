package com.n11.tech.endlessadapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.n11.tech.endlessadapter.R;

import java.util.List;

public abstract class EndlessAdapter<T> extends BaseAdapter {

	protected List<T> adapterList;
	protected View pendingView;
	private boolean isLoading;
	protected int size;
	protected Context context;
	protected boolean hasMoreData = true;

	public EndlessAdapter(Context context, List<T> adapterList, boolean autoLoadFirstData) {
		this.adapterList = adapterList;
		this.context = context;
		LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		pendingView = mInflater.inflate(R.layout.pending_row, null);
		if (autoLoadFirstData) {
			size = 1;
		} else {
			size = adapterList.size() == 0 ? 0 : adapterList.size() + 1;
		}
	}

	@Override
	public int getCount() {
		return size;
	}

	@Override
	public T getItem(int position) {
		if (adapterList.size() > position) {
			return adapterList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (position == getCount() - 1 && hasMoreData) {

			if (!isLoading) {
				loadMoreData();

				pendingView.setVisibility(View.VISIBLE);
			}
			return pendingView;
		} else {
			if (convertView == pendingView) {
				convertView = null;
			}
			return getRowView(position, convertView, parent);
		}
	}

	protected void notifyAdapter(List<T> list, int currentPage, int totalPage) {
		adapterList.addAll(list);
		size = adapterList.size();
		clearLoadDataView();
		setHasMoreData(currentPage, totalPage);
		isLoading = false;
		notifyDataSetChanged();
	}

	private void clearLoadDataView() {
		pendingView.setVisibility(View.GONE);
	}

	private void setHasMoreData(int currentPage, int totalPage) {
		if (currentPage == totalPage || totalPage == 0) {
			hasMoreData = false;
		} else {
			hasMoreData = true;
			size++;
		}
	}

	protected abstract View getRowView(int position, View convertView, ViewGroup parent);

	protected void loadMoreData() {
		isLoading = true;
	}

}
