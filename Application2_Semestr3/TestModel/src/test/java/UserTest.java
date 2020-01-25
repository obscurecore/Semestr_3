import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {
    private User user;

    //Before - what happened every time you start
    @Before
    public void beforeOrAnyHandleName() {
        user = new User(29);
        System.out.println(user.getAge());
    }

    //what happend when every test is completed
    @After
    public void afterOrAnyHandleName() {
        user = new User(0);
        System.out.println(user.getAge());
    }

    @Test
    public void whenGrowThenAgeIncrement() {
        final int result = user.grow();
        assertThat(result, is(30));
    }

    @Test
    public void whenGrow2ThenAgeAdd2() {
        final int result = user.grow2();
        assertThat(result, is(31));
    }
}