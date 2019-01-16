package by.bsuir.saimmod.entity;

public class WorkFlow {
    private double intencity;
    private double timeLeft;
    private Message message;

    WorkFlow(double intencity)
    {
        this.intencity = intencity;
    }

    public void setMessage(Message message) {
        timeLeft = Generator.getExponentialTime(intencity);
        this.message = message;
    }

    public void minusTime(double time)
    {
        this.timeLeft -= time;
    }

    public double getTimeLeft() {
        return timeLeft;
    }

    public Message getMessage() {
        Message msg = message;
        message = null;
        return msg;
    }

    public boolean isEmpty(){
        return message == null;
    }
}
