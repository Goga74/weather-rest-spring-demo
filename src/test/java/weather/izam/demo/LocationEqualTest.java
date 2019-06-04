package weather.izam.demo;

import junit.framework.TestCase;
import org.junit.Test;
import weather.izam.demo.model.Location;

public class LocationEqualTest extends TestCase {
    @Test
    public void testEqual() {
        assertEquals(true, new Location(1.1, -2.23).equals(
                new Location( 1.1, -2.23)));
    }
}
