package task;

public class SingletonBillPugh {

    private SingletonBillPugh() {
    }

    private static class SingletonHelper {
        private static final SingletonBillPugh INSTANCE = new SingletonBillPugh();


    }

    public static SingletonBillPugh getSingleton() {
        return SingletonHelper.INSTANCE;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
