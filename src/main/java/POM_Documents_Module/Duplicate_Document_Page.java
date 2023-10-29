package POM_Documents_Module;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Duplicate_Document_Page 
{
		//Data members should be declare globally with access level private by using @FindBy annotation
		@FindBy(xpath="//input[@id='status_id']") private WebElement Status;
		@FindBy(xpath="//input[@id='document_name']") private WebElement Document_Name;
		@FindBy(xpath="//input[@id='template_type']") private WebElement Document_type;
		@FindBy(xpath="//input[@id='is_template']") private WebElement Is_templateCheckBox;
		@FindBy(xpath="//input[@id='active_date']")private WebElement Publish_Date;
		@FindBy(xpath="//input[@id='exp_date']")private WebElement Exp_Date;
		@FindBy(xpath="//input[@id='category_id']") private WebElement Category;
		@FindBy(xpath="//input[@id='subcategory_id']") private WebElement Subcategory;
		@FindBy(xpath="//input[@id='related_doc_name']") private WebElement Related_doc;
		@FindBy(xpath="//input[@id='contract_name']") private WebElement ContractName;
		
		//Initialize the constructor with access level public using PageFactory class
		public Duplicate_Document_Page(WebDriver driver)
		{
					PageFactory.initElements(driver, this);
		}
		//Utilize within the methods with access level public
		public String getStatus()
		{
			String status = Status.getAttribute("value");
			return status;
		}
		public String getDocument_Name()
		{
			String document_Name = Document_Name.getAttribute("value");
			return document_Name;
		}
		public String getDocument_type()
		{
			String document_type = Document_type.getAttribute("value");
			return document_type;
		}
		public boolean Is_templateCheckBox()
		{
			boolean is_templateCheckBox = Is_templateCheckBox.isSelected();
			return is_templateCheckBox;
		}
		public String getPublish_Date()
		{
			String publish_Date = Publish_Date.getAttribute("value");
			//String[] date1 = publish_Date.split("-");
			return publish_Date;
		}
		public String[] getExp_Date()
		{
			String exp_Date = Exp_Date.getAttribute("value");
			String[] date1=exp_Date.split("-");
			return date1;
			
		}
		public String getCategory()
		{
			String category = Category.getAttribute("value");
			return category;
		}
		public String getSubCategory()
		{
			String SubCategory = Subcategory.getAttribute("value");
			return SubCategory;
		}
		public String getRelated_doc()
		{
			String related_doc = Related_doc.getAttribute("value");
			return related_doc;
		}
		public String getContractName()
		{
			String contract_name = ContractName.getAttribute("value");
			return contract_name;
		}
}
