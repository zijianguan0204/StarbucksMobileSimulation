/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/**
 * Authentication Proxy for App Controller
 * Device implements IApp, IPinAuthObserver$ORIENTATION_MODE
 */
public class Device implements IApp, IPinAuthObserver {

    private static Device theDevice = null;
    private boolean fourPin = true ;
    private boolean sixPin = false ;
    private String pin = "" ;

    private IApp app ;
    private KeyPad kp ;
    private Passcode pc ;
    private PasscodeForSixPin pc6 ;
    private PinScreen ps ;
    //    private InvalidPin ip ;
//    private InvalidPinSix ip6 ;
    private Spacer sp ;
    private boolean authenticated = false ;
    private PinEntryMachine pm ;

    public static final int screen_frame_header = 3 ;
    public static final int portrait_screen_width = 15 ;
    public static final int portrait_screen_length = 10 ;
    public static final int landscape_screen_width = 32 ;
    public static final int landscape_screen_length = 6 ;

    public boolean landscapeFlag = false;
    /**
     * Authentication Proxy for App Controller
     * Device implements IApp, IPinAuthObserver$ORIENTATION_MODE
     */
    public enum ORIENTATION_MODE {
        PORTRAIT, LANDSCAPE
    }


    private ORIENTATION_MODE device_orientation_state ;
    /**
     * Get Current mode
     * @return device_orientation_state
     */
    public ORIENTATION_MODE getDeviceOrientation() {
        return this.device_orientation_state ;
    }

    public void setPortraitOrientation() {
        this.device_orientation_state = ORIENTATION_MODE.PORTRAIT ;
    }

    public void setLandscapeOrientation() {
        this.device_orientation_state = ORIENTATION_MODE.LANDSCAPE ;
    }

    public ORIENTATION_MODE getOrientation()
    {
        return device_orientation_state;
    }

    private Device() { }

    /** Debug Device State */
    public static void debug()
    {
        Device d = Device.getInstance() ;
        System.err.println( "============================================" ) ;
        System.err.println( "--------- D E V I C E  S T A T E  ----------" ) ;
        System.err.println( "============================================" ) ;
        System.err.println( "Pin Option    = " + d.getPinOption() ) ;
        System.err.println( "Stored Pin    = " + d.getPin() ) ;
        System.err.println( "Authenticated = " + d.isAuthenticated() ) ;
        System.err.println( "Orientation   = " + d.getDeviceOrientation() ) ;
        System.err.println( "============================================" ) ;
    }



    /**
     * Get Current Auth State
     * @return Auth T/F
     */
    public String isAuthenticated() {
        return Boolean.toString( authenticated ) ;
    }

    /**
     * Return the current Pin Option:
     *  0 = User Chosed No Pin
     *  4 = User Chosed 4-digit Pin
     *  6 = User Chosed 6-digit Pin
     * @return Pin Option
     */
    public int getPinOption() {
        if ( fourPin )
            return 4 ;
        else if ( sixPin )
            return 6 ;
        else
            return 0 ;
    }

    /**
     * Get Current Pin
     * @return Pin
     */
    public String getPin() {
        return pin ;
    }


    /**
     * Set Pin
     * @param p New Pin
     */
    private void setPin( String p ) {
        pin = p ;
        int len = p.length() ;
        switch ( len ) {
            case 0:
                fourPin = false ;
                sixPin = false ;
                break;
            case 4:
                fourPin = true ;
                sixPin = false ;
                break ;
            case 6:
                fourPin = false ;
                sixPin = true ;
                break ;
            default:
                fourPin = false ;
                sixPin = false ;
                break;
        }
    }

    /**
     * Device Reset Pin
     */
//    private void clearPin()
//    {
//        this.pin = "" ;
//    }

    /**
     * Get Singleton Instance
     * @return Reference to Current Device Config (Create if none exists)
     */
    public synchronized static Device getInstance() {
        if (theDevice == null) {
            return getNewInstance( "000000" ) ;
        }
        else
            return theDevice;
    }

    /**
     * Get New Instance
     * @return Reference to Device (Create New Singleton)
     */
    public synchronized static Device getNewInstance() {
        return getNewInstance( "1234" ) ;
    }
    /**
     * Get New Instance
     * @param pin String
     * @return theDevice Reference to Device (Create New Singleton)
     */
    public synchronized static Device getNewInstance( String pin ) {
        theDevice = new Device() ;
        theDevice.setPin( pin ) ;
        theDevice.startUp() ;
        debug() ;
        return theDevice ;
    }

    /**
     * Device Starup Process.
     * Starts Up with Default 4-Pin Option
     */
    public void startUp()
    {


        kp = new KeyPad() ;
        if(fourPin == true)
        {
            pc = new Passcode() ;
        }
        else if(sixPin == true)
        {
            pc6 = new PasscodeForSixPin() ;
        }
        sp = new Spacer() ;
        ps = new PinScreen() ;
        pm = new PinEntryMachine() ;

        // setup the composite pattern

        if(fourPin == true)
        {
            ps.addSubComponent( pc ) ;
        }
        else if(sixPin == true)
        {
            ps.addSubComponent( pc6 ) ;
        }
        ps.addSubComponent( sp ) ;
        ps.addSubComponent( kp ) ;


        // setup the observer pattern

        if(fourPin == true)
        {
            ((IKeyPadSubject)kp).attach( pc ) ;
        }
        else if(sixPin == true)
        {
            ((IKeyPadSubject)kp).attach( pc6 ) ;
        }

        ((IKeyPadSubject)kp).attach( pm ) ;
        ((IPinAuthSubject)pm).registerObserver(this) ;

        // get app controller reference
        app = new AppController() ;

        // startup in portrait
        this.device_orientation_state = ORIENTATION_MODE.PORTRAIT ;
    }

    /**
     * Switch to Landscape View
     */
    public void landscape() {
        if ( authenticated )
        {
            app.landscape() ;
            landscapeFlag = true;
        }
    }

    /**
     * Switch to Portait View
     */
    public void portrait() {
        if ( authenticated )
        {
            app.portrait() ;
            landscapeFlag = false;
        }
    }

    /**
     * User Touch at X,Y Coordinates
     * @param x X Coordinate
     * @param y Y Coordinate
     */
    public void touch(int x, int y) {
        if ( authenticated )
            app.touch(x, y) ;
        else
            ps.touch(x, y) ;
    }

    /**
     * Display Screen Contents to Terminal
     */
    public void display() {
        System.out.println( screenContents() ) ;
    }

    /**
     * Get Class Name of Screen
     * @return Class Name of Current Screen
     */
    public String screen() {
        if ( authenticated )
            return app.screen() ;
        else
            return ps.name() ;
    }

    /**
     * @return out String
     */
    public String invalid()
    {
        if(fourPin == true && kp.getCountPinDigits() > 0 && kp.getCountPinDigits() % 4 == 0)
        {
            pc.resetPasscode();
            kp.resetKeyPad();
            return "  Invalid Pin\n\n";
        }

        else if(sixPin == true && kp.getCountPinDigits() > 0 && kp.getCountPinDigits() % 6 == 0)
        {
            pc6.resetPasscode6();
            kp.resetKeyPad();
            return "  Invalid Pin\n\n";
        }
        return "\n\n";
    }

    /**
     * Get Screen Contents as a String
     * @return Screen Contents of Current Screen
     */
    public String screenContents() {
        if ( authenticated ) {
            return app.screenContents() ;
        } else {
            String out = "" ;
            out = "---------------\n" ;
            out += "   " + ps.name() + "  \n" ;
            out += "---------------\n" + invalid();
            out += centerLayout(ps.display()) ;
            out += "\n\n\n\n---------------\n" ;
            return out ;
        }
    }




    /**
     * Select a Menu Command
     * @param c Menu Option (A, B, C, E, or E)
     */
    public void execute( String c ) {
        if ( authenticated )
            app.execute( c ) ;
    }

    /**
     * Navigate to Previous Screen
     */
    public void prev() {
        if ( authenticated )
            app.prev() ;
    }

    /**
     * Navigate to Next Screen
     */
    public void next() {
        if ( authenticated )
            app.next() ;
    }

    /**
     * Receive Authenticated Event from Authenticator
     * @param this.authenticated = true
     */
    public void authEvent() {
        this.authenticated = true ;
    }
    /**
     * Receive Authenticated Event from Authenticator
     * @param str input String
     * @return sout.toString()
     */
    public String centerLayout(String str)
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
    /**
     * pad screen
     * @param num int
     * @return space.toString()
     */
    private String padSpaces(int num) {
        StringBuilder space = new StringBuilder();
        for ( int i = 0; i<num; i++ )
            space.append(" ");
        return space.toString() ;
    }



}
