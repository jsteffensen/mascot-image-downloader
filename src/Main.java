import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		final Path path = Paths.get("mascot.txt");

		List<String> images = getFile(path);

		images.forEach((line)->{
			downloadImage(line);
	    });
		System.out.println();
		System.out.println("Images saved to C:/mascot_images/");
	}

	private static void downloadImage(String line) {

		String[] nameAndAddress = line.split(";");
		String filename = nameAndAddress[0];
		String address = nameAndAddress[1];
		String extension = address.split("\\.")[3];
		Path image = Paths.get("C:/mascot_images/" + filename + "." + extension);

		try {
			URI uri = new URI(
				    "https",
				    address.split("/")[2],
				    "/" + address.split("/")[3],
				    null);
			String request = uri.toString();

			if( Files.notExists(image) ) {
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

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}


	}

	private static List<String> getFile(Path path) {

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
			System.out.println("File mascot.txt not found.");
		}

		return list;
	}

}
