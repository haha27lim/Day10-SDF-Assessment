package assessment.task01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Check if the correct number of arguments was provided
        if (args.length != 2) {
            System.out.println("Usage: java MailMerge <csv_file> <template_file>");
            return;
        }

        // Read the file names from the arguments
        String csvFileName = args[0];
        String templateFileName = args[1];

        // Read the CSV file and store the data in a list of maps
        List<Map<String, String>> data = readCsvFile(csvFileName);
        if (data == null) {
            return;
        }

        // Read the template file and store the contents in a string
        String template = readTemplateFile(templateFileName);
        if (template == null) {
            return;
        }

        // Process each line of the CSV file and generate a filled-in template for each line
        int index = 1;
        for (Map<String, String> lineData : data) {
            // Fill in the template with the data from the current line of the CSV file
            String filledTemplate = fillTemplate(template, lineData);

            // Write the filled-in template to a separate output file
            String outputFileName = "output" + index + ".txt";
            writeOutputFile(outputFileName, filledTemplate);

            index++;
        }
    }

    private static List<Map<String, String>> readCsvFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Read the header line to get the field names
            String headerLine = reader.readLine();
            String[] fieldNames = headerLine.split(",");

            // Read the rest of the lines and store the data in a list of maps
            List<Map<String, String>> data = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> lineData = new HashMap<>();
                for (int i = 0; i < fieldNames.length; i++) {
                    lineData.put(fieldNames[i], values[i]);
                }
                data.add(lineData);
            }

            return data;
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
            return null;
        }
    }

    private static String readTemplateFile(String fileName) {
      try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
          StringBuilder template = new StringBuilder();
          String line;
          while ((line = reader.readLine()) != null) {
              template.append(line).append("\n");
          }
          return template.toString();
      } catch (IOException e) {
          System.out.println("Error reading template file: " + e.getMessage());
          return null;
      }
  }

  private static String fillTemplate(String template, Map<String, String> data) {
      String filledTemplate = template;
      for (Map.Entry<String, String> entry : data.entrySet()) {
          String fieldName = entry.getKey();
          String value = entry.getValue();
          filledTemplate = filledTemplate.replaceAll("<<" + fieldName + ">>", value);
      }
      return filledTemplate;
  }

  private static void writeOutputFile(String fileName, String content) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
          writer.write(content);
      } catch (IOException e) {
          System.out.println("Error writing output file: " + e.getMessage());
      }
  }
}
