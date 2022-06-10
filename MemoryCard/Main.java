import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static String [][] panel = new String[4][4];
    public static String [][] cards = new String[4][4];
    public static Scanner input = new Scanner(System.in);

    //Making the panel
    public static void printPanel(){
        for(int i = 0; i < 4; i++){ //Number of rows
            System.out.print("|");

            for(int j = 0; j < 4; j++){ //Number of columns
                System.out.print(panel[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public static void shuffleCards(){
        Random random = new Random();
        //A list of letters, doubled because of pairing
        ArrayList<String> letters = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H",
                                                                  "A","B","C","D","E","F","G","H"));

        //Randomizes the card placements
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                int index = random.nextInt(letters.size());
                cards[i][j] = letters.get(index);
                //Prevents repetition
                letters.remove(index);
            }
        }
    }

    //Playing and checking
    public static void checkInput(String [][] cards){
        while(true){
            if(!gameOver()){
                //First card choice
                System.out.println("Row: 1-4");
                int row1 = input.nextInt();
                while(!(1 <= row1 && row1 <= 4)){ //If out of bounds
                    System.out.println("Please input 1-4");
                    row1 = input.nextInt();
                }

                System.out.println("Column: 1-4");
                int column1 = input.nextInt();
                while(!(1 <= column1 && column1 <= 4)){ //If out of bounds
                    System.out.println("Please input 1-4");
                    column1 = input.nextInt();
                }

                //If the pair has been found already
                if(!panel[row1-1][column1-1].equals(" - ")){
                    System.out.println("Already Entered!");
                    System.out.println();

                    printPanel();
                    continue;
                }else{
                    //Revealing that card
                    panel[row1-1][column1-1] = " " + cards[row1-1][column1-1] + " ";
                    printPanel();
                }

                //Second card choice
                System.out.println("Row: 1-4");
                int row2 = input.nextInt();
                while(!(1 <= row2 && row2 <= 4)){ //If out of bounds
                    System.out.println("Please input 1-4");
                    row2 = input.nextInt();
                }

                System.out.println("Column: 1-4");
                int column2 = input.nextInt();
                while(!(1 <= column2 && column2 <= 4)){ //If out of bounds
                    System.out.println("Please input 1-4");
                    column2 = input.nextInt();
                }

                //If the pair has been found already
                if(!panel[row2-1][column2-1].equals(" - ")){
                    System.out.println("Already Entered!");

                    //Closes the card
                    panel[row1-1][column1-1] = " - ";
                    System.out.println();

                    printPanel();
                    continue;
                }else{
                    //Revealing that card
                    panel[row2-1][column2-1] = " " + cards[row2-1][column2-1] + " ";

                    //Checks if the pair is wrong
                    if(panel[row1-1][column1-1].equals(panel[row2-1][column2-1])){
                        //Reveals card throughout the game if it's correct. 
                        printPanel();
                        System.out.println("");
                    }else{
                        printPanel();
                        System.out.println("");
                        //Closes the card
                        panel[row1-1][column1-1] = " - ";
                        panel[row2-1][column2-1] = " - ";
                        printPanel();
                    }
                }

            }else{
                System.out.println("You win!!");
                break;
            }
        }
    }

    //Checks if there's an uncovered pair yet
    public static boolean gameOver(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(panel[i][j].equals(" - ")){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        boolean flag = true;
        System.out.println("Welcome to Memory Card Game.");
        //Choosing to play or quit
        do{
            System.out.println("1. New game");
            System.out.println("2. Quit");
            System.out.print("Choice >> ");
            int option = input.nextInt();

            if(!(option == 1 || option == 2)){
                option = 3;
            }
            switch(option){
                case 1: 
                    shuffleCards();

                    //Set everything to " - "
                    for(int i = 0; i < 4; i++){
                        for(int j = 0; j < 4; j++){
                            panel[i][j] = " - ";
                        }
                    }

                    printPanel();
                    checkInput(cards);
                    break;

                case 2: 
                    System.out.println("Exitting...");
                    flag = false;
                    break;
                
                case 3:
                    System.out.println("Choose either 1 or 2.");
                    break;
            }
        }while(flag);  
    input.close();
    }

}
