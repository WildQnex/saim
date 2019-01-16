package by.bsuir.saimmod.entity;

public class Message
{
    public int timeAmount;
    private boolean isKickedOnFirst;
    private boolean isKickedOnSecond;

    public boolean isKickedOnFirst()
    {
        return isKickedOnFirst;
    }

    public boolean isKickedOnSecond()
    {
        return isKickedOnSecond;
    }

    public void kickOnFirst()
    {
            isKickedOnFirst = true;
    }

    public void kickOnSecond()
    {
            isKickedOnSecond = true;
    }

    public boolean isKicked()
    {
        return isKickedOnFirst || isKickedOnSecond;
    }
}
