package fr.lebronnecf.invesment.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.client.RestTemplate;

@Entity
public class InvestmentApplication
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private int sumInvested;
	private int termInMonths;
	private BigDecimal payback;
	private Boolean approved;
	
	
	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public BigDecimal getInterestRate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject("http://invest-secure.fr/getInterestRate", BigDecimal.class);
	}



	public BigDecimal getPayback() {
		return payback;
	}

	public void setPayback(BigDecimal payback) {
		this.payback = payback;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSumInvested() {
		return sumInvested;
	}

	public void setSumInvested(int sumInvested) {
		this.sumInvested = sumInvested;
	}

	public int getTermInMonths() {
		return termInMonths;
	}

	public void setTermInMonths(int termInMonths) {
		this.termInMonths = termInMonths;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
