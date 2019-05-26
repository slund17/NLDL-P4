package systemTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComparableFile {
    private int routerId;
    private File file;
    private List<String> linesList = new ArrayList<>();

    public ComparableFile(File file) {
        this.file = file;
        this.routerId = findRouterId(file);

        try {
            generateFiles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void generateFiles() throws FileNotFoundException {
        List<String> rawList = new ArrayList<>();

        // Reading the file to get all files
        Scanner scanner = new Scanner(new File(file.getPath()));
        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();
            rawList.add(s);
        }
        scanner.close();

        // Adding 'meaningfull' linesList to the filtered list
        for (String line : rawList) {
            if(!(line.contains("!") && line.length() == 1)){
                this.linesList.add(line);
            }
        }
    }

    private int findRouterId(File file) {
        String idString = "";
        if(file.getName().contains("_")){
            idString = file.getName().substring(1, file.getName().indexOf("_"));
        } else {
            idString = file.getName().substring(1, file.getName().indexOf("."));
        }

        try {
            return Integer.valueOf(idString);
        }catch (RuntimeException e){
            return 99;

        }
    }

    public int getRouterId() {
        return routerId;
    }

    public File getFile() {
        return file;
    }

    public List<String> getLinesList() {
        return linesList;
    }
}
