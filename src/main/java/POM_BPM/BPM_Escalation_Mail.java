package POM_BPM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BPM_Escalation_Mail 
{
	//Data members should be declare globally with access level private by using @FindBy annotations
	@FindBy(xpath="//input[@id='inbox_field']") private WebElement MailId;
	@FindBy(xpath="//button[normalize-space(text())='GO']")private WebElement Go;
	@FindBy(xpath="//div[text()='Compose']")private WebElement Compose;
	/*@FindBy(xpath="//table[@class='table-striped jambo_table']//tr[1]//td[2]") private WebElement FromText;
	@FindBy(xpath="//table[@class='table-striped jambo_table']//tr[1]//td[3]") private WebElement SubjectText;
	@FindBy(xpath="//table[@class='table-striped jambo_table']//tr[1]//td[4]")private WebElement ReceivedTime;*/
	
	
	//Initialize the constructor with access level public using PageFactory class
	public BPM_Escalation_Mail(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilize within the methods with access level public
	public String selectConditionalTask(WebDriver driver)
	{
		WebElement EmailText = null;
		String text = null;
		int i=1,j;
		for(j=2;j<5;j++)
		{
			EmailText = driver.findElement(By.xpath("//table[@class='table-striped jambo_table']//tr["+i+"]//td["+j+"]"));
		}
		if(j==2)
		{
			text = EmailText.getText();
			
		}else if(j==3)
		{
			text = EmailText.getText();
			
		}else if(j==4)
		{
			text = EmailText.getText();
		}
		return text;
		
	}
	
	public void enterEmail(String email)
	{
		MailId.sendKeys(email);
		
	}
	public void clickOnGO()
	{
		Go.click();
		
	}
	
	public void clickOnCompose()
	{
		Compose.click();
		
	}
	
}
