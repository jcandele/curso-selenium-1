package com.ejemplo_proyecto_maven.EjemploProyectoMaven;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejercicio9 {

	public static void main(String[] args) {
		// Crear una clase por ejercicio y meter el código dentro del main de cada clase
		// EJERCICIO 9:
		// Navegar a http://uitestingplayground.com/shadowdom
		// Hacer click en el botón con el engranaje e imprimir por consola el valor del campo de texto
		System.out.println( "Ejercicio 9" );
		
		/* 
		 * shadowdom
		 *  
		 */
		
		// #################### Initialize variables and browser ####################
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");  
        //ChromeDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();
        // Maximize browser
        driver.manage().window().maximize();
		
		// #################### http://uitestingplayground.com/shadowdom ####################
        // Navegar a http://uitestingplayground.com/shadowdom
        driver.get("http://uitestingplayground.com/shadowdom");
        
        WebElement shadowHost = driver.findElement(By.xpath("//guid-generator"));
        System.out.println("##########" + " Imprimiendo el shadowHost:\n" + 
        		shadowHost + "\n" + shadowHost.getText() + "\n" + "#########");
        
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        //WebElement shadowContentEngranaje = shadowRoot.findElement(By.xpath("//button[@id='buttonGenerate']")); // No funciona por XPATH
        // Css del engranaje: button#buttonGenerate -> El sahowDOM solo funciona con CSS
        WebElement shadowContentEngranaje = shadowRoot.findElement(By.cssSelector("button#buttonGenerate"));
        System.out.println("##########" + " Imprimiendo el engranaje:\n" + 
        		shadowContentEngranaje + "\n" + shadowContentEngranaje.getText() +
        		"Texto/Valor escrito en el button: " +
        		shadowContentEngranaje.getAttribute("value") + ".\n" + "##########");
        shadowContentEngranaje.click();
        
        //Css del input de texto: input#editField
        WebElement shadowContentTexto = shadowRoot.findElement(By.cssSelector("input#editField"));
        System.out.println("##########" + " Imprimiendo el texto:\n" + 
        		shadowContentTexto + "\n" + shadowContentTexto.getSize() + "\n" +
        		shadowContentTexto.getText().trim() + "\n" +
        		"Texto/Valor escrito en el input: " +
        		shadowContentTexto.getAttribute("value") + "\n" + "##########"); // Para obtener el valor/texto de un input: shadowContentTexto.getAttribute("value")
        
        /*
        shadowContentTexto.clear();
        shadowContentTexto.sendKeys("asd");
        System.out.println("##########" + " Imprimiendo el texto:\n" + 
        		shadowContentTexto + "\n" + shadowContentTexto.getSize() + "\n" +
        		shadowContentTexto.getText().trim() + "\n" +
        		"Texto/Valor escrito en el input: " +
        		shadowContentTexto.getAttribute("value") + "\n" + "##########"); // Para obtener el valor/texto de un input: shadowContentTexto.getAttribute("value")
        */
        
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
