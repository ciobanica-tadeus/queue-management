public class Task implements Comparable <Task>{
    private final int id;
    private int arrivalTime;
    private int serviceTime;

    public Task(int id, int arrivalTime, int executionTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = executionTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public int compareTo(Task o) {
        if( this.arrivalTime > o.getArrivalTime())
            return 1;
        else if( this.arrivalTime < o.getArrivalTime())
            return -1;
        return 0;
    }
}
