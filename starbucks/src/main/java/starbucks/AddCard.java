/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/**
 * Add New Card Screen
 */
public class AddCard extends Screen
{
    Device d = Device.getInstance() ; //
    IApp app = (IApp) d ;
    private KeyPad kp = new KeyPad();
    private static CardID cardID = CardID.getInstance();
    private static CardCode cardCode = CardCode.getInstance();
    private static Cards card = Cards.getInstance();
    private static CardFocus cardfocus = CardFocus.getInstance();

    /**
     * Add New Card using component pattern
     */
    public AddCard()
    {
        resetCard();
        addSubComponent( cardID ) ;
        addSubComponent( cardCode ) ;
        addSubComponent( kp ) ;

        ((IKeyPadSubject)kp).attach( cardID ) ;
        ((IKeyPadSubject)kp).attach( cardCode ) ;

    }
    /**
     * Get Display Contents
     * @return name
     */
    public String name()
    {
        return "Add Card";
    }
    /**
     * go to prev section setting
     */
    public void prev()
    {
        System.err.println( "touched prev" );
        resetCard();
        app.execute("E");
    }
    /**
     * go to next section mycard
     */
    public void next()
    {
        System.err.println( "touched next in addcard" );
        if(cardID.getCardID().length() == 11 && cardCode.getCardCode().length() ==5)
        {
            card.set_cardID(cardID.getCardID());
            card.set_cardCode(cardCode.getCardCode());
            card.set_balance("$20.00");
            resetCard();
            app.execute("A");
        }
        else
            resetCard();
    }
    /**
     * clear the info once exit
     */
    public void resetCard()
    {
        cardID.set_cardID("");
        cardID.set_countID(0);
        cardCode.set_CardCode("");
        cardCode.set_countCode(0);
        cardfocus.setIDFlag(true);
        cardfocus.setCodeFlag(false);
    }


}
