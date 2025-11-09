package org.example.lab5.Controller;

import org.example.lab5.Api.StudentApiResponse;
import org.example.lab5.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController
{
    public static int id = 1;
    ArrayList<Student> students = new ArrayList<>();

    //student cant have GPA more 5.00 or less than 0.01
    @PostMapping("/add")
    public StudentApiResponse addStudent(@RequestBody Student student){
        if (student.getGPA() > 5.00 || student.getGPA() < 0.00){
            return new StudentApiResponse("invalid GPA");
        }
        student.setID(id++);
        students.add(student);
        return new StudentApiResponse("Student added successfully");
    }

    @GetMapping("/get")
    public ArrayList<Student> getAllStudents(){
        return students;
    }

    @PutMapping("/update/{id}")
    public StudentApiResponse updateStudent(@PathVariable int id,@RequestBody Student student){
        for (Student s : students){
            if (s.getID() == id){
                if (student.getName() != null){
                    s.setName(student.getName());
                }
                if (student.getAge() != 0){
                    s.setAge(student.getAge());
                }
                if (student.getDegree() != null){
                    s.setDegree(student.getDegree());
                }
                if (student.getGPA() != 0){
                    s.setGPA(student.getGPA());
                }
                return new StudentApiResponse("Student updated successfully");
            }
        }
        return new StudentApiResponse("Student not found");
    }

    @DeleteMapping("/delete/{id}")
    public StudentApiResponse deleteStudent(@PathVariable int id){
        for (Student s : students){
            if (s.getID() == id){
                students.remove(s);
                return new StudentApiResponse("Student deleted successfully");
            }
        }
        return new StudentApiResponse("Student not found");
    }

    @GetMapping("/honor/{id}")
    public StudentApiResponse honorStudent(@PathVariable int id){
        for (Student s : students){
            if (s.getID() == id){
                if (s.getGPA() >= 4.75){
                    return new StudentApiResponse("First honor student");
                } else if (s.getGPA() >= 4.00) {
                    return new StudentApiResponse("Second honor student");
                }else {
                    return new StudentApiResponse("not an honor student");
                }
            }
        }
        return new StudentApiResponse("Student not found");
    }

    @GetMapping("/avg")
    public ArrayList<Student> getAverageStudents(){
        double average = students.stream()
                .mapToDouble(Student::getGPA)
                .average()
                .orElse(0.0);
        ArrayList<Student> averageStudents = new ArrayList<>();
        for (Student s : students){
            if (s.getGPA() >= average){
                averageStudents.add(s);
            }
        }
        return averageStudents;
    }

    //return a list of all first honor students
    @GetMapping("/honor/first")
    public ArrayList<Student> getFirstHonor(){
        ArrayList<Student> firstHonor = new ArrayList<>();
        for (Student s : students){
            if (s.getGPA() >= 4.75){
                firstHonor.add(s);
            }
        }
        return firstHonor;
    }

    //return a list of all second honor students
    @GetMapping("/honor/second")
    public ArrayList<Student> getSecondHonor(){
        ArrayList<Student> secondHonor = new ArrayList<>();
        for (Student s : students){
            if (s.getGPA() >= 4.00 && s.getGPA() <= 4.74){
                secondHonor.add(s);
            }
        }
        return secondHonor;
    }


}
