package pl.koder95.dznp;

import java.io.IOException;
import java.io.InputStream;

public class Skipper {

    private final InputStream input;

    public Skipper(InputStream input) {
        this.input = input;
    }

    private int skipped = 0;
    private int lastLastRead = -1;
    private int lastRead = -1;

    public void skip() throws IOException {
        next();
        while (input.available() > 0) {
            if (lastRead == '\\') {
                next();
                if (isSpaceOrParagraphChar(lastRead)) lastRead = lastLastRead;
                break;
            }
            if (isSpaceOrParagraphChar(lastRead)) skipSpaceCharSequence();
            if (lastRead == '%') skipCommandSequence();
            if (lastRead == '\n') skipNLSequence();
            if (lastRead == '\n') break;
        }
    }

    private void next() throws IOException {
        this.lastLastRead = this.lastRead;
        this.lastRead = input.read();
    }

    public int getSkipped() {
        return skipped;
    }

    public int getLastRead() {
        return lastRead;
    }

    private void skipSpaceCharSequence() throws IOException {
        while (input.available() > 0) {
            if (isSpaceOrParagraphChar(lastRead)) {
                skipped++;
                next();
            }
            else return;
        }
    }

    private static boolean isSpaceOrParagraphChar(int lastRead) {
        return Character.isSpaceChar(lastRead) && lastRead != '\n';
    }

    private void skipCommandSequence() throws IOException {
        if (lastRead != '%') return;
        if (lastRead == '%' && lastLastRead == '\\') return;
        while (input.available() > 0) {
            if (lastRead == '\n') return;
            else {
                skipped++;
                next();
            }
        }
    }

    private void skipNLSequence() throws IOException {
        if (lastRead != '\n') return;
        while (input.available() > 0) {
            if (lastRead == '\n') {
                skipped++;
                next();
            }
        }
    }
}
