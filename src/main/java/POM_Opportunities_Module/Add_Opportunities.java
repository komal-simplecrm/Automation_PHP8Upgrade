package POM_Opportunities_Module;




import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Library_Files.UtilityClass;



public class Add_Opportunities 
{
	//Data members should be declare globally with access level private by using @findBy annotation
		@FindBy(xpath="//input[@id='name']")private WebElement Name;
		@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-account_name']")private WebElement AccountNameSerachIcon;
		@FindBy(xpath="//div[@id='currency_id']")private WebElement Currency;
		@FindBy(xpath="//input[@id='date_closed']")private WebElement Date_closed;
		@FindBy(xpath="//button[@id='date_closed']")private WebElement Expected_Date_closed;
		@FindBy(xpath="//input[@id='amount']")private WebElement OpportunityAmount;
		@FindBy(xpath="//input[@id='opportunity_type']")private WebElement Type;
		@FindBy(xpath="//input[@id='sales_stage']")private WebElement SalesStage;
		@FindBy(xpath="//input[@id='probability']")private WebElement Probability;
		@FindBy(xpath="//input[@id='next_step']")private WebElement Next_Step;
		@FindBy(xpath="//button[@id='actual_date_closed_c']")private WebElement Actual_date_closed;
		@FindBy(xpath="//button[@id='date_lost_c']")private WebElement Date_lost;
		@FindBy(xpath="//textarea[@id='description']")private WebElement Description;
		@FindBy(xpath="//textarea[@id='reason_for_lost_c']")private WebElement Reason_for_lost;
		@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-account_name']")private WebElement CampaignSearchIcon;
		@FindBy(xpath="//div[@role='dialog']//input[@id='name']")private WebElement windowName;
		@FindBy(xpath="//button[contains(@class,'underlineHover')]")private WebElement BackToListView;
		@FindBy(xpath="//button[@form='relate-search-form']")private WebElement SearchBtnOnWindow;
		
		
		//Xpath for Sales flow of Create Opportunity
		@FindBy(xpath="//input[@id='Opportunitiesamount']")private WebElement SalesFlowOpportunityAmount;
		@FindBy(xpath="//input[@id='Opportunitiessales_stage']")private WebElement SalesFlowSalesStage;
		@FindBy(xpath="//div[@id='Opportunitiescurrency_id']")private WebElement SalesFlowCurrency;
		@FindBy(xpath="//button[@id='Opportunitiesdate_closed']")private WebElement SalesFlowDate_Closed;
		@FindBy(xpath="//textarea[@id='Opportunitiesdescription']")private WebElement SalesFlowDescription;
		@FindBy(xpath="//input[@id='Opportunitiesname']")private WebElement SalesFlowOpportunitiesName;
		@FindBy(xpath="//h6//div[@id='status']")private WebElement DetailViewStatus;
		@FindBy(xpath="//h6//div[@id='sales_stage']")private WebElement DetailViewSalesStageOfOpportunities;
		
		//Initialize the constructor with access level public using PageFactory class
		public Add_Opportunities(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
			
		}

		//Utilize within the methods with access level public
		public String enterName( String opportunityName)
		{
			
			String s = Keys.chord(Keys.CONTROL, "a");
			Name.sendKeys(s);
			Name.sendKeys(Keys.DELETE);
			//JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
			//set the text
			//jsExecutor.executeScript("document.getElementById('name').value='"+opportunityName+"'");  
			Name.sendKeys(opportunityName);
			String Opportunityname=Name.getAttribute("value");
			return Opportunityname;
		}
		
		public void enterAccountName(WebDriver driver, String accountName) throws InterruptedException
		{
			AccountNameSerachIcon.click();
			windowName.sendKeys(accountName);
			SearchBtnOnWindow.click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+accountName+"')]")).click();
			
			
		}
		
		public void selectCurrency(WebDriver driver, String currency)
		{
			
			Currency.click();
			driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+currency+"')]")).click();
		} 
		public String enterDate_closed(WebDriver driver, String date) throws Exception
		{
			String s = Keys.chord(Keys.CONTROL, "a");
			Date_closed.sendKeys(s);
			Date_closed.sendKeys(Keys.DELETE);
			Expected_Date_closed.click();
			UtilityClass.wait_until_element_found(driver, Date_closed);
			Thread.sleep(2000);
			UtilityClass.selectDates(driver, date, "dd-MMM-yyyy");
			String date1 = Expected_Date_closed.getAttribute("value");
			return date1;
		}
		
		
		public String enterOpportunityAmount(String opportunityAmount)
		{
			String s = Keys.chord(Keys.CONTROL, "a");
			OpportunityAmount.sendKeys(s);
			OpportunityAmount.sendKeys(Keys.DELETE);
			OpportunityAmount.sendKeys(opportunityAmount);
			String Opportunityamount=OpportunityAmount.getAttribute("value");
			return Opportunityamount;
			
		}
		public void selectType(WebDriver driver, String type)
		{
			
			Type.click();
			driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+type+"')]")).click();
		} 
		public String selectSalesStage(WebDriver driver, String salesStage, String date, String reason_for_lost, String Actualdate, String description) throws Exception
		{
			
			String s = Keys.chord(Keys.CONTROL, "a");
			SalesStage.sendKeys(s);
			SalesStage.sendKeys(Keys.DELETE);
			SalesStage.click();
			Thread.sleep(2000);
			WebElement SalesStage=driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+salesStage+"')]"));
			SalesStage.click();
			
			//String Stages=driver.findElement(By.xpath("//ul[@role='listbox']//li[@data-value='Closed Lost']")).getAttribute("data-value");
			
			
			System.out.println(salesStage);
			Thread.sleep(2000);
			String SlaesStages="Closed Lost";
			if(salesStage.equals(SlaesStages))
			{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", Type);
				String s1 = Keys.chord(Keys.CONTROL, "a");
				Date_lost.sendKeys(s1);
				Date_lost.sendKeys(Keys.DELETE);
				Date_lost.click();
				UtilityClass.wait_until_element_found(driver, Date_lost);
				System.out.println(date);
				UtilityClass.selectDates(driver, date, "dd-MMM-yyyy");
				Thread.sleep(5000);
				String s3 = Keys.chord(Keys.CONTROL, "a");
				Reason_for_lost.sendKeys(s3);
				Reason_for_lost.sendKeys(Keys.DELETE);
				Reason_for_lost.sendKeys(reason_for_lost);
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", Type);
			}else
			{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", Next_Step);
				String s2 = Keys.chord(Keys.CONTROL, "a");
				Actual_date_closed.sendKeys(s2);
				Actual_date_closed.sendKeys(Keys.DELETE);
				Actual_date_closed.click();
				UtilityClass.wait_until_element_found(driver, Actual_date_closed);
				System.out.println("Actual date: "+Actualdate);
				UtilityClass.selectDates(driver, Actualdate, "dd-MMM-yyyy");
				Thread.sleep(5000);
				String s4 = Keys.chord(Keys.CONTROL, "a");
				Description.sendKeys(s4);
				Description.sendKeys(Keys.DELETE);
				Description.sendKeys(description);
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", Type);
			}
			return salesStage;
		} 
		/*public void enterDate_lost_c(WebDriver driver, String date) throws Exception
		{
			Date_lost_c.click();
			UtilityClass.wait_until_element_found(driver, Date_lost_c);
			UtilityClass.selectDates(driver, date, "dd/MMM/yyyy");
		}*/
		public void enterNext_Step(String next_step)
		{
			Next_Step.sendKeys(next_step);
		}
		/*public void enterActual_date_closed_c(WebDriver driver, String date) throws Exception
		{
			Actual_date_closed_c.click();
			UtilityClass.wait_until_element_found(driver, Actual_date_closed_c);
			UtilityClass.selectDates(driver, date, "dd/MMM/yyyy");
		}*/
		public void enterProbability(String probability)
		{
			Probability.sendKeys(probability);
		}
		public void enterDescription(String description)
		{
			Description.sendKeys(description);
		}
		public String verifyOpportunityName(WebDriver driver,String name)
		{
			WebElement Oppname=driver.findElement(By.xpath("//tbody//td[@data-tableid='ListViewTable']//p//a[text()='"+name+"']"));
			String Opportuityname=Oppname.getText();
			System.out.println(Opportuityname);
			return Opportuityname;
		
		}
		public void clickOnOpp(WebDriver driver,String  OppName)
		{
			driver.findElement(By.xpath("//a[text()='"+OppName+"']")).click();
		}
		public void backToListView()
		{
			BackToListView.click();
		}
		
		//functions for Convert Lead perform actions on opportunity
		public void scrollUpToCreateOpportuity(WebDriver driver )
		{
			WebElement text = driver.findElement(By.xpath("//h6[text()='Create Opportunity']"));
			
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", text);
		}
		
		public void selectSalesFlowCurrency(WebDriver driver, String currency)
		{
			
			SalesFlowCurrency.click();
			driver.findElement(By.xpath("//ul[@role='listbox']//li//span[contains(text(),'"+currency+"')]")).click();
		} 
		
		public void selectSalesFlowSalesStage(WebDriver driver, String salesstage)
		{
			
			SalesFlowSalesStage.click();
			driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+salesstage+"')]")).click();
		}
		
		public void selectSalesFlowOpportunityAmount(String amount)
		{
			String s4 = Keys.chord(Keys.CONTROL, "a");
			SalesFlowOpportunityAmount.sendKeys(s4);
			SalesFlowOpportunityAmount.sendKeys(Keys.DELETE);
			SalesFlowOpportunityAmount.sendKeys(amount);
		}
		
		public void selectSalesFlowDate_Closed(WebDriver driver, String date) throws Exception
		{
			SalesFlowDate_Closed.click();
			UtilityClass.wait_until_element_found(driver, SalesFlowDate_Closed);
			UtilityClass.selectDates(driver, date, "dd-MMM-yyyy");
			Thread.sleep(5000);
		
		}
		
		public void selectSalesFlowDescription(String description)
		{	String s2 = Keys.chord(Keys.CONTROL, "a");
			SalesFlowDescription.sendKeys(s2);
			SalesFlowDescription.sendKeys(Keys.DELETE);
			SalesFlowDescription.sendKeys(description);
		}
		
		public String getStatusConverted()
		{
			String status = DetailViewStatus.getText();
			return status;
		}
		
		public String getOpportunitiesName()
		{
			String Opportunitiesname = SalesFlowOpportunitiesName.getAttribute("value");
			return Opportunitiesname;
		}
		
		public String getDetailViewSalesStageOfOpportunities()
		{
			String SalesStageOfOpportunities = DetailViewSalesStageOfOpportunities.getText();
			return SalesStageOfOpportunities;
		}
		
}

