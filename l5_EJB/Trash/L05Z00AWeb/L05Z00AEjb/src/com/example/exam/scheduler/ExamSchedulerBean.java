package com.example.exam.scheduler;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.example.exam.beans.Exam;

@Stateless
public class ExamSchedulerBean implements ExamScheduler {
	private final List<Exam> exams = new ArrayList<Exam>();
	{
		Exam exam = new Exam("Angielski");
		exams.add(exam);
		exam = new Exam("SOA");
		exams.add(exam);
		exam = new Exam("Algorytmy");
		exams.add(exam);
		exam = new Exam("Sieci Komputerowe");
		exams.add(exam);
		exam = new Exam("Systemy Operacyjne");
		exams.add(exam);
	}

	public List<Exam> getExams() {
		return exams;
	}
}