package fi.tuni.eemil;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class AHK{
    int min = 350, max = 450, amountofClicks, delay;
    boolean clicking = true;
    Robot r = new Robot();
    Scanner s = new Scanner(System.in);
    public AHK() throws AWTException, InterruptedException {
        ahk();
    }

    public void ahk() throws InterruptedException {
        System.out.println("MIN AIKA KLIKKAUKSEN VÄLILLÄ " + min + " JA MAX " + max);
        s.nextLine();
        clickeri();
    }

    public void clickeri() throws InterruptedException {
        Point p = MouseInfo.getPointerInfo().getLocation(), y;
        while(true) {
            while(clicking){
                y = MouseInfo.getPointerInfo().getLocation();
                if(Math.round(y.getX()+y.getY()) != Math.round(p.getX()+p.getY())) {
                    p = y;
                    clicking = false;
                    System.out.println("HERE");
                    break;
                }
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            amountofClicks++;
            delay = ThreadLocalRandom.current().nextInt(min, max + 1);
            System.out.println("AMOUNT OF CLICKS: " + amountofClicks);
            System.out.println("CURRENT CLICKDELAY: " + delay);
            Thread.sleep(delay);
            }
            Thread.sleep(550);
            y = MouseInfo.getPointerInfo().getLocation();
            if(Math.round(y.getX()+y.getY()) == Math.round(p.getX()+p.getY()) && !clicking) {
                clicking = true;
            }else if(Math.round(y.getX()+y.getY()) != Math.round(p.getX()+p.getY())) {
                p = y;
            }

        }

    }
}
