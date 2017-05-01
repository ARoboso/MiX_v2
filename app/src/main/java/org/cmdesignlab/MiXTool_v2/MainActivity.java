package org.cmdesignlab.MiXTool_v2;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

/**
 * To-Do function list
 */
public class MainActivity extends Activity {

    private NfcAdapter nfcAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if(nfcAdapter!=null && nfcAdapter.isEnabled()){

        }else{
            finish();
        }
    }

    public void onResume() {
        super.onResume();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        IntentFilter[] intentFilter = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
    }

    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        centerToast(getString(R.string.card_read));
        if(intent.hasExtra(NfcAdapter.EXTRA_TAG)){
            //Toast.makeText(this, "NFC Intent!", Toast.LENGTH_SHORT).show();
            Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if(parcelables!=null && parcelables.length>0){
                String message = readTextFromMessage((NdefMessage) parcelables[0]);
                transferTag(message);
            } else {
                Toast.makeText(this, "No NDEF messages found!", Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, "No NFC Intent!", Toast.LENGTH_SHORT).show();
        }
    }

    private void centerToast(String string){
        Toast readToast = Toast.makeText(this, string, Toast.LENGTH_SHORT);
        readToast.setGravity(Gravity.CENTER,0,0);
        readToast.show();
    }
    private void transferTag(String message){
        Intent passTagID = new Intent(this, CardVideo.class);
        passTagID.putExtra("tagID", message);
        startActivity(passTagID);
    }

    protected void onPause(){
        nfcAdapter.disableForegroundDispatch(this);
        super.onPause();
    }
    /*
    private void testActivity(View view){
        Intent intent = new Intent(this, TestVideo.class);
        startActivity(intent);
    }

    public void testVideoActivity(View view) {
        Intent passTagID = new Intent(this, CardVideo.class);
        passTagID.putExtra("tagID", message);
        startActivity(passTagID);
    }
*/
    private String readTextFromMessage(NdefMessage ndefMessage){
        NdefRecord[] ndefRecords = ndefMessage.getRecords();
        if(ndefRecords != null && ndefRecords.length>0){
            NdefRecord ndefRecord = ndefRecords[0];
            String tagContent = getTextFromNdefRecord(ndefRecord);
            return tagContent;
        } else{
            Toast.makeText(this, "No NDEF records found!", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private String getTextFromNdefRecord(NdefRecord ndefRecord){
        String tagContent = null;
        try{
            byte[] payload = ndefRecord.getPayload();
            String textEncoding = ((payload[0]&128)==0)?"UTF-8":"UTF-16";
            int languageSize = payload[0] & 0063;
            tagContent = new String(payload, languageSize+1, payload.length - languageSize - 1, textEncoding);
        } catch(UnsupportedEncodingException e){
            Log.e("getTextFromNdefRecord", e.getMessage(),e);
        }
        return tagContent;
    }

}