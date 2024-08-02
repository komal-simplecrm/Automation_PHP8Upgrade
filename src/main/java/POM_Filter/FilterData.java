package POM_Filter;


import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import Library_Files.Base_Class;
import Library_Files.CommonFunctions;
import Library_Files.UtilityClass;
import POM_Account_Module.Add_Account;
import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Duplicate_Opp_Page;

public class FilterData extends Base_Class
{
	static SoftAssert soft = new SoftAssert();
	Add_Account add_Account = new Add_Account(driver);
	static Duplicate_Opp_Page duplicate_opp = new Duplicate_Opp_Page(driver);
	static Lead_ListView list_View = new Lead_ListView(driver);
	//Data members should be declare globally with access level private by using FindBy annotation.
	
	@FindBy(xpath="//button[@form='filter-form']") private WebElement SearchBtnOnFilter;
	@FindBy(xpath="//span[text()='Clear Filter']") private WebElement ClearFilterBtnOnFilter;
	@FindBy(xpath="//div[contains(@class,'MuiToolbar')]//span[text()='Clear Filter']") private WebElement ClearFilterBtnOnListView;
	@FindBy(xpath="//button[@title='Close Filter']") private WebElement CloseFilterIconOnFilter;
	@FindBy(xpath="//div[@id='sort_column']") private WebElement sortColumn;
	
	
	//button[@title='Close Filter']
	//Initialize the constructor with access level public using PageFactory class
	public FilterData(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Function for verifying the data enter on the filter pop-up the same data is showing in the list view or not
	//But this function is only for text without link and text color is black.
	//This function is not verify Date fields
	public static void getListViewData( String columnName, String dataFromExcelSheet) throws InterruptedException, AWTException, ParseException
	{
		System.out.println("Enter in getListViewData function. ");
		System.out.println("Column Name"+columnName);
		test.info("Enter in getListViewData function.");
		try {
		WebElement element = driver.findElement(By.xpath("//span[contains(@class,'MUIDataTableHeadCell')]//div[text()='"+columnName+"']"));
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
		}
		catch(NoSuchElementException e)
		{
			System.out.println("No record found");
		}
		String ColumnName = columnName;
		if(columnName.contains("Any"))
		{
			
			ColumnName = columnName.replace("Any ", "");
			if(ColumnName.equalsIgnoreCase("Address"))
			{
				
				ColumnName = ColumnName.replace(ColumnName, "Street");
				System.out.println("For Address column name changed to street."+ColumnName);
			}

			System.out.println("Column Name to be searched: " + ColumnName);
			
		}
		
		else if(columnName.contains("First Name")||columnName.contains("Last Name"))
		{
			ColumnName = columnName.replace(columnName, "Name");
		}
		
		int count = 0 ;
		int linkcount = 0;
		List<WebElement> link = null;
		 if(ColumnName.equals("Name"))
		 {
			link = driver.findElements(By.xpath("//td[contains(@data-testid,'MuiDataTableBodyCell')]//div[text()='"+ColumnName+"']/..//a[@variant='body2']"));
			
		 }
		 else 
		 {
			 //Data which have the link in it
			 link = driver.findElements(By.xpath("//td[contains(@data-testid,'MuiDataTableBodyCell')]//div[contains(text(),'"+ColumnName+"')]/..//a[@variant='body2']"));
		//link = driver.findElements(By.xpath("//td[contains(@data-testid,'MuiDataTableBodyCell')]//div[text()='"+ColumnName+"']/..//a[@variant='body2']"));
		}
		 Thread.sleep(2000);
		
		linkcount = link.size();
		System.out.println("link count: "+linkcount);
		if(linkcount > 0)
		{
			for(int i=1; i<=linkcount;i++)
			{
				
				WebElement datalistview = null;
				String listviewdata = null;
				try {
					if(/*ColumnName.contains("First Name")||ColumnName.contains("Last Name")||*/ColumnName.contains("Name"))
					 {
						datalistview = driver.findElement(By.xpath("(//td[contains(@data-testid,'MuiDataTableBodyCell')]//div[text()='"+ColumnName+"']/..//a[@variant='body2'])["+i+"]"));
					 }
					else {
						datalistview = driver.findElement(By.xpath("(//td[contains(@data-testid,'MuiDataTableBodyCell')]//div[contains(text(),'"+ColumnName+"')]/..//a[@variant='body2'])["+i+"]"));
					}
					Thread.sleep(2000);
					listviewdata = datalistview.getText();
					 if(listviewdata.equals(""))
					{
						 listviewdata = datalistview.getAttribute("innerText");
						 if(listviewdata.equals(""))
							{
							 listviewdata = datalistview.getAttribute("textContent");
							}
					}
				
					}
					catch(Exception e)
					{
						
						System.out.println("column is not matched.");
					
					
					}
				System.out.println("Data in list view: " +listviewdata);
				
				//This is used for multiselect fields
				if (dataFromExcelSheet.contains(","))
				{
					System.out.println("Enter in comma");
				    	String[] parts = dataFromExcelSheet.split(",");
				    	String firstPart = parts[0].trim();
				    	String secondPart = parts[1].trim();
				    	if(firstPart.equalsIgnoreCase(listviewdata))
				    	{
				    		soft.assertEquals(listviewdata,firstPart, "Failed: "+listviewdata+" not matched with "+secondPart+" .");
				    	}
				    	else
				    	{
				    		soft.assertEquals(listviewdata,secondPart, "Failed: "+listviewdata+" not matched with "+secondPart+" .");
				    	}
				
				} 
				else if (listviewdata == "null" || dataFromExcelSheet=="null")
				{
					System.out.println("data not present.");
				}
				else {
						if(columnName.contains("First Name")/*||ColumnName.contains("Name")*/)
						{
							String[] ListViewData = listviewdata.split(" ");
							soft.assertEquals(ListViewData[0], dataFromExcelSheet);
						}
						else if(columnName.contains("Last Name"))
						{
							String[] ListViewData = listviewdata.split(" ");
							soft.assertEquals(ListViewData[1], dataFromExcelSheet);
						}
						else 
						{
							soft.assertEquals(listviewdata, dataFromExcelSheet);
						}
					}
				
			}
		}
		
		else 
		{
			List<WebElement> data = driver.findElements(By.xpath("//td[contains(@data-testid,'MuiDataTableBodyCell')]//div[contains(text(),'"+ColumnName+"')]/following-sibling::div"));
			count = data.size();
			System.out.println("count: "+count);
			
			if(count > 0)
			{
				for(int i=1; i<=count;i++)
				{
					//Sibling xpath used
					
					String listviewdata = null;
					try {
						WebElement datalistview = driver.findElement(By.xpath("(//td[contains(@data-testid,'MuiDataTableBodyCell')]//div[contains(text(),'"+ColumnName+"')]/following-sibling::div)["+i+"]"));
						Thread.sleep(2000);
						listviewdata = datalistview.getText();
						 if(listviewdata.equals(""))
						{
							 listviewdata = datalistview.getAttribute("innerText");
							 if(listviewdata.equals(""))
								{
								 listviewdata = datalistview.getAttribute("textContent");
								}
						}
					
						}
						catch(Exception e)
						{
							
							System.out.println("column is not matched.");
						
						
						}
				
					System.out.println("Data in list view: " +listviewdata);
					//This is used for Date fields
					if(ColumnName.contains("Date"))
					{
						System.out.println("Enter in Date 2");
						String[] listviewData = listviewdata.split(" ");
						String Date = listviewData[0].trim();
					    //String Time = listviewData[1].trim();
						
						//Convert date in to string[] to pass in the dateCompare(String[]) which contains only string array
					    //for that convert string to string[]
					    //dateComare() function is used to convert month into month name
						String[] DateListView = new String[] { Date };
					    String DateFromListView = duplicate_opp.dateCompare(DateListView);
					    
						String[] parts = dataFromExcelSheet.split(",",2);
					    //String firstPart = parts[0].trim();
					    String secondPart = parts[1].trim();
					    
					    
					    //System.out.println("Date From List View: "+DateFromListView);
				    	//System.out.println("second Part: "+secondPart);
					    if(secondPart.equals(DateFromListView))
					    {
					    	
					    	soft.assertEquals(secondPart,DateFromListView, "Failed: "+secondPart+" not matched with "+DateFromListView+" .");
					    }
					    else
					    {
					    	System.out.println("Dates does not matched.");
					    }
					 
					}
					//This is use for Multiselect fields
					else if (dataFromExcelSheet.contains(",")) {
					    String[] parts = dataFromExcelSheet.split(",");
					    String firstPart = parts[0].trim();
					    String secondPart = parts[1].trim();
					    if(firstPart.equalsIgnoreCase(listviewdata))
					    {
					    	soft.assertEquals(listviewdata,firstPart, "Failed: "+listviewdata+" not matched with "+secondPart+" .");
					    }
					    else
					    {
					    	soft.assertEquals(listviewdata,secondPart, "Failed: "+listviewdata+" not matched with "+secondPart+" .");
					    }
					    
					} else {
						
						soft.assertEquals(listviewdata,dataFromExcelSheet);
					}
					
				}
			}
			/*else if(linkcount == 0 && count == 0)
			{
				System.out.println("Record does not exist.");
			}*/
			else 
			{
				System.out.println(ColumnName+" column does not exist in list view.");
				
				//Call the function to arrange the column to be displayed in list view
				list_View.ColumnFunctionality(ColumnName, dataFromExcelSheet);
				//Check the column is added in the list view or not
				
			}
			
		}
		soft.assertAll();
		
	}
	
	
	
	
	
	
	
	
	//Filter functionality for Text field
	public void getTextFieldFromFilter(WebDriver driver, /*String data,*/ int RowIndex, int HeadingRowIndex, int k) throws EncryptedDocumentException, IOException, InterruptedException, AWTException, ParseException
	{
		
			
			ClearFilterBtnOnFilter.click();
			
			
			Thread.sleep(2000);
			System.out.println("Enter in TextField");
			test.info("This is Text field.");
			//Get id of that element
			
			WebElement ID = driver.findElement(By.xpath("//div[@datafieldindex='field-"+k+"']//input"));
			String id = ID.getAttribute("id");
			System.out.println("Id of fields: "+id);
			//Get label of field to compare with excel sheet column name
			WebElement fieldLabel = driver.findElement(By.xpath("//div[@datafieldindex='field-"+k+"']//label"));
			String FieldLabel  = CommonFunctions.GetText(fieldLabel);
			System.out.println("Id of "+FieldLabel+" field: "+id);
			int columnNo = 0;
			//Call the function to get the excel sheet data with the cell index
			Object Data[] = getExceldata(columnNo, FieldLabel,HeadingRowIndex);
			Object cellIndex = Data[0];
			int cell = (Integer) cellIndex;
			Object Exceldata = Data[1];
			String ExcelData = String.valueOf(Exceldata);
			
			System.out.println("Cell index from sheet:" +cell);
			System.out.println("Excel data from sheet:" +ExcelData);
			//Compare the field label with cell headings in excel sheet
			if(ExcelData.equalsIgnoreCase(FieldLabel))
			{
				//add logs for Extent report
				test.info(ExcelData+" column name in excel sheet "+FieldLabel+" column name is matched.");
				
				if(ExcelData.equalsIgnoreCase("Save filter as"))
				{
					System.out.println("Skipped");
				}
				else
				{
					//Scroll upto that field on Filter Panel
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",fieldLabel);
					//Enter data in that field
					String ExcelDatatoBeSend = UtilityClass.fetchDataFromExcelSheet("Filter", RowIndex, cell);
					
					//add logs for Extent report
					test.info(ExcelDatatoBeSend+" data enter in the "+FieldLabel+" field.");
					driver.findElement(By.id(id)).sendKeys(ExcelDatatoBeSend);
						
						//This is added to check the Save Filter as with using text enter in Name field (first field on the filter pop-up) functionality
						if(k==0)
						{
							int t = 1;
							String MyFilter1;
							driver.findElement(By.id("text")).sendKeys("Test Filter By Automation");
							SearchBtnOnFilter.click();
							Thread.sleep(4000);
							driver.findElement(By.xpath("//span[text()='My Filter']")).click();
							List<WebElement> myFilter=driver.findElements(By.xpath("//ul[contains(@class,'MuiList-dense')]//li//span[contains(@class,'displayBlock')]"));
							WebElement MyFilter = driver.findElement(By.xpath("(//ul[contains(@class,'MuiList-dense')]//li//span[contains(@class,'displayBlock')])["+t+"]"));
							MyFilter1 = MyFilter.getText();
							System.out.println("My Filter: "+MyFilter1);
							for(WebElement i: myFilter)
							{
								MyFilter1 = i.getText();
								System.out.println("My Filter1: "+MyFilter1);
								if(MyFilter1.equalsIgnoreCase("Test Filter By Automation"))
								{
									Assert.assertEquals(MyFilter1, "Test Filter By Automation", "Filter not saved successfully.");
									System.out.println("t : "+t);
									driver.findElement(By.xpath("(//ul[contains(@class,'MuiList-dense')]//li//span[contains(@class,'displayBlock')])["+t+"]")).click();
									//break;
								}
								else {
									System.out.println("Filter not saved successfully.");
								}
								t++;
							}
							
							
						}
						else 
						{
							SearchBtnOnFilter.click();
							Thread.sleep(4000);
							
						}
						//This is used to check the Filter is apply to he correct field we send or not in List view the Tag is dislayed which contains the Field name and data passed in it.
						//This functionality is removed that code is commented
						/*WebElement labelOnListView = driver.findElement(By.xpath("//span[contains(@class,'MuiChip-label')]"));
						Thread.sleep(4000);
						String Label = labelOnListView.getText();
						String Data1 = FieldLabel+" : "+ExcelDatatoBeSend;
						System.out.println("Filter data label on List view"+Data1);
						Assert.assertEquals(Label, Data1,"Failed: Inserted data does not matched.");
						/*WebElement name = driver.findElement(By.xpath("//tbody//td[@data-tableid='ListViewTable']//a[text()='"+data+"']"));
						Name = name.getText();
						System.out.println("Account name in the List View: "+Name);*/
						
						//Close the filter panel so that all column should be apear in List view.
						CloseFilterIconOnFilter.click();
						Thread.sleep(2000);
						//Call the function to verify correct data should be filtered.
						getListViewData( FieldLabel, ExcelDatatoBeSend);
						
						//getListViewData(FieldLabel, data);
						Thread.sleep(2000);
						ClearFilterBtnOnListView.click();
						//Click on filter button
						Thread.sleep(2000);
						list_View.ClickOnApplyFilter();
						//System.out.println("Name:  "+Name);
						//soft.assertEquals(Name, "IDBI Finance");
				}
			}else
				{
					System.out.println("Field does not exist.");
				}
			// j++;
			// k++;
			
		//}
		soft.assertAll();
		
		
	}
	
	// Get data from excel sheet for that first need to compare column heading with field labels and the get the data of that field
	public Object[] getExceldata(int columnNo, String FieldLabel, int HeadingRowIndex) throws EncryptedDocumentException, IOException 
	{
		
		String exceldata = UtilityClass.fetchDataFromExcelSheet("Filter", HeadingRowIndex, columnNo);
		System.out.println("Field label on Filter pop-up: "+FieldLabel);
		System.out.println("Excel column: "+exceldata);
		Object[] data = new Object[2];
		
		if(exceldata.equalsIgnoreCase(FieldLabel))
		{
			
			
			System.out.println("Excel column matched and return the value: "+exceldata);
			data[0] = columnNo; 
			data[1] = exceldata;
			return data;
			
		}

		else 
		{
			System.out.println("Excel data and field label does not matched.");
			
		}
		//To stop recursion function
		if(exceldata.equalsIgnoreCase(""))
		{
			System.out.println("Excel data: "+exceldata);
			System.out.println("No columns are available next.");
		}
		columnNo++;
		System.out.println("column No: "+columnNo);
		return getExceldata(columnNo, FieldLabel, HeadingRowIndex);
		
		
	}
	
	//Filter functionality for Multi-select fields
	public void getMultiSelectFromFilter(WebDriver driver, /*String data,*/ int RowIndex,  int HeadingRowIndex, int k) throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException 
	{
		//String Name = null;
			//add logs for Extent report
			test.info("This is multiselect field.");
			Thread.sleep(2000);
			
			//Get id of that element
			WebElement ID = driver.findElement(By.xpath("//div[@datafieldindex='field-"+k+"']//input"));
			String id = ID.getAttribute("id");
			System.out.println("Id of fields: "+id);
			
			//Get label of field to compare with excel sheet column name
			WebElement fieldLabel = driver.findElement(By.xpath("//div[@datafieldindex='field-"+k+"']//label"));
			String FieldLabel  = fieldLabel.getText();
			System.out.println("Id of "+FieldLabel+" field: "+id);
			
			//Call the function to get excel data from excel sheet with cell number
			int columnNo = 0;
			Object Data[] = getExceldata(columnNo, FieldLabel, HeadingRowIndex);
			Object cellIndex = Data[0];
			int cell = (Integer) cellIndex;
			Object Exceldata = Data[1];
			String ExcelData = String.valueOf(Exceldata);
			
			System.out.println("Cell index from sheet:" +cell);
			System.out.println("Excel data from sheet:" +ExcelData);
			
			if(ExcelData.equalsIgnoreCase(FieldLabel))
			{
				//add logs for Extent report
				test.info(ExcelData+" column name in excel sheet "+FieldLabel+" column name is matched.");
				
				//Get data from excel sheet
				String dataexcel = UtilityClass.fetchDataFromExcelSheet("Filter", RowIndex, cell);
				String [] data1 = dataexcel.split(","); 
				System.out.println("dataexcel: " +dataexcel);
				
				//add logs for Extent report
				test.info(dataexcel+" data enter in the "+FieldLabel+" field.");
				//Scroll upto that field on Filter Panel
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",fieldLabel);
				for(int y=0; y<data1.length; y++)
				{
					
					driver.findElement(By.xpath("//form[@id='filter-form']//input[@id='"+id+"']")).click();
					
					System.out.println("data1["+y+"]: " +data1[y]);
					driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+data1[y]+"')]")).click();
					
					System.out.println("RowIndex: " +RowIndex);
				}
					SearchBtnOnFilter.click();
				Thread.sleep(4000);
				//Close the filter panel so that all column should be apear in List view.
				CloseFilterIconOnFilter.click();
				//call the function to verify the excel data and list data
				getListViewData( FieldLabel, dataexcel);
				Thread.sleep(2000);
				//ClearFilterBtnOnListView.click();
				list_View.ClickOnApplyFilter();
				
				Thread.sleep(2000);
			
			}else
			{
				System.out.println("Field does not exist.");
			}
				
			soft.assertAll();
		}

	//Combine all the types of field functions in one function 
	public void typeOfField(WebDriver driver,/*String data,*/ int RowIndex,  int HeadingRowIndex ) throws Exception
	{
		
		List<WebElement> label = driver.findElements(By.xpath("//div[contains(@datafieldindex,'field')]"));
		
		String fieldType = null;
		int k = 0;
		for (WebElement i : label) 
		{	
			int j=1;
			System.out.println("Address: "+label);
			System.out.println("i: "+i);
			
			try {
				System.out.println("k in type:"+k);
				WebElement element = driver.findElement(By.xpath("//div[@datafieldindex='field-"+k+"']"));
			fieldType = element.getAttribute("datacustomfieldtype");
			System.out.println("fieldType: "+fieldType);
			Thread.sleep(2000);
			}
			catch (NoSuchElementException e)
			{
				//System.out.println("StaleElementReferenceException");
				System.out.println("NoSuchElementException");
				System.out.println("No more fields are available on he filter");
				test.info("No more fields are available on he filter");
				Thread.sleep(5000);
				break;
			}	
				
				Thread.sleep(2000);
				if(fieldType.equalsIgnoreCase("name")||fieldType.equalsIgnoreCase("varchar")||fieldType.equalsIgnoreCase("url"))
				{
					System.out.println("Enter in text field function");
					
					getTextFieldFromFilter(driver, /*data,*/ RowIndex, HeadingRowIndex,k);
				}
				else if(fieldType.equalsIgnoreCase("multienum"))
				{
				
					System.out.println("Enter in multiselect field function");
					getMultiSelectFromFilter(driver, /*data,*/  RowIndex, HeadingRowIndex,k);
				}
				else if(fieldType.equalsIgnoreCase("datetimecombo"))
				{
				
					System.out.println("Enter in Date Field On Filter1 field function");
					DateFieldOnFilter1(driver,/*data,*/RowIndex, HeadingRowIndex, k);
				
				}
				else if(fieldType.equalsIgnoreCase("null"))
				{
				
					System.out.println("Skipped the sub date fields.");
				
				}
				else if(fieldType.equalsIgnoreCase("enum"))
				{
					EnumOnFilter(driver,/*data,*/RowIndex, HeadingRowIndex, k);
				}
				else if(fieldType.equalsIgnoreCase("relate"))
				{
					ClearFilterBtnOnFilter.click();
					relateField(driver, RowIndex, HeadingRowIndex, k);
				}
			k++;
		j++;
		}
	
	}
	
	
	
	
	// Filter functionality for Date fields 
	public void DateFieldOnFilter1(WebDriver driver, /*String data,*/ int RowIndex, int HeadingRowIndex , int k) throws Exception
	{
	
			//add logs for Extent report
			test.info("This is Date field with multiple drop down conditions ");
			
			Thread.sleep(2000);
			//Get label of field to compare with excel sheet column name
			WebElement fieldLabel = driver.findElement(By.xpath("//div[@datafieldindex='field-"+k+"']//label"));
			String FieldLabel  = fieldLabel.getText();
			System.out.println("Field Label" +FieldLabel);
			
			WebElement ID = driver.findElement(By.xpath("//div[@datafieldindex='field-"+k+"']//input"));
			String id = ID.getAttribute("name");
			
			System.out.println("Name: "+id);
			
			int first = id.indexOf("_");
			int second = id.indexOf("_", first + 1);
			String ids_sub_part = id.substring(0,second);
			
			System.out.println("Ids first two words: "+ids_sub_part);
			//Call the function to get excel data from excel sheet with cell number
			Object Data[] = getExceldata(k, FieldLabel, HeadingRowIndex);
			Object cellIndex = Data[0];
			int cell = (Integer) cellIndex;
			Object Exceldata = Data[1];
			String ExcelData = String.valueOf(Exceldata);
			
			System.out.println("Cell index from sheet:" +cell);
			System.out.println("Excel data from sheet:" +ExcelData);
			
			//Scroll upto that field on Filter Panel
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",fieldLabel);
			
			String dataexcel = UtilityClass.fetchDataFromExcelSheet("Filter", RowIndex, cell);
			String [] data1 = dataexcel.split(","); 
			System.out.println("dataexcel: " +dataexcel);
			String option = data1[0];
			System.out.println("data1[0]: " +option);
			System.out.println("data1[1]: " +data1[1]);
			
			driver.findElement(By.xpath("//div[@id='"+id+"']")).click();
			
			
			WebElement dropdownoption = driver.findElement(By.xpath("//ul[@role='listbox']//li//span[contains(text(),'"+option+"')]"));
			dropdownoption.click();
			Thread.sleep(2000);
			if(option.equalsIgnoreCase("Is Between"))
			{
				//add logs for Extent report
				test.info("Is Between value is selected in drop down.");
				test.info(data1[1]+" filter record is between "+data1[2]);
				WebElement datePicker = driver.findElement(By.xpath("//button[@id='start_range_"+ids_sub_part+"']"));
				datePicker.click();
				UtilityClass.wait_until_element_found(driver, datePicker);
				UtilityClass.selectDates(driver, data1[1], "dd-MMM-yyyy");
			
				
			
				WebElement datePicker1 = driver.findElement(By.xpath("//button[@id='end_range_"+ids_sub_part+"']"));
				datePicker1.click();
				UtilityClass.wait_until_element_found(driver, datePicker1);
				UtilityClass.selectDates(driver, data1[2], "dd-MMM-yyyy");
				
			}
			else if(option.equals("Equals")||option.equals("Not On")||option.equals("After")||option.equals("Before"))
			{
				//add logs for Extent report
				test.info(option+" value is selected in drop down.");
				test.info(data1[1]+" filter record as date .");
				
				WebElement datePicker = driver.findElement(By.xpath("//button[@id='range_"+ids_sub_part+"']"));
				datePicker.click();
				UtilityClass.wait_until_element_found(driver, datePicker);
				UtilityClass.selectDates(driver, data1[1], "dd-MMM-yyyy");
			}
				
				SearchBtnOnFilter.click();
				Thread.sleep(4000);
				//Close the filter panel so that all column should be apear in List view.
				CloseFilterIconOnFilter.click();
				getListViewData( FieldLabel, dataexcel);
				Thread.sleep(2000);
				ClearFilterBtnOnListView.click();
				Thread.sleep(2000);
				list_View.ClickOnApplyFilter();
					
	}
	
	//Function created for only Date field on the filter pop-up
	public void DateFieldOnFilter(int HeadingRowIndex, int k, String data) throws Exception
	{
		//add logs for Extent report
		test.info("This is date field.");
		
		//Get label of field to compare with excel sheet column name
		WebElement fieldLabel = driver.findElement(By.xpath("//div[@datafieldindex='field-"+k+"']//label"));
		String FieldLabel  = fieldLabel.getText();
		System.out.println("Field Label" +FieldLabel);
		
		WebElement ID = driver.findElement(By.xpath("//div[@datafieldindex='field-"+k+"']//input"));
		String id = ID.getAttribute("id");
		
		System.out.println("id: "+id);
		//Call the function to get excel data from excel sheet with cell number
		Object Data[] = getExceldata(k, FieldLabel, HeadingRowIndex);
		Object cellIndex = Data[0];
		int cell = (Integer) cellIndex;
		Object Exceldata = Data[1];
		String ExcelData = String.valueOf(Exceldata);
		
		System.out.println("Cell index from sheet:" +cell);
		System.out.println("Excel data from sheet:" +ExcelData);
		
		if(FieldLabel.equalsIgnoreCase(ExcelData))
		{
			//add logs for Extent report
			test.info(ExcelData+" column name in excel sheet "+FieldLabel+" column name is matched.");

			//add logs for Extent report
			test.info(data+" data enter in the "+FieldLabel+" field.");
			WebElement datepicker = driver.findElement(By.xpath("//button[@id='"+id+"']"));
			datepicker.click();
			UtilityClass.wait_until_element_found(driver, datepicker);
			UtilityClass.selectDates(driver, data, "dd-MMM-yyyy");
			
		}
		else
		{
			System.out.println("Field does not exist.");
		}
		
		SearchBtnOnFilter.click();
		//Call the data verifying function
			
	}
	
	
	///Function created to test Save filter as field on the filter pop-up
	public void EnumOnFilter(WebDriver driver, /*String data,*/ int RowIndex,  int HeadingRowIndex, int k) throws Exception
	{
		//add logs for Extent report
		test.info("This is enum field.");
		
		WebElement fieldLabelandId = driver.findElement(By.xpath("//div[@datafieldindex='field-"+k+"']//label"));
		String id = fieldLabelandId.getAttribute("for");
		String FieldLabel  = fieldLabelandId.getText();
		
		
		int j = 1;
		int y = 3;
		int t = 0;
		//if(FieldLabel.equalsIgnoreCase("Sort Column"))
		//{
			driver.findElement(By.xpath("//form[@id='filter-form']//div[@id='"+id+"']")).click();
			
			List<WebElement> columnName = driver.findElements(By.xpath("//ul[@aria-labelledby='sort_column-label']//li//span[not(contains(@class,'MuiTouchRipple'))]"));
			for(WebElement i: columnName)
			{
				
				WebElement columnname = driver.findElement(By.xpath("(//ul[@aria-labelledby='sort_column-label']//li//span[not(contains(@class,'MuiTouchRipple'))])["+j+"]"));
				
				String ColumnName = columnname.getText();
				 columnname.click();
				//if(FieldLabel.equalsIgnoreCase("Sort Order"))
				//{
				 if (t % 2 == 0) 
				 {
				       t = 0;
				 }
					driver.findElement(By.xpath("//form[@id='filter-form']//div[@id='sort_order']")).click();
					//String data1 = UtilityClass.fetchDataFromExcelSheet("Filter", RowIndex, cell);
					String ar[] = {"Ascending","Descending"};
					driver.findElement(By.xpath("//ul[@aria-labelledby='sort_order-label']//li//span[text()='"+ar[t]+"']")).click();
			
				//}
				SearchBtnOnFilter.click();
				
			
				/*// Identify the column element
				WebElement columnElement = driver.findElement(By.xpath("//table//th//div[contains(text(),'"+ColumnName+"')]"));

				// Extract the column data into a list of integers
				List<Integer> columnData = driver.findElements(By.xpath("//table//td[y]"))
				                                  .stream()
				                                  .map(WebElement::getText)
				                                  .map(Integer::parseInt)
				                                  .collect(Collectors.toList());

				// Create a sorted copy of the list
				List<Integer> sortedColumnData = new ArrayList<>(columnData);
				Collections.sort(sortedColumnData);
				 System.out.println("Column data in "+ar[t]+" order." +sortedColumnData);
				// Compare the original and sorted lists
				if (columnData.equals(sortedColumnData)) {
				    System.out.println("Column data is sorted in "+ar[t]+" order.");
				} else {
				    System.out.println("Column data is not sorted in "+ar[t]+" order.");
				}*/
				y++;
				j++;
				t++;
				Thread.sleep(4000);
				
				getListViewData1(ColumnName);
				list_View.ClickOnApplyFilter();
				ClearFilterBtnOnFilter.click();
				driver.findElement(By.xpath("//form[@id='filter-form']//div[@id='"+id+"']")).click();
				
			}
			
		
		
		
		
		
	}
	
	//Pending : custom ids need to be added to get column names in list view and Compare both the Arraylist<String>
	public void getListViewData1(String columnName) throws InterruptedException, AWTException
	{
		System.out.println(columnName);
		//Data which have the link in it
		/*List<WebElement> ListViewColumnName = driver.findElements(By.xpath("(//tr[@id='MUIDataTableBodyRow-ListViewTable-1']//td[@data-tableid='ListViewTable']//div[1])[3]"));
		int count1 = ListViewColumnName.size();
		ArrayList<String> al1 = new ArrayList<String>();
		for(int i=3; i<=count1;i++)
		{
			 
				WebElement datalistview = driver.findElement(By.xpath("(//tr[@id='MUIDataTableBodyRow-ListViewTable-1']//td[@data-tableid='ListViewTable']//div[1])["+i+"]"));
				Thread.sleep(2000);
				String listviewdata = datalistview.getText();
				al1.add(listviewdata);
		}*/
		
		List<WebElement> link = driver.findElements(By.xpath("//td[contains(@data-testid,'MuiDataTableBodyCell')]//div[contains(text(),'"+columnName+"')]/..//a[@variant='body2']"));
		int linkcount = link.size();
		System.out.println("link count: "+linkcount);
		if(linkcount > 0)
		{
			ArrayList<String> al = new ArrayList<String>();
			for(int i=1; i<linkcount;i++)
			{
				 
					WebElement datalistview = driver.findElement(By.xpath("(//td[contains(@data-testid,'MuiDataTableBodyCell')]//div[contains(text(),'"+columnName+"')]/..//a[@variant='body2'])["+i+"]"));
					Thread.sleep(2000);
					String listviewdata = datalistview.getText();
					al.add(listviewdata);
			}
			// Collections.sort(al);
			 
		        // Let us print the sorted list
		       // System.out.println("List after the use of" +
		                       // " Collection.sort() :\n" + al);
		
		}
		else 
		{
			List<WebElement> data = driver.findElements(By.xpath("//td[contains(@data-testid,'MuiDataTableBodyCell')]//div[contains(text(),'"+columnName+"')]/following-sibling::div"));
			int count = data.size();
			System.out.println("count: "+count);
			
			if(count > 0)
			{
				ArrayList<String> Al = new ArrayList<String>();
				for(int i=1; i<count;i++)
				{
					//WebElement datalistview = driver.findElement(By.xpath("(//td[contains(@data-testid,'MuiDataTableBodyCell')]//div[contains(text(),'"+columnName+"')]/following-sibling::div)["+i+"]"));
					//Thread.sleep(2000);
					String listviewdata = null;
					try {
							WebElement datalistview = driver.findElement(By.xpath("(//td[contains(@data-testid,'MuiDataTableBodyCell')]//div[contains(text(),'"+columnName+"')]/following-sibling::div)["+i+"]"));
							Thread.sleep(2000);
							listviewdata = datalistview.getText();
							if(listviewdata.equals(""))
							{
								listviewdata = datalistview.getAttribute("innerText");
								if(listviewdata.equals(""))
								{
									listviewdata = datalistview.getAttribute("textContent");
								}
							}
						 }
						catch(Exception e)
						{
						
							System.out.println("column is not matched.");
					
					
						}
					Al.add(listviewdata);
				}
				 //Collections.sort(Al);
				 
			        // Let us print the sorted list
			        //System.out.println("List after the use of" +
			                        //" Collection.sort() :\n" + Al);
			       
			}
			else
			{
				System.out.println(columnName+" column does not exist in list view.");
				
				//Call the function to arrange the column to be displayed in list view
				//list_View.ColumnFunctionality(columnName, dataFromExcelSheet);
				//Check the column is added in the list view or not
				//getListViewData1(columnName, dataFromExcelSheet);
			}
			
		}
		
		
	}
	
	public void relateField(WebDriver driver, int RowIndex, int HeadingRowIndex, int k) throws Exception
	{
		//add logs for Extent report
		test.info("This is relate field.");
		
		String dataexcel = null;
		WebElement fieldId = driver.findElement(By.xpath("//div[@datafieldindex='field-"+k+"']//label"));
		String label = fieldId.getAttribute("for");
		String FieldLabel  = fieldId.getText();
		//Click on Search button
		WebElement searchIcon = driver.findElement(By.xpath("//*[local-name()='svg' and @id='seach-btn-"+label+"']"));
		searchIcon.click();
		Thread.sleep(2000);
		
		//Call the function to get the excel sheet data with the cell index
		Object Data[] = getExceldata(k, FieldLabel, HeadingRowIndex);
		Object cellIndex = Data[0];
		int cell = (Integer) cellIndex;
		Object Exceldata = Data[1];
		String ExcelData = String.valueOf(Exceldata);
				
		System.out.println("Cell index from sheet:" +cell);
		System.out.println("Excel data from sheet:" +ExcelData);
				
				
		//Field count on Search window for relate to field
		WebElement NameField = driver.findElement(By.xpath("//p[text()='Search']/..//input[contains(@id,'name')]"));
		if(ExcelData.equalsIgnoreCase(FieldLabel))
		{
			//add logs for Extent report
			test.info(ExcelData+" column name in excel sheet "+FieldLabel+" column name is matched.");
			
			dataexcel = UtilityClass.fetchDataFromExcelSheet("Filter", RowIndex, cell);
			NameField.sendKeys(dataexcel);
			//add logs for Extent report
			test.info(dataexcel+" data enter in the "+FieldLabel+" field.");

			driver.findElement(By.xpath("//p[text()='Search']/..//span[text()='Search']")).click();	
			Thread.sleep(2000);
			WebElement clickOnName = driver.findElement(By.xpath("(//tbody//tr[contains(@id,'MUIDataTableBodyRow-RelateListViewTable')]//a[contains(text(),'"+dataexcel+"')])[1]"));
			//Thread.sleep(2000);
			//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",clickOnName);
			Thread.sleep(2000);
			clickOnName.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@form='filter-form']//span[text()='Search']")).click();	
			
			
		}
		else {
				System.out.println("Field label does not matched.");
			}
		Thread.sleep(2000);
		getListViewData( FieldLabel, dataexcel);
		Thread.sleep(2000);
		list_View.ClickOnApplyFilter();
		ClearFilterBtnOnFilter.click();
	}
	
	
	public void SortColumn() throws InterruptedException, AWTException
	{
		//add logs for Extent report
		test.info("This is sort field.");
		
		//WebElement sortColumn = driver.findElement(By.xpath("//div[@id='sort_column']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",sortColumn);
		sortColumn.click();
		String columnNameString = null;
		List<WebElement> ColumnNames = driver.findElements(By.xpath("//ul[@aria-labelledby='sort_column-label']//li//span[not(contains(@class,'MuiTouchRipple'))]"));
		//y = column index in drop down
		int y = 1;
		int s = 1;
		for(WebElement i : ColumnNames)
		{
			try {
			//System.out.println("ColumnName: "+ColumnNames);
			System.out.println("y: "+y);
			WebElement ColumnName = driver.findElement(By.xpath("(//ul[@aria-labelledby='sort_column-label']//li//span[not(contains(@class,'MuiTouchRipple'))])["+y+"]"));
			UtilityClass.wait_until_element_invisible(driver, ColumnName);
			columnNameString = CommonFunctions.GetText(ColumnName);
			
			System.out.println("column Name String: " +columnNameString);
			UtilityClass.wait_until_element_invisible(driver, ColumnName);
			/*Actions act = new Actions(driver);
			act.moveToElement(ColumnName).perform();	
			act.click().perform();*/
			driver.findElement(By.xpath("//ul[@aria-labelledby='sort_column-label']//li//span[text()='"+columnNameString+"']")).click();
			//ColumnName.click();
			}catch(WebDriverException e)
			{
				System.out.println("WebDriverException thrown!");
				System.out.println("No more columns are present in drop down.");
				test.info("No more columns are present in drop down.");
				break;
				
			}
			Thread.sleep(2000);
			WebElement sortOrder = driver.findElement(By.xpath("//div[@id='sort_order']"));
			sortOrder.click();
			
			String ar[] = {"Ascending","Descending"};
			driver.findElement(By.xpath("//ul[@aria-labelledby='sort_order-label']//li//span[text()='"+ar[s]+"']")).click();
	
			SearchBtnOnFilter.click();
			Thread.sleep(2000);
			//Close the filter panel so that all column should be apear in List view.
			CloseFilterIconOnFilter.click();
			Thread.sleep(2000);
			//Call the function to verify correct data should be filtered.
			getData(columnNameString, ar[s]);
			
			//getListViewData(FieldLabel, data);
			Thread.sleep(2000);
			//ClearFilterBtnOnListView.click();
			//Click on filter button
			//Thread.sleep(2000);
			list_View.ClickOnApplyFilter();
			y++;
			s++;
			if(s==2)
			{
				s = 0;
			}
			Thread.sleep(5000);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",sortColumn);
			sortColumn.click();
			
		}
	}
	
	public void getData(String ColumnName, String ar)
	{
			//add logs for Extent report
			test.info(ColumnName+ " column is sorting.");
			
		   // xpath of column elements 
		   List<WebElement> columnElements = driver.findElements(By.xpath("//td[contains(@data-testid,'MuiDataTableBodyCell')]//div[contains(text(),'"+ColumnName+"')]/following-sibling::div"));

		   // Create a object of Array list to store the text values of the column data
		   List<String> columnData = new ArrayList<>();

		   // Retrieve the text values of the column data elements and add them to the list
		   for (WebElement element : columnElements) 
		   {
		        columnData.add(CommonFunctions.GetText(element));
		   }

		    // Create a copy of the column data list for comparison
		    List<String> sortedColumnData = new ArrayList<>(columnData);

		    // Sort the copy of the column data list in ascending order
		    Collections.sort(sortedColumnData, String.CASE_INSENSITIVE_ORDER);
		    System.out.println("columnData: "+columnData);
		    System.out.println("sortedColumnData: "+sortedColumnData);
		    
		    //add logs for Extent report
		    test.info("columnData: "+columnData);
		    test.info("sortedColumnData: "+sortedColumnData);
		    
		    // Compare the original column data list with the sorted copy
		    boolean isAscending = columnData.equals(sortedColumnData);
		    boolean isDescending = columnData.equals(reverseList(sortedColumnData));

		    if(isAscending == true)
		    {
		    	soft.assertEquals("Ascending", ar);
		    	System.out.println("Is column data sorted in ascending order? " + isAscending);
		    	//add logs for Extent report
			    test.info("Is column data sorted in ascending order? " + isAscending);
		    }
		    else if(isDescending == true)
		    {
		    	soft.assertEquals("Descending", ar);
		    	System.out.println("Is column data sorted in descending order? " + isDescending);
		    	//add logs for Extent report
			    test.info("Is column data sorted in descending order? " + isDescending);
		    }
		    
		       soft.assertAll();
	}

		    // Create a function to reverse a list
		    private static <T> List<T> reverseList(List<T> list) 
		    {
		        List<T> reversedList = new ArrayList<>(list);
		        Collections.reverse(reversedList);
		        System.out.println("reversedList: "+reversedList);
		        return reversedList;
		    }
		

	
}
