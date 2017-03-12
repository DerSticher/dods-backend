package io.dods.services.rules;

import io.dods.interfaces.repositories.DodsRepository;
import io.dods.model.rules.Rule;

/**
 * @author Richard Gottschalk
 */
interface RuleRepository extends DodsRepository<Rule, Long> {}
