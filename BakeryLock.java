public class BakeryLock implements Lock 
{

    private final int n;
    private final VolatileBoolean[] flag;
    private final VolatileInt[] label;

    public BakeryLock(int n) 
    {
        this.n = n;
        this.flag = new VolatileBoolean[n];
        this.label = new VolatileInt[n];
        
        for (int i = 0; i < n; i++) 
        {
            flag[i] = new VolatileBoolean(false); 
            label[i] = new VolatileInt(0);
        }
    }

    @Override
    public void lock(int threadId) 
    {
        flag[threadId] = new VolatileBoolean(true); 
        int max = 0;
        for (int i = 0; i < n; i++) 
        {
            if (label[i].value > max) 
            {
                max = label[i].value;
            }
        }
        label[threadId].value = max + 1;
        
        for (int k = 0; k < n; k++) 
        {
            if (k == threadId) continue;
            while (flag[k].value && (label[k].value < label[threadId].value || (label[k].value == label[threadId].value && k < threadId))) {}
        }
    }

    @Override
    public void unlock(int threadId) 
    {
        flag[threadId] = new VolatileBoolean(false); 
    }
}