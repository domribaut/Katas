import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestHello{
  
    @Test
	public void shouldPassWhenTestingInfrastructure(){
	    assertTrue("Test checking infrastructure", true);
	}
	
	@Test
	public void shouldReturnHello(){
	    assertTrue("must say something", new Hello().sayHello() != null);
	}
	
	
}