package io.dods.services.regelwerk;

import io.dods.model.rules.Regelwerk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class RegelwerkService {

    @Autowired
    private RegelwerkRepository regelwerkRepository;

    public Regelwerk findById(long regelwerkId) {
        return regelwerkRepository.findById(regelwerkId);
    }
}
