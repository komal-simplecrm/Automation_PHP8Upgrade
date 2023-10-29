package POM_Feedback_Module;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Add_Feedback 
{
	//Data members should be declare globally with access level private by using @findBy annotation
	@FindBy(xpath="//input[@id='feeback_service_rating_c']")private WebElement AntonymsOfSatisfaction;
	@FindBy(xpath="//input[@id='feedback_resolution_time_c']")private WebElement IssueResolved;
	@FindBy(xpath="//input[@id='feedback_date_entered_c']")private WebElement DateEntered;
	@FindBy(xpath="//input[@id='feedback_explaination_time_c']")private WebElement ArticulateTroubleshootingSteps;
	@FindBy(xpath="//input[@id='feedback_recommend_friend_c']")private WebElement Rating;
	@FindBy(xpath="//input[@id='feedback_recommendation_time_c']")private WebElement Referance;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-cases_scrm_feedback_1_name']")private WebElement SearchIconCases;
	@FindBy(xpath="//input[@id='case_number']")private WebElement Number;
	@FindBy(xpath="//button[@form='relate-search-form']")private WebElement SearchBtnOnWindow;
	@FindBy(xpath="//input[@id='feedback_resolution_result_c']")private WebElement UnderstandEngineerClearly;
	@FindBy(xpath="//input[@id='feedback_on_website_c']")private WebElement PrintFeedbackOnWebsite;
	@FindBy(xpath="//input[@id='feedback_description_c']")private WebElement Remarks;


	//Initialize the constructor with access level public using PageFactory class
	public Add_Feedback(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
		
	//Utilize within the methods with access level public
	public void enterAntonymsOfSatisfaction(String Antonyms) throws InterruptedException
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		AntonymsOfSatisfaction.sendKeys(s);
		AntonymsOfSatisfaction.sendKeys(Keys.DELETE);
		AntonymsOfSatisfaction.sendKeys(Antonyms);
	}
	
	public void enterIssueResolved(String YesNo) throws InterruptedException
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		IssueResolved.sendKeys(s);
		IssueResolved.sendKeys(Keys.DELETE);
		IssueResolved.sendKeys(YesNo);
	}
	
	public void enterDateEntered(String dateentered) throws InterruptedException
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		DateEntered.sendKeys(s);
		DateEntered.sendKeys(Keys.DELETE);
		DateEntered.sendKeys(dateentered);
	}

	public void enterArticulateTroubleshootingSteps(String YesNo) throws InterruptedException
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		ArticulateTroubleshootingSteps.sendKeys(s);
		ArticulateTroubleshootingSteps.sendKeys(Keys.DELETE);
		ArticulateTroubleshootingSteps.sendKeys(YesNo);
	}
	//Rating in between 1 - 10
	public void enterRating(String rating) throws InterruptedException
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		Rating.sendKeys(s);
		Rating.sendKeys(Keys.DELETE);
		Rating.sendKeys(rating);
	}
	public void enterReferance(String referance) throws InterruptedException
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		Referance.sendKeys(s);
		Referance.sendKeys(Keys.DELETE);
		Referance.sendKeys(referance);
	}
	
	public void selectCases(WebDriver driver, String number) throws InterruptedException
	{
		SearchIconCases.click();
		Number.sendKeys(number);
		SearchBtnOnWindow.click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//table[@role='grid']//tbody//td//div[text()='"+number+"']/../..//a")).click();
	}
	
	public void enterUnderstandEngineerClearly(String YesNo) throws InterruptedException
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		UnderstandEngineerClearly.sendKeys(s);
		UnderstandEngineerClearly.sendKeys(Keys.DELETE);
		UnderstandEngineerClearly.sendKeys(YesNo);
	}
	
	public void enterPrintFeedbackOnWebsite(String YesNo) throws InterruptedException
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		PrintFeedbackOnWebsite.sendKeys(s);
		PrintFeedbackOnWebsite.sendKeys(Keys.DELETE);
		PrintFeedbackOnWebsite.sendKeys(YesNo);
	}
	
	public void enterRemarks(String remarks) throws InterruptedException
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		Remarks.sendKeys(s);
		Remarks.sendKeys(Keys.DELETE);
		Remarks.sendKeys(remarks);
	}
}
