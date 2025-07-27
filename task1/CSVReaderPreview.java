package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException; // Import for specific exception handling

public class CSVReaderPreview {

    public static void main(String[] args) {
        // Define the path to your CSV file.
        // For Eclipse, place 'dataset.csv' directly in your project's root directory.
        String filePath = "dataset.csv";
        String line; // To store each line read from the file
        String delimiter = ","; // The delimiter used in the CSV file

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            // Read the header line first
            String headerLine = br.readLine();
            if (headerLine == null || headerLine.trim().isEmpty()) {
                System.out.println("The CSV file is empty or only contains blank lines.");
                return;
            }

            System.out.println("=== CSV Data Preview ===\n");

            // Process and display columns
            String[] columns = headerLine.split(delimiter);
            System.out.println("Columns:");
            for (int i = 0; i < columns.length; i++) {
                System.out.print(columns[i].trim()); // Trim whitespace from column names
                if (i < columns.length - 1) {
                    System.out.print(" | "); // Use a pipe as a separator for clarity
                }
            }
            System.out.println("\nTotal columns: " + columns.length);
            System.out.println("\n------------------------\n");

            System.out.println("First 5 Records (excluding header):\n");

            int recordsDisplayedCount = 0;
            // Read and display the first 5 data records
            while ((line = br.readLine()) != null && recordsDisplayedCount < 5) {
                String[] data = line.split(delimiter);
                // Print each data point, trimming whitespace and joining with a tab for alignment
                for (int i = 0; i < data.length; i++) {
                    System.out.print(data[i].trim());
                    if (i < data.length - 1) {
                        System.out.print("\t"); // Use tab for better column alignment
                    }
                }
                System.out.println(); // New line for the next record
                recordsDisplayedCount++;
            }

            // Now, count the remaining records to get the total count
            int totalRecordsCount = recordsDisplayedCount; // Start with the records already displayed
            while (br.readLine() != null) {
                totalRecordsCount++; // Increment for each subsequent line
            }

            System.out.println("\n------------------------");
            System.out.println("Total Records (excluding header): " + totalRecordsCount);

        } catch (FileNotFoundException e) {
            System.err.println("Error: The file '" + filePath + "' was not found.");
            System.err.println("Please ensure 'dataset.csv' is in your project's root directory.");
        } catch (IOException e) {
            System.err.println("An error occurred while reading the CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
