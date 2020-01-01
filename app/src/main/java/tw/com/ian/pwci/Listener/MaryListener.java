package tw.com.ian.pwci.Listener;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;


import java.util.List;

import tw.com.ian.pwci.Util.Initializer;
import tw.com.ian.pwci.Service.OnBootService;

public class MaryListener implements RecognitionListener {
    public Initializer app;
    public Context context;
    private TextToSpeech tts;


    public MaryListener(Context context) {
        this.context = context;
        app = (Initializer) ((Service) context).getApplication();
        tts = app.tts;

    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        app.logv("onReadyForSpeech ");

    }

    @Override
    public void onBeginningOfSpeech() {
        app.logv("onBeginningOfSpeech");


    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
        app.logv("onEndOfSpeech");
    }

    @Override
    public void onError(int error) {

    }

    @Override
    public void onResults(Bundle results) {
        List<String> resList = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        StringBuffer sb = new StringBuffer();
        for (String res : resList) {
            sb.append(res + "\n");
            app.logv(res);
            if (tts == null) {
                tts = app.tts;
            } else {
                switch (res) {
                    case "哈囉":
                        saySomething("你好彥亨");
                        break;
                    case "拿藥":
                        saySomething("下星期一回診");
                        break;
                }
            }


        }

    }

    private void saySomething(String msg) {

        tts.speak(msg, TextToSpeech.QUEUE_FLUSH, null, null);
        Intent it = new Intent();
        it.setClass(context, OnBootService.class);
        it.putExtra("Mary", "Hello Mary");
        context.startService(it);

    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        Log.v("Jacky", "onPartialResults ");

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }


}
