import java.awt.*;
import java.applet.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.*;
import java.awt.Panel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;


public class HigherOrLower {
    
     private static final int WIN_WIDTH = 600;
    private static final int WIN_HEIGHT = 500;
    private static Window window;   
    public static void main (String [] args)
    {
        System.out.println("Welcome to HigherOrLower!!");
        System.out.println("A card will be dealt");
        System.out.println("And you will have to predict if it is higher or lower");
        System.out.println("GOOD LUCK!");
        System.out.println();

        int highestScore=0;
        boolean playAgain;
        int money=500;
        int bet=0;
        Scanner scan=new Scanner(System.in);
        do{
            int scoreThisGame;
            scoreThisGame=play();
            if(scoreThisGame>highestScore) highestScore=scoreThisGame;
            System.out.print("PLay Again?");
            playAgain=scan.nextBoolean();
        }   while(playAgain);

        System.out.println();
        System.out.println("Your highst score was "+ highestScore);
    }
    
    public static void printboard(boolean[][] board){
        for(int i=0;i<20;i++){
        for(int j=0;j<20;j++){
        if(board[i][j]==false){
        System.out.print(" ");
        }else{
        System.out.print("■ ");
        }
        }
        System.out.println();
        }
        try{
        Thread.sleep(100);
        }catch(InterruptedException ex){
        Thread.currentThread().interrupt();
        }
        }
        class Window{
            public JFrame windowFrame;
            
            public Screen a;
            
            public boolean running=false;
            public boolean flashing=false;
            public Window main=this;
        
            public boolean password=true;
            
            Window(int width,int height,String title){
                windowFrame=new JFrame();
                windowFrame.setBounds(0,0,width,height);
                windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                windowFrame.setCursor(new Cursor(Cursor.HAND_CURSOR));
                windowFrame.setTitle(title);
                windowFrame.setResizable(false);
                windowFrame.setLayout(null);
                
                windowFrame.setVisible(true);
                windowFrame.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
                windowFrame.setSize(width,height);
            a=new Screen(this);
            
        }
    }
    class Screen extends Canvas{
        public int[][] array=new int[8][8];
        public boolean[][] available=new boolean[8][8];
        public int player=1;
        private Window main;
        private boolean won=false;
        public int winner=0;
        public boolean hints=true;
        
        public Screen(Window mainin){
            main=mainin;
            setSize(321,321);
            setBackground(new Color(0,0,0));
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    array[i][j]=0;
                }
            }
            array[3][3]=1;
            array[4][4]=1;
            array[3][4]=2;
            array[4][3]=2;
            
        }
    }
        private static int play()
        {   
            int moneymade=0;
            int money=500;
            Deck deck=new Deck();
            Card currCard;
            Card nextCard;
            int correctGuesses;
            String guess;
            deck.shuffle();
            correctGuesses=0;
            currCard=deck.dealCard();
            System.out.println("The first card is the " + currCard);

            while(true && money>0)
            {   int bet;
                
                    Scanner console = new Scanner(System.in);
                    System.out.println("Please, enter the bet you want to make.");
                    System.out.println("You have this amount of money " +  money);
                    if(console.hasNextInt()){
                        bet=console.nextInt();
                    }else{
                        return 0;
                    }
                
                Scanner scann=new Scanner(System.in);
                System.out.println("Will the next card be higher(H) or lower (L)");
                Scanner scan=new Scanner(System.in);
                do{
                    
                    guess=scan.next();
                    if(!guess.equals("H") && !guess.equals("L"))
                    System.out.println("Please respond with a 'H' or 'L'");
               
                
                }while(!guess.equals("H") && !guess.equals("L"));

                nextCard=deck.dealCard();
                System.out.println("The next card is " + nextCard);

                if(nextCard.getValue()== currCard.getValue())
                {
                    System.out.println("The value is the same as the previous card");
                    System.out.println("You lose on ties");
                    money=money-bet;
                    
                }
                else if(nextCard.getValue()> currCard.getValue())
                {
                    if(guess.equals("H")){
                        System.out.println("Your prediction was correct!");
                        moneymade=moneymade+bet;
                        money=money+bet;
                        correctGuesses++;
                    }
                    else{
                        System.out.println("Your prediction was incorrect!");
                        money=money-bet;
                    }
                }
                else{
                    if(guess.equals("L"))
                    {
                        System.out.println("Your prediction was correct!");
                        money=money+bet;
                        moneymade=moneymade+bet;
                        correctGuesses++;
                    }
                    else{
                        System.out.println("Your prediction was incorrect!");
                        money=money-bet;
                    }
                }

                currCard=nextCard;
                System.out.println();
                System.out.println("The card is " + currCard);
            }

            System.out.println();
            System.out.println("You made this amount " + moneymade);
            System.out.println("The game is over.You made " + correctGuesses + " correct guesses!");
            System.out.println();

            return correctGuesses;
        }
    
}
