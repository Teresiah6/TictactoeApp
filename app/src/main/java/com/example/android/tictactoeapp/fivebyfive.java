package com.example.android.tictactoeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class fivebyfive extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[5][5];

    private int player1points;
    private int player2points;

    private boolean player1Turn = true;

    private int roundCount;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fivebyfive);

        textViewPlayer1 = findViewById(R.id.text_view_player1);
        textViewPlayer2 = findViewById(R.id.text_view_player2);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);


            }
        }
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 25) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }


    private boolean checkForWin() {
        String[][] field = new String[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 5; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2]) && field[i][0].equals(field[i][3])
                    && field[i][0].equals(field[i][4]) &&
                    !field[i][0].equals("")) {
                return true;
                //code for win on horizontal wins for player 1 and 2
            }
        }
        for (int i = 0; i < 5; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i]) && field[0][i].equals(field[3][i])
                    && field[0][i].equals(field[4][i]) &&
                    !field[0][i].equals("")) {
                return true;
                //code to check for win on vertical for player 1 and 2
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2]) && field[0][0].equals(field[3][3])
                && field[0][0].equals(field[4][4]) &&
                !field[0][0].equals("")) {
            return true;
            //code to check for diagonal win
        }
        if (field[0][4].equals(field[1][3])
                && field[0][4].equals(field[2][2]) && field[0][4].equals(field[3][1])
                && field[0][4].equals(field[4][0]) &&
                !field[0][4].equals("")) {
            return true;
        }
        //check for diagonal win
        return false;
    }

    private void player1Wins() {
        player1points++;
         Toast.makeText(this, "Player 1 wins", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2points++;
        Toast.makeText(this, "Player 2 wins", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + player1points);
        textViewPlayer2.setText("Player 2: " + player2points);
    }

    private void resetBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame() {
        player1points = 0;
        player2points = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1points);
        outState.putInt("player2Points", player2points);
        outState.putBoolean("player1turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount = savedInstanceState.getInt("roundCount");
        player1points = savedInstanceState.getInt("player1points");
        player2points = savedInstanceState.getInt("player2points");
        player1Turn = savedInstanceState.getBoolean("player1turn");
    }
}


