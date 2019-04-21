package sche;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Random;

public class FileUtil {

	public static void writeToFile(String fileName, List<ScheTask> scheList, double d) {
		try {
			File file = new File(fileName);
			FileWriter fw = new FileWriter(file);
			for(ScheTask task : scheList) {
				fw.write(task.toString()+"\n");
			}
			fw.write("Average waiting time " + d);
			fw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
