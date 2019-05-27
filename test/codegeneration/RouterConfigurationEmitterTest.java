package codegeneration;

import org.junit.jupiter.api.Test;
import settings.DNSServerSetting;
import symbols.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class RouterConfigurationEmitterTest {

    File file = new File("test/testConfigs/test/" +this.getClass().getSimpleName());

    @Test
    void testExists() {
        assertTrue(file.exists());
    }

    @Test
    void writeCommand() throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String[] commands = new String[]{"DNS 20.10.300.20"};
        RouterConfigurationEmitter routerConfigurationEmitter = new RouterConfigurationEmitter(commands);
        routerConfigurationEmitter.writeCommand(bufferedWriter);
        bufferedWriter.close();
    }

    @Test
    void addCommand() {
        RouterConfigurationEmitter routerConfigurationEmitter = new RouterConfigurationEmitter();
        routerConfigurationEmitter.addCommand("random command");
    }

    @Test
    void equals() {
    }
}