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

public class ConflictResolverTest {
  
  private static Map<String, List<Map<String, Object>>> rules;
  private static PatternMatcher patternMatcher;
  private static ConflictResolver conflictResolver;

  @BeforeClass
  public static void setup() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      rules = mapper.readValue(TestHelper.loadStringRules(), new TypeReference<Map<String, List<Map<String, Object>>>>() {});
      patternMatcher = new PatternMatcher();
      conflictResolver = new ConflictResolver();

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

  @Test
  public void conflictResolverTest1() {
    String jsonFact = "{\"Current Activity\":\"LyingDown\",\"Activity Duration\":\"1h\",\"Activity Environment\":\"BedRest\",\"Activity Status\":\"Non-continuous\"}";
    Map<String, Object> fact = TestHelper.buildFact(jsonFact);
    List<FiredRule> firedRules = null;
    
    try {
      firedRules = patternMatcher.fireRule(fact, rules.get("rules"));
    } catch (UnsupportedTypeException e) {}
    assertEquals(2, firedRules.size());
    
    int expected = 1;
    List<FiredRule> resolvedRules = conflictResolver.resolveConflict(firedRules);
    assertEquals(expected, resolvedRules.size());
  }
  
  @Test
  public void conflictResolverTest2() {
    String jsonFact = "{\"Current Activity\":\"LyingDown\",\"Activity Duration\":\"1h\",\"Activity Environment\":\"BedRest\",\"Activity Status\":\"Non-continuous\"}";
    Map<String, Object> fact = TestHelper.buildFact(jsonFact);
    List<FiredRule> firedRules = null;
    
    try {
      firedRules = patternMatcher.fireRule(fact, rules.get("rules"));
    } catch (UnsupportedTypeException e) {}
    
    String expected = "18";
    List<FiredRule> resolvedRules = conflictResolver.resolveConflict(firedRules);
    assertEquals(expected, resolvedRules.get(0).getRuleId());
  }
  
}
