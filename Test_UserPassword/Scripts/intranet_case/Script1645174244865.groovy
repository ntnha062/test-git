import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.table.data.Data as Data

boolean check = false

def passlist = ((['1', '2', 'Ntnh@123456']) as String[])

for (int i = 0; i < 3; i++) {
    WebUI.setText(findTestObject('Object Repository/Page_Single Sign On - CAS - Central Authent_012c29/input_U_username'), 
        user)

    WebUI.setText(findTestObject('Object Repository/Page_Single Sign On - CAS - Central Authent_012c29/input_P_password'), 
        passlist[i])

    WebUI.click(findTestObject('Object Repository/Page_Single Sign On - CAS - Central Authent_012c29/input_Remember Me_submit'))

    println(WebUI.getUrl())

    if (WebUI.getUrl() == url) {
        println('Failed')
    } else {
        println('Passed' + (passlist[i]))

        check = true

        break
    }
}

WebUI.mouseOver(findTestObject('Page_Single Sign On - CAS - Central Authent_012c29/Page_Corporate Intranet/a_Tools'))

WebUI.click(findTestObject('Page_Single Sign On - CAS - Central Authent_012c29/Page_Corporate Intranet/a_Employee Contact'))

WebUI.switchToWindowTitle('HRM Tool')

WebUI.setText(findTestObject('Page_Single Sign On - CAS - Central Authent_012c29/Page_HRM Tool/input_Fullname_tfemp_fullname'), 
    'thinh')

WebUI.click(findTestObject('Page_Single Sign On - CAS - Central Authent_012c29/Page_HRM Tool/input_Filters__search'))

WebDriver driver = DriverFactory.getWebDriver()

WebElement table = driver.findElement(By.xpath('//*[@id=\'resultTable\']/tbody'))

List<WebElement> rowsInTable = table.findElements(By.tagName('tr'))

def listdata = []

int rowsCount = rowsInTable.size()

WebUI.comment("rowsCount=$rowsCount")

for (int r = 0; r < rowsCount; r++) {
    Data data = new Data()

    List<WebElement> columnsInRow = rowsInTable.get(r).findElements(By.tagName('td'))

    int columnsCount = columnsInRow.size()

    String cellLocalText = columnsInRow.get(6).getText()

    if (cellLocalText == 'Lab 4') {
        data.fullname = columnsInRow.get(1).getText()

        data.id = columnsInRow.get(0).getText()

        data.mail = columnsInRow.get(3).getText()

        data.phone = columnsInRow.get(4).getText()

        listdata.add(data)
    }
}

def myFile = new File('C:\\Users\\nthanhnha\\Desktop\\Katalon-proj\\data.txt')

for (int i = 0; i < listdata.size(); i++) {
    myFile.append(((((((listdata[i].id + ' ') + listdata[i].fullname) + ' ') + listdata[i].mail) + ' ') + listdata[i].phone) + 
        '\n')

    println((((((listdata[i].id + ' ') + listdata[i].fullname) + ' ') + listdata[i].mail) + ' ') + listdata[i].phone)
}

@com.kms.katalon.core.annotation.SetUp
def setup() {
    WebUI.openBrowser('')

    WebUI.navigateToUrl(url)
}

@com.kms.katalon.core.annotation.TearDown
def teardown() {
    WebUI.closeBrowser()
}

