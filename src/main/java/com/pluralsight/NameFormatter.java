package com.pluralsight;

public class NameFormatter {
    private NameFormatter(){}

    //This is not exhaustive obviously
    private static final String[] SUFFIX_LIST = {"Jr.", "Sr.", "M.D.", "PhD", "II", "III", "IV"};

    public static String format(String firstName, String lastName) {
        return lastName + ", " + firstName;
    }

    public static String format(String prefix, String firstName, String middleName, String lastName, String suffix) {
        StringBuilder container = new StringBuilder();
        container.append(lastName).append(",");
        if (!prefix.isBlank())
            container.append(" ").append(prefix);
        if (!firstName.isBlank())
            container.append(" ").append(firstName);
        if (!middleName.isBlank())
            container.append(" ").append(middleName);
        if (!suffix.isBlank())
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

        //Check if our last index matches anything in our suffix list
        int hasSuffix = 0;
        for (String s : SUFFIX_LIST) {
            if (nameArray[nameArray.length-1].equalsIgnoreCase(s)) {
                suffix = s.trim();
                hasSuffix++;
                break;
            }
        }

        //Get the last name (we're assuming everyone has a first and last name, obviously)
        lastName = nameArray[nameArray.length - (1+hasSuffix)].trim();

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
