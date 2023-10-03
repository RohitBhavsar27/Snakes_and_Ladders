package snakeS_ladders_game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class SnakeLadder{
    final static int WINPOINT = 100;
    static Map<Integer, Integer> snake = new HashMap <>();
    {
        snake.put(99,54);
        snake.put(70,55);
        snake.put(52,42);
        snake.put(25,2);
        snake.put(95,72);
    }
    static Map<Integer, Integer> ladder = new HashMap <>();
    {
        ladder.put(6,25);
        ladder.put(11,40);
        ladder.put(60,85);
        ladder.put(46,90);
        ladder.put(17,69);
    }

    public int rollDice(){
        int n = 0;
        Random r = new Random();
        n = r.nextInt(7);
        if(n == 0){
            return 1;
        } else {
            return n;
        }
    }

    public int calculatePlayerPositionAfterRollingDice(int currentPosition, int diceValue){
        int playerNewPosition = currentPosition + diceValue;

        if(playerNewPosition > WINPOINT){
            return currentPosition;
        }
        if(snake.get(playerNewPosition) != null){
            System.out.println("OOPS !!, Swallowed By The Snake..");
            playerNewPosition = snake.get(playerNewPosition);
        }

        if(ladder.get(playerNewPosition) != null){
            System.out.println("YAY !!, Climbing The Ladder..");
            playerNewPosition = ladder.get(playerNewPosition);
        }
        return playerNewPosition;
    }

    public boolean isWin(int currentPosition){
        return(WINPOINT == currentPosition);
    }

    public void startGame(){
        int player1_Pos = 0;
        int player2_Pos = 0;
        int whosTurn = -1;

        Scanner sc = new Scanner(System.in);
        String diceRolled;
        int diceValue = 0;
        do{
            if(whosTurn == -1){
                System.out.println("\n\n First Player's Turn");
            } else{
                System.out.println("\n\n Second Player's Turn");
            }
            System.out.println("Press 'R' to roll the Dice");
            diceRolled = sc.next();
            diceValue = rollDice();

            if(whosTurn == -1){
                player1_Pos = calculatePlayerPositionAfterRollingDice(player1_Pos, diceValue);
                System.out.println("First Player Position: " + player1_Pos);
                System.out.println("Second Player Position: " + player2_Pos);
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");

                if(isWin(player1_Pos)){
                    System.out.println("Congratulation !!, First player Won");
                    return;
                }
            } else{
                player2_Pos = calculatePlayerPositionAfterRollingDice(player2_Pos, diceValue);
                System.out.println("First Player Position: " + player1_Pos);
                System.out.println("Second Player Position: " + player2_Pos);
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");

                if(isWin(player2_Pos)){
                    System.out.println("Congratulation !!, Second player Won");
                    return;
                }
            }
            whosTurn = -whosTurn;
        }
        while("R".equals(diceRolled));
    }
}

public class Application{
    public static void main(String [] args){
        SnakeLadder sn = new SnakeLadder();
        sn.startGame();
    }
}
