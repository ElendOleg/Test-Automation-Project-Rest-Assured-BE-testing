package pojoExampleWithLombok;

import java.util.List;
import lombok.Data;

public @Data class AnimalPojo{
	private String animal;
	private List<ParametersItem> parameters;
	private int age;
}