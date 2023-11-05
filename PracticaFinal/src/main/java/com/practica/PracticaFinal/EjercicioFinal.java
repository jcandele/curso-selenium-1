package com.practica.PracticaFinal;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

public class EjercicioFinal {

	public static void main(String[] args) {
		
		// #################### Inicializa variables y el navegador ####################
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");  
        //ChromeDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();
        // Maximiza el navegador
        driver.manage().window().maximize();
        // Se crea una espera explícita
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        // Producto a buscar
        // Se busca un producto específico, para que al seleccionar el enlace de amazon muestre un producto concreto con su precio y su fecha de entrega
        String productoBuscadoString = "Bosch Bomba de Aire Bicicleta";
        //String productoBuscadoString = "Apple iPhone 13 (128 GB)";
        //String productoBuscadoString = "Apple Watch Series 7 (GPS, 45mm)";
        //String productoBuscadoString = "Monopoly Súper Recompensas";
        //String productoBuscadoString = "Premium Supersonic Secador de pelo, 1600W";
        System.out.println("Producto buscado: " + productoBuscadoString + "\n");
        
        try {
	        // #################### 1- Accede a Google con el navegador Chrome ####################
	        // Navegar a https://www.google.es
	        driver.get("https://www.google.es");
	        // Aceptar la cookies
	        driver.findElement(By.xpath("//*[text()='Aceptar todo']")).click();
	        
	        // #################### 2- Busca un producto ####################
	        WebElement buscadorGoogle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("APjFqb")));
	        buscadorGoogle.sendKeys(productoBuscadoString);
	        buscadorGoogle.submit();
	        
	        // #################### 3- En los resultados accede al enlace de Amazon ####################
	        WebElement enlaceAmazon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Amazon' or text()='Amazon.es']//following::h3[contains(text(), '" + productoBuscadoString + "')]")));
	        new Actions(driver).moveToElement(enlaceAmazon).perform();
	        enlaceAmazon.click();
	        
	        //WebElement botonAceptarCookies = wait.until(ExpectedConditions.elementToBeClickable(By.id("sp-cc-accept")));
	        WebElement botonAceptarCookies = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sp-cc-accept")));
	        botonAceptarCookies.click();
	        //driver.findElement(By.xpath("//*[text()='Aceptar cookies']")).click();
	        
	        // #################### 4- Imprime por pantalla el precio y la fecha de entrega ####################
	        // Se busca un producto específico, para que al seleccionar el enlace de amazon muestre un producto concreto con su precio y su fecha de entrega
	        WebElement precioProducto = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='corePrice_feature_div' or @id='usedBuySection']")));
	        System.out.println("Precio:\n\t" + precioProducto.getText().replace("\n", ","));
	        WebElement fechaEntregaProducto = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deliveryBlock_feature_div")));
	        System.out.println("Fecha entrega:\n\t" + fechaEntregaProducto.getText().replace("\n", "\n\t"));
	        
	        // #################### 5- En el buscador general de amazon vuelve a buscar el producto ####################
	        WebElement buscadorAmazon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='twotabsearchtextbox']")));
	        buscadorAmazon.sendKeys(productoBuscadoString);
	        buscadorAmazon.submit();
	        
	        // #################### 6- Filtrar por entregas prime ####################
	        // No aparece el filtro de entregas Prime como tal
	        // Se marca el filtro de "Envío gratis por Amazon", que es el equivalente
	        WebElement checkBoxEnvioGratis = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("p_n_free_shipping_eligible/20930980031")));
	        if (!checkBoxEnvioGratis.isSelected())
	        	checkBoxEnvioGratis.click();
	        
	        // Se espera para que se marque y refresce la página con el filtro seleccionado
	        try {        	
	        	Thread.sleep(5000); // ms
			} catch (InterruptedException ie) {
				System.out.println("ERROR: " + ie.getMessage().toString());
			}
	        
	        // #################### 7- Ordena por precio de mas bajo a más alto ####################
	        WebElement dropdownOrdenar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("s-result-sort-select")));
	        Select dropdown = new Select(dropdownOrdenar);
	        dropdown.selectByValue("price-asc-rank");
	        
	        // Se espera para que se marque y refresce la página con el filtro seleccionado
	        try {        	
	        	Thread.sleep(5000); // ms
			} catch (InterruptedException ie) {
				System.out.println("ERROR: " + ie.getMessage().toString());
			}
	        
	        // #################### 8- ####################
	        // Imprime por pantalla el nombre de los productos de la primera pantalla y el precio, solo imprime los productos que se han encontrado no 
	        // los sugeridos, ni las búsquedas antiguas.
	        
	        List<WebElement> listaProductos = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@data-component-type='s-search-result']")));
	        System.out.println("\nTamaño listaProductos=" + listaProductos.size());
	        List<WebElement> listaProductosNombres = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@data-component-type='s-search-result']//following::div[contains(@class, 'title-instructions-style')]")));
	        System.out.println("Tamaño listaProductosNombres=" + listaProductosNombres.size());
	        List<WebElement> listaProductosPrecio = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@data-component-type='s-search-result']//following::div[contains(@class, 'price-instructions-style')]")));
	        System.out.println("Tamaño listaProductosPrecio=" + listaProductosPrecio.size());
	        
	        // Diccionario para guardar el nombre y el precio, por si hubiera que reutilizarlos luego
	        HashMap<String, String> diccionarioProductos = new HashMap<String, String>();
	        
	        // Se recorre la lista de los productos encontrados
	        int j = 0; // Para recorrer la ListaProductosPecio
	        for (int i = 0; i < listaProductosNombres.size(); i++) {
	        	/*
	        	 * Hay dos posibilidades
	        	 * 		1- Todos los productos tienen un precio -> listaProductosNombres.size() == listaProductosPrecio.size()
	        	 * 		2- Noo todos los productos tienen un precio -> listaProductosNombres.size() != listaProductosPrecio.size()
	        	 */
	        	if ( (listaProductosNombres.size() == listaProductosPrecio.size()) ||
	        			(listaProductos.get(i).getText().contains("€") && !listaProductos.get(i).getText().contains("Ofertas destacadas no disponibles")) ) {
	        		System.out.println("Producto [" + i +"]: " + listaProductosNombres.get(i).getText());
	        		System.out.println("\tPrecio: " + listaProductosPrecio.get(j).getText().replace("\n", ","));
	        		diccionarioProductos.put("[" + i + "] " + listaProductosNombres.get(i).getText(), listaProductosPrecio.get(j).getText());
	        		j++;
	        	}
	        	else {
	        		System.out.println("Producto [" + i +"]: " + listaProductosNombres.get(i).getText());
	        		System.out.println("\tPrecio: No tiene precio");
	        		diccionarioProductos.put("[" + i + "] " + listaProductosNombres.get(i).getText(), "No tiene precio");
	        	}
	        }

	        // Imprime el diccionario
	        /*
	        for (String p : diccionarioProductos.keySet()) {
	        	System.out.println("Producto: " + p + "\n\tPrecio: " + diccionarioProductos.get(p).replace("\n", ","));
	        }
	        */
	        /*
	        for (Entry p : diccionarioProductos.entrySet()) {
	        	System.out.println("Producto: " + p.getKey() + "\n\tPrecio: " + p.getValue().toString().replace("\n", ","));
	        }
	        */
        }
        catch (Exception e) {
			System.out.print("\nMensaje de ERROR:\n[\n" + e.getMessage() + "\n]");
		}
        
        // #################### Cierra el navegador ####################
        driver.quit();
	}

}
