package some.service;

import java.math.BigDecimal;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Something {
	private Long id;
	private String name;
	private BigDecimal value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
