package codegeneration;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public abstract class ConfigurationEmitter {

    private final List<String> strings;

    public ConfigurationEmitter() {
        this.strings = new ArrayList<>();
    }

    public ConfigurationEmitter(String... strings) {
        this.strings = new ArrayList<>(Arrays.asList(strings));
    }

    public void addCommand(String command){
        strings.add(command);
    }

    public void writeCommand(BufferedWriter writer) throws IOException{
        for (String s : strings) {
            writer.write(s);
            writer.write(System.getProperty("line.separator"));
        }
        writer.write("end" + System.getProperty("line.separator"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConfigurationEmitter)) return false;
        ConfigurationEmitter that = (ConfigurationEmitter) o;
        return Objects.equals(strings, that.strings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strings);
    }
}
