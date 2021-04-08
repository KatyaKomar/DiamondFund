package by.task.komar.builder;

import by.task.komar.exception.FundException;
import by.task.komar.fund.Mineral;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFundBuilder {
    protected Set<Mineral> minerals;

    public AbstractFundBuilder() {
        minerals = new HashSet<>();
    }

    public Set<Mineral> getMinerals() {
        return minerals;
    }

    public abstract void buildSetMinerals(String filename) throws FundException;
}
