package by.bsuir.saimmod.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StateMachine
{
    Random random = new Random();

    private Map<Integer, Message> currentState = new HashMap<>();
    {
        currentState.put(1, null);
        currentState.put(2, null);
        currentState.put(3, null);
    }
    private List<Message> messages = new ArrayList<>();

    private double p;
    private double pi1;
    private double pi2;
    private double pi3;
    private int messageAmount = 0;

    public StateMachine(double p, double pi1, double pi2, double pi3)
    {
        this.p = p;
        this.pi1 = pi1;
        this.pi2 = pi2;
        this.pi3 = pi3;
    }

    public void performTact()
    {
        Message message = null;

        //pi 3
        if (currentState.get(3) != null)
        {
            currentState.get(3).incTact();
            messageAmount++;
            if (isPi3())
            {
                currentState.get(3).markProcessed();

                currentState.put(3, null);
            }
        }

        //pi 1
        if (currentState.get(1) != null)
        {
            currentState.get(1).incTact();
            messageAmount++;
            if (isPi1())
            {
                if (currentState.get(3) == null)
                {

                    currentState.put(3, currentState.get(1));
                    currentState.put(1, null);
                }
                else
                {
                    currentState.put(1, null);
                }
            }
        }

        //pi 2
        if (currentState.get(2) != null)
        {
            currentState.get(2).incTact();
            messageAmount++;
            if (isPi2())
            {
                if (currentState.get(3) == null)
                {
                    currentState.put(3, currentState.get(2));
                    currentState.put(2, null);
                }
                else
                {
                    currentState.put(2, null);
                }
            }
        }


        //generator
        if (isP())
        {
            message = new Message();
            messages.add(message);
            //message.incTact();
            if (currentState.get(1) == null)
            {
                currentState.put(1, message);
            }
            else if (currentState.get(2) == null)
            {
                currentState.put(2, message);
            }
        }

        //currentState.entrySet().stream().filter((e) -> e.getValue() != null).forEach(msg -> msg.getValue().incTact());
    }

    private boolean isP()
    {
        return random.nextDouble() > p;
    }

    private boolean isPi1()
    {
        return random.nextDouble() > pi1;
    }

    private boolean isPi2()
    {
        return random.nextDouble() > pi2;
    }

    private boolean isPi3()
    {
        return random.nextDouble() > pi3;
    }

    public List<Message> getMessages()
    {
        return messages;
    }

    public int getMessageAmount()
    {
        return messageAmount;
    }
}
