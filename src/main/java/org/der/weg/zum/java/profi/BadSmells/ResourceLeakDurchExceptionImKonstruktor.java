package org.der.weg.zum.java.profi.BadSmells;

import java.io.*;

public class ResourceLeakDurchExceptionImKonstruktor {

    public static class IncorrectZipConverter {
        private InputStream inputStream = null;
        private OutputStream outputStream = null;

        public IncorrectZipConverter(final String inputFile, final String outputFile) throws FileNotFoundException {
            this.inputStream = new FileInputStream(inputFile);
            this.outputStream = new FileOutputStream(outputFile);
        }
        protected final InputStream getInputStream(){return inputStream;}
        protected final OutputStream getOutputStream(){return outputStream;}
    }

    public static class BetterZipConverter{
        private InputStream inputStream = null;
        private OutputStream outputStream = null;


        public BetterZipConverter(final String inputFile, final String outputFile) {
            //Errormeldung wird nicht propagiert. Nach außen keine Mitteilung, dass das Object inkorrekt initialisiert wurde.
            try{
                this.inputStream = new FileInputStream(inputFile);
            }
            catch (final FileNotFoundException fnfe){
               // IOUtils.closeQuietly
            }
            try{
                this.outputStream = new FileOutputStream(outputFile);
            }
            catch (final FileNotFoundException fnfe){
                // IOUtils.closeQuietly(outputStream);
            }

        }

        protected final InputStream getInputStream(){return inputStream;}
        protected final OutputStream getOutputStream(){return outputStream;}
    }

    public static class AlmostCorrectZipConverter {
        private InputStream inputStream = null;
        private OutputStream outputStream = null;

        private AlmostCorrectZipConverter() {

        }

        public static AlmostCorrectZipConverter createZipConverter(final String inputFile, final String outputFile) throws FileNotFoundException {
                AlmostCorrectZipConverter correctZipConverter = null;
                try {
                    correctZipConverter = new AlmostCorrectZipConverter();
                    correctZipConverter.init(inputFile,outputFile);
                    return correctZipConverter;
                } catch (final FileNotFoundException fnfe){
                    // IOUtils.closeQuietly(correctZipConverter.getInputStream());
                    // IOUtils.closeQuietly(correctZipConverter.getOutputStream());
                    throw fnfe;
                }
        }

        private void init(String inputFile, String outputFile) throws FileNotFoundException {
            this.inputStream = new FileInputStream(inputFile);
            this.outputStream = new FileOutputStream(outputFile);
        }

        protected final InputStream getInputStream(){return inputStream;}
        protected final OutputStream getOutputStream(){return outputStream;}

    }

    public static class CorrectZipConverter {
        private InputStream inputStream = null;
        private OutputStream outputStream = null;

        private CorrectZipConverter(){}

        public static CorrectZipConverter createZipConverter(final String inputFile, final String outputFile) throws FileNotFoundException {
            CorrectZipConverter correctZipConverter = null;
            try {
                correctZipConverter = new CorrectZipConverter();
                correctZipConverter.init(inputFile,outputFile);
                return correctZipConverter;
            } catch (final FileNotFoundException fnfe){
                correctZipConverter.release();
                throw fnfe;
            }
        }

        private void release() {
            // IOUtils.closeQuietly(correctZipConverter.getInputStream());
            // IOUtils.closeQuietly(correctZipConverter.getOutputStream());
        }

        @Override
        protected void finalize() throws Throwable {
            release();
            super.finalize();
        }

        private void init(String inputFile, String outputFile) throws  FileNotFoundException {
            this.inputStream = new FileInputStream(inputFile);
            this.outputStream = new FileOutputStream(outputFile);
        }

        protected final InputStream getInputStream(){return inputStream;}
        protected final OutputStream getOutputStream(){return outputStream;}
    }
}
