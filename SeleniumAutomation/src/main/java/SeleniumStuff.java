
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeleniumStuff {


    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver","src/main/java/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.worldometers.info/coronavirus/");
        driver.manage().window().maximize();


        List<WebElement> theTable = driver.findElements(By.xpath("//*[@id=\"main_table_countries\"]/tbody[1]/tr"));
        System.out.println(theTable.size());
        Thread.sleep(10000);
        List<obj> data = new ArrayList<obj>();
        for(int x = 1; x <= theTable.size(); x++){
            obj aObj = new obj();
            aObj.setCountry(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/div[14]/table/tbody[1]/tr["+x+"]/td[1]")).getText());
            aObj.setTotal_cases(getValue(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/div[14]/table/tbody[1]/tr["+x+"]/td[2]")).getText()));
            aObj.setNew_cases(getValue(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/div[14]/table/tbody[1]/tr["+x+"]/td[3]")).getText()));
            aObj.setTotal_deaths(getValue(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/div[14]/table/tbody[1]/tr["+x+"]/td[4]")).getText()));
            aObj.setNew_deaths(getValue(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/div[14]/table/tbody[1]/tr["+x+"]/td[5]")).getText()));
            aObj.setActive_cases(getValue(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/div[14]/table/tbody[1]/tr["+x+"]/td[6]")).getText()));
            aObj.setTotal_recovered(getValue(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/div[14]/table/tbody[1]/tr["+x+"]/td[7]")).getText()));
            aObj.setSerious_critical(getValue(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/div[14]/table/tbody[1]/tr["+x+"]/td[8]")).getText()));
            data.add(aObj);
        }

        for(obj b : data){
            System.out.println(b.toString());
           addToDB(b);
        }
        Thread.sleep(10000);
        driver.quit();
    }
    private static int getValue(String aStringOrNull){
        if(aStringOrNull.length() > 0 && aStringOrNull != null){
            String formatted = aStringOrNull.replaceAll(",", "");
            return Integer.parseInt(formatted);
        }else
            return 0;
    }

        private static void addToDB(obj aObj) throws ClassNotFoundException {
            long timeToSQL = System.currentTimeMillis();
            Timestamp now = new Timestamp(timeToSQL);
            String sql = "INSERT INTO covid19(country, total_cases,new_cases,total_deaths,new_deaths,active_cases,total_recovered,serious_critical,date_captured) values (?,?,?,?,?,?,?,?,?)";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/data_visualization?autoReconnect=true&useSSL=false", "root", "root");

                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1,aObj.getCountry());
                preparedStatement.setInt(2, aObj.getTotal_cases());
                preparedStatement.setInt(3,aObj.getNew_cases());
                preparedStatement.setInt(4,aObj.getTotal_deaths());
                preparedStatement.setInt(5, aObj.getNew_deaths());
                preparedStatement.setInt(6,aObj.getActive_cases());
                preparedStatement.setInt(7,aObj.getTotal_recovered());
                preparedStatement.setInt(8, aObj.getSerious_critical());
                preparedStatement.setTimestamp(9, now);

                preparedStatement.execute();
                con.close();
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
@Data
public static class obj{
    private String country;
    private int total_cases;
    private int new_cases;
    private int total_deaths;
    private int new_deaths;
    private int active_cases;
    private int total_recovered;
    private int serious_critical;

    public obj(){}

    @Override
    public String toString() {
        return "obj{" +
                "country='" + country + '\'' +
                ", total_cases=" + total_cases +
                ", new_cases=" + new_cases +
                ", total_deaths=" + total_deaths +
                ", new_deaths=" + new_deaths +
                ", active_cases=" + active_cases +
                ", total_recovered=" + total_recovered +
                ", serious_critical=" + serious_critical +
                '}';
    }


}
}