package exam.platform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import exam.platform.dto.request.ModuleRequest;
import exam.platform.entity.Course;
import exam.platform.entity.Module;
import exam.platform.exception.EntityNotFoundException;
import exam.platform.repository.CourseRepository;
import exam.platform.repository.ModuleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Module getModuleById(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Module with id " + id + " not found"));
    }

    public Module createModule(Module module) {
        Long courseId = module.getCourse().getId();

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));

        module.setCourse(course);

        return moduleRepository.save(module);
    }

    public Module updateModule(Long id, ModuleRequest moduleRequest) {
        Module existingModule = moduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Module with id " + id + " not found"));

        if (moduleRequest.getTitle() != null) {
            existingModule.setTitle(moduleRequest.getTitle());
        }

        if (moduleRequest.getOrderIndex() != null) {
            existingModule.setIndex(moduleRequest.getOrderIndex());
        }

        if (moduleRequest.getCourseId() != null) {
            Course course = courseRepository.findById(moduleRequest.getCourseId())
                    .orElseThrow(() -> new EntityNotFoundException("Course with id " + moduleRequest.getCourseId() + " not found"));
            existingModule.setCourse(course);
        }

        return moduleRepository.save(existingModule);
    }

    public void deleteModule(Long id) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Module with id " + id + " not found"));
        moduleRepository.delete(module);
    }
}
