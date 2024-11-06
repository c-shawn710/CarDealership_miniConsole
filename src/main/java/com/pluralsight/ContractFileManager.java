package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    public static void saveContract(Contract contract) {
        if(contract == null) {
            throw new IllegalArgumentException("Contract cannot be null");
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contracts.csv", true));
            //read first line of csv which is a contract
            bufferedWriter.write("\n" + contract.toCsv());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}