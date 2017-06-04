package com.iem.lp.attendancechecker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by aturlier on 31/05/17.
 */

public class AttendanceAdapter extends BaseAdapter {

    LayoutInflater inflater;

    public AttendanceAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return userList().size();
    }

    @Override
    public User getItem(int position) {
        return userList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.attendance_listview_row, parent);
        }

        TextView nameText = (TextView) convertView.findViewById(R.id.attendance_listview_row_name);
        TextView checkAttendanceText = (TextView) convertView.findViewById(R.id.attendance_listview_row_last);

        nameText.setText(getItem(position).getFullName());
        SimpleDateFormat sdf = new SimpleDateFormat("d M HH:mm");
        checkAttendanceText.setText(sdf.format(getItem(position).getLastAttendance()));

        return convertView;
    }

    private ArrayList<User> userList() {
        return DataRepository.getInstance().userList;
    }
}
