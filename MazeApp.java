/*
 * Program Name: Lab 1: Maze Solver
 * Student Name: Duncan Scott Martinson
 * Semester: Fall 2017
 * Class: Data Structures
 * Professor: Dr. Scherger
 * Due: September 7, 2017
 *
 * Program Overview: This program reads in data from standard input about a maze,
 * solves the maze using a recursive algorithm, and prints out the solved maze.
 *
 * Input: Standard input, prefferably redirected to a formatted data file
 * containing the number of mazes to solve on execution, the dimensions of each
 * maze, and the start and finish coordinates
 *
 * Output: prints out the solved mazes to standard output.
 *
 * Significant Variables:
 * -in: a Scanner used to read from standard input
 * -mazeCount: The number of mazes to solve
 * -maze: a Maze object that contains the current maze being solved
 *
 * Program Limitations:
 * -The path it finds is not always the most efficient
 * -If the user doesn't have the formatted data files or know how the maze data
 * is formatted, then the input may cause the program not to function correctly.
 */

import java.io.*;
import java.util.*;
public class MazeApp {
   public static void main(String[] args)
   {
//Make a Scanner, read in and store how many mazes there are
      Scanner in        = new Scanner(System.in);
      int     mazeCount = in.nextInt();
//Create a Maze object
      Maze maze = new Maze();

//The main loop. This loops as many times as there are mazes.
      while (mazeCount != 0)
      {
//Read the data from the maze into it.
         maze.read(in);
//If the maze has a valid path, print out the solved maze
         if (maze.findPath(maze.getStart(), 0))
         {
            maze.print();
         }
//If there is no clear path, notify the user.
         else
         {
            System.out.println("NO PATH EXISTS");
         }
//Decrement and repeat.
         mazeCount--;
      }
   }
}
