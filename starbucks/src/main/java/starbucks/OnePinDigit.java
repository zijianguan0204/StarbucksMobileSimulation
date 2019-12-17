/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** One Pig Digit State */
public class OnePinDigit implements IPinState
{
    IPinStateMachine machine ;

    /**
     * Constructor
     * @param  m Reference to State Machine
     */
    public OnePinDigit( IPinStateMachine m )
    {
        this.machine = m ;
    }

    /** Backspace Event */
    public void backspace() {
        machine.setStateNoPinDigits() ;
    } // delete the current key, and get back to the previous state : pin0

   /**
     * Number Event
     * @param digit Digit pressed
     */    
    public void number( String digit ) {
        machine.setStateTwoPinDigits( digit ) ;
    } // pass the number to the next state, change the current state to pin2

    /** Valid Pin Event */
    public void validPin() {

    }

    /** Invalid Pin Event */
    public void invalidPin() {

    }
}
