package algorithm.test;


import lombok.Data;

@Data
public  class Machine {

    private int availableTime;  //泡咖啡的机器可以使用的时间点。
    private int costTime;  //咖啡机泡一杯咖啡需要花费的时间。

    public Machine(int availableTime, int costTime) {
        this.availableTime = availableTime;
        this.costTime = costTime;
    }

}