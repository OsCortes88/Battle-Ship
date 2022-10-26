import java.util.*;
/*
*Name: Battleship Project
*Author: Oswaldo Cortes
*Date: February 23, 2018
*Summary: This program simulates a single player game of Battleship.
*Players have 20 attempts to find all of the enemy’s ships or they will lose.
*This project used 2D arrays, nested for loops, HashMaps, parallel arrays, methods, etc.
*/

public class MyProgram extends ConsoleProgram
{
    static char [][] userView = {
        {'~','~','~','~','~','~'},
        {'~','~','~','~','~','~'},
        {'~','~','~','~','~','~'},
        {'~','~','~','~','~','~'},
        {'~','~','~','~','~','~'},
        {'~','~','~','~','~','~'}
    };
    
    static char [][] board = {
        {'~','~','T','~','D','~'},
        {'B','~','~','~','D','~'},
        {'B','~','~','~','~','~'},
        {'B','L','L','L','~','~'},
        {'B','~','~','~','~','~'},
        {'~','~','~','R','R','~'}
    };
    
    //This heps infecate the rows.
    static char[] letters = {'A','B','C','D','E','F'};
    
    //This helps indicate te colums.
    static int [] nums = {0,1,2,3,4,5};
    
    //This works as an answer key to now how many slots each ship covers.
    static HashMap<Character, Integer> ships = new HashMap<Character, Integer>();

    public void run()
    {
        ships.put('T', 1);
        ships.put('B', 4);
        ships.put('L', 3);
        ships.put('D', 2);
        ships.put('R', 2);
        
        printDirections();
        printUserBoard();
        
        //The following 2 arrays are parrellel arrays.
        int [] shipHits = {0,0,0,0,0};
        
        //This is an abreveation of the ship names.
        char [] shipLetters = {'T','B','L','D','R'};
        int times = 0;
        
        //THis boolean will later work to verify if the user lost the game.
        boolean fail = true;
        
        //This wil ask the user to enter the coordinates for 20 times.
        while(times != 20)
        {
            
            String letter = readLine("\n\nPlease enter in a letter to indicate the row: ");
            //This will work as an "ignoreCase" method with the string the user enters.
            char option = Character.toUpperCase(letter.charAt(0));
            int num = readInt("Please enter in a number to indicate the column: ");
            //THis boolean will save the value that is returned when it checks the slot.
            //It will also print whether or not the user has hit the sip or if they entered the wrong values.
            boolean d = checkSlot(option, num, shipLetters, shipHits);
            
            //This will ask  the user to enter in the correct values if they entered the wrong values.
            if(d == false)
            {
                //This will end the while loop.
                boolean second = false;
                //This while loop will keep asking the user to enter the correct values until they enter the correct values.
                while(second != true)
                {
                    String letter2 = readLine("\n\nPlease enter in a letter to indicate the row: ");
                    //This will work as an "ignoreCase" method with the string the user enters.
                    char option2 = Character.toUpperCase(letter2.charAt(0));
                    int num2 = readInt("Please enter in a number to indicate the column: ");
                    boolean ad = checkSlot(option2, num2, shipLetters, shipHits);
                    //This will set the  variable the value of ad and will determine if the while loop should stop or end.
                    //depending on the value of ad.
                    second = ad;
                }
            }
            printUserBoard();
            boolean cw = checkWinner(shipLetters, shipHits);
            fail = cw;
            //This will set the varible fail and will check if the user has won.
            //If he did then it will stop the while loop and will print that the user won and will end the game.
            if(fail == true)
            {
                break;
            }
            //This keeps track of the turns the user has.
            times++;
        }
        // This will do the following code if the user lost.
        if(fail == false)
        {
            System.out.println("\n\n~~I'm sorry (not sorry), but you lost.~~" + "\nHere's the winning board:\n");
            //The following for lopps will print the winning board for the user.
            for(int i = 0; i < nums.length; i++)
            {
                System.out.print("\t" + nums[i]);
            }
            System.out.print("");
            for(int row = 0; row < board.length; row++)
            {
                
                for(int col = 0; col < board[row].length; col++)
                {
                    if(col == 0)
                    {
                        System.out.print("\n" + letters[row]);
                    }
                    System.out.print("\t" + board[row][col]);
                }
                
            }
            //This will give the last statement of the game and will end.
            System.out.println("\n\nThanks for playing! Try again if you wish to beat me.");
        }
        
    }
    
    public void printDirections()
    {
        System.out.println("\t\tWelcome to the game of Battleship!\nYour job is to uncover all of the hidden ships on the board." + 
        "You will enter a letter for a row (A-F) and a number for\nthe column (0-5) to undicate which spot you want to hit.");
        
        System.out.println("\nThere are 5 ships you will need to find:" + "\n\t\tThe Tirpitz (T) -- covers 1 slot" +
    	"\n\t\tThe Bismark (B) -- covers 4 slots" + "\n\t\tThe Lusitania (L) -- covers 3 slots" + "\n\t\tThe Deathship (D) -- covers 2 slots" +
	    "\n\t\tThe Roma (R) -- covers 2 slot");
	
        System.out.println("\nYou have 20  attempts to uncover all of the enemies ships.\n\t\t\tGood luck!\n\n");

    }
    
    
    public void printUserBoard()
    {
        for(int i = 0; i < nums.length; i++)
        {
            System.out.print("\t" + nums[i]);
        }
        System.out.print("");
        for(int row = 0; row < userView.length; row++)
        {
            
            for(int col = 0; col < userView[row].length; col++)
            {
                if(col == 0)
                {
                    System.out.print("\n" + letters[row]);
                }
                System.out.print("\t" + userView[row][col]);
            }
            
        }
    }
    
    public boolean checkSlot(char row, int col, char[] shipLetters, int[] shipHits)
    {
        
        if(row == 'A' && col < board[0].length)
        {
            if (userView[0][col] == '*' || userView[0][col] == 'T'|| userView[0][col] == 'B'|| userView[0][col] == 'L'|| userView[0][col] == 'D'|| userView[0][col] == 'R')
            {
                System.out.println("\nWrong input. Please try again.");
                return false;
            }
            for(int i = 0; i < shipLetters.length; i++)
            {
                if(board[0][col] == shipLetters[i])
                {
                    userView[0][col] = shipLetters[i];
                    shipHits[i]++;
                    System.out.println("\nYou Hit Something!!!\n");
                    return true;
                }
            }
            if (board[0][col] != 'T' ||board[0][col] !=  'B' ||board[0][col] !=  'L' ||board[0][col] !=  'D' ||board[0][col] !=  'R') 
            {
                userView[0][col] = '*';
                System.out.println("\nSorry. You missed the shot.\n");
                return true;
            }
            
            
        }
        else if(row == 'B' && col < board[0].length)
        {
            if (userView[1][col] == '*')
            {
                System.out.println("\nWrong input. Please try again");
                return false;
            }
            for(int i = 0; i < shipLetters.length; i++)
            {
                if(board[1][col] == shipLetters[i])
                {
                    userView[1][col] = shipLetters[i];
                    shipHits[i]+=1;
                    System.out.println("\nYou Hit Something!!!\n");
                    return true;
                }
            }
            if (board[1][col] != 'T' ||board[1][col] !=  'B' ||board[1][col] !=  'L' ||board[1][col] !=  'D' ||board[1][col] !=  'R') 
            {
                userView[1][col] = '*';
                System.out.println("\nSorry. You missed the shot.\n");
                return true;
            }
            
        }
        
        else if(row == 'C' && col < board[0].length)
        {
            if (userView[2][col] == '*')
            {
                System.out.println("\nWrong input. Please try again");
                return false;
            }
            for(int i = 0; i < shipLetters.length; i++)
            {
                if(board[2][col] == shipLetters[i])
                {
                    userView[2][col] = shipLetters[i];
                    shipHits[i]+=1;
                    System.out.println("\nYou Hit Something!!!\n");
                    return true;
                }
            }
            if (board[2][col] != 'T' ||board[2][col] !=  'B' ||board[2][col] !=  'L' ||board[2][col] !=  'D' ||board[2][col] !=  'R') 
            {
                userView[2][col] = '*';
                System.out.println("\nSorry. You missed the shot.\n");
                return true;
            }
        }
        
        else if(row == 'D' && col < board[0].length)
        {
            if (userView[3][col] == '*')
            {
                System.out.println("\nWrong input. Please try again");
                return false;
            }
            for(int i = 0; i < shipLetters.length; i++)
            {
                if(board[3][col] == shipLetters[i])
                {
                    userView[3][col] = shipLetters[i];
                    
                    System.out.println("\nYou Hit Something!!!\n");
                    shipHits[i]++;
                    return true;
                }
            }
            if (board[3][col] != 'T' ||board[3][col] !=  'B' ||board[3][col] !=  'L' ||board[3][col] !=  'D' ||board[3][col] !=  'R') 
            {
                userView[3][col] = '*';
                System.out.println("\nSorry. You missed the shot.\n");
                return true;
            }
        }
        else if(row == 'E' && col < board[0].length)
        {
            if (userView[4][col] == '*')
            {
                System.out.println("\nWrong input. Please try again");
                return false;
            }
            for(int i = 0; i < shipLetters.length; i++)
            {
                if(board[4][col] == shipLetters[i])
                {
                    userView[4][col] = shipLetters[i];
                    
                    System.out.println("\nYou Hit Something!!!\n");
                    shipHits[i]++;
                    return true;
                }
            }
            if (board[4][col] != 'T' ||board[4][col] !=  'B' ||board[4][col] !=  'L' ||board[4][col] !=  'D' ||board[4][col] !=  'R') 
            {
                userView[4][col] = '*';
                System.out.println("\nSorry. You missed the shot.\n");
                return true;
            }
        }
        
        else if(row == 'F' && col < board[0].length)
        {
            if (userView[5][col] == '*')
            {
                System.out.println("\nWrong input. Please try again");
                return false;
            }
            for(int i = 0; i < shipLetters.length; i++)
            {
                if(board[5][col] == shipLetters[i])
                {
                    userView[5][col] = shipLetters[i];
                    shipHits[i]++;
                    System.out.println("\nYou Hit Something!!!\n");
                    return true;
                }
            }
            if (board[5][col] != 'T' ||board[5][col] !=  'B' ||board[5][col] !=  'L' ||board[5][col] !=  'D' ||board[5][col] !=  'R') 
            {
                userView[5][col] = '*';
                System.out.println("\nSorry. You missed the shot.\n");
                return true;
            }
        }
        else
        {
            System.out.println("\nWrong iput. Please try again");
        }
        return false;
        
        
    }
    
    public boolean checkWinner(char[] shipLetters, int[] shipHit)
    {
        for(int i = 0; i < shipLetters.length; i++)
        {
            if(ships.get(shipLetters[i]) != shipHit[i])
            {
                return false;
            }
        }
        System.out.println("\n~~~~~~Congradulation. This is the first and last time you will beat a computer.~~~~~~\n\t\t\t~~~~~~You should feel proud.~~~~~~"
        + "\n\n\nThanks for playing! Make another attemp if you DARE.");
        return true;
    }
    
}