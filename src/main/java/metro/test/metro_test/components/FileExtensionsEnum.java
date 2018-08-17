package metro.test.metro_test.components;

public enum FileExtensionsEnum {
    CSV(".csv"), TXT(".txt");

    public final String name;

    FileExtensionsEnum(String name) {
        this.name = name;
    }
}
