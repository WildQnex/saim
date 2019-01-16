package by.bsuir.saimmod.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StateMachine {
    Random random = new Random();
    Queue queue1;
    Queue queue2;
    WorkFlow workFlow1;
    WorkFlow workFlow2;

    private Map<Integer, Message> currentState = new HashMap<>();

    {
        currentState.put(1, null);
        currentState.put(2, null);
    }

    private List<Message> messages = new ArrayList<>();

    private double inputIntencity;
    private int messsagesAmount;
    private int counter;
    private double currentTime = 0;
    private double timeToNextGeneration = 0D;

    public StateMachine(double inputIntencity, double firstChanelIntencity, double secondChanelIntencity,
                        int messsagesAmount, int firstQueueLength, int secondQueueLength) {
        this.inputIntencity = inputIntencity;
        workFlow1 = new WorkFlow(firstChanelIntencity);
        workFlow2 = new WorkFlow(secondChanelIntencity);
        this.messsagesAmount = messsagesAmount;
        queue1 = new Queue(firstQueueLength);
        queue2 = new Queue(secondQueueLength);
    }

    public void performTact() {
        while (counter < messsagesAmount) {
            if (!workFlow2.isEmpty() && workFlow2.getTimeLeft() <= 0) {
                workFlow2.getMessage();
            }

            if (workFlow2.isEmpty() && queue2.isNotEmpty()) {
                workFlow2.setMessage(queue2.getMessage());
            }

            if (!workFlow1.isEmpty() && workFlow1.getTimeLeft() <= 0) {
                Message message = workFlow1.getMessage();
                if (queue2.isTotallyEmpty()) {
                    if (workFlow2.isEmpty()) {
                        workFlow2.setMessage(message);
                    } else {
                        queue2.addMessage(message);
                    }
                } else if (queue2.isEmpty()) {
                    queue2.addMessage(message);
                } else {
                    message.kickOnSecond();
                }
            }

            if (workFlow1.isEmpty() && queue1.isNotEmpty()) {
                workFlow1.setMessage(queue1.getMessage());
            }

            if (timeToNextGeneration <= 0) {
                counter++;
                timeToNextGeneration += Generator.getExponentialTime(inputIntencity);
                Message message = new Message();
                messages.add(message);
                if (queue1.isTotallyEmpty()) {
                    if (workFlow1.isEmpty()) {
                        workFlow1.setMessage(message);
                    } else {
                        queue1.addMessage(message);
                    }
                } else if (queue1.isEmpty()) {
                    queue1.addMessage(message);
                } else {
                    message.kickOnFirst();
                }
            }
            minusTime(getMinimalTime());
        }
    }

    public Result getResult() {
        Result result = new Result();
        result.Potk = ((double)messages.stream().filter(Message::isKicked).count())/(double) messages.size();
        result.Potk1 = ((double)messages.stream().filter(Message::isKickedOnFirst).count())/(double) messages.size();
        result.Potk2 = ((double)messages.stream().filter(Message::isKickedOnSecond).count())/(((double) messages.size())
                - ((double)messages.stream().filter(Message::isKickedOnFirst).count()));
        return result;
    }

    public double getMinimalTime() {
        double min = timeToNextGeneration;
        if (workFlow1.getTimeLeft() < min && workFlow1.getTimeLeft() > 0) {
            min = workFlow1.getTimeLeft();
        }
        if (workFlow2.getTimeLeft() < min && workFlow2.getTimeLeft() > 0) {
            min = workFlow2.getTimeLeft();
        }
        return min;
    }

    private void minusTime(double time) {
        timeToNextGeneration -= time;
        workFlow1.minusTime(time);
        workFlow2.minusTime(time);
    }
}
