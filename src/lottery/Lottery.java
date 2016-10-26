package lottery;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Lottery {
    
    private static List<Integer> winningNumbers = new ArrayList<>();
    private static List<Integer> chosenNumbers = new ArrayList<>();
    private static int winCount = 0, reward, choice;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) 
    {
        genLotteryNumbers();
        getChosenNumbers();
        getReward();
    }

    private static void genLotteryNumbers() 
    {
        //generate 7 random numbers between 1 and 49
        for (int i = 0; i < 7; i++)
        {
            int number = (int) (Math.ceil(Math.random() * 49));
            
            while (true)
            {
                if (winningNumbers.contains(number))
                {
                    number = (int) (Math.ceil(Math.random() * 49));
                }
                
                else
                {
                    winningNumbers.add(number);
                    break;
                }
            }
        }
    }
    
    private static void getChosenNumbers()
    {
        System.out.println("Enter your seven numbers:");

        //get the numbers from the user
        for (int i = 0; i < 7; i++)
        {
            //while the input is invalid
            while (true) 
            {  
                try 
                {
                    choice = sc.nextInt();
                    
                    //is the chosen number in the correct range
                    if (choice < 1 || choice > 49)
                    {
                        System.out.println("your choice must be between 1 and 49, please re-enter your choice");
                        continue;
                    }
                    
                    //has the chosen number already been chosen
                    if (chosenNumbers.contains(choice))
                    {
                        System.out.println("You have already selected that number, please enter a different number");
                        continue;
                    }
                    
                    //break loop if inputted value is valid
                    break;
                } 
                
                //catch if inputted value is not integer and reset scanner so it can be re-entered
                catch (InputMismatchException exception) 
                {
                    System.out.println("invalid input, please re-enter your choice");
                    sc.next();
                }
            }
            
            //add the chosen number to the list
            chosenNumbers.add(choice);
            
            //if the chosen number is in the list of winning numbers, add one the the win count
            if (winningNumbers.contains(choice))
            {
                winCount += 1;
            }
            
        }
    }
    
    private static void getReward()
    {
        //get the reward amount based on the number of correct numbers
        switch (winCount)
        {
            default: reward = 0; break;
            case 3: reward = 10; break;
            case 4: reward = 100; break;
            case 5: reward = 10000; break;
            case 6: reward = 100000; break;
            case 7: reward = 1000000; break;
            
        }
        
        System.out.println("the winning numbers are " + winningNumbers + ", your choices were " + chosenNumbers + ", you win £" + reward);
    }
    
}