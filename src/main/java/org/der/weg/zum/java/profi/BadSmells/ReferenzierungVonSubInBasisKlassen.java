package org.der.weg.zum.java.profi.BadSmells;

public class ReferenzierungVonSubInBasisKlassen {
    public static class DataManager {
        public static void main(String[] args) {
            DataManager dataManager = null;
            int val = 0;
            switch (val){
                case 1 : dataManager = new DataManagerA(); break;
                case 2 : dataManager = new DataManagerB(); break;
                case 3 : dataManager = new DataManagerC(); break;
            }
        }
    }
    public static class DataManagerA extends DataManager {}
    public static class DataManagerB extends DataManager {}
    public static class DataManagerC extends DataManager {}

    public static class DataManagerFactory {
       DataManager createDataManager(String id){return null;}
    }

}
