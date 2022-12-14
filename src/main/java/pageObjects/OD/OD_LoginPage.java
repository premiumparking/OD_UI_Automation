package pageObjects.OD;

import org.openqa.selenium.By;

import components.BaseClass;

/*
 * Class which contains the web elements and performs Home page activities (methods)
 * 
 * Extends : BaseClass
 * 
 * Author : Venu Thota(venu.t@comakeit.com)
 */
public class OD_LoginPage extends BaseClass {

	// ****************** WEB ELEMENTS ****************************//
	public By textbox_UserName = By.id("user_email");
	By textbox_Password = By.id("user_password");
	By button_SignIn = By.xpath(".//input[@value='Sign In']");

	// ****************** ACTIONS ****************************//
	/*
	 * Method to navigate to perform login
	 * 
	 * Author : Venu Thota(venu.t@comakeit.com)
	 */
	public OD_HomePage login() {
		enterText(textbox_UserName, BaseClass.od_username);
		enterText(textbox_Password, BaseClass.od_password);
		clickOnButton(button_SignIn, "Sign In button");
		OD_HomePage homePage = new OD_HomePage();
		waitForElementTobeDisplayed(homePage.label_OperatorDashboard);
		passStep("Logged in with Brendan Bodensteiner 's credentials");
		return new OD_HomePage();

	}

}
