package POM_Quote_Module;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Duplicate_Quote_Page 
{
	//Data members should be declare globally with access level private by using @findBy annotation
	@FindBy(xpath="//input[@id='opportunity']")private WebElement Opportunity;
	@FindBy(xpath="//input[@id='stage']")private WebElement QuoteStage;
	@FindBy(xpath="//input[@id='expiration']")private WebElement ValidUntil;
	@FindBy(xpath="//input[@id='invoice_status']")private WebElement InvoiceStatus;
	@FindBy(xpath="//input[@id='term']")private WebElement PaymentTerm;
	@FindBy(xpath="//input[@id='approval_status']")private WebElement ApprovalStatus;
	@FindBy(xpath="//textarea[@id='approval_issue']")private WebElement ApprovalIssue;
	@FindBy(xpath="//input[@id='scrm_knight_aos_quotes_1_name']")private WebElement Knight;
	@FindBy(xpath="//input[@id='billing_account']")private WebElement Account;
	@FindBy(xpath="//input[@id='billing_contact']")private WebElement Contact;
	@FindBy(xpath="//div[@id='currency_id']")private WebElement Currency;
	@FindBy(xpath="//input[@id='number']")private WebElement QuoteNumber;
	@FindBy(xpath="//label[text()='Quantity']/../../..//input[@id='product_qty']")private WebElement ProductLineQuantity;

	
	@FindBy(xpath="//label[text()='Quantity']/../../..//input[@id='product_discount']")private WebElement ProductLineDiscount;
	@FindBy(xpath="//label[text()='Quantity']/../../..//div[@id='discount']")private WebElement ProductLineDiscountType;
	@FindBy(xpath="//label[text()='Quantity']/../../..//textarea[@id='item_description']")private WebElement ProductLineItemDescription;
	@FindBy(xpath="//textarea[@id='description']")private WebElement Note;
	
	//----------------Service Line----------------//
	@FindBy(xpath="//textarea[@id='name']")private WebElement ServiceName;
	
	@FindBy(xpath="//input[@id='total_amount']")private WebElement GrandTotal;
	
	@FindBy(xpath="//h6[text()='Approval Status']/../following-sibling::div//h6")private WebElement DetailViewApprovalStatus;
	
	//Xpath for Email Quotation
	@FindBy(xpath="//div[@id='mui-component-select-from_addr_name']//span")private WebElement EmailFrom;
		//Initialize the constructor with access level public using PageFactory class
		public Duplicate_Quote_Page(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
			
		}

		//Utilize within the methods with access level public
/*
		public String getOpportunity()
		{
			String OppName = Opportunity.getAttribute("value");
			return OppName;
		}
		public String getQuoteStage()
		{
			String Qstage = QuoteStage.getText();
			return Qstage;
		}

		public String getValidUntil()
		{
			String ValidDate = ValidUntil.getAttribute("value");
			return ValidDate;
		}
		public String getInvoiceStatus()
		{
			String Istatus = InvoiceStatus.getText();
			return Istatus;
		}
		public String getPaymentTerm()
		{
			String term = PaymentTerm.getText();
			return term;
		}
		public String getApprovalStatus()
		{
			String Astatus = ApprovalStatus.getText();
			return Astatus;
		}
		public String getApprovalIssue()
		{
			String AIssue = ApprovalIssue.getText();
			return AIssue;
		}
		*/
		public String getAccount()
		{
			String account = Account.getAttribute("value");
			return account;
		}
		
		public String getContact()
		{
			String contact = Contact.getAttribute("value");
			return contact;
		}
		public String getCurrency()
		{
			String currency = Currency.getText();
			return currency;
		}
		public String getEmailFrom()
		{
			String emailfrom = EmailFrom.getText();
			return emailfrom;
			
		}
	
		public String getGrandTotal()
		{
			String total = GrandTotal.getAttribute("value");
			return total;
			
		}
		
		public String getDetailViewApprovalStatus()
		{
			String approvalstatus = DetailViewApprovalStatus.getText();
			System.out.println(approvalstatus);
			return approvalstatus;
		}
}
