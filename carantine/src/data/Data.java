package data;

/**
 * Created by AleXWerton
 */
public abstract class Data {
    private long enterTime;
    private long exitTime;

    public Data(long enterTime) {
        this.enterTime = enterTime;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }

    public long getEnterTime() {
        return enterTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    public long getProcessTime(){
        return exitTime-enterTime;
    }

    @Override
    public String toString() {
        return "process time:"+getProcessTime()+" ";
    }
}
