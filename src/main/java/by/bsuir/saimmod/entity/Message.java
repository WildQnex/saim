package by.bsuir.saimmod.entity;

public class Message
{
    private int tactsAmount;
    private boolean isProcessed;

    public void incTact()
    {
        tactsAmount++;
    }

    public int getTactsAmount()
    {
        return tactsAmount;
    }

    public void markProcessed()
    {
        isProcessed = true;
    }

    public boolean isProcessed()
    {
        return isProcessed;
    }
}
