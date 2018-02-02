public class Main {

	public static void main(String[] args) {
		
		ImageSet is = new ImageSet();
		
		is.loadCSVFileData();
		is.createDownloadObjects();
		is.startDownloads();

		System.out.println("\nImages saved to C:/mascot_images/");
	}

}
