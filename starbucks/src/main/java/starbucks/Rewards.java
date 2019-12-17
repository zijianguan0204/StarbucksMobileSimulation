/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Rewards Screen */
public class Rewards extends Screen
{

    public Rewards()
    {

    }
    /**
     * Change the display of the screen
     * @return  str.toString() the display string
     */
    public String display()
    {
        StringBuilder str = new StringBuilder();
        str.append("Make Every");
        str.append("\nVisit Count");
        return str.toString();
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
