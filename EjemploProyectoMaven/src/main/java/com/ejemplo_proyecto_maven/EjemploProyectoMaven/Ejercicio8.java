package com.ejemplo_proyecto_maven.EjemploProyectoMaven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Ejercicio8 {

	public static void main(String[] args) {
		// Crear una clase por ejercicio y meter el código dentro del main de cada clase
		// EJERCICIO 8:
		// Navegar a https://demoqa.com/nestedframes
		// Leer los textos de las cajas e imprimirlos por consola
		System.out.println( "Ejercicio 8" );
		
		/* 
		 * iFrames
		 * Para acceder a elementos dentro de un iFrame tienes que moverte a él primero
		 * Hay 3 maneras de moverte:
		 * 		- Usando un WebElement para encontrar el elemento iFrame y luego e mueves
		 * 			//Store the web element
					WebElement iframe = driver.findElement(By.cssSelector("#modal>iframe"));
					//Switch to the frame
					driver.switchTo().frame(iframe);
		 * 		- Usando el nombre o ID del iframe (driver.switchTo().frame("ID or name");)
		 * 		- Usando un índice (driver.switchTo().frame(1); accede al segundo iFrame)
		 * 
		 * Para salir de un iFrame (driver.switchTo().defaultContent();)
		 */
		
		// #################### Initialize variables and browser ####################
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");  
        //ChromeDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();
        // Maximize browser
        driver.manage().window().maximize();
		
		// #################### https://demoqa.com/nestedframes ####################
        // Navegar a https://demoqa.com/nestedframes
        driver.get("https://demoqa.com/nestedframes");

        WebElement descripcionFueraIframe = driver.findElement(By.xpath("//*[contains(text(), 'Sample Nested')]"));
        System.out.println("Imprimiendo el texto descripción fuera de los iframe:\n" + 
        		descripcionFueraIframe + "\n" + descripcionFueraIframe.getText() + "\n");
        
        // Usando WebElement para mover a iFrame
        /*
        WebElement iframe1 = driver.findElement(By.xpath("//*[@id='frame1']"));
        driver.switchTo().frame(iframe1);
        */
        
        // Usando el nombre o ID del iFrame para moverme
        driver.switchTo().frame("frame1");

        WebElement elementoCaja1 = driver.findElement(By.xpath("//*[text()='Parent frame']"));
        System.out.println("Imprimiendo el texto del iframe 1:\n" + 
        		elementoCaja1 + "\n" + elementoCaja1.getText() + "\n");

        // Usando WebElement para mover a iFrame
        /*
        WebElement iframe2= driver.findElement(By.xpath("//*[@srcdoc='<p>Child Iframe</p>']"));
        driver.switchTo().frame(iframe2);
        */
        
        // Usando el índice para moverme al iframe 2, es índice 0 porque dentro del iframe1 solo hay uno más
        // El iframe 1 y 2 no están al mismo nivel, el segundo está dentro del primero
        driver.switchTo().frame(0);
        
        WebElement elementoCaja2 = driver.findElement(By.xpath("//*[text()='Child Iframe']"));
        System.out.println("Imprimiendo el texto del iframe 2:\n" + 
        		elementoCaja2 + "\n" + elementoCaja2.getText() + "\n");
        
        // Salir del iframe hijo y vovler fuera a la página princiapl, sale también del iframe padre
        driver.switchTo().defaultContent();
        //driver.findElement(By.xpath("//*[text()='Parent frame']")); // Esto da error no lo encuentra, está en la página principal
        driver.findElement(By.xpath("//*[contains(text(), 'Sample Nested')]"));
        
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
