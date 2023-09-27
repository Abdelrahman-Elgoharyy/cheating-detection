package cheatingDetection;

import Main.Class;
import Main.Method;

import static Main.Main.chooseMethodsToBeCompared;

public class Engine {

    public static void main (String[]args) throws Exception {
        Class c1 = new Class("c1");
        Class c2 = new Class("c2");
        Method m1 = new Method("numbers");
        Method m2 = new Method("numbers");
        m1.setBody("\n" +
                "        if(num = 0)\n" +
                "            sysetm.out.print(\"number equal zero\");\n" +
                "        else\n" +
                "            if(num>0)\n" +
                "                sysetm.out.print(\"number is positive\");\n" +
                "            else\n" +
                "                sysetm.out.print(\"number is not negative\");\n" +
                "    ");

        m2.setBody("\n" +
                "        if(num = 0)\n" +
                "            sysetm.out.print(\"number equal zero\");\n" +
                "        else\n" +
                "            if(num>0)\n" +
                "                sysetm.out.print(\"number is not positive\");\n" +
                "            else\n" +
                "                sysetm.out.print(\"number is negative\");\n" +
                "    ");
        c1.methods.add(m1);
        c2.methods.add(m2);
        chooseMethodsToBeCompared(c1,c2);


    }
}
