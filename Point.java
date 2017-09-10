/*The Point class is used to store the x and y coordinates of a spot in the
 * maze.*/
public class Point {
   private int x, y;
   public Point()
   {
      x = 1;
      y = 1;
   }

   public Point(int inX, int inY)
   {
      x = inX;
      y = inY;
   }

   public Point(Point copy)
   {
      x = copy.getX();
      y = copy.getY();
   }

   public int getX()
   {
      return x;
   }

   public void setX(int newX)
   {
      x = newX;
   }

   public int getY()
   {
      return y;
   }

   public void setY(int newY)
   {
      y = newY;
   }

   public boolean isequal(Point p)
   {
      return x == p.getX() && y == p.getY();
   }

   public String toString()
   {
      return "(" + x + "," + y + ")";
   }
}
