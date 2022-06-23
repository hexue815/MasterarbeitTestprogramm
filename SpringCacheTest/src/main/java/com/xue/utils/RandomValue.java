package com.xue.utils;

public class RandomValue {
    public static String getRandomName(){
        String name;
        char[] nameChar;
        int nameLength = (int)(Math.random()*(10-7+1))+3;
        nameChar = new char[nameLength];

        nameChar[0] = (char) (Math.random()*26+65);
        for (int i = 1; i < nameLength; i++) {
            nameChar[i] = (char)(Math.random()*26+97);
        }
        name = new String(nameChar);
        return name;
    }

    public static String getRandomGender(){
        int gender = (int) (Math.random()*2);
        return gender == 0 ? "male" : "female";
    }

    public static int getRandomAge(){
        return (int)(Math.random()*(65-18+1)) + 18;
    }

    public static String getRandomEmail(){
        String email;
        char[] emailChar;
        int emailLength = (int)(Math.random()*(5-3+1))+3;
        String[] mailEnd = new String[]{"@gmail.com", "@hotmail.com", "@gmx.de", "@st.ovgu.de"};
        emailChar = new char[emailLength];
        for (int i = 0; i < emailLength; i++) {
            emailChar[i] = (char)(Math.random()*26+97);
        }
        email = new String(emailChar) + mailEnd[(int)(Math.random()*(mailEnd.length))];
        return email;
    }

    public static String getRandomAddress(){
        String[] address = {"Baden-Württemberg", "Bayern","Berlin", "Brandenburg", "Bremen", "Hamburg",
        "Hessen", "Mecklenburg-Vorpommern", "Niedersachsen", "Nordrhein-Westfalen", "Rheinland-Pfalz", "Saarland", "Sachsen-Anhalt",
        "Sachsen", "Schleswig-Holstein", "Thüringen"};
        return address[(int) (Math.random() * (address.length))];
    }

    public static String getRandomPhone(){
        String phone;
        char[] phoneChar = new char[11];
        phoneChar[0] = '1';
        for (int i = 1; i < 11; i++) {
            phoneChar[i] = (char)(Math.random()*10+48);
        }
        phone = new String(phoneChar);
        return phone;
    }
}




































