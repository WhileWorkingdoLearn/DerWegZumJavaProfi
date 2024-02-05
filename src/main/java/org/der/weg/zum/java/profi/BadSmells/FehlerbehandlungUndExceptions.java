package org.der.weg.zum.java.profi.BadSmells;

import java.util.Objects;

public class FehlerbehandlungUndExceptions {
    private Boolean startFolder;
    private Boolean ascending;

    public void incorrectFill(final String path){
        if(this.startFolder != null){
            if(this.ascending){
                fillFolderContent(path);
            } else {
                throw new NullPointerException("Sort order not implemented");
            }
        } else {
            throw new NullPointerException("No startFolder");
        }
    }

    private void fillFolderContent(String path) {
    }

    public void correctFill(final String path){
        Objects.requireNonNull(path,"parameter 'path' must be not null");

        if(Objects.isNull(startFolder)){
            throw new IllegalStateException("attribute stattFolder must not be null, call ...");
        }

        if(!ascending){
            throw new UnsupportedOperationException("descending sort order not implemented!");
        }

        fillFolderContent(path);

    }
}
