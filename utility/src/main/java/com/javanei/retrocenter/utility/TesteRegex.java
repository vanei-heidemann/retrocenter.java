package com.javanei.retrocenter.utility;

public class TesteRegex {
    public static void main(String[] args) {
        String[] patthers = {
                //"ver \\w\\w\\-\\w",
                //"ver [\\w]+",
                //"ver [\\w-]+",
                //"ver [\\w\\-]+",
                //"ver [\\w\\-\\.]+"
                //"\\d\\.\\d\\d"
                //"\\d\\.\\d\\d\\ \\w*"
                //"version [\\w\\?\\.-]*"
                //"ver. [\\w\\?\\.\\-:]*",
                "\\d [Pp]layer[\\w\\s]*",
                "\\d [Pp]layers[\\w\\s]*"
        };

        for (String p : patthers) {
            //System.out.println(p + " -> "+ "ver JA-B".matches(p));
            //System.out.println(p + " --> "+ "ver D 1.01".matches(p));
            //System.out.println(p + " ---> "+ "ver D1.01".matches(p));
            //System.out.println(p + " ----> "+ "ver D1-01".matches(p));
            System.out.println(p + " -> " + "2 player".matches(p));
            System.out.println(p + " -> " + "2 player teste".matches(p));
            System.out.println(p + " -> " + "2 players".matches(p));
            System.out.println(p + " -> " + "2 players teste".matches(p));
            //System.out.println(p + " -> " + "ith 2xZ80".matches(p));
            //System.out.println(p + " -> " + "1a06".matches(p));
            System.out.println("------------------------");
        }

        //System.out.println("Player's Edge Plus (IP0028) Joker Poker - French".matches(".*(IP\\d\\d\\d\\d).*"));
    }
}
