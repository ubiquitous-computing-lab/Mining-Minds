package org.uclab.scl.framework.recbuilder;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uclab.scl.datamodel.FiredRule;

public class PatternMatcherTest {
  private static Map<String, List<Map<String, Object>>> rules;
  private static PatternMatcher patternMatcher;

  @BeforeClass
  public static void setup() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      rules = mapper.readValue(TestHelper.loadStringRules(), new TypeReference<Map<String, List<Map<String, Object>>>>() {});
      patternMatcher = new PatternMatcher();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @AfterClass
  public static void afterClass() {
    // . TODO afterClass stuff
  }

  @Test
  public void rulesLoaded() {
    int expected = 19;
    int actual = (rules.get("rules")).size();
    assertEquals(expected, actual);
  }
  public void fireRuleTest1() {
    String jsonFact = "{\"Current Activity\":\"LyingDown\",\"Activity Duration\":\"1h\",\"Activity Environment\":\"BedRest\",\"Activity Status\":\"Non-continuous\"}";
    Map<String, Object> fact = TestHelper.buildFact(jsonFact);
    int expected = 2;
    try{
      List<FiredRule> firedRules = patternMatcher.fireRule(fact, rules.get("rules"));
      assertEquals(expected, firedRules.size());
    }catch(UnsupportedTypeException e){e.printStackTrace();}
  }

  @Test
  public void fireRuleTest2() {
    String jsonFact = "{\"Current Activity\":\"LyingDown\",\"Activity Duration\":\"1h\"}";
    Map<String, Object> fact = TestHelper.buildFact(jsonFact);
    
    String expected = "19";
    
    try{
      List<FiredRule> firedRules = patternMatcher.fireRule(fact, rules.get("rules"));
      assertEquals(expected, firedRules.get(0).getRuleId());
    }catch(UnsupportedTypeException e){} 
  }
  
  @Test
  public void fireRuleTest3() {
    String jsonFact = "{\"Current Activity\":\"LyingDown\",\"Activity Duration\":\"1h\"}";
    Map<String, Object> fact = TestHelper.buildFact(jsonFact);
    
    int expected = 0;
    fact.put("Current Activity", "Nothing");
    try{
      List<FiredRule> firedRules = patternMatcher.fireRule(fact, rules.get("rules"));
      assertEquals(expected, firedRules.size());
    }catch(UnsupportedTypeException e){}
  }

}
