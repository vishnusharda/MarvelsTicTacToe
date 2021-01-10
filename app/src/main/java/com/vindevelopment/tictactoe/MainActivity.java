 package com.vindevelopment.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9;
    TextView winner_tv;
    Button play_again;
    int active;
    int[] position = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    Boolean activeGame = true;
    String winner = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        winner_tv = findViewById(R.id.winner_tv);
        play_again = findViewById(R.id.play_again);
        winner_tv.setVisibility(View.VISIBLE);
        winner_tv.setText("Captain America's turn");
    }

    public void click(View view) {
        ImageView iv = (ImageView) view;
        int tagPosition = Integer.parseInt(iv.getTag().toString());

        if (position[tagPosition] == 2 && activeGame) {
            iv.animate().alpha(1).setDuration(500);
            position[tagPosition] = active;

            // 0:Captain America, 1:Iron Man, 2:Empty
            if (active == 0) {
                winner_tv.setVisibility(View.VISIBLE);
                winner_tv.setText("Iron Man's Turn");
                iv.setImageResource(R.drawable.captainamerica);
                active = 1;
            } else {
                iv.setImageResource(R.drawable.ironman);
                winner_tv.setVisibility(View.VISIBLE);
                winner_tv.setText("Captain America's Turn");
                active = 0;
            }

            for (int[] winningPosition : winningPositions) {
                if (position[winningPosition[0]] == position[winningPosition[1]]
                        && position[winningPosition[1]] == position[winningPosition[2]]
                        && position[winningPosition[0]] != 2) {

                    activeGame = false;

                    if (active == 1) {
                        winner = "Winner is Captain America";
                        gameFinish(winner);
                    } else if (active == 0) {
                        winner = "Winner is Iron Man";
                        gameFinish(winner);
                    }
                } else if (isTied()
                        && position[winningPosition[0]] != position[winningPosition[1]]
                        && position[winningPosition[1]] != position[winningPosition[2]]
                        ) {
                    winner = "Draw Match";
                    gameFinish(winner);
                }
            }
        }
    }

    public void PlayAgain(View view) {
        TextView winner_tv = (TextView) findViewById(R.id.winner_tv);
        Button play_again = (Button) findViewById(R.id.play_again);

        play_again.setVisibility(View.INVISIBLE);
        winner_tv.setVisibility(View.INVISIBLE);

        winner_tv.setVisibility(View.VISIBLE);
        winner_tv.setText("Captain America's turn");

        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
            imageView.animate().alpha(0).setDuration(500);
        }

        for (int i = 0; i < position.length; i++) {
            position[i] = 2;
        }
        active = 0;
        activeGame = true;

    }

    public void gameFinish(String winner) {

        play_again.setVisibility(View.VISIBLE);
        winner_tv.setVisibility(View.VISIBLE);
        winner_tv.setText(winner);
    }

    public boolean isTied() {

        for (int i = 0; i < position.length; i++) {
            if (position[i] == 2) {
                return false;
            }
        }
        return true;
    }

}