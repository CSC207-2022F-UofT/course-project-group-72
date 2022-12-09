package report_tests.screens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import report_feature.screens.*;

import static org.junit.jupiter.api.Assertions.*;

class MultiMapTest {

    static MultiMap<String, String> multiMap;

    @BeforeEach
    void setUp() {
        multiMap = new MultiMap<>();
    }

    @Test
    void put() {
        multiMap.put("key", "value");
        assertTrue(multiMap.containsKey("key"));
        assertEquals(1, multiMap.size());

    }

    @Test
    void get() {
        multiMap.put("key", "value");
        assertEquals("value", multiMap.get("key").iterator().next());
    }

    @Test
    void keySet() {
        multiMap.put("key", "value");
        assertEquals("key", multiMap.keySet().iterator().next());
    }

    @Test
    void values() {
        multiMap.put("key", "value");
        assertEquals("value", multiMap.values().iterator().next().iterator().next());
    }

    @Test
    void containsKey() {
        multiMap.put("key", "value");
        assertTrue(multiMap.containsKey("key"));

    }

    @Test
    void size() {
        multiMap.put("key", "value");
        assertEquals(1, multiMap.size());
    }

    @Test
    void isEmpty() {
        assertTrue(multiMap.isEmpty());
    }
}