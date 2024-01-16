package kp;

/**
 * Test constants.
 *
 */
@SuppressWarnings("doclint:missing")
public final class TestConstants {
	public static final String ROOT_URL = "http://localhost";
	public static final String DEPARTMENTS_URL = ROOT_URL + Constants.DEPARTMENTS_PATH;
	public static final String EMPLOYEES_URL = ROOT_URL + Constants.EMPLOYEES_PATH;

	public static final long DEP_TEST_INDEX_LOWER_BOUND = 1;
	public static final long DEP_TEST_INDEX_UPPER_BOUND = 2;
	public static final long EMP_TEST_INDEX_LOWER_BOUND = 1;
	public static final long EMP_TEST_INDEX_UPPER_BOUND = 2;
	public static final int LARGE_SET_DEPARTMENTS_NUMBER = 15;

	// departments tests
	public static final long EXPECTED_DEPARTMENT_ID = DEP_TEST_INDEX_LOWER_BOUND;
	public static final String EXPECTED_DEPARTMENT_NAME = Constants.DEP_NAME_FUN.apply(DEP_TEST_INDEX_LOWER_BOUND);
	public static final String EXPECTED_DEPARTMENT_ID_URL = String.format("%s/%d", DEPARTMENTS_URL,
			EXPECTED_DEPARTMENT_ID);
	public static final String EXPECTED_DEPARTMENT_ID_EMP_HREF = String.format("%s/employees",
			EXPECTED_DEPARTMENT_ID_URL);

	public static final long CREATED_DEPARTMENT_ID = 12345;
	public static final String CREATED_DEPARTMENT_NAME = Constants.DEP_NAME_FUN.apply(CREATED_DEPARTMENT_ID);
	public static final String CREATED_DEPARTMENT_CONTENT = String.format("{\"id\":%d, \"name\":\"%s\"}",
			CREATED_DEPARTMENT_ID, CREATED_DEPARTMENT_NAME);
	public static final String CREATED_DEPARTMENT_ID_HREF = String.format("%s%s/%d", ROOT_URL,
			Constants.DEPARTMENTS_PATH, CREATED_DEPARTMENT_ID);
	public static final String CREATED_DEPARTMENT_ID_EMP_HREF = String.format("%s%s/%d/employees", ROOT_URL,
			Constants.DEPARTMENTS_PATH, CREATED_DEPARTMENT_ID);

	public static final String UPDATED_DEPARTMENT_NAME = "D-Name-UPDATED";
	public static final String UPDATED_DEPARTMENT_CONTENT = String.format("{\"name\":\"%s\"}", UPDATED_DEPARTMENT_NAME);

	// departments test on third page
	public static final int DEP_TEST_PAGE_NUMBER = 2;
	public static final int DEP_TEST_PAGE_SIZE = 3;
	public static final long DEP_ID_ON_THIRD_PAGE = DEP_TEST_PAGE_NUMBER * DEP_TEST_PAGE_SIZE + 1;
	public static final String DEP_NAME_ON_THIRD_PAGE = Constants.DEP_NAME_FUN.apply(DEP_ID_ON_THIRD_PAGE);
	public static final String DEP_THIRD_PAGE_HREF = String.format("%s/%d", DEPARTMENTS_URL, DEP_ID_ON_THIRD_PAGE);
	public static final String DEP_THIRD_PAGE_EMP_HREF = String.format("%s/%d/employees", DEPARTMENTS_URL,
			DEP_ID_ON_THIRD_PAGE);
	public static final String DEP_FIRST_PAGE_HREF = DEPARTMENTS_URL + "?page=0&size=3&sort=name,asc";
	public static final String DEP_PREV_PAGE_HREF = DEPARTMENTS_URL + "?page=1&size=3&sort=name,asc";
	public static final String DEP_THIS_PAGE_HREF = DEPARTMENTS_URL + "?page=2&size=3&sort=name,asc";
	public static final String DEP_NEXT_PAGE_HREF = DEPARTMENTS_URL + "?page=3&size=3&sort=name,asc";
	public static final String DEP_LAST_PAGE_HREF = DEPARTMENTS_URL + "?page=4&size=3&sort=name,asc";
	public static final String DEP_PROFILE_HREF = ROOT_URL + "/profile/departments";
	public static final String DEP_SEARCH_HREF = DEPARTMENTS_URL + "/search";

	// employees tests
	public static final long EXPECTED_EMPLOYEE_ID = Constants.EMP_INDEX_FUN.applyAsLong(DEP_TEST_INDEX_LOWER_BOUND,
			EMP_TEST_INDEX_LOWER_BOUND);
	public static final String EXPECTED_EMPLOYEE_F_NAME = Constants.EMP_F_NAME_FUN.apply(DEP_TEST_INDEX_LOWER_BOUND,
			EMP_TEST_INDEX_LOWER_BOUND);
	public static final String EXPECTED_EMPLOYEE_L_NAME = Constants.EMP_L_NAME_FUN.apply(DEP_TEST_INDEX_LOWER_BOUND,
			EMP_TEST_INDEX_LOWER_BOUND);

	public static final long CREATED_EMPLOYEE_ID = 67890;
	public static final String CREATED_EMPLOYEE_F_NAME = Constants.EMP_F_NAME_FUN.apply(CREATED_DEPARTMENT_ID,
			CREATED_EMPLOYEE_ID);
	public static final String CREATED_EMPLOYEE_L_NAME = Constants.EMP_L_NAME_FUN.apply(CREATED_DEPARTMENT_ID,
			CREATED_EMPLOYEE_ID);
	public static final String CREATED_EMPLOYEE_CONTENT = String.format(
			"{\"id\":%d, \"firstName\":\"%s\", \"lastName\":\"%s\"}", CREATED_EMPLOYEE_ID, CREATED_EMPLOYEE_F_NAME,
			CREATED_EMPLOYEE_L_NAME);

	public static final String CREATED_EMPLOYEE_ID_HREF = String.format("%s%s/%d", ROOT_URL, Constants.EMPLOYEES_PATH,
			CREATED_EMPLOYEE_ID);
	public static final String CREATED_EMPLOYEE_ID_DEP_HREF = String.format("%s%s/%d/department", ROOT_URL,
			Constants.EMPLOYEES_PATH, CREATED_EMPLOYEE_ID);
	public static final String EXPECTED_EMPLOYEE_ID_URL = String.format("%s%s/%d", ROOT_URL, Constants.EMPLOYEES_PATH,
			EXPECTED_EMPLOYEE_ID);
	public static final String EXPECTED_EMPLOYEE_ID_DEP_HREF = String.format("%s/department", EXPECTED_EMPLOYEE_ID_URL);
	public static final String UPDATED_EMPLOYEE_F_NAME = "EF-Name-UPDATED";
	public static final String UPDATED_EMPLOYEE_L_NAME = "EL-Name-UPDATED";
	public static final String UPDATED_EMPLOYEE_CONTENT = String.format("{\"firstName\":\"%s\", \"lastName\":\"%s\"}",
			UPDATED_EMPLOYEE_F_NAME, UPDATED_EMPLOYEE_L_NAME);

	public static final long ABSENT_EMPLOYEE_ID = 99999;
	public static final String NAME_LIKE = "%01%";

	private TestConstants() {
		throw new IllegalStateException("Utility class");
	}
}