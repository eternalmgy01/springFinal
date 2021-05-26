import java.util.ArrayList;
import java.util.Scanner;

public class lab5 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<DB> dbList = new ArrayList<>();

        while (true) {

            String line = sc.nextLine();

            if (line.equals(""))
                break;

            String[] words = line.split(" ");

            if (words[1].equals("DB")) {
                if (words[2].charAt(words[2].length() - 1) == 'h' || words[2].charAt(words[2].length() - 1) == 'q' ||
                        words[2].charAt(words[2].length() - 1) == 'b' || words[2].charAt(words[2].length() - 1) == 'd')
                    dbList.add(new DB(words[0], (byte) toDecimal(words[2]), words[2].charAt(words[2].length() - 1)));
                else
                    dbList.add(new DB(words[0], Byte.parseByte(words[2])));
            }

            switch (words[0]) {
                case "ADD":
                    for (int i = 0; i < dbList.size(); i++) {
                        if (dbList.get(i).getName().equals(words[1])) {
                            for (int j = 0; j < dbList.size(); j++) {
                                if (dbList.get(j).getName().equals(words[2])) {
                                    dbList.get(i).setValue((byte) (dbList.get(i).getValue() + dbList.get(j).getValue()));
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                case "INC":
                    for (int i = 0; i < dbList.size(); i++) {
                        if (dbList.get(i).getName().equals(words[1])) {
                            dbList.get(i).setValue((byte) (dbList.get(i).getValue() + 1));
                            break;
                        }
                    }
                    break;
                case "SUB":
                    for (int i = 0; i < dbList.size(); i++) {
                        if (dbList.get(i).getName().equals(words[1])) {
                            for (int j = 0; j < dbList.size(); j++) {
                                if (dbList.get(j).getName().equals(words[2])) {
                                    dbList.get(i).setValue((byte) (dbList.get(i).getValue() - dbList.get(j).getValue()));
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                case "DEC":
                    for (int i = 0; i < dbList.size(); i++) {
                        if (dbList.get(i).getName().equals(words[1])) {
                            dbList.get(i).setValue((byte) (dbList.get(i).getValue() - 1));
                            break;
                        }
                    }
                    break;
                case "MUL":
                    for (int i = 0; i < dbList.size(); i++) {
                        if (dbList.get(i).getName().equals(words[1])) {
                            for (int j = 0; j < dbList.size(); j++) {
                                if (dbList.get(j).getName().equals(words[2])) {
                                    dbList.get(i).setValue((byte) (dbList.get(i).getValue() * dbList.get(j).getValue()));
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                case "DIV":
                    for (int i = 0; i < dbList.size(); i++) {
                        if (dbList.get(i).getName().equals(words[1])) {
                            for (int j = 0; j < dbList.size(); j++) {
                                if (dbList.get(j).getName().equals(words[2])) {
                                    dbList.get(i).setValue((byte) (dbList.get(i).getValue() / dbList.get(j).getValue()));
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                case "MOV":
                    for (int i = 0; i < dbList.size(); i++) {
                        if (dbList.get(i).getName().equals(words[1])) {
                            for (int j = 0; j < dbList.size(); j++) {
                                if (dbList.get(j).getName().equals(words[2])) {
                                    dbList.get(i).setValue(dbList.get(j).getValue());
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                case "XCHG":
                    for (int i = 0; i < dbList.size(); i++) {
                        if (dbList.get(i).getName().equals(words[1])) {
                            for (int j = 0; j < dbList.size(); j++) {
                                if (dbList.get(j).getName().equals(words[2])) {
                                    byte tmp = dbList.get(i).getValue();
                                    dbList.get(i).setValue(dbList.get(j).getValue());
                                    dbList.get(j).setValue(tmp);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
            }
        }

        for (DB aDbList : dbList) {
            System.out.println("DB name - " + aDbList.getName() + ", DB value - " + aDbList.getConvertedValue());
        }
    }

    static class DB {

        private String name;
        private byte value;
        private char radix;

        public DB(String name, byte value) {
            this.name = name;
            this.value = value;
            radix = ' ';
        }

        public DB(String name, byte value, char radix) {
            this.name = name;
            this.value = value;
            this.radix = radix;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public byte getValue() {
            return value;
        }

        public void setValue(byte value) {
            this.value = value;
        }

        public char getRadix() {
            return radix;
        }

        public void setRadix(char radix) {
            this.radix = radix;
        }

        public String getConvertedValue() {
            if (radix == 'h')
                return Integer.toHexString(value) + 'h';
            else if (radix == 'q')
                return Integer.toOctalString(value) + 'q';
            else if (radix == 'b')
                return Integer.toBinaryString(value) + 'b';
            else if (radix == 'd')
                return Integer.toString(value) + 'd';
            else
                return Integer.toString(value);
        }
    }

    static class DW {

        private String name;
        private short value;
        private char radix;

        public DW(String name, short value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public short getValue() {
            return value;
        }

        public void setValue(short value) {
            this.value = value;
        }
    }

    static class DD {

        private String name;
        private int value;
        private char radix;

        public DD(String name, int value) {
            this.name = name;
            this.value = value;
            radix = 'd';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static int toDecimal(String str) {
        if (str.charAt(str.length() - 1) == 'h') {
            return Integer.parseInt(str.substring(0, str.length() - 1), 16);
        } else if (str.charAt(str.length() - 1) == 'q') {
            return Integer.parseInt(str.substring(0, str.length() - 1), 8);
        } else if (str.charAt(str.length() - 1) == 'b') {
            return Integer.parseInt(str.substring(0, str.length() - 1), 2);
        } else if (str.charAt(str.length() - 1) == 'd') {
            return Integer.parseInt(str.substring(0, str.length() - 1));
        } else
            return Integer.parseInt(str);
    }
}
