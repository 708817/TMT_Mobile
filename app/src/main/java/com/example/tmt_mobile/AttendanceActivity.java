/*
DESCRIPTION OF AttendanceActivity.java
        Dito yung tap mode ng app. Once na nascan ng phone yung RFID, checheck niya kung enrolled
        student siya sa class na pinili ng user.
*/

package com.example.tmt_mobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AttendanceActivity extends AppCompatActivity implements AsyncResponse{

    private WSSetAttendance wsSetAttendance;

    private Intent oldIntent;
    private Bundle oldBundle;

    private ImageView ivStudent;
    private TextView tvStatement_1;
    private Toast toast;
    private AlertDialog.Builder builder;

    private String email, course, section, name, result;

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        // XML UI Assignment START
        ivStudent = (ImageView) findViewById(R.id.ivStudent);
        tvStatement_1 = (TextView) findViewById(R.id.tvStatement_1);
        // XML UI Assignment END

        // GetExtras START
        oldIntent = getIntent();
        oldBundle = oldIntent.getExtras();
        email = oldBundle.getString("email");
        course = oldBundle.getString("course");
        section = oldBundle.getString("section");
        // GetExtras END

        // Assigning and Declaring Variables START
        tvStatement_1.setText("Tap the student ID at the back of the phone to start scanning");
        // Assigning and Declaring Variables END

        wsSetAttendance = new WSSetAttendance();
        wsSetAttendance.delegate = this;

        // NFC Assignments
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, this.getClass())
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        resolveIntent(intent);
    }

    private void init() {
        ivStudent.setVisibility(View.INVISIBLE);
    }

    @Override
    public void processFinish(Object output) {
        result = (String) output;
        if (result.isEmpty()) {
            // Dito sana iseset yung image ng student
            ivStudent.setVisibility(View.INVISIBLE);
            builder = new AlertDialog.Builder(AttendanceActivity.this, R.style.CustomAlertDialog);
            builder.setTitle("Error Occurred");
            builder.setMessage("Attendance Failed");
            builder.setCancelable(true);
            builder.show();
        } else {
            ivStudent.setVisibility(View.VISIBLE);
            toast = Toast.makeText(getApplicationContext(), "Attendance Successful", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }
    }

    // NFC Methods
    private void resolveIntent(Intent intent) {
        String action = intent.getAction();

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs;

            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];

                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }

            } else {
                byte[] empty = new byte[0];
                byte[] id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
                Tag tag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                byte[] payload = dumpTagData(tag).getBytes();
                NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, id, payload);
                NdefMessage msg = new NdefMessage(new NdefRecord[] {record});
                msgs = new NdefMessage[] {msg};
            }

            displayMsgs(msgs);
        }
    }

    private String dumpTagData(Tag tag) {
        StringBuilder sb = new StringBuilder();
        byte[] id = tag.getId();
        sb.append(toHex(id)).append('\n');
        return sb.toString();
    }

    private String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = bytes.length - 1; i >= 0; --i) {
            int b = bytes[i] & 0xff;
            if (b < 0x10)
                sb.append('0');
            sb.append(Integer.toHexString(b));
            if (i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private void displayMsgs(NdefMessage[] msgs) {
        if (msgs == null || msgs.length == 0)
            return;

        StringBuilder builder = new StringBuilder();
        List<ParsedNdefRecord> records = NdefMessageParser.parse(msgs[0]);
        final int size = records.size();

        for (int i = 0; i < size; i++) {
            ParsedNdefRecord record = records.get(i);
            String str = record.str();
            builder.append(str).append("\n");
        }

        wsSetAttendance.execute(builder.toString());
        //text.setText(builder.toString());
    }
}
