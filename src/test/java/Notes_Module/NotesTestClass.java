package Notes_Module;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Library_Files.Base_Class;
import Library_Files.CommonFunctions;
import Library_Files.UtilityClass;
import Login_Page.Dashboard;
import POM_Documents_Module.Add_Documents;
import POM_Documents_Module.Duplicate_Document_Page;
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Duplicate_Page;
import POM_Lead_Module.Lead_ListView;
import POM_Notes_Module.Add_Notes;
import POM_Opportunities_Module.Add_Opportunities;
import POM_Quote_Module.Add_Quotes;
import POM_Task_Module.Add_Task;
import POM_Task_Module.Duplicate_Task_Page;

public class NotesTestClass extends Base_Class
{
	Dashboard dashboard;
	Add_Lead create_Lead;
	Lead_ListView list_View;
	Add_Task task;
	Add_Opportunities add_Opportunities;
	Add_Documents documents;
	Add_Notes notes;
	Duplicate_Document_Page duplicate_document;
	Duplicate_Page duplicate;
	Duplicate_Task_Page duplicate_task;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws InterruptedException
	{
		
		dashboard = new Dashboard(driver);
		create_Lead = new Add_Lead(driver);
		list_View = new Lead_ListView(driver);
		documents = new Add_Documents(driver);
		add_Opportunities= new Add_Opportunities(driver);
		task = new Add_Task(driver);
		notes = new Add_Notes(driver);
		duplicate_document = new Duplicate_Document_Page(driver);
		duplicate=new Duplicate_Page(driver);
		duplicate_task = new Duplicate_Task_Page(driver);
		
		//directed to the dash board page
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Notes");
		
		Thread.sleep(5000);
		dashboard.clickOnMenuOption(driver,"Notes");
		Thread.sleep(5000);
		dashboard.closeMenuOption();
	}
	
	//Create test case for create multiple Account
	@Test(groups={"Create", "Sanity"})
	public void CreateNotes() throws Exception 
	{
		//Create this test case in Extent Report
		 test= extent.createTest("Create Notes").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		 test.info("Create multiple notes");
		 for(int i=1;i<=14;i++)//Loop run up to 14 Product drop down value - Because for Target Related To field shows No Record defect.
			{
			 	
			 
			 Thread.sleep(5000);
			 list_View.clickOnAddButton();
			 Thread.sleep(5000);
			 
				SoftAssert soft=new SoftAssert();
			try {
					notes.selectContacts(driver, UtilityClass.fetchDataFromExcelSheet("Notes",i,0));
					task.clickRelatedTo(driver, UtilityClass.fetchDataFromExcelSheet("Notes",i,1), UtilityClass.fetchDataFromExcelSheet("Notes",i,2));
					//notes.selectMessage(driver, UtilityClass.fetchDataFromExcelSheet("Notes",i,3));
					String subject = task.enterSubject(UtilityClass.fetchDataFromExcelSheet("Notes",i,3));
					notes.selectKnight(driver, UtilityClass.fetchDataFromExcelSheet("Notes",i,6));
					documents.uploadFile(driver, UtilityClass.fetchDataFromExcelSheet("Notes",i,4));
				
					soft.assertNotNull(subject);
					
					create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Notes",i,5));
					String Note = duplicate.getDescription();
					soft.assertNotNull(Note);
					create_Lead.scrollpage(driver);
					create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Notes",i,7));
					
					create_Lead.clickOnSavebtn();
					test.info(subject+" Note is created successfully.");
					Thread.sleep(4000);
					add_Opportunities.backToListView();
					Thread.sleep(4000);
					list_View.scrolluptoAddBtn(driver);
					Thread.sleep(2000);
					soft.assertAll();
				}
	 				catch(NullPointerException e) 
				{
	 					System.out.println("NullPointerException thrown!");
				}
			
			}
		 
	}	 
	//Create test case for Edit Account
	@Test( groups={"Edit", "Sanity"})
	public void EditNote() throws InterruptedException, EncryptedDocumentException, IOException 
	{
			//Create this test case in Extent Report
			test= extent.createTest("Edit Note").assignAuthor("Komal")
										.assignCategory("Functional Test Case").assignDevice("Chrome");
								
			test.info("User should Edit Note Status");
			String input1="Note 3 created";
			list_View.enterTextInSearchBtn(driver,input1);
			Thread.sleep(5000);
								 	
			String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
			if(OppName.equals(input1))
			{
				list_View.clickOnName(driver,input1);
				Thread.sleep(5000);
				list_View.clickOnEditBtn();
				
				task.enterSubject(UtilityClass.fetchDataFromExcelSheet("Notes",2,3));
				notes.selectKnight(driver, UtilityClass.fetchDataFromExcelSheet("Notes",2,6));
				create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Notes", 1, 8));
				create_Lead.scrollpage(driver);
				create_Lead.clickOnSavebtn();
				String msg=list_View.EveryPageAlert();
				test.info(msg+ "Message showing on CRM");
				test.info(input1+ " record is updated");
			}
			else
			{
				System.out.println("Subject not matched");
				test.info(input1+ " note is not matched");
			}
		 
		}
		
		//Create test case for Delete Note From Edit Option
		@Test( groups = {"DeleteFromEditView", "Sanity"}, dependsOnMethods={"CreateNotes"})
		public void DeleteNoteFromEditOption() throws Exception 
		{
			CommonFunctions.DeleteRecordFromEditOption(dashboard, list_View, add_Opportunities,"Note 2 created","Notes");
			
		}
		
		
		//Create test case for Delete Note From List View
		@Test( groups = {"DeleteFromListView", "Sanity"}, dependsOnMethods={"CreateNotes"})
		public void DeleteNoteFromListView() throws InterruptedException, EncryptedDocumentException, IOException 
		{
			CommonFunctions.DeleteRecordFromListView(list_View, add_Opportunities, "Note 6 created","Notes" );
				
		}
			
		
		@Test( groups= {"Duplicate"})
		public void DuplicateNotes() throws InterruptedException, EncryptedDocumentException, IOException, ParseException
		{
			//Create this test case in Extent Report
			 test= extent.createTest("Verify Account Is Duplicate ?").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			 test.info("Verify Account is duplicate or not");
			 		
			SoftAssert soft=new SoftAssert();
			String input1="Note 1 created";
			list_View.enterTextInSearchBtn(driver,input1);
			list_View.clickOnName(driver,input1);
			list_View.menu(driver,"DUPLICATE");
			
			String uploadfile = UtilityClass.fetchDataFromExcelSheet("Notes",1, 4);
			System.out.println(uploadfile);
			String sub = uploadfile.substring(39, 48);
			String filename = sub.toUpperCase();
			
			System.out.println(filename);
			int j=0,k=0;
			String related = duplicate_task.getRelatedTo();
			String arr1[]= {notes.getContactsName(), related, duplicate_task.getRelatedToDynamic(related), duplicate_task.getSubject(),notes.getUploadedFileName(),duplicate.getDescription(), notes.getKnightValue()};
			
			String arr2[]= {UtilityClass.fetchDataFromExcelSheet("Notes",1, 0),UtilityClass.fetchDataFromExcelSheet("Notes",1, 1),UtilityClass.fetchDataFromExcelSheet("Notes",1, 2),
					UtilityClass.fetchDataFromExcelSheet("Notes",1, 3), filename, UtilityClass.fetchDataFromExcelSheet("Notes",1, 5),  UtilityClass.fetchDataFromExcelSheet("Notes",1, 6)};
			
			for(int i=0;i<7;i++)
			{
				
				soft.assertEquals(arr1[j], arr2[k],"Failed: Both result are different");
				
				j++;
				k++;
				
			}
			soft.assertAll();
			create_Lead.scrollpage(driver);
			create_Lead.clickOnSavebtn();
			test.info(input1+ " duplicate record is Created");
		}
		
		//Test case for Mass update Partner Contacts Module
		@Test( groups= {"MassUpdate", "Sanity"})
		public void MassUpdateNotesModule() throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
		{
		
			CommonFunctions.MassUpdateCaseModule(list_View, "Notes", "Note 5 created");	 	
		
		}
		
		//Test case for Export functionality
		@Test( groups = {"Export", "Sanity"})
		public void ExportRecordsForNotes() throws Exception
		{
			 //Call function from Common Function class
			CommonFunctions.ExportRecords(list_View, "Note 5 created", "Notes");
		}
		
}
