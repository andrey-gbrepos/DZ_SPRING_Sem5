package ru.gb.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.ProjectEmploy;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.EmployeeRepository;
import ru.gb.timesheet.repository.ProjectEmployRepository;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TimesheetApplication {

	/**
	 * spring-data - ...
	 * spring-data-jdbc - зависимость, которая предоставляет удобные преднастроенные инструемнты
	 * для работы c реляционными БД
	 * spring-data-jpa - зависимость, которая предоставляет удобные преднастроенные инструемнты
	 * для работы с JPA
	 *      spring-data-jpa
	 *       /
	 *     /
	 *   jpa   <------------- hibernate (ecliselink ...)
	 *
	 * spring-data-mongo - завимость, которая предоставляет инструменты для работы с mongo
	 * spring-data-aws
	 *
	 *
	 */

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);

//		JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
//		jdbcTemplate.execute("delete from project");

		ProjectRepository projectRepo = ctx.getBean(ProjectRepository.class);

		for (int i = 1; i <= 5; i++) {
			Project project = new Project();
			project.setName("Project #" + i);
			projectRepo.save(project);
		}

		EmployeeRepository employeeRepository = ctx.getBean(EmployeeRepository.class);
		String[] employeeNames = {"Viktoria", "Andrey", "Danil", "Maria", "Roman", "Igor", "Veronica"};
		for (int i = 0; i < employeeNames.length; i++) {
			Employee employee = new Employee();
			employee.setName(employeeNames[i]);
			employeeRepository.save(employee);
		}


		TimesheetRepository timesheetRepo = ctx.getBean(TimesheetRepository.class);

		LocalDate createdAt = LocalDate.now();
		for (int i = 1; i <= 30; i++) {
			createdAt = createdAt.plusDays(1);

			Timesheet timesheet = new Timesheet();
			timesheet.setEmployeeId(ThreadLocalRandom.current().nextLong(1, 8));
			timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));

			timesheetRepo.save(timesheet);
		}


	}

}