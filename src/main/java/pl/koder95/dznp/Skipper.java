package pl.koder95.dznp;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class is representation of skipping algorithm system. It manages InputManager
 * to skip ignored read bytes. You can do this by invoking {@link #skip()}. After that
 * you can get last read by using {@link #getLastRead()} or get skipped read bytes by
 * invoking {@link #getSkipped()}.
 *
 * @author Kamil Mularski [Koder95]
 * @version 1.0.0
 */
public class Skipper {

    private final InputStream input;

    /**
     * Create new instance with specified InputStream. This stream will read to skip bytes.
     * @param input stream for reading bytes to skip them
     */
    public Skipper(InputStream input) {
        this.input = input;
    }

    private int skipped = -1;
    private int lastLastRead = -1;
    private int lastRead = -1;

    /**
     * Skips ignored bytes and save results which can be read by {@link #getSkipped()} and
     * {@link #getLastRead()} methods. Before invoking this method getters returns -1.
     *
     * @throws IOException see {@link InputStream#read()}
     */
    public void skip() throws IOException {
        skipped = 0;
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

    /**
     * Returns number of bytes skipped during {@link #skip()} method ran.
     * @return
     *      {@code -1} - before invoking {@link #skip()},
     *      {@code >= 0} - after invoking
     */
    public int getSkipped() {
        return skipped;
    }

    /**
     * Returns the last byte read during {@link #skip()} method ran.
     * @return see {@link InputStream#read()}
     */
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
