package org.example.frame;

 class ThreadB extends Thread {
    int total;

    @Override
    public void run() {
        synchronized (this){
            for (int i = 0; i < 1; i++) {
                total+=i;
                try {
                    sleep(500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                notify();


            }
        }
    }
}