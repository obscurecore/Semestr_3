package ru.potapov.Threads;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadFile implements Runnable {
    Lock lock= new ReentrantLock();
    static String [] s = ThreadPool.getS();



    public static String[] getS() {
        return s;
    }

    public static void download() throws IOException {
        int p = ThreadPool.j-1;
        String idStr = s[p].substring(s[p].lastIndexOf("."));
        ReadableByteChannel readChannel = Channels.newChannel(new URL(s[p]).openStream());
        FileOutputStream fileOS = null;
        fileOS = new FileOutputStream(p+"file" + idStr);
        FileChannel writeChannel = fileOS.getChannel();
        writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);
        readChannel.close();
        writeChannel.close();
    }

    @Override
    public void run() {
        lock.lock();
        try {
            download();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lock.unlock();

    }
}
