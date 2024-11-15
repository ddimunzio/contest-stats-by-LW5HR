package org.lw5hr.contest.utils;


import org.lw5hr.contest.model.Qso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Author: Diego Dimunzio - LW5HR   
**/

public class CQWWUBNReader {
    public List<String> readUbnFile(final File filePath, List<Qso> qsos) {
        List<String> result = new ArrayList<>();
        BufferedReader reader;
        final int NIL = 1;
        final int INCORRECT_CALL = 2;
        final int INCORRECT_EXCHANGE_INFO = 3;
        final int BAND_CHANGE_VIOLATION = 4;
        final int UNIQUE_CALL = 5;
        final int LOST_MULTI = 5;

       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        int currentReport = 0;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();

            while (line != null) {
                if (currentReport != 0 && !line.isEmpty() && !line.startsWith("***")) {
                    List<String> ubnLine = Arrays.stream(line.split(" ")).filter(a -> !Objects.equals(a, "")).toList();
                    LocalDateTime UbnDateTime = LocalDateTime.parse(ubnLine.get(2) + " " + ubnLine.get(3), formatter);
                    Optional<Qso> qso = qsos.stream()
                            .filter(q -> q.getCall().equalsIgnoreCase(ubnLine.get(6)))
                            .filter(q -> UbnDateTime.getHour() == q.getTime().getHour())
                            .filter(q -> UbnDateTime.getMinute() == q.getTime().getMinute()).findFirst();
                    if (qso.isPresent()) {
                        if (currentReport == INCORRECT_CALL) {
                            System.out.println(qso.get().toString() + "," + ubnLine.get(9));
                            result.add(qso.get().toString() + "," + ubnLine.get(9));
                        } else if (currentReport == INCORRECT_EXCHANGE_INFO) {
                            System.out.println(qso.get().toString() + "," + ubnLine.get(7) + "," + ubnLine.get(9));
                            result.add(qso.get().toString() + "," + ubnLine.get(7) + "," + ubnLine.get(9));
                        } else {
                            System.out.println(qso.get().toString());
                            result.add(qso.get().toString());
                        }
                    }
                }
                // read next line
                line = reader.readLine();
                if (line != null) {
                    if (line.equalsIgnoreCase("************************* Not In Log *************************")) {
                        System.out.println("************************* Not In Log *************************");
                        result.add("************************* Not In Log *************************");
                        currentReport = NIL;
                        line = reader.readLine();
                    } else if (line.equalsIgnoreCase("*********************** Incorrect Call ***********************")) {
                        System.out.println("*********************** Incorrect Call ***********************");
                        result.add("*********************** Incorrect Call ***********************");
                        currentReport = INCORRECT_CALL;
                        line = reader.readLine();
                    } else if (line.equalsIgnoreCase("*************** Incorrect Exchange Information ***************")) {
                        System.out.println("*************** Incorrect Exchange Information ***************");
                        result.add("*************** Incorrect Exchange Information ***************");
                        currentReport = INCORRECT_EXCHANGE_INFO;
                        line = reader.readLine();
                    } else if (line.equalsIgnoreCase("******************* Band Change Violations *******************")) {
                        System.out.println("******************* Band Change Violations *******************");
                        result.add("******************* Band Change Violations *******************");
                        currentReport = BAND_CHANGE_VIOLATION;
                        ;
                        line = reader.readLine();
                    } else if (line.equalsIgnoreCase("********* Unique Calls Receiving Credit (not removed)*********")) {
                        System.out.println("********* Unique Calls Receiving Credit (not removed)*********");
                        result.add("********* Unique Calls Receiving Credit (not removed)*********");
                        currentReport = UNIQUE_CALL;
                        line = reader.readLine();
                    } else if (line.equalsIgnoreCase("********************** Lost Multipliers **********************")) {
                        System.out.println("********************** Lost Multipliers **********************");
                        currentReport = LOST_MULTI;
                        line = reader.readLine();
                    } else if (line.equalsIgnoreCase("*******************  Multipliers by Band  ********************")) {
                        currentReport = 0;
                    }
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

