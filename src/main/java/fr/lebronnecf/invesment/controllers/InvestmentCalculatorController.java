package fr.lebronnecf.invesment.controllers;

import java.math.BigDecimal;
import java.net.URI;

import fr.lebronnecf.invesment.repositories.InvestmentRepository;
import fr.lebronnecf.invesment.models.InvestmentApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InvestmentCalculatorController {

	@Autowired
	private InvestmentRepository data;

	private RestTemplate restTemplate = new RestTemplate();
	
	// Render the form
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView renderNewInvestmentForm()
	{
		InvestmentApplication investment = new InvestmentApplication();
		return new ModelAndView("newApplication","form",investment);
	} 

	@RequestMapping(value="/",method=RequestMethod.POST)
	public ModelAndView processNewInvestmentApplication(InvestmentApplication investment)
	{
		data.save(investment);

		URI location = restTemplate.postForLocation("http://invest-secure.fr/application", investment); //this line sends the loan for approval request, which could take up to 24 hours
		
		BigDecimal applicableRate = investment.getInterestRate().divide(new BigDecimal("100"));
		applicableRate = applicableRate.add(new BigDecimal("1"));
		
		BigDecimal totalRepayable = new BigDecimal(investment.getSumInvested() * Double.parseDouble(applicableRate.toString()) * investment.getTermInMonths() / 12);
		BigDecimal repayment = totalRepayable.divide(new BigDecimal("" + investment.getTermInMonths()));
		investment.setPayback(repayment);
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(investment.getName());
		message.setSubject("Thank you for your investment application.");
		message.setText("We're currently processing your request, and will send you a further email when we have a decision.");
		JavaMailSender mailSender = new JavaMailSenderImpl();
		mailSender.send(message);
		
		return new ModelAndView("requestAccepted");
	} 
	
	
}
