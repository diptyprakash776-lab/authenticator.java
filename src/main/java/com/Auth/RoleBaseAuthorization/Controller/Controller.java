package com.Auth.RoleBaseAuthorization.Controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.Auth.RoleBaseAuthorization.StudentRepository;
import com.Auth.RoleBaseAuthorization.entity.Student;

@RestController
public class Controller {
	@Autowired
	StudentRepository studentRepository;
	@GetMapping("/students")
	public List<Student>getAllStudents(){
		return studentRepository.findAll();
		
		
	}
	@PostMapping("/students")
	public Student createStduent(@RequestBody Student student) {
		
		return studentRepository.save(student);
	}
	@PutMapping("/students/{id}")
	public Student updaStudent(@PathVariable int id,@RequestBody Student student) {
		Student existing=studentRepository.findById(id).get();
		existing.setName(student.getName());
		existing.setMarks(student.getMarks());
		studentRepository.save(existing);
		return existing;
	}
	 @DeleteMapping("/students/{id}")
	public Map<String,Boolean>delete(@PathVariable int id){
		Student existing= studentRepository.findById(id).get();
		studentRepository.delete(existing);
		Map<String,Boolean>response=new HashMap<String,Boolean>();
		response.put ("Deleted",Boolean.TRUE);
		return response;
		
	}
	
}