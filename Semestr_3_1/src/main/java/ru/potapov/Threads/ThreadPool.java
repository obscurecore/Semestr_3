package ru.potapov.Threads;

import java.util.concurrent.*;

public class ThreadPool {
    static int j=0;
    ExecutorService executor;
    public int coreAmount=2;

    private String[] a = DownloadFile.getS();
    int overrun = 0;
    int maxWorkCount = a.length;
    private static String[] s;

    public static void setS(String[] s) {
        ThreadPool.s = s;
    }

    public static String[] getS() {
        return s;
    }

    public ThreadPool(Builder builder) {
        coreAmount = builder.n;
        this.a = builder.a;
    }



    public void GPool(Runnable threadAPI) throws Exception {
        executor = new ThreadPoolExecutor(coreAmount, coreAmount + overrun, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(maxWorkCount));
        for (j = 0; j < a.length; j++) {
            executor.execute(threadAPI);
        }
        executor.shutdown();
    }

    static class Builder {
        private int n;
        private String[] a;

        public Builder(String[] a) {
            this.a = a;
        }

        public Builder setN(int n) {
            this.n = n;
            return this;
        }

        public ThreadPool build() {
            return new ThreadPool(this);

        }
    }


}
