package com.ejemplo_proyecto_maven.EjemploProyectoMaven;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejercicio10 {

	public static void main(String[] args) {
		// Crear una clase por ejercicio y meter el código dentro del main de cada clase
		// EJERCICIO 10:
		// Navegar a https://demoqa.com/automation-practice-form
		// Rellenar el formulario usando el id de los campos
		// Confirmar los valores y en el modal comprobar que se muestran correctamente
		System.out.println( "Ejercicio 10" );
		
		/* 
		 * Trabajar con tablas
		 *  
		 */
		
		// Variables para rellenar el formulario
		String stringFirstName = "Julio";
		String stringLastName = "Candelario";
		String stringEmail = "julio@example.com";
		String stringGender = "Male";
		String stringMobile = "1234567890";
		String stringDateOfBirth = "27 September,1993";
		String stringSubjects = "asd";
		String stringHobbies = "Sports";
		String stringFichero = "Automatizate 2023-PRIMERO.pdf";
		String rutaFichero = "C:\\Users\\jcandele\\eclipse-workspace\\" + stringFichero;
		String stringCurrentAddress = "Dirección pruebas";
		String stringState = "NCR";
		String stringCity = "Noida";
		
		HashMap<String, String> diccionarioComprobacion = new HashMap<String, String>();
		diccionarioComprobacion.put("Student Name", stringFirstName + " " + stringLastName);
		diccionarioComprobacion.put("Address", stringCurrentAddress);
		diccionarioComprobacion.put("State and City", stringState + " " + stringCity);
		diccionarioComprobacion.put("Picture", stringFichero);
		diccionarioComprobacion.put("Student Email", stringEmail);
		diccionarioComprobacion.put("Date of Birth", stringDateOfBirth);
		diccionarioComprobacion.put("Gender", stringGender);
		diccionarioComprobacion.put("Mobile", stringMobile);
		diccionarioComprobacion.put("Subjects", stringSubjects);
		diccionarioComprobacion.put("Hobbies", stringHobbies);
		
		
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
        firstName.sendKeys(stringFirstName);
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys(stringLastName);
        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys(stringEmail); 
        
        // Click en radiobutton
        // Exception in thread "main" org.openqa.selenium.ElementClickInterceptedException: element click intercepted: Element <input name="gender" required="" type="radio" id="gender-radio-1" class="custom-control-input" value="Male"> is not clickable at point (989, 460). Other element would receive the click: <label title="" for="gender-radio-1" class="custom-control-label">...</label>
        //WebElement gender = driver.findElement(By.id("gender-radio-1")); // Da error porque tiene otro elemento superpuesto que recibe el click, hayq ue hacer click en el otro elemento
        WebElement gender = driver.findElement(By.xpath("//*[text()='" + stringGender + "']")); // Como no tiene id el elemento superpuesto se usa XPATH
        //new Actions(driver).moveToElement(subjects).perform(); // para mover el puntero (mouse) al elemento localizado
        System.out.println(gender.isDisplayed());
        gender.click();
               
        WebElement mobileNumber = driver.findElement(By.id("userNumber"));
        mobileNumber.sendKeys(stringMobile); // 10 dígitos
        
        // Seleccionar en datepicker
        WebElement dateBirthday = driver.findElement(By.id("dateOfBirthInput"));
        dateBirthday.sendKeys(Keys.CONTROL + "a");
        //dateBirthday.sendKeys(Keys.DELETE); // Si borras lo que hay en el datepicker se te pone la página en blanco, no borrarlo
        dateBirthday.sendKeys(stringDateOfBirth);
        dateBirthday.sendKeys(Keys.ENTER);
        
        WebElement subjects = driver.findElement(By.id("subjectsInput"));
        //new Actions(driver).moveToElement(subjects).perform(); // para mover el puntero (mouse) al elemento localizado
        subjects.sendKeys(stringSubjects);
        
        // Cargar un archivo
        WebElement upload_file = driver.findElement(By.id("uploadPicture"));
        upload_file.sendKeys(rutaFichero);
           
        // Click en checkbox
        //WebElement hobbies = driver.findElement(By.id("hobbies-checkbox-1")); // Da error porque tiene otro elemento superpuesto que recibe el click, hayq ue hacer click en el otro elemento
        WebElement hobbies = driver.findElement(By.xpath("//*[text()='" + stringHobbies + "']"));
        hobbies.click();
        
        WebElement currentAdress = driver.findElement(By.id("currentAddress"));
        currentAdress.sendKeys(stringCurrentAddress);
        
        WebElement state = driver.findElement(By.id("react-select-3-input"));
        state.sendKeys(stringState);
        state.sendKeys(Keys.TAB);
        
        WebElement city = driver.findElement(By.id("react-select-4-input"));
        city.sendKeys(stringCity);
        city.sendKeys(Keys.TAB);
        
        // Enviar formulario
        WebElement buttonSubmit = driver.findElement(By.id("submit"));
        buttonSubmit.click();
        
        // #################### Validar en el modal que se muestran correctamente los valores ####################
        // Tabla de 10 filas y 2 columnas
        HashMap<String, String> diccionarioModal = new HashMap<String, String>();
        for (int row = 1; row <= 10; row++) {
        	WebElement tableValueColumn1 = driver.findElement(By.xpath("//table/tbody/tr[" + row + "]/td[1]"));
        	WebElement tableValueColumn2 = driver.findElement(By.xpath("//table/tbody/tr[" + row + "]/td[2]"));
        	//System.out.println(tableValueColumn1.getText() + "/" + tableValueColumn2.getText());
        	diccionarioModal.put(tableValueColumn1.getText(), tableValueColumn2.getText());
        }
        System.out.println("Tamaño del diccionario diccionarioModal: " + diccionarioModal.size());
        
        System.out.println("\n##### Validar en el modal que se muestran correctamente los valores mandados #####");
        for (String i : diccionarioModal.keySet()) {
        	//System.out.println("Key: " + i + " | Value: " + diccionarioModal.get(i));
        	if (diccionarioModal.get(i).equals(diccionarioComprobacion.get(i)))
        		System.out.println(i + ": Correcto");
        	else if (i.equals("Subjects"))
				System.out.println(i + ": Correcto siempre (aunque se rellene se manda vacío siempre)");
        	else
        		System.out.println(i + ": Incorrecto (Tabla:" + diccionarioModal.get(i) + " != Original:" + diccionarioComprobacion.get(i) +")");
        }
        
        // Cerrar modal
        WebElement buttonClose = driver.findElement(By.id("closeLargeModal"));
        buttonClose.click();
        
        try {        	
        	Thread.sleep(3000); // ms
        	//TimeUnit.SECONDS.sleep(10); // por dentro hace un Thread.sleep(x)
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		
		// #################### Close the browser ####################
        // Close the browser
        driver.quit();
	}

}
