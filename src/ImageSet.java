import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ImageSet {
	
	private final Path path = Paths.get("mascot.csv");
	private List<String> csvFileLines;
	private List<ImageDownload> downloadObjects;
	
	ImageSet() {
		csvFileLines =  new ArrayList<String>();
		downloadObjects =  new ArrayList<ImageDownload>();
	}
	
	public void loadCSVFileData() {

		if(Files.exists(path)) {
			try {
			  Files.lines(path)
				      .forEach((line)->{
				    	  String[] lines = line.split(";");
				    	  csvFileLines.add(lines[0] + ";" + lines[3]);
				    	});
				} catch (IOException e) {
					System.out.println( "--" );
				  e.printStackTrace();
				}
		} else {
			System.out.println("File not found.");
		}

	}
	
	public void createDownloadObjects() {
		this.csvFileLines.forEach((line)->{
			createDownloadObject(line);
	    });
	}
	
	private void createDownloadObject(String line) {

		if( imageNotDownloaded(getImageString(line)) ) {
			downloadObjects.add( new ImageDownload(getURIString(line), getImageString(line)));
		}
		
	}
	
	private String getURIString(String line) {
		
		String[] lineArray = line.split(";");
		String address = lineArray[1];

		StringBuilder s = new StringBuilder();
		
		try {
			URI uri = new URI(
				    "https",
				    address.split("/")[2],
				    "/" + address.split("/")[3],
				    null);
			s.append(uri.toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return s.toString();

	}

	private String getImageString(String line) {
		
		String[] lineArray = line.split(";");
		String filename = lineArray[0];
		String address = lineArray[1];
		String extension = address.split("\\.")[3];
		
		return "C:/mascot_images/" + filename + "." + extension;
	}
	
	private boolean imageNotDownloaded(String imageString) {
		
		Path imagePath = Paths.get(imageString);
		return Files.notExists(imagePath);
		
	}
	
	public void startDownloads() {
		this.downloadObjects.forEach((download)->{
			download.start();
	    });
	}

}
