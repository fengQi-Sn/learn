import example.example.creation.singleton.Demo;
import example.example.creation.singleton.IdGeneratorEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SingletonTest {
    /**
     * Mockito cannot mock/spy because :
     *  - final class
     * 将mockito-core 修改成mockito-inline可以
     */
    @Mock
    private IdGeneratorEnum idGeneratorEnum;
    @Test
    public void test_single() {
        Demo test = new Demo();
        Mockito.when(idGeneratorEnum.getId()).thenReturn(10L);
        Long result = test.method("123", idGeneratorEnum);

        Assert.assertEquals(10+"", result+"");
    }

}
