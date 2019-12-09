package tw.com.ian.pwci.Fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import tw.com.ian.pwci.R;
import tw.com.ian.pwci.RecogintionView;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;

import java.util.Locale;

import tw.com.ian.pwci.Initializer;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment implements TextToSpeech.OnInitListener {
    private FrameLayout frameLayout;
    private TextToSpeech tts;
    private TextView tv;
    private String text;
    private Button btn;
    public GameFragment() {
     }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);
        tv = v.findViewById(R.id.tv);
        frameLayout = v.findViewById(R.id.viewport);
        text = tv.getText().toString();
        btn = v.findViewById(R.id.mread);
        tts = new Initializer().tts;
        tts = new TextToSpeech(getContext(), this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tts !=null )
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);     //發音
            }
        });
        RecogintionView rv = new RecogintionView(container.getContext(),null);
        frameLayout.addView(rv);
        return v;
    }


    @Override
    public void onStart() {
        super.onStart();



    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.TRADITIONAL_CHINESE);    //設定語言為中文
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("Jacky", "This Language is not supported");

            } else {
                tts.setPitch(0.7f);    //語調(1為正常語調；0.5比正常語調低一倍；2比正常語調高一倍)
                tts.setSpeechRate(0.7f);    //速度(1為正常速度；0.5比正常速度慢一倍；2比正常速度快一倍)
             }} else {
            Log.e("Jacky", "Initilization Failed!");
        }

    }

    @Override
    public void onDestroy() {
        // shutdown tts
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}
