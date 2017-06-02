package com.iem.lp.attendancechecker;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by aturlier on 31/05/17.
 */

public class AttendanceAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return userList().size();
    }

    @Override
    public Object getItem(int position) {
        return userList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


    private ArrayList<User> userList() {
        return DataRepository.getInstance().userList;
    }
}
