import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader {

	public static File getResource(String fileName) {
		File file = new File(fileName);
		return file;
	}

	public static List<Point> readFile(String fileName) throws IOException {

		File file = getResource(fileName);
		List<Point> result = new ArrayList<Point>();

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		for (String line = br.readLine(); line != null; line = br.readLine()) {
			String[] tokens = line.toString().split(";");
			try {
				int id = Integer.parseInt(tokens[0]); //TODO A supprimer
				double x = Double.parseDouble(tokens[1]);
				double y = Double.parseDouble(tokens[2]);
				double z = Double.parseDouble(tokens[3]);
				result.add(new Point(id, x, y, z));
			} catch (NumberFormatException e) {
				//Ligne non conforme -> Evite de prendre la ligne d'en tête
			}
		}

		br.close();
		fr.close();

		return result;
	}

}
