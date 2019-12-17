/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Pin Entry Machine - Context for State Pattern */
public class PinEntryMachine implements IPinStateMachine, IKeyPadObserver, IPinAuthSubject
{

    /**
     * Get name of current state for unit testing
     * @return Class Name of Current State
     */
    public String getCurrentState()
    {
        return state.getClass().getName() ;
    }

    // Pin Domain Object
    private Device d = Device.getInstance();
    private String pin = d.getPin();
    private IPinAuthObserver auth ; // single observer 

    // pin machine states
    private NoPinDigits pin0 ;
    private OnePinDigit pin1 ;
    private TwoPinDigits pin2 ;
    private ThreePinDigits pin3 ;
    private FourPinDigits pin4 ;
    private FivePinDigits pin5 ;
    private SixPinDigits pin6 ;

    // current state
    private IPinState state ;

    //flag for FourPinState to check is either 4pin mode or 6pin mode
    //private boolean SixPinFlag = false;

    // pin captured so far
    private String d1, d2, d3, d4, d5, d6 ;
    public String d1() { return d1 ; }
    public String d2() { return d2 ; }
    public String d3() { return d3 ; }
    public String d4() { return d4 ; }
    public String d5() { return d5 ; }
    public String d6() { return d6 ; }

//    public void set_flag(boolean flag)
//    {
//        SixPinFlag = flag;
//    }


    /**
     * Constructor - Set Up State Objects
     * and Set Initial State to "PIN 0"
     */
    public PinEntryMachine( )
    {
        pin = d.getPin();
        pin0 = new NoPinDigits( this ) ; //
        pin1 = new OnePinDigit( this ) ;
        pin2 = new TwoPinDigits( this ) ;
        pin3 = new ThreePinDigits( this ) ;
        pin4 = new FourPinDigits( this ) ;
        pin5 = new FivePinDigits( this );
        pin6 = new SixPinDigits( this );
        this.d1 = "" ;
        this.d2 = "" ;
        this.d3 = "" ;
        this.d4 = "" ;
        this.state = pin0 ; // the initial state was set to state 0, getting into NoPinDigits.java
    }

    /** Backspace Event */
    public void backspace() {
        this.state.backspace() ;
    }

    /**
     * Number Event
     * @param digit Digit Pressed
     */
    public void number( String digit ) {
        this.state.number( digit ) ; //get into different number functions depends on which state is in, initially in pin0
    }

    /** Valid Pin Event */
    public void validPin() {
        this.state.validPin() ;
    }

    /** Invalid Pin Event */
    public void invalidPin() {
        this.state.invalidPin() ;
    }

    /** Change the State to No Pin State */
    public void setStateNoPinDigits()
    {
        this.state = pin0 ;
        this.d1 = "" ;
        this.d2 = "" ;
        this.d3 = "" ;
        this.d4 = "" ;
        this.d5 = "" ;
        this.d6 = "" ;
        debug() ;
    }

    /**
     * Change the State to One Pin State
     * @param digit Digit/Number Enterred
     */
    public void setStateOnePinDigit( String digit )
    {
        this.state = pin1 ;
        if ( digit != null )
            this.d1 = digit ;
        else {
            this.d2 = "" ;
            this.d3 = "" ;
            this.d4 = "" ;
        }
        debug() ;
    }

    /**
     * Change the State to Two Pin State
     * @param digit Digit/Number Enterred
     */
    public void setStateTwoPinDigits( String digit )
    {
        this.state = pin2 ;
        if ( digit != null )
            this.d2 = digit ;
        else {
            this.d3 = "" ;
            this.d4 = "" ;
        }
        debug() ;
    }

    /**
     * Change the State to Three Pin State
     * @param digit Digit/Number Enterred
     */
    public void setStateThreePinDigits( String digit )
    {
        //this.pinCount = 3 ;
        this.state = pin3 ;
        if ( digit != null )
            this.d3 = digit ;
        else {
            this.d4 = "" ;
        }
        debug() ;
    }

    /**
     * Change the State to Four Pin State
     * @param digit Digit/Number Enterred
     */
    public void setStateFourPinDigits( String digit )
    {
        //this.pinCount = 4 ;
        this.state = pin4 ;
        if(pin.length() == 4)
        {
            if ( digit != null )
            {
                this.d4 = digit ;
                debug() ;
                System.err.println( "Authenticating..." ) ;
                if ( pin.equals( d1+d2+d3+d4 ) ) // if the pin equals to 1234, initialized at the beginning,
                {
                    System.err.println( "Successful Login!" ) ;
                    notifyObserver() ; // change the auth state to true in order to boardcast all subjects
                }
                else
                {
                    System.err.println( "Login Failed!" ) ;
                    setStateNoPinDigits() ; // if the password is false, return the state to pin0
                }
            }
        }
        else if(pin.length() == 6)
        {
            if ( digit != null )
                this.d4 = digit ;
            else {
                this.d5 = "" ;
            }
            debug() ;
        }

    }
    /**
     * Change the State to Five Pin State
     * @param digit Digit/Number Enterred
     */
    public void setStateFivePinDigits( String digit )
    {
        //this.pinCount = 5 ;
        this.state = pin5 ;
        if ( digit != null )
            this.d5 = digit ;
        else {
            this.d6 = "" ;
        }
        debug() ;
    }
    /**
     * Change the State to Six Pin State
     * @param digit Digit/Number Enterred
     */
    public void setStateSixPinDigits( String digit ) {
        //this.pinCount = 6;
        this.state = pin6;

        if (digit != null) {
            this.d6 = digit;
            debug();
            System.err.println("Authenticating...");
            if (pin.equals(d1 + d2 + d3 + d4 + d5 + d6)) // if the pin equals to 1234, initialized at the beginning,
            {
                System.err.println("Successful Login!");
                notifyObserver(); // change the auth state to true in order to boardcast all subjects
            } else {
                System.err.println("Login Failed!");
                setStateNoPinDigits(); // if the password is false, return the state to pin0
            }

        }
    }

    /**
     * Observer of Key Events
     * @param c   Num Keys So Far
     * @param key Last Key Enterred
     */
    public void keyEventUpdate( int c, String key ) //it keeps track on key, and there is another keyEventUpdate in Passcode, keep track on count,
    {
        System.err.println( "Key: " + key + " Count: " + c ) ;
        if (c == 5)
        {
            pin4.setFlag();
        }
        if ( key.equals(" ") )
            /* nothing */ ;
        else if ( key.equals("X"))
            backspace() ; // go to the backspace function in the current state, normally it changes the state to the previous one
        else
            number( key ) ;       // when there is a normal key coming (1-0), go to the number function
    }

    /**
     * Register Observers for Pin Authentication
     * @param obj Object Observing Pin Auth
     */
    public void registerObserver( IPinAuthObserver obj )
    {
        this.auth = obj ;
    }

    /**
     * Remove Pin Auth Observer
     * @param obj Object No Longer Observing Pin Auth
     */
    public void removeObserver( IPinAuthObserver obj )
    {
        this.auth = null ;
    }

    /**
     * Notify Pin Auth Observers
     */
    public void notifyObserver( )
    {
        if ( this.auth != null )
            this.auth.authEvent() ; // set the flag to true and boardcast
    }

    /** Debug Dump to Stderr State Machine Changes */
    private void debug()
    {
        System.err.println( "Current State: " + state.getClass().getName() ) ;
        System.err.println( "D1 = " + d1 ) ;
        System.err.println( "D2 = " + d2 ) ;
        System.err.println( "D3 = " + d3 ) ;
        System.err.println( "D4 = " + d4 ) ;
        System.err.println( "D5 = " + d5 ) ;
        System.err.println( "D6 = " + d6 ) ;
    }

}
