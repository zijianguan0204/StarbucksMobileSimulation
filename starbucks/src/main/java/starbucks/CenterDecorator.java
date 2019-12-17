package starbucks;
/**@extends from Decorator*/
public class CenterDecorator extends Decorator {
    private String addState;

    /**
     * about center Decorator funtions
     */
    public CenterDecorator(IScreen isc)
    {
        super(isc);
    }
    /**
     * Display "Echo Feedback" on Pins enterred so far
     * @return String for Display
     */
    public String display ()
    {
        addState = super.display();
        return addBehavior(addState);
    }
    /**
     * @param num as integer
     * @return String for padSpaces
     */
    private String padSpaces(int num) {
        StringBuilder space = new StringBuilder();
        for ( int i = 0; i<num; i++ )
            space.append(" ");
        return space.toString() ;
    }
    /**
     * Display addBehavior on Pins enterred so far
     * @param str as String
     * @return  addBehavior String for Display
     */
    public String addBehavior(String str)
    {
        StringBuilder sout = new StringBuilder();
        String [] arrOfStr = str.split("\n");
        for(int i = 0; i < arrOfStr.length; i++)
        {
            String out = arrOfStr[i];
            if ((out.length() % 2 != 0) && out.length() < 15 ) { // odd number
                int pad = (15 - out.length()) / 2 ;
                out = padSpaces( pad ) +out;
            }
            else if ((out.length() % 2 == 0) && out.length() < 15) // even number
            {
                int pad = (15 - out.length()) / 2 +1;
                out = padSpaces( pad ) +out ;
            }
            if( i != arrOfStr.length -1)
            {
                sout.append(out);
                sout.append("\n");
            }
            else
            {
                sout.append(out);
            }
        }
        return sout.toString();
    }
}
