package tw.com.ian.pwci.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

import tw.com.ian.pwci.Object.GAME;
import tw.com.ian.pwci.Object.GameLevel;
import tw.com.ian.pwci.Util.Initializer;

public class GameReceiver extends BroadcastReceiver  {
    Initializer app ;
    TextToSpeech tts;
   public GameReceiver(TextToSpeech tts){
       this.tts = tts;
   }
    @Override
    public void onReceive(Context context, Intent intent) {
       app = (Initializer) context.getApplicationContext();
        Bundle bundle = intent.getExtras();
        int stage = bundle.getInt("stage",0);

        switch (stage){
            case 1:  sayGame1(bundle); break;
            case 2:  sayGame(bundle); break;
            case 3:  sayGame(bundle); break;
        }



    }

    public void sayGame1(Bundle bundle) {
        GAME game = (GAME) bundle.getSerializable("game");
        GameLevel gameLevel = (GameLevel) bundle.getSerializable("gameLevel");
        String myQuestion = gameLevel.getTopic();
        tts.speak(game.getTopic(), TextToSpeech.QUEUE_ADD,null,null);
        tts.speak(myQuestion,TextToSpeech.QUEUE_ADD,null,null);
    }

    public void sayGame(Bundle bundle) {
        GAME game = (GAME) bundle.getSerializable("game");
        tts.speak(game.getTopic(), TextToSpeech.QUEUE_ADD,null,null);

    }
}
