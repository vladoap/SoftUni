package magicGame.repositories.interfaces;

import magicGame.models.magicians.Magician;
import magicGame.models.magics.Magic;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static magicGame.common.ExceptionMessages.*;

public class MagicianRepositoryImpl implements MagicianRepository<Magician>{

    private Map<String, Magician> magiciansRepository;

    public MagicianRepositoryImpl() {
        this.magiciansRepository = new LinkedHashMap<>();
    }


    @Override
    public Collection<Magician> getData() {
        return magiciansRepository.values();
    }

    @Override
    public void addMagician(Magician magician) {
        if (magician == null) {
            throw new NullPointerException(INVALID_MAGICIAN_REPOSITORY);
        }
        magiciansRepository.put(magician.getUsername(), magician);
    }

    @Override
    public boolean removeMagician(Magician magician) {
        return magiciansRepository.remove(magician.getUsername()) != null;
    }

    @Override
    public Magician findByUsername(String username) {
        return magiciansRepository.get(username);
    }


}
