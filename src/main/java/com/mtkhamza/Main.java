package com.mtkhamza;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("No text input file was provided !");
        }

        String inputFile = args[0];

        try (
                InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(inputFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line = reader.readLine();
            String[] plateauCoordinates = line.split(" ");
            int xPlateau = Integer.parseInt(plateauCoordinates[0]);
            int yPlateau = Integer.parseInt(plateauCoordinates[1]);

            while ((line = reader.readLine()) != null) {
                int xRover = Integer.parseInt(line.split(" ")[0]);
                int yRover = Integer.parseInt(line.split(" ")[1]);
                char roverDirection = line.split(" ")[2].charAt(0);
                String command = reader.readLine();

                Rover rover = new Rover(xRover, yRover, Direction.fromCode(roverDirection));
                rover.processCommand(command, xPlateau, yPlateau);

                System.out.println(rover);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}