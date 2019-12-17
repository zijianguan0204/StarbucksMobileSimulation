package starbucks;

/**
 * Decorator implements IScreen
 */
public class Decorator implements  IScreen{
    private IScreen screen;
    public Decorator(IScreen  isc)
    {
        screen = isc;
    }

    /**
     * Add Display Component to Screen
     * @return screen.display()
     */
    public String display()
    {
        return screen.display();
    }

    /**
     * Add Display Component to Screen
     * @param x Touch X Coord.
     * @param y Touch Y Coord.
     * @return screen.touch(x, y)
     */
    public void touch(int x, int y)
    {
        screen.touch(x, y);
    }
    /**
     * Add Display Component to Screen
     * @return screen.name()
     */
    public String name()
    {
        return screen.name();
    }
    /**
     * Add Display Component to Screen
     * @return screen.next()
     */
    public void next()
    {
        screen.next();
    }
    /**
     * Add Display Component to Screen
     * @return screen.prev()
     */
    public void prev()
    {
        screen.prev();
    }
    /**
     * Add Display Component to Screen
     * @param s IScreen
     * @param n String
     * @return screen.setNext(s, n)
     */
    public void setNext(IScreen s, String n )
    {
        screen.setNext(s, n);
    }
    /**
     * Add Display Component to Screen
     * @param s IScreen
     * @param n String
     * @return screen.setPrev(s, n)
     */
    public void setPrev(IScreen s, String n )
    {
        screen.setPrev(s, n);
    }

}
