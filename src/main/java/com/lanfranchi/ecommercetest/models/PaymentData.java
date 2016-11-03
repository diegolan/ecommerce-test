package com.lanfranchi.ecommercetest.models;

import java.math.BigDecimal;

public class PaymentData {

	private BigDecimal value;

	public PaymentData() {
	}

	public PaymentData(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

}
