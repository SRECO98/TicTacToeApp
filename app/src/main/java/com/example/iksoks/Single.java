package com.example.iksoks;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Single extends AppCompatActivity implements View.OnClickListener {

    public TextView playerOneScore, playerTwoScore;
    public Button [] buttons = new Button[9];
    public Button resetGame;

    private CountDownTimer timer;
    private boolean isActiveGame = false;

    public int playerOneScoreCount, playerTwoScoreCount, rountCount;
    public boolean activePlayer;

    int  [] gameState = {2,2,2,2,2,2,2,2,2};
    boolean g;

    int [][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Beat the Computer");

        playerOneScore = (TextView) findViewById(R.id.textView13);
        playerTwoScore = (TextView) findViewById(R.id.textView14);
        resetGame = (Button) findViewById(R.id.button);

        for(int i = 0; i < buttons.length; i++){
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }

        rountCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayer = true;

        isActiveGame = true;
    }

    @Override
    public void onClick(View v) {
        Log.i("test", "buttons is clicked");
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId()); //btn_2 imagine we clicked this
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length() - 1, buttonID.length()));  // pretvorili smo u int jer buttonID vraca string

        if (isActiveGame) {

            if (activePlayer) {
                ((Button) v).setText("X"); // ako igra player1 stavljamo x
                ((Button) v).setTextSize(60);
                ((Button) v).setTextColor(Color.parseColor("#0751FA"));
                gameState[gameStatePointer] = 0;
            } else {
                g = true;
                while(g){
                     i = (int) (Math.random() * 10);
                     if(i <= 8){
                        if(gameState[i] == 2) {
                            buttons[i].setTextSize(60);
                            buttons[i].setTextColor(Color.parseColor("#F90404"));
                            buttons[i].setText("O");
                            g = false;
                            gameState[i] = 1;
                        }}
                }}
            rountCount++;
            if (checkWinner()) {
                isActiveGame = false;
                if (activePlayer) {
                    playerOneScoreCount++;
                    updatePlayerScore();
                    Toast.makeText(this, "Player One Won!", Toast.LENGTH_SHORT).show();

                } else {
                    playerTwoScoreCount++;
                    updatePlayerScore();
                    Toast.makeText(this, "Player Two Won!", Toast.LENGTH_SHORT).show();

                }

                restartGame();

            } else if (rountCount == 9) {
                playAgain();
                Toast.makeText(this, "DRAW!", Toast.LENGTH_SHORT).show();
            } else {
                activePlayer = !activePlayer;
            }

            resetGame.setOnClickListener(v1 -> {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                updatePlayerScore();
            });
        }
    }


    /**
     * Funkcija koja kreira tajmer od 3 sekunde
     * Nakon 3 sekunde igra se ponovo pokreće
     */
    private void restartGame() {

        timer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                playAgain();
            }
        }.start();

    }

    public boolean checkWinner(){
        boolean winnerResult = false;

        for(int [] winningPosition : winningPositions){
            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[2]] != 2) {
                buttons[winningPosition[0]].setBackgroundColor(getResources().getColor(R.color.light_blue));
                buttons[winningPosition[1]].setBackgroundColor(Color.rgb(3, 255, 239));
                buttons[winningPosition[2]].setBackgroundColor(Color.rgb(3, 255, 239));
                winnerResult = true;
            }
        }
        return winnerResult;
    }

    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void playAgain(){
        // Resetujemo boje button-a za početak nove partije
        for(int i = 0; i < 9;i++) {
            buttons[i].setBackgroundColor(
                    getResources().getColor(R.color.button_color_start)
            );
        }

        rountCount = 0;
        activePlayer = true;

        for(int i = 0; i < buttons.length; i++){
            gameState[i] = 2;
            buttons[i].setText("");
        }
        isActiveGame = true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Sprečavamo crash ako je tajmer pokrenut a korisnik izađe iz app
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}