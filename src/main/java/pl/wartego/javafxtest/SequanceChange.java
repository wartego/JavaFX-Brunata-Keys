package pl.wartego.javafxtest;

public class SequanceChange {
    private String stringKeyAfter;
    private String stringKeyBefore;


    public String changeSequence(String stringKeyBefore){
        this.stringKeyBefore = stringKeyBefore;
        System.out.println("key before " + stringKeyBefore);
        char[] ch = stringKeyBefore.toCharArray();

        if (ch.length != 32) {
            stringKeyAfter = "Wrong key provided";
        } else {
            stringKeyAfter = String.valueOf(ch[30] + "" + ch[31] + ch[28] + "" + ch[29] + "" + ch[26] + "" + ch[27] + "" + ch[24] + "" + ch[25] + "" + ch[22] + "" + ch[23] + "" + ch[20] + "" + ch[21] + "" + ch[18] + "" + ch[19] + "" + ch[16] + "" + ch[17] + "" + ch[14] + "" + ch[15] + "" + ch[12] + "" + ch[13] + "" + ch[10] + "" + ch[11] + "" + ch[8] + "" + ch[9] + "" + ch[6] + "" + ch[7] + "" + ch[4] + "" + ch[5] + "" + ch[2] + "" + ch[3] + "" + ch[0] + "" + ch[1]);
        }
        return stringKeyAfter;
    }
}
