package systemTest;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SystemTestTest {

    @Test
    void fileComparison() throws FileNotFoundException {
        File compilerFilesDir = new File("testConfigs/compilerFiles");
        File manualFilesDir = new File("testConfigs/manualConfigFiles");

        List<ComparableFile> compilerFiles = getFiles(compilerFilesDir);
        List<ComparableFile> manualFiles = getFiles(manualFilesDir);

        for (int i = 0; i < manualFiles.size(); i++) {
            if(!compareFiles(manualFiles.get(i), compilerFiles.get(i))){
                throw new RuntimeException("Files didn't match: " + manualFiles.get(i).getRouterId());
            }
        }
    }

    private boolean compareFiles(ComparableFile manualFile, ComparableFile compilerFile) {
        if(manualFile.getLinesList().size() != compilerFile.getLinesList().size()){
            return false;
        }

        for (int i = 0; i < manualFile.getLinesList().size(); i++) {
            String manualLine = manualFile.getLinesList().get(i);
            String compilerLine = compilerFile.getLinesList().get(i);

            if(!manualLine.equalsIgnoreCase(compilerLine)){
                return false;
            }

        }

        return true;
    }

    private List<ComparableFile> getFiles(File dir) {
        List<File> files = new ArrayList<>();

        if(dir.list() != null){
            if(Objects.requireNonNull(dir.list()).length != 0){
                for(String s: Objects.requireNonNull(dir.list())){
                    files.add(new File(dir.getPath(),s));
                }
            }
            return sortList(files);
        }

        return null;
    }

    private List<ComparableFile> sortList(List<File> files) {
        List<ComparableFile> ComparableFiles = new ArrayList<>();

        files.forEach(file -> ComparableFiles.add(new ComparableFile(file)));
        ComparableFiles.sort((o1, o2) -> o1.getRouterId() - o2.getRouterId());

        return ComparableFiles;
    }
}
