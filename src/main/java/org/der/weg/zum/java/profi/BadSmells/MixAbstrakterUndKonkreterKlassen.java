package org.der.weg.zum.java.profi.BadSmells;

public class MixAbstrakterUndKonkreterKlassen {

    public static void main(String[] args) {
        /*
        Klassen, die nicht abstrakt sind, sollten sich eigenständig initialisieren lassen.
        * */
    }
    public static class ProcessState {}

    public static class BaseProcess {
       public ProcessState getProcessState(){return  new ProcessState();};
       public void setProcessState(ProcessState processState) {}
    }

    public static abstract class AbstractProcess extends BaseProcess {
        abstract void pause();
        abstract void resume();
        abstract void start();
    }

   public static class ProcessA extends AbstractProcess{

       @Override
       void pause() {
           System.out.println("pause");
       }

       @Override
       void resume() {
           System.out.println("resume");
       }

       @Override
       void start() {
           System.out.println("start");
       }
   }


   public static abstract class CorrectAAbstractProcess{
        protected final ProcessStateUtils processState = new ProcessStateUtils();

       public ProcessState getProcessState(){return  new ProcessState();};
       public void setProcessState(ProcessState processState) {}
       abstract void pause();
       abstract void resume();
       abstract void start();
   }

   public static class ProcessStateUtils {
       public ProcessState getProcessState(){return  new ProcessState();};
       public void setProcessState(ProcessState processState) {}
   }

   public static class CorrectProcessA extends CorrectAAbstractProcess {

       @Override
       void pause() {

       }

       @Override
       void resume() {

       }

       @Override
       void start() {

       }
   }

   public  interface IProcess {
       ProcessState getProcessState();
       void setProcessState(ProcessState processState);
       void pause();
       void resume();
       void start();
   }

   public static abstract class InterfaceAbstractProcess implements IProcess{
       protected final ProcessStateUtils processState = new ProcessStateUtils();
   }

   public static class IProcessA extends InterfaceAbstractProcess {

       @Override
       public ProcessState getProcessState() {
           return processState.getProcessState();
       }

       @Override
       public void setProcessState(ProcessState processState) {
           this.processState.setProcessState(processState);
       }

       @Override
       public void pause() {

       }

       @Override
       public void resume() {

       }

       @Override
       public void start() {

       }
   }
   public static class IProcessB extends InterfaceAbstractProcess {

       @Override
       public ProcessState getProcessState() {
           return null;
       }

       @Override
       public void setProcessState(ProcessState processState) {

       }

       @Override
       public void pause() {

       }

       @Override
       public void resume() {

       }

       @Override
       public void start() {

       }
   }

}
