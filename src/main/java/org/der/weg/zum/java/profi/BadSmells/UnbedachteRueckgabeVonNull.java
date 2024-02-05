package org.der.weg.zum.java.profi.BadSmells;

public class UnbedachteRueckgabeVonNull {

    public static void main(String[] args) {
        final String value1 = toHashSeparateString("012");
        final String value2 = toHashSeparateString("");
    }

    public static String toHashSeparateString(final String message){
        if(message.length() == 0) return  null;
      //  if(message.length() == 0) return  "";

        final StringBuffer stringBuffer = new StringBuffer("'");

        for (int i = 0; i < message.length(); i++) {
            stringBuffer.append(message.charAt(i)).append('#');
        }
        return stringBuffer.toString();
    }
}
