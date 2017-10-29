package sn.projet.hubschool.filter.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * Transforme un outputStream en String
 */
public class ServletOutputStreamCopier extends ServletOutputStream {

    private static final int BUFFER_SIZE = 1024;
    private OutputStream outputStream;
    private ByteArrayOutputStream copy;

    /**
     * Constructeur standard
     */
    public ServletOutputStreamCopier(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.copy = new ByteArrayOutputStream(BUFFER_SIZE);
    }

    /**
     * Ecrit un entier
     */
    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
        copy.write(b);
    }

    /**
     * Retourne la String
     */
    public byte[] getCopy() {
        return copy.toByteArray();
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}
