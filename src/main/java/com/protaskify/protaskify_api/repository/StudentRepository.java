package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findAllByEmail(String email);

    @Query(
            value = "SELECT m.student_id, c.class_name, m.content, m.date, m.status, g.group_name as groupName " +
                    "FROM Messages m " +
                    "JOIN ( " +
                    "    SELECT student_id, MAX(date) as MaxDate " +
                    "    FROM Messages " +
                    "    GROUP BY student_id " +
                    ") latestMsg ON m.student_id = latestMsg.student_id AND m.date = latestMsg.MaxDate " +
                    "join student s on s.student_id = m.student_id " +
                    "join class c on s.class_id = c.class_id " +
                    "join groups g on g.group_id = s.group_id " +
                    "where c.semester_id = :semesterId and m.lecturer_id = :lecturerId", nativeQuery = true)
    List<?> getMessagesInfo(String semesterId, String lecturerId);

    @Query(
            "SELECT st FROM Student st"+
                    " JOIN st.group g"+
                    " ON st.group.id = g.id"+
                    " WHERE g.classes.id = :classId AND g.id = :groupId"
    )
    List<Student> findStudentByGroupId(@Param("classId") Long classId, @Param("groupId") Long groupId);

    Student findStudentById(String studentId);

    @Query("SELECT st FROM Student st JOIN Group g ON st.group.id = g.id WHERE st.group.id = :groupId AND st.isLeader = TRUE ")
    Student findLeaderByGroupId(Long groupId);

    @Query("SELECT st FROM Student st JOIN st.classes c ON st.classes.id = c.id WHERE st.group.id IS NULL AND c.id = :classId")
    List<Student> findStudentsWithNullGroupId(Long classId);
}
