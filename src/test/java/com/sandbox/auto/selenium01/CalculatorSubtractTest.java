package com.sandbox.auto.selenium01;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorSubtractTest extends CalculatorBaseTest {

    @Test(dataProviderClass = CalculatorDataProvider.class, dataProvider = "Calculator Data")
    public void subtractTest(long a, long b) {
        assertThat(calculator.sub(a, b))
                .as("Calculator subtraction: %d - %d is not equal to expected", a, b)
                .isEqualTo(a - b);
    }
}
