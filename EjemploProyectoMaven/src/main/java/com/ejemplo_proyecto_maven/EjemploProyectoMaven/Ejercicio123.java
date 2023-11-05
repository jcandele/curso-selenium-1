package com.ejemplo_proyecto_maven.EjemploProyectoMaven;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;


/**
 * Hello world!
 *
 */
public class Ejercicio123 
{
    public static void main( String[] args )
    {
        // Sugerencias Alejandro Llorente Corral:
        // Crear desde un main y llamar a varias clases
        // PageObject intentar crearlo para la segunda web, crear un page object -> Estudiar el modelo Page Object
        // Lo mejor crear una clase main por cada ejercicio para probarlos de manera separada (así primero y luego irle dando forma creando más clases)
        
        // Crear una clase por ejercicio y meter el código dentro del main de cada clase
        // #################### www.estadiodeportivo.com ####################
        // EJERCICIO 1,2,3:
        // Navegar a www.estadiodeportivo.com -> OK
        // Aceptar las cookies -> OK
        // Click icono escudo Betis -> OK
        // Listar por consola todas las noticias principales -> OK
        // Accede a una noticia -> Ok
        // Listar primera y última noticia del carrusel -> OK
        // Acceder a la última noticia -> OK
    	System.out.println( "Ejecicio 1,2,3 (java y selenium)" );
        
        // #################### Initialize variables and browser ####################
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");  
        ChromeDriver driver = new ChromeDriver();
        // Maximize browser
        driver.manage().window().maximize();
        
        // #################### https://www.estadiodeportivo.com/ ####################
        // Launching website
        driver.get("https://www.estadiodeportivo.com/");
        
        // Aceptar las cookies
        driver.findElement(By.id("acceptAllMain")).click();
        
        // Click icono escudo Betis
        //driver.findElement(By.xpath("//a[@title='Noticias del Real Betis en EstadioDeportivo.com']")).click();
        driver.findElement(By.xpath("//a[@title='Real Betis Balompié']")).click(); // Han actualizado el títutlo
        
        // Listar por consola todas las noticias principales -> Hay 2 noticias principales
        String texto = "";
        texto += "\n########## Listar por consola todas las noticias principales ##########\n";
        //List<WebElement> noticias_principales = driver.findElements(By.className("card pila-v m-foto-completa")); // Da error
        List<WebElement> noticias_principales = driver.findElements(By.xpath("//h2[@class='titular titulo-1']"));
        for (WebElement noticia : noticias_principales) {
        	System.out.println("-noticias_principales: " + noticia.getText());
        	texto += "Noticia:\n" + "\t" + noticia.getText() + "\n";
        }
        
        List<WebElement> noticias_principales2 = driver.findElements(By.xpath("//article[@class='card pila-v m-foto-completa']"));
        for (WebElement noticia : noticias_principales2) {
        	System.out.println("-noticias_principales2: " + noticia.getText());
        	texto += "Noticia detallada:\n" + "\t" + noticia.getText().replace("\n", "\n\t") + "\n";
        }
        texto += "##########";
        System.out.println(texto);
        
        // Accede a una noticia
        noticias_principales.get(0).click(); // Hace click en la primera noticia principal
        
        //  Hay que espera a que se carge el carrusel y las noticias, a que sea visible en pantalla, si no devuelve longitud 0
        // Espera X segundos
        try {        	
        	Thread.sleep(5000); // ms
        	//TimeUnit.SECONDS.sleep(10); // por dentro hace un Thread.sleep(x)
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
        
        // Listar primera y última noticia del carrusel
        WebElement carrusel = driver.findElement(By.xpath("//*[@class='swiper-wrapper']"));
        
        // En python: para mover el puntero (mouse) al elemento localizado
        // action = ActionChains(context.driver)
        // action.move_to_element(element).perform()
        Actions action = new Actions(driver);
        action.moveToElement(carrusel).perform();
        
        List<WebElement> lista_carrusel = driver.findElements(By.xpath("//*[@class='swiper-wrapper']//child::*[@role='group']"));
        // Este XPATH también sirve para los articulos del carrusel: By.xpath("//article[@class='card pila-v m-foto-completa']"
        System.out.println("tamaño lista_carrusel=" + lista_carrusel.size());
        for (WebElement elemento_carrusel : lista_carrusel) {
        	// Solo lee las noticias que ve, como la noticia 6 del carrusel no se muestra en pantalla, no pilla su texto y aparece vacía
        	// Hay que moverse a ella para que sea visible
        	new Actions(driver).moveToElement(elemento_carrusel).perform();
        	System.out.println("lista_carrusel[]= " + elemento_carrusel.getText());
        }
        
        System.out.println("\n");
        for (int i=0; i<lista_carrusel.size(); i++) {
        	// Solo lee las noticias que ve, como la noticia 6 del carrusel no se muestra en pantalla, no pilla su texto y aparece vacía
        	// Hay que moverse a ella para que sea visible
        	new Actions(driver).moveToElement(lista_carrusel.get(i)).perform();
        	System.out.println("lista_carrusel[" + i + "]= " + lista_carrusel.get(i).getText());
        }
        
        texto = "\n########## Listar primera y última noticia del carrusel ##########\n";
        new Actions(driver).moveToElement(lista_carrusel.get(0)).perform();
        texto += "Primera noticia del carrusel:\n\t" + lista_carrusel.get(0).getText().replace("\n", "\n\t") + "\n";
        new Actions(driver).moveToElement(lista_carrusel.get(lista_carrusel.size()-1)).perform();
        texto += "Útima noticia del carrusel:\n\t" + lista_carrusel.get(lista_carrusel.size()-1).getText().replace("\n", "\n\t") + "\n";
        texto += "##########";
        System.out.println(texto);
        
        // Acceder a la última noticia
        lista_carrusel.get(lista_carrusel.size()-1).click();
        
        // Espera X segundos
        try {        	
        	Thread.sleep(2000); // ms
        	//TimeUnit.SECONDS.sleep(10); // por dentro hace un Thread.sleep(x)
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
        
        // #################### Close the browser ####################
        // Close the browser
        driver.quit();
    }
    
    // Se pueden crear funciones privadas dentro de esta misma clase para acceder y reutilizar algunos elementos y sus XPATH
    private void clickarGenero(String string) {
		//WebElement elemento = 
		// Puede devolver el elemento o que directamente clicke
		// Hay que pasarle el driver
    	// Crear una clase que sea pantallaFormulario y llamarla desde los diferentes ejercicios
	}

}
