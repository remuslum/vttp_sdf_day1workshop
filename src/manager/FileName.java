package src.manager;

import java.io.File;

public class FileName {
    private final String dirPath;
    private final String fileType = ".db";

    public FileName() {
        this.dirPath = checkIfDatabaseExists();
    }

    public String generateFileName(String username) {
        return this.dirPath + File.separator + username + this.fileType;
    }

    public String getDirPath() {
        return dirPath;
    }

    public String getFileType() {
        return fileType;
    }

    private String checkIfDatabaseExists() {
        if (!(new File("cartdb")).exists()){
            // If it does not, create a new database file named db
            return "db";
        } else {
            return "cartdb";
        }
    }
}
