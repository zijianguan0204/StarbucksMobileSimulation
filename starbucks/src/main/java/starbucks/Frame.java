/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;


/**
 * Frame Class -- Container for Screens
 */
public class Frame implements IFrame
{
    private IScreen current ;
    private IMenuInvoker menuA = new MenuOption() ; //set command for each menu, and invoke(execute) the command from AppController
    private IMenuInvoker menuB = new MenuOption() ;
    private IMenuInvoker menuC = new MenuOption() ;
    private IMenuInvoker menuD = new MenuOption() ;
    private IMenuInvoker menuE = new MenuOption() ;

    private IMenuInvoker menuA_pay= new MenuOption();
    private IMenuInvoker menuA_options= new MenuOption();
    private IMenuInvoker menuA_moreoptions= new MenuOption();
    private IMenuInvoker menuE_addcard= new MenuOption();

    private IOrientationStrategy portraitStrategy ;
    private IOrientationStrategy landscapeStrategy ;
    private IOrientationStrategy currentStrategy ;

    /**
     * Return Screen Name
     * @return Screen Name
     */
    public String screen()
    {
        StringBuilder screen_name = new StringBuilder();
        if(currentStrategy == landscapeStrategy)
        {
            int len = current.name().length();
            screen_name.append(padSpaces((32-len)/2));
        }
        else if(currentStrategy == portraitStrategy)
        {
            int len = current.name().length();
            screen_name.append(padSpaces((15-len)/2 + 1));
        }
        screen_name.append(current.name());
        return screen_name.toString();
    }

    /** Switch to Landscape Strategy */
    public void landscape()
    {
        currentStrategy = landscapeStrategy ;
    }

    /** Switch to Portrait Strategy */
    public void portrait()  { currentStrategy = portraitStrategy ; }

    /** Nav to Previous Screen */
    public void previousScreen() {
        // add code here
        current.prev();
    }

    /** Nav to Next Screen */
    public void nextScreen() {
        // add code here
        current.next();
    }
    /**
     * Helper Debug Dump to STDERR
     * @return current IScreen
     */
    public IScreen getCurrent()
    {
        return current;
    }

    /**
     * Helper Debug Dump to STDERR
     * @param str Lines to print
     */
    private void dumpLines(String str) {
        String[] lines = str.split("\r\n|\r|\n");
        for ( int i = 0; i<lines.length; i++ ) {
            System.err.println( i + ": " + lines[i] ) ;
        }
    }

    /**
     * Helper:  Count lines in a String
     * @param  str Lines to Count
     * @return     Number of Lines Counted
     */
    private int countLines(String str){

        /*
          // this approach doesn't work in cases: "\n\n"
          String lines = str ;
          String[] split = lines.split("\r\n|\r|\n") ;
          return split.length ;
        */

        if (str == null || str.isEmpty()) {
            return 0;
        }

        int lines = 0;
        int pos = 0;
        while ((pos = str.indexOf("\n", pos) + 1) != 0) {
            lines++;
        }

        return lines ;
    }

    /**
     * Helper:  Pad lines for a Screen
     * @param  num Lines to Padd
     * @return     Padded Lines
     */
    private String padLines(int num) {
        StringBuilder lines = new StringBuilder();
        for ( int i = 0; i<num; i++ ) {
            lines.append("\n") ;
        }
        return lines.toString() ;
    }

    /**
     * Helper:  Pad Spaces for a Line
     * @param  num Num Spaces to Pad
     * @return     Padded Line
     */
    private String padSpaces(int num) {
        StringBuilder spaces = new StringBuilder();
        for ( int i = 0; i<num; i++ )
            spaces.append(" ");
        return spaces.toString() ;
    }

    /** Constructor */
    public Frame(IScreen initial)
    {
        current = initial ;

        portraitStrategy = new IOrientationStrategy()
        {
            /**
             * Display Screen Contents
             * @param s Reference to Screen
             */
            public void display(IScreen s)
            {
                System.out.println( contents(s) ) ;
            }


            /**
             * Return String / Lines for Frame and Screen
             * @param  s [description]
             * @return   [description]
             */
            public String contents(IScreen s)
            {
                String out = "" ;
                out += "===============\n" ;
                int nameLen = s.name().length() ;
                if ((nameLen % 2 != 0) && nameLen < 15 ) { // odd number
                    int pad = (15 - nameLen) / 2 ;
                    out += padSpaces( pad ) ;
                }
                else if ((nameLen % 2 == 0) && nameLen < 15) // even number
                {
                    int pad = (15 - nameLen) / 2 +1;
                    out += padSpaces( pad ) ;
                }
                out += s.name() + "\n" ;
                out += "===============\n" ;
                String screen = s.display() + "\n" ;
                int cnt1 = countLines( screen ) ;
                int pad1 = (10 - cnt1) / 2;
                //System.err.println( "cnt1: " + cnt1 ) ;
                //System.err.println( "pad1: " + pad1 ) ;
                out += padLines( pad1 ) ;
                out += screen  ;
                //dumpLines( out ) ;
                int cnt2 = countLines( out ) ;
                int pad2 = 13 - cnt2 ;
                //System.err.println( "cnt2: " + cnt2 ) ;
                //System.err.println( "pad2: " + pad2 ) ;
                //dumpLines( out ) ;
                String padlines = padLines( pad2 ) ;
                out += padlines ;
                out +=  "===============\n" ;
                out +=  "[A][B][C][D][E]\n" ;
                dumpLines( out ) ;
                return out ;
            }

            /** Select Command A
             * @return  menuA.invoke()  return the menu command
             * */
            public void selectA() { menuA.invoke() ; }

            /** Select Command B
             * @return  menuB.invoke() return the menu command
             * */
            public void selectB() { menuB.invoke() ; }

            /** Select Command C
             * @return  menuC.invoke() return the menu command
             * */
            public void selectC() { menuC.invoke() ; }

            /** Select Command D
             * @return  menuD.invoke() return the menu command
             * */
            public void selectD() { menuD.invoke() ; }

            /** Select Command E
             * @return  menuE.invoke() return the menu command
             * */
            public void selectE() { menuE.invoke() ; }
            //under mycard-main
            /**
             * Return menu option
             * @return menuA_pay.invoke() return the menu command
             */
            public void selectA_pay() { menuA_pay.invoke() ; }
            /**
             * Return menu option
             * @return menuA_pay.invoke() return the menu command
             */
            public void selectA_options() { menuA_options.invoke() ; }
            /**
             * Return menu option
             * @return menuA_moreoptions.invoke() return the menu command
             */
            public void selectA_moreoptions() { menuA_moreoptions.invoke() ; }
            //under setting
            /**
             * Return menu option
             * @return menuE_addcard.invoke() return the menu command
             */
            public void selectE_addcard() { menuE_addcard.invoke() ; }
        } ;

        landscapeStrategy = new IOrientationStrategy()
        {
            /**
             * Display Screen Contents
             * @param s Reference to Screen
             */
            public void display(IScreen s)
            {
                System.out.println( contents(s) ) ;
            }

            /**
             * Display Contents of Frame + Screen
             * @param  s Screen to Display
             * @return   Contents for Screen
             */
            public String contents(IScreen s)
            {
                String out = "" ;
                out += "================================\n" ;
                //out += "  " + s.name() + "  \n" ;
                int nameLen = s.name().length() ;
                int pad = (32 - nameLen) / 2;
                out += padSpaces( pad ) ;
                out += s.name() + "\n" ;
                out += "================================\n" ;
                //out += "\n\n" + s.display() + "\n\n\n"  ;// changed to match the requirement
                String screen = centerDisplay(s.display()) + "\n" ;
                int cnt1 = countLines( screen ) ;
                int pad1 = (6 - cnt1) / 2;
                //System.err.println( "cnt1: " + cnt1 ) ;
                //System.err.println( "pad1: " + pad1 ) ;
                out += padLines( pad1 ) ;
                out += screen  ;
                //dumpLines( out ) ;
                int cnt2 = countLines( out ) ;
                int pad2 = 9 - cnt2 ;
                //System.err.println( "cnt2: " + cnt2 ) ;
                //System.err.println( "pad2: " + pad2 ) ;
                //dumpLines( out ) ;
                String padlines = padLines( pad2 ) ;
                out += padlines ;
                out += "================================\n" ;
                dumpLines( out ) ;
                return out ;
            }



            /**
             * Select Command A
             */
            public void selectA() {  }

            /**
             * Select Command B
             */
            public void selectB() {  }

            /**
             * Select Command C
             */
            public void selectC() {  }

            /**
             * Select Command D
             */
            public void selectD() {  }

            /**
             * Select Command E
             */
            public void selectE() {  }
            /**
             * Select Command A_pay
             */
            public void selectA_pay() {  }
            /**
             * Select Command A_option
             */
            public void selectA_options() {  }
            /**
             * Select Command A_moreoptions
             */
            public void selectA_moreoptions() {  }
            /**
             * Select Command E_addcard
             */
            public void selectE_addcard() { }

        } ;

        /* set default layout strategy */
        currentStrategy = portraitStrategy ;
    }
    /**
     * Display Contents of Frame + Screen
     * @param  str input string
     * @return   sout.toString() screen display
     */
    public String centerDisplay(String str)
    {
        StringBuilder sout = new StringBuilder();
        String [] arrOfStr = str.split("\n");
        for(int i = 0; i < arrOfStr.length; i++)
        {
            String out = arrOfStr[i].trim();
            if ((out.length() % 2 != 0) && out.length() < 32 ) { // odd number
                int pad = (32 - out.length()) / 2 + 1 ;
                out = padSpaces( pad ) +out;
            }
            else if ((out.length() % 2 == 0) && out.length() < 32) // even number
            {
                int pad = (32 - out.length()) / 2;
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
     * Change the Current Screen
     * @param s Screen Object
     */
    public void setCurrentScreen( IScreen s )
    {
        current = s ;
    }

    /**
     * Configure Selections for Command Pattern 
     * @param slot A, B, ... E
     * @param c    Command Object
     */
    public void setMenuItem( String slot, IMenuCommand c )
    {
        if ( "A".equals(slot) ) { menuA.setCommand(c) ; }
        if ( "B".equals(slot) ) { menuB.setCommand(c) ; }
        if ( "C".equals(slot) ) { menuC.setCommand(c) ; }
        if ( "D".equals(slot) ) { menuD.setCommand(c) ; }
        if ( "E".equals(slot) ) { menuE.setCommand(c) ; }
        if ( "A_pay".equals(slot) ) { menuA_pay.setCommand(c) ; }
        if ( "A_options".equals(slot) ) { menuA_options.setCommand(c) ; }
        if ( "A_moreoptions".equals(slot) ) { menuA_moreoptions.setCommand(c) ; }
        if ( "E_addcard".equals(slot) ) { menuE_addcard.setCommand(c) ; }
    }

    /**
     * Send Touch Event
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y)
    {
        if ( current != null )
            current.touch(x,y) ;

    }

    /**
     * Get Contents of the Frame + Screen 
     * @return Frame + Screen Contents
     */
    public String contents()
    {
        if ( current != null )
        {
            return currentStrategy.contents( current ) ;
        }
        else
        {
            return "" ;
        }
    }

    /** Display Contents of Frame + Screen */
    public void display()
    {
        if ( current != null )
        {
            currentStrategy.display( current ) ;
        }
    }

    /**
     *  Execute a Command 
     * @param c Command
     */
    public void cmd( String c )
    {
        if ( "A".equals(c) ) { selectA() ; }
        if ( "B".equals(c) ) { selectB() ; }
        if ( "C".equals(c) ) { selectC() ; }
        if ( "D".equals(c) ) { selectD() ; }
        if ( "E".equals(c) ) { selectE() ; }
        if ( "A_pay".equals(c)) {selectA_pay() ; }
        if ( "A_options".equals(c)) {selectA_options() ; }
        if ( "A_moreoptions".equals(c)) {selectA_moreoptions() ; }
        if ( "E_addcard".equals(c)) {selectE_addcard() ; }
    }

    /** Select Command A */
    public void selectA() { currentStrategy.selectA() ;  }

    /** Select Command B */
    public void selectB() { currentStrategy.selectB() ;  }

    /** Select Command C */
    public void selectC() { currentStrategy.selectC() ;  }

    /** Select Command D */
    public void selectD() { currentStrategy.selectD() ;  }

    /** Select Command E */
    public void selectE() { currentStrategy.selectE() ;  }

    /**
     * Select Command A_pay
     * @return currentStrategy.selectA_pay()
     */
    public void selectA_pay() { currentStrategy.selectA_pay() ;  }
    /**
     * Select Command A_option
     * @return currentStrategy.selectA_options()
     */
    public void selectA_options() { currentStrategy.selectA_options() ;  }
    /**
     * Select Command A_pay
     * @return currentStrategy.selectA_pay()
     */
    public void selectA_moreoptions() { currentStrategy.selectA_moreoptions() ;  }
    /**
     * Select E_addcard
     * @return currentStrategy.selectE_addcard()
     */
    public void selectE_addcard() { currentStrategy.selectE_addcard() ;  }

}
