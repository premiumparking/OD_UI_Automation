package testcases.OD;

import org.testng.annotations.Test;

import components.BaseClass;
import components.Constants;
import dataModel.OD.EventRates;
import dataModel.OD.Location;
import dataModel.OD.Market;
import pageObjects.OD.OD_EventRatesPage;
import pageObjects.OD.OD_HomePage;
import pageObjects.OD.OD_LocationsPage;
import pageObjects.OD.OD_LoginPage;
import pageObjects.OD.OD_MarketsPage;
import pageObjects.OD.OD_VenuesPage;
import pageObjects.OD.OD_VirtualLocationsPage;
import utils.XML_Operations;

public class Manage_Menu_Testcases extends BaseClass {
	// ****************** CLASS INSTANCES ****************************//
	XML_Operations xml_Ops = new XML_Operations();
	OD_HomePage homePage = new OD_HomePage();
	OD_LoginPage loginPage = new OD_LoginPage();
	OD_MarketsPage marketsPage = new OD_MarketsPage();
	OD_VenuesPage venuesPage = new OD_VenuesPage();
	OD_LocationsPage locationsPage = new OD_LocationsPage();
	OD_VirtualLocationsPage vl_Page = new OD_VirtualLocationsPage();
	OD_EventRatesPage eventRates_page = new OD_EventRatesPage();

	// ****************** TEST SCRIPTS ****************************//
	/*
	 * This is a test case to create Market
	 * 
	 * Author : Venu Thota(venu.t@comakeit.com)
	 */
	@Test(groups = { "smoke", "regression" })
	public void TC_01_Create_Market() {

		loginPage = launch_OD_Application();
		homePage = loginPage.login();
		marketsPage = homePage.navigateToMarketsPage();
		Market market = xml_Ops.getMarketTestData();
		market.setName(market.getName() + getTimestamp());
		marketsPage.create_Market(market);

	}

	/*
	 * This is a test case to create Location
	 * 
	 * Author : Venu Thota(venu.t@comakeit.com)
	 */
	@Test(groups = { "smoke", "regression" })
	public void TC_02_Create_Location() {
		String pNumber = "P" + get4DigitRandomNumber();
		loginPage = launch_OD_Application();
		homePage = loginPage.login();
		locationsPage = homePage.navigateToLocationsPage();
		Location location = xml_Ops.getLocationTestData();

		// Setting location test data
		location.setName(pNumber);
		location.setPropertyName(location.getPropertyName() + getTimestamp());
		location.setAddress(location.getAddress() + getTimestamp());

		// Creating location
		locationsPage.create_Location(location);

	}

	/*
	 * This is a test case to create Location
	 * 
	 * Author : Venu Thota(venu.t@comakeit.com)
	 */
	@Test(groups = { "smoke", "regression" })
	public void TC_03_Create_VirtualLocation() {

		loginPage = launch_OD_Application();
		homePage = loginPage.login();
		vl_Page = homePage.navigateToVirtualLocationsPage();
		String vl_name = "Test_Virtual_Location_" + getTimestamp();
		vl_Page.create_VirtualLocation(vl_name, Constants.VIRTUAL_LOCATIONS);

	}

	/*
	 * This is a test case to create EventRate()
	 * 
	 * Author : Lakshmi N Ayyagari
	 */
	@Test(groups = { "smoke", "regression" })
	public void TC_04_Create_EventRate() {
		EventRates eventRate = xml_Ops.getEventRatesTestData();
		loginPage = launch_OD_Application();
		homePage = loginPage.login();
		eventRates_page = homePage.navigateToEventRatesPage();

		eventRates_page.create_newevent(eventRate);

	}

	/*
	 * TC_05_Create_Blockout TC_06_Create_Whitelist TC_07_Create_Promocode
	 * TC_08_Create_User
	 */

}
