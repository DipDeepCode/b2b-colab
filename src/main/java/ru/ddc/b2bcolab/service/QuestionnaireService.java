package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.model.Questionnaire;
import ru.ddc.b2bcolab.repository.QuestionnaireRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;

    public Questionnaire saveQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    public Optional<Questionnaire> getQuestionnaireById(Long id) {
        return questionnaireRepository.findById(id);
    }

    public Questionnaire updateQuestionnaire(Questionnaire questionnaire) {
        if (questionnaireRepository.exists(questionnaire.getId())) {
            questionnaireRepository.update(questionnaire);
            return questionnaire;
        }
        return null;
    }

    public boolean deleteQuestionnaire(Long id) {
        if (questionnaireRepository.exists(id)) {
            questionnaireRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
