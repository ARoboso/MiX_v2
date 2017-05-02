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
                player.loadVideo("-OR0CW8joNw");
            } else if (message.equals("p.vanish")) {
                player.loadVideo("mtjgyDXXrqw");
            } else if(message.equals("p.transposition")) {
                player.loadVideo("BhGqu_nZfuU");
            }else if(message.equals("p.transformation")) {
                player.loadVideo("DnvCAtnqh1o");
            }else if(message.equals("p.penetration")) {
                player.loadVideo("2kZl5aLGovU");
            }else if(message.equals("p.restoration")) {
                player.loadVideo("KpWyIHfdmyg");
            }else if(message.equals("p.animation")) {
                player.loadVideo("TnNWHqyVKS0");
            }else if(message.equals("p.anti")){
                player.loadVideo("26LwAj4KK4M");
            }else if(message.equals("p.invulnerability")) {
                player.loadVideo("xMCgzVqaVdc");
            }else if(message.equals("p.physical")) {
                player.loadVideo("L4BvD4ZJIxc");
            }else if(message.equals("p.sympathetic")) {
                player.loadVideo("JAsRBsqXTUo");
            }else if(message.equals("p.identification")) {
                player.loadVideo("zhaggslczwc");
            }else if(message.equals("p.telepathy")) {
                player.loadVideo("BYUfzI3wzJo");
            }else if(message.equals("p.esp")) {
                player.loadVideo("B6WExy0Mkw4");
            }else if(message.equals("p.telekinesis")) {
                player.loadVideo("A8HYhIWxViY");
            }else if (message.equals("m.production")) {
                player.loadVideo("ZR2UbUNmgxs");
            } else if (message.equals("m.vanish")) {
                player.loadVideo("RWbAR1AfBtU");
            } else if(message.equals("m.transposition")) {
                player.loadVideo("2d5AUwHkDYg");
            }else if(message.equals("m.transformation")) {
                player.loadVideo("WyFFSLPcMVY");
            }else if(message.equals("m.penetration")) {
                player.loadVideo("-7WXIn0OPdw");
            }else if(message.equals("m.restoration")) {
                player.loadVideo("LX-vkzurlEY");
            }else if(message.equals("m.animation")) {
                player.loadVideo("6DcxHfMAr4M");
            }else if(message.equals("m.anti")) {
                player.loadVideo("1mesqvGTL9g");
            }else if(message.equals("m.invulnerability")) {
                player.loadVideo("UY74olSHapw");
            }else if(message.equals("m.physical")) {
                player.loadVideo("t4F43oJLosM");
            }else if(message.equals("m.sympathetic")) {
                player.loadVideo("UId1Ee9e0KY");
            }else if(message.equals("m.identification")) {
                player.loadVideo("2aI0gi6wsjY");
            }else if(message.equals("m.telepathy")) {
                player.loadVideo("fKmAyqXLwS0");
            }else if(message.equals("m.esp")) {
                player.loadVideo("cA3ceIRAPxk");
            }else if(message.equals("m.telekinesis")) {
                player.loadVideo("95BEWa3lTiM");
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