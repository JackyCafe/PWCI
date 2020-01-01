package tw.com.ian.pwci.Util;

import android.app.Application;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class Initializer extends Application implements TextToSpeech.OnInitListener {
    public TextToSpeech tts;
    public static Initializer app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = new Initializer();
        tts = new TextToSpeech(getApplicationContext(),this);

    }

    public void initTTS()
    {
        tts = new TextToSpeech(getApplicationContext(),this);

    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.TRADITIONAL_CHINESE);    //設定語言為中文
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("Jacky", "This Language is not supported");

            } else {
                tts.setPitch(1);    //語調(1為正常語調；0.5比正常語調低一倍；2比正常語調高一倍)
                tts.setSpeechRate(1);    //速度(1為正常速度；0.5比正常速度慢一倍；2比正常速度快一倍)
            }
        } else {
            Log.e("Jacky", "Initilization Failed!");
        }
    }

    public void logv(String msg)
    {
        Log.v("Jacky", msg);

    }
}
