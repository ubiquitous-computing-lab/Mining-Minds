package org.uclab.scl.framework.recbuilder;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TypeComparatorTest {
  
  @BeforeClass
  public static void setup(){
  }
  
  @AfterClass
  public static void releaseResources(){
    //. release resources if any
  }
  
  @Test
  public void testCompareString1(){
    String v1 = "setting";
    String v2 = "setting";
    String op = "=";
    
    assertEquals(true, TypeComparator.compare(v1, v2, op));
  }
  
  @Test
  public void testCompareString2(){
    String v1 = "setting";
    String v2 = "standing";
    String op = "=";
    
    assertEquals(false, TypeComparator.compare(v1, v2, op));  
  }
  
  @Test
  public void testCompareString3(){
    String v1 = "setting";
    String v2 = "standing";
    String op = "!=";
    
    assertEquals(true, TypeComparator.compare(v1, v2, op)); 
  }
  
  @Test
  public void testCompareInt1(){
    int seconds1 = 45;
    int seconds2 = 45;
    String op = "=";
    
    assertEquals(true, TypeComparator.compare(seconds1, seconds2, op));
  }
  @Test
  public void testCompareInt2(){
    int seconds1 = 125;
    int seconds2 = 130;
    String op = "!=";
    
    assertEquals(true, TypeComparator.compare(seconds1, seconds2, op));
  }
  
  @Test
  public void testCompareInt3(){
    int seconds1 = 45;
    int seconds2 = 35;
    String op = ">";
    
    assertEquals(true, TypeComparator.compare(seconds1, seconds2, op));
  }
  
  @Test
  public void testCompareInt4(){
    int seconds1 = 125;
    int seconds2 = 130;
    String op = "<";
    
    assertEquals(true, TypeComparator.compare(seconds1, seconds2, op));
  }
  
  @Test
  public void testCompareBoolean1(){
    boolean v1 = true;
    boolean v2 = true;
    String op = "=";
    
    assertEquals(true, TypeComparator.compare(v1, v2, op));
  }
  
  @Test
  public void testCompareBoolean2(){
    boolean v1 = true;
    boolean v2 = false;
    String op = "=";
    
    assertEquals(false, TypeComparator.compare(v1, v2, op));
  }
  
  @Test
  public void testCompareBoolean3(){
    boolean v1 = true;
    boolean v2 = false;
    String op = "!=";
    
    assertEquals(true, TypeComparator.compare(v1, v2, op));
  }
}
