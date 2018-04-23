package pl.mateusz.exam.scheduler;

import pl.mateusz.exam.beans.Exam;

import java.util.List;

public interface ExamScheduler {
    List<Exam> getExams();
}