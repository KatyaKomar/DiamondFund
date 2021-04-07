package by.task.komar.fund;

import java.util.Objects;

public class Semiprecious extends Mineral {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Semiprecious that = (Semiprecious) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Semiprecious{" +
                ", name=" + name +
                ", origin='" + this.getOrigin() + '\'' +
                ", parametrs=" + this.getParameters() +
                ", value=" + this.getValue() +
                ", date=" + this.getDate() +
                '}';
    }
}
