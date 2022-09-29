package bjetpro;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class ConnectionDriver extends ChromeDriver {

    public ConnectionDriver(){
        this.manage().window().setSize(new Dimension(1850, 1053));
        this.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
