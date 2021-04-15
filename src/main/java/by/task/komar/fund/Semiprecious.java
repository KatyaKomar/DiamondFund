package by.task.komar.fund;

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
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 37 * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Semiprecious{");
        builder.append("name=").append(name);
        builder.append(", origin=").append(this.getOrigin());
        builder.append(", parameters=").append(this.getParameters());
        builder.append(", value=").append(this.getValue());
        builder.append(", date=").append(this.getDate());
        builder.append('}');
        return builder.toString();
    }
}
