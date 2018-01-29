import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileInputOutput {
	
	static List<String> csvFileDataToList(Path path) {

		List<String> list =  new ArrayList<String>();

		if(Files.exists(path)) {
			try {
			  Files.lines(path)
				      .forEach((line)->{
				    	  String[] lines = line.split(";");
				    	  list.add(lines[0] + ";" + lines[3]);
				    	});
				} catch (IOException e) {
				  e.printStackTrace();
				}
		} else {
			System.out.println("File not found.");
		}

		return list;
	}
	
	static void saveImageFile(String request, Path image, String address) {
		
		try(InputStream in = new URL(request).openStream()){
			Thread.sleep(100);
			Path dir = Paths.get("C:/mascot_images");
			Files.createDirectories(dir);
		    Files.copy(in, image);
		    System.out.print(".");
		} catch (Exception e) {
			System.out.println("");
			System.out.println("Error getting: " + address);
		}
		
	}
}
