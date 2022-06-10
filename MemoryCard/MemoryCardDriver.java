public class MemoryCardDriver extends MemoryCardClass {
    public static void main(String[] args){
        MemoryCardClass g1 = new MemoryCardClass();

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
                    System.out.println("Choose Difficulty: ");
                    System.out.println("1. Normal");
                    System.out.println("2. Custom");
                    System.out.print("Choice >> ");
                    int option2 = input.nextInt();

                    if(option2 == 1){
                        g1.setSize(4, 4);

                    }else if(option2 == 2){ //Customize
                        int rowSize;
                        int columnSize;
                        do{
                            System.out.print("Row Size >> ");
                            rowSize = input.nextInt();

                            System.out.print("Column Size >> ");
                            columnSize = input.nextInt();

                            if (rowSize * columnSize % 2 == 0){
                                break;
                            }else{ //The size must be even because of pairing
                                System.out.println("One of them must be even");
                            }
                        }while(true);
                        g1.setSize(rowSize, columnSize);
                    }
    
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
                    System.out.println("Goodbye :]");
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
