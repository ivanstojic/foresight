package com.ordecon.testdatagenerator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Ivan Stojic
 */
public class Fake {
    private static String[] splitLine(String line) {
        String[] temp;
        StringTokenizer tokenizer = new StringTokenizer(line, ",");

        temp = new String[tokenizer.countTokens()];


        return temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:/CampaignImport.csv")));
        String line;
        String[] fields;

        String campaignName;
        String campaignDescription;
        String campaignType;
        String campaignMandatory;
        String campaignPriority;
        String campaignStart;
        String campaignEnd;
        String campaignReason1;
        String campaignReason2;
        String campaignReason3;
        String campaignReason4;

        // Campaign data
        // Name
        line = br.readLine();
        fields = splitLine(line);

        campaignName = fields[1];

        // Description
        line = br.readLine();
        fields = splitLine(line);

        campaignDescription = fields[1];

        // Type
        line = br.readLine();
        fields = splitLine(line);

        campaignType = fields[1];

        // Mandatory
        line = br.readLine();
        fields = splitLine(line);

        campaignMandatory = fields[1];

        // Priority
        line = br.readLine();
        fields = splitLine(line);

        campaignPriority = fields[1];

        // Start
        line = br.readLine();
        fields = splitLine(line);

        campaignStart = fields[1];

        // End
        line = br.readLine();
        fields = splitLine(line);

        campaignEnd = fields[1];

        // Reason Code 1
        line = br.readLine();
        fields = splitLine(line);

        campaignReason1 = fields[1];

        // Store it into the database

        // Create a new lead source

        // Eat up the header line
        line = br.readLine();

        // Parse the data
        while ((line = br.readLine()) != null) {
            fields = splitLine(line);

            // Find user and generate a link to the lead source
        }

    }
}
