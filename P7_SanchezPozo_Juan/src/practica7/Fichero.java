package practica7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Fichero {
	private final String filePath;

	public Fichero(String filePath) {
		this.filePath = filePath;
	}

	public void processFile(Consumer<String> consumer) throws IOException {
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			stream.forEach(consumer);
		} catch (IOException e) {
			throw e;
		}
	}

	public static void escribirFichero(String ruta, String texto) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
			bw.write(texto);
		} catch (IOException e) {
			throw e;
		}
	}

	public String getFilePath() {
		return filePath;
	}
}
