package com.tests.integration;

import java.io.*;
import java.util.List;

public class IntegrationTest {

    public boolean isMuted = false;

    public BufferedReader br;
    public BufferedWriter bw;

    public String output;

    public IntegrationTest() {
        ProcessBuilder builder = JavaProcess.exec(com.csb.Main.class, List.of("-Xmx200m"), List.of(""));
        builder.redirectErrorStream(true);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStreamReader isr = new InputStreamReader(process.getInputStream());
        br = new BufferedReader(isr);

        OutputStreamWriter osw = new OutputStreamWriter(process.getOutputStream());
        bw = new BufferedWriter(osw);
    }

    public void runTest(String input) {
        try {
            bw.write(input);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line;
        try {
            while ((line = br.readLine()) != null) {
                if (!isMuted) {
                    System.out.println("[Child process] " + line);
                }
                output += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getOutput() {
        return output;
    }
}
