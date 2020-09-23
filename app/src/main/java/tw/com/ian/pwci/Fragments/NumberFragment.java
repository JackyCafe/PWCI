package tw.com.ian.pwci.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import tw.com.ian.mnist.DigitsDetector;
import tw.com.ian.mnist.PaintView;
import tw.com.ian.pwci.DAO.GameDAO;
import tw.com.ian.pwci.DAO.GameLevelDAO;
import tw.com.ian.pwci.Object.GAME;
import tw.com.ian.pwci.Object.GameLevel;
import tw.com.ian.pwci.R;
import tw.com.ian.pwci.Receiver.GameReceiver;
import tw.com.ian.pwci.Util.Initializer;
import tw.com.ian.pwci.View.RecogintionView;

import static android.app.Activity.RESULT_OK;
import static java.lang.StrictMath.random;


/**
 * A simple {@link Fragment} subclass.
 */
public class NumberFragment<ansLintener> extends Fragment {
    private PaintView paintView;
    private View detectButton,clearButton;
    LinearLayout inferencePreview;
    ImageView previewImage;
    TextView mResultText;
    private DigitsDetector mnistClassifier;
    private static final int PIXEL_WIDTH = 28;

    public NumberFragment() {

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_number, container, false);
        paintView = v.findViewById(R.id.paintView);
        detectButton = v.findViewById(R.id.button_detect);
        inferencePreview = v.findViewById(R.id.inference_preview);
        mnistClassifier = new DigitsDetector(getActivity());
        previewImage = v.findViewById(R.id.preview_image);
        mResultText = v.findViewById(R.id.text_result);
        clearButton = v.findViewById(R.id.button_clear);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        paintView.init(metrics);

        detectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDetectClicked();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearClicked();
            }
        });
        return v;
    }

    private void onDetectClicked() {
        inferencePreview.setVisibility(View.VISIBLE);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(paintView.getBitmap(), PIXEL_WIDTH, PIXEL_WIDTH, false);
        int digit = mnistClassifier.classify(scaledBitmap);
        previewImage.setImageBitmap(scaledBitmap);
        if (digit >= 0) {
            Log.d("Jacky", "Found Digit = " + digit);
            mResultText.setText(String.valueOf(digit));
        } else {
            mResultText.setText(getString(R.string.not_detected));
        }
    }

    private void onClearClicked() {
        mResultText.setText("");
        paintView.clear();
    }

}
