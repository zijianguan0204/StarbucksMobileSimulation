/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Passcode Screen Component */
public class CardID implements ITouchEventHandler, IDisplayComponent, IKeyPadObserver
{
    ITouchEventHandler nextHandler ;
    private static CardFocus cardfocus = CardFocus.getInstance();
    private int countID = 0;
    private String cardID = "";
    private final static CardID cardid =new CardID();
    /**get card instance item */
    public static CardID getInstance()
    {
        return cardid;
    }
    /**get card instance item */
    public void set_countID(int num)
    {
        countID = num;
    }
    /**get card instance item */
    public void set_cardID(String str)
    {
        cardID = str;
    }
    /**
     * Get Display Contents
     * @return  getCardID
     */
    public String getCardID()
    {
        return "[" + cardID + "]";
    }
    /**get card instance item */
    public int getCountID()
    {
        return countID ;
    }
    /**
     * Touch Event Ignored by Passcode
     * @param x Touch X
     * @param y Touch Y
     */
    public void touch(int x, int y)
    {
        if ( x == 2 && y==3 )
        {
            System.err.println( "focus on card code" ) ;
            cardfocus.setIDFlag(false);
            cardfocus.setCodeFlag(true);
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
        return "[" + cardID + "]" ;
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
        System.err.println( "Key: " + key ) ; //
        if(key.equals("X") && cardfocus.getIDFlag() == true)
        {
            if(countID > 0)
            {
                countID--;
                cardID = cardID.substring(0, cardID.length() -1);
            }
        }
        else if(!key.equals(" "))
        {
            if(cardfocus.getIDFlag() == true && countID < 9)
            {
                countID ++ ;
                cardID += key;
            }
        }

        if(c < 0)
            c = 0;
    }
}
