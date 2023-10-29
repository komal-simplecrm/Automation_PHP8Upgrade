package POM_Quote_Module;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Library_Files.UtilityClass;

public class Add_Quotes 
{
	//Data members should be declare globally with access level private by using @findBy annotation
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-opportunity']")private WebElement SearchIconOpportunity;
	@FindBy(xpath="//input[@id='opportunity']")private WebElement Opportunity;
	@FindBy(xpath="//input[@id='number']")private WebElement QuoteNumber;
	@FindBy(xpath="//input[@id='stage']")private WebElement QuoteStage;
	@FindBy(xpath="//button[@id='expiration']")private WebElement ValidUntil;
	@FindBy(xpath="//input[@id='expiration']")private WebElement GetValidUntil;
	@FindBy(xpath="//input[@id='invoice_status']")private WebElement InvoiceStatus;
	@FindBy(xpath="//input[@id='term']")private WebElement PaymentTerm;
	@FindBy(xpath="//input[@id='approval_status']")private WebElement ApprovalStatus;
	@FindBy(xpath="//textarea[@id='approval_issue']")private WebElement ApprovalIssue;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-scrm_knight_aos_quotes_1_name']")private WebElement SearchIconKnight;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-aos_products_aos_quotes_1_name']")private WebElement SearchIconProduct;
	@FindBy(xpath="//input[@id='scrm_knight_aos_quotes_1_name']")private WebElement Knight;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-billing_account']")private WebElement SearchIconAccount;
	@FindBy(xpath="//input[@id='billing_account']")private WebElement Account;
	@FindBy(xpath="//input[@id='billing_contact']")private WebElement Contact;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-billing_contact']")private WebElement SearchIconContact;
	@FindBy(xpath="//div[@id='currency_id']")private WebElement Currency;
	@FindBy(xpath="//input[@id='shipping_amount']")private WebElement ShippingAmount;
	@FindBy(xpath="//input[@id='shipping_tax']")private WebElement ShippingTax;
	@FindBy(xpath="//span[text()='ADD GROUP']")private WebElement AddGroup;
	@FindBy(xpath="//span[text()='ADD PRODUCT LINE']")private WebElement AddProductLine;
	@FindBy(xpath="//span[text()='ADD SERVICE LINE']")private WebElement AddServiceLine;
	@FindBy(xpath="//label[text()='Quantity']")private WebElement getProductTitle;
	@FindBy(xpath="//label[text()='Name']")private WebElement getServiceTitle;
	@FindBy(xpath="//div[@id='panel1bh-content']//input[@id='total_amount']")private WebElement GrandTotalProductPrice;
	//Window xpath
	@FindBy(xpath="//div[@role='dialog']//input[@id='name']")private WebElement WindowName;
	@FindBy(xpath="//button[@form='relate-search-form']")private WebElement SearchBtnOnWindow;
	@FindBy(xpath="//div[@role='dialog']//input[@id='first_name']")private WebElement windowFirstName;
	@FindBy(xpath="//div[@role='dialog']//input[@id='last_name']")private WebElement windowLastName;
	
	
	//Xpath for ADD PRODUCT LINE
	@FindBy(xpath="//label[text()='Quantity']/../../..//input[@id='product_qty']")private WebElement ProductLineQuantity;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-aos_products']")private WebElement SearchIconProducts;
	@FindBy(xpath="//label[text()='Quantity']/../../..//input[@id='aos_products']")private WebElement Products;
	@FindBy(xpath="//input[@id='part_number']")private WebElement PartNumber;
	@FindBy(xpath="//label[text()='Quantity']/../../..//input[@id='product_list_price']")private WebElement ProductLineListPrice;
	@FindBy(xpath="//label[text()='Quantity']/../../..//input[@id='product_discount']")private WebElement ProductLineDiscount;
	@FindBy(xpath="//label[text()='Quantity']/../../..//input[@id='discount']")private WebElement ProductLineDiscountType;
	@FindBy(xpath="//label[text()='Quantity']/../../..//input[@id='product_unit_price']")private WebElement UnitPrice;
	@FindBy(xpath="//label[text()='Quantity']/../../..//input[@id='vat_amt']")private WebElement TaxAmount;
	@FindBy(xpath="//label[text()='Quantity']/../../..//input[@id='product_total_price']")private WebElement TotalProductPrice;
	@FindBy(xpath="//label[text()='Quantity']/../../..//input[@id='vat']")private WebElement ProductLineTax;
	@FindBy(xpath="//label[text()='Quantity']/../../..//button[@aria-label='delete']")private WebElement ProductLineDeleteIcon;
	@FindBy(xpath="//label[text()='Quantity']/../../..//button[@aria-label='show more']")private WebElement ProductLineShowMoreIcon;
	@FindBy(xpath="//label[text()='Quantity']/../../..//textarea[@id='item_description']")private WebElement ProductLineItemDescription;
	
	//Xpath for ADD SERVICE LINE
	@FindBy(xpath="//label[text()='Name']/../../..//textarea[@id='name']")private WebElement ServiceLineName;
	@FindBy(xpath="//label[text()='Name']/../../..//input[@id='product_list_price']")private WebElement ServiceLineListPrice;
	@FindBy(xpath="//label[text()='Name']/../../..//input[@id='product_discount']")private WebElement ServiceLineDiscount;
	@FindBy(xpath="//label[text()='Name']/../../..//input[@id='discount']")private WebElement ServiceLineDiscountType;
	@FindBy(xpath="//label[text()='Name']/../../..//input[@id='vat']")private WebElement ServiceLineTax;
	@FindBy(xpath="//label[text()='Name']/../../..//button[@aria-label='delete']")private WebElement ServiceLineDeleteIcon;
	@FindBy(xpath="//label[text()='Name']/../../..//input[@id='product_unit_price']")private WebElement ServiceUnitPrice;
	@FindBy(xpath="//label[text()='Name']/../../..//input[@id='vat_amt']")private WebElement ServiceTaxAmount;
	@FindBy(xpath="//label[text()='Name']/../../..//input[@id='product_total_price']")private WebElement ServiceTotalProductPrice;

	//Xpath for Email Quotation
	
	@FindBy(xpath="//input[@name='to_addrs_names']")private WebElement EmailTo;
	@FindBy(xpath="//span[text()='Select']") private WebElement Select;
	@FindBy(xpath="//label[text()='Subject']/..//input") private WebElement SubjectOnComposeEmail;
	@FindBy(xpath="//button[@type='submit']") private WebElement Send;
	@FindBy(xpath="//input[@type='file']/..") private WebElement AttachFile;
	@FindBy(xpath="//button[@aria-label='Attach Documents']") private WebElement AttachDocuments;
	@FindBy(xpath="//button[@title='Choose Email Template']") private WebElement ChooseEmailTemplate;
	@FindBy(xpath="//button[@title='Save Draft']") private WebElement SaveDraft;
	@FindBy(xpath="//button[@title='Related To']") private WebElement RelatedTo;
	@FindBy(xpath="//div[@name='parent_name']//input[@id='parent_name']") private WebElement relatedTo;
	
	//xpath document name on window
	@FindBy(xpath="//div[@role='dialog']//input[@id='document_name']")private WebElement WindowDocumentName;
	@FindBy(xpath="//input[@id='total_amount']")private WebElement GrandTotal;
	@FindBy(xpath="//input[@aria-label='search']")private WebElement SearchBtn;
	@FindBy(xpath="//button[@type='submit']")private WebElement Searchicon;
	@FindBy(xpath="//button[@id='LBL_EDIT_BUTTON_TITLE']")private WebElement Edit;
	@FindBy(xpath="//div[@id='mui-component-select-from_addr_name']//span")private WebElement EmailFrom;
	//Initialize the constructor with access level public using PageFactory class
	public Add_Quotes(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilize within the methods with access level public
	public void selectOpportunity(WebDriver driver, String opportunityName) throws InterruptedException
	{
		SearchIconOpportunity.click();
		String s = Keys.chord(Keys.CONTROL, "a");
		WindowName.sendKeys(s);
		WindowName.sendKeys(Keys.DELETE);
		WindowName.sendKeys(opportunityName);
		SearchBtnOnWindow.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+opportunityName+"')]")).click();
	}
	
	public void enterQuoteNumber(String quoteNumber)
	{
		QuoteNumber.sendKeys(quoteNumber);
		
	}
	
	public String selectQuoteStage(WebDriver driver, String quoteStage)
	{
		QuoteStage.click();
		WebElement stage = driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+quoteStage+"')]"));
		stage.click();	
		String stagevalue = QuoteStage.getText();
		return stagevalue;
	}
	
	public String selectValidUntil(WebDriver driver, String ValidDate) throws Exception
	{
		
		ValidUntil.click();
		UtilityClass.wait_until_element_found(driver, ValidUntil);
		UtilityClass.selectDates(driver, ValidDate, "dd-MMM-yyyy" );
		String Validdate = GetValidUntil.getAttribute("value");
		return Validdate;
		
	}
	
	public void selectInvoiceStatus(WebDriver driver, String invoiceStatus)
	{
		InvoiceStatus.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+invoiceStatus+"')]")).click();	
	}

	public void selectPaymentTerm(WebDriver driver, String paymentTerm)
	{
		PaymentTerm.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+paymentTerm+"')]")).click();	
	}
	
	public void selectApprovalStatus(WebDriver driver, String approvalStatus, String approvalIssue) throws InterruptedException
	{
		ApprovalStatus.click();
		WebElement approvalvalue = driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+approvalStatus+"']"));	
		approvalvalue.click();
		String status = ApprovalStatus.getText();
		Thread.sleep(2000);
		System.out.println(status);
		if(status.equals("Not Approved")||status.equals("Pending Approval"))
		{
			ApprovalIssue.sendKeys(approvalIssue);
		}
	
	}
	
	/*public void enterApprovalIssue(String approvalIssue)
	{
		ApprovalIssue.sendKeys(approvalIssue);
		
	}*/
	public void selectKnight(WebDriver driver, String knight) throws InterruptedException
	{
		SearchIconKnight.click();
		String[] saprateknight = knight.split(" ");
		String firstName = saprateknight[0];
		String lastName = saprateknight[1];
		
		//Clear the field
		String s = Keys.chord(Keys.CONTROL, "a");
		windowFirstName.sendKeys(s);
		windowFirstName.sendKeys(Keys.DELETE);
		//enter text in first name field
		windowFirstName.sendKeys(firstName);
		//Clear the field
		String s1 = Keys.chord(Keys.CONTROL, "a");
		windowLastName.sendKeys(s1);
		windowLastName.sendKeys(Keys.DELETE);
		//enter text in last name field
		windowLastName.sendKeys(lastName);
		
		//windowFirstName.sendKeys(assigned);
		SearchBtnOnWindow.click();
		Thread.sleep(4000);
		WebElement contact = driver.findElement(By.xpath("(//table[@role='grid']//tbody//td//a[contains(text(),'"+knight+"')])[1]"));
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",contact);
		contact.click();
	}
	
	public void selectAccount(WebDriver driver, String AccountName) throws InterruptedException
	{
		SearchIconAccount.click();
		String s = Keys.chord(Keys.CONTROL, "a");
		WindowName.sendKeys(s);
		WindowName.sendKeys(Keys.DELETE);
		WindowName.sendKeys(AccountName);
		SearchBtnOnWindow.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+AccountName+"')]")).click();
	}
	
	public void selectContact(WebDriver driver, String ContactName) throws InterruptedException
	{
		SearchIconContact.click();
		String[] sapratename = ContactName.split(" ");
		String firstName = sapratename[0];
		String lastName = sapratename[1];
		
		//Clear the field
		String s = Keys.chord(Keys.CONTROL, "a");
		windowFirstName.sendKeys(s);
		windowFirstName.sendKeys(Keys.DELETE);
		//enter text in first name field
		windowFirstName.sendKeys(firstName);
		//Clear the field
		String s1 = Keys.chord(Keys.CONTROL, "a");
		windowLastName.sendKeys(s1);
		windowLastName.sendKeys(Keys.DELETE);
		//enter text in last name field
		windowLastName.sendKeys(lastName);
		
		//windowFirstName.sendKeys(assigned);
		SearchBtnOnWindow.click();
		Thread.sleep(4000);
		WebElement contact = driver.findElement(By.xpath("(//table[@role='grid']//tbody//td//a[contains(text(),'"+ContactName+"')])[1]"));
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",contact);
		contact.click();
	
	}
	
	public void selectCurrency(WebDriver driver, String currency)
	{
		Currency.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li//span[contains(text(),'"+currency+"')]")).click();	
	}
	//Add Product line functions
	public void clickOnAddProductLine()
	{
		AddProductLine.click();
	}
	
	public void enterProductLineQuantity(String quantity)
	{
		ProductLineQuantity.sendKeys(quantity);
	}
	
	public void selectProducts(WebDriver driver, String product) throws InterruptedException
	{
		SearchIconProducts.click();
	
		WindowName.sendKeys(product);
		SearchBtnOnWindow.click();//defect in this field
		Thread.sleep(3000);
		driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+product+"')]")).click();
	}
	
	/*public void enterProductLineListPrice(String listPrice)
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		ProductLineListPrice.sendKeys(s);
		ProductLineListPrice.sendKeys(Keys.DELETE);
		ProductLineListPrice.sendKeys(listPrice);
	}*/
	
	public void enterProductLineDiscount(String discount)
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		ProductLineDiscount.sendKeys(s);
		ProductLineDiscount.sendKeys(Keys.DELETE);
		ProductLineDiscount.sendKeys(discount);
	}
	
	public void selectProductLineDiscountType(WebDriver driver, String discountType)
	{
		ProductLineDiscountType.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+discountType+"')]")).click();	
	}
	
	public void selectProductLineTax(WebDriver driver, String tax)
	{
		ProductLineTax.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+tax+"')]")).click();	
	}
	
	public void clickOnDeleteIcon()
	{
		ProductLineDeleteIcon.click();
	}
	
	public void clickOnShowMore()
	{
		ProductLineShowMoreIcon.click();
	}
	
	public void enterDescription(String description)
	{
		ProductLineItemDescription.sendKeys(description);
	}
	
	//Add Service line functions
	public void clickOnAddGroup()
	{
		AddGroup.click();
	}
	
	public void clickOnAddServiceLine()
	{
		AddServiceLine.click();
	}
	
	public void enterServiceLineName(String name)
	{
		ServiceLineName.sendKeys(name);
	} 
	
	public void enterServiceLineListPrice(String ListPrice)
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		ServiceLineListPrice.sendKeys(s);
		ServiceLineListPrice.sendKeys(Keys.DELETE);
		ServiceLineListPrice.sendKeys(ListPrice);
	}
	
	public void enterServiceLineDiscount(String Discount)
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		ServiceLineDiscount.sendKeys(s);
		ServiceLineDiscount.sendKeys(Keys.DELETE);
		ServiceLineDiscount.sendKeys(Discount);
	}
	
	public void selectServiceLineDiscountType(WebDriver driver, String discountType)
	{
		ServiceLineDiscountType.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+discountType+"')]")).click();	
	}
	
	public void selectServiceLineTax(WebDriver driver, String Tax)
	{
		ServiceLineTax.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+Tax+"')]")).click();	
	}
	
	public void clickOnServiceLineDeleteIcon()
	{
		ServiceLineDeleteIcon.click();
	}
	
	public void enterShippingAmount(String shippingAmount)
	{
		ShippingAmount.sendKeys(shippingAmount);
	}
	
	public void selectShippingTax(WebDriver driver, String Tax)
	{
		ShippingTax.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+Tax+"')]")).click();	
	}
	
	public void scrollUpToCurrency(WebDriver driver)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", Currency);
	}
	
	public void scrollUpToDescription(WebDriver driver)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",ProductLineItemDescription);
	}
	
	

	public void selectTemplate(WebDriver driver, String templateName)
	{
		WebElement Template = driver.findElement(By.xpath("//div[@aria-labelledby='simple-dialog-title']//ul//span[text()='"+templateName+"']"));
		
		Template.click();
		
	}
	
	//Click on CC and BCC
	public void addCCorBCC(WebDriver driver, String label, String title)
	{
		//this xpath is used to click CC, BCC.
		//title = Add CC, and Add BCC
		//label = CC and BCC 	
		driver.findElement(By.xpath("//label[text()='"+label+"']/..//div//div//button[@title='"+title+"']")).click();	
	}
	
	//Function for search functionality and select Account, Contacts and Leads.
	public void searchIconFuctionality(WebDriver driver, String label, String title, String name)
	{
		//label = To, CC and BCC 
		//title = "Accounts","Contacts" and "Leads"
		if(label.equalsIgnoreCase("CC")||label.equalsIgnoreCase("BCC"))
		{
			driver.findElement(By.xpath("//button[@title='Add "+label+"']")).click();
		}
		//click on search icon and open "Accounts","Contacts" and "Leads" icons only pass label in which label search icon you need to click.
		driver.findElement(By.xpath("//label[text()='"+label+"']/..//div//div//button[@title='Select from CRM']")).click();
		//click on "Accounts","Contacts" and "Leads" icon pass with label To, CC and BCC any one
		driver.findElement(By.xpath("//label[text()='"+label+"']/..//div//div//button[@title='"+title+"']")).click();
		String[] Name = name.split(" ");
		String FirstName = Name[0];
		String LastName = Name[1];
		if(title.equalsIgnoreCase("Contacts")||title.equalsIgnoreCase("Leads"))
		{
			windowFirstName.sendKeys(FirstName);
			windowLastName.sendKeys(LastName);
		}
		else
		{
			WindowName.sendKeys(name);
		}
		SearchBtnOnWindow.click();
		driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+name+"')]/../../../..//input")).click();
		Select.click();
		
	}
	
	public void enterSubject(String subject)
	{
		SubjectOnComposeEmail.sendKeys(subject);
	}
	
	public void clickOnattachFile() throws InterruptedException, AWTException
	{
		AttachFile.click();
		//Upload File maximum size 7MB
		StringSelection photo = new StringSelection("D:\\Test Documents for Documents module\\Test8.jpg"); 
		File file = new File("D:\\Test Documents for Documents module\\Test8.jpg");
		
		
		//File upload size must be less than 7 MB
		long expectedSizeInMB = 7;
		
		long sizeInBytes = file.length();
		//transform in MB
		long sizeInkb = sizeInBytes / 1024;
		long sizeInMb = sizeInBytes / (1024 * 1024);

		if (expectedSizeInMB > sizeInMb) {
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(photo, null);

			//Pasting the contents of clipboard in the field "File name" of the Window Pop-up
			Thread.sleep(3000); //Some sleep time to detect the window popup
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			//To click on the "Open" button to upload files
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			 System.out.println("File is uploaded successfully");
			 System.out.println(sizeInkb+" KB");
		
		} else 
		{
		   System.out.println("Not bigger than " + expectedSizeInMB + " MB");
		}
		
	}
	
	public void clickOnAttachDocuments(WebDriver driver, String documentName) throws InterruptedException
	{
		AttachDocuments.click();
		Thread.sleep(4000);
		WindowDocumentName.sendKeys(documentName);
		SearchBtnOnWindow.click();
		
		WebElement document = driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+documentName+"')]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",document);
		Thread.sleep(2000);
		document.click();
	}
	
	public void clickOnChooseEmailTemplate(WebDriver driver, String emailTemplateName) throws InterruptedException
	{
		ChooseEmailTemplate.click();
		Thread.sleep(4000);
		WindowName.sendKeys(emailTemplateName);
		SearchBtnOnWindow.click();
		driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+emailTemplateName+"')]/../../../..//input")).click();
	}
	
	public void clickOnSend()
	{
		Send.click();
	}
	public void ClickrelatedTo(WebDriver driver, String options)
	{
		relatedTo.click();
		driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+options+"')]")).click();
	}
	public void enterTextInSearchBtn(WebDriver driver,String leadname) throws InterruptedException
	{
		Thread.sleep(2000);
		//((JavascriptExecutor)driver).executeScript("arguments[0].value='Quote1'", SearchBtn);
		SearchBtn.sendKeys(leadname);
		//((JavascriptExecutor)driver).executeScript("arguments[0].click", Searchicon);
		WebElement Searchicon = driver.findElement(By.xpath("//button[@type='submit']"));
		UtilityClass.wait_until_element_invisible(driver, Searchicon);
		Searchicon.sendKeys(Keys.RETURN);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	public void clickOnEditBtn(WebDriver driver)
	{  // Actions act= new Actions(driver);
	//act.moveToElement(Edit).perform();
	//act.click().perform();
		//((JavascriptExecutor)driver).executeScript("arguments[0].click", Edit);
		//Edit.submit();
		WebElement element = driver.findElement(By.xpath("//button[@id='LBL_EDIT_BUTTON_TITLE']"));
		//element.sendKeys(Keys.RETURN);
		//or 
		element.sendKeys(Keys.ENTER);
	}
	
	
	public void selectApprovalStatus1(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(2000);
		
		//((JavascriptExecutor) driver).executeScript("arguments[0].click();", ApprovalStatus);
		WebElement element = driver.findElement(By.xpath("//input[@id='approval_status']"));
		element.click();
		//ApprovalStatus.click();
		WebElement approvalvalue = driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='Approved']"));	
		//((JavascriptExecutor) driver).executeScript("arguments[0].click();", approvalvalue);
		
		
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", approvalvalue);
		Thread.sleep(2000);
		approvalvalue.click();
		
	
	}
	
	public void scrollpageuptoSearchIconKnight(WebDriver driver)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",ApprovalStatus);
	}
	
	//--------------Duplicate Functionality-------------------//
	
	public String getOpportunity()
	{
		String OppName = Opportunity.getAttribute("value");
		return OppName;
	}
	public String getQuoteStage()
	{
		String Qstage = QuoteStage.getAttribute("value");
		return Qstage;
	}

	public String[] getValidUntil()
	{
		String ValidDate = GetValidUntil.getAttribute("value");
		String[] date1 = ValidDate.split(" ");
		return date1;
	}
	public String getInvoiceStatus()
	{
		String Istatus = InvoiceStatus.getAttribute("value");
		return Istatus;
	}
	public String getPaymentTerm()
	{
		String term = PaymentTerm.getAttribute("value");
		return term;
	}
	public String getApprovalStatus()
	{
		String Astatus = ApprovalStatus.getAttribute("value");
		return Astatus;
	}
	public String getApprovalIssue()
	{
		String AIssue = ApprovalIssue.getText();
		return AIssue;
	}
	public String getKnight()
	{
		String knight = Knight.getAttribute("value");
		return knight;
	}
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
	
	public String getQuantity()
	{
		String qut = ProductLineQuantity.getAttribute("Value");
		return qut;
	}
	
	public String getProducts()
	{
		String prod = Products.getAttribute("Value");
		return prod;
	}
	
	public String getProductLineListPrice()
	{
		String listPrice = ProductLineListPrice.getAttribute("Value");
		return listPrice;
	}
	
	public String getDiscount()
	{
		String discount = ProductLineDiscount.getAttribute("Value");
		return discount;
	}
	
	public String getDiscountType()
	{
		String discountType = ProductLineDiscountType.getAttribute("Value");
		return discountType;
	}
	
	public String getProductLineTax()
	{
		String tax = ProductLineTax.getAttribute("Value");
		return tax;
	}
	
	public String getProductDescription()
	{
		String des = ProductLineItemDescription.getText();
		return des;
	}
	
	public String getServiceLineName()
	{
		String name = ServiceLineName.getText();
		return name;
	}
	
	public String getServiceLineDiscount()
	{
		String discount = ServiceLineDiscount.getAttribute("Value");
		return discount;
	}
	
	public String getServiceLineDiscountType()
	{
		String discountType = ServiceLineDiscountType.getAttribute("Value");
		return discountType;
	}
	
	public String getServiceLineListPrice()
	{
		String listPrice = ServiceLineListPrice.getAttribute("Value");
		return listPrice;
	}
	
	public String getServiceLineTax()
	{
		String tax = ServiceLineTax.getAttribute("Value");
		return tax;
	}
	
	public String getShippingAmount()
	{
		String shippingAmount = ShippingAmount.getAttribute("Value");
		return shippingAmount;
	}
	
	public String getShippingTax()
	{
		String shippingTax = ShippingTax.getAttribute("Value");
		return shippingTax;
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
	
	public String getUnitPrice()
	{
		String unitprice = UnitPrice.getAttribute("value");
		return unitprice;
		
	}
	
	public String getServiceLineUnitPrice()
	{
		String unitprice = ServiceUnitPrice.getAttribute("value");
		return unitprice;
	}
	
	public String getTaxAmount()
	{
		String taxAmount = TaxAmount.getAttribute("value");
		return taxAmount;
		
	}
	
	public String getServiceLineTaxAmount()
	{
		String taxAmt = ServiceTaxAmount.getAttribute("value");
		return taxAmt;
	}
	public String getTotalProductPrice()
	{
		String totalprice = TotalProductPrice.getAttribute("value");
		return totalprice;
		
	}
	
	public String getServiceLineTotalPrice()
	{
		String totalprice = ServiceTotalProductPrice.getAttribute("value");
		return totalprice;
		
	}
	public String getProductLineItems()
	{
		String Quantity = getProductTitle.getText();
		return Quantity;
	}
	
	public String getServiceLineItems()
	{
		String Name = getServiceTitle.getText();
		return Name;
	}
	
	public String FinalGrandTotal()
	{
		String finaltotal = GrandTotalProductPrice.getAttribute("value");
		return finaltotal;
	}
	public float ConvertStringTofloat(String str)
	{
		 float val = 0;
	        System.out.println("String = " + str);
	  if(str.contains("%"))
	  {
		 String str1[] = str.split("%");
		 System.out.println("Pecentage: "+str1[0]);
		 str =str1[0];
	  }
	  else if(str.contains(","))
	  {
		  str = str.replace(",","");
	  }
	        // Convert the String into integer
	        try {
	            val = Float.parseFloat(str);
	            System.out.println(val);
	        }
	        catch (NumberFormatException e) {
	  
	            
	            System.out.println("Invalid String");
	        }
	        return val;
	}
	
	
	}
