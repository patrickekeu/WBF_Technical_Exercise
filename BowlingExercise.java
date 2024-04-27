import java.util.Scanner;


public class BowlingExercise {


    public int newscore(String game) // Game must be in the format "X-X-X-X-X-X-X-X-X-XXX" with 10 frames
    {
        // declare variables
        int score = 0;
        int frame = 1;
        boolean bonusmode = false;
        char finalchar = game.charAt(game.length() - 1);
        int finalnum = Character.getNumericValue(finalchar);

        // Split the game into frames using endframe indices
        int endframe1 = game.indexOf('-');
        int endframe2 = game.indexOf('-', endframe1 + 1);
        int endframe3 = game.indexOf('-', endframe2 + 1);
        int endframe4 = game.indexOf('-', endframe3 + 1);
        int endframe5 = game.indexOf('-', endframe4 + 1);
        int endframe6 = game.indexOf('-', endframe5 + 1);
        int endframe7 = game.indexOf('-', endframe6 + 1);
        int endframe8 = game.indexOf('-', endframe7 + 1);
        int endframe9 = game.indexOf('-', endframe8 + 1);


        String[] frames = new String[10]; // A list of all the frames in bowling game
        

        // Get all the frames in the game
        frames[0] = game.substring(0, endframe1);
        frames[1] = game.substring(endframe1 + 1, endframe2);
        frames[2] = game.substring(endframe2 + 1, endframe3);        
        frames[3] = game.substring(endframe3 + 1, endframe4);        
        frames[4] = game.substring(endframe4 + 1, endframe5);        
        frames[5] = game.substring(endframe5 + 1, endframe6);        
        frames[6] = game.substring(endframe6 + 1, endframe7);        
        frames[7] = game.substring(endframe7 + 1, endframe8);        
        frames[8] = game.substring(endframe8 + 1, endframe9);        
        frames[9] = game.substring(endframe9 + 1);
                
        int[] frameScore = new int[11];
        

        // Calculate the initial score for each frame
        for(int i = 0; i < 10; i++)
        {
            if(frames[i].equals("X")) // If the frame is a strike
            {
                frameScore[i] = 10;
                
            }
            else if(frames[i].contains("/")) // If the frame is a spare
            {
                frameScore[i] = 10;
            }
            else if(frames[i].equals("XXX")) // If the final frame has 3 strikes
            {
                frameScore[i] = 30;
            }
            else if(frames[9].equals("XX" + finalnum)) // If the final frame has 2 strikes and a number
            {
                frameScore[9] = 20 + finalnum;
                
            }
            else
            {
                frameScore[i] = Integer.parseInt(frames[i]); // Convert the frame to an integer
                int actualscore = findSum(frameScore[i]); // Calculate the actual score for the frame
                frameScore[i] = actualscore;
            }
            
        }
        score = 0; // Initialize the final score to 0

        // Calculate the final score for each frame including bonuses

        for(int i = 0; i < 10; i++)
        {
            if(frames[i].equals("X")) // If the current frame is a strike
            {
                // If the next two frames are strikes
                if(frames[i + 1].equals("X") && frames[i + 2].equals("X"))
                {
                    frameScore[i] = 30;
                }
                else if(frames[i + 1].equals("X"))
                {
                    if(frames[i + 2].contains("X")) // Checks whether the last frame contains a strike
                    {
                        frameScore[i] = 30;
                    }
                    else
                    {
                        frameScore[i] = 20 + Integer.parseInt(frames[i + 2].charAt(0) + "");
                    }
                }
                else if(frames[i + 1].contains("/")) // If the next frame contains a spare
                {
                    frameScore[i] = 20;
                }
                else
                {
                    frameScore[i] = 10 + frameScore[i + 1]; // Add the next two normal rolls to the current frame
                }
                
            }
            else if(frames[i].contains("/")) // If the current frame is a spare
            {
                if(i < 9 && frames[i + 1].equals("X")) // If the next frame is a strike
                {
                    frameScore[i] = 20; 
                }
                else
                {
                    if(i < 9)
                    {
                        frameScore[i] = 10 + frames[i + 1].charAt(0) - '0'; // Add the next roll to the current frame
                    }
                    else
                    {
                        frameScore[i] = 10 + finalnum;  // Add the final roll to the current frame
                    }
                    
                }
            }
            else
            {
                
            }
                
            if(frameScore[i] > 30) // If somehow the frame score is greater than 30, set it to 30
            {
                frameScore[i] = 30;
            }
            
            score += frameScore[i]; // Add the frame score to the total score

        }

        return score; // Returns the final score
    }

    static int findSum(int number)  //function that calculates the sum of the digits of the number
    {         
        int sum = 0;  
        while (number != 0)  
        {  
            //finds the last digit from the number and add it to the variable sum      
            sum = sum + number % 10;  
             //removes the last digit  
            number = number/10;  
        }  
        //returns the sum  
        return sum;  
    }
    

    public static void main(String[] args) {
        BowlingExercise bowling = new BowlingExercise();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the game: ");
        String game = scanner.nextLine();
        System.out.println("Total Score: " + bowling.newscore(game));
    }
}

    


