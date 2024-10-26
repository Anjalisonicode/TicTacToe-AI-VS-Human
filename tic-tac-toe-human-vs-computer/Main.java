import java.util.*;
public class Main{
    
     // System.out.println(" TIC TAC TOE GAME HUMAN VS COMPUTER(AI)  -->");
       //    Scanner sc=new Scanner(System.in);
       public static int SIDE=3;
       public static char board[][]=new char[SIDE][SIDE];
       public static int COMPUTER=1;
        public static int HUMAN=2;
        public static char COMPUTERMOVE='0';
       public static char HUMANMOVE= 'X';
        public static char want ='y';
   
    public static void main(String args[]){
          System.out.println(" TIC TAC TOE GAME HUMAN VS COMPUTER(AI)  -->");
           Scanner sc=new Scanner(System.in);
           System.out.println("please choose your goti , do you want X or 0, please select one");
          // HUMANMOVE =sc.next().charAt(0);
           boolean flag=false;
           while(flag==false){
                HUMANMOVE =sc.next().charAt(0);
           if(HUMANMOVE=='X'){
               HUMANMOVE='X';
               COMPUTER='0';
               flag=true;
           }else if(HUMANMOVE=='0'){
                HUMANMOVE='0';
               COMPUTERMOVE='X';
               flag=true;
               
           }else {
               System.out.println("invalid selection , please choose between X and 0");
           }
           }
        do{
            System.out.println("Do you want to start first (y/n) : ");
            char choise=sc.next().charAt(0);
            if(choise=='y'){
                playTacToe(HUMAN, board);
            }else if(choise=='n'){
                playTacToe(COMPUTER, board);
            }
            else
            {
                System.out.println("Invalid choise : choose y for yes or n for no.. ");
            }
            System.out.println("DO YOU WANT TO QUIT THE GAME : ");
            want=sc.next().charAt(0);
            
            
            
        }while(want=='n');
        
    }
    
    
    
    // Main logic of playing tic tac toe 
    public static void playTacToe(int WHOSETURN , char board[][]){
        int moveIndex=0, x=0, y=0;
        initialize(board);
       showInstructions();
        Scanner sc=new Scanner(System.in);
       while(gameOver(board)==false&&moveIndex!=SIDE*SIDE){
           
           if(WHOSETURN==HUMAN){
               System.out.println(" SELECT THE ONE CELL OUT OF THE GIVEN CELLS : ");
               for(int i=0;i<SIDE;i++){
                   for(int j=0;j<SIDE;j++){
                       if(board[i][j]=='*'){
                           System.out.print((i*3+j)+1+ " ");
                       }
                   }
               }
               int n = sc.nextInt();
              int temp=n;
               n--;
               x=n/SIDE;
               y=n%SIDE;
               if(board[x][y]=='*'&&n>=0&&n<9){
               board[x][y]=HUMANMOVE;
                 System.out.println("HUMAN HAS PUT HIS MOVE "+ HUMANMOVE + " IN CELL "+ temp);
               showBoard(board);
               moveIndex++;
               WHOSETURN=COMPUTER;
               }else if(board[x][y]!='*'&&n>=0&&n<9){
                   System.out.println(" THIS PLACE IS ALREADY OCCUPIED , PLEASE SELECT A VALID POSITION : ");
               }else if(n<0||n>8){
                   System.out.println(" iNVALID POSITION : ");
               }
               
           }
           else if(WHOSETURN==COMPUTER){
               System.out.println("COMPUTER THINKING AND PLAYING HIS TURN PLEASE WAIT : ");
               int n=bestMove(board, moveIndex);
               int temp=n;
               n--;
               x=n/SIDE;
               y=n%SIDE;
               if(board[x][y]=='*'&&n>=0&&n<9){
               board[x][y]=COMPUTERMOVE;
                System.out.println("COMPUTER HAS PUT HIS MOVE "+ COMPUTERMOVE + " IN CELL "+ temp);
            
               showBoard(board);
               moveIndex++;
               WHOSETURN=HUMAN;
                }else if(board[x][y]!='*'&&n>=0&&n<9){
                   System.out.println(" THIS PLACE IS ALREADY OCCUPIED , PLEASE SELECT A VALID POSITION : ");
               }else if(n<0||n>8){
                   System.out.println(" iNVALID POSITION : ");
               }
               
           }
       }
      if(gameOver(board)==false&&moveIndex==SIDE*SIDE){
          System.out.println("ITS DRAW , NO ONE WIN THIS GAME , PLEASE PLAY AGAIN ");
         System.out.println("Thank You ");
      }else{
      if(WHOSETURN==HUMAN){
          WHOSETURN=COMPUTER;
      }else if(WHOSETURN==COMPUTER){
          WHOSETURN=HUMAN;
      }
      
      declareWinner(WHOSETURN);
      }
       
        
    }
    
    //Functionn for declaring winner 
    public static void declareWinner(int WHOSETURN){
        if(WHOSETURN==COMPUTER){
            System.out.println("COMPUTER HAS WON ");
        }else if(WHOSETURN==HUMAN){
            System.out.println("YOU HAVE WON THIS GAME . CONGRAULATIONS...");
        }
    }
    
    //function to find the best move by the computer using minimax algorithm
   public static int bestMove(char board[][], int moveIndex){
        int x=-1, y=-1;
        int score=0;
        int bestScore=-999;
        for(int i=0;i<SIDE;i++){
            for(int j=0;j<SIDE;j++){
                if(board[i][j]=='*'){
                    board[i][j]=COMPUTERMOVE;
                    score=minimax(board, moveIndex+1, false);
                    board[i][j]='*';
                    if(score>bestScore){
                        bestScore=score;
                        x=i;
                        y=j;
                    }
                    
                }
            }
        }
        return (x*3+y)+1;
    }
    
    //minimax algorith to find the maximum score so that computer can put best move ,, 
    //we find maximum for computer move and minimun for HUMAN
  public static int minimax(char board[][], int depth, boolean isAI) {
        int score = 0;
        int bestScore = 0;

        if (gameOver(board)) {
            if (isAI) {
                return -10;
            } else {
                return 10;
            }
        } else {
            if (depth < 9) {
                if (isAI) {
                    bestScore = Integer.MIN_VALUE; // Negative infinity equivalent
                    for (int i = 0; i < SIDE; i++) {
                        for (int j = 0; j < SIDE; j++) {
                            if (board[i][j] == '*') {
                                board[i][j] = COMPUTERMOVE;
                                score = minimax(board, depth + 1, false);
                                board[i][j] = '*';
                                if (score > bestScore) {
                                    bestScore = score;
                                }
                            }
                        }
                    }
                    return bestScore;
                } else {
                    bestScore = Integer.MAX_VALUE; // Positive infinity equivalent
                    for (int i = 0; i < SIDE; i++) {
                        for (int j = 0; j < SIDE; j++) {
                            if (board[i][j] == '*') {
                                board[i][j] = HUMANMOVE;
                                score = minimax(board, depth + 1, true);
                                board[i][j] = '*';
                                if (score < bestScore) {
                                    bestScore = score;
                                }
                            }
                        }
                    }
                    return bestScore;
                }
            } else {
                return 0; // Draw
            }
        }
    }
    
    
    //initialize the board to the empty initially
    public static void initialize(char board[][]){
        for(int i=0;i<SIDE;i++){
            for(int j=0;j<SIDE;j++){
                board[i][j]='*';
            }
        }
        
    }
    // to visualize the board
    public static void showBoard(char board[][]){
        System.out.println("\t\t\t " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("\t\t\t-----------");
        System.out.println("\t\t\t " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("\t\t\t-----------");
        System.out.println("\t\t\t " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }
    
    
     public static void showInstructions() {
        System.out.println("\nChoose a cell numbered from 1 to 9 as below and play\n");
        System.out.println("\t\t\t 1 | 2 | 3 ");
        System.out.println("\t\t\t-----------");
        System.out.println("\t\t\t 4 | 5 | 6 ");
        System.out.println("\t\t\t-----------");
        System.out.println("\t\t\t 7 | 8 | 9 \n");
    }
    
    //Check for row cross
    public static boolean rowCross(char board[][]){
        for(int i=0;i<SIDE;i++){
           if(board[i][0]==board[i][1]&&board[i][1]==board[i][2]&&board[i][0]!='*'){
               return true;
           }
        }
        return false;
    }
    
    //check for column rowCross
    public static boolean columnCross(char board[][]){
        for(int j=0;j<SIDE;j++){
            if(board[0][j]==board[1][j]&&board[1][j]==board[2][j]&&board[0][j]!='*'){
                return true;
            }
        }
        return false;
    }
    
    
    // check for the diagonal cross
    public static boolean diagonalCross(char board[][] ){
        if(board[0][0]==board[1][1]&&board[1][1]==board[2][2]&&board[0][0]!='*'){
            return true;
        }
         if(board[0][2]==board[1][1]&&board[1][1]==board[2][0]&&board[0][2]!='*'){
            return true;
        }
        
        return false;
    }
    
    //chech for the game over i.e game is over when one of the three cross is happen
    public static boolean gameOver(char board[][]){
        return (columnCross(board)||rowCross(board)||diagonalCross(board));
    }
    
    
    
    
}