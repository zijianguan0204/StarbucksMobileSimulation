/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card Options Screen */
public class MyCardsOptions extends Screen
{
    Device d = Device.getInstance() ; //added value
    IApp app = (IApp) d ; // addedvalue
    private Cards card = Cards.getInstance();

    public MyCardsOptions()
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
        str.append("Reload");
        str.append("\nRefresh");
        str.append("\nMore Options");
        str.append("\nCancel");
        return str.toString();
    }
    /**
     * Send Touch Events to the Chain
     * @param x Touch X Coord.
     * @param y Touch Y Coord.
     */
    public void touch(int x, int y)
    {
        if(x >= 1 && x <= 3 && y == 7)
            app.execute("A_moreoptions");
    }
    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name()
    {
        return "My Cards";
    }


}
