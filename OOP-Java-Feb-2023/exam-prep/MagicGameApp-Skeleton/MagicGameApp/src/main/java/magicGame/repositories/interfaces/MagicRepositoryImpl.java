package magicGame.repositories.interfaces;

import magicGame.models.magics.Magic;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static magicGame.common.ExceptionMessages.*;

public class MagicRepositoryImpl implements MagicRepository<Magic>{

    private Map<String, Magic> magicsRepository;

    public MagicRepositoryImpl() {
        this.magicsRepository = new LinkedHashMap<>();
    }

    @Override
    public Collection<Magic> getData() {
        return magicsRepository.values();
    }

    @Override
    public void addMagic(Magic magic) {
        if (magic == null) {
            throw new NullPointerException(INVALID_MAGIC_REPOSITORY);
        }
         magicsRepository.put(magic.getName(), magic);
    }

    @Override
    public boolean removeMagic(Magic magic) {
         return magicsRepository.remove(magic.getName()) != null;
    }

    @Override
    public Magic findByName(String name) {
        return magicsRepository.get(name);
    }
}
