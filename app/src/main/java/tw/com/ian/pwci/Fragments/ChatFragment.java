package tw.com.ian.pwci.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tw.com.ian.pwci.Adapter.MsgAdapter;
import tw.com.ian.pwci.DAO.QuizDAO;
import tw.com.ian.pwci.Initializer;
import tw.com.ian.pwci.Interface.OnRecyclerViewClickListener;
import tw.com.ian.pwci.Object.Msg;
import tw.com.ian.pwci.Object.Quiz;
import tw.com.ian.pwci.R;

import static android.app.Activity.RESULT_OK;


public class ChatFragment extends Fragment implements TextToSpeech.OnInitListener {
    private static final int REQUEST_CODE = 007;
    private ImageView mic;
    private EditText inputText;
    private RecyclerView msgView;
    private List<Msg> msgList = new ArrayList<>();
    private MsgAdapter adapter ;
    private  QuizDAO quizDAO;
    private int [] quiz_array;
    private int select = 2;
    private Msg msg;
    private Button send;
    private TextToSpeech tts;

    public ChatFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        quizDAO = new QuizDAO(v.getContext());
        if (quizDAO.getAll().size()<=0){ generationQuiz();  }
        quiz_array = new int[quizDAO.getAll().size()-1];
        for (int i=0;i<quiz_array.length;i++)
            quiz_array[i] =i+1;

        for (int j = quiz_array.length-1; j>1;j--) {
            int rnd = (int)(Math.random()*j) +1;
            int tmp = quiz_array[rnd];
            quiz_array[rnd] = quiz_array[j];
            quiz_array[j] = tmp;
        }

        tts = new Initializer().tts;
        tts = new TextToSpeech(getContext(), this);

        mic = v.findViewById(R.id.mic);
        inputText = v.findViewById(R.id.inputText);
        send = v.findViewById(R.id.send);
        putMsg(1);
        adapter = new MsgAdapter(msgList);
        adapter.notifyDataSetChanged();
        msgView = (RecyclerView) v.findViewById(R.id.msgView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        msgView.setLayoutManager(layoutManager);
        msgView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void onItemClickListener(View view) {
                String text = ((TextView)view).getText().toString();
                tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
             }

            @Override
            public void onItemLongClickListener(View view) {

            }
        });
        mic.setOnClickListener(micOnClick);
        send.setOnClickListener(sendOnClick);
        return v;
    }


    /**
     *  麥克風傳回字串值
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
                tts.speak(speech.get(0),TextToSpeech.QUEUE_FLUSH,null,null);
                inputText.setText(speech.get(0));
            }
        }
    }

    /***
     * 按下麥克風
     */
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


    /***
     *  按下Send 時
     */

    View.OnClickListener sendOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String content=inputText.getText().toString();
            if("".equals(content))
                return;
            msg = new Msg(content,Msg.TYPE.SENT);
            msgList.add(msg);
            int newSize = msgList.size() - 1;

            adapter.notifyItemInserted(newSize);
            msgView.scrollToPosition(newSize);
            inputText.setText("");

            if(select<quizDAO.getAll().size()) {
                putMsg(quiz_array[select]);
                select++;
                newSize = msgList.size() - 1;
                adapter.notifyItemInserted(newSize);
                tts.speak(msgList.get(newSize).getMsg(),TextToSpeech.QUEUE_FLUSH,null,null);
                msgView.scrollToPosition(newSize);
            }else{
                msg = new Msg("謝謝! 掰掰",Msg.TYPE.RECEIVED);
                putMsg(quiz_array.length);
                newSize = msgList.size() - 1;
                adapter.notifyItemInserted(newSize);
                msgView.scrollToPosition(newSize);
            }
        }
    };

    /**
     * 產生題庫
     */
    public void generationQuiz()
    {

        String [] questions = {"阿伯你叫什麼名字","你從那裏來?","現在是民國幾年?",
                "現在是幾月?" ,
                "今天星期幾",
                "你今年幾歲?",
                "這裡是哪裡?" ,
                "你退休了沒?" ,
                "之前做什麼工作",
                "職務是什麼?" ,
                "你有幾個兒子",
                "你太太跟誰住",
                "你有吃早餐嗎?",
                "你現在有100元，花掉7塊錢還剩幾塊?"
        };

        for (String s: questions)
        {
            Quiz quiz = new Quiz((long)0,s);
            quiz = quizDAO.insert(quiz);
        }


    }

    public void putMsg(int i)
    {
        Quiz q = quizDAO.get(i);
        {
            msg = new Msg(q.getQuestion(),Msg.TYPE.RECEIVED);
            msgList.add(msg);
        }
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
}
