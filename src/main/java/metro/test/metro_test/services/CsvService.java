package metro.test.metro_test.services;

import metro.test.metro_test.entities.BaseEntity;
import metro.test.metro_test.entities.impl.Customer;

import java.io.IOException;

public interface CsvService {
    BaseEntity read(String path, Class clazz) throws IOException;

    void write(String fileName, BaseEntity baseEntity) throws IOException;
}
