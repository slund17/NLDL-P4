package codegeneration;

import org.junit.jupiter.api.Test;
import symbols.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class InterfaceConfigurationEmitterTest {

    File file = new File("configs/test/" +this.getClass().getSimpleName());

    @Test
    void testExists() {
        assertTrue(file.exists());
    }

    @Test
    void writeCommand() throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String[] commands = new String[]{"no-shutdown", "dead-interval 100"};
        PhysicalInterface physicalInterface = new PhysicalInterface(null, new InterfaceIndex(0, 1, InterfaceType.FAST_ETHERNET));
        InterfaceConfigurationEmitter interfaceConfigurationEmitter = new InterfaceConfigurationEmitter(physicalInterface, commands);
        interfaceConfigurationEmitter.writeCommand(bufferedWriter);
        bufferedWriter.close();
    }
}