import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageDownload {

	private String downloadFromAddress;
	private String saveToAddress;
	private int failedAttempts;
	private int sleepDuration;
	
	ImageDownload(String downloadAddress, String saveAddress) {
		this.downloadFromAddress = downloadAddress;
		this.saveToAddress = saveAddress;
		failedAttempts = 0;
		sleepDuration = 100;
	}
	
	public void start() {
		
		if(this.failedAttempts < 4) {
			Path saveToPath = Paths.get(saveToAddress);
			
			try(InputStream in = new URL(downloadFromAddress).openStream()){
				Thread.sleep(sleepDuration);
				Path dir = Paths.get("C:/mascot_images");
				Files.createDirectories(dir);
				
			    Files.copy(in, saveToPath);
			    System.out.print(".");
			    
			} catch (IOException e) {
				this.sleepDuration += 1000;
				this.failedAttempts++;
				System.out.print("|" + this.sleepDuration + "ms|");
				this.start();
			} catch (InterruptedException e) {
				System.out.println("");
				System.out.println("Error getting: " + downloadFromAddress);
			}
		} else {
			System.out.println("");
			System.out.println("Error getting: " + downloadFromAddress);			
		}
	}
}
