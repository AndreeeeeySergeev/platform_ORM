package exam.platform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import exam.platform.dto.request.LessonRequest;
import exam.platform.entity.Lesson;
import exam.platform.entity.Module;
import exam.platform.exception.EntityNotFoundException;
import exam.platform.repository.LessonRepository;
import exam.platform.repository.ModuleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModuleRepository moduleRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson with id " + id + " not found"));
    }

    public Lesson createLesson(Lesson lesson) {
        Long moduleId = lesson.getModule().getId();

        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new EntityNotFoundException("Module with id " + moduleId + " not found"));

        lesson.setModule(module);

        return lessonRepository.save(lesson);
    }

    public Lesson updateLesson(Long id, LessonRequest lessonRequest) {
        Lesson existingLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson with id " + id + " not found"));

        if (lessonRequest.getTitle() != null) {
            existingLesson.setTitle(lessonRequest.getTitle());
        }

        if (lessonRequest.getContent() != null) {
            existingLesson.setContent(lessonRequest.getContent());
        }

        if (lessonRequest.getModuleId() != null) {
            Module module = moduleRepository.findById(lessonRequest.getModuleId())
                    .orElseThrow(() -> new EntityNotFoundException("Module with id " + lessonRequest.getModuleId() + " not found"));
            existingLesson.setModule(module);
        }

        return lessonRepository.save(existingLesson);
    }

    public void deleteLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson with id " + id + " not found"));
        lessonRepository.delete(lesson);
    }
}
