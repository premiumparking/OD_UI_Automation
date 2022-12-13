package testcases.OD;

import java.util.List;

import org.testng.annotations.Test;

import components.BaseClass;
import dataModel.Profile;
import pageObjects.OD.BusinessAccountsPage;
import utils.Excel_Operations;

/*
 * Class which contains the actual test scripts 
 * 
 * Extends : BaseClass
 * 
 * Author : Venu Thota(venu.t@comakeit.com)
 */
public class MassImport_BusinessAccounts extends BaseClass {

	Excel_Operations excel_Ops = new Excel_Operations();
	BusinessAccountsPage businessAccountsPage = new BusinessAccountsPage();

	List<Profile> profiles;
	// String fileName = "AnnapolisResidentPermits_2022_2023_Initial.xlsx";
	String fileName = "AnnapolisResidentPermits_2022_2023.xlsx";

	/*
	 * This is a test case to create profile
	 * 
	 * Author : Venu Thota(venu.t@comakeit.com)
	 */
	@Test(priority = 1)
	public void TC_01_CreateProfile() {
		loginPage = launchApplication();
		homePage = loginPage.login();
		profilePage = homePage.navigateToProfilesPage();
		List<Profile> profiles = excel_Ops.load_ProfilesData_From_ExcelSheet(fileName, "Profiles_4903");
		for (Profile profile : profiles) {
			if (!profilePage.isPrfileExist(profile.getEmail())) {
				profilePage.createProfile(profile);
			}
		}
	}

	/*
	 * This is a test case imporsonate Business Account
	 * 
	 * Author : Venu Thota(venu.t@comakeit.com)
	 */
	@Test(priority = 2)
	public void TC_02_ImpersonateBusinessAccount() {
		loginPage = launchApplication();
		homePage = loginPage.login();
		businessAccountsPage = homePage.navigateToBusinessAccountsPage();
		List<String> sheets = excel_Ops.get_Total_Sheets(fileName);
		// for (String sheet : sheets.subList(1, sheets.size())) {
		List<Profile> profiles = excel_Ops.load_ProfilesData_From_ExcelSheet(fileName, "BA1");
		if (businessAccountsPage.isBusinessAccountExist(profiles.get(0).getBusinessAccountName(),
				profiles.get(0).getLocation())) {
			businessAccountsPage.imporsonateBusinessAccount(profiles.get(0));
			for (Profile profile : profiles) {
				if (!businessAccountsPage.isMembertExist(profile)) {
					businessAccountsPage.add_Member(profile);
				}
				if (businessAccountsPage.is_CopyLink_displayed()) {
					businessAccountsPage.activate_Member(profile.getLpNumber());
					//businessAccountsPage.navigate_To_BusinessAcounts_Page();
				} else {
					businessAccountsPage.add_Vehicle(profile.getLpNumber());
				}
			}
		}
		businessAccountsPage.navigate_To_BusinessAcounts_Page();
		// }

	}

}