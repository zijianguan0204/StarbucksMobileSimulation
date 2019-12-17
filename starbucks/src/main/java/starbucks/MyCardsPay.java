/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card Pay Screen */
public class MyCardsPay extends Screen
{
    Device d = Device.getInstance() ; //added value
    IApp app = (IApp) d ; // addedvalue
    private Cards card = Cards.getInstance();



    public MyCardsPay() {

    };
    /**
     * Send Touch Events to the Chain
     * @param x Touch X Coord.
     * @param y Touch Y Coord.
     */
    public void touch(int x, int y)
    {
        if(x == 3 && y ==3 )
        {
            app.execute("A");
        }

        if((x == 2 || x == 3) && y ==2 )
        {
            if(d.landscapeFlag == false)
            {
                pay();
            }
        }

    }
    /**
     * Get display
     * @return str.toString()
     */
    public String display()
    {
        StringBuilder str = new StringBuilder();
        str.append(card.get_cardID());
        str.append("\n");
        str.append("\n");
        str.append("\n");
        str.append("Scan Now");
        return str.toString();
    }

    /**
     * Pay to decrease the balance by 1.5 when balance is enough
     */
    public void pay()
    {
        String balance = card.get_balance().substring(1, card.get_balance().length()-1);
        double temp = Double.parseDouble(balance);
        if(temp >= 1.5)
            temp -=1.5;
        card.set_balance("$" + Double.toString(temp) +"0");
    }
    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name ()
    {
        return "My Cards";
    }


}

