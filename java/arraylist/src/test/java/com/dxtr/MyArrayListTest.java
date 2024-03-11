package com.dxtr;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * MyArrayListTest
 */
public class MyArrayListTest {


    private final int size = 10;
    private MyArrayList<Integer> intArr;

    @Before
    public void setUp(){
        intArr = new MyArrayList<Integer>(size);

        for (int i = 0; i < size; i++) {
            intArr.add(i);
        }

    }

    @Test
    public void testArrayGetCorrectValue(){

        MyArrayList<String> arr = new MyArrayList<>();
        arr.add("one");
        arr.add("two");
        arr.add("three");
        arr.add("four");
        
        assertEquals("four", arr.get(3));

    }

    @Test
    public void testArrayGetCorrectValueExceedCapacity(){

        final int size = 4;
        MyArrayList<String> arr = new MyArrayList<>(size);
        arr.add("one");
        arr.add("two");
        arr.add("three");
        arr.add("four");
        arr.add("five");
        arr.add("six");
        
        assertEquals("five", arr.get(4));

    }
    
    @Test
    public void testCapacityIncreaseAfterSizeExceedCap(){

        int size =  4;
        MyArrayList<Integer> arr = new MyArrayList<>(size);

        for (int i = 0; i < size; i++) {
            arr.add(i);
        }
        arr.add(2);

        int newCapacity = size  + size/ 2; 
        assertEquals(newCapacity, arr.capacity());
    }

    @Test
    public void testSizeMatch(){

        int size =  2;
        MyArrayList<Integer> arr = new MyArrayList<>(size);

        for (int i = 0; i < size; i++) {
            arr.add(i);
        }
        arr.add(2);
        arr.add(3);
        arr.add(4);

        assertEquals(size + 3 , arr.size());

    }

    @Test
    public void testSetPos(){

        int pos = 5;
        int newValue = 99;
        intArr.set(pos,newValue);
        assertEquals(newValue, (int) intArr.get(pos));

    }
}
