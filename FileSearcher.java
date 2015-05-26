import java.io.File;
import java.util.ArrayList;
/**
 * This program allows the user to search for files easily.
 * @author Ofek Gila
 * @version 1.0
 * @since  May 25th, 2015
 */
public final class FileSearcher	{

	/**
	 * Searches in current folder only
	 */
	public final static int CURRENT_FOLDER = 0;
	/**
	 * Searches current folder and subfolders
	 */
	public final static int SUBFOLDERS_AND_CURRENT = 1;
	/**
	 * Searches everything for the current user
	 */
	public final static int SEARCH_USER = 2;
	/**
	 * Searches EVERYTHING in the computer
	 */
	public final static int SEARCH_EVERYTHING = 3;

	public final static File currentDirectory = new File(System.getProperty("user.dir"));

	private FileSearcher()	{}	// to prevent initialization

	private static File file = null;

	public static File findFile(String name, int searchType)	{
		return findFile(name, currentDirectory, searchType);
	}

	public static File findFile(String name, File currentFolder, int searchType)	{
		findFileR(getName(name), getExtension(name), getFolder(currentFolder, searchType), searchType);
		return file;
	}

	private static void findFileR(String name, String extension, final File folder, int searchType)	{
		try {
			for (final File fileEntry : folder.listFiles())
				if (file != null) return;
				else if (fileEntry.isDirectory() && !(searchType == CURRENT_FOLDER))
					findFileR(name, extension, fileEntry, searchType);
				else if (getName(fileEntry.getName()).equals(name) && (extension.equals("") || extension.equals(getExtension(fileEntry.getName()))))
					file = fileEntry;
		}	catch(NullPointerException e)	{}
	}

	public static ArrayList<File> findFiles(String name, int searchType)	{
		return findFiles(name, currentDirectory, searchType);
	}

	public static ArrayList<File> findFiles(String name, File currentFolder, int searchType)	{
		if (getName(name).equals("*"))
			return findFiles(currentFolder, searchType, getExtension(name));
		else return findFilesR(getName(name), getExtension(name), getFolder(currentFolder, searchType), searchType, new ArrayList<File>());
	}

	private static ArrayList<File> findFilesR(String name, String extension, final File folder, int searchType, ArrayList<File> files)	{
		try {
			for (final File fileEntry : folder.listFiles())
				if (fileEntry.isDirectory() && !(searchType == CURRENT_FOLDER))
					findFilesR(name, extension, fileEntry, searchType, files);
				else if (getName(fileEntry.getName()).equals(name) && (extension.equals("") || extension.equals(getExtension(fileEntry.getName()))))
					files.add(fileEntry);
		}	catch(NullPointerException e)	{}
		return files;
	}

	public static ArrayList<File> findFiles(int searchType, String... extensions)	{
		return findFiles(currentDirectory, searchType, extensions);
	}

	public static ArrayList<File> findFiles(File currentFolder, int searchType, String... extensions)	{
		return findFilesR(getFolder(currentFolder, searchType), searchType, getExtensions(extensions), new ArrayList<File>());
	}

	private static ArrayList<File> findFilesR(final File folder, int searchType, String[] extensions, ArrayList<File> files)	{
		try {
			for (final File fileEntry : folder.listFiles())
				if (fileEntry.isDirectory() && !(searchType == CURRENT_FOLDER))
					findFilesR(fileEntry, searchType, extensions, files);
				else if (extensions.length == 0)
					files.add(fileEntry);
				else for (String extension : extensions)
					if (getExtension(fileEntry.getName()).equals(extension))	{
						files.add(fileEntry);
						break;
					}
		}	catch(NullPointerException e)	{}
		return files;
	}

	public static void printFiles(int searchType)	{
		printFiles(currentDirectory, searchType);
	}

	public static void printFiles(File currentFolder, int searchType)	{
		printFilesR(getFolder(currentFolder, searchType), searchType);
	}

	private static void printFilesR(final File folder, int searchType)	{
		try {
			for (final File fileEntry : folder.listFiles())
				if (fileEntry.isDirectory() && !(searchType == CURRENT_FOLDER))
					printFilesR(fileEntry, searchType);
				else System.out.println(fileEntry);
		}	catch(NullPointerException e)	{}
	}

	public static File findFolder(String name, int searchType)	{
		return findFolder(name, currentDirectory, searchType);
	}

	public static File findFolder(String name, File currentFolder, int searchType)	{
		findFolderR(name, getFolder(currentFolder, searchType), searchType);
		return file;
	}

	private static void findFolderR(String name, final File folder, int searchType)	{
		try {
			for (final File fileEntry : folder.listFiles())
				if (file != null) return;
				else if (fileEntry.isDirectory())
					if (fileEntry.getName().equals(name))
						file = fileEntry;
					else if (!(searchType == CURRENT_FOLDER))
						findFolderR(name, fileEntry, searchType);
		}	catch(NullPointerException e)	{}
	}

	public static ArrayList<File> findFolders(String name, int searchType)	{
		return findFolders(name, currentDirectory, searchType);
	}

	public static ArrayList<File> findFolders(String name, File currentFolder, int searchType)	{
		return findFoldersR(name, getFolder(currentFolder, searchType), searchType, new ArrayList<File>());
	}

	private static ArrayList<File> findFoldersR(String name, final File folder, int searchType, ArrayList<File> folders)	{
		try {
			for (final File fileEntry : folder.listFiles())
				if (fileEntry.isDirectory())	{
					if (fileEntry.getName().equals(name))
						folders.add(fileEntry);
					if (!(searchType == CURRENT_FOLDER))
						findFoldersR(name, fileEntry, searchType, folders);
				}
		}	catch(NullPointerException e)	{}
		return folders;
	}

	public static File getFolder(File folder, int searchType)	{
		switch (searchType)	{
			case SEARCH_USER:
				while(folder.getParentFile().getParentFile().getParentFile() != null)
					folder = folder.getParentFile();
				return folder;
			case SEARCH_EVERYTHING:
				while(folder.getParentFile() != null)
					folder = folder.getParentFile();
				return folder;
			default: return folder;
		}
	}
	public static String getExtension(String name)	{
		return name.lastIndexOf(".") == -1 ? "":name.substring(name.lastIndexOf(".")).substring(1);
	}

	public static String getName(String name)	{
		return name.substring(name.lastIndexOf("\\")+1, name.lastIndexOf(".") == -1 ? name.length():name.lastIndexOf("."));
	}

	public static String[] getExtensions(String[] extensions)	{
		for (int i = 0; i < extensions.length; i++)
			if (!getExtension(extensions[i]).equals(""))
				extensions[i] = getExtension(extensions[i]);
		return extensions;
	}
}