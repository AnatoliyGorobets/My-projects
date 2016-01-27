
/**
 * Write a description of ColdTemperaturerFinder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ColdTemperaturerFinder {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for(CSVRecord currentRow : parser) {
            lowestSoFar = getlowestOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("The coldest temperature on that day was "+lowest.get("TemperatureF")+" at "
                                                         +lowest.get("TimeEDT")); 
        System.out.println("");
    }

    
    public String fileWithColdestTemperature() {
        CSVRecord coldestFile = null;
        String nameOfColdestFile ="";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource file = new FileResource(f); 
            CSVRecord lowestSoFar = coldestHourInFile(file.getCSVParser());
            if (coldestFile == null) {
                coldestFile = lowestSoFar;
                nameOfColdestFile = f.getName();
            }
            else {
            double lowestSoFarTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
            double coldestFileTemp = Double.parseDouble(coldestFile.get("TemperatureF"));
                if (lowestSoFarTemp < coldestFileTemp) {
                    coldestFile = lowestSoFar;
                    nameOfColdestFile = f.getAbsolutePath();
                }
            }
        }
        return (nameOfColdestFile.substring(0));
    }
    
    public void testFileWithColdestTemperature() {
       String nameOfColdestFile = fileWithColdestTemperature();
       FileResource file = new FileResource(nameOfColdestFile.substring(0));
       CSVParser parser = file.getCSVParser();       
       CSVRecord coldestFile = coldestHourInFile(parser);
       System.out.println("Coldest day in file "+nameOfColdestFile.substring(0));
       System.out.println("Coldest temperature on that day was "+coldestFile.get("TemperatureF"));
       System.out.println("All the Temperatures on the coldest day were:");
       parser = file.getCSVParser();
       for (CSVRecord record : parser){
        String date = record.get("DateUTC");
        String temperature = record.get("TemperatureF");
        System.out.print(date);
        System.out.println("  "+temperature);
        }
       System.out.println("");
    }
    
    
    public CSVRecord  lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for(CSVRecord currentRow : parser) {
            lowestSoFar = getlowestOfTwoHumodity(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVRecord csv = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC")); 
        System.out.println("");
    }
    
    
   public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getlowestOfTwoHumodity(currentRow, lowestSoFar);
        }
        return  lowestSoFar;
    }
    
   public void testLowestHumidityInManyFiles() {
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+lowest.get("Humidity")+" at "+lowest.get("DateUTC"));
        System.out.println("");
   }
    
    
   public double averageTemperatureInFile(CSVParser parser) {
       double sum = 0;
       int count = 0;
       for (CSVRecord record : parser) {
           double temp = Double.parseDouble(record.get("TemperatureF"));
           if (temp == -9999) {
               temp = 0;
           }
           sum = sum + temp;
           count = count + 1;
       }
       double average = sum/count;
       return (average);
   }
   
   public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double average = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is "+average);  
        System.out.println("");
   }
   
   public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
       double sum = 0;
       int count = 0;
       for (CSVRecord record : parser) {
           int hum = Integer.parseInt(record.get("Humidity"));
           if ( hum >= value) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            if (temp == -9999) {
               temp = 0;
            }
            sum = sum + temp;
            count = count + 1;
           }
        }
       if (sum == 0) {
           return(0);
        }
       else { 
       double average = sum/count;
       return (average);   
       }
   }
   
   public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (average == 0) {
          System.out.println("No temperatures with that humidity");
        }
        else {
          System.out.println("Average Temp when high Humidity is "+average);  
          System.out.println("");
        }
   }
   
   
   public CSVRecord getlowestOfTwoHumodity(CSVRecord currentRow, CSVRecord lowestSoFar){
           String humidity = currentRow.get("Humidity");
           if (humidity.equals("N/A")) {
               return lowestSoFar;
            }
            else if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            else {
                double currentHum = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHum = Double.parseDouble(lowestSoFar.get("Humidity"));
                if (currentHum < lowestHum) {
                    lowestSoFar = currentRow;
                }
            }
           return lowestSoFar;
   }
   public CSVRecord getlowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
        double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));    
            if (currentTemp == -9999) {
                return lowestSoFar;
            }
            else if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            else {
                double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
                if (currentTemp < lowestTemp) {
                    lowestSoFar = currentRow;
                }
            }
            return lowestSoFar;
   }
}  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
