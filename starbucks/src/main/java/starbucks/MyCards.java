/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

import java.util.ArrayList;

/** My Cards Screen */
public class MyCards extends Screen
{
    Device d = Device.getInstance() ; //added value
    IApp app = (IApp) d ; // addedvalue
    private static Cards card = Cards.getInstance();
    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name()
    {
        return "My Cards";
    };

    public MyCards()
    {
        addSubComponent(card);
    }
    /**
     * Get display
     * @return str.toString()
     */
    public String display()
    {
        StringBuilder str = new StringBuilder();
        str.append(card.get_balance());
        return str.toString();
    }
    /**
     * Send Touch Events to the Chain
     * @param x Touch X Coord.
     * @param y Touch Y Coord.
     */
    public void touch(int x, int y)
    {
        System.err.println( "Mycards Touched at (" + x + ", " + y + ")" );
        if(x == 3 && y == 3)
        {
            app.execute("A_pay");
        }
        else if(x == 2 && y == 4)
            app.execute("A_options");
    }





}
