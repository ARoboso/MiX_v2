package org.cmdesignlab.MiXTool_v2;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class CardVideo extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(org.cmdesignlab.MiXTool_v2.R.layout.activity_card_video);

        youTubeView = (YouTubePlayerView) findViewById(org.cmdesignlab.MiXTool_v2.R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        IntentFilter[] intentFilter = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //centerToast("新規読み取りのためにメイン画面に戻って下さい\n端末の戻るボタンを押して下さい");
        /*LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.notice,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }

    private void centerToast(String string){
        Toast readToast = Toast.makeText(this, string, Toast.LENGTH_LONG);
        readToast.setGravity(Gravity.CENTER,0,0);
        readToast.show();
    }
    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            Intent intent = getIntent();
            //String message = "p.telekinesis";
            String message = intent.getStringExtra("tagID");
            //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            if (message.equals("p.production")) {
                player.loadVideo("-a8l65tuQ0Q");
            } else if (message.equals("p.vanish")) {
                player.loadVideo("jAXQdZVXK3c");
            } else if(message.equals("p.transposition")) {
                player.loadVideo("qqDollvvz-Q");
            }else if(message.equals("p.transformation")) {
                player.loadVideo("deIhIqmYKrE");
            }else if(message.equals("p.penetration")) {
                player.loadVideo("zHz-B8-FA4E");
            }else if(message.equals("p.restoration")) {
                player.loadVideo("DjDAxa2GPsw");
            }else if(message.equals("p.animation")) {
                player.loadVideo("N-QiPHdBNKc");
            }else if(message.equals("p.anti")){
                player.loadVideo("JpnFrXrtKaE");
            }else if(message.equals("p.invulnerability")) {
                player.loadVideo("aa-8CXLSiGI");
            }else if(message.equals("p.physical")) {
                player.loadVideo("WyhFtl8stX4");
            }else if(message.equals("p.sympathetic")) {
                player.loadVideo("k05G35bYioA");
            }else if(message.equals("p.identification")) {
                player.loadVideo("hoALW7DtU90");
            }else if(message.equals("p.telepathy")) {
                player.loadVideo("ocVyO1OgJGQ");
            }else if(message.equals("p.esp")) {
                player.loadVideo("NVnf9VvgGWk");
            }else if(message.equals("p.telekinesis")) {
                player.loadVideo("VH06yuC8r-Q");
            }else if (message.equals("m.production")) {
                player.loadVideo("akCv6Sr6bjU");
            } else if (message.equals("m.vanish")) {
                player.loadVideo("ZJ0IZMCZ4Rg");
            } else if(message.equals("m.transposition")) {
                player.loadVideo("sc--7mBe4cQ");
            }else if(message.equals("m.transformation")) {
                player.loadVideo("Lhu3OFZlcMA");
            }else if(message.equals("m.penetration")) {
                player.loadVideo("Eigg4KrjOYo");
            }else if(message.equals("m.restoration")) {
                player.loadVideo("V_kP7aWS7p8");
            }else if(message.equals("m.animation")) {
                player.loadVideo("jAw32iK3leU");
            }else if(message.equals("m.anti")) {
                player.loadVideo("PFVo-cG9kT8");
            }else if(message.equals("m.invulnerability")) {
                player.loadVideo("EVmm4qhPzWo");
            }else if(message.equals("m.physical")) {
                player.loadVideo("W3wvJHbEws8");
            }else if(message.equals("m.sympathetic")) {
                player.loadVideo("uDuPJknn33Y");
            }else if(message.equals("m.identification")) {
                player.loadVideo("LVghre_4_cE");
            }else if(message.equals("m.telepathy")) {
                player.loadVideo("tmjU4zaHTHg");
            }else if(message.equals("m.esp")) {
                player.loadVideo("mP-kB_5aIqU");
            }else if(message.equals("m.telekinesis")) {
                player.loadVideo("K-9vQ_ulNNk");
            }else{
                Toast.makeText(this, "Sample Video", Toast.LENGTH_LONG).show();
                player.loadVideo("a3ICNMQW7Ok");
            }
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(org.cmdesignlab.MiXTool_v2.R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }
}