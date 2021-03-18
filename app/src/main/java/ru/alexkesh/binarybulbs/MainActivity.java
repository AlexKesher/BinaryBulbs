package ru.alexkesh.binarybulbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8,
                        successImageView;

    private TextView resultTextView, targetNumberTextView;

    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter(this);

        resultTextView = findViewById(R.id.resultTextView);
        targetNumberTextView = findViewById(R.id.targetNumberTextView);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        successImageView = findViewById(R.id.successImageView);

        View.OnClickListener onClickListener = (View v) -> presenter.onbBulbPressed(v.getId());

        imageView1.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);
        imageView3.setOnClickListener(onClickListener);
        imageView4.setOnClickListener(onClickListener);
        imageView5.setOnClickListener(onClickListener);
        imageView6.setOnClickListener(onClickListener);
        imageView7.setOnClickListener(onClickListener);
        imageView8.setOnClickListener(onClickListener);

        presenter.generateAndSetNumber();
    }

    public void setResult(String result) {
        resultTextView.setText(result);
    }

    public void setTargetNumber(String number) {
        targetNumberTextView.setText(number);
    }

    public void setupForRightAnswer() {
        resultTextView.setTextColor(getResources().getColor(R.color.green_success, null));
        successImageView.setVisibility(View.VISIBLE);

    }

    public void setupForDefaultAnswer() {
        resultTextView.setTextColor(Color.BLACK);
        successImageView.setVisibility(View.INVISIBLE);
    }

    public void setImageResource(int viewId, int resId) {
        ImageView imageView = findViewById(viewId);
        imageView.setImageResource(resId);
    }

    public void resetBulbs() {
        imageView1.setImageResource(R.drawable.round_button_not_checked);
        imageView2.setImageResource(R.drawable.round_button_not_checked);
        imageView3.setImageResource(R.drawable.round_button_not_checked);
        imageView4.setImageResource(R.drawable.round_button_not_checked);
        imageView5.setImageResource(R.drawable.round_button_not_checked);
        imageView6.setImageResource(R.drawable.round_button_not_checked);
        imageView7.setImageResource(R.drawable.round_button_not_checked);
        imageView8.setImageResource(R.drawable.round_button_not_checked);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}