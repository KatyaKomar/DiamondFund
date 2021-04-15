package by.task.komar.fund;

public class VisualParameters {
    private String color;
    private int transparency;
    private int cut;

    public VisualParameters(String color, int transparency, int cut) {
        this.color = color;
        this.transparency = transparency;
        this.cut = cut;
    }

    public VisualParameters() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    public int getCut() {
        return cut;
    }

    public void setCut(int cut) {
        this.cut = cut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualParameters that = (VisualParameters) o;
        return transparency == that.transparency &&
                cut == that.cut &&
                color.equals(that.color);
    }

    @Override
    public int hashCode() {
        int result = 11;
        result = 37 * result + (color == null ? 0 : color.hashCode());
        result = 37 * result + transparency;
        result = 37 * result + cut;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("VisualParameters{");
        builder.append("color=").append(color);
        builder.append(", transparency=").append(transparency);
        builder.append(", cut=").append(cut);
        builder.append('}');
        return builder.toString();
    }
}