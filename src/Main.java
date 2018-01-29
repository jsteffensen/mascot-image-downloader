import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		final Path path = Paths.get("mascot.txt");
		List<String> csvFileLines = FileInputOutput.csvFileDataToList(path);

		csvFileLines.forEach((line)->{
			downloadImage(line);
	    });

		System.out.println("\nImages saved to C:/mascot_images/");
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
				FileInputOutput.saveImageFile(request, image, address);
			}

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}


	}


}
