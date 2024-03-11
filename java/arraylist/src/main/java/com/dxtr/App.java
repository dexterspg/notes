package com.dxtr;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        int size =  10;
        List<Integer> myArr = new ArrayList<>(size);

        for (int i = 0; i < size ; i++) {
            myArr.add(i);
        }
        // myArr.add(2);
        // myArr.add(3);
        // myArr.add(4);

        int out =myArr.set(4,999);
        System.out.println(out);
        boolean b = myArr.contains(999);
        System.out.println(b);

        myArr.stream().forEach(System.out::println);
        System.out.println("Size"+myArr.size());
        System.out.println(myArr.getClass().getSimpleName());
        


    }
}
