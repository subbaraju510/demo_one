package com.samplepach;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class DatafromExcel {
	@SuppressWarnings("deprecation")
	public static  void main(String args[]) throws IOException {
	//set the ChromeDriver path
		System.setProperty("webdriver.firefox.driver","C:\\Program Files\\geckodriver"); 
	    //   WebDriver driver = new ChromeDriver(); 

    //Create an object of File class to open xls file
    File file =    new File("D:\\TestData\\TestData.xls");
    
    //Create an object of FileInputStream class to read excel file
    FileInputStream inputStream = new FileInputStream(file);
    
    //creating workbook instance that refers to .xls file
    HSSFWorkbook wb=new HSSFWorkbook(inputStream);
    
    //creating a Sheet object
    HSSFSheet sheet=wb.getSheet("STUDENT_DATA");
    
    //get all rows in the sheet
    int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
    
   //Creating an object of ChromeDriver
    WebDriver driver = new ChromeDriver();
    
    //Navigate to the URL
    driver.get("https://nlp.nexterp.in/nlp/nlp/login");


    //Identify the WebElements for the student registration form
    WebElement username=driver.findElement(By.name("username"));
    WebElement password=driver.findElement(By.name("password"));
    
    WebElement code=driver.findElement(By.name("code"));
    WebElement submitBtn=driver.findElement(By.name("btnSignIn"));



    //iterate over all the rows in Excel and put data in the form.
    for(int i=1;i<=rowCount;i++) {
        //Enter the values read from Excel in firstname,lastname,mobile,email,address
    	username.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
    	password.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
    	code.sendKeys(sheet.getRow(i).getCell(2).getStringCellValue());
        
        //Click on the gender radio button using javascript
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].click();", genderMale);
		 */
        
        //Click on submit button
        submitBtn.click();
        
        //Verify the confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[text()='Thanks for submitting the form']"));
        
        //create a new cell in the row at index 6
        HSSFCell cell = sheet.getRow(i).createCell(4);
        
        //check if confirmation message is displayed
        if (confirmationMessage.isDisplayed()) {
            // if the message is displayed , write PASS in the excel sheet
            cell.setCellValue("PASS");
            
        } else {
            //if the message is not displayed , write FAIL in the excel sheet
            cell.setCellValue("FAIL");
        }
        
        // Write the data back in the Excel file
        FileOutputStream outputStream = new FileOutputStream("D:\\TestData\\TestData.xls");
        wb.write(outputStream);

        //close the confirmation popup
        WebElement closebtn = driver.findElement(By.id("closeLargeModal"));
        closebtn.click();
        
        //wait for page to come back to registration page after close button is clicked
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
    }
    
    //Close the workbook
    wb.close();
    
    //Quit the driver
    driver.quit();
    }
}