package Main;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import ANTLR.Java8Lexer;
import ANTLR.Java8Parser;
import Listeners.MainListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import testGenerate.Tester;

import static cheatingDetection.SimilarityDetection.detectSimilarity;

public class Main {

    static ArrayList<String> filesPath = new ArrayList<>();
    static Listeners.MainListener MainListener;

    public static void main(String[] args) throws Exception {

        String path1 = takeInput();
        String path2 = takeInput();

        // find all java files in this file
        findFiles(path1);

        //main listener to find all classes , interfaces , enums , annotations
        MainListener = new MainListener();
        for (String file : filesPath) {
            isJavas(file);
        }
        //we will use Javas meaning classes , interfaces , annotations , enums
        // , or any java file

        ArrayList<Javas> project1 = MainListener.javas;
        //here we have all javas Objects with names and paths

        // use more specific listener for each file
        // (class -> class listener - > method listener , interface -> interface listener , etc)

        for (Javas j : project1) {
            System.out.println(j.path);
            System.out.println(j.name);
            try {
                j.analyze();
            } catch (Exception e) {
                System.out.println("problem happened here");
                System.out.println(e.getMessage());
            }
            System.out.println("----------");
        }
        ///-------------------------------------------

        filesPath.clear();
        Listeners.MainListener.javas.clear();

        findFiles(path2);
        for (String file : filesPath) {
            isJavas(file);
        }

        ArrayList<Javas> project2 = MainListener.javas;
        for (Javas j : project2) {
            System.out.println(j.path);
            System.out.println(j.name);
            try {
                j.analyze();
            } catch (Exception e) {
                System.out.println("problem happened here");
                System.out.println(e.getMessage());
            }
            System.out.println("----------");
        }

        // here, we should have all data we need from our input
        // have all classes( names , variables , methods )
        // interfaces (methods)
        //TODO: support enums , annotations


        Collections.sort(project1);
        Collections.sort(project2);

        for(int i = 0 ; i < project1.size() ; i++){
            if(project1.get(i).name.equals(project2.get(i).name) && project1.get(i).getClass().equals(project2.get(i).getClass())
            && project1.get(i).getClass().equals(Class.class)) {
                chooseMethodsToBeCompared( (Class)project1.get(i), (Class)project2.get(i));
            }
        }

    }

    private static String takeInput() {
//        enter the path of the src
//        System.out.println("Enter src path : ");
//        Scanner sc= new Scanner(System.in);
//        String p = sc.next();

        String p = "C:/Users/Asus/Desktop/myProjects/Connect4/src";
        return p;
    }

    private static void findFiles(String path) {
        File directory = new File(path);
        String contents[] = directory.list();
        for (String inside : contents) {
            String insidePath = path + "/" + inside;
            File insidePathF = new File(insidePath);
            if (insidePathF.isDirectory()) {
                findFiles(insidePath);
            } else if (insidePath.contains(".java")) {
                filesPath.add(insidePath);
            }
        }

    }

    private static void isJavas(String file) {
        Path fileP = Paths.get(file);
        Java8Lexer lexer = null;
        try {
            lexer = new Java8Lexer(CharStreams.fromPath(fileP));
        } catch (IOException e) {
            System.out.println("Error in " + fileP);
        }
        Java8Parser parser = new Java8Parser(new CommonTokenStream(lexer));
        parser.addParseListener(MainListener);
        parser.compilationUnit();
        // add path to the javas
        MainListener.javas.get(MainListener.javas.size() - 1).path = file;
    }

//    public static void detectCheating(Class c1, Class c2) throws Exception {
//        ArrayList<Method> methodsOfC1 = c1.methods;
//        ArrayList<Method> methodsOfC2 = c2.methods;
//        Method m1 = null;
//        Method m2 = null;
//        if( methodsOfC1.size() > methodsOfC2.size() || methodsOfC1 == methodsOfC2){
//            for(int i = 0 ; i < methodsOfC2.size() ; i++){
//                m2 = methodsOfC2.get(i);
//                if ( methodsOfC1.contains(m2) ) {
//                    int j = 0;
//                    while(true){
//                        if (methodsOfC1.get(j).equals(m2)) {
//                            m1 = methodsOfC1.get(j);
//                            break;
//                        }
//                        j++;
//                    }
//                    detectSimilarity(m1, m2);
//                }
//            }
//        }
//        else {
//            for(int i = 0 ; i < methodsOfC1.size() ; i++){
//                m1 = methodsOfC1.get(i);
//                if ( methodsOfC2.contains(m1) ) {
//                    int j = 0;
//                    while(true){
//                        if (methodsOfC2.get(j).equals(m1)) {
//                            m2 = methodsOfC1.get(j);
//                            break;
//                        }
//                        j++;
//                    }
//                    detectSimilarity(m1, m2);
//                }
//            }
//        }
//    }


    public static void chooseMethodsToBeCompared( Class c1, Class c2 ) throws Exception {
        ArrayList<Method> methodsOfC1 = c1.methods;
        ArrayList<Method> methodsOfC2 = c2.methods;
        if( methodsOfC1.size() > methodsOfC2.size() ){
            for(int i = 0 ; i < methodsOfC2.size() ; i++){
                Method m2 = methodsOfC2.get(i);
                for(int j = 0 ; j < methodsOfC1.size() ; j++){
                    Method m1 = methodsOfC1.get(j);
                    if(m2.name.equals(m1.name)){
                        detectSimilarity(m1,m2);
                    }
                }
            }
        }
        else {
            for(int i = 0 ; i < methodsOfC1.size() ; i++){
                Method m1 = methodsOfC1.get(i);
                for(int j = 0 ; j < methodsOfC2.size() ; j++) {
                    Method m2 = methodsOfC2.get(j);
                    if(m1.name.equals(m2.name)){
                        detectSimilarity(m1,m2);
                    }
                }
            }
        }
    }


}