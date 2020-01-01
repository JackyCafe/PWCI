package tw.com.ian.pwci.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import androidx.annotation.Nullable;

import tw.com.ian.pwci.Util.Initializer;
import tw.com.ian.pwci.Listener.MaryListener;

public class OnBootService extends Service {
    private SpeechRecognizer recognizer;
    protected Intent mSpeechRecognizerIntent;
    public Initializer app = Initializer.app;
    private MaryListener listener;

    public OnBootService() {
        app.logv("OnBootService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app.logv("OnCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        listener = new MaryListener(this);
        recognizer = SpeechRecognizer.createSpeechRecognizer(this);
        recognizer.setRecognitionListener(listener);
        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "zh-TW");
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.getPackageName());
        recognizer.startListening(mSpeechRecognizerIntent);
        app.logv("onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        app.logv("onDestroy");
        if (recognizer != null){
            recognizer.destroy();
            recognizer=null;
        }
        super.onDestroy();

    }





}
