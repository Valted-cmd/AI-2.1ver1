package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CardOrder {

    private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {

        WebDriverManager.chromedriver().setup();


    }

    @BeforeEach
    void setUp2() {    // запускается перед каждым тестом
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach     // закрывает все окна браузера
    public void close() {
        driver.quit();
        driver = null;
    }

    @Test
    public void test() {
        driver.get("http://localhost:9999/");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иван Петров-Иванов");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79867281447");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=\"order-success\"]")).getText();

        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

}