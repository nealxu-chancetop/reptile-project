import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

/**
 * @author Neal
 */
public class CustomTest {
    @Test
    public void testMockito() {
        var mockedList = Mockito.mock(List.class);

        //Mock
        Mockito.when(mockedList.get(0)).thenReturn("first");
        Mockito.when(mockedList.get(1)).thenThrow(new RuntimeException());

        //check
        Assertions.assertEquals(mockedList.get(0), "first");
        Assertions.assertThrows(RuntimeException.class, () -> mockedList.get(1));
    }
}
