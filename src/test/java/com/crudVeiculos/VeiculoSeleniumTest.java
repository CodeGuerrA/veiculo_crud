package com.crudVeiculos;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VeiculoSeleniumTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:8080/crud-veiculo.html");

            // Exemplo de veículos
            String[][] veiculos = {
                    {"Carro Teste", "Toyota", "Preto", "ABC-1234"},
                    {"Moto Rapida", "Honda", "Vermelho", "XYZ-5678"},
                    {"Fusca Clássico", "Volkswagen", "Azul", "FSC-4321"}
            };

            for (String[] v : veiculos) {
                // Preenche cada campo como humano
                sendKeysHuman(driver.findElement(By.id("descricao")), v[0]);
                sendKeysHuman(driver.findElement(By.id("fabricante")), v[1]);
                sendKeysHuman(driver.findElement(By.id("cor")), v[2]);
                sendKeysHuman(driver.findElement(By.id("placa")), v[3]);

                // Clica no botão "Salvar"
                driver.findElement(By.xpath("//button[text()='Salvar']")).click();

                // Espera 2 segundos antes do próximo
                Thread.sleep(2000);

                // Limpa os campos para o próximo veículo
                driver.findElement(By.id("descricao")).clear();
                driver.findElement(By.id("fabricante")).clear();
                driver.findElement(By.id("cor")).clear();
                driver.findElement(By.id("placa")).clear();
            }

        } finally {
            driver.quit();
        }
    }
    private static void sendKeysHuman(WebElement element, String text) throws InterruptedException {
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            Thread.sleep(150); // 150ms entre cada letra
        }
    }
}
