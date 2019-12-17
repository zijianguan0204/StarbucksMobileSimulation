/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** CardCode Component */
public class CardCode implements ITouchEventHandler, IDisplayComponent, IKeyPadObserver
{
    ITouchEventHandler nextHandler ;
    private static CardFocus cardfocus = CardFocus.getInstance();
    private int countCode = 0;
    private String cardcode = "";
    private final static CardCode card_code =new CardCode();
    /** get cardcode item */
    public static CardCode getInstance()
    {
        return card_code;
    }
    /** set cardcode code */
    public void set_CardCode(String str)
    {
        cardcode = str;
    }
    /** set cardcode code count */
    public void set_countCode(int num)
    {
        countCode = num;
    }
    /**
     * Get Display Contents
     * @return getCardCode()
     */
    public String getCardCode()
    {
        return "[" + cardcode + "]";
    }
    /** get cardcode code */
    public int getCountCode()
    {
        return countCode ;
    }
    /**
     * Touch Event Ignored by Passcode
     * @param x Touch X
     * @param y Touch Y
     */
    public void touch(int x, int y)
    {
        if ( x >= 1 && x <= 3 && y==2)
        {
            System.err.println( "focus on card ID" ) ;
            cardfocus.setIDFlag(true);
            cardfocus.setCodeFlag(false);
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
        return "[" + cardcode + "]\n" ;

    }
    /**
     * Add Sub Component (Not used)
     * @param c Sub Component to Add
     */
    public void addSubComponent( IDisplayComponent c ) {}
    /**
     * Key Event Update
     * @param c   Count of Keys So Far
     * @param key Last key Pressed
     */
    public void keyEventUpdate( int c, String key )  //only change the number of digits
    {
        System.err.println("Key: " + key); //
        if(key.equals("X") && cardfocus.getCodeFlag() == true)
        {
            if( countCode > 0)
            {
                countCode--;
                cardcode = cardcode.substring(0, cardcode.length() -1);
            }
        }
        else if(!key.equals(" "))
        {
            if (cardfocus.getCodeFlag() == true && countCode < 3) {
                cardcode += key;
                countCode++;
            }
        }

        if (c < 0)
            c = 0;
    }
}
