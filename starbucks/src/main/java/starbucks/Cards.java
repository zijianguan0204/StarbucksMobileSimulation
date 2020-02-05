package starbucks ;
/**
 * Base Class for cards.
 *
 * Provides Common Functionality
 * For Setting Up the Composite and
 * Chain of Responsibility Patterns.
 *
 */
public class Cards implements IDisplayComponent, ITouchEventHandler {

    static String _balance ;
    static String _cardID ;
    static String _cardCode ;
    private final static Cards cards =new Cards();
    ITouchEventHandler nextHandler ;


    /**
     * @return static Cards getInstance
     */
    public static Cards getInstance()
    {
        _balance = "$0.00";
        _cardID = "[000000000]";
        _cardCode = "";

        return cards;
    }
    /**
     * empty constructor
     *
     */
    public Cards() {}
    /**
     * card display its balance
     *
     */
    public String display()
    {
        return _balance;
    }
    /**
     * card class sets its balance
     *
     */
    public void set_balance(String balance)
    {
        _balance = balance;
    }
    /**
     * card class sets cardID
     *
     */
    public void set_cardID(String cardID)
    {
        _cardID = cardID;
    }
    /**
     * card class sets cardcode
     *
     */
    public void set_cardCode(String cardCode)
    {
        _cardCode = cardCode;
    }
    /**
     * card class return its balance
     *
     */
    public String get_balance()
    {
        return _balance;
    }
    /**
     * card class return its cardID
     *
     */
    public String get_cardID()
    {
        return _cardID;
    }
    /**
     * card class return its cardcode
     *
     */
    public String get_cardCode()
    {
        return _cardCode;
    }
    /**
     * Add Display Component to Screen
     * @param c IDisplayComponent
     */
    public void addSubComponent( IDisplayComponent c )
    {
    }
    /**
     * @param next ITouchEventHandler
     * Next Screen - Not Used
     */
    public void setNext(ITouchEventHandler next)
    {
        if(nextHandler != null)
            nextHandler = next;
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



