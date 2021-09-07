package desafio.grupo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class atcPaquetes {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void init(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @After
    public void close(){
        if(driver != null){
            driver.close();
        }
    }

    @Test
    public void tc_009() {
        driver.get("https://www.viajesfalabella.cl/");
        WebElement cOrigen = driver.findElement(By.xpath("//input[contains(@class,'sbox-places-first sbox-origin-container')]"));
        WebElement cDestino = driver.findElement(By.xpath("//input[contains(@class,'sbox-places-second')]"));
        WebElement btnBuscar = driver.findElement(By.xpath("//a[contains(@class, 'sbox-search')]"));
        WebElement checkboxSinFecha = driver.findElement(By.xpath("//body/app-root[1]/app-searchbox-container[1]/div[1]/app-searchbox[1]/div[2]/div[1]/div[1]/div[3]/div[2]/div[9]/span[1]/label[1]/span[1]"));
        By localizadorOpcion = By.xpath("//body/div[11]/div[1]/div[1]/ul[1]/li[1]");
        By localizadorCheckbox = By.xpath("//*[contains(@class, 'switch-container')]");
        String origen = "Buenos Aires";
        String destino = "Isla de Pascua";

        // 3- Introducir "Buenos Aires" en el campo origen y presionar la tecla enter.
        String opcionOrigen = seleccionarCiudadBusqueda(cOrigen, localizadorOpcion, origen);
        Assert.assertEquals("Buenos Aires, Ciudad de Buenos Aires, Argentina",opcionOrigen);

        // 5- Introducir "Isla de Pascua" en el campo destino y presionar la tecla enter.
         String opcionDestino = seleccionarCiudadBusqueda(cDestino,localizadorOpcion,destino);
        Assert.assertEquals("Isla de Pascua, Valparaíso, Chile",opcionDestino);

        // 6- Hacer click en el campo Todavía no elegí fecha.
        wait.until(ExpectedConditions.elementToBeClickable(localizadorCheckbox));
        checkboxSinFecha.click();


        // 7- Hacer click en el botón Buscar.
        btnBuscar.click();
        wait.until(ExpectedConditions.urlContains("paquetes-a-isla-de-pascua-desde-buenos-aires"));
        Assert.assertTrue(driver.getCurrentUrl().contains("paquetes-a-isla-de-pascua-desde-buenos-aires"));
    }

    @Test
    public void tc_010(){
        driver.get("https://www.viajesfalabella.cl/");
        WebElement btnVueloAuto = driver.findElement(By.xpath("//*[@id=\"sboxContainer-packages\"]/div/div/div[3]/div[1]/div[2]/div/div/span[3]/input"));
        WebElement cOrigen = driver.findElement(By.xpath("//input[contains(@class, 'sbox-places-first sbox-origin-container')]"));
        WebElement cDestino = driver.findElement(By.xpath("//input[contains(@class, 'sbox-places-second')]"));
        WebElement btnBuscar = driver.findElement(By.xpath("//a[contains(@class, 'sbox-search')]"));
        WebElement fechaIda = driver.findElement(By.xpath("//input[@placeholder='Ida']"));
        By localizadorOpcion = By.xpath("/html/body/div[11]/div/div[1]/ul/li[1]");
        String origen = "Buenos Aires";
        String destino = "Bariloche";

        // 2- Hacer click en botón “Vuelo + Auto”
        btnVueloAuto.click();

        // 4- Introducir "Buenos Aires" en el campo origen y presionar la tecla enter.
        String textOrigen = seleccionarCiudadBusqueda(cOrigen,localizadorOpcion,origen);
        Assert.assertEquals("Buenos Aires, Ciudad de Buenos Aires, Argentina",textOrigen);

        //6- Introducir "Bariloche" en el campo destino y presionar la tecla enter.
        String textDestino = seleccionarCiudadBusqueda(cDestino,localizadorOpcion,destino);
        Assert.assertEquals("San Carlos de Bariloche, Rio Negro, Argentina",textDestino);

        // 7- Seleccionar campo fecha desde.  8- Seleccionar fecha 3 dic 2021.
        fechaIda.click();
        WebElement btnNext = driver.findElement(By.xpath("//body/div[5]/div[1]/div[2]/div[2]"));
        for (int i =1;i<=2;i++) btnNext.click();
        driver.findElement(By.xpath("//body/div[5]/div[1]/div[5]/div[4]/div[4]/span[6]")).click();
        driver.findElement(By.xpath("//body/div[5]/div[1]/div[5]/div[4]/div[4]/span[16]")).click();
        driver.findElement(By.xpath("/html/body/div[7]/div/div[6]/div[2]/button[2]")).click();

        // 11- Hacer click en el botón Buscar.
         btnBuscar.click();
        wait.until(ExpectedConditions.urlContains("/trip/flight/"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/trip/flight/"));
    }

    @Test
    public void tc_011() {
        driver.get("https://www.viajesfalabella.cl/");
        WebElement btnVueloDosAlojamientos = driver.findElement(By.xpath("//input[@value='vhh']"));
        WebElement cOrigen = driver.findElement(By.xpath("//input[contains(@class, 'sbox-places-first sbox-origin-container')]"));
        WebElement cDestino = driver.findElement(By.xpath("//input[contains(@class, 'sbox-places-second')]"));
        WebElement fechaIda = driver.findElement(By.xpath("//input[@placeholder='Ida']"));
        WebElement btnBuscar = driver.findElement(By.xpath("//a[contains(@class, 'sbox-search')]"));
        By localizadorOpcion = By.xpath("//body/div[11]/div[1]/div[1]/ul[1]/li[1]");
        String origen = "Buenos Aires";
        String destino1 = "Isla Mujeres";
        String destino2 = "Cancun";

        // 2- Hacer click en el bóton Vuelo + 2 Alojamientos.
        btnVueloDosAlojamientos.click();

        // 4- Introducir "Buenos Aires" en el campo origen de la sección Vuelo ida y vuelta y presionar la tecla enter.
        String textOrigen = seleccionarCiudadBusqueda(cOrigen,localizadorOpcion,origen);

        //  6- Introducir "Isla Mujeres" en el campo destino de la sección Vuelo ida y vuelta y presionar la tecla enter.
        String textDestino1 =seleccionarCiudadBusqueda(cDestino,localizadorOpcion,destino1);

        Assert.assertEquals("Buenos Aires, Ciudad de Buenos Aires, Argentina",textOrigen);
        Assert.assertEquals("Isla Mujeres, Quintana Roo, México",textDestino1);

        //8- Seleccionar fecha ida “ 4 oct 2021”. 10- Seleccionar fecha vuelta “ 16 oct 2021”.
        fechaIda.click();
        driver.findElement(By.xpath("/html/body/div[5]/div/div[5]/div[2]/div[4]/span[4]")).click(); // Seleccionamos fecha ida
        driver.findElement(By.xpath("/html/body/div[5]/div/div[5]/div[2]/div[4]/span[16]")).click(); // Seleccionamos fecha vuelta
        driver.findElement(By.xpath("/html/body/div[7]/div/div[6]/div[2]/button[2]")).click();

        //13-  Seleccionar campo fecha desde. 14- Seleccionar fecha desde “ 4 oct 2021”.
        // 15-  Seleccionar campo fecha hasta. 16- Seleccionar fecha hasta “ 9 oct 2021”.
        WebElement fechaHasta = driver.findElement(By.xpath("//input[contains(@class,'sbox-hotel-first-checkout-date')]"));
        fechaHasta.click();
        driver.findElement(By.xpath("//body/div[2]/div[1]/div[5]/div[2]/div[4]/span[9]")).click();
        driver.findElement(By.xpath("//body/div[2]/div[1]/div[6]/div[2]/button[2]")).click();

        //  17- Seleccionar campo Segundo destino. 18- Introducir "Cancún" en el campo Segundo destino de la sección Elige donde alojarte y presionar la tecla enter.
        WebElement segundoDestino = driver.findElement(By.xpath("//input[contains(@class,'sbox-hotel-second-destination')]"));
        String textDestino2 =seleccionarCiudadBusqueda(segundoDestino,localizadorOpcion,destino2);
        Assert.assertEquals("Cancún, Quintana Roo, México",textDestino2);
        //  19- Hacer click en el botón Buscar.
        btnBuscar.click();

        // 20- Hacer click en el botón Ver resumen.
        By localizadorBtnResumen = By.xpath("//body[1]/div[13]/div[1]/div[3]/div[1]/div[2]/div[1]/div[2]");
        wait.until(ExpectedConditions.elementToBeClickable(localizadorBtnResumen));
        driver.findElement(localizadorBtnResumen).click();
        WebElement resumen = driver.findElement(By.xpath("//*[@id=\"pkg-wizard\"]/div/div[4]"));
        WebElement headerResumen = resumen.findElement(By.xpath("//body/div[@id='pkg-wizard']/div[1]/div[4]/div[1]/div[1]/div[1]"));
        Assert.assertEquals("El resumen de tu viaje", headerResumen.getText());
    }

    @Test
    public void tc_012() throws InterruptedException {
        driver.get("https://www.viajesfalabella.cl/");

        // 2- Seleccionar paquete “Montevideo saliendo de Santiago de Chile” de los paquetes sugeridos en la página.
        WebElement paqueteMVD = driver.findElement(By.xpath("/html/body/app-root/div/app-offers/div/div[2]/div[2]/div/swipper/div[1]/div/div[2]/a"));
        paqueteMVD.click();
        wait.until(ExpectedConditions.urlContains("trip/accommodations/results/"));
        Assert.assertTrue(driver.getCurrentUrl().contains("trip/accommodations/results/"));

        WebElement fechaIda = driver.findElement(By.xpath("//input[@placeholder='Ida']"));
        WebElement habitaciones = driver.findElement(By.xpath("//body/aloha-app-root[1]/aloha-results[1]/div[1]/div[1]/div[2]/div[1]/aloha-old-research[1]/div[1]/div[1]/div[1]/div[3]/div[2]/div[5]/div[1]/div[1]"));

        // 4- Seleccionar fecha desde 6 dic 2021
        fechaIda.click();
        driver.findElement(By.xpath("//body/div[7]/div[1]/div[5]/div[4]/div[4]/span[6]")).click();
        // 6- Seleccionar fecha hasta 16 dic 2021
        driver.findElement(By.xpath("//body/div[7]/div[1]/div[5]/div[4]/div[4]/span[16]")).click();
        driver.findElement(By.xpath("//body/div[7]/div[1]/div[6]/div[2]/button[2]")).click();

        // 7- Hacer click en  "habitaciones".
        habitaciones.click();
        // 8- Seleccionar 1 adulto.
        driver.findElement(By.xpath("//body/div[4]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/a[1]")).click();
        driver.findElement(By.xpath("//body/div[4]/div[1]/div[2]/a[1]")).click();

        // 9- Seleccionar el alojamiento sugerido.  10- Hacer click en el botón siguiente.
        WebElement alojamiento = driver.findElement(By.xpath("//body/aloha-app-root[1]/aloha-results[1]/div[1]/div[1]/div[2]/div[2]/div[2]/aloha-list-view-container[1]/div[2]/aloha-cluster-container[1]/div[1]"));
        alojamiento.findElement(By.xpath("//*[@class='eva-3-btn -md -primary -eva-3-fwidth']")).click();

        //TODO WAIT
        Thread.sleep(2000);

        // CAMBIO PESTAÑA
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        Thread.sleep(5000);
        //TODO Wait

        /*
        *   8- El campo "habitaciones"  debe tener el valor 1 para "Adulto"
            12-Se carga la página de vuelos.
            14- Se carga la página de adicionales.
            16- Se muestra el mensaje “Quitaste el transfer de tu paquete”.
            19- Se muestra una ventana con el titulo “Tu itinerario de viaje” y los datos de la reserva.
        */
        Assert.assertEquals("Esplendor by Wyndham Cervantes", driver.getTitle());
        // 11- Seleccionar habitación “Habitación Doble Estándar con Minibar”.
        driver.findElement(By.xpath("//body[1]/aloha-app-root[1]/aloha-detail[1]/div[1]/div[4]/div[1]/div[2]/aloha-roompacks-container[1]/aloha-roompacks-grid-container[1]/div[2]/div[1]/aloha-roompacks-group-container[2]/div[2]/aloha-roompack-container[1]/div[4]/aloha-roompack-selection[1]")).click();
        //12- Hacer click en el botón siguiente.
        driver.findElement(By.xpath("//body[1]/aloha-app-root[1]/aloha-detail[1]/div[1]/div[4]/div[1]/div[2]/aloha-roompacks-container[1]/aloha-roompacks-grid-container[1]/div[2]/div[2]/aloha-reservation-summary-container[1]/div[1]/aloha-next-step-button[1]/aloha-button[1]")).click();

        Thread.sleep(4000);
        wait.until(ExpectedConditions.urlContains("/trip/flight/"));

        //13- Seleccionar vuelo sugerido como primer resultado de busqueda.
        WebElement vuelo = driver.findElement(By.xpath("//body/div[@id='flights-container-wrapper']/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/span[3]/trips-cluster-selected[1]/span[1]/cluster[1]/div[1]/div[1]"));
        //14- Hacer click en el botón siguiente.
        vuelo.findElement(By.xpath("//body/div[@id='flights-container-wrapper']/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/span[3]/trips-cluster-selected[1]/span[1]/cluster[1]/div[1]/div[1]/div[2]/fare[1]/span[1]/span[1]/div[2]/buy-button[1]/a[1]")).click();
        //TODO WAIT
        Thread.sleep(5000);
        By localizadorAviso = By.xpath("//body/app-root[1]/app-toasts-container[1]/div[1]");
        //15- Seleccionar quitar traslado compartido.
        driver.findElement(By.xpath("//body/app-root[1]/div[1]/div[3]/div[1]/app-highlighted-products[1]/div[1]/div[2]/div[2]/div[1]/app-transfer[1]/div[1]/div[1]/div[2]/span[1]/div[1]/a[1]")).click();
        //16- Presionar botón quitar.
        driver.findElement(By.xpath("//body/app-root[1]/div[1]/app-confirm-delete-modal[1]/div[1]/div[3]/button[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(localizadorAviso));
        String aviso = driver.findElement(By.xpath("//body/app-root[1]/app-toasts-container[1]/div[1]/div[1]/app-toast[1]/div[1]/div[2]")).getText();
        //Thread.sleep(2000);
        Assert.assertEquals("Quitaste el transfer de tu paquete",aviso);


        Thread.sleep(4000);
        // 17- Seleccionar agregar “Auto Premium”.
        driver.findElement(By.xpath("//body/app-root[1]/div[1]/div[3]/div[2]/div[2]/app-products-carousel[1]/div[2]/app-carousel[1]/div[1]/swiper[1]/div[1]/div[2]")).click();
        // 18- Presionar botón agregar.
        driver.findElement(By.xpath("//body/app-root[1]/div[1]/app-transfer-modal[1]/div[1]/div[3]/app-modal-pricebox-sticky[1]/div[1]/div[2]/a[1]")).click();

        Thread.sleep(4000);
        // 19- Hacer click en botón ver viaje.
        driver.findElement(By.xpath("//body/app-root[1]/div[1]/div[1]/app-wizard-ab[1]/wizard[1]/div[1]/div[2]/wizard-step[3]/div[1]/div[1]/div[1]/a[1]")).click();
        Thread.sleep(2000);
        String titulos = driver.findElement(By.xpath("//body/app-root[1]/div[1]/div[1]/app-wizard-ab[1]/wizard[1]/wizard-modal[1]/div[1]/div[1]/div[1]")).getText();
        String titulo = titulos.split("\n")[0];
        Assert.assertEquals("Tu itinerario de viaje",titulo);
    }

    //Función para completar los campos de busqueda de ciudades.
    private String seleccionarCiudadBusqueda(WebElement element, By localizador, String ciudad) {
        element.sendKeys(ciudad);
        wait.until(ExpectedConditions.elementToBeClickable(localizador));
        String text= driver.findElement(localizador).getText();
        element.sendKeys(Keys.ENTER);
        return text;
    }


}
