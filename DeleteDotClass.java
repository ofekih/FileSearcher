import java.nio.file.Files;
import java.io.File;
import java.io.IOException;

public class DeleteDotClass	{
	public static void main(String... pumpkins)	{
		for (File file : FileSearcher.findFiles(FileSearcher.SUBFOLDERS_AND_CURRENT, "class"))
			try	{
				Files.delete(file.toPath());
				System.out.println("Deleted:\t" + file.getName());
			}	catch(IOException e)	{
				System.out.println("Failed:\t" + file.getName());
			}
	}
}