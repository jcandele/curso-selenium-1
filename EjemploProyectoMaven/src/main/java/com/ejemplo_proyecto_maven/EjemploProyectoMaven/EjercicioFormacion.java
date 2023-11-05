package com.ejemplo_proyecto_maven.EjemploProyectoMaven;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class EjercicioFormacion {

	public static void main(String[] args) {
		// Crear una clase por ejercicio y meter el código dentro del main de cada clase
		// EJERCICIO propuesto Formación Selenium Básico:
		// Navegar a https://katalon-demo-cura.herokuapp.com/#appointment
		// 1-Hacer login en la página, rellenar el formulario y mandarlo
		// 2-Comprobar después de mandarlo, que el resumen son los mismos valores que tenemos guardados
		// 3-Al terminar, darle a las 3 rayitas de arriba a la derecha y pulsar en "Home" para regresar
		System.out.println( "EJERCICIO propuesto Formación Selenium Básico" );
		
		/* 
		 * Repaso: ejercicio propuesto en la formación selenium básico
		 *  
		 */
		
		// Se crea un diccionario para guardar los valores y después comprobarlos	
		HashMap<String, String> diccionarioVariables = new HashMap<String, String>();
		//diccionarioComprobacion.put("Student Name", stringFirstName + " " + stringLastName);		
		
		// #################### Initialize variables and browser ####################
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");  
        //ChromeDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();
        // Maximize browser
        driver.manage().window().maximize();
		
		// #################### https://katalon-demo-cura.herokuapp.com/#appointment ####################
        driver.get("https://katalon-demo-cura.herokuapp.com/#appointment");
        
        WebElement buttonMakeAppointment = driver.findElement(By.id("btn-make-appointment"));
        buttonMakeAppointment.click();
        
        WebElement user = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        diccionarioVariables.put("username", user.getAttribute("value"));
        WebElement pass = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        diccionarioVariables.put("password", pass.getAttribute("value"));
        
        WebElement userLogin = driver.findElement(By.id("txt-username"));
        userLogin.sendKeys(diccionarioVariables.get("username"));
        WebElement passLogin = driver.findElement(By.id("txt-password"));
        passLogin.sendKeys(diccionarioVariables.get("password"));
        WebElement buttonLogin = driver.findElement(By.id("btn-login"));
        buttonLogin.click();
        
        WebElement facilityCombo = driver.findElement(By.id("combo_facility"));
        Select dropdown = new Select(facilityCombo);
        dropdown.selectByValue("Seoul CURA Healthcare Center");
        diccionarioVariables.put("facility", facilityCombo.getAttribute("value")); // Obtener el valor seleccionado
        
        WebElement checkBoxApply = driver.findElement(By.id("chk_hospotal_readmission"));
        if (!checkBoxApply.isSelected()) // Si no está seleccionado se marca
        	checkBoxApply.click();
        //diccionarioVariables.put("hospital readmission", String.valueOf(checkBoxApply.isSelected()));
        diccionarioVariables.put("hospital readmission", "Yes");
        
        diccionarioVariables.put("healthcare program", "Medicaid");
        WebElement radioProgram = driver.findElement(By.xpath("//input[@type='radio' and @value='" + diccionarioVariables.get("healthcare program") + "']"));
        radioProgram.click();
        
        diccionarioVariables.put("visit date", "17/12/2023");
        WebElement visitDateElement = driver.findElement(By.id("txt_visit_date"));
        visitDateElement.sendKeys(diccionarioVariables.get("visit date"));
        visitDateElement.sendKeys(Keys.TAB);
        
        WebElement textoElement = driver.findElement(By.xpath("//h2[text()='Make Appointment']"));
        diccionarioVariables.put("comment", textoElement.getText());
        WebElement commentlElement = driver.findElement(By.id("txt_comment"));
        commentlElement.sendKeys(diccionarioVariables.get("comment"));
        
        try {        	
        	Thread.sleep(3000); // ms
        	//TimeUnit.SECONDS.sleep(10); // por dentro hace un Thread.sleep(x)
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
        
        WebElement buttonBookAppointment = driver.findElement(By.id("btn-book-appointment"));
        buttonBookAppointment.click();
        
        // #################### Validar en el modal que se muestran correctamente los valores ####################
        System.out.println("\n##### Imprime el diccionario #####");
        for (String i : diccionarioVariables.keySet()) {
        	System.out.println("Key: " + i + " | Value: " + diccionarioVariables.get(i));
        }
        
        System.out.println("\n##### Validar en el modal que se muestran correctamente los valores #####");
        WebElement facilitySent = driver.findElement(By.xpath("//*[@id='facility']"));
        if (facilitySent.getText().equals(diccionarioVariables.get("facility")))
        	System.out.println("Verification 'Facility': Passed");
		else
			System.out.println("Verification 'Facility': Failed");
        
        WebElement applySent = driver.findElement(By.xpath("//*[@id='hospital_readmission']"));
        if (applySent.getText().equals(diccionarioVariables.get("hospital readmission")))
        	System.out.println("Verification 'Apply for hospital readmission': Passed");
		else {
			System.out.println(applySent.getText() + " != " + diccionarioVariables.get("hospital readmission"));
			System.out.println("Verification 'Apply for hospital readmission': Failed");
		}
        
        WebElement programSent = driver.findElement(By.xpath("//*[@id='program']"));
        if (programSent.getText().equals(diccionarioVariables.get("healthcare program")))
        	System.out.println("Verification 'Healthcare Program': Passed");
		else
			System.out.println("Verification 'Healthcare Program': Failed");
        
        WebElement visitDateSent = driver.findElement(By.xpath("//*[@id='visit_date']"));
        if (visitDateSent.getText().equals(diccionarioVariables.get("visit date")))
        	System.out.println("Verification 'Visit Date': Passed");
		else
			System.out.println("Verification 'Visit Date': Failed");
        
        WebElement commentSent = driver.findElement(By.xpath("//*[@id='comment']"));
        if (commentSent.getText().equals(diccionarioVariables.get("comment")))
        	System.out.println("Verification 'Comment': Passed");
		else
			System.out.println("Verification 'Comment': Failed");
        
        try {        	
        	Thread.sleep(3000); // ms
        	//TimeUnit.SECONDS.sleep(10); // por dentro hace un Thread.sleep(x)
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
        
        // #################### Vuelve a la home ####################
        WebElement buttonMenu = driver.findElement(By.id("menu-toggle"));
        buttonMenu.click();
        WebElement linkHome = driver.findElement(By.xpath("//a[text()='Home']"));
        linkHome.click();
        
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
