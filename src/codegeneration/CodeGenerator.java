package codegeneration;

import settings.InterfaceSetting;
import settings.RouterSetting;
import symbolTables.RouterSymbolTable;
import symbols.IpAddress;
import symbols.PhysicalInterface;
import symbols.Router;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CodeGenerator {
    RouterSymbolTable envR;

    //Map over interfaces that share subnets
    Map<IpAddress, List<PhysicalInterface>> interfaceNetworkMap = new HashMap<>();

    public CodeGenerator(RouterSymbolTable envR) {
        this.envR = envR;
    }

    public void generate(){
        populateNetworkMap();
        createOutputDirectory();

        for (Router router : envR.getRouters()) {
            Set<RouterConfigurationEmitter> routerEmitters = new HashSet<>();
            Set<InterfaceConfigurationEmitter> interfaceEmitters = new HashSet<>();

            for (RouterSetting setting : router.getSettings()) {
                setting.addEmitters(router, routerEmitters, interfaceEmitters, interfaceNetworkMap);
            }

            for (PhysicalInterface physicalInterface : router.getInterfaces()) {
                for (InterfaceSetting setting : physicalInterface.getSettings()) {
                    setting.addEmitters(physicalInterface, routerEmitters, interfaceEmitters, interfaceNetworkMap);
                }

                IpAddress mask = physicalInterface.getSubnetMask();
                IpAddress ip = physicalInterface.getIp();

                interfaceEmitters.add(new InterfaceConfigurationEmitter(physicalInterface,
                        String.format("Ip Address %d.%d.%d.%d %d.%d.%d.%d",
                                ip.seg1, ip.seg2, ip.seg3, ip.seg4,
                                mask.seg1, mask.seg2, mask.seg3, mask.seg4),
                        "no shutdown"
                ));
            }

            File file = new File(String.format("configs/%s.txt", router.getName()));
            FileWriter fr = null;
            BufferedWriter br = null;
            try{
                fr = new FileWriter(file);
                br = new BufferedWriter(fr);


                for (InterfaceConfigurationEmitter emitter : interfaceEmitters) {
                    emitter.writeCommand(br);
                }

                for (RouterConfigurationEmitter emitter : routerEmitters) {
                    emitter.writeCommand(br);
                }

                br.write("Write"+System.getProperty("line.separator"));
                br.write("!"+System.getProperty("line.separator"));

            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    if( br != null){
                        br.close();
                        fr.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createOutputDirectory() {
        //  Creating the configs directory for the configuration files
        File configsDir = new File("configs");
        if (!configsDir.exists()){
            configsDir.mkdir();
        } else
        if(configsDir.list() != null){
            // If the configs directory already exists and contains files then delete them
            if(Objects.requireNonNull(configsDir.list()).length != 0){
                for(String s: Objects.requireNonNull(configsDir.list())){
                    File currentFile = new File(configsDir.getPath(),s);
                    currentFile.delete();
                }
            }
        }
    }


    void populateNetworkMap() {
        for (Router router : envR.getRouters()) {
            for (PhysicalInterface physicalInterface : router.getInterfaces()) {
                IpAddress networkAdd = physicalInterface.getNetworkAddress();
                interfaceNetworkMap.computeIfAbsent(networkAdd, nwAdd -> new ArrayList<>());
                interfaceNetworkMap.get(networkAdd).add(physicalInterface);
            }
        }
    }

}
