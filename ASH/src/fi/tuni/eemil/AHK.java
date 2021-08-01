package fi.tuni.eemil;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class AHK{
    int min, max, amountofClicks;
    int delay;
    char stop;
    boolean clicking = false;
    Robot r = new Robot();
    Scanner s = new Scanner(System.in);
    public AHK() throws AWTException, InterruptedException {
        System.out.println("ANNA MIN AIKA");
        min = Integer.parseInt(s.nextLine());
        System.out.println("ANNA MAX AIKA");
        max = Integer.parseInt(s.nextLine());
        System.out.println("ANNA NÄPPÄIN JOKA PYSÄYTTÄÄ JA KÄYNNISTÄÄ AUTOCLICK (1! NÄPPÄIN)");
        stop = s.nextLine().charAt(0);
        ahk();
    }

    public void ahk() throws InterruptedException {
        System.out.println("MIN AIKA KLIKKAUKSEN VÄLILLÄ " + min + " JA MAX " + max);
        System.out.println("AUTOKLIKKERI EI OLE PÄÄLLÄ PAINA " + stop + " JA ENTER KÄYNNISTÄÄKSESI SEN!");
        if(s.nextLine().charAt(0) == stop) {
            clicking = true;
        }
        if(clicking) {
            clickeri();
        }
    }

    public void clickeri() throws InterruptedException {
        System.out.println("KLIKKERI KÄYNNISTYY, PAINA " + stop + " JA ENTER MISSÄ VAIN VAIHEESSA PYSÄYTTÄÄKSESI SEN!");

        Point p = MouseInfo.getPointerInfo().getLocation(), y;
        while(true) {
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            amountofClicks++;
            delay = ThreadLocalRandom.current().nextInt(min, max + 1);
            System.out.println("CURRENT CLICKDELAY: " + delay);
            System.out.println("AMOUNT OF CLICKS: " + amountofClicks);
            y = MouseInfo.getPointerInfo().getLocation();
            Thread.sleep(delay);
            if(Math.round(y.getX()+y.getY()) != Math.round(p.getX()+p.getY())) {
                System.out.println("Hiirtä liikutettu, jatketaan klikkausta 3 sekunnin kuluttua");
                Thread.sleep(3000);
                p = y;
            }
        }
    }
}
