package POM_Invoice_Module;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Library_Files.UtilityClass;

public class Add_Invoice 
{
	//Data members should be declare globally with access level private by using @findBy annotation
	
	@FindBy(xpath="//input[@id='number']")private WebElement InvoiceNumber;
	@FindBy(xpath="//input[@id='quote_number']")private WebElement QuoteNumber;
	@FindBy(xpath="//button[@id='quote_date']")private WebElement QuoteDate;
	@FindBy(xpath="//input[@id='quote_date']")private WebElement getQuoteDate;
	@FindBy(xpath="//button[@id='due_date']")private WebElement DueDate;
	@FindBy(xpath="//input[@id='due_date']")private WebElement getDueDate;
	@FindBy(xpath="//button[@id='invoice_date']")private WebElement InvoiceDate;
	@FindBy(xpath="//input[@id='invoice_date']")private WebElement getInvoiceDate;
	@FindBy(xpath="//input[@id='status']")private WebElement Status;
	@FindBy(xpath="//label[text()='Quantity']/../../..//textarea[@id='description']")private WebElement ProductLineNote;
	@FindBy(xpath="//input[@id='number']")private WebElement QuoteNumberinQuoteModule;
	
	//Initialize the constructor with access level public using PageFactory class
	public Add_Invoice(WebDriver driver)
	{
			PageFactory.initElements(driver, this);
	}
		
	//Utilize within the methods with access level public
	public void enterInvoiceNumber(String invoicenumber) 
	{
		InvoiceNumber.sendKeys(invoicenumber);
			
	}
	public void enterQuoteNumber(String quotenumber) 
	{
		QuoteNumber.sendKeys(quotenumber);
			
	}
	
	public void selectQuoteDate(WebDriver driver, String date) throws Exception 
	{
		QuoteDate.click();
		UtilityClass.wait_until_element_found(driver, QuoteDate);
		UtilityClass.selectDates(driver, date, "dd-MMM-yyyy");
	}
	
	public void selectDueDate(WebDriver driver, String duedate) throws Exception 
	{
		DueDate.click();
		UtilityClass.wait_until_element_found(driver, DueDate);
		UtilityClass.selectDates(driver, duedate, "dd-MMM-yyyy");
	}
	
	public void selectInvoiceDate(WebDriver driver, String invoicedate) throws Exception 
	{
		InvoiceDate.click();
		UtilityClass.wait_until_element_found(driver, InvoiceDate);
		UtilityClass.selectDates(driver, invoicedate, "dd-MMM-yyyy");
	}
	
	public void selectStatus(WebDriver driver, String status) 
	{
		Status.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+status+"')]")).click();	
			
	}
	
	public void enterProductLineNote(String note) 
	{
		ProductLineNote.sendKeys(note);
			
	}
	
	//Create the functions to get the data from invoice
	
	public String getQuoteNumber() 
	{
		String quoteNo = QuoteNumber.getAttribute("value");
			return quoteNo;
	}
	
	public String[] getQuoteDate() 
	{
		String Date = getQuoteDate.getAttribute("value");
		String[] quoteDate = Date.split(" ");
		return quoteDate;
		
	}
	
	public String[] getDueDate() 
	{
		String Date = getDueDate.getAttribute("value");
		String[] dueDate = Date.split(" ");
		return dueDate;
		
	}
	
	public String[] getInvoiceDate() 
	{
		String Date = getInvoiceDate.getAttribute("value");
		String[] invoiceDate = Date.split(" ");
		return invoiceDate;
	}
	
	public String getProductLineNote() 
	{
		String Note = ProductLineNote.getText();
			return Note;
	}
	
	public String getQuoteNumberinQuoteModule() 
	{
		String quotenumber = QuoteNumberinQuoteModule.getAttribute("value");
			return quotenumber;
	}
	
	
}


