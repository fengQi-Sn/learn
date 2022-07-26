package example.example.structure.combination.file;

import sun.security.krb5.internal.PAData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSystemNode {
    private String path;
    private boolean isFile;
    private List<FileSystemNode> subNodes = new ArrayList<>();

    public FileSystemNode(String path, boolean isFile) {
        this.path = path;
        this.isFile = isFile;
    }

    public void addSubNode(FileSystemNode fileOrDir) {
        subNodes.add(fileOrDir);
    }

    public String getPath() { return path; }

    public void removeSubNode(FileSystemNode fileOrDir) {
        int size = subNodes.size();
        int i=0;
        for (; i<size; i++) {
            if(subNodes.get(i).getPath().equalsIgnoreCase(fileOrDir.getPath())) {
                break;
            }
        }
        if(i<size) {
            subNodes.remove(i);
        }
    }

    public int countNumOfFiles() {
        if(isFile) {
            return 1;
        }
        int numOfFiles = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            numOfFiles += fileOrDir.countNumOfFiles();
        }
        return numOfFiles;
    }


    public long countSizeOfFiles() {
        if(isFile) {
            File file = new File(path);
            if(!file.exists()) {
                return 0;
            }
            return file.length();
        }
        long sizeOfFiles = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            sizeOfFiles += fileOrDir.countSizeOfFiles();
        }
        return sizeOfFiles;
    }
}
