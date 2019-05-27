package systemTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SystemTestTest {

    @Test
    void systemTest() throws FileNotFoundException {
        File compilerFilesDir = new File("test/testConfigs/compilerFiles");
        File manualFilesDir = new File("test/testConfigs/manualConfigFiles");

        List<ComparableFile> compilerFiles = getFiles(compilerFilesDir);
        List<ComparableFile> manualFiles = getFiles(manualFilesDir);

        for (int i = 0; i < manualFiles.size(); i++) {
            assertTrue(compareFiles(manualFiles.get(i), compilerFiles.get(i)));
        }
    }

    private boolean compareFiles(ComparableFile manualFile, ComparableFile compilerFile) {
        assertEquals(manualFile.getLinesList().size(), manualFile.getLinesList().size());

        for (int i = 0; i < manualFile.getLinesList().size(); i++) {
            String manualLine = manualFile.getLinesList().get(i);
            String compilerLine = compilerFile.getLinesList().get(i);

            assertTrue(manualLine.equalsIgnoreCase(compilerLine), compilerFile.getRouterId() + "; " + compilerLine);
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
