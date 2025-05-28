import java.util.*;


//game states will be initial state -> Playing ->Won/lose -> play again
//                                       


class GuessGame{

    public GuessGame(){}   
    
    public static Scanner sc = new Scanner(System.in); //Global input Scanner

    public int genRandNo(){                     //genrates Random number
        Random rand = new Random();
        return rand.nextInt(20);  
    }
    public void playGame(int value){            // main gameplay 
        // System.out.println(value);           //Random Gen No 
        int chances = 3;
      
        
        while (chances >= 0){
            
            System.out.println("\nChances Left  : " + chances);
            System.out.println("Enter -1 for Exit to Menu");
            System.out.println("The Number is between 0 - 20");
            System.out.println("Enter the Number");
            int ans = sc.nextInt();

            if(value == ans){
                System.out.println("Congrats!! You Guessed the Right Number!");
                return;
            }
            else if(-1 == ans){
                return;
            }
            else if (ans > 20 || ans < -1) {
                System.out.println("Number is out of Bound");
            }
            else{
                System.out.println("Wrong Number");
                chances--;
            }

        }
        System.out.println("Game Over!!");

        // System.out.println("Game Playing");
    }
    public static void main(String[] args){
        int choice;
        GuessGame game = new GuessGame();
       
    
        while(true){                    // choice loop
            System.out.print("\n Start Game : 1 \n Exit Game : 0 \nEnter the choice : ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1: //Start Game  
                        game.playGame(game.genRandNo());
                    break;
                case 0: //Exit Game  
                        return ;
                default:
                    System.out.println("Ivalid input!!");
                    return;                    
            }
        }
    }
}