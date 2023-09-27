package Main;

import java.util.ArrayList;

public class Javas implements Comparable{

    String path;
    String name;
    ArrayList<String> modifier;
    String signature;

    void analyze(){

    }
    void generateTest(String outputPath){

    }

    void detectCheat(String outputPath){

    }

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo( ((Javas)o).name);
    }

}
