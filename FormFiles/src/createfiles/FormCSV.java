package createfiles;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FormCSV {

	private Random random = new Random();
	private Charset utf8 = StandardCharsets.UTF_8;
	private Path path;                      // путь к CSV-файлу
	private long maxFileSize;               // max размер файла

	public FormCSV(Path path) {
		this.path = path;
		this.maxFileSize = 1073741824; // 1 Гб
	}

	public FormCSV(Path path, long maxFileSize) {
		this.path = path;
		this.maxFileSize = maxFileSize;
	}

	public void createCSVFile() throws IOException {
		long currentFileSize = 0;                    // текущий размер файла (если файла не существует, то равен 0)
		if (Files.exists(path))
			currentFileSize = Files.size(path);     // текущий размер файла

		while (currentFileSize <= maxFileSize) {
			List<Integer> numberBuffer = new ArrayList<Integer>(100000);
			for (int i = 0; i < 100000; i++)
				numberBuffer.add(random.nextInt());

			byte[] byteBuffer = numberBuffer.toString().replaceAll("[^0-9-,]", "").getBytes(utf8);

			Files.write(path, byteBuffer, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

			currentFileSize = Files.size(path);
		}
	}
}