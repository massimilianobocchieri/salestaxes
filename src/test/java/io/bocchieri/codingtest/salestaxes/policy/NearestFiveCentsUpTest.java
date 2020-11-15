package io.bocchieri.codingtest.salestaxes.policy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NearestFiveCentsUpTest {
	private NearestFiveCentsUp nearestFiveCentsUp;

	@BeforeEach
	void setUp() {
		nearestFiveCentsUp = new NearestFiveCentsUp();
	}

	@ParameterizedTest
	@CsvSource({
			"0,0.00",
			"1,1.00",
			"0.001, 0.05",
			"0.005, 0.05",
			"0.12, 0.15",
			"0.15,0.15",
			"0.16,0.20",
			"0.24,0.25",
			"0.210,0.25",
			"0.495,0.50",
			"0.550, 0.55",
			"0.551, 0.60"})
	void testRounding(BigDecimal input, BigDecimal expectedOutput) {
		assertEquals(expectedOutput, nearestFiveCentsUp.round(input));
	}
}