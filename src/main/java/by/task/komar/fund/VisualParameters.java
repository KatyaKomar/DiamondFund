package by.task.komar.fund;

import java.util.Objects;

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

    public void setColor(String color) {
        this.color = color;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    public void setCut(int cut) {
        this.cut = cut;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualParameters that = (VisualParameters) o;
        return transparency == that.transparency &&
                cut == that.cut &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, transparency, cut);
    }

    public int getTransparency() {
        return transparency;
    }

    public int getCut() {
        return cut;
    }

    @Override
    public String toString() {
        return "VisualParameters{" +
                "color='" + color + '\'' +
                ", transparency=" + transparency +
                ", cut=" + cut +
                '}';
    }
}