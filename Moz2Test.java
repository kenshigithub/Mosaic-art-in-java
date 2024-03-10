import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Moz2Test {
  
  @Test
  public void test() {
    var myJPanel = new MyJPanel();
    assertEquals(4, myJPanel.mozsize);
  }
}
