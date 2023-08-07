package pl.wartego.javafxtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class NetworkIP {
    public String getIP() throws Exception {
        String urlString = "http://checkip.amazonaws.com/";
        URL url = new URL(urlString);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //todo
}


