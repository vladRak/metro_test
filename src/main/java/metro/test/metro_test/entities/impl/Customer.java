package metro.test.metro_test.entities.impl;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import metro.test.metro_test.entities.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "customers")
@Getter
@Setter
@JsonPropertyOrder({"id", "name", "age", "mobile_no"})
public class Customer extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private String mobile_no;
}
