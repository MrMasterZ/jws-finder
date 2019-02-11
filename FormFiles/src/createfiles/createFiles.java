package createfiles;

import java.io.IOException;
import java.nio.file.Paths;

public class createFiles {
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();

		new FormCSV(Paths.get("G:/1")).createCSVFile();
		new FormCSV(Paths.get("G:/2")).createCSVFile();
		new FormCSV(Paths.get("G:/3")).createCSVFile();
		new FormCSV(Paths.get("G:/4")).createCSVFile();
		new FormCSV(Paths.get("G:/5")).createCSVFile();
		new FormCSV(Paths.get("G:/6")).createCSVFile();
		new FormCSV(Paths.get("G:/7")).createCSVFile();
		new FormCSV(Paths.get("G:/8")).createCSVFile();
		new FormCSV(Paths.get("G:/9")).createCSVFile();
		new FormCSV(Paths.get("G:/10")).createCSVFile();
		new FormCSV(Paths.get("G:/11")).createCSVFile();
		new FormCSV(Paths.get("G:/12")).createCSVFile();
		new FormCSV(Paths.get("G:/13")).createCSVFile();
		new FormCSV(Paths.get("G:/14")).createCSVFile();
		new FormCSV(Paths.get("G:/15")).createCSVFile();
		new FormCSV(Paths.get("G:/16")).createCSVFile();
		new FormCSV(Paths.get("G:/17")).createCSVFile();
		new FormCSV(Paths.get("G:/18")).createCSVFile();
		new FormCSV(Paths.get("G:/19")).createCSVFile();
		new FormCSV(Paths.get("G:/20")).createCSVFile();

		long timeSpent = System.currentTimeMillis() - startTime;
		System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
	}

}
