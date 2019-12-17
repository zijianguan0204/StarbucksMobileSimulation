package starbucks;
/**
 * LeftDecorator extends Decorator
 */
public class LeftDecorator extends Decorator {
    private String addState;
    public LeftDecorator(IScreen isc)
    {
        super(isc);
    }

    /**
     * get the display
     * @return  addBehavior(addState)
     */
    public String display ()
    {
        addState = super.display();
        return addBehavior(addState);
    }

    public String addBehavior(String str)
    {
        return str;
    }
}
