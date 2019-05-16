package codegeneration;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

public class RouterConfigurationEmitter extends ConfigurationEmitter{
    public RouterConfigurationEmitter() {
        super();
    }

    public RouterConfigurationEmitter(String... strings) {
        super(strings);
    }

    @Override
    public void writeCommand(BufferedWriter writer) throws IOException {
        writer.write("Configure Terminal"+System.getProperty("line.separator"));
        super.writeCommand(writer);
    }
}
