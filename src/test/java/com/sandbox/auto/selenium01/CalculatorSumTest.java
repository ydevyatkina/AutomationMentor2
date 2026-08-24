package com.sandbox.auto.selenium01;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorSumTest extends CalculatorBaseTest {

    @Test(dataProviderClass = CalculatorDataProvider.class, dataProvider = "Calculator Data")
    public void sumTest(long a, long b) {
        assertThat(calculator.sum(a, b))
                .as("Calculator sum: %d + %d is not equal to expected", a, b)
                .isEqualTo(a + b);
    }
}
