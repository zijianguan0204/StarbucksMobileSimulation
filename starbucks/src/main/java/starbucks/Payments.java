/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Payments Screen */
public class Payments extends Screen
{

    public Payments()
    {

    }
    /**
     * Get display
     * @return str.toString()
     */
    public String display()
    {
        StringBuilder str = new StringBuilder();
        str.append("Find Store");
        str.append("\nEnable Payments");
        return str.toString();
    }
    /**
     * Send Touch Events to the Chain
     * @param x Touch X Coord.
     * @param y Touch Y Coord.
     */
    public void touch (int x, int y)
    {
        System.err.println(x + " " + y);
    }

}
