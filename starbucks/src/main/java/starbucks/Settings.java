/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** Settings Screen */
public class Settings extends Screen
{
    Device d = Device.getInstance() ; //added value
    IApp app = (IApp) d ; // addedvalue
    private static CardFocus cardfocus = CardFocus.getInstance();
    private static CardID cardid = CardID.getInstance();
    private static CardCode card_code = CardCode.getInstance();

    public Settings()
    {

    }
    /**
     * Send Touch Events to the Chain
     * @param x Touch X Coord.
     * @param y Touch Y Coord.
     */
    public void touch(int x, int y)
    {
        if(x >= 1 && x <= 3 && y == 1)
        {
            resetCard();
            app.execute("E_addcard");
        }

    }
    /**
     * Get display
     * @return str.toString()
     */
    public String display()
    {
        StringBuilder str = new StringBuilder();
        str.append("Add Card\n");
        str.append("Delete Card\n");
        str.append("Billing\n");
        str.append("Passcode\n");
        str.append("\n");
        str.append("About|Terms\n");
        str.append("Help");

        return str.toString();
    }
    /**
     * reset display
     * public void resetCard()
     */
    public void resetCard()
    {
        cardid.set_cardID("");
        cardid.set_countID(0);
        card_code.set_CardCode("");
        card_code.set_countCode(0);
        cardfocus.setIDFlag(true);
        cardfocus.setCodeFlag(false);
    }


}
