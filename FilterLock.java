public class FilterLock implements Lock 
{

    private final int n;
    private final VolatileInt[] level; //level[i] for thread i
    private final VolatileInt[] victim;//victim L for level[L]

    public FilterLock(int n) 
    {
        this.n = n;
        this.level = new VolatileInt[n];
        this.victim = new VolatileInt[n];

        for(int i = 0; i<n; i++){
            level[i] = new VolatileInt(0); //thread i is in level x
            victim[i] = new VolatileInt(0);//the victim of level i (victim[i]) is thead x

        }

    }

    @Override
    public void lock(int threadId) 
    {
       for(int L = 1; L < this.n; L++) {
            this.level[threadId].value = L;
            this.victim[L].value = threadId;

            boolean problem = true;
            while (problem) {
                problem = false; 
                
                for(int k = 0; k < n; k++) {
                    if (k != threadId && level[k].value >= L && victim[L].value == threadId) {
                        problem = true; 
                        break;           
                    }
                }
            }
        }
    }

    @Override
    public void unlock(int threadId) 
    {
        level[threadId].value = 0;
    }
}