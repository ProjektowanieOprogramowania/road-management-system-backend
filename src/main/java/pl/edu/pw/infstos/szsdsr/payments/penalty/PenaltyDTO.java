package pl.edu.pw.infstos.szsdsr.payments.penalty;

import pl.edu.pw.infstos.szsdsr.payments.passing.Passing;

import javax.validation.constraints.NotNull;

public class PenaltyDTO {

	private Long id;

	@NotNull
	private Long userId;

	@NotNull
	private Passing passing;

	private String description;

	@NotNull
	private boolean paid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Passing getPassing() {
		return passing;
	}

	public void setPassing(Passing passing) {
		this.passing = passing;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
}
