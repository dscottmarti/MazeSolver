import java.util.*;
import java.io.*;

/*The Maze class stores the maze in a 2d array, as well as stores relevant data
 *  about the maze and contains methods to manipulate, solve, and print the maze.*/
public class Maze {
//The array the maze is stored in
   private char[][] arr;
//The start and finish points of the maze
   private Point start, finish;
//The maze's length and width
   private int len, wid;
//A HashMap used to store the directions N S E W
   private Map<String, Point> dir;


//The default constructor
   public Maze()
   {
      arr    = new char[0][0];
      start  = new Point(0, 0);
      finish = new Point(0, 0);
      len    = 0;
      wid    = 0;
      dir    = new HashMap<String, Point>();
      dir.put("N", new Point(-1, 0));
      dir.put("S", new Point(1, 0));
      dir.put("E", new Point(0, 1));
      dir.put("W", new Point(0, -1));
   }

//The read method takes a scanner and reads in data about the maze and the maze itself
//and stores it in the Maze object
   public void read(Scanner in)
   {
      //Load the new maze properties in
      len    = in.nextInt();
      wid    = in.nextInt();
      arr    = new char[len + 2][wid + 2];
      start  = new Point(in.nextInt(), in.nextInt());
      finish = new Point(in.nextInt(), in.nextInt());

      //Build walls around the maze to prevent out of bounds checks
      for (int r = 0; r < arr.length; r++)
      {
         arr[r][0] = '*';
         arr[r][arr[r].length - 1] = '*';
      }
      for (int c = 0; c < arr[0].length; c++)
      {
         arr[0][c] = '*';
         arr[arr.length - 1][c] = '*';
      }

      //Move the scanner pointer to the beginning of the maze
      in.nextLine();

      //Read the maze one line at a time and load the characters into the array
      String line = "";
      for (int r = 1; r <= len; r++)
      {
         line = in.nextLine();
         for (int c = 1; c <= wid; c++)
         {
            arr[r][c] = line.charAt(c - 1);
         }
      }
   }

/*The print method prints out the contents of the maze to StdOut*/
   public void print()
   {
      for (int r = 1; r <= len; r++)
      {
         for (int c = 1; c <= wid; c++)
         {
            System.out.print(arr[r][c]);
         }
         System.out.println();
      }
      System.out.println();
   }

/*findPath() recursively searches the maze for a path to the finish given a
 * starting point and the number of steps taken so far, the first call being the
 * starting point and 0. It checks every point around it in the order South, East,
 * North, West to see if it is an open spot that hasn't been traveled to yet,
 * dilineated by a '.'. If a spot is open, it the method calls itself again for that
 * spot. If it reaches a dead end, it returns false. If a path is found, it returns true*/
   public boolean findPath(Point currentPos, int stepNum)
   {
      //Drop a breadcrumb at the current spot
      breadcrumb(currentPos, stepNum);
      //The successful base case. Check to see if the current spot is the end of the maze.
      if (currentPos.isequal(this.finish))
      {
         return true;
      }
      //if it's not, check every point around it.
      else
      {
         //Check a direction
         Point temp = new Point(combine(currentPos, dir.get("S")));
         if (arr[temp.getX()][temp.getY()] == '.')
         {
            //Repeat the process for the next point
            if (findPath(temp, stepNum + 1))
            {
               //If there is a clear path to finish from that direction, it will pass
               //the true signal back through the recursive calls
               return true;
            }
         }
         //Identical steps for the other directions.
         temp = new Point(combine(currentPos, dir.get("E")));
         if (arr[temp.getX()][temp.getY()] == '.')
         {
            if (findPath(temp, stepNum + 1))
            {
               return true;
            }
         }

         temp = new Point(combine(currentPos, dir.get("N")));
         if (arr[temp.getX()][temp.getY()] == '.')
         {
            if (findPath(temp, stepNum + 1))
            {
               return true;
            }
         }

         temp = new Point(combine(currentPos, dir.get("W")));
         if (arr[temp.getX()][temp.getY()] == '.')
         {
            if (findPath(temp, stepNum + 1))
            {
               return true;
            }
         }
         //The failure base case. This will only execute in a case where there are no
         //clear paths from this point. It will pick up its breadcrumb and
         //return false, telling every prior recursive call that this path is a
         //dead end.
         backtrack(currentPos);
         return false;
      }
   }

/*This method is used to combine a point with the direction points mapped in
 * the dir Map in order to "step" in a specific direction*/
   public Point combine(Point a, Point b)
   {
      return new Point(a.getX() + b.getX(), a.getY() + b.getY());
   }

//This method drops a breadcrumb at the position p. The breadcrumb is denoted
//by a lowercase letter corresponding to number of steps taken from 'a'-'z',
//wrapping back around to 'a' when we go more than 26 steps.
   public void breadcrumb(Point p, int stepNum)
   {
      //A string containing every letter of the alphabet
      String alph = "abcdefghijklmnopqrstuvwxyz";

      //drop the breadcrumb corresponding to the number of steps taken
      arr[p.getX()][p.getY()] = alph.charAt(stepNum % 26);
   }

//this method picks a breadcrumb back up from point p
   public void backtrack(Point p)
   {
      arr[p.getX()][p.getY()] = '.';
   }

   public Point getStart()
   {
      return start;
   }

//the printall method is purely for debugging
   public void printall()
   {
      System.out.println("Maze dimensions: " + len + "x" + wid);
      System.out.println("Start coordinates: " + start.toString());
      System.out.println("Finish coordinates: " + finish.toString());
   }
}
