
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class BabyBirths {
    public void totalBitrhs(FileResource fr) {
        CSVParser parser = fr.getCSVParser(false);
        int totalBirths = 0;
        int countMale = 0;
        int countFemale = 0;
        int uniqueB = 0;
        int uniqueG = 0;
        int totalNames =0;
            for (CSVRecord rd : parser) {
                int numBorn = Integer.parseInt(rd.get(2));
                totalNames += 1;
                totalBirths += numBorn;
                if (rd.get(1).equals("M")) {
                    countMale += numBorn;
                    uniqueB += 1;
                }
                else {
                    countFemale += numBorn;
                    uniqueG += 1;
                }

            }
            System.out.println("Number of babies is "+totalBirths);
            System.out.println("Number of boys is "+countMale);
            System.out.println("Number of girls is "+countFemale);
            System.out.println("Number of unique boys is "+uniqueB);
            System.out.println("Number of unique girls is "+uniqueG);
            System.out.println("Number of names in the file is "+totalNames);
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBitrhs(fr);
    }
    
    
    
    public void returnPasth() {
        DirectoryResource dr = new DirectoryResource();
        String path = ""; 
        for(File f : dr.selectedFiles()) {
           path = f.getAbsolutePath();
           System.out.println(path);
        }
    }
    
    
    
    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource(
        "F:/QA Engineer/Java/Files/Babby Born/us_babynames_by_year/yob"+year+".csv");
        int rank = 1;
        int totalGirls = 1;
        int noName = 0;
        for (CSVRecord rd : fr.getCSVParser()) {
            rank += 1;
            if (rd.get(1).equals("F")) {
                totalGirls += 1;
            }
            if (rd.get(0).equals(name) && rd.get(1).equals(gender)) {
                noName = 1;    
                break;    
            }
        }
        if (noName == 0) {
            rank = -1;
        }
        if (gender.equals("M") && rank != -1) {
                    rank = rank - totalGirls;
           } 
        return rank;
    }
    
    public String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource(
        "F:/QA Engineer/Java/Files/Babby Born/us_babynames_by_year/yob"+year+".csv");
        int count = 1;
        int totalGirls = 1;
        String ourName = "";
        for (CSVRecord rd : fr.getCSVParser()) {
            count += 1;
            if (rd.get(1).equals("F")) {
                totalGirls += 1;
            }
            if (gender == "F") {
                if (count == rank && rd.get(1).equals(gender)) {
                    ourName = rd.get(0);
                    break;    
                }
            }
            else if (gender == "M"){
                if ((count-totalGirls) == rank && rd.get(1).equals(gender)) {
                    ourName = rd.get(0);
                    break;    
                }
            }
        }
        if (ourName == "") {
            ourName = "NO Name";
        }
        return (ourName);
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
    if(newName.equals("NO Name")) {
            System. out.println("Wow!"+name.substring(0)+", you have unique name.");
    }
    else {
        if (gender == "F") {
            System. out.println(name.substring(0)+" born in "+year+" would be "
                            +newName.substring(0)+" if she was born in "+newYear);
        }
        else if (gender == "M"){
            System. out.println(name.substring(0)+" born in "+year+" would be "
                            +newName.substring(0)+" if he was born in "+newYear);
        }
    }
    }
    
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int maxRank = 1000000000;
        int maxYear = 0;
        for(File f : dr.selectedFiles()) {
           String fileName = f.getName();
           int index = fileName.indexOf("b")+1;
           int year = Integer.parseInt(fileName.substring(index, index+4));
           int rank = getRank(year, name, gender);
           if (rank == -1) {
               maxRank = maxRank;
               maxYear = maxYear;
            }
           else if (rank < maxRank) {
               maxRank = rank;
               maxYear = year;
            }          
        }
        if (maxRank == 1000000000) {
            maxYear = -1;
        }
        return (maxYear);
    }
    
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double averege = 0;
        int count = 0;
        for(File f : dr.selectedFiles()) {
           String fileName = f.getName();
           int index = fileName.indexOf("b")+1;
           int year = Integer.parseInt(fileName.substring(index, index+4));
           int rank = getRank(year, name, gender);

           if (rank == -1) {
               averege = averege;
               count = count;
            }
           else {
                averege += rank;
                count += 1;
            }          
        }
        if (count == 0) {
            return (averege);
        }
        else {
            averege = averege/count;
            return (averege);
        }
    }
    
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource(
        "F:/QA Engineer/Java/Files/Babby Born/us_babynames_by_year/yob"+year+".csv");
        int rank = getRank(year, name, gender);
        int totalBirthsHigher = 0;
        int count = 1;
        for (CSVRecord rd : fr.getCSVParser(false)) {
            if (rd.get(1).equals(gender) && count < rank) {
                int numBorn = Integer.parseInt(rd.get(2));
                totalBirthsHigher += numBorn;
                count += 1;
            }
        }
        return totalBirthsHigher;
    }
}
