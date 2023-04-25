package com.mrgrd56.util.fs;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileLinesIterable implements Iterable<String>, AutoCloseable {
    private final List<AutoCloseable> closeables = new ArrayList<>();

    private final Path filePath;

    public FileLinesIterable(Path filePath) {
        this.filePath = filePath;
    }

    @NotNull
    @Override
    public Iterator<String> iterator() {
        try {
            var iterator = new FileLinesIterator(filePath);
            closeables.add(iterator);
            return iterator;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        for (AutoCloseable closeable : closeables) {
            closeable.close();
        }
    }

    private static class FileLinesIterator implements Iterator<String>, AutoCloseable {
        private final BufferedReader fileReader;

        private String nextLine = null;
        private boolean hasNextLineReady = false;
        private boolean isClosed = false;

        public FileLinesIterator(Path filePath) throws IOException {
            fileReader = Files.newBufferedReader(filePath);
        }

        @Override
        public boolean hasNext() {
            if (hasNextLineReady) {
                return nextLine != null;
            }

            try {
                nextLine = fileReader.readLine();
                hasNextLineReady = true;
                boolean result = nextLine != null;
                if (!result) {
                    try {
                        close();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                return result;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String next() throws NoSuchElementException {
            if (hasNextLineReady) {
                hasNextLineReady = false;
                return validated(nextLine);
            } else {
                if (hasNext()) {
                    return nextLine;
                } else {
                    throw new NoSuchElementException();
                }
            }
        }

        private String validated(String element) throws NoSuchElementException {
            if (element == null) {
                throw new NoSuchElementException();
            }

            return element;
        }

        @Override
        public void close() throws Exception {
            if (!isClosed) {
                isClosed = true;
                fileReader.close();
            }
        }
    }
}
