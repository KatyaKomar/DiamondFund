package by.task.komar.fund;

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
        return name.equals(precious.name);
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
        builder.append("Precious{");
        builder.append("name=").append(name.getTitle());
        builder.append(", origin=").append(this.getOrigin());
        builder.append(", parameters=").append(this.getParameters());
        builder.append(", value=").append(this.getValue());
        builder.append(", date=").append(this.getDate());
        builder.append('}');
        return builder.toString();
    }
}
