package io.dods.services.rules;

import io.dods.model.rules.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    public Rule findById(long ruleId) {
        return ruleRepository.findById(ruleId);
    }
}
