package ru.alexkesh.binarybulbs;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Presenter {

    private ArrayList<Bulb> bulbs = new ArrayList<>(Arrays.asList(
            new Bulb(1),
            new Bulb(2),
            new Bulb(4),
            new Bulb(8),
            new Bulb(16),
            new Bulb(32),
            new Bulb(64),
            new Bulb(128)
    ));

    private MainActivity activity;

    private int result;

    private int maxNumber;
    private int currentNumber;

    private boolean gameIsStarted = false;

    private Random random;

    public Presenter(MainActivity activity) {
        this.activity = activity;
        this.result = 0;
        this.maxNumber = 32;
        this.currentNumber = 0;
    }

    public void onbBulbPressed(int id) {
        // identify the view element
        // get the DB element associated with this view element
        Bulb bulb = getClickedBulb(id);

        if (bulb.isChecked()) {
            bulb.setChecked(false); // change the DB element
            result -= bulb.getDecimalNumber();
            activity.setImageResource(id, R.drawable.round_button_not_checked); // order the view element to change
        } else {
            bulb.setChecked(true); // change the DB element
            result += bulb.getDecimalNumber();
            activity.setImageResource(id, R.drawable.round_button_checked); // order the view element to change
        }

        activity.setResult(String.valueOf(result)); // order the view element to change

        checkAnswer();
    }

    public void startGame() {
        gameIsStarted = true;
    }

    public void stopGame() {
        gameIsStarted = false;
    }

    public void checkAnswer() {
        if (result == currentNumber) {
            activity.setupForRightAnswer();

            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {
                result = 0;
                activity.setResult(String.valueOf(result));
                activity.setupForDefaultAnswer();

                turnOffAllBulbs();
                activity.resetBulbs();

                generateAndSetNumber();
            }, 1000);
        }
    }

    private void turnOffAllBulbs() {
        for (Bulb b : bulbs) {
            b.setChecked(false);
        }
    }

    public void generateAndSetNumber() {
        random = new Random();
        int randomInt;

        do {
            randomInt = random.nextInt(maxNumber) + 1;
        } while (currentNumber == randomInt);

        currentNumber = randomInt;

        activity.setTargetNumber(String.valueOf(currentNumber));
    }

    private Bulb getClickedBulb(int id) {
        Bulb bulb = bulbs.get(0);

        if (id == R.id.imageView2) {
            bulb = bulbs.get(1);
        } else if (id == R.id.imageView3) {
            bulb = bulbs.get(2);
        } else if (id == R.id.imageView4) {
            bulb = bulbs.get(3);
        } else if (id == R.id.imageView5) {
            bulb = bulbs.get(4);
        } else if (id == R.id.imageView6) {
            bulb = bulbs.get(5);
        } else if (id == R.id.imageView7) {
            bulb = bulbs.get(6);
        } else if (id == R.id.imageView8) {
            bulb = bulbs.get(7);
        }

        return bulb;
    }

    public void onDestroy() {
        activity = null;
    }
}
