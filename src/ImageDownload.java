import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageDownload {

	private String downloadFromAddress;
	private String saveToAddress;
	
	ImageDownload(String downloadAddress, String saveAddress) {
		this.downloadFromAddress = downloadAddress;
		this.saveToAddress = saveAddress;
	}
	
	public void start() {
		
		Path saveToPath = Paths.get(saveToAddress);
		
		try(InputStream in = new URL(downloadFromAddress).openStream()){
			Thread.sleep(100);
			Path dir = Paths.get("C:/mascot_images");
			Files.createDirectories(dir);
			
		    Files.copy(in, saveToPath);
		    System.out.print(".");
		    
		} catch (IOException e) {
			System.out.println("");
			System.out.println("Error getting: " + downloadFromAddress);
		} catch (InterruptedException e) {
			System.out.println("");
			System.out.println("Error getting: " + downloadFromAddress);
		}
		
	}
}
