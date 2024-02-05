package org.der.weg.zum.java.profi.ApplikationsBausteine;

public class Konfigurationsparameter {

    enum AppParameter {
        DEBUG_SHORT("d"),
        HELP_SHORT("?"),
        WIDTH_SHORT("w="),
        WIDTH("width="),
        HEIGHT_SHORT("h="),
        HEIGHT("height=");
        final String parName;
        final int valueOffset;
        AppParameter(final String name){
            this.parName = "-" + name;
            this.valueOffset = parName.length();
        }
    }

    private static boolean hasBooleanParam(final String cmdArg,final AppParameter appParameter){
        return cmdArg.equals(appParameter.parName);
    }

    private static boolean hasValueParam(final String cmdArg,final AppParameter appParameter){
        return cmdArg.startsWith(appParameter.parName) && cmdArg.length() > appParameter.valueOffset;
    }

    public static int extractInt(final String cmdArgs,final AppParameter appParameter){
        return Integer.parseInt(cmdArgs.substring(appParameter.valueOffset));
    }

    public static void main(String[] args) {

        int height = 700;
        int width = 700;
        boolean debug = false;
        boolean showHelp = false;

        final String[] sampleArgs = {"-h","-w=500","-height=550"};
/*
        for(final String cmdArg : sampleArgs){
            if(cmdArg.startsWith("-d")) {
                debug = true;
            }
            else if (cmdArg.startsWith("-h")){
                showHelp = true;
            }
            else if (cmdArg.startsWith("-w=")){
                width = Integer.parseInt(cmdArg.substring(3));
            } else if (cmdArg.startsWith("-width=")) {
                width = Integer.parseInt(cmdArg.substring(7))
            }

        } */
        for(final String cmdArg : sampleArgs){
            if(hasBooleanParam(cmdArg,AppParameter.DEBUG_SHORT)){
                debug = true;
            }
            if(hasBooleanParam(cmdArg,AppParameter.HELP_SHORT)){
                showHelp = true;
            }
            if(hasValueParam(cmdArg,AppParameter.WIDTH_SHORT)){
                width = extractInt(cmdArg,AppParameter.WIDTH_SHORT);
            }
            if(hasBooleanParam(cmdArg,AppParameter.WIDTH)){
                width = extractInt(cmdArg,AppParameter.WIDTH);
            }
            if(hasValueParam(cmdArg,AppParameter.HEIGHT_SHORT)){
                height = extractInt(cmdArg,AppParameter.HEIGHT_SHORT);
            }
            if(hasValueParam(cmdArg,AppParameter.HEIGHT)){
                height = extractInt(cmdArg,AppParameter.HEIGHT);
            }
        }

    }
}
