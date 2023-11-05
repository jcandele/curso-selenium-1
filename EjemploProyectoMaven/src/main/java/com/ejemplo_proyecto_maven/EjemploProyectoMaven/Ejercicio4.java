package com.ejemplo_proyecto_maven.EjemploProyectoMaven;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejercicio4 {

	public static void main(String[] args) {
		
		// Crear una clase por ejercicio y meter el código dentro del main de cada clase
		// EJERCICIO 4:
        // Navegar a https://demoqa.com/automation-practice-form
        // Rellenar el formulario usando el id de los campos
		System.out.println( "Ejercicio 4" );
		
		// #################### Initialize variables and browser ####################
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");  
        //ChromeDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();
        // Maximize browser
        driver.manage().window().maximize();
		
		// #################### https://demoqa.com/automation-practice-form ####################
        // Navegar a https://demoqa.com/automation-practice-form
        driver.get("https://demoqa.com/automation-practice-form");
        
        // Rellenar el formulario usando el id de los campos
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("Julio");
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Candelario");
        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("julio@example.com"); 
        
        // Click en radiobutton
        //driver.findElement(By.cssSelector("input#gender-radio-1")).click(); // No hace click, da error
        //driver.findElement(By.xpath("//input[@id='gender-radio-1']")).click(); // No hace click, da error
        //driver.findElement(By.id("gender-radio-1")).click(); // No hace click, da error
        // Exception in thread "main" org.openqa.selenium.ElementClickInterceptedException: element click intercepted: Element <input name="gender" required="" type="radio" id="gender-radio-1" class="custom-control-input" value="Male"> is not clickable at point (989, 460). Other element would receive the click: <label title="" for="gender-radio-1" class="custom-control-label">...</label>
        //WebElement gender = driver.findElement(By.id("gender-radio-1")); // Da error porque tiene otro elemento superpuesto que recibe el click, hayq ue hacer click en el otro elemento
        WebElement gender = driver.findElement(By.xpath("//*[text()='Male']")); // Como no tiene id el elemento superpuesto se usa XPATH
        //new Actions(driver).moveToElement(subjects).perform(); // para mover el puntero (mouse) al elemento localizado
        System.out.println(gender.isDisplayed());
        gender.click();
               
        WebElement mobileNumber = driver.findElement(By.id("userNumber"));
        mobileNumber.sendKeys("1234567890"); // 10 dígitos
        
        // Seleccionar en datepicker
        WebElement dateBirthday = driver.findElement(By.id("dateOfBirthInput"));
        dateBirthday.sendKeys(Keys.CONTROL + "a");
        //dateBirthday.sendKeys(Keys.DELETE); // Si borras lo que hay en el datepicker se te pone la página en blanco, no borrarlo
        dateBirthday.sendKeys("27 Septiembre 1993");
        dateBirthday.sendKeys(Keys.ENTER);
        
        WebElement subjects = driver.findElement(By.id("subjectsInput"));
        //new Actions(driver).moveToElement(subjects).perform(); // para mover el puntero (mouse) al elemento localizado
        subjects.sendKeys("asd fgh");
        
        // Cargar un archivo
        WebElement upload_file = driver.findElement(By.id("uploadPicture"));
        String rutaFichero = "C:\\Users\\jcandele\\eclipse-workspace\\Automatizate 2023-PRIMERO.pdf";
        upload_file.sendKeys(rutaFichero);
           
        // Click en checkbox
        //WebElement hobbies = driver.findElement(By.id("hobbies-checkbox-1")); // Da error porque tiene otro elemento superpuesto que recibe el click, hayq ue hacer click en el otro elemento
        WebElement hobbies = driver.findElement(By.xpath("//*[text()='Sports']"));
        hobbies.click();
        
        WebElement currentAdress = driver.findElement(By.id("currentAddress"));
        currentAdress.sendKeys("asd fgh jkl");
        
        WebElement state = driver.findElement(By.id("react-select-3-input"));
        state.sendKeys("NCR");
        state.sendKeys(Keys.TAB);
        
        WebElement city = driver.findElement(By.id("react-select-4-input"));
        city.sendKeys("Noida");
        city.sendKeys(Keys.TAB);
        
        // Enviar formulario
        WebElement buttonSubmit = driver.findElement(By.id("submit"));
        buttonSubmit.click();
        
        try {        	
        	Thread.sleep(5000); // ms
        	//TimeUnit.SECONDS.sleep(10); // por dentro hace un Thread.sleep(x)
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
        
        // Cerrar modal
        WebElement buttonClose = driver.findElement(By.id("closeLargeModal"));
        buttonClose.click();
        
        try {        	
        	Thread.sleep(5000); // ms
        	//TimeUnit.SECONDS.sleep(10); // por dentro hace un Thread.sleep(x)
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		
		// #################### Close the browser ####################
        // Close the browser
        driver.quit();
	}
	
}
