package codegeneration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import symbolTables.RouterSymbolTable;
import symbols.IpAddress;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static codegeneration.SymbolTableFactory.table1;
import static codegeneration.SymbolTableFactory.table2;
import static org.junit.jupiter.api.Assertions.*;

class CodeGeneratorTest {

    RouterSymbolTable routerSymbolTable1 = SymbolTableFactory.table1();
    RouterSymbolTable routerSymbolTable2 = SymbolTableFactory.table2();

    // Delete all previously generated configs
    @BeforeEach
    void cleanup() {
        routerSymbolTable1.getRouters().forEach(router -> nameToPath(router.getName()).toFile().delete());
        routerSymbolTable2.getRouters().forEach(router -> nameToPath(router.getName()).toFile().delete());
    }

    @Test
    void populateNetworkMap1() {
        CodeGenerator codeGenerator = new CodeGenerator(SymbolTableFactory.table1());
        codeGenerator.populateNetworkMap();
        assertNotNull(codeGenerator.interfaceNetworkMap.get(new IpAddress(10, 20, 30, 40)));
    }

    @Test
    void populateNetworkMap2() {
        CodeGenerator codeGenerator = new CodeGenerator(SymbolTableFactory.table2());
        codeGenerator.populateNetworkMap();
        assertNotNull(codeGenerator.interfaceNetworkMap.get(new IpAddress(100, 200, 155, 20)));
    }


    @Test
    void configDoesNotExist1() {
        routerSymbolTable1.getRouters().forEach( router -> assertFalse(nameToPath(router.getName()).toFile().exists()));
    }

    @Test
    void configExists1() {
        CodeGenerator codeGenerator = new CodeGenerator(routerSymbolTable1);
        codeGenerator.generate();
        // Ensure a config for all routers in table1
        codeGenerator.envR.getRouters().forEach(
                router ->
                        assertTrue(nameToPath(router.getName()).toFile().exists())
        );
    }

    @Test
    void table1test1() throws IOException {
        new CodeGenerator(routerSymbolTable1).generate();
        List<String> stringList = Files.readAllLines(nameToPath(SymbolTableFactory.ROUTER_NAME1));
        assertEquals("Configure Terminal", stringList.get(0));
    }

    @Test
    void table1test2() throws IOException {
        new CodeGenerator(routerSymbolTable1).generate();
        List<String> stringList = Files.readAllLines(nameToPath(SymbolTableFactory.ROUTER_NAME1));
        assertTrue(stringList.contains("ip name-server 10.10.10.10"));
    }

    @Test
    void table1test3() throws IOException {
        new CodeGenerator(routerSymbolTable1).generate();
        List<String> stringList = Files.readAllLines(nameToPath(SymbolTableFactory.ROUTER_NAME1));
        assertEquals("!", stringList.get(stringList.size() - 1));
    }

    @Test
    void table1test4() throws IOException {
        new CodeGenerator(routerSymbolTable1).generate();
        List<String> stringList = Files.readAllLines(nameToPath(SymbolTableFactory.ROUTER_NAME1));
        assertEquals("Configure Terminal", stringList.get(0));
    }

    @Test
    void configExists2() {
        CodeGenerator codeGenerator = new CodeGenerator(routerSymbolTable2);
        codeGenerator.generate();
        // Ensure a config for all routers in table2 after generating
        codeGenerator.envR.getRouters().forEach( router ->
                assertTrue(nameToPath(router.getName()).toFile().exists())
        );
    }


    @Test
    void table2Size() {
        assertEquals(3, routerSymbolTable2.getRouters().size());
    }

    @Test
    void table2test1() throws IOException {
        new CodeGenerator(routerSymbolTable2).generate();
        List<String> stringList = Files.readAllLines(nameToPath(SymbolTableFactory.ROUTER_NAME3));
        assertEquals(stringList.get(0), "Configure Terminal");
    }

    @Test
    void table2test2() throws IOException {
        new CodeGenerator(routerSymbolTable2).generate();
        List<String> stringList = Files.readAllLines(nameToPath(SymbolTableFactory.ROUTER_NAME3));
    }

    // Helper functions
    private Path nameToPath(String routerName) {
        return Paths.get("configs/" + routerName + ".txt");
    }

    private String stringListToString(List<String> stringList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringList.forEach(s -> stringBuilder.append(s).append("\n"));
        return stringBuilder.toString();
    }
}