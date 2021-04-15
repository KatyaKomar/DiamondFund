package by.task.komar.fund;

public enum Name {
    ALEXANDRITE("alexandrite"),
    DIAMOND("diamond"),
    EMERALD("emerald"),
    RUBY("ruby"),
    SAPPHIRE("sapphire"),
    CHRYSOBERYL("chrysoberyl");

    private String title;

    Name(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
