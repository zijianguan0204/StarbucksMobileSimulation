/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Passcode Screen Component */
public class InvalidPin implements ITouchEventHandler, IDisplayComponent, IKeyPadObserver
{

    ITouchEventHandler nextHandler ;
    private int count = 1;

    /**
     * Touch Event Ignored by InvalidPin
     * @param x Touch X
     * @param y Touch Y
     */
    public void touch(int x, int y) {
        if ( nextHandler != null )
            nextHandler.touch(x, y) ;
    }

    /**
     * Set Next Touch Handler
     * @param next Touch Event Handler
     */
    public void setNext( ITouchEventHandler next)
    {
        nextHandler = next ;
    }


    /**
     * Display "Echo Feedback" on Pins enterred so far
     * @return Passcode String for Display
     */
    public String display()
    {
        String value = "";
        if(count % 4 == 0)
            value = "Invalid Pin\n" ;
        else
            value = "\n";
        return value;
    }

    /**
     * Add Sub Component (Not used)
     * @param c Sub Component to Add
     */
    public void addSubComponent( IDisplayComponent c )
    {

    }

    /**
     * Key Event Update
     * @param c   Count of Keys So Far
     * @param key Last key Pressed
     */
    public void keyEventUpdate( int c, String key )  //only change the number of digits
    {
        System.err.println( "Key: " + key ) ; //
        if(c >= 1)
            count = c  ;
    }
}
