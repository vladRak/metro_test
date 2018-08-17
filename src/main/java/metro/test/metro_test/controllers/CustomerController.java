package metro.test.metro_test.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import metro.test.metro_test.components.FileExtensionsEnum;
import metro.test.metro_test.components.UniqueFileNameResolver;
import metro.test.metro_test.entities.impl.Customer;
import metro.test.metro_test.services.CsvService;
import metro.test.metro_test.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(
        value = "customer",
        produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.ALL_VALUE},
        consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.ALL_VALUE})
public class CustomerController {

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class.getName());

    @Autowired
    CustomerService customerService;
    @Autowired
    CsvService csvService;
    @Autowired
    ObjectMapper objectMapper;

    @PostMapping
    ResponseEntity<?> createCSV(@RequestBody String string) {

        Customer customer;
        String fileName = UniqueFileNameResolver.generateFileName(FileExtensionsEnum.CSV);
        ObjectNode object;
        try {
            customer = objectMapper.readValue(string, Customer.class);
            //После выполнения метода customer будет уже с ИД.
            customerService.createCustomer(customer);
            //В файл запишется customer с ИД соответствующим ИД в базе.
            csvService.write(fileName, customer);
            object = objectMapper.createObjectNode();
            object.put("file_name", fileName);

        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(object, HttpStatus.CREATED);
    }

    @GetMapping(value = "{uniqueFileName}")
    ResponseEntity<?> getFromCSV(@PathVariable("uniqueFileName") String fileName) {
        Customer customer;
        fileName = fileName + FileExtensionsEnum.CSV.name;
        try {
            customer = (Customer) csvService.read(fileName, Customer.class);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(customer, HttpStatus.OK);
    }
}
