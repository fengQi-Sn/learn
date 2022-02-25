import mockLearn.AService;
import mockLearn.BService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.anyString;

@Transactional
@Rollback
@SpringBootTest
@RunWith(SpringRunner.class)
public class MockLearnMySqlTest {
    @Mock
    private AService aService;

    @Test
    public void test_error() {
        BService bService = new BService();
        bService.init(aService);
        //对于void返回函数的mock -异常
        Mockito.doThrow(new RuntimeException("")).when(aService).get1();
        //对于void返回函数的mock -正常
        Mockito.doNothing().when(aService).get1();

        //有返回-异常
        Mockito.when(aService.get2(anyString())).thenThrow(new RuntimeException(""));
        //有返回-正常
        Mockito.when(aService.get2(anyString())).thenReturn("");


        bService.testMethod();
        //校验多个
        Assert.assertArrayEquals(new Object[]{"", "参数不正确"}, new Object[]{"", ""});
        //校验单个
        Assert.assertEquals("", "");
    }
    


}
