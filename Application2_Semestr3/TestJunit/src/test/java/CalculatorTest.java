import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

class CalculatorTest {
    @Test
    public void whenDivFirstInSecondsThenResultIs5() {
        final Calculator calculator = new Calculator();
        final int result = calculator.div(15, 5);
        assertThat(result, is(3));
    }
    @Test(expected = ArithmeticException.class)
    public void whenDivOnZeroThenExpectedArithmeticException(){
        final Calculator calculator = new Calculator();
        calculator.div(15,0);
    }
    @Test
    public void whenDivThenResultNotNull(){
        final Calculator calculator= new Calculator();
        final int result = calculator.div(15,5);
        assertNotNull(result);
    }
}