package net.humanity_game.core.packets;

public abstract class AbstractIncomingPacketListener implements Runnable {

    protected boolean closeRequested;

    @Override
    public abstract void run();

    protected abstract Object readObject();

    protected abstract boolean isDataPresent();

    protected void yield() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void requestClose() {
        this.closeRequested = true;
    }

    public boolean isCloseRequested() {
        return this.closeRequested;
    }
}
