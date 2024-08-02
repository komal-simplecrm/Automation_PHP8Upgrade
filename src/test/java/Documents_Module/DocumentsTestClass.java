package Documents_Module;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Library_Files.Base_Class;

import Library_Files.UtilityClass;
import Login_Page.Dashboard;
import POM_Documents_Module.Add_Documents;
import POM_Documents_Module.Duplicate_Document_Page;
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Duplicate_Page;
import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;

public class DocumentsTestClass extends Base_Class
{
	Dashboard dashboard;
	Add_Lead create_Lead;
	Lead_ListView list_View;
	Add_Documents documents;
	Add_Opportunities add_Opportunities;
	Duplicate_Document_Page duplicate_document;
	Duplicate_Page duplicate;
	
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws InterruptedException
	{
		dashboard = new Dashboard(driver);
		create_Lead = new Add_Lead(driver);
		list_View = new Lead_ListView(driver);
		documents = new Add_Documents(driver);
		add_Opportunities= new Add_Opportunities(driver);
		duplicate_document = new Duplicate_Document_Page(driver);
		duplicate=new Duplicate_Page(driver);
		
		//directed to the dash board page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Documents");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		dashboard.clickOnMenuOption(driver,"Documents");
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(4000);
	}
	
			//Create test case for create multiple Account
			@Test( groups={"Create", "Sanity"})
			public void CreateDocument() throws Exception 
			{
				//Create this test case in Extent Report
				 test= extent.createTest("Create Documents Records").assignAuthor("Komal")
						.assignCategory("Functional Test Case").assignDevice("Chrome");
				
				 test.info("Create multiple documents records");
				 for(int i=6;i<=8;i++)
					{
					 	
					 
					 Thread.sleep(5000);
					 list_View.clickOnAddButton();
					 	
					 	//Thread.sleep(5000);
						SoftAssert soft=new SoftAssert();
					try {
						
					
						//Thread.sleep(2000);
						documents.uploadFile(driver, UtilityClass.fetchDataFromExcelSheet("Documents",i,11));
						String documentName = duplicate_document.getDocument_Name();
						soft.assertNotNull(documentName);
						test.info(documentName+ " document is tring to upload.");
						documents.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("Documents",i,0));
						//documents.enterRevision(UtilityClass.fetchDataFromExcelSheet("Documents",i,1));
						documents.selectDocument_type(driver, UtilityClass.fetchDataFromExcelSheet("Documents",i,2));
						documents.clickOnIs_templateCheckBox();
						//documents.selectPublishDate(driver, UtilityClass.fetchDataFromExcelSheet("Documents",i,3));
						documents.selectExpDate(driver, UtilityClass.fetchDataFromExcelSheet("Documents",i,4));
						documents.selectCategory(driver, UtilityClass.fetchDataFromExcelSheet("Documents",i,5));
						documents.selectSubCategory(driver, UtilityClass.fetchDataFromExcelSheet("Documents",i,6));
						create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Documents",i,7));
						create_Lead.scrollpage(driver);
						documents.enterRelated_document(driver, UtilityClass.fetchDataFromExcelSheet("Documents",i,8));
						documents.enterContractName(driver, UtilityClass.fetchDataFromExcelSheet("Documents",i,9));
						create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Documents",i,10));
						
						create_Lead.clickOnSavebtn();
						list_View.getAlertMessage(documentName);
						Thread.sleep(7000);
						add_Opportunities.backToListView();
						Thread.sleep(4000);
						list_View.scrolluptoAddBtn(driver);
						Thread.sleep(4000);
					}
			 		catch(NullPointerException e) {
					System.out.println("NullPointerException thrown!");
					//create_Lead.clickOnSavebtn();
					//continue;
				}
					
			}
		}	 
				 
				@Test( groups={"Edit", "Sanity"})
				public void EditDocument() throws EncryptedDocumentException, IOException, Exception
				{
					
					//Create this test case in Extent Report
					 test= extent.createTest("Edit Document").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
					
					 
					 test.info("User should Edit Document revision and publish date");
						String input1="Test3.JPG";
						//Filter the Document
						Thread.sleep(5000);
						//list_View.clickSettingInListView(driver, "Filter","",input1,"Documents" );	
						list_View.clickOnFilter(driver, "",input1,"Documents");
						String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
						if(OppName.equals(input1))
						{
							Thread.sleep(5000);
							list_View.clickOnName(driver,input1);
							list_View.clickOnEditBtn();
							documents.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("Documents",1,0));
							documents.enterRevision(UtilityClass.fetchDataFromExcelSheet("Documents",1,1));
							documents.selectPublishDate(driver, UtilityClass.fetchDataFromExcelSheet("Documents",1,3));
							test.info(input1+ " Document is editted");
						}
						else
						{
							System.out.println("Documents Name not matched");
							test.info(input1+ " Documents name is not matched");
						}	
						
					}
				
				 
				//Test case for Delete Document From List View functionality
				@Test( groups= {"DeleteFromListView", "Sanity"}, dependsOnMethods={"CreateDocument"})
				public void DeleteDocumentFromListView() throws Exception
				{
					//Create this test case in Extent Report
					test= extent.createTest( "Record Is Deleted From List View ").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
					
					String input1="Test1.pdf";
					
					Thread.sleep(5000);
					//Filter the Document
					//list_View.clickSettingInListView(driver, "Filter","",input1,"Documents" );	 	
					list_View.clickOnFilter(driver,"",input1,"Documents" );
					String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
					if(OppName.equals(input1))
					{
					
						list_View.selectCheckBox(driver, input1);
						Thread.sleep(5000);
						list_View.clickOnDelete();
						
						String msg=list_View.EveryPageAlert();
						 test.info(msg+ " Message showing on CRM");
						test.info(input1+ " Record is Deleted");
					}else
					{
						System.out.println("Record Name not matched");
						test.info(input1+ " record is not matched");
					}
					
					
					
				}
				 
				//Test case for Delete Document From Edit Option functionality
				@Test( groups= {"DeleteFromEditView", "Sanity"}, dependsOnMethods={"CreateDocument"})
				public void DeleteDocumentFromEditOption() throws Exception
				{
					//Create this test case in Extent Report
					test= extent.createTest( "Record Is Deleted From Edit View ").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
					
					String input1="Test5.txt";
					//Filter the Document
					Thread.sleep(5000);
					//list_View.clickSettingInListView(driver, "Filter","",input1,"Documents" );
					list_View.clickOnFilter(driver,"",input1,"Documents" );
					String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
					if(OppName.equals(input1))
					{
						Thread.sleep(5000);
						list_View.clickOnName(driver,input1);
						dashboard.clickOnMenuOption();
						//UtilityClass.ZoomIn();
						list_View.menu(driver,"DELETE");
						//UtilityClass.ZoomOut();
						list_View.confirmDelete();
						String msg=list_View.EveryPageAlert();
						 test.info(msg+ " Message showing on CRM");			
						 test.info(input1+ " record is deleted");
					}
					else {
						System.out.println("Record Name not matched");
						test.info(input1+ " record is not matched");
					}
				}
				
				@Test( groups={"Duplicate"})
				public void DuplicateDocument() throws InterruptedException, EncryptedDocumentException, IOException, AWTException
				{	
					//Create this test case in Extent Report
					test= extent.createTest("Verify Document Is Duplicate?").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
					
					test.info("Verify document is duplicate or not");
					SoftAssert soft=new SoftAssert();
					
					String input1="Test3.JPG";
										
					//Filter the Document
					Thread.sleep(5000);
					//list_View.clickSettingInListView(driver, "Filter","",input1,"Documents" );	 	
					list_View.clickOnFilter(driver,"",input1,"Documents" );
					String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
					if(OppName.equals(input1))
					{
						list_View.clickOnName(driver,input1);
					
					Thread.sleep(5000);
					list_View.menu(driver,"DUPLICATE");
					
					//get Exp Date 
					String[] date = duplicate_document.getExp_Date();
					String month=date[1];
					//convert date string to int
					int m=Integer.parseInt(month);  
					//get month name format MMM
					String MonthName=UtilityClass.convertMonth(m);
					//combine date dd-MMM-yyyy
					String ExactexpDate=date[0]+"-"+MonthName+"-"+date[2];
					
					//get Publish Date 
					String pubdate = duplicate_document.getPublish_Date();
					//String pubmonth=pubdate[1];
					
					//convert date string to int
					//int m1=Integer.parseInt(pubmonth);  
					//get month name format MMM
					//String pubMonthName=UtilityClass.convertMonth(m1);
					//combine date dd-MMM-yyyy
					//String ExactpubDate=pubdate[0]+"-"+pubMonthName+"-"+pubdate[2];
					
					//If the document is created today then to compare Publish date with current date only at that time publish date field assertion is pass.
					LocalDate pubDate = java.time.LocalDate.now();
					 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");

					    String TodaysDate = pubDate.format(myFormatObj);
					    System.out.println("After formatting: " + TodaysDate);
					
					int j=0,k=0;
					String arr1[]= {duplicate_document.getStatus(),duplicate_document.getDocument_Name(),duplicate_document.getDocument_type(),
							pubdate,ExactexpDate,duplicate_document.getCategory(),duplicate_document.getSubCategory(),
							duplicate.getDescription(),duplicate_document.getRelated_doc(),duplicate_document.getContractName()};
					String arr2[]= {UtilityClass.fetchDataFromExcelSheet("Documents",2, 0),UtilityClass.fetchDataFromExcelSheet("Documents",2, 8),UtilityClass.fetchDataFromExcelSheet("Documents",2, 2),
							TodaysDate,UtilityClass.fetchDataFromExcelSheet("Documents",2, 4),UtilityClass.fetchDataFromExcelSheet("Documents",2, 5),
							UtilityClass.fetchDataFromExcelSheet("Documents",2, 6),UtilityClass.fetchDataFromExcelSheet("Documents",2, 7),UtilityClass.fetchDataFromExcelSheet("Documents",2, 8),
							UtilityClass.fetchDataFromExcelSheet("Documents",2, 9)};
					
					for(int i=0;i<=9;i++)
					{
						
						soft.assertEquals(arr1[j], arr2[k],"Failed: Both result are different");
						
						j++;
						k++;
						
					}
					soft.assertAll();
					create_Lead.clickOnSavebtn();
					test.info(input1+ " duplicate record is Created");
				}
					
			}
				
				//Test case for Export functionality
				@Test(groups = {"Export", "Sanity"})
				public void ExportRecordsForDocuments() throws Exception
				{
					//Create this test case in Extent Report
					test= extent.createTest( "Export functionality for Documents.").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
					
					String input1="Test5.txt";
					//Filter the Document
					Thread.sleep(5000);
					//list_View.clickSettingInListView(driver, "Filter","",input1,"Documents" );
					list_View.clickOnFilter(driver,"",input1,"Documents");
					Thread.sleep(5000);
					list_View.ExportAllRecords(driver, input1, "Documents");
				}
					
				
				//Test case for Mass update functionality
				@Test(  groups={"MassUpdate", "Sanity"})
				public void MassUpdateDocuments() throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
				{
					//Create this test case in Extent Report
					 test= extent.createTest("Mass Update Documents Module").assignAuthor("Komal")
								.assignCategory("Functional Test Case").assignDevice("Chrome");
					String input1="Test5.txt";
					list_View.clickOnFilter(driver,"",input1,"Documents");
					Thread.sleep(5000);
					list_View.massUpdate(driver, "Test5.txt", "Rahul Thakre" );
					
				}	
				
				
}
