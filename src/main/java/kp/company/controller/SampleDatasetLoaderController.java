package kp.company.controller;

import static kp.Constants.DEP_INDEX_LOWER_BOUND;
import static kp.Constants.DEP_INDEX_UPPER_BOUND;
import static kp.Constants.EMP_F_NAME_FUN;
import static kp.Constants.EMP_INDEX_FUN;
import static kp.Constants.EMP_INDEX_LOWER_BOUND;
import static kp.Constants.EMP_INDEX_UPPER_BOUND;
import static kp.Constants.EMP_L_NAME_FUN;
import static kp.Constants.LOAD_SAMPLE_DATASET_PATH;
import static kp.Constants.LOAD_SAMPLE_DATASET_RESULT;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kp.company.domain.Department;
import kp.company.domain.Employee;
import kp.company.repository.DepartmentRepository;
import kp.company.repository.EmployeeRepository;

/**
 * The sample dataset loader controller.
 *
 * <p>
 * The standard dataset:
 * <ol>
 * <li>Department
 * <ol>
 * <li>Employee
 * <li>Employee
 * </ol>
 * <li>Department
 * <ol>
 * <li>Employee
 * <li>Employee
 * </ol>
 * </ol>
 */
@RestController
public class SampleDatasetLoaderController {
	private static final Log logger = LogFactory.getLog(MethodHandles.lookup().lookupClass().getName());
	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;

	private long depIndexLowerBound = DEP_INDEX_LOWER_BOUND;
	private long depIndexUpperBound = DEP_INDEX_UPPER_BOUND;
	private long empIndexLowerBound = EMP_INDEX_LOWER_BOUND;
	private long empIndexUpperBound = EMP_INDEX_UPPER_BOUND;

	/**
	 * The constructor.
	 * 
	 * @param departmentRepository the {@link DepartmentRepository}
	 * @param employeeRepository   the {@link EmployeeRepository}
	 */
	public SampleDatasetLoaderController(DepartmentRepository departmentRepository,
			EmployeeRepository employeeRepository) {
		this.departmentRepository = departmentRepository;
		this.employeeRepository = employeeRepository;
	}

	/**
	 * The creator used by tests.
	 * 
	 * @param departmentRepository the {@link DepartmentRepository}
	 * @param employeeRepository   the {@link EmployeeRepository}
	 * @param depIndexLowerBound   the {@link Department}'s index lower bound
	 * @param depIndexUpperBound   the {@link Department}'s index upper bound
	 * @param empIndexLowerBound   the {@link Employee}'s index lower bound
	 * @param empIndexUpperBound   the {@link Employee}'s index upper bound
	 * @return the sample dataset loader controller
	 */
	public static SampleDatasetLoaderController of(DepartmentRepository departmentRepository,
			EmployeeRepository employeeRepository, long depIndexLowerBound, long depIndexUpperBound,
			long empIndexLowerBound, long empIndexUpperBound) {

		final SampleDatasetLoaderController controller = new SampleDatasetLoaderController(departmentRepository,
				employeeRepository);
		controller.depIndexLowerBound = depIndexLowerBound;
		controller.depIndexUpperBound = depIndexUpperBound;
		controller.empIndexLowerBound = empIndexLowerBound;
		controller.empIndexUpperBound = empIndexUpperBound;
		return controller;
	}

	/**
	 * Loads the sample dataset for the {@link Department}s with the
	 * {@link Employee}s.<br>
	 * 
	 * @param depIndex the {@link Department}'s index
	 * @param empIndex the {@link Employee}'s index
	 * @return the dataset loading confirmation response
	 */
	@GetMapping(LOAD_SAMPLE_DATASET_PATH)
	public String loadSampleDataset(Long depIndex, Long empIndex) {

		depIndexUpperBound = Optional.ofNullable(depIndex).orElse(DEP_INDEX_UPPER_BOUND);
		empIndexUpperBound = Optional.ofNullable(empIndex).orElse(EMP_INDEX_UPPER_BOUND);
		departmentRepository.deleteAll();
		employeeRepository.deleteAll();
		createAndSaveDepartmentList();
		logger.info(String.format("loadSampleDataset(): dep bound lower/upper[%d/%d], emp bound lower/upper[%d/%d]",
				depIndexLowerBound, depIndexUpperBound, empIndexLowerBound, empIndexUpperBound));
		return LOAD_SAMPLE_DATASET_RESULT;
	}

	/**
	 * Creates the list of {@link Department}s
	 * 
	 */
	private void createAndSaveDepartmentList() {

		List<Department> departmentList = LongStream.rangeClosed(depIndexLowerBound, depIndexUpperBound).boxed()
				.map(Department::fromIndex).toList();
		departmentList = departmentRepository.saveAll(departmentList);
		departmentList.forEach(this::addEmployeesToDepartment);
		departmentRepository.saveAll(departmentList);
	}

	/**
	 * Adds list of the {@link Employee}s to the {@link Department}
	 * 
	 * @param department the {@link Department}
	 */
	private void addEmployeesToDepartment(Department department) {

		List<Employee> employeeList = LongStream.rangeClosed(empIndexLowerBound, empIndexUpperBound).boxed()
				.map(idx -> fromIndexes(department.getId(), idx)).toList();
		employeeList.forEach(employee -> employee.setDepartment(department));
		employeeList = employeeRepository.saveAll(employeeList);
		department.getEmployees().addAll(employeeList);
	}

	/**
	 * Creates the employee from the department index and the employee index.
	 * 
	 * @param depIndex the department index
	 * @param empIndex the employee index
	 * @return the employee
	 */
	public static Employee fromIndexes(long depIndex, long empIndex) {
		return new Employee(EMP_INDEX_FUN.applyAsLong(depIndex, empIndex), EMP_F_NAME_FUN.apply(depIndex, empIndex),
				EMP_L_NAME_FUN.apply(depIndex, empIndex));
	}

}
