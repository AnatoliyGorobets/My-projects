
/**
 * Write a description of WichCoutriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class WichCoutriesExport {
    public void testExport () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
            System.out.println("    writeCountryInfo    ");
        writeCountryInfo(parser, "Nauru");
            System.out.println("");
            parser = fr.getCSVParser();
            System.out.println("    listExportersTwoProducts    ");
        listExportersTwoProducts(parser, "cotton", "flowers");
            System.out.println("");
            parser = fr.getCSVParser();
            System.out.println("    numberOfExporters   ");
        numberOfExporters(parser, "cocoa");
            System.out.println("");
            parser = fr.getCSVParser();
            System.out.println("    bigExporters    ");
        bigExporters(parser, "$999,999,999,999");
            System.out.println("");
            parser = fr.getCSVParser();
    }
    
    public void writeCountryInfo(CSVParser parser, String ourCountry) {
        int condition = 0;
        for (CSVRecord record : parser) {
            String cntryFrList = record.get("Country");
            
            if (cntryFrList.contains(ourCountry)) {
                String expFrList = record.get("Exports");
                String valueFrList = record.get("Value (dollars)");
                System.out.println(cntryFrList+": "+expFrList+": "+valueFrList);
                condition = 1;
            }
        }
        if (condition == 0){
            System.out.println("Country is not found");
        }
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser) {
            String itemList = record.get("Exports");
            if (itemList.contains(exportItem1) && itemList.contains(exportItem2)) {
                String cntryFrList = record.get("Country");
                System.out.println(cntryFrList);
            }
        }
    }
    
    public void numberOfExporters(CSVParser parser, String exportitem){
        int count = 0;
        for(CSVRecord record : parser) {
            String itemList = record.get("Exports");
            if (itemList.contains(exportitem)) {
                count = count+1;
            }
        }
        System.out.println("The number of counties wich export item "+exportitem+"in this list is: "+count);
    }
    
    public void bigExporters(CSVParser parser, String amount) {
           for(CSVRecord record : parser) {
            String valueFrList = record.get("Value (dollars)");
            if (valueFrList.length()> amount.length()) {
                String cntryFrList = record.get("Country");
                System.out.println(cntryFrList+": "+valueFrList);
            }
        }     
    }
}
