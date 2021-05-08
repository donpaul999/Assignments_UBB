package repository;

import model.BaseEntity;
import model.From;
import model.Into;
import model.validators.Validator;
import model.validators.ValidatorException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FileRepository<ID, T extends BaseEntity<ID> & From<String[]> & Into<String[]>> extends InMemoryRepository<ID, T> {
    private final String filePath;
    private final Class<T> tClass;

    public FileRepository(Validator<T> validator, String filePath, Class<T> tClass) {
        super(validator);
        this.filePath = filePath;
        this.tClass = tClass;
        this.loadFile();
    }

    @Override
    public Optional<T> findOne(ID id) {
        return super.findOne(id);
    }

    @Override
    public Iterable<T> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        Optional<T> save = super.save(entity);
        this.writeToFile();
        return save;
    }

    @Override
    public Optional<T> delete(ID id) {
        Optional<T> delete = super.delete(id);
        this.writeToFile();
        return delete;
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        Optional<T> update = super.update(entity);
        this.writeToFile();
        return update;
    }

    /**
     * Loads objects from file.
     * @return the set of objects found in file.
     */
    Set<T> loadFile() {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Path.of(this.filePath)));

            var split = content.split("\\n");
            return Arrays.stream(split)
                    .map(a -> a.replace("\n", "").replace("\r", ""))
                    .map(a -> a.split(","))
                    .map(a -> {
                        try {
                            T object = this.tClass.getDeclaredConstructor().newInstance();
                            object.from(a);
                            super.save(object);
                            return object;
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toSet());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile() {
        try (FileWriter fileWriter = new FileWriter(this.filePath, false);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter))
        {
            this.findAll().forEach(entity -> printWriter.println(String.join(",", entity.into())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
