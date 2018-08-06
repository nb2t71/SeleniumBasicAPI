package com.logigear.selenium.pageobjects.railway;

import java.util.Arrays;
import java.util.List;
import java.lang.System;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.logigear.selenium.constant.Constant;

public class MyTicketPage extends GeneralPage{

	//Locators
	private final By cmbDepartStation = By.xpath("//select[@name='FilterDpStation']");
	private final By cmbArriveStation = By.xpath("//select[@name='FilterArStation']");
	private final By txtDepartDate = By.xpath("//input[@type='text' and @name='FilterDpDate']");
	private final By cmbStatus = By.xpath("//select[@name='FilterStatus']");
	private final By btnApplyFilter = By.xpath("//input[@type='submit' and @value='Apply filter']");
	private final By tlpTicketTable = By.xpath("//table[@class='MyTable']");
	private final By divFitlerErrorMsg = By.xpath("//div[@class='error message']");
	
	//Elements
	protected WebElement getCmbDepartStation() {
		return Constant.getWebDriver().findElement(cmbDepartStation);
	}
	
	protected WebElement getCmbArriveStation() {
		return Constant.getWebDriver().findElement(cmbArriveStation);
	}
	
	protected WebElement getTxtDepartDate() {
		return Constant.getWebDriver().findElement(txtDepartDate);
	}
	
	protected WebElement getCmbStatus() {
		return Constant.getWebDriver().findElement(cmbStatus);
	}
	
	protected WebElement getBtnApplyFilter() {
		return Constant.getWebDriver().findElement(btnApplyFilter);
	}
	
	protected WebElement getTlpTicketTable() {
		return Constant.getWebDriver().findElement(tlpTicketTable);
	}
	
	protected WebElement getDivFitlerErrorMsg() {
		return Constant.getWebDriver().findElement(divFitlerErrorMsg);
	}
	
	//Methods
	public MyTicketPage cancelFirstTicket() {
		Constant.getWebDriver().findElement(By.xpath("(//input[@type='button' and @value='Cancel'])[1]")).click();
		Constant.getWebDriver().switchTo().alert().accept();
		return this;
	}
	
	public String[] getFirstTicketInfo() {
		List<WebElement> cols= this.getTlpTicketTable().findElements(By.xpath(".//tr[1]/th"));
		String[] strArrayInfo = new String[cols.size()-2];
		By currentCell;
		int j;
		for(int i=0; i<strArrayInfo.length; i++) {
			j = i+1;
			currentCell = By.xpath("//table[@class='MyTable']//tr[2]/td["+ j +"]");
			strArrayInfo[i] = Constant.getWebDriver()
					.findElement(currentCell).getText();
		}
		return strArrayInfo;
	}
	
	public String[][] getTickets(){
		List<WebElement> rows= this.getTlpTicketTable().findElements(By.xpath(".//tr"));;
		List<WebElement> cols= this.getTlpTicketTable().findElements(By.xpath(".//tr[1]/th"));
		String [][] strArrayTickets = new String[rows.size()-1][cols.size()-2];
		By currentCell;
		int row;
		int col;
		
		for(int i=0; i<rows.size()-1; i++) {
			row = i+2;
			for(int j=0; j<cols.size()-2; j++) {
				col = j+2;
				currentCell = By.xpath("//table[@class='MyTable']//tr["+row+"]/td["+ col +"]");
				strArrayTickets[i][j] = Constant.getWebDriver()
						.findElement(currentCell).getText();
			}
		}
		
		return strArrayTickets;
	}
	
	public String[][] filterTickets(String strByDepartStationValue, String strByArriveStationValue
			, String strByDepartDateValue, String strByStatusValue) {
		
		if(strByDepartStationValue.isEmpty()==false) {
			new Select(this.getCmbDepartStation()).selectByVisibleText(strByDepartStationValue);
		}
		
		if(strByArriveStationValue.isEmpty()==false) {
			new Select(this.getCmbArriveStation()).selectByVisibleText(strByArriveStationValue);
		}
		
		if(strByDepartDateValue.isEmpty()==false) {
			this.getTxtDepartDate().sendKeys(strByDepartDateValue);
		}
		
		if(strByStatusValue.isEmpty()==false) {
			new Select(this.getCmbStatus()).selectByVisibleText(strByStatusValue);
		}
		
		this.getBtnApplyFilter().click();
		
		return getTickets();
	}
	
	public Boolean checkTicketsExistInTable(String[][] strArrayCurrentTickets, String[][] strArrayExpectedTickets) {
		Boolean bl=true;
		String[] strArrayResult = new String[strArrayExpectedTickets.length];
		for(int i=0; i<strArrayExpectedTickets.length; i++) {
			for(int j=0; j<strArrayCurrentTickets.length; j++) {
				if(Arrays.equals(strArrayExpectedTickets[i],strArrayCurrentTickets[j])) {
					strArrayResult[i]="exist";
					int originLength1 = strArrayCurrentTickets.length;
					int originLength2 = strArrayCurrentTickets[i].length;
					String[][] strArrayTemp = new String[originLength1-1][originLength2]; 
					System.arraycopy(strArrayCurrentTickets, 0, strArrayTemp, 0, j);
					System.arraycopy(strArrayCurrentTickets, j+1, strArrayTemp, j, originLength1-j-1);
					strArrayCurrentTickets = strArrayTemp;
					j=originLength1;
				}				
			}
		}
		
		for(String s: strArrayResult) {
			if(s!="exist") {
				bl = false;
			}else {
				bl = true;
			}
		}
		
		return bl;
	}
	
	public String getFitlerErrorMsg() {
		return this.getDivFitlerErrorMsg().getText();
	}
}
