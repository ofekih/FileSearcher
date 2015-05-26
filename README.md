# FileSearcher
Allows user to search through computer to find specific file / files

# Usage

 - All methods in FileSearcher require an int searchType to be passed, which refers to one of 4 constants
 - CURRENT_FOLDER only searchs for items in the current folder
 - SUBFOLDERS_AND_CURRENT only searchers for items in the current folder, and subfolders
 - SEARCH_USER searches for all the items in the current user
 - SEARCH_EVERYTHING searches everything
 - Keep in mind that search user and search everything can take a while (30 seconds - 2 minutes)
 
 - 
 - Most methods allow the user to pass a directory to start searching from, specifically when searching current folder and when searching subfolders as well.
 A directory can be made using new File(directoryLocation), such as new File("C:\Users\ofekg_000\Documents")

DeleteDotClass is an example of how simply FileSearcher can be used.
It uses FileSearcher to find all .class files in the current folder and all subfolders, and then deletes them one by one.
