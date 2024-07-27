package com.eightpotatoes.nexters.scraping

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.jsoup.Jsoup
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.chrome.ChromeDriverService
import java.io.FileOutputStream
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

fun main() {
    // URL 설정
    val url = "https://m.place.naver.com/place/13503807/home"

    // Webdriver headless 모드 설정
    System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver") // chromedriver 경로 설정
    val options = ChromeOptions()
    // options.addArguments("headless") // 필요 시 헤드리스 모드 활성화
    options.addArguments("window-size=1920x1080")
    options.addArguments("disable-gpu")

    // WebDriver 초기화
    val driverService = ChromeDriverService.createDefaultService()
    val driver: WebDriver = ChromeDriver(driverService, options)
    // 암시적 대기 설정 (Duration 사용)
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30))
    driver.get(url)

    // 페이지 다운
    driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN)
    Thread.sleep(2000)

    // 페이지 소스 가져오기
    val html = driver.pageSource
    driver.quit()

    val doc = Jsoup.parse(html)

    // 별점 추출
    val ratingElement = doc.selectFirst("div.dAsGb span.PXMot.LXIwF span.place_blind")
    val rating = ratingElement?.nextSibling()?.toString()?.trim() ?: "N/A"
    println("별점: $rating")

    // 메뉴와 가격 추출
    val menuItems = doc.select("div.vV_z_ ul.Jp8E6.a0hWz li")

    // Excel 파일 생성
    val workbook = XSSFWorkbook()
    val sheet = workbook.createSheet("output")
    val header = sheet.createRow(0)
    header.createCell(0).setCellValue("menu")
    header.createCell(1).setCellValue("price")

    var rowIndex = 1
    for (menuItem in menuItems) {
        val menu = menuItem.selectFirst("span.A_cdD")?.text() ?: "N/A"
        val price = menuItem.selectFirst("div.CLSES em")?.text()?.replace(",", "")?.replace("원", "") ?: "N/A"

        println("메뉴: $menu / 가격: $price")

        val row = sheet.createRow(rowIndex++)
        row.createCell(0).setCellValue(menu)
        row.createCell(1).setCellValue(price)
    }

    // 파일 저장
    val now = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")
    val fileName = "naver_place_${now.format(formatter)}.xlsx"
    FileOutputStream(fileName).use { workbook.write(it) }

    println("엑셀 파일 저장 완료: $fileName")
}