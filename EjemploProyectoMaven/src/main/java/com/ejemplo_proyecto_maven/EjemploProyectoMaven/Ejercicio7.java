package com.ejemplo_proyecto_maven.EjemploProyectoMaven;

import java.time.Duration;
import org.openqa.selenium.Alert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ejercicio7 {

	public static void main(String[] args) {
		// Crear una clase por ejercicio y meter el código dentro del main de cada clase
		// EJERCICIO 7:
		// Navegar a https://demoqa.com/alerts
		// Hacer click en los botones y cerrar los mensajes que aparecen
		System.out.println( "Ejercicio 7" );
		
		// #################### Initialize variables and browser ####################
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");  
        //ChromeDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();
        // Maximize browser
        driver.manage().window().maximize();
		
		// #################### https://demoqa.com/alerts ####################
        // Navegar a https://demoqa.com/alerts
        driver.get("https://demoqa.com/alerts");
        
        // ########## Alerta 1 ##########
        WebElement button1 = driver.findElement(By.xpath("//button[@id='alertButton']"));
        button1.click();
        //Wait for the alert to be displayed
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        //Store the alert in a variable
        Alert alert = driver.switchTo().alert();
        //Store the alert in a variable for reuse
        String text = alert.getText();
        System.out.println("Texto alerta 1: " + text);
        //Press the OK button
        alert.accept();
        
        // ########## Alerta 2 ##########
        WebElement button2 = driver.findElement(By.xpath("//button[@id='timerAlertButton']"));
        button2.click();
        //Wait for the alert to be displayed
        Wait<WebDriver> wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        //Wait for the alert to be displayed and store it in a variable
        Alert alert2 = wait2.until(ExpectedConditions.alertIsPresent());;
        //Store the alert in a variable for reuse
        String text2 = alert2.getText();
        System.out.println("Texto alerta 2: " + text2);
        //Press the Cancel button
        alert2.dismiss();
        
        // ########## Alerta 3 ##########
        WebElement button3 = driver.findElement(By.xpath("//button[@id='confirmButton']"));
        button3.click();
        //Wait for the alert to be displayed
        Wait<WebDriver> wait3 = new WebDriverWait(driver, Duration.ofSeconds(5));
        //Wait for the alert to be displayed and store it in a variable
        Alert alert3 = wait3.until(ExpectedConditions.alertIsPresent());
        //Store the alert in a variable for reuse
        String text3 = alert3.getText();
        System.out.println("Texto alerta 3: " + text3);
        //Press the Cancel button
        alert3.dismiss();
        
        try {        	
        	Thread.sleep(5000); // ms
        	//TimeUnit.SECONDS.sleep(10); // por dentro hace un Thread.sleep(x)
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
        
        //Press the OK button
        button3.click();
        alert3 = wait3.until(ExpectedConditions.alertIsPresent());
        text3 = alert3.getText();
        System.out.println("Texto alerta 3: " + text3);
        alert3.accept();
        
        // ########## Alerta 4 ##########
        WebElement button4 = driver.findElement(By.xpath("//button[@id='promtButton']"));
        button4.click();
        //Wait for the alert to be displayed
        Wait<WebDriver> wait4 = new WebDriverWait(driver, Duration.ofSeconds(5));
        //Wait for the alert to be displayed and store it in a variable
        Alert alert4 = wait4.until(ExpectedConditions.alertIsPresent());
        //Store the alert in a variable for reuse
        String text4 = alert4.getText();
        System.out.println("Texto alerta 4: " + text4);
        //Type your message
        alert4.sendKeys("Selenium");
        //Press the OK button
        alert4.accept();

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
