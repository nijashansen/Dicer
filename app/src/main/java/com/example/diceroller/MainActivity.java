package com.example.diceroller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Random random = new Random();
    int turn = 0;
    public ArrayList<Integer> listGroup = new ArrayList<>();
    HashMap<String, ArrayList<Integer>> listItem = new HashMap<>();
    TextView closeToTop;
    LinearLayout logHandler;
    EditText numDice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonRoll = findViewById(R.id.btRoll);
        Button buttonAdd = findViewById(R.id.btAddDice);
        Button buttonHis = findViewById(R.id.btHistory);
        buttonAdd.setOnClickListener(this);
        buttonRoll.setOnClickListener(this);
        buttonHis.setOnClickListener(this);
        closeToTop = findViewById(R.id.closeToTp);
        logHandler = findViewById(R.id.logHandler);
        numDice = findViewById(R.id.etNumOfDice);
    }

    public void addDice() {
        try {
            FlexboxLayout diceLayout = findViewById(R.id.llDiceImgContainer);
            diceLayout.removeAllViews();

            int dices = 0;
            String numOfDice = numDice.getText().toString();
            if (!"".equals(numDice)) {
                dices = Integer.parseInt(numOfDice);
            }

            if (dices > 6) {
                Toast.makeText(this, "Dices cant be more than 6", Toast.LENGTH_SHORT).show();
            } else {
                for (int i = 0; i < dices; i++) {
                    ImageView img = new ImageView(this);
                    img.setImageResource(R.drawable.dice1);
                    img.setLayoutParams(new android.view.ViewGroup.LayoutParams(100, 100));

                    diceLayout.addView(img);
                    closeToTop.setText("pick the number og dice you want");
                }
            }
        } catch (NumberFormatException e) {
            closeToTop.setTextColor(Color.RED);
            closeToTop.setText(" It must be a number between 1 and 6 ");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btAddDice:
                addDice();
                break;
            case R.id.btRoll:
                roll();
                break;
            case R.id.btHistory:
                startNextActivity();
        }
    }

    private void startNextActivity() {
        Intent i = new Intent(this, ListView.class);
        startActivity(i);
    }


    private void roll(){
        turn++;
        String round = "Turn " + turn ;

        ViewGroup diceLayout = findViewById(R.id.llDiceImgContainer);
        for(int i = 0; i<diceLayout.getChildCount(); i++){

            View nexChild = diceLayout.getChildAt(i);
            if(nexChild instanceof ImageView){
                int rando = random.nextInt(6) + 1;
                listGroup.add(rando);
                switch (rando){
                    case 1:
                        ((ImageView) nexChild).setImageResource(R.drawable.dice1);
                        break;
                    case 2:
                        ((ImageView) nexChild).setImageResource(R.drawable.dice2);
                        break;
                    case 3:
                        ((ImageView) nexChild).setImageResource(R.drawable.dice3);
                        break;
                    case 4:
                        ((ImageView) nexChild).setImageResource(R.drawable.dice4);
                        break;
                    case 5:
                        ((ImageView) nexChild).setImageResource(R.drawable.dice5);
                        break;
                    case 6:
                        ((ImageView) nexChild).setImageResource(R.drawable.dice6);
                }

            }

        }
        ClearList(round);
    }

    private void ClearList(String round) {
        TextView textView = new TextView(this);

        logHandler.addView(textView);
        if(turn > 6 ){
            logHandler.removeAllViews();
            turn = 0;
        }

        listItem.put(round, listGroup);

        textView.setText(listItem.toString());

        Toast.makeText(this, listItem.toString(), Toast.LENGTH_LONG).show();

        listItem.clear();
        listGroup.clear();
    }

    public ArrayList<Integer> getListGroup(){
        return listGroup;
    }

    public HashMap<String, ArrayList<Integer>> getListItem(){
        return listItem;
    }
}
