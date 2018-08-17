package metro.test.metro_test.services.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import metro.test.metro_test.entities.BaseEntity;
import metro.test.metro_test.entities.impl.Customer;
import metro.test.metro_test.services.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
public class CsvServiceImpl implements CsvService {

    private static Logger logger = LoggerFactory.getLogger(CsvServiceImpl.class.getName());
    private static CsvMapper mapper = new CsvMapper();
    private static CsvSchema schema;

    private CsvSchema getSchema(Class clazz){
        return schema = mapper.schemaFor(clazz);
    }

    public BaseEntity read(String fileName,Class clazz) {

        MappingIterator<Customer> it;
        BaseEntity baseEntity = null;
        try {
            it = mapper
                    .readerWithSchemaFor(clazz)
                    .readValues(new File(fileName));
            baseEntity = it.nextValue();
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return baseEntity;
    }

    public void write(String fileName, BaseEntity baseEntity) {
        ObjectWriter writer = mapper
                .writer(getSchema(baseEntity.getClass()).withLineSeparator("\n"));
        try {
            writer.writeValue(new File(fileName), baseEntity);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
    }

}
