package tw.com.ian.pwci.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import tw.com.ian.pwci.DAO.GameDAO;
import tw.com.ian.pwci.DAO.GameLevelDAO;
import tw.com.ian.pwci.Object.GAME;
import tw.com.ian.pwci.Object.GameLevel;
import tw.com.ian.pwci.R;
import tw.com.ian.pwci.Receiver.GameReceiver;
import tw.com.ian.pwci.View.RecogintionView;

import android.speech.tts.TextToSpeech;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import tw.com.ian.pwci.Util.Initializer;

import static android.app.Activity.RESULT_OK;
import static java.lang.StrictMath.random;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment<ansLintener> extends Fragment {
    private static final int MY_DATA_CHECK_CODE = 988;
    private FrameLayout frameLayout;
    private TextToSpeech tts;
    private TextView tv;
    private String myQuestion;
    private Button btn;
    private ImageView mic;
    private static final int REQUEST_CODE = 006;
    private EditText inputText;
    Initializer app;
    private List<GAME> games;
    private GameDAO gameDAO = null;
    private GameLevelDAO gameLevelDAO = null;
    private List<GameLevel> gameLevels;
    private GameLevel gameLevel;
    private RecogintionView rv;
    private GAME game;
    public final static String ACTION = "com.ian.gamereceiver";
    public GameReceiver receiver;
    List<GameLevel> gameLevelSatges;

    public GameFragment() {

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        app = (Initializer) getActivity().getApplication();
        tts = app.tts;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);
        //註冊廣播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        receiver = new GameReceiver(tts);
        app.registerReceiver(receiver, intentFilter);

        frameLayout = v.findViewById(R.id.viewport);
        inputText = v.findViewById(R.id.inputText);
        mic = v.findViewById(R.id.mic);
        mic.setOnClickListener(micOnClick);
        btn = v.findViewById(R.id.send);
        gameDAO = dataInit(v.getContext());
        games = gameDAO.getAll();
        gameLevelDAO = gameLevelInit(v.getContext());
        gameLevels = gameLevelDAO.getAll();
        if (gameDAO.getFirstGame().getProcess() == 1) {
            //1. 隨機抓取題庫
            gameLevelSatges = gameLevelDAO.getStageLevel(1);
            int size = gameLevelSatges.size();
            int rnd = (int) (random() * size);
            game = gameDAO.getFirstGame();
            myQuestion = gameLevelSatges.get(rnd).getTopic();

            Intent intent = new Intent(ACTION);
            Bundle bundle = new Bundle();
            bundle.putInt("stage", 1);
            bundle.putSerializable("game", game);
            bundle.putSerializable("gameLevel", gameLevelSatges.get(rnd));
            intent.putExtras(bundle);
            app.sendBroadcast(intent);
        }
        btn.setOnClickListener(new AnsOnClick(myQuestion));
        rv = new RecogintionView(container.getContext(), null);
        frameLayout.addView(rv);
        return v;
    }


    //三個字題庫
    private GameLevelDAO gameLevelInit(Context context) {
        GameLevelDAO dao = new GameLevelDAO(context);
        if (dao.getAll().size() <= 0) {
            GameLevel gameLevel = null;
            gameLevel = new GameLevel(0, 1, "電腦蘋果朋友", 0);
            dao.insert(gameLevel);
            gameLevel = new GameLevel(0, 1, "課本蛋糕老師", 0);
            dao.insert(gameLevel);
            gameLevel = new GameLevel(0, 1, "手機冰淇淋小叮噹", 0);
            dao.insert(gameLevel);
            gameLevel = new GameLevel(0, 1, "象棋披薩小妹", 0);
            dao.insert(gameLevel);
            gameLevel = new GameLevel(0, 1, "汽車熱狗奶奶", 0);
            dao.insert(gameLevel);
            gameLevel = new GameLevel(0, 2, "綠", R.color.colorGreen);
            dao.insert(gameLevel);
            gameLevel = new GameLevel(0, 2, "紅", R.color.colorRed);
            dao.insert(gameLevel);
            gameLevel = new GameLevel(0, 2, "黃", R.color.colorYellow);
            dao.insert(gameLevel);
            gameLevel = new GameLevel(0, 2, "藍", R.color.colorBlue);
            dao.insert(gameLevel);
            gameLevel = new GameLevel(0, 2, "灰", R.color.colorGray);
            dao.insert(gameLevel);
            gameLevel = new GameLevel(0, 2, "黑", R.color.colorBlack);
            dao.insert(gameLevel);
        }
        return dao;

    }

    //三道題初始
    private GameDAO dataInit(Context context) {
        GameDAO gameDAO = new GameDAO(context);
        GAME game;
        if (gameDAO.getAll().size() <= 0) {
            game = new GAME((long) 0, "我現在唸三個字，跟我念一次", 0, "很好，幫我記得這三樣喔", 1);
            gameDAO.insert(game);
            game = new GAME((long) 1, "幫我看一下螢幕上圖是什麼顏色", 0, "", 0);
            gameDAO.insert(game);
            game = new GAME((long) 2, "如果你現在有100元，，花掉7塊錢，還剩多少錢", 0, "又花掉7塊錢，還剩多少錢", 0);
            gameDAO.insert(game);
        } else {
            //reset status
            for (int i = 0; i < gameDAO.getSize(); i++) {
                // 重設第一題進度
                game = gameDAO.get(i);
                if (i == 0) {
                    game.setProcess(1);
                } else {
                    game.setProcess(0);
                }
                game.setStatus(0);
                gameDAO.update(game);
            }

        }
        return gameDAO;
    }

    /*init tts resource*/
    @Override
    public void onStart() {
        super.onStart();
        app = (Initializer) getActivity().getApplication();
        app.initTTS();

    }

    @Override
    public void onResume() {
        super.onResume();
        app = (Initializer) getActivity().getApplication();
        app.initTTS();
    }

    /*按下麥克風按鈕，產生的圖案*/
    View.OnClickListener micOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String DIALOG_TEXT = "你說吧!!我在聽";
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, DIALOG_TEXT);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "zh-TW");
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            startActivityForResult(intent, REQUEST_CODE);
        }
    };

    /**
     * 麥克風傳回字串值
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        ArrayList<String> speech = new ArrayList<>();
        if (resultCode == RESULT_OK) {

            if (requestCode == REQUEST_CODE) {
                speech = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                tts.speak(speech.get(0), TextToSpeech.QUEUE_FLUSH, null, null);
                inputText.setText(speech.get(0));
            }
        }
    }

    /*release tts resource*/
    @Override
    public void onDetach() {
        super.onDetach();
        //取消廣播
        app.unregisterReceiver(receiver);
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

    class AnsOnClick implements View.OnClickListener {
        public String question;
        public String myAns;

        public AnsOnClick(String question) {
            this.question = question;

        }

        @Override
        public void onClick(View v) {
            myAns = inputText.getText().toString();
            game = gameDAO.getFirstGame();
            if (game.getProcess() == 1)
                checkGame1(game, question);
            game = gameDAO.get(1);
            if (game.getProcess() == 1)
                checkGame2(game);
            game = gameDAO.get(2);
            if (game.getProcess() == 1)
                checkGame3(game);
        }

        private void checkGame3(GAME game) {
            String myAns = inputText.getText().toString();
            int ans = 0;
            try {
                if (myAns.length() > 0)
                    ans = Integer.valueOf(myAns);
                if (ans == 93) {
                    tts.speak("剛剛請你記住的三樣東西是什麼", TextToSpeech.QUEUE_ADD, null, null);


                } else if (myAns.length() > 0) {
                    tts.speak(game.getTopic(), TextToSpeech.QUEUE_ADD, null, null);
                }
            } catch (Exception e) {

            }

        }

        private void checkGame2(GAME game) {
            String myAns = inputText.getText().toString();

            if (myAns.contains(gameLevel.getTopic())) {
                tts.speak("很好", TextToSpeech.QUEUE_ADD, null, null);
                int id = Math.toIntExact(game.getId());
                game.setProcess(0);
                gameDAO.update(game);
                GAME next_game = gameDAO.get(id);
                next_game.setProcess(1);
                gameDAO.update(next_game);
                inputText.setText(null);
                Intent intent = new Intent(ACTION);
                Bundle bundle = new Bundle();
                bundle.putInt("stage", 3);
                bundle.putSerializable("game", next_game);
                intent.putExtras(bundle);
                app.sendBroadcast(intent);

            } else if (myAns.length() > 0) {
                tts.speak("再想想", TextToSpeech.QUEUE_ADD, null, null);

            }


        }

        //第一題比對答案
        private void checkGame1(GAME game, String question) {
            for (int i = 0; i < 3; i++) {
                myAns = inputText.getText().toString();

                if (question.equals(myAns)) {
                    //比對成功
                    tts.speak(game.getPostword(), TextToSpeech.QUEUE_ADD, null, null);
                    //下一題
                    //原題目的process 設為0
                    int id = Math.toIntExact(game.getId());
                    game.setProcess(0);
                    gameDAO.update(game);
                    GAME next_game = gameDAO.get(id);
                    next_game.setProcess(1);
                    gameDAO.update(next_game);
                    inputText.setText(null);
                    //發送廣播

                    GameLevelDAO dao = new GameLevelDAO(getContext());
                    gameLevels = dao.getStageLevel(2);
                    int size = gameLevels.size();
                    int rnd = (int) (random() * size);
                    int color = gameLevels.get(rnd).getColor();
                    gameLevel = gameLevels.get(rnd);
                    Intent intent = new Intent(ACTION);
                    Bundle bundle = new Bundle();
                    bundle.putInt("stage", 2);
                    bundle.putSerializable("game", next_game);
                    bundle.putSerializable("gameLevel", gameLevels.get(rnd));
                    intent.putExtras(bundle);
                    app.sendBroadcast(intent);
                    rv = new RecogintionView(getContext(), null, color);
                    frameLayout.addView(rv);
                    inputText.setText(null);
                    break;
                } else if (myAns.length() > 0) {
                    tts.speak("再想一下，第一項是個東西，第二個是吃的，第三項是個人", TextToSpeech.QUEUE_ADD, null, null);
                    inputText.setText(null);
                    app.logv("再想一下，第一項是個東西，第二個是吃的，第三項是個人 " + question);

                }
            }
        }

    }

    ;
}
