package algorithm.test;

import java.util.Comparator;

public  class MachineComparator implements Comparator<Machine> {
    @Override
    public int compare(Machine o1, Machine o2) {
        return (o1.getAvailableTime() + o1.getCostTime()) - (o2.getAvailableTime() + o2.getCostTime());
    }
}