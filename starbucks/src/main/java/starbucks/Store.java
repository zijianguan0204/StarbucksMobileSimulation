/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Store Screen */
public class Store extends Screen
{

    public Store()
    {

    }
    /**
     * Get display
     * @return str.toString()
     */
    public String display()
    {
        StringBuilder str = new StringBuilder();
        str.append("         X\n");
        str.append("   X\n");
        str.append("       X\n");
        str.append("      X\n");
        str.append("  X\n");
        str.append("           X\n");
        str.append("  X\n");
        return str.toString();
    }
    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name()
    {
        return "Find Store";
    }
    /**
     * Send Touch Events to the Chain
     * @param x Touch X Coord.
     * @param y Touch Y Coord.
     */
    public void touch(int x, int y)
    {
        System.err.println(x + " " + y);
    }

}
