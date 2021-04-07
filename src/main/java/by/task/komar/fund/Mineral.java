package by.task.komar.fund;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Mineral {

    private String origin;
    private VisualParameters parameters = new VisualParameters();
    private double value;
    private LocalDate date;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public VisualParameters getParameters() {
        return parameters;
    }

    public void setParameters(VisualParameters parameters) {
        this.parameters = parameters;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mineral mineral = (Mineral) o;
        return Double.compare(mineral.value, value) == 0 &&
                Objects.equals(origin, mineral.origin) &&
                Objects.equals(parameters, mineral.parameters) &&
                Objects.equals(date, mineral.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, parameters, value, date);
    }

    @Override
    public String toString() {
        return "Mineral{" +
                ", origin='" + origin + '\'' +
                ", parameters=" + parameters +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
