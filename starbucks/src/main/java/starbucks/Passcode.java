/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Passcode Screen Component */
public class Passcode implements ITouchEventHandler, IDisplayComponent, IKeyPadObserver
{
    ITouchEventHandler nextHandler ;
    private int count = 0;
    /**
     * resetPasscoe() for reset the variable of keypad
     */
    public void resetPasscode()
    {
        count = 0;
    }
    /**
     * Touch Event Ignored by Passcode
     * @param x Touch X
     * @param y Touch Y
     */
    public void touch(int x, int y)
    {
        if ( y==2 )
        {
            System.err.println( "Passcode Touched at (" + x + ", " + y + ")" ) ;  // adding * to passcode section
        }
        else
        {
            if ( nextHandler != null )
                nextHandler.touch(x,y) ;
        }
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
        String value = "" ;
        switch ( count % 4 )
        {
            case 0: value = "[_][_][_][_]" ; break ;
            case 1: value = "[*][_][_][_]" ; break ;
            case 2: value = "[*][*][_][_]" ; break ;
            case 3: value = "[*][*][*][_]" ; break ;
            case 4: value = "[_][_][_][_]" ; break ;
            default: value = "";
        }
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
        if(c < 0)
            c = 0;
        if(c >= 0 && c <= 4)
            count = c ;
        else
            count = 0;
    }
}
