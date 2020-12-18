package com.example.thought.lucaslouca;

import com.example.thought.lucaslouca.commands.LLCommandFactory;
import com.example.thought.lucaslouca.commands.LLCommandProccesor;
import com.example.thought.lucaslouca.commands.LLRailRoadServiceCommandFactory;
import com.example.thought.lucaslouca.service.LLRailRoadService;
import com.example.thought.lucaslouca.service.LLRailRoadServiceImpl;
import com.example.thought.lucaslouca.util.LLPropertyFactory;
import com.example.thought.lucaslouca.util.LLTownMap;
import com.example.thought.lucaslouca.util.LLTownMapImpl;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(LLPropertyFactory.getProperties().get("usage"));
            System.exit(1);
        } else {
            String graphFilePath = args[0];
            String commandsFilePath = args[1];

            LLTownMap map = new LLTownMapImpl();
            try {
                map.init(graphFilePath);

                LLRailRoadService service = new LLRailRoadServiceImpl(map);

                // Create an LLCommandFactory
                LLCommandFactory commandFactory = new LLRailRoadServiceCommandFactory(service);

                // Create an LLCommandProccesor that uses commandFactory
                LLCommandProccesor processor = new LLCommandProccesor(commandFactory);

                System.out.println(processor.runAll(commandsFilePath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
