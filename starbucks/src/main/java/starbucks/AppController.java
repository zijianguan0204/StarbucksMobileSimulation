/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/**
 * Main App Controller Class
 */
public class AppController implements IApp {

    private IScreen mycards ; // mycards-main
    private IScreen store ;
    private IScreen rewards ;
    private IScreen payments ;
    private IScreen settings;
    //under mycards main
    private IScreen mycardspay; // touch(3,3) on mycards-main
    private IScreen mycardsoptions;
    private IScreen mycardsmoreoptions;
    //under settings
    private IScreen addcard;

    private IMenuCommand displayMyCards ;
    private IMenuCommand displayPayments ;
    private IMenuCommand displayRewards ;
    private IMenuCommand doStore ;
    private IMenuCommand doSettings;
    private IMenuCommand doMyCardsPay;
    private IMenuCommand doMyCardsOptions;
    private IMenuCommand doMyCardsMoreOptions;
    private IMenuCommand doAddCard;

    private IFrame frame ;

    public AppController() {

        mycards = new CenterDecorator(new MyCards()) ;
        store = new LeftDecorator (new Store());
        rewards = new LeftDecorator (new Rewards());
        payments = new LeftDecorator(new Payments());

        settings = new CenterDecorator (new Settings()) ;
        frame = new Frame( mycards ) ;

        //screen under mycard-main
        mycardspay = new CenterDecorator(new MyCardsPay()) ;

        mycardsoptions = new LeftDecorator (new MyCardsOptions());;

        mycardsmoreoptions = new MyCardsMoreOptions();
        //screen under setting
        addcard = new CenterDecorator (new AddCard());

        // setup command pattern
        displayMyCards  = new MenuCommand() ;
        displayPayments = new MenuCommand() ;
        displayRewards  = new MenuCommand() ;
        doStore         = new MenuCommand() ;
        doSettings      = new MenuCommand() ;
        //menu under mycard-main
        doMyCardsPay    = new MenuCommand() ;
        doMyCardsOptions= new MenuCommand() ;
        doMyCardsMoreOptions = new MenuCommand();
        //menu under setting
        doAddCard = new MenuCommand();

        displayMyCards.setReceiver( // set receiver for each command
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( mycards ) ; // call this function when the command is here
              }//do action is the methods when the command is execute
        }
        ) ;
        displayPayments.setReceiver(
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( payments ) ;
              }
        }
        ) ;
        displayRewards.setReceiver(
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( rewards ) ;
              }
        }
        ) ;
        doStore.setReceiver(
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( store ) ;
              }
        }
        ) ;
        doSettings.setReceiver(
            new IMenuReceiver() {
                /** Command Action */
                public void doAction() {
                    frame.setCurrentScreen( settings ) ;
                }
        }
        ) ;
        //action set receiver under my card-main
        doMyCardsPay.setReceiver(
                new IMenuReceiver() {
                    /** Command Action */
                    public void doAction() {
                        frame.setCurrentScreen( mycardspay ) ;
                    }
                }
        ) ;
        doMyCardsOptions.setReceiver(
                new IMenuReceiver() {
                    /** Command Action */
                    public void doAction() {
                        frame.setCurrentScreen( mycardsoptions ) ;
                    }
                }
        ) ;
        doMyCardsMoreOptions.setReceiver(
                new IMenuReceiver() {
                    /** Command Action */
                    public void doAction() {
                        frame.setCurrentScreen( mycardsmoreoptions ) ;
                    }
                }
        ) ;
        doAddCard.setReceiver( // set receiver for each command
                new IMenuReceiver() {
                    /** Command Action */
                    public void doAction() {
                        frame.setCurrentScreen( addcard ) ; // call this function when the command is here
                    }
                }
        ) ;



        frame.setMenuItem ( "A", displayMyCards ) ; // insert the command into the hash map and ABCD as the key
        frame.setMenuItem ( "B", displayPayments ) ;
        frame.setMenuItem ( "C", displayRewards ) ;
        frame.setMenuItem ( "D", doStore ) ;
        frame.setMenuItem ( "E", doSettings ) ;
        //under mycards-main
        frame.setMenuItem ( "A_pay", doMyCardsPay) ;
        frame.setMenuItem ( "A_options", doMyCardsOptions);
        frame.setMenuItem ( "A_moreoptions", doMyCardsMoreOptions);
        //under setting
        frame.setMenuItem ( "E_addcard", doAddCard);
    }


    /**
      * Switch to Landscape Mode
      */
    public void landscape() {
        if(frame.getCurrent() == mycards || frame.getCurrent() == mycardspay)
            frame.landscape() ;
    }

    /**
     * Switch to Portait Mode
     */
    public void portrait() {
        frame.portrait() ;
    }

    /**
     * Send In Touch Events
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y) {
        frame.touch(x, y) ;
    }

    /**
     * Display Current Screen
     */
    public void display() {
        frame.display() ;
    }

    /**
     * Execute Menu Bar Command
     * @param c Menu Bar Option (A, B, C, D or E)
     */
    public void execute( String c ) {
        frame.cmd( c ) ;
    }

    /**
     * Navigate to Previous Screen
     */
    public void prev() {
        frame.previousScreen() ;
    }

    /**
     * Navigate to Next Screen
     */
    public void next() {
        frame.nextScreen() ;
    }

    /**
     * Get Current Screen Name
     * @return Screen Name
     */
    public String screen() {
        return frame.screen() ;
    }

    /**
     * Get Current Screen Contents
     * @return Current Screen Contents
     */
    public String screenContents() {
        return frame.contents() ;
    }



}
