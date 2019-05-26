package codegeneration;

import symbols.InterfaceIndex;
import symbols.InterfaceType;
import symbols.PhysicalInterface;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

public class InterfaceConfigurationEmitter extends ConfigurationEmitter{

    private final PhysicalInterface inf;

    public InterfaceConfigurationEmitter(PhysicalInterface inf, String... commands) {
        super(commands);
        this.inf = inf;
    }

    public InterfaceConfigurationEmitter(PhysicalInterface inf) {
        super();
        this.inf = inf;
    }

    @Override
    public void writeCommand(BufferedWriter writer) throws IOException {
        InterfaceIndex ifI = inf.getInterfaceIndex();
        writer.write("Configure Terminal" + System.getProperty("line.separator"));
        if(ifI.getIndex2()<0) {
            writer.write(String.format("Interface %s %d", ifI.getInterfaceType().toString(), ifI.getIndex1())+System.getProperty("line.separator"));
        } else{
            writer.write(String.format("Interface %s %d/%d", ifI.getInterfaceType().toString(), ifI.getIndex1(), ifI.getIndex2())+System.getProperty("line.separator"));
        }
        super.writeCommand(writer);
    }
}
