package Main;

import java.util.ArrayList;
import java.util.HashMap;

public class Method implements Comparable {
    public String name;
    public String className;
    public String returnType;
    public ArrayList<String> parameters = new ArrayList<>();
    public String signature;
    public String modifier;
    public int dependency;
    public boolean empty;
    public int size;

    public HashMap<String, Variable> variables = new HashMap<>();
    public ArrayList<String> Conditions = new ArrayList<>();
    public ArrayList<String> Loops = new ArrayList<>();
    public ArrayList<String> methodsInvoked = new ArrayList<>();
    public String body;

    public Method() {
        this.body = "{}";
    }

    public Method(String name) {
        this.name = name;
        this.body = "{}";
    }

    public void setDependency(int dependency) {
        this.dependency = dependency;
    }

    public void setBody(String body){ this.body = body; }

    @Override
    public int compareTo(Object o) {
        Method temp = (Method) o;
        if (this.name.equals(temp.name) )
            return this.dependency - ((Method) o).dependency;
        return this.name.compareTo(temp.name);
    }


    @Override
    public String toString() {
        return signature;
    }

    //TODO: compare method
}
