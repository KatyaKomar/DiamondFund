package by.task.komar.fund;

import java.util.Objects;

public class Precious extends Mineral {
    private Name name;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Precious precious = (Precious) o;
        return name == precious.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Precious{" +
                ", name=" + name.getTitle() +
                ", origin='" + this.getOrigin() + '\'' +
                ", parameters=" + this.getParameters() +
                ", value=" + this.getValue() +
                ", date=" + this.getDate() +
                '}';
    }
}
