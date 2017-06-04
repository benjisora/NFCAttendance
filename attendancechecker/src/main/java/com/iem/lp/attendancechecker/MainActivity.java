package com.iem.lp.attendancechecker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ListView attendanceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attendanceList = (ListView) findViewById(R.id.main_activity_listview_attendance);

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        FirebaseListAdapter<User> mAdapter = new FirebaseListAdapter<User>(this, User.class, R.layout.attendance_listview_row, database.getReference("users")) {
            @Override
            protected void populateView(View view, User user, int position) {
                ((TextView)view.findViewById(R.id.attendance_listview_row_name)).setText(user.getFullName());
                if (!user.getLastAttendance().isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("d MMMM HH:mm");
                    ((TextView) view.findViewById(R.id.attendance_listview_row_last)).setText(sdf.format(new Date(Long.parseLong(user.getLastAttendance()))));
                }

            }
        };
        attendanceList.setAdapter(mAdapter);


    }
}
