package tw.com.ian.pwci;

import android.app.Application;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class Initializer extends Application{
    public TextToSpeech tts;

    @Override
    public void onCreate() {
        super.onCreate();

    }


}
