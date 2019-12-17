package starbucks;
/**CardFocus*/
public class CardFocus {
    private boolean ID_flag;
    private boolean Code_flag;
    private final static CardFocus cardFocus = new CardFocus();

    /**
     * Cardfocus to determine which part is selected
     */
    public CardFocus()
    {
        ID_flag = true;
        Code_flag = false;
    }
    /**
     * return ID_flag
     */
    public boolean getIDFlag()
    {
        return ID_flag;
    }
    /**
     * return code_flag
     */
    public boolean getCodeFlag()
    {
        return Code_flag;
    }
    /**
     * set ID_flag
     */
    public void setIDFlag(boolean flag)
    {
        ID_flag = flag;
    }

    /**
     * set code_flag
     */
    public void setCodeFlag(boolean flag)
    {
        Code_flag = flag;
    }

    /**
     * instance return
     */
    public static CardFocus getInstance()
    {
        return cardFocus;
    }
}
