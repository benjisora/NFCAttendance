package com.projects.dreamteam.nfcattendance;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddUserActivity extends AppCompatActivity {

    @BindView(R.id.add_user_first_name)
    EditText userFirstName;

    @BindView(R.id.add_user_last_name)
    EditText userLastName;

    @BindView(R.id.add_user_student_id)
    EditText userStudentId;

    @BindView(R.id.add_user_class)
    EditText userClass;

    @BindView(R.id.add_user_group)
    EditText userGroup;

    final String[][] techList = new String[][]{
            new String[]{
                    NfcA.class.getName(),
                    MifareClassic.class.getName()
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        ButterKnife.bind(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            ArrayList<String> values = new ArrayList<>();
            String idcard = "";
            try {
                for (int i = 0; i < 4; i++) {
                    values.add(Integer.toHexString(tag.getId()[i]).toUpperCase());
                    idcard += values.get(i).substring(values.get(i).length() - 2, values.get(i).length());
                }
                Student student = new Student(userFirstName.getText().toString(), userLastName.getText().toString(), userGroup.getText().toString(), userStudentId.getText().toString());

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                database.getReference("users").child(idcard).getRef().setValue(student);
                database.getReference("classes").child(userClass.getText().toString()).child(idcard).getRef().setValue(idcard);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.finish();
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter filter = new IntentFilter();
        filter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{filter}, techList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.disableForegroundDispatch(this);
    }

    @IgnoreExtraProperties
    private class Student {
        String firstname;
        String lastname;
        String lastattendance = "1";
        String group;
        String studentid = "p1461208";

        public String getStudentid() {
            return studentid;
        }

        public void setStudentid(String studentid) {
            this.studentid = studentid;
        }

        public Student() {
        }

        public Student(String firstname, String lastname, String group, String studentid) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.group = group;
            this.lastattendance = String.valueOf(System.currentTimeMillis());
            this.studentid = studentid;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getLastattendance() {
            return lastattendance;
        }

        public void setLastattendance(String lastattendance) {
            this.lastattendance = lastattendance;
        }


        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

    }
}
