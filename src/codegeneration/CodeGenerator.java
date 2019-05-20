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

            //Creating the configs directory for the configuration files
            File configsDir = new File("configs");
            if (!configsDir.exists()){
                configsDir.mkdir();
            }


            File file = new File(String.format("configs/%s.txt", router.getName()));
            FileWriter fr = null;
            BufferedWriter br = null;
            try{
                fr = new FileWriter(file);
                br = new BufferedWriter(fr);

                for (RouterConfigurationEmitter emitter : routerEmitters) {
                    emitter.writeCommand(br);
                }

                for (InterfaceConfigurationEmitter emitter : interfaceEmitters) {
                    emitter.writeCommand(br);
                }

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

    private void populateNetworkMap() {
        for (Router router : envR.getRouters()) {
            for (PhysicalInterface physicalInterface : router.getInterfaces()) {
                IpAddress networkAdd = physicalInterface.getNetworkAddress();
                if(interfaceNetworkMap.containsKey(networkAdd)){
                    interfaceNetworkMap.get(networkAdd).add(physicalInterface);
                } else {
                    List<PhysicalInterface> list = new ArrayList<>();
                    list.add(physicalInterface);
                    interfaceNetworkMap.put(networkAdd, list);
                }
            }
        }
    }


    //Go through each router and generate its code?
        //HashSet over router settings
        //HashSet over interface settings for each interface
        //interfaces can emmit to either the router settings or its interface settings

    //Create files and input streams for each router

    //Write code?
}
