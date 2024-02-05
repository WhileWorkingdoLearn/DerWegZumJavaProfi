package org.der.weg.zum.java.profi.ApplikationsBausteine;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

record HighScore(String name, int points, int level, LocalDate day) {}

public final class HighscoreEinlesen {
    private static final Logger LOGGER = LogManager.getLogManager().getLogger("HighscoreEinlesen");

    public static List<HighScore> readHighscoreFromCSV(final String filename){
            final List<HighScore> highScores = new LinkedList<HighScore>();

            try{
                final var lines = Files.readAllLines(Paths.get(filename), StandardCharsets.ISO_8859_1);
                lines.forEach(line -> {
                    final Optional<HighScore> optionalHighScore = extractHighscoreFrom(line);
                    optionalHighScore.ifPresent(highScore -> highScores.add(highScore));
                });
            }catch (final IOException ioException){
              //  LOGGER.warning("processing of file '" + filename + "' failed:" + ioException);
            }

            return highScores;
    }

    private static Optional<HighScore> extractHighscoreFrom(String line) {
        final int VALUE_COUNT = 4;
        final var values = line.split(";|,");

        if(isEmptyLineOrComment(values)|| values.length != VALUE_COUNT){
            return Optional.empty();
        }

        try{
            final var name = values[0].trim();
            final int points = Integer.parseInt(values[1].trim());
            final int level = Integer.parseInt(values[2].trim());
            final String dateAsString = values[3].trim();

            final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            final LocalDate date = LocalDate.parse(dateAsString,dateTimeFormatter);

            return Optional.of(new HighScore(name,points,level,date));

        } catch (final NumberFormatException nfe){
           // LOGGER.warning("Skipping invalid points or level value: '" + line + "'");
        } catch (final DateTimeParseException dtpe){
          //  LOGGER.warning("Skipping invalid date value: '" + line + "'");
        }
        return Optional.empty();
    }

    private static boolean isEmptyLineOrComment(String[] values) {
        return (values.length == 1 && values[0].trim().length() == 0) || values.length >= 1 && values[0].trim().startsWith("#");
    }

    public static void main(String[] args) {
        final var path = "src/main/resources/Test/Highscore.csv";
        final var highscores = HighscoreEinlesen.readHighscoreFromCSV(path);

        highscores.forEach(System.out::println);
    }
}
