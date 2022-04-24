package com.tests.integration;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class IntegrationTest {

    public boolean isMuted = false;

    //public BufferedReader br;
    public BufferedWriter bw;
    public Scanner inputScanner;

    public String output;

    public IntegrationTest(boolean isMuted) {
        this.isMuted = isMuted;
        ProcessBuilder builder = JavaProcess.exec(com.csb.Main.class, List.of("-Xmx200m"), List.of(""));
        builder.redirectErrorStream(true);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStreamReader isr = new InputStreamReader(process.getInputStream());
        //br = new BufferedReader(isr);
        inputScanner = new Scanner(isr);

        OutputStreamWriter osw = new OutputStreamWriter(process.getOutputStream());
        bw = new BufferedWriter(osw);
    }

    public void runTest(String input) {
        try {
            bw.write(input);
            bw.flush();
            bw.close();
            //bw.newLine();
            //bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line = "";
        while (inputScanner.hasNextLine()) {
            line = inputScanner.nextLine();
            if (!isMuted) {
                System.out.println("\t[Child process] " + line);
            }
            output += line + "\n";
        }
    }

    public String getOutput() {
        return output;
    }
}
