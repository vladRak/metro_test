package metro.test.metro_test.components;

public class UniqueFileNameResolver {

    public static String generateFileName(FileExtensionsEnum extension) {
        return new String(String.valueOf(System.currentTimeMillis()) + extension.name);
    }
}
