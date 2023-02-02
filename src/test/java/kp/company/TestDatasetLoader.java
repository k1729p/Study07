package kp.company;

import static kp.TestConstants.DEP_TEST_INDEX_LOWER_BOUND;
import static kp.TestConstants.DEP_TEST_INDEX_UPPER_BOUND;
import static kp.TestConstants.EMP_TEST_INDEX_LOWER_BOUND;
import static kp.TestConstants.EMP_TEST_INDEX_UPPER_BOUND;
import static kp.TestConstants.LARGE_SET_DEPARTMENTS_NUMBER;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kp.company.controller.SampleDatasetLoaderController;
import kp.company.repository.DepartmentRepository;
import kp.company.repository.EmployeeRepository;

/**
 * The loader for the test dataset.
 *
 */
@Component
public class TestDatasetLoader {
	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * The constructor.
	 */
	TestDatasetLoader() {
		super();
	}

	/**
	 * Loads standard test dataset.
	 * 
	 */
	public void loadTestDataset() {
		loadTestDataset(DEP_TEST_INDEX_UPPER_BOUND, EMP_TEST_INDEX_UPPER_BOUND);
	}

	/**
	 * Loads large test dataset.
	 * 
	 */
	public void loadLargeTestDataset() {
		loadTestDataset(LARGE_SET_DEPARTMENTS_NUMBER, EMP_TEST_INDEX_UPPER_BOUND);
	}

	/**
	 * Loads test dataset.
	 * 
	 * @param depIndexUpperBound the department index upper bound
	 * @param empIndexUpperBound the employee index upper bound
	 */
	private void loadTestDataset(long depIndexUpperBound, long empIndexUpperBound) {

		final SampleDatasetLoaderController sampleDatasetLoaderController = SampleDatasetLoaderController.of(
				departmentRepository, employeeRepository, DEP_TEST_INDEX_LOWER_BOUND, depIndexUpperBound,
				EMP_TEST_INDEX_LOWER_BOUND, empIndexUpperBound);
		sampleDatasetLoaderController.loadSampleDataset(Optional.of(depIndexUpperBound),
				Optional.of(empIndexUpperBound));
	}
}
