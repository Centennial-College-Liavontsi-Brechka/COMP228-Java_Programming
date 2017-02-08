package abstracts;

public class PayrollTest
{
	public static void main(String[] args)
	{
		// create subclass objects
		SalariedEmployee salariedEmployee =
				new SalariedEmployee("John", "Smith", "111-11-1111", 800.00);
		HourlyEmployee hourlyEmployee =
				new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40);

		System.out.println("Employees processed individually:");
		System.out.printf("%n%s%n%s: $%,.2f%n%n",
				salariedEmployee, "earned", salariedEmployee.earnings());
		System.out.printf("%s%n%s: $%,.2f%n%n",
				hourlyEmployee, "earned", hourlyEmployee.earnings());

		// create an Employee array
		Employee[] employees = new Employee[2];
		// initialize array with Employees
		employees[0] = salariedEmployee;
		employees[1] = hourlyEmployee;

		//Employee[] employees = {salariedEmployee, hourlyEmployee};

		System.out.printf("Employees processed polymorphically:%n%n");

		// generically process each element in array employees
		for (Employee currentEmployee : employees)
		{
			System.out.println(currentEmployee); // invokes toString
			System.out.printf(
					"earned $%,.2f%n%n", currentEmployee.earnings());
		} // end for

		// get type name of each object in employees array
		for (int j = 0; j < employees.length; j++)
		System.out.printf("Employee %d is a %s%n", j,
		"GetClassNameHere");

	} // end main
} // end class PayrollSystemTest