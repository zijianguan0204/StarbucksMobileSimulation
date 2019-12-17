/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card More Options Screen */
public class MyCardsMoreOptions extends Screen
{

    public MyCardsMoreOptions()
    {
    }

    /**
     * Get display
     * @return str.toString()
     */
    public String display()
    {
        StringBuilder str = new StringBuilder();
        str.append("Refresh\n");
        str.append("Reload\n");
        str.append("Auto Reload\n");
        str.append("Transactions");
        return str.toString();
    }
    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name()
    {
        return "My Cards";
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
