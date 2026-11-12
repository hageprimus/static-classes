package com.pluralsight;

public class NameFormatter {
    private NameFormatter(){}

    public static String format(String firstName, String lastName) {
        return lastName + ", " + firstName;
    }

    public static String format(String prefix, String firstName, String middleName, String lastName, String suffix) {
        StringBuilder container = new StringBuilder();
        container.append(lastName).append(",");
        if (prefix != null && !prefix.isBlank())
            container.append(" ").append(prefix);
        if (firstName != null && !firstName.isBlank())
            container.append(" ").append(firstName);
        if (middleName != null && !middleName.isBlank())
            container.append(" ").append(middleName);
        if (suffix != null && !suffix.isBlank())
            container.append(", ").append(suffix);
        return container.toString().trim();
    }

    public static String format(String fullName) {
        //Initialize variables and split name array
        String prefix = "", firstName, middleName = "", lastName, suffix = "";
        String[] nameArray = fullName.split(" ");

        //If the first string contains a period, assume it's a prefix
        int hasPrefix = 0;
        if (nameArray[0].contains(".")) {
            prefix = nameArray[0].trim();
            hasPrefix++;
        }

        //Get first name (shift index forward one if there's a prefix)
        firstName = nameArray[hasPrefix].trim();

        //If the second to last string contains a comma, assume the last one is a suffix
        int hasSuffix = 0;
        if (nameArray[nameArray.length-2].contains(",")) {
            suffix = nameArray[nameArray.length-1];
            lastName = nameArray[nameArray.length-2].replace(",","");
            hasSuffix++;
        } else {
            lastName = nameArray[nameArray.length-1];
        }

        //Check if there's any remaining indexes in the array, and treat them all as a middle name
        //Accounts for if someone inputs a name with multiple middle names
        if (hasPrefix+1 != nameArray.length - (1+hasSuffix)) {
            StringBuilder middleContainer = new StringBuilder();
            for (var i = hasPrefix+1; i < nameArray.length - (1+hasSuffix); i++) {
                middleContainer.append(nameArray[i]).append(" ");
            }
            middleName = middleContainer.toString().trim();
        }

        //Call the explicit format method to avoid reusing code
        return format(prefix, firstName, middleName, lastName, suffix);
    }
}
