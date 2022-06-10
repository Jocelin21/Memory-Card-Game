import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class MemoryCardClass {
    private static int rowSize;
    private static int columnSize;

    public static String [][] panel = new String[10][10];
    public static String [][] cards = new String[10][10];
    public static Scanner input = new Scanner(System.in);

    public void setSize(){
        this.rowSize = 4;
        this.columnSize = 4;
    }

    public void setSize(int rowSize, int columnSize){
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }

    public int getRowSize(){
        return this.rowSize;
    }

    public int getColumnSize(){
        return this.columnSize;
    }

    //Making the panel
    public static void printPanel(){
        for(int i = 0; i < rowSize; i++){ //Number of rows
            System.out.print("|");

            for(int j = 0; j < columnSize; j++){ //Number of columns
                System.out.print(panel[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public static void shuffleCards(){
        Random random = new Random();
        ArrayList<String> letters = new ArrayList<String>();

        //Amount of letter for that row and column size
        int letterSize = rowSize * columnSize / 2;

        //Making the list of letters depending on the sizes
        for(int i = 0; i < (letterSize); i++){
            char letter = 'A';
            int convert = letter + i;
            String s = String.valueOf((char)convert);
            letters.add(s);
            letters.add(s);
        }

        //Randomizes the card placements
        for(int i = 0; i < rowSize; i++){
            for(int j = 0; j < columnSize; j++){
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
                System.out.print("Row: ");
                int row1 = input.nextInt();
                while(!(1 <= row1 && row1 <= rowSize)){ //If out of bounds
                    System.out.println("Please input 1 - " + rowSize);
                    row1 = input.nextInt();
                }

                System.out.print("Column: ");
                int column1 = input.nextInt();
                while(!(1 <= column1 && column1 <= columnSize)){ //If out of bounds
                    System.out.println("Please input 1 - " + columnSize);
                    column1 = input.nextInt();
                }

                //If the pair has been found already
                if(!panel[row1-1][column1-1].equals(" ? ")){
                    System.out.println("Choose another one!");
                    System.out.println();

                    printPanel();
                    continue;
                }else{
                    //Revealing that card
                    panel[row1-1][column1-1] = " " + cards[row1-1][column1-1] + " ";
                    printPanel();
                }

                //Second card choice
                System.out.print("Row: ");
                int row2 = input.nextInt();
                while(!(1 <= row2 && row2 <= rowSize)){ //If out of bounds
                    System.out.println("Please input 1 - " + rowSize);
                    row2 = input.nextInt();
                }

                System.out.print("Column: ");
                int column2 = input.nextInt();
                while(!(1 <= column2 && column2 <= columnSize)){ //If out of bounds
                    System.out.println("Please input 1 - " + columnSize);
                    column2 = input.nextInt();
                }

                //If the pair has been found already
                if(!panel[row2-1][column2-1].equals(" ? ")){
                    System.out.println("Choose another one!");

                    //Closes the card
                    panel[row1-1][column1-1] = " ? ";
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
                        panel[row1-1][column1-1] = " ? ";
                        panel[row2-1][column2-1] = " ? ";
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
        for(int i = 0; i < rowSize; i++){
            for(int j = 0; j < columnSize; j++){
                if(panel[i][j].equals(" ? ")){
                    return false;
                }
            }
        }
        return true;
    }

}
