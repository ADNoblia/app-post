package com.noblia.app;

import com.noblia.app.dtos.PageRequest;
import com.noblia.app.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	@Autowired
	private PageService pageService;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var req = PageRequest
				.builder()
				.userId(4L)
				.title("Debbugeando Ideas")
				.build();

		var res = this.pageService.create(req);
		System.out.println(res);
	}
}
