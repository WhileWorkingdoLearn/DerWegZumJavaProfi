package org.der.weg.zum.java.profi.BadSmells;

public final class CallAbstractInitInBaseclass {

    abstract static class AbstractBase{

        protected final Integer baseValue = 42;

        AbstractBase(){
            init();
        }

        abstract void init();
    }

    static class Derived extends AbstractBase {

        private  final Integer value = 13;

        @Override
        void init() {
            System.out.println("baseValue= " + baseValue);
            System.out.println("value= " + value);
        }
    }

    public static void main(String[] args) {
        new Derived();

        new RadioCommunication();

        /*Ein Objekt der Klasse Derived wird erzeugt. Bei der Erzeugung des Objekts wird wiederum der Konstruktor der Basisklasse aufgerufen.
        * Bei der Erzeugung eines Objektes werden die Konstruktoren ausgehend von der Basis Object bis zum eigentlichen Derived-Objekt durchlaufen.
        * Dabei wird auch der entsprechende Speicherbereich für die einzelnen variablen der Herachie angelegt.
        *
        * Abstrakte Methoden sollten niemals im Konstruktor verwendet werden!!!
        *
        *
        * */
    }

    public abstract static class CommunicationBase{
        public CommunicationBase() {
            createComponent();
        }

        abstract protected  void createComponent();
    }

    public static class RadioCommunication extends CommunicationBase{
        private final Object sharedSyncObject = new Object();

        private RadioSender datasender = null;
        private RadioReceiver dataresceiver = null;
        @Override
        protected void createComponent() {
            datasender  = new RadioSender(sharedSyncObject);
        }
    }

    public static final class RadioSender {
        private final Object sharedSyncObject;

        public RadioSender(Object sharedSyncObject) {
                this.sharedSyncObject = sharedSyncObject;
        }

        public void send(final byte[] msg){
            synchronized (sharedSyncObject){
                sendBytes();
            }
        }

        private void sendBytes() {
        }
    }
    public static final class RadioReceiver {

    }

}
