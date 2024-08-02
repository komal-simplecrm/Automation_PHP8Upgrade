package POM_Documents_Module;

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

public class Add_Documents 
{
	
	
	//Data members should be declare globally with access level private by using @FindBy annotation
		@FindBy(xpath="//input[@id='status_id']") private WebElement Status;
		@FindBy(xpath="//input[@id='document_name']") private WebElement Document_Name;
		@FindBy(xpath="//input[@id='revision']") private WebElement Revision;
		@FindBy(xpath="//input[@id='template_type']") private WebElement Document_type;
		@FindBy(xpath="//input[@id='is_template']") private WebElement Is_templateCheckBox;
		@FindBy(xpath="//button[@id='active_date']")private WebElement Publish_Date;
		@FindBy(xpath="//button[@id='exp_date']")private WebElement Exp_Date;
		@FindBy(xpath="//input[@id='category_id']") private WebElement Category;
		@FindBy(xpath="//input[@id='subcategory_id']") private WebElement Subcategory;
		@FindBy(xpath="//*[local-name()='svg' and @id='search-btn-related_doc_name']") private WebElement Related_doc;
		@FindBy(xpath="//div[@role='dialog']//input[@id='document_name']") private WebElement Document_name;
		@FindBy(xpath="//button[@form='relate-search-form']")private WebElement SearchOnWindow;
		@FindBy(xpath="//input[@id='contract_name']") private WebElement ContractName;
		@FindBy(xpath="//label[@id='filename']") private WebElement UploadFile;
		//@FindBy(xpath="//span[text()='Upload File Name']") private WebElement UploadFile;
		
		public Add_Documents(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		//Utilize within the methods with access level public        
		public void selectStatus(WebDriver driver, String status)
		{	                    
			Status.click();
			driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+status+"']")).click();
		}
		
		/*public void enterDocument_Name(String name)
		{	                                     
			Document_Name.sendKeys(name);
		}*/
		
		public void enterRevision(String revision)
		{	    
			String s = Keys.chord(Keys.CONTROL, "a");
			Revision.sendKeys(s);
			Revision.sendKeys(Keys.DELETE);
			Revision.sendKeys(revision);
		}
		
		public void selectDocument_type(WebDriver driver, String document_type)
		{	  
			Document_type.click();
			driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+document_type+"']")).click();
		}
		
		public void clickOnIs_templateCheckBox()
		{	                                     
			Is_templateCheckBox.click();
		}
		
		public String selectPublishDate(WebDriver driver, String date) throws Exception
		{	                                    
		String s = Keys.chord(Keys.CONTROL, "a");
		Publish_Date.sendKeys(s);
		Publish_Date.sendKeys(Keys.DELETE);
		Publish_Date.click();
		UtilityClass.wait_until_element_found(driver, Publish_Date);
		UtilityClass.selectDates(driver, date, "dd-MMM-yyyy");
		String datestart = Publish_Date.getAttribute("value");
		return datestart;
		}
		
		public String selectExpDate(WebDriver driver, String date) throws Exception
		{	                                    
		String s = Keys.chord(Keys.CONTROL, "a");
		Exp_Date.sendKeys(s);
		Exp_Date.sendKeys(Keys.DELETE);
		Exp_Date.click();
		UtilityClass.wait_until_element_found(driver, Exp_Date);
		UtilityClass.selectDates(driver, date, "dd-MMM-yyyy");
		String datestart = Exp_Date.getAttribute("value");
		return datestart;
		}
		
		public void selectCategory(WebDriver driver, String category)
		{	                                     
			Category.click();
			driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+category+"']")).click();
		}
		
		public void selectSubCategory(WebDriver driver, String subcategory)
		{	                                     
			Subcategory.click();
			driver.findElement(By.xpath("//ul[@role='listbox']//li[text()='"+subcategory+"']")).click();
		}
		
		public void enterRelated_document(WebDriver driver, String documentname) throws InterruptedException
		{	                  
			Related_doc.click();
			Document_name.sendKeys(documentname);
			SearchOnWindow.click();
			Thread.sleep(4000);
			WebElement document = driver.findElement(By.xpath("(//tbody//td[contains(@data-testid,'MuiDataTableBodyCell')]//p//a[text()='"+documentname+"'])[1]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",document);
			Thread.sleep(1000);
			document.click();
		
		}
		
		public void enterContractName(WebDriver driver, String Name)
		{	                                     
			ContractName.sendKeys(Name);
		}
		
		public void uploadFile(WebDriver driver, String filePath) throws AWTException, InterruptedException
		{	                                     
			UploadFile.click();
			Thread.sleep(5000);
			
			
			//Code for clicking on the image button that brings up the window dialog box
			 

			//Putting all the absolute paths of the pics to upload(here, 3 files)
			//String arr[] = {"D:\\Test Documents for Documents module\\download.jpg","D:\\Test Documents for Documents module\\images.jpg",
							//"D:\\Test Documents for Documents module\\Test Word Document.docx"};

			//Copying the path of the file to the clipboard     
			//StringSelection photo = new StringSelection(arr[0]+arr[1]+arr[2]); //Putting the path of the image to upload
			
			StringSelection photo = new StringSelection(filePath); 
			File file = new File(filePath);
			
			
			//File upload size must be less than 7 MB
			long expectedSizeInMB = 7;
			//long expectedSizeInBytes = 1024 * 1024 * expectedSizeInMB;

			
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

				//To Click on the "Open" button to upload files
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				 System.out.println("File is uploaded successfully");
				 System.out.println(sizeInkb+" KB");
				// test.info("File size is less than " + expectedSizeInMB + " MB");
			} else {
			 //  test.info("Not bigger than " + expectedSizeInMB + " MB");
			   System.out.println("Not bigger than " + expectedSizeInMB + " MB");
			}
			
			//String text = Document_Name.getAttribute("value");
			//return text;
						
		}
		
		
		
		
		
		
}
