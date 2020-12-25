package micronaut.tut;


import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;

import javax.annotation.Nullable;
import javax.validation.constraints.PositiveOrZero;

@Introspected
public class IssueTicketBean {

	@PathVariable
	@PositiveOrZero
	int number;

	@Nullable
	@QueryValue
	@PositiveOrZero
	Double minPrice;

	@Nullable
	@QueryValue
	@PositiveOrZero
	Double maxPrice;

	public IssueTicketBean(int number, Double minPrice, Double maxPrice) {
		this.number = number;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public int getNumber() {
		return number;
	}

	@Nullable
	public Double getMaxPrice() {
		return maxPrice;
	}

	@Nullable
	public Double getMinPrice() {
		return minPrice;
	}

}
